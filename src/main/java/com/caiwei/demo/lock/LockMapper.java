package com.caiwei.demo.lock;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface LockMapper {

    @Insert("insert into user_lock values(#{type})")
    void lock(String type);

    @Delete("delete from user_lock where lock_time = #{type}")
    void unlock(String type);



    @Select(" select execute_time from lock_2 for update nowait")
    String queryTaskExecuteTimeById(String taskId);

    @Update(" update lock_2 set execute_time = #{executeTime} where task_id = #{taskId}")
    void updateExecuteTimeById(@Param("taskId") String taskId, @Param("executeTime") String executeTime);

}

