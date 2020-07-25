package com.example;

import com.example.service.WatchService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mk
 * @title: WatchServiceTest
 * @description: TODO
 * @date 2020/6/29
 */
public class WatchServiceTest {

    private WatchService watchService;

    @Before
    public void init() {
        this.watchService = new WatchService();
    }

    /*@Test
    public void testListenerTask() throws Exception {
        watchService.listenerTask();
    }*/

    @Test
    public void testListenerTaskPath() throws Exception {
        watchService.listenerPath();
    }

    /*@Test
    public void testListenerTree() throws Exception {
        watchService.listenerTree();
    }*/
}
