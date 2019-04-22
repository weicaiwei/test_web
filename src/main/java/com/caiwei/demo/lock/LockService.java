package com.caiwei.demo.lock;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@Log
public class LockService {

    @Autowired
    private LockMapper lockMapper;


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


}
