package com.caiwei.demo.lock;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface LockMapper {

    @Insert("insert into user_lock values(#{type})")
    void lock(String type);

    @Delete("delete from user_lock where lock_time = #{type}")
    void unlock(String type);


}

