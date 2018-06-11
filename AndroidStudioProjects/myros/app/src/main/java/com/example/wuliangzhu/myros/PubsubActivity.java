package com.example.wuliangzhu.myros;

import org.jboss.netty.buffer.ChannelBuffer;
import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.ros.android.RosActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jilk.ros.Example;
import com.jilk.ros.Topic;
import com.jilk.ros.rosbridge.implementation.JSON;

import org.ros.android.MessageCallable;
import org.ros.android.RosActivity;
import org.ros.android.view.RosImageView;
import org.ros.android.view.RosTextView;
import org.ros.message.MessageListener;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import org.ros.android.*;
import java.time.LocalTime;

import sensor_msgs.CompressedImage;
import sensor_msgs.Image;

/**
 * @author damonkohler@google.com (Damon Kohler)
 */
public class PubsubActivity extends RosActivity {

    private RosTextView<std_msgs.String> response;
    private RosTextView<std_msgs.String> msg;
//    private RosImageView<sensor_msgs.CompressedImage> camera;
    private RosCameraView camera;
    private Button startMove;
    private Button stopMove;
    private Button startPlay;
    private Button turn;

    private ChessNodeProxy proxy;
    private Talker<std_msgs.String> talker;
    private TopicHandler<CompressedImage> videoHandler;

    public PubsubActivity() {
        // The RosActivity constructor configures the notification title and ticker
        // messages.
        super("Pubsub Tutorial", "Pubsub Tutorial");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pubsub);
        response = (RosTextView<std_msgs.String>) findViewById(R.id.response);
        //response.setTopicName("/android_node/chess_op_ack");
        response.setTopicName("/judge");
        response.setMessageType(std_msgs.String._TYPE);
        response.setMessageToStringCallable(new MessageCallable<String, std_msgs.String>() {
            @Override
            public String call(std_msgs.String message) {
                System.out.println("receive /judge:" + message.getData());
                /*JSONParser parser = new JSONParser();
                try {
                   JSONObject tmp =  (JSONObject) parser.parse(message.getData());
                   int ack_id = (int)(long)(Long) tmp.get("ack_id");
                   JSONArray jsonArray = (JSONArray) tmp.get("ack_args");

                   int len = jsonArray.size();
                   int[] ack_args = new int[len];
                   for (int i = 0; i < len; i++) {
                       ack_args[i] = (int)(long)jsonArray.get(i);
                   }

                   String ack_msg = tmp.get("ack_msg").toString();

                   return ack_msg;
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
                //return "handle error";
                switch (message.getData()){
                    case "startPutChessCommand":
                        return "开始摆棋";
                    case "stopPutChessCommand":
                        return "停止摆棋";
                    case "initChessCommand":
                        return "开始下棋";
                    case "playChessCommand":
                        return "小优下棋";
                }
                return "等待命令";
            }
        });

        this.msg = (RosTextView<std_msgs.String>)findViewById(R.id.msg);
        this.msg.setTopicName("speech_command");
        this.msg.setMessageType(std_msgs.String._TYPE);
        this.msg.setMessageToStringCallable(new MessageCallable<String, std_msgs.String>() {

            public String call(std_msgs.String string) {
                return string.getData();
            }
        });


        // add camera
//        this.camera = (RosImageView<sensor_msgs.CompressedImage>) findViewById(R.id.camera);
        this.camera = (RosCameraView)findViewById(R.id.rossurface);
        this.stopMove = (Button) findViewById(R.id.stopMove);
        this.startMove = (Button) findViewById(R.id.startMove);
        this.turn = (Button) findViewById(R.id.turn);
        this.startPlay = (Button) findViewById(R.id.startPlay);

        View.OnClickListener clicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Talker<std_msgs.String> talker = PubsubActivity.this.talker;

                std_msgs.String message = talker.newMessage();
                String data = null;
                switch(view.getId()){
                    case R.id.startMove:
                        data = "startPutChessCommand";
                        break;
                    case R.id.stopMove:
                        data = "stopPutChessCommand";
                        break;
                    case R.id.startPlay:
                        data = "initChessCommand";
                        break;
                    case R.id.turn:
                        data = "playChessCommand";
                }
                /*JSONObject op = new JSONObject();

                Object tag = view.getTag();
                op.put("op_id", tag == null ? 1001 : Integer.parseInt(tag.toString()));
                op.put("op_args", new JSONArray());

                String opStr = op.toJSONString();*/
                System.out.println("send msg:" + data);

                message.setData(data);

                talker.send(message);
            }
        };

        this.startMove.setOnClickListener(clicker);
        this.stopMove.setOnClickListener(clicker);
        this.startPlay.setOnClickListener(clicker);
        this.turn.setOnClickListener(clicker);
        // add logic
//        camera.setTopicName("/usb_cam_node/image_rect_color/compressed");
////        camera.setTopicName("/usb_cam_node/image_raw");
//        camera.setMessageType(CompressedImage._TYPE);
//        camera.setMessageToBitmapCallable(new MessageCallable<Bitmap, CompressedImage>() {
//            @Override
//            public Bitmap call(CompressedImage compressedImage) {
//                ChannelBuffer buffer = compressedImage.getData();
//                byte[] data = buffer.array();
//                return BitmapFactory.decodeByteArray(data, buffer.arrayOffset(), buffer.readableBytes());
//            }
//        });
//        camera.setMessageToBitmapCallable(new BitmapFromStdImage());

    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        talker = new Talker("/judge", std_msgs.String._TYPE);
        //talker = new Talker("/android_node/chess_op_ask", std_msgs.String._TYPE);
        this.videoHandler = new TopicHandler<>("/usb_cam_node/image_rect_color/compressed",
                CompressedImage._TYPE, new MessageListener<CompressedImage>() {
            @Override
            public void onNewMessage(CompressedImage compressedImage) {
//                long s = System.currentTimeMillis();

                ChannelBuffer buffer = compressedImage.getData();
                byte[] data = buffer.array();
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, buffer.arrayOffset(), buffer.readableBytes());

//                long e = System.currentTimeMillis();

//                System.out.println("used time:" + (e - s));

                PubsubActivity.this.camera.updateMainImage(bitmap);
            }
        });

        this.proxy = new ChessNodeProxy();
        this.proxy.addToNode(this.talker);
        this.proxy.addToNode(this.response);
        this.proxy.addToNode(this.msg);
        this.proxy.addToNode(this.videoHandler);

//        Example.main(null);
        // At this point, the user has already been prompted to either enter the URI
        // of a master to use or to start a master locally.

        // The user can easily use the selected ROS Hostname in the master chooser
        // activity.
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(this.proxy, nodeConfiguration);

//        nodeMainExecutor.execute(talker, nodeConfiguration);
//        // The RosTextView is also a NodeMain that must be executed in order to
//        // start displaying incoming messages.
//        nodeMainExecutor.execute(this.rosTextView, nodeConfiguration);
//        nodeMainExecutor.execute(this.videoHandler, nodeConfiguration);
//        nodeMainExecutor.execute(camera, nodeConfiguration);
    }

    /**
     * 开始下棋
     */
    public void startChess() {

    }

    /**
     * 停止下棋
     */
    public void stopChess() {

    }

    public void startVoice() {

    }

    public void stopVoice() {

    }
}
