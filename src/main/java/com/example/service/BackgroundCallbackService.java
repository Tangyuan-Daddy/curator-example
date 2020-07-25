package com.example.service;

import com.example.client.CuratorClientService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 汤圆爹爹
 * @title: BackgroundCallbackService
 * @description: Curator操作异步回调
 * @date 2020/6/28
 */
public class BackgroundCallbackService {

    private static Logger logger = LoggerFactory.getLogger(BackgroundCallbackService.class);

    // 定义线程池去执行获取异步回调对象
    Executor executor = Executors.newFixedThreadPool(5);

    /** 定义异步回调对象 */
    private BackgroundCallback backgroundCallback = new BackgroundCallback() {
        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
            // 打印返回code码和路径信息
            logger.info("Thread Name = " + Thread.currentThread().getName() + ", Result Code = " + curatorEvent.getResultCode() + ", Path:" + curatorEvent.getPath());
        }
    };

    /**
     * 异步返回操作
     * @throws Exception
     */
    public void backgroundOperation() throws Exception {
        CuratorFramework client = CuratorClientService.getInstance().getCuratorFramework();
        // 循环创建临时节点，通过异步回调对象获取操作的返回信息
        for (int i = 1; i <= 50; i++) {
            client.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(backgroundCallback, executor)
                .forPath("/ephemeral_node/" + i, String.valueOf(i).getBytes());
        }
        // 等待5秒，等待异步回调方法执行
        Thread.sleep(5000);
    }

}
