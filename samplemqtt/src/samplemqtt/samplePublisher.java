package samplemqtt;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class samplePublisher extends Thread implements MqttCallback {
	MqttClient client;

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("connection lost");
	}
 
	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("Delivery completed");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(topic + ": " + Arrays.toString(message.getPayload()));
		 System.out.println("\nReceived a Message!" +
		            "\n\tTopic:   " + topic +
		            "\n\tMessage: " + new String(message.getPayload()));
	}
	public  MqttClient sub() throws MqttException {
		System.out.println("connect...");
		MqttClient	client = new MqttClient("tcp://localhost:1883", "Sending");
		return this.client=client;
	}
	@Override
	public void run() {
		try {
			client=sub();
		} catch (MqttException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Thread 1..");
		int i=0;
		MqttMessage message = new MqttMessage();
		message.setQos(1);
		message.setPayload("A single message from my computer fff"
				.getBytes());
		try {
			////Thread thread1=new Thread(new SUbscriberThread(client));
			//thread1.start();
			
			while (true) {
				client.publish("foo1", message);
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws MqttException, InterruptedException {
		try {
			new samplePublisher().sub();
			//client.setCallback(this);
			//client.subscribe("foo");
			
			Thread thread2=new Thread(new samplePublisher());
			
			thread2.start();
			MqttMessage message = new MqttMessage();
			//message.setQos(1);
			message.setPayload("A single message from my computer fff"
					.getBytes());
			//new samplePublisher().sub(client);
			System.out.println("kkkk");
			
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
