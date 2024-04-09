package com.example.jpastudy.inheritance.join;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@SuperBuilder
@NoArgsConstructor
@Getter
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String name;
    private int price;

}
