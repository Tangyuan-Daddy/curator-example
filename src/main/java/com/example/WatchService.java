package com.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 汤圆爹爹
 * @title: WatchService
 * @description: 监听示例
 * @date 2020/6/28
 */
public class WatchService {

    private static Logger logger = LoggerFactory.getLogger(WatchService.class);

    private static String PATH = "/watchNode";

    /**
     * PathChildrenCache监听子节点
     * @throws Exception
     */
    public void listenerPath() throws Exception {
        CuratorFramework client = CuratorClientService.getInstance().getCuratorFramework();
        PathChildrenCache cache = new PathChildrenCache(client, PATH, true);
        cache.start();
        PathChildrenCacheListener cacheListener = (curatorFramework, event) -> {
            logger.info("Event Type ：" + event.getType());
            if (null != event.getData()) {
                logger.info("data ：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
            }
        };
        cache.getListenable().addListener(cacheListener);
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * NodeCache监听指定节点
     * @throws Exception
     */
    public void listenerTask() throws Exception {
        CuratorFramework client = CuratorClientService.getInstance().getCuratorFramework();
        NodeCache nodeCache = new NodeCache(client, PATH, false);
        nodeCache.start(true);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData childData = nodeCache.getCurrentData();
                if (childData != null) {
                    logger.info("node data = " + new String(childData.getData()));
                    Stat stat = childData.getStat();
                    logger.info("node stat = " + stat);
                } else {
                    logger.info("node delete.");
                }
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * TreeCache监听整个树的节点
     * @throws Exception
     */
    public void listenerTree() throws Exception {
        CuratorFramework client = CuratorClientService.getInstance().getCuratorFramework();
        TreeCache cache = new TreeCache(client, PATH);
        cache.start();
        TreeCacheListener listener = (curatorFramework, event) -> {
            logger.info("Event Type ：" + event.getType());
            if (null != event.getData()) {
                logger.info("data ：" + event.getData().getPath() + " = " + new String(event.getData().getData()));
            }
        };
        cache.getListenable().addListener(listener);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
