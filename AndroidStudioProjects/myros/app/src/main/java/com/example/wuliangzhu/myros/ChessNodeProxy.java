package com.example.wuliangzhu.myros;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

import java.util.ArrayList;

import std_msgs.String;

/**
 * 通过调用对应节点的服务，进行业务逻辑处理
 * 1 下棋开始，下棋结束；
 * 2 开启语音，关闭语音；
 *
 * Created by wuliangzhu on 2018/3/19.
 */

public class ChessNodeProxy extends AbstractNodeMain {
    private ArrayList<NodeMain> nodes = new ArrayList<>();

    public  void addToNode(NodeMain node) {
        this.nodes.add(node);
    }
    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.newAnonymous();
    }

    @Override
    public void onStart(final ConnectedNode connectedNode) {
        for (NodeMain node : this.nodes
             ) {
            node.onStart(connectedNode);
        }
    }


    @Override
    public void onShutdown(Node node) {

    }
}
