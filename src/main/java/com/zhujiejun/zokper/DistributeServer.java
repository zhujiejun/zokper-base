package com.zhujiejun.zokper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class DistributeServer {

    private static final String connectString = "node101:2181,node102:2181,node103:21811";
    private static final String parentNode = "/servers";
    private static final int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;

    // 创建到zk的客户端连接
    public void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, (event) -> {

        });
    }

    // 注册服务器
    public void registServer(String hostname) throws Exception {
        String create = zkClient.create(parentNode + "/server", hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is online " + create);
    }

    // 业务功能
    public void business(String hostname) throws Exception {
        System.out.println(hostname + " is working ...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(args[0]);
        // 1 获取zk连接
        DistributeServer server = new DistributeServer();
        server.getConnect();
        // 2 利用zk连接注册服务器信息
        server.registServer(args[0]);
        // 3 启动业务功能
        server.business(args[0]);
    }
}
