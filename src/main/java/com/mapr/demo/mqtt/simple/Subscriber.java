package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.*;

public class Subscriber {

  public static void main(String[] args) throws MqttException {

    String user = args[1];
    String pass = args[2];
    String topic = "iot_data";

    if (args.length > 3) {
      topic = args[3];
    }

    String id = MqttClient.generateClientId();
    System.out.println("== START SUBSCRIBER ==");

    MqttClient client=new MqttClient("tcp://localhost:1883", id);
    MqttConnectOptions connOpts = setUpConnectionOptions(user, pass);
    client.setCallback( new SimpleMqttCallBack() );
    client.connect(connOpts);

    client.subscribe(topic);
  }

  private static MqttConnectOptions setUpConnectionOptions(String username, String password) {
    MqttConnectOptions connOpts = new MqttConnectOptions();
    connOpts.setUserName(username);
    connOpts.setPassword(password.toCharArray());
    return connOpts;
  }

}
