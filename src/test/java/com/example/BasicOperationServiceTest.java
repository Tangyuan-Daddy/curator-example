package com.example;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mk
 * @title: BasicOperationServiceTest
 * @description: BasicOperationService测试
 * @date 2020/6/28
 */
public class BasicOperationServiceTest {

    private BasicOperationService basicOperationService;

    @Before
    public void init() {
        this.basicOperationService = new BasicOperationService();
    }

    @Test
    public void testBasicOperation() {
        try {
            basicOperationService.basicOperation();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
