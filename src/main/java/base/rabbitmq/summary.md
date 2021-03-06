### rabbitmq
- 消息系统，持久性，投递确认，发布者证实，高可用性
- 消息通过交换机进行路由 如果有复杂的路由需求可以组合交换机或者自己写交换机
- rabbitmq支持多种消息协议中的消息传递
- 核心就是发送消息和接受消息
- 队列存储消息，多个消费者能从一个队列取消息（取的是不同的消息 如果要取相同的消息弄多个队列绑定同一个交换机），多个生产者可以把消息发送给队列


### 虚拟主机
- 一个虚拟主机有一组交换机，对立和绑定 主要权限控制，不能访问不在同一个虚拟主机的消息 默认是/

### 交换机
- exchange转发消息，不做存储，根据路由键（routing key）转发到对应的队列，如果没有绑定的队列消息被丢弃
- 在启用ack模式后，交换机找不到队列会返回错误
- 交换机四种模式 direct， topic， headers， fanout
- direct：先匹配在投送，绑定时设定routing key，然后发消息的时候带上key，然后匹配到了投送 因为一个交换机可以有多个队列，所以路由键来标识，一个交换机到一个队列可以有多个routingkey 如果没有routingkey那就是队列名
- Topic：按规则转发消息（最灵活）队列和交换机的绑定会定义一种路由模式 根据通配符 * # 匹配消息的路由键和路由模式
- Headers：设置header attribute参数类型的交换机
- Fanout：转发消息到所有绑定队列（这个exchange绑定的所有队列）

___

### springboot中使用rabbitmq
-
``` java
Map<String, Object> args = new HashMap<String, Object>();
args.put("x-max-length", 10); // 设置queue的最大长度10
args.put("x-max-length-bytes", 1024); // 设置最大总字节数1KB
channel.queueDeclare("myqueue", false, false, false, args);

```
