package com.rahman.webflux_playground.sec02_r2dbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("customer")
public class Customer {
    @Id
    Integer id;

    String name;
    String email;
}
