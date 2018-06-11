package com.example.wuliangzhu.myros;

/**
 * Created by wuliangzhu on 2018/3/15.
 */

/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */


        import org.ros.concurrent.CancellableLoop;
        import org.ros.internal.message.Message;
        import org.ros.namespace.GraphName;
        import org.ros.node.AbstractNodeMain;
        import org.ros.node.ConnectedNode;
        import org.ros.node.NodeMain;
        import org.ros.node.topic.Publisher;

/**
 * A simple {@link Publisher} {@link NodeMain}.
 *
 * @author damonkohler@google.com (Damon Kohler)
 */
public class Talker<T extends Message> extends AbstractNodeMain {
    private String topic_name;
    private String messageType;
    private Publisher<T> publisher;

    public Talker() {
        topic_name = "logger";
    }

    public Talker(String topic, String messageType)
    {
        this.topic_name = topic;
        this.messageType = messageType;
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of(this.getClass().getSimpleName());
    }

    public T newMessage() {
        return  this.publisher.newMessage();
    }

    public void send(T message) {
        this.publisher.publish(message);
    }

    @Override
    public void onStart(final ConnectedNode connectedNode) {
        this.publisher = connectedNode.newPublisher(topic_name, messageType);
//        // This CancellableLoop will be canceled automatically when the node shuts
//        // down.
//        connectedNode.executeCancellableLoop(new CancellableLoop() {
//            private int sequenceNumber;
//
//            @Override
//            protected void setup() {
//                sequenceNumber = 0;
//            }
//
//            @Override
//            protected void loop() throws InterruptedException {
//                std_msgs.String str = publisher.newMessage();
//                str.setData("Hello world! " + sequenceNumber);
//                publisher.publish(str);
//                sequenceNumber++;
//                Thread.sleep(1000);
//            }
//        });
    }
}