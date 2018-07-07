package com.lzy.j2ee.server.impl.jms.topic;

import com.lzy.j2ee.server.enums.Constant;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by laizhiyuanm on 2017/8/13.
 *
 * 不管是实现队列还是主题消费者，都有两个关键点：
 * 1、实现接口：MessageListener
 * 2、注解：@MessageDriven
 * 队列和主题关键的不同之处在于：
 * 1、destinationType：目的地类型
 * 2、destination：根据目的地类型找到这个类型的目的地名称
 *
 * name:是这个MDB在Jboss容器的JNDI
 * mappedName：是订阅主题的JNDI
 * @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")：
 * 表示消息发送类型是发布/订阅
 * @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")：
 * 定义了消息确认模式，这是使用在把消息交给MDB处理后，容器立即向JMS的providerf发送一个确认
 * @ActivationConfigProperty(propertyName = "clientId", propertyValue = "pub_sub_1"):
 * 定义一个Id
 *
 */
@MessageDriven(
        name = Constant.TopicMessageConsumer1,
        mappedName = "fileDownloadTopic",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "clientId", propertyValue = "my_topic_1"),
                @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "my_topic_1"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/topic/fileDownload")
        }
)
public class TopicMessageConsumer1 implements MessageListener {

    public TopicMessageConsumer1() {
        super();
    }

    @Override
    public void onMessage(Message msg) {
        TextMessage textMessage = (TextMessage) msg;
        try {
            Thread.sleep(20000);
            System.out.println("=============> 我是Topic的1号消费者，我收到消息：" + textMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
