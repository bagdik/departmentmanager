package by.departmentmanager.api.controller;

import by.departmentmanager.api.configuration.ApplicationConfig;
import by.departmentmanager.api.model.Department;
import by.departmentmanager.api.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJsonString;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class DepartmentControllerTest {


    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void test_get_all_success() throws Exception {
        List<Department> departments = Arrays.asList(
                new Department(1L, "Marketing", 300.3),
                new Department(2L, "Management", 500.5)
        );
        when(departmentService.getDepartments()).thenReturn(departments);

        mockMvc.perform(get("/department"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].depId", is(1)))
                .andExpect(jsonPath("$[0].depName", is("Marketing")))
                .andExpect(jsonPath("$[0].avgSalary", is(300.3)))
                .andExpect(jsonPath("$[1].depId", is(2)))
                .andExpect(jsonPath("$[1].depName", is("Management")))
                .andExpect(jsonPath("$[1].avgSalary", is(500.5)));

        verify(departmentService, times(1)).getDepartments();
        verifyNoMoreInteractions(departmentService);

    }

    @Test
    public void test_get_by_id_success() throws Exception {
        Department department = new Department(1L, "Marketing", 300.3);
        when(departmentService.getDepartment(1L)).thenReturn(department);
        mockMvc.perform(get("/department/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.depId", is(1)))
                .andExpect(jsonPath("$.depName", is("Marketing")))
                .andExpect(jsonPath("$.avgSalary", is(300.3)));

        verify(departmentService, times(1)).getDepartment( 1L);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(departmentService.getDepartment(1L)).thenReturn(null);

        mockMvc.perform(get("/department/{id}", 1)).andExpect(status().isNotFound());

        verify(departmentService, times(1)).getDepartment(1L);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void test_create_department_success() throws Exception {
        Department department = new Department(1L, "Marketing", 300.3);

        when(departmentService.createDepartment(department)).thenReturn(1);

        mockMvc.perform(post("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(department)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Department created - ", "1"));

        verify(departmentService, times(1)).createDepartment(department);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void test_create_department_fail_404_not_found() throws Exception {
        Department department = null;

        when(departmentService.createDepartment(department)).thenReturn(1);

        mockMvc.perform(post("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(department)))
                .andExpect(status().isBadRequest());

        verify(departmentService, times(0)).createDepartment(department);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void test_update_department_success() throws Exception {
        Department department = new Department(1L, "Marketing", 300.3);

        when(departmentService.getDepartment(1L)).thenReturn(department);
        when(departmentService.updateDepartment(department)).thenReturn(1);

        mockMvc.perform(
                put("/department/{id}", department.getDepId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(department)))
                .andExpect(status().isOk());
        verify(departmentService, times(1)).getDepartment(1L);
        verify(departmentService, times(1)).updateDepartment(department);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void test_update_department_fail_404_not_found() throws Exception {
        Department department = new Department(1L, "Marketing", 300.3);

        when(departmentService.getDepartment(1L)).thenReturn(null);

        mockMvc.perform(
                put("/department/{id}", department.getDepId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(department)))
                .andExpect(status().isNotFound());

        verify(departmentService, times(1)).getDepartment(1L);
        verifyNoMoreInteractions(departmentService);
    }

    // =========================================== Delete User ============================================

    @Test
    public void test_delete_department_success() throws Exception {
        Department department = new Department(1L, "Marketing", 300.3);

        when(departmentService.getDepartment(department.getDepId())).thenReturn(department);
        when(departmentService.deleteDepartment(department.getDepId())).thenReturn(1);

        mockMvc.perform(
                delete("/department/{id}", department.getDepId()))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).getDepartment(department.getDepId());
        verify(departmentService, times(1)).deleteDepartment(department.getDepId());
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void test_delete_user_fail_404_not_found() throws Exception {
        Department department = new Department(1L, "Marketing", 300.3);

        when(departmentService.getDepartment(department.getDepId())).thenReturn(null);

        mockMvc.perform(
                delete("/department/{id}", department.getDepId()))
                .andExpect(status().isNotFound());

        verify(departmentService, times(1)).getDepartment(department.getDepId());
        verifyNoMoreInteractions(departmentService);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}