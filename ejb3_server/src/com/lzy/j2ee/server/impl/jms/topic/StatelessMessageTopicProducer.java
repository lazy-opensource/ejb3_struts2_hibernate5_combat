package com.lzy.j2ee.server.impl.jms.topic;

import com.lzy.j2ee.server.enums.Constant;
import com.lzy.j2ee.server.publicinterface.remote.IStatelessMessageTopicProducer;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 * Created by laizhiyuan on 2017/8/14.
 */
@Stateless(name = Constant.StatelessMessageTopicProducer)
@Remote(IStatelessMessageTopicProducer.class)
public class StatelessMessageTopicProducer implements IStatelessMessageTopicProducer {

    /**
     * 注入连接工厂和目的地
     */
    @Resource(mappedName="java:/ConnectionFactory")
    private TopicConnectionFactory conFactory;
    @Resource(mappedName="java:jboss/exported/jms/topic/fileDownload")
    private Destination destination;

    @Override
    public String send(String msg) {
        TopicConnection connection=null;
        TopicSession session=null;
        try {
            connection=conFactory.createTopicConnection();
            session=connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
            MessageProducer producer=session.createProducer(destination);
            TextMessage message = session.createTextMessage(msg);
            producer.send(message);
            System.out.println("====================> 广播消息：" + message.getText());
            return Constant.SendJmsMessageSuccessCode;
        } catch (JMSException e) {
            e.printStackTrace();
            return Constant.SendJmsMessageFaildCode;
        }finally{
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
                return Constant.SendJmsMessageFaildCode;
            }

        }
    }
}
