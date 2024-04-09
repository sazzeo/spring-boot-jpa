package com.example.jpastudy.inheritance.join;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@SuperBuilder
@Data
@NoArgsConstructor
public class Album extends Item {
    private String artist;
}
