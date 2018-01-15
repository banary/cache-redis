###基于spring-data-redis+codis的缓存实现

- 为什么使用spring-data-redis
1. 一般webapp基本都会使用spring容器，spring-data-redis提供了丰富的缓存注解
2. spring-data-redis提供了完善的集成方案，可以一次性集成很多缓存服务
3. 使用方便，只需要通过spring编码的方式，实现配置一个自定义的CacheManager就可以
4. 数据序列化方式也可以快速集成切换

- 为什么采用codis？
1. codis实现了高可用的redis集群方案
2. 提供了成熟的客户端jodis，依赖jedis，支持基于zookeeper的负载均衡，支持链接池
3. codis解决了redis主从部署时的节点偏移问题
4. 内部采用codis-proxy, 实现了redis所有对外服务，提供和redis一样无差异的服务
5. 采用zookeeper来管理集群