package com.collabera.hcp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Enrollee Model")
public class Enrollee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Id for Enrollee", name = "Id", value = "123")
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
