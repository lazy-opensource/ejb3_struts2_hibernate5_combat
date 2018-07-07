package com.lzy.j2ee.server.impl.jms.p2p;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessMessageQueueProducer;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 * Created by laizhiyuan on 2017/8/14.
 */
@Stateless(name = Constant.StatelessMessageQueueProducer)
@Remote(IStatelessMessageQueueProducer.class)
public class StatelessMessageQueueProducer implements IStatelessMessageQueueProducer {

    //注入连接工厂和目的地
    @Resource(mappedName="java:/ConnectionFactory")
    private QueueConnectionFactory conFactory;

    @Resource(mappedName="java:jboss/exported/jms/queue/fileUpload")
    private Destination destination;


    @Override
    public String send(String msg) {
        QueueConnection queueConnection=null;
        QueueSession session=null;
        try {
            //创建队列连接
            queueConnection=conFactory.createQueueConnection();
            session=queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            //创建消息提供者
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(msg);
            //发送消息
            producer.send(message);
            System.out.println("=====================> 发送队列消息：" + message.getText());
            return Constant.SendJmsMessageSuccessCode;
        } catch (JMSException e) {
            e.printStackTrace();
            return Constant.SendJmsMessageFaildCode;
        }finally{
            try {
                session.close();
                queueConnection.close();
            } catch (JMSException e) {
                e.printStackTrace();
                return Constant.SendJmsMessageFaildCode;
            }

        }
    }
}
