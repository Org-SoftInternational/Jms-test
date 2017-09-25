package com.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class AppProducer {
	
	private static final String url="tcp://127.0.0.1:61616";
	private static final String queueName="queue-test";
	
	public static void main(String[] args) throws JMSException {
		//1.����ConnectionFactory
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
		//2.��������
			Connection connection=connectionFactory.createConnection();
		//3.��������
			connection.start();
		//4.�����Ự����һ���������Ƿ��������д���
			Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.����һ��Ŀ��
			Destination destination=session.createQueue(queueName);
		//6.����һ��������
			MessageProducer producer=session.createProducer(destination);
		
			for(int i=0;i<500;i++){
				//7.������Ϣ
				TextMessage text=session.createTextMessage("text"+i);
				//8.������Ϣ
				producer.send(text);
				System.out.println("������Ϣ"+text.getText());
			}
			
			//9.�ر�����
			connection.close();
	}
}
