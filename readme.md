## 问题点

+ 服务下线后需要很久才会在列表中移除，以及消费者获取到的服务列表更新不及时
+ 设置**服务失效的时间**与**服务续约任务的调用间隔时间**数值与实际执行数值不一致？ 未生效？
+ 集群中服务下线节点不一直，8081 8083 同时停止服务时 各集群节点下线的服务节点不一致



```
#服务续约任务的调用间隔时间 服务注册端使用
eureka.instance.lease-renewal-interval-in-seconds=1
#服务失效的时间 服务端在收到最后一次心跳之后等待的时间上限，超过该事件之后服务端会将该服务示例从服务清单中剔除
eureka.instance.lease-expiration-duration-in-seconds=2
# 获取服务列表间隔时间
eureka.client.registry-fetch-interval-seconds=30
```