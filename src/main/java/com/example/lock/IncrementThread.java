package com.example.lock;

import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mk
 * @title: IncrementThread
 * @description: TODO
 * @date 2020/6/29
 */
public class IncrementThread extends Thread {

    private static Logger logger = LoggerFactory.getLogger(IncrementThread.class);

    private InterProcessLock lock = null;

    private IncrementObject incrementObject;

    public IncrementThread(InterProcessLock lock, IncrementObject incrementObject) {
        this.lock = lock;
        this.incrementObject = incrementObject;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            for (int i = 0; i < 10; i++) {
                lock.acquire();
                logger.info(threadName + " 获取锁");
                incrementObject.setCount(incrementObject.getCount() + 1);
                lock.release();
                logger.info(threadName + " 释放锁");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
