package com.example.lock;

import com.example.client.CuratorClientService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        InterProcessLock lock = new InterProcessMutex(client, "/lockNode");
        IncrementObject incrementObject = new IncrementObject();

        int threadCount = 5;
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new IncrementThread(lock, incrementObject);
            t.setName("T" + (i + 1));
            threadList.add(t);
        }

        Long startTime = System.currentTimeMillis();
        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("end time :" + endTime + ", used time : " + (endTime - startTime));
        System.out.println(incrementObject);
    }
}
