package com.example.job.companypart.repo;

import com.example.job.companypart.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
