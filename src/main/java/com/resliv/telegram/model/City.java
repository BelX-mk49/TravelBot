package com.resliv.telegram.model;

import com.resliv.telegram.bot.State;
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
public class City extends AbstractBaseEntity {
    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "bot_state", nullable = false)
    @NotBlank
    private State botState;

    @Override
    public String toString() {
        return  "City='" + name + '\'' +
                ", correctAnswer='" + description + '\'' +
                '}';
    }

    public City(String name) {
        this.name = name;
        this.description = "";
    }
}
