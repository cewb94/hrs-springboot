// package com.mycompany.hrs.service.async;

// import com.mycompany.hrs.entity.HrsAsgDetailsV2;
// import com.mycompany.hrs.repository.HrsAsgDetailsV2Repository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.CompletableFuture;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.*;

// /**
//  * Unit tests for {@link AsgDetailsV2AsyncService}.
//  *
//  * We mock out the repository and verify that each async fetch method:
//  * 1) Delegates to the correct repository call
//  * 2) Returns a CompletableFuture whose value matches the repository’s response
//  */
// @ExtendWith(MockitoExtension.class)
// class AsgDetailsV2AsyncServiceTest {

//     @Mock
//     private HrsAsgDetailsV2Repository repository;

//     @InjectMocks
//     private AsgDetailsV2AsyncService service;

//     private HrsAsgDetailsV2 detail1;
//     private HrsAsgDetailsV2 detail2;

//     @BeforeEach
//     void setUp() {
//         // Create a couple of dummy entities
//         detail1 = new HrsAsgDetailsV2();
//         detail1.setId(1L);
//         detail1.setEmpNumber("E001");

//         detail2 = new HrsAsgDetailsV2();
//         detail2.setId(2L);
//         detail2.setEmpNumber("E002");
//     }

//     @Test
//     void fetchAllAsgDetails_returnsAllEntities() throws Exception {
//         List<HrsAsgDetailsV2> allRows = Arrays.asList(detail1, detail2);
//         when(repository.findAll()).thenReturn(allRows);

//         CompletableFuture<List<HrsAsgDetailsV2>> future = service.fetchAllAsgDetails();
//         List<HrsAsgDetailsV2> result = future.get();

//         assertThat(result)
//             .hasSize(2)
//             .containsExactly(detail1, detail2);

//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void fetchAllAsgDetails_returnsEmptyListWhenNone() throws Exception {
//         when(repository.findAll()).thenReturn(Collections.emptyList());

//         CompletableFuture<List<HrsAsgDetailsV2>> future = service.fetchAllAsgDetails();
//         List<HrsAsgDetailsV2> result = future.get();

//         assertThat(result).isEmpty();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void fetchAsgDetailById_found() throws Exception {
//         when(repository.findById(1L)).thenReturn(Optional.of(detail1));

//         CompletableFuture<HrsAsgDetailsV2> future = service.fetchAsgDetailById(1L);
//         HrsAsgDetailsV2 result = future.get();

//         assertThat(result).isEqualTo(detail1);
//         verify(repository, times(1)).findById(1L);
//     }

//     @Test
//     void fetchAsgDetailById_notFound_returnsNull() throws Exception {
//         when(repository.findById(99L)).thenReturn(Optional.empty());

//         CompletableFuture<HrsAsgDetailsV2> future = service.fetchAsgDetailById(99L);
//         HrsAsgDetailsV2 result = future.get();

//         assertThat(result).isNull();
//         verify(repository, times(1)).findById(99L);
//     }

//     @Test
//     void fetchAsgDetailByEmpNum_found() throws Exception {
//         when(repository.findByEmpNumber("E001")).thenReturn(detail1);

//         CompletableFuture<HrsAsgDetailsV2> future = service.fetchAsgDetailByEmpNum("E001");
//         HrsAsgDetailsV2 result = future.get();

//         assertThat(result).isEqualTo(detail1);
//         verify(repository, times(1)).findByEmpNumber("E001");
//     }

//     @Test
//     void fetchAsgDetailByEmpNum_notFound_returnsNull() throws Exception {
//         when(repository.findByEmpNumber("UNKNOWN")).thenReturn(null);

//         CompletableFuture<HrsAsgDetailsV2> future = service.fetchAsgDetailByEmpNum("UNKNOWN");
//         HrsAsgDetailsV2 result = future.get();

//         assertThat(result).isNull();
//         verify(repository, times(1)).findByEmpNumber("UNKNOWN");
//     }
// }
