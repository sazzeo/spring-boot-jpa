package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Setter
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "seq_member",
        allocationSize = 1
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;
}
