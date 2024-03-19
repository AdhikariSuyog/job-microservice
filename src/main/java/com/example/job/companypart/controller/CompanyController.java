package com.example.job.companypart.controller;

import com.example.job.companypart.model.Company;
import com.example.job.companypart.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company")
@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Company>> findAll() {
        return companyService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        System.out.println("+++++++++++++"+company.toString());
        return companyService.createCompany(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable Long id) {
        return companyService.updateCompany(company, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }


}
