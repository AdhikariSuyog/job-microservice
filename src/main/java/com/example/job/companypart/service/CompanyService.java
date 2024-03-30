package com.example.job.companypart.service;

import com.example.job.companypart.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface CompanyService {
    ResponseEntity<List<Company>> findAll();
    ResponseEntity<Company> createCompany(Company company);
    ResponseEntity<Company> findById(Long id);
    ResponseEntity<Company> updateCompany(Company company, Long id);
    ResponseEntity<String> deleteCompany(Long id);
    Boolean existsById(Long id);
}
