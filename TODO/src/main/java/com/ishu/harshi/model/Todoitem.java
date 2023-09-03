package com.ishu.harshi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="to_do")
public class Todoitem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //variable to store id
    @NotBlank(message = "Description is required")
    private String description;
    @Column(name="COMPLETE" , nullable = false)
    private Boolean complete;
    private Instant created_at;
    private Instant updated_at;

    @Override
    public String toString()
    {
        return String.format("Todoitem{id=%d, description='%s', complete='%s', created_at='%s', updated_at='%s'}",id ,description,complete,created_at,updated_at);
    }
}