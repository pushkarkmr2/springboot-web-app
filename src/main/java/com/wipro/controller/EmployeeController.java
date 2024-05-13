package com.wipro.controller;

import com.wipro.exceptions.CustomBadRequestException;
import com.wipro.exceptions.ResourceNotFoundException;
import com.wipro.model.Employee;
import com.wipro.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        log.info("Get all Employee List.");
        model.addAttribute("allemplist", employeeService.getAll());
        return "index";
    }

    @GetMapping("/addEmp")
    public String addNewEmployee(Model model) {
        log.info("addEmp called.");
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add";
    }
    @PostMapping("/add")
    public String saveEmployee1(@ModelAttribute("employee") Employee employee) {
        log.info("Creating Employee");
        employeeService.addNewEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/updateEmp/{id}")
    public String updateForm(@PathVariable(value = "id") int id, Model model) {
        log.info("Updating Employee.");
        Optional<Employee> employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "update";
    }

    @GetMapping("/deleteEmp/{id}")
    public String deleteThroughId(@PathVariable(value = "id") int id) {
        log.info("Delete Employee.");
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}


