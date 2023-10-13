package com.example.voting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Origami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long origamiId;
    private String name;
    private int votes;
}
