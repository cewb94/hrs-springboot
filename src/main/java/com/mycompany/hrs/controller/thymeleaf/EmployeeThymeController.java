package com.mycompany.hrs.controller.thymeleaf;

import com.mycompany.hrs.dto.Country;
import com.mycompany.hrs.dto.EmployeeForm;
import com.mycompany.hrs.entity.HrsAsgDetailsV2;

import com.mycompany.hrs.service.AsgDetailsV2Service;
import com.mycompany.hrs.service.CountryService;
import com.mycompany.hrs.service.DepartmentService;
import com.mycompany.hrs.service.GradeService;
import com.mycompany.hrs.service.JobService;
import com.mycompany.hrs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This controller serves Thymeleaf web pages for employees.
 */
@Controller
@RequestMapping
public class EmployeeThymeController {

    private final AsgDetailsV2Service asgDetailsService;
    private final DepartmentService departmentService;
    private final JobService jobService;
    private final GradeService gradeService;
    private final EmployeeService employeeService;
    private final CountryService countryService;

    @Autowired
    public EmployeeThymeController(AsgDetailsV2Service asgDetailsService,
                                       DepartmentService departmentService,
                                       JobService jobService,
                                       GradeService gradeService,
                                       EmployeeService employeeService,
                                       CountryService countryService) {
        this.asgDetailsService = asgDetailsService;
        this.departmentService = departmentService;
        this.jobService = jobService;
        this.gradeService = gradeService;
        this.employeeService = employeeService;
        this.countryService = countryService;
    }

    /**
     * Show a list of employees (with assignment details) by querying the view.
     */
    @GetMapping("/employees")
    public String listEmployees(Model model) throws ExecutionException, InterruptedException {
        List<HrsAsgDetailsV2> employees = asgDetailsService.getAllAsgDetails();
        model.addAttribute("employees", employees);
        return "employees";  // Thymeleaf template: employees.html
    }

    /**
     * Show the “Hire Employee” form. 
     * We need to supply:
     *   - EmployeeForm (empty)
     *   - All Departments
     *   - All Jobs
     *   - All Grades
     *   - (Optionally) list of possible supervisors (we can just reuse HrsAsgDetailsV2 list)
     */
    @GetMapping("/new-employee")
    public String showHireForm(Model model) throws ExecutionException, InterruptedException {
        EmployeeForm form = new EmployeeForm();
        model.addAttribute("employeeForm", form);

        // Populate dropdowns: departments, jobs, grades, countries, and possible supervisors
        model.addAttribute("departments", departmentService.getAllDepartments().get());
        model.addAttribute("jobs", jobService.getAllJobs().get());
        model.addAttribute("grades", gradeService.getAllGrades().get());
        model.addAttribute("countries", countryService.getAllCountries()); // ${countries}

        // We also supply a list of existing employees & their names (from view) as possible supervisors:
        model.addAttribute("supervisors", asgDetailsService.getAllAsgDetails());


        return "new-employee";  // Thymeleaf template: new-employee.html
    }

    /**
     * Process the Hire Employee form: 
     *   ○ call the employeeService.hireEmployee(...) method
     *   ○ redirect back to /employees
     */
    @PostMapping("/hire")
    public String hireEmployee(@ModelAttribute("employeeForm") EmployeeForm form) throws ExecutionException, InterruptedException {
        // Map fields to HrsEmployee and HrsAssignment
        com.mycompany.hrs.entity.HrsEmployee e = new com.mycompany.hrs.entity.HrsEmployee();
        e.setEmpNumber(form.getEmpNumber());
        e.setFirstName(form.getFirstName());
        e.setLastName(form.getLastName());
        e.setDob(form.getDob());
        e.setGender(form.getGender());
        e.setNationality(form.getNationality());

        com.mycompany.hrs.entity.HrsAssignment a = new com.mycompany.hrs.entity.HrsAssignment();
        a.setDeptId(form.getDeptId());
        a.setJobId(form.getJobId());
        a.setGradeId(form.getGradeId());
        a.setSuperId(form.getSupervisorId());
        a.setAssiNumber(form.getAssiNumber());
        a.setAssiAction(form.getAssiAction());
        a.setEffStartDate(form.getEffStartDate());
        a.setEffEndDate(form.getEffEndDate());

        employeeService.hireEmployee(e, a).get(); // wait for completion
        return "redirect:/employees";
    }
}