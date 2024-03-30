package com.example.job.companypart.service;

import com.example.job.companypart.model.Company;
import com.example.job.companypart.repo.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Company> createCompany(Company company) {
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Company> findById(Long id) {
        return new ResponseEntity<>(companyRepository.findById(id).orElseThrow(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Company> updateCompany(Company company, Long id) {
        Optional<Company> optionalJob = companyRepository.findById(id);
        if (optionalJob.isPresent()) {
            Company company1 = optionalJob.get();
            company1.setJobs(company.getJobs());
            company1.setName(company.getName());
            company1.setDescription(company.getDescription());
            return new ResponseEntity<>(companyRepository.save(company1), HttpStatus.OK);
        }
        return new ResponseEntity<>(company, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteCompany(Long id) {
        Optional<Company> optionalJob = companyRepository.findById(id);
        if (optionalJob.isPresent()) {
            Company company = optionalJob.get();
            companyRepository.delete(company);
            return new ResponseEntity<>("job with id: " + id + " is deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("job with id: " + id + " is deleted.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }
}
