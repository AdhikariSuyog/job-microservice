package com.example.job.jobpart.controller;

import com.example.job.jobpart.model.Job;
import com.example.job.jobpart.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return jobService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @PostMapping("/jobs/{companyId}")
    public ResponseEntity<Job> createJob(@RequestBody Job job, @PathVariable Long companyId) {
        return jobService.createJob(job, companyId);
    }

    @PutMapping("/jobs/update/{id}")
    public ResponseEntity<Job> updateJob(@RequestBody Job job, @PathVariable Long id) {
        return jobService.updateJob(job, id);
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
}
