package com.paypal.bfs.test.employeecontroller.controller;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Integration Test : Controller
*/

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class EmployeeResourceControllerTest {

    @MockBean
    private EmployeeResource employeeResource;

    @Autowired
    EmployeeResourceController employeeResourceController;

    @Autowired
    private MockMvc mockMvc;


    //Valid Request to retrieve employee
    @Test
    public void getEmployee_valid() throws Exception {

        Employee newEmployee = new Employee(1, "Sam", "Smith", "01-01-2002", new Address("602 N Oliver", "", "Wichita", "KS", "USA", "67208-1418"));

        Mockito.when(employeeResource.findById(1)).thenReturn(java.util.Optional.of(newEmployee));
        mockMvc.perform(MockMvcRequestBuilders.get("/getEmployee/{id}", 1))
                .andExpect(status().isFound())
                .andReturn();
    }

    //Valid Employee input
    @Test
    public void createEmployeeTest_Valid() throws Exception {
        String employee = "{\n" +
                "    \"id\": 0,\n" +
                "    \"first_name\": \"Sagun\",\n" +
                "    \"last_name\": \"Shrestha\",\n" +
                "    \"date_of_birth\": \"07/25/1986\",\n" +
                "    \"address\": {\n" +
                "        \"line1\": \"602 N Oliver\",\n" +
                "        \"line2\": \"\",\n" +
                "        \"city\": \"Wichita\",\n" +
                "        \"state\": \"KS\",\n" +
                "        \"country\": \"USA\",\n" +
                "        \"zip_code\": \"67208-1418\"\n" +
                "    }\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createEmployee")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }

    //Invalid Employee input
    @Test
    public void createEmployeeTest_Invalid() throws Exception {
        String employee = "{\n" +
                "    \"id\": 0,\n" +
                "    \"first_name\": \"\",\n" +
                "    \"last_name\": \"Shrestha\",\n" +
                "    \"date_of_birth\": \"07/25/1986\",\n" +
                "    \"address\": {\n" +
                "        \"line1\": \"602 N Oliver\",\n" +
                "        \"line2\": \"\",\n" +
                "        \"city\": \"Wichita\",\n" +
                "        \"state\": \"KS\",\n" +
                "        \"country\": \"USA\",\n" +
                "        \"zip_code\": \"67208-1418\"\n" +
                "    }\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createEmployee")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is("First Name Required")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }

}
