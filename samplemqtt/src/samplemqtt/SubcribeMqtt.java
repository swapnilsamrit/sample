package samplemqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubcribeMqtt implements MqttCallback {

	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		//System.out.println("arg1 "+arg1.getPayload().toString());
		System.out.println(topic );//+ ": " + Arrays.toString(message.getPayload()));	
	}
	void connectI(MqttClient client){
		
		client.setCallback(this);
	}
	public static void main(String[] args) throws MqttException {
		SubcribeMqtt mqtt=new SubcribeMqtt();
		
		MqttClient	client = new MqttClient("tcp://broker.hivemq.com:1883", "Sending");
		client.connect();
		mqtt.connectI(client);
		System.out.println("called ..");
		client.subscribe("#");
	}
}
