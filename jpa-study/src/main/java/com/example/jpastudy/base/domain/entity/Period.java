package com.example.jpastudy.base.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class Period {

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

}
