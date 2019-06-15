package com.mapr.demo.mqtt.simple;

import org.eclipse.paho.client.mqttv3.*;

public class Publisher {


  public static void main(String[] args) throws MqttException {

    String messageString = "Hello World from Java!";
    String user = args[1];
    String pass = args[2];
    String topic = "iot_data";

    if (args.length > 3 ) {
      messageString = args[3];
    }

    if (args.length > 4) {
      topic = args[4];
    }

    String id = MqttClient.generateClientId();
    System.out.println("== START PUBLISHER ==");


    MqttClient client = new MqttClient("tcp://localhost:1883", id);
    MqttConnectOptions connOpts = setUpConnectionOptions(user, pass);
    client.connect(connOpts);
    MqttMessage message = new MqttMessage();
    message.setPayload(messageString.getBytes());
    client.publish(topic, message);

    System.out.println("\tMessage '"+ messageString +"' to " + topic);

    client.disconnect();

    System.out.println("== END PUBLISHER ==");

  }

  private static MqttConnectOptions setUpConnectionOptions(String username, String password) {
    MqttConnectOptions connOpts = new MqttConnectOptions();
    connOpts.setUserName(username);
    connOpts.setPassword(password.toCharArray());
    return connOpts;
  }


}
