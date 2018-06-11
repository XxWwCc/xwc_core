package com.example.wuliangzhu.myros;

import android.graphics.Bitmap;

import com.jilk.ros.rosapi.message.Topic;

import org.ros.android.view.RosImageView;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import  org.ros.message.*;
import org.ros.node.topic.Subscriber;

/**
 * Created by wuliangzhu on 2018/3/20.
 */

public class TopicHandler<T> extends AbstractNodeMain {
    private String topicName;
    private String messageType;
    private MessageListener<T> listener;

    public TopicHandler(String topic, String messageType, MessageListener<T> listener) {
        this.topicName = topic;
        this.messageType = messageType;
        this.listener = listener;
    }
    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of(this.getClass().getSimpleName());
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        Subscriber<T> subscriber = connectedNode.newSubscriber(this.topicName, this.messageType);
        subscriber.addMessageListener(this.listener);
    }

}
