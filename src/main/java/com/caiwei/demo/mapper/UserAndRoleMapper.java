package com.caiwei.demo.mapper;

import com.caiwei.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @ClassName: UserAndRoleMapper
 * @Description: TODO
 * @auther: caiwei
 * @date: 2019/6/25 23:30
 */
@Repository
@Mapper
public interface UserAndRoleMapper {

    @Results(id = "userMap", value = {
            @Result(column = "username", property = "username", javaType = String.class,jdbcType = JdbcType.VARCHAR ),
            @Result(column = "password", property = "password", javaType = String.class,jdbcType = JdbcType.VARCHAR ),
            @Result(column = "phone",    property = "phone",    javaType = String.class,jdbcType = JdbcType.VARCHAR ),
            @Result(column = "email",    property = "email",    javaType = String.class,jdbcType = JdbcType.VARCHAR ),
            @Result(column = "image_url", property = "imageUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR ),
            @Result(column = "last_ip",   property = "lastIp",  javaType = String.class,jdbcType = JdbcType.VARCHAR ),
            @Result(column = "last_time", property = "lastTime",javaType = String.class,jdbcType = JdbcType.VARCHAR )
    })

    //通过手机号查询用户信息
    @Select(" select username, password, email, phone, image_url, last_ip, last_time " +
            " from oauth_user" +
            " where phone=#{username}")
    User queryUserByPhone(String username);

    //通过手机号查询用户角色
    @Select(" select role_name roleName" +
            " from oauth_user_role" +
            " where user_name=#{username}")
    Set<String> queryRoleByPhone(String phone);
}
