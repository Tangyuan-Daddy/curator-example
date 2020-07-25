package com.example.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 汤圆爹爹
 * @title: CuratorClientService
 * @description: Curator客户端对象服务
 * @date 2020/6/28
 */
public class CuratorClientService {

    private static CuratorClientService curatorClientService = null;

    /** Zookeeper连接地址 */
    private static String CONNECT_STRING = "182.92.230.190:2181,182.92.230.190:2182,182.92.230.190:2183";

    private CuratorFramework curatorFramework;

    private CuratorClientService() {
        curatorFramework = getCuratorClient();
    }

    private CuratorFramework getCuratorClient() {
        // 定义重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 创建客户端连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_STRING)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                // 异常重试策略
                .retryPolicy(retryPolicy)
                // 命名空间(以后创建节点都在这个节点之下)
                .namespace("myTestPath")
                .build();
        /*CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECT_STRING, 5000, 5000, retryPolicy);*/
        // 开启连接
        client.start();
        return client;
    }

    /**
     * 获取实例
     * @return
     */
    public static CuratorClientService getInstance() {
        if (curatorClientService == null) {
            synchronized (CuratorClientService.class) {
                if (curatorClientService == null) {
                    curatorClientService = new CuratorClientService();
                }
            }
        }
        return curatorClientService;
    }

    /**
     * 获取Curator客户端连接实例
     * @return
     */
    public CuratorFramework getCuratorFramework() {
        return this.curatorFramework;
    }
}
