package com.example.job.jobpart.repo;

import com.example.job.jobpart.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
