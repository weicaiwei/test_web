package com.caiwei.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: Department
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/24 15:05
 */
@Data
public class Department {

    private Integer id;
    private String name;
    private List<Employee> employees;


}
