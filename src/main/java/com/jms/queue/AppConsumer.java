package com.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class AppConsumer {

	private static final String url="url://127.0.0.1:61616";
	private static final String queueName="queue-test1";
	
	public static void main(String[] args) throws JMSException {
		
		//1.�������ӹ���
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
		//2.�������ݿ�����
		Connection conn=connectionFactory.createConnection();
		
		//3.��������
		conn.start();
		//4.�����Ự����һ���������Ƿ��������д���
		Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.����һ��Ŀ��
		Destination destination=session.createQueue(queueName);
		
		//6.����һ��������
		MessageConsumer consumer=session.createConsumer(destination);
		
		//7.����һ��������
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage text=(TextMessage) message;
				
				try {
					System.out.println("���յ�����Ϣ��"+text.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//8.�ر�����
//		conn.close();
	}
}
