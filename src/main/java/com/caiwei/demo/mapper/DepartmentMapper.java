package com.caiwei.demo.mapper;

import com.caiwei.demo.model.Department;
import com.caiwei.demo.model.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: DepartmentMapper
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/4/24 15:11
 */
@Mapper
@Repository
public interface DepartmentMapper {

    @Results(id="department", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "employees", column = "id", many =
            @Many(select = "queryEmployeeByDepartmentId"))
    })
    @Select("select * from department where id = #{id}")
    Department queryDepartment(Integer id);

    @Select("select * from employee where d_id = #{id}")
    List<Employee> queryEmployeeByDepartmentId(Integer departmentId);


}
