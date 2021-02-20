package com.collabera.hcp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "Name must be provided!")
    private String name;
    @NotNull(message = "Birthday must be provided!")
    private Date birthDate;
    @NotNull(message = "Activation status must be provided!")
    private Boolean status;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Dependent> dependents;
}
