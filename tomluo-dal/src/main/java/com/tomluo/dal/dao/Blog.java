package com.tomluo.dal.dao;

import lombok.Data;

@Data
public class Blog {
    private Integer bid;

    private String name;

    private Integer authorId;

}