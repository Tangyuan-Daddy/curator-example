package com.example.lock;

import com.example.CuratorClientService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;

/**
 * @author mk
 * @title: TestIncrementThread
 * @description: TODO
 * @date 2020/6/29
 */
public class TestIncrementThread {

    @Test
    public void testIncrementThread() throws InterruptedException {
        CuratorFramework client = CuratorClientService.getInstance().getCuratorFramework();
        InterProcessLock lock = new InterProcessMutex(client, "/myLock");
        IncrementObject incrementObject = new IncrementObject();

        Thread t1 = new IncrementThread(lock, incrementObject);
        t1.setName("t1");

        Thread t2 = new IncrementThread(lock, incrementObject);
        t2.setName("t2");

        Thread t3 = new IncrementThread(lock, incrementObject);
        t3.setName("t3");

        Thread t4 = new IncrementThread(lock, incrementObject);
        t4.setName("t4");

        Thread t5 = new IncrementThread(lock, incrementObject);
        t5.setName("t5");

        Long startTime = System.currentTimeMillis();
        System.out.println("start time :" + startTime);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        Long endTime = System.currentTimeMillis();
        System.out.println("end time :" + endTime + ", used time : " + (endTime - startTime));
        //Thread.sleep(10000);
        System.out.println(incrementObject);
    }
}
