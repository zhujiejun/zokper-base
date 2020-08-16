package com.zhujiejun.zokper;

import com.google.common.collect.Lists;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class DistributeClient {

    private static final String connectString = "node101:2181,node102:2181,node103:21811";
    private static final String parentNode = "/servers";
    private static final int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;

    // 创建到zk的客户端连接
    public void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, (event) -> {
            // 再次启动监听
            try {
                getServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 获取服务器列表信息
    public void getServerList() throws Exception {
        // 1 获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zkClient.getChildren(parentNode, true);
        // 2 存储服务器信息列表
        List<String> servers = Lists.newArrayList();
        // 3 遍历所有节点，获取节点中的主机名称信息
        for (String child : children) {
            byte[] data = zkClient.getData(parentNode + "/" + child, false, null);
            servers.add(new String(data));
        }
        // 4 打印服务器列表信息
        System.out.println(servers);
    }

    // 业务功能
    public void business() throws Exception {
        System.out.println("client is working ...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        // 1 获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnect();
        // 2 获取servers的子节点信息，从中获取服务器信息列表
        client.getServerList();
        // 3 业务进程启动
        client.business();
    }
}
