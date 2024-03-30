package com.example.job.jobpart.controller;

import com.example.job.jobpart.model.Job;
import com.example.job.jobpart.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Job>> findAll() {
        return jobService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @PostMapping("/company/{companyId}")
    public ResponseEntity<Job> createJob(@RequestBody Job job, @PathVariable Long companyId) {
        return jobService.createJob(job, companyId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@RequestBody Job job, @PathVariable Long id) {
        return jobService.updateJob(job, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
}
