package com.resliv.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cities extends AbstractBaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String city;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Override
    public String toString() {
        return  "City='" + city + '\'' +
                ", correctAnswer='" + description + '\'' +
                '}';
    }
}
