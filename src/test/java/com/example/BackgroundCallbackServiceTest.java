package com.example;

import com.example.service.BackgroundCallbackService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mk
 * @title: BackgroundCallbackServiceTest
 * @description: CuratorBackgroundService测试
 * @date 2020/6/28
 */
public class BackgroundCallbackServiceTest {

    private BackgroundCallbackService backgroundCallbackService;

    @Before
    public void init() {
        this.backgroundCallbackService = new BackgroundCallbackService();
    }

    @Test
    public void testBackgroundOperation() throws Exception {
        backgroundCallbackService.backgroundOperation();
    }
}
