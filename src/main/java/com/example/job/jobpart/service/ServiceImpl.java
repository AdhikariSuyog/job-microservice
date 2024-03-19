package com.example.job.jobpart.service;

import com.example.job.companypart.model.Company;
import com.example.job.companypart.service.CompanyService;
import com.example.job.jobpart.model.Job;
import com.example.job.jobpart.repo.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CompanyService companyService;

    public ServiceImpl(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>( jobRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Job> createJob(Job job, Long companyId) {
        Company company = companyService.findById(companyId).getBody();
        job.setCompany(company);
        jobRepository.save(job);
        return new ResponseEntity<>(jobRepository.save(job),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Job> findById(Long id) {
        return new ResponseEntity<>(jobRepository.findById(id).orElseThrow(),HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Job> updateJob(Job job, Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job1 = optionalJob.get();
            job1.setLocation(job.getLocation());
            job1.setMaxSalary(job.getMaxSalary());
            job1.setMinSalary(job.getMinSalary());
            job1.setTitle(job.getTitle());
            job1.setDescription(job.getDescription());
            job1.setCompany(job.getCompany());
            return new ResponseEntity<>(jobRepository.save(job1), HttpStatus.OK);
        }
       return new ResponseEntity<>(job,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteJob(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            jobRepository.delete(job);
            return new ResponseEntity<>("job with id: "+id +" is deleted." ,HttpStatus.OK);

        }
        return new ResponseEntity<>("job with id: "+id +" is deleted." ,HttpStatus.BAD_REQUEST);
    }
}
