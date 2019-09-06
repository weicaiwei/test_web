package com.caiwei.demo.lock;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log
public class LockService {

    @Autowired
    private LockMapper lockMapper;


    //基于insert的锁
    public void lock(String lockType) {
        for (; ; ) {
            try {
                lockMapper.lock(lockType);
                break;
            } catch (DuplicateKeyException e) {
                log.info("获取锁失败，继续尝试中");
            }
        }

    }

    public void unlock(String lockType) {
        try {
            lockMapper.unlock(lockType);
        } catch (Exception e) {
            log.info("删除锁失败");
        }
    }


    //通过事务和悲观锁(select for update | nowait 在事务中起作用)实现非阻塞锁，当然如果sql语句不加nowait即可作为阻塞锁使用
    @Transactional
    public void testTask() {
        try {
            String lastExecuteTime = lockMapper.queryTaskExecuteTimeById("1000");
            String nowTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            if(!nowTime.equals(lastExecuteTime)){
                System.out.println("线程：" + Thread.currentThread().getName() + "在执行程序中");
                Thread.sleep(2 * 1000);
                lockMapper.updateExecuteTimeById("1000", nowTime);
            }else {
                System.out.println(nowTime+"已执行过此定时任务");
            }
        } catch (Exception e) {
            if (e instanceof UncategorizedSQLException) {
                System.out.println(Thread.currentThread().getName() + "本服务器未抢到锁");
            } else {
                try {
                    throw e;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }finally {
            System.out.println();
        }

    }


}
