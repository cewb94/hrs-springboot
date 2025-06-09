package com.mycompany.hrs.service.async;

import com.mycompany.hrs.entity.HrsAsgDetailsV2;
import com.mycompany.hrs.entity.HrsEmployee;
import com.mycompany.hrs.repository.HrsAsgDetailsV2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AsgDetailsV2AsyncService}.
 *
 * We mock out the repository and verify that each async fetch method:
 * 1) Delegates to the correct repository call
 * 2) Returns a CompletableFuture whose value matches the repository’s response
 */
@ExtendWith(MockitoExtension.class)
class AsgDetailsV2AsyncServiceTest {

    // This instance (class) variable is found in the service we are unit testing
    @Mock
    private HrsAsgDetailsV2Repository repository;

    // this is the service we are unit testing
    @InjectMocks
    private AsgDetailsV2AsyncService service;

    private HrsAsgDetailsV2 detail1;
    private HrsAsgDetailsV2 detail2;

    @BeforeEach
    void setUp() {
        // Create immutable/mock entities, stub only getters
        // HrsEmployee mockEmp1 = mock(HrsEmployee.class, "mockEmp1");
        // when(mockEmp1.getEmpId()).thenReturn(6L);
        // when(mockEmp1.getEmpNumber()).thenReturn("13");
        // when(mockEmp1.getFirstName()).thenReturn("Fatma");
        // when(mockEmp1.getLastName()).thenReturn("Saad");

        detail1 = mock(HrsAsgDetailsV2.class, "detail1");
        // when(detail1.getAssiId()).thenReturn(6L);
        //when(detail1.getEmployee()).thenReturn(mockEmp1);
        when(detail1.getEmpNumber()).thenReturn("13");
        // when(detail1.getAssiNumber()).thenReturn("E13");
        when(detail1.getFullName()).thenReturn("Fatma Saad");
        
        // HrsEmployee mockEmp2 = mock(HrsEmployee.class, "mockEmp2");
        // when(mockEmp2.getFirstName()).thenReturn("Bader");when(mockEmp2.getLastName()).thenReturn("Alnaser");
        // when(mockEmp2.getEmpId()).thenReturn(2L);
        // when(mockEmp2.getEmpNumber()).thenReturn("11");

        detail2 = mock(HrsAsgDetailsV2.class, "detail2");
        // when(detail2.getAssiId()).thenReturn(2L);
        //when(detail2.getEmployee()).thenReturn(mockEmp2);
        when(detail2.getEmpNumber()).thenReturn("20");
        // when(detail2.getAssiNumber()).thenReturn("E20");
        when(detail2.getFullName()).thenReturn("Bader Alnaser");
    }

    @Test
    void fetchAllAsgDetails_returnsAllEntities() throws Exception {
        // Arrange: make the repository return our two samples
        when(repository.findAll()).thenReturn(Arrays.asList(detail1, detail2));

        // Act: call the async service method
        CompletableFuture<List<HrsAsgDetailsV2>> future = service.fetchAllAsgDetails();
        List<HrsAsgDetailsV2> actual = future.get();

        // Assert: size and content
        assertThat(actual).hasSize(2);

        // check first element
        HrsAsgDetailsV2 first = actual.get(0);
        assertThat(first.getFullName()).isEqualTo("Fatma Saad");
        assertThat(first.getEmpNumber()).isEqualTo("13");

        // check second element
        HrsAsgDetailsV2 second = actual.get(1);
        assertThat(second.getFullName()).isEqualTo("Bader Alnaser");
        assertThat(second.getEmpNumber()).isEqualTo("20");

        // Verify interaction
        verify(repository).findAll();
    }

}