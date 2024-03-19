package com.example.job.jobpart.service;

import com.example.job.jobpart.model.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface JobService {
    ResponseEntity<List<Job>> findAll();
    ResponseEntity<Job> createJob(Job job, Long companyId);
    ResponseEntity<Job> findById(Long id);
    ResponseEntity<Job> updateJob(Job job, Long id);
    ResponseEntity<String> deleteJob(Long id);
}
