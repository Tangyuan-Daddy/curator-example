package com.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 汤圆爹爹
 * @title: BasicOperationService
 * @description: Curator基础操作Service
 * @date 2020/6/28
 */
public class BasicOperationService {

    private static Logger logger = LoggerFactory.getLogger(BasicOperationService.class);

    /**
     * 基础操作（新增、删除节点，获取和更新节点的值）
     * @throws Exception
     */
    public void basicOperation() throws Exception {
        // 获取客户端实例
        CuratorFramework client = CuratorClientService.getInstance().getCuratorFramework();
        // 创建一个持久节点，并赋值（4种节点类型）
        client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/myTestNode", "initNodeData".getBytes());
        // 获取节点的data数据
        logger.info("Node data = " + new String(client.getData().forPath("/myTestNode")));
        // 更新节点的data数据
        client.setData().forPath("/myTestNode", "updateNodeData".getBytes());
        logger.info("Node data = " + new String(client.getData().forPath("/myTestNode")));
        // 删除节点
        client.delete().deletingChildrenIfNeeded().forPath("/myTestNode");
    }

}
