package com.example.job.companypart.model;

import com.example.job.jobpart.model.Job;
import com.example.job.reviewspart.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "job_table")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Job> jobs;
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

}
