# Redis

## 一、技术介绍

### 1.概述

+ Redis是一个基于**内存**的key-value结构的数据库。
+ **优点**：基于内存存储，读写性能高。
+ **适用场景**：适合存储热点数据，比如热点商品、资讯、新闻。



### 2.Redis数据类型

Redis存储的是key-value结构的数据，其中key是字符串类型，value则有以下5种常用的数据类型：

+ **字符串(string)**：普通字符串，是Redis中最简单的数据类型。
+ **哈希(hash)**：也叫散列，类似于Java中的`HashMap`，适合存储Java对象。
+ **列表(list)**：按照插入顺序排序，可以有重复元素，类似于Java中的`LinkedList`。
+ **集合(set)**：无序集合，不能有重复元素，类似于Java中的`HashSet`。
+ **有序集合(sorted set/zset)**：集合中的每个元素关联着一个权重，按照权重升序排序，不能有重复元素，适合存储排行榜、投票…… 

![image-20260108232701420](images/image-20260108232701420.png)

 

---



### 3.Redis常用命令

#### 3.1 字符串操作命令

| 命令                      | 说明                                                   |
| ------------------------- | ------------------------------------------------------ |
| `set key value`           | 设置指定key的value。                                   |
| `get key`                 | 获取指定key的value。                                   |
| `setex key seconds value` | 设置指定key的value，并将key的过期时间设置为seconds秒。 |
| `setnx key value`         | 只有在key不存在时才设置key的value。                    |

示例如下：

![image-20260108235206071](images/image-20260108235206071.png)



---



#### 3.2 哈希操作命令

| 命令                   | 说明                                 |
| ---------------------- | ------------------------------------ |
| `hset key field value` | 为哈希表key设置指定field的value。    |
| `hget key field`       | 获取存储在哈希表中指定field的value。 |
| `hdel key field`       | 删除存储在哈希表中指定field的value。 |
| `hkeys key`            | 获取哈希表中的所有field。            |
| `hvals key`            | 获取哈希表中的所有value。            |

示例如下：

![image-20260109000654290](images/image-20260109000654290.png)



---



#### 3.3 列表操作命令（可重）

| 命令                                     | 说明                                                      |
| ---------------------------------------- | --------------------------------------------------------- |
| `lpush key value1 [value2] ... [valueN]` | 将一个或多个value插入到列表key的头部。                    |
| `lrange key start stop`                  | 获取列表指定范围内的元素。（索引从0开始，-1表示末尾元素） |
| `rpop key`                               | 移除并获取列表最后一个元素。                              |
| `llen key`                               | 获取列表长度。                                            |

示例如下：

![image-20260109001412511](images/image-20260109001412511.png)

![image-20260109001811326](images/image-20260109001811326.png)

![image-20260109001850687](images/image-20260109001850687.png)



---



#### 3.4 集合操作命令（不可重）

| 命令                                       | 说明                              |
| ------------------------------------------ | --------------------------------- |
| `sadd key member1 [member2] ... [memberN]` | 向集合key中添加一个或多个member。 |
| `smembers key`                             | 返回集合中的所有member。          |
| `scard key`                                | 获取集合的member数量。            |
| `sinter key1 [key2] ... [keyN]`            | 返回所有给定集合的交集。          |
| `sunion key1 [key2] ... [keyN]`            | 返回所有给定集合的并集。          |
| `srem key member1 [member2] ... [memberN]` | 删除集合中的一个或多个member。    |

示例如下：

![image-20260109002913135](images/image-20260109002913135.png)



---



#### 3.5 有序集合操作命令（不可重）

| 命令                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `zadd key score1 member1 [score2 member2] ... [scoreN memberN]` | 向有序集合key中添加一个或多个member，同时指定它们的score（double类型）。 |
| `zrange key start stop [withscores]`                         | 返回有序集合中指定区间内的member，可以顺带返回score。（索引从0开始，-1表示末尾元素） |
| `zincrby key increment member`                               | 对有序集合中的指定member的score加上增量increment。           |
| `zrem key member1 [member2] ... [memberN]`                   | 移除有序集合中的一个或多个member。                           |

示例如下：

![image-20260109004509877](images/image-20260109004509877.png)



---



#### 3.6 通用命令

| 命令           | 说明                               |
| -------------- | ---------------------------------- |
| `keys pattern` | 查找所有符合指定模式pattern的key。 |
| `exists key`   | 检查指定key是否存在。              |
| `type key`     | 返回key存储的value的类型。         |
| `del key`      | 若key存在则删除该key。             |

示例如下：

![image-20260109005014035](images/image-20260109005014035.png)



---



### 4.在Java中操作Redis

#### 4.1 Redis的Java客户端

Redis的主流Java客户端有以下几个：

+ Jedis
+ Lettuce
+ Spring Data Redis

其中，Spring Data Redis是Spring的一部分，对Redis底层开发包进行了高度封装。



#### 4.2 Spring Data Redis

1. 导入Spring Data Redis的maven坐标：

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   ```

2. 配置Redis数据源：

   ```yml
   spring:
     redis:
       # 无密码
       host: 
       port: 
       database: 
   ```

3. 编写配置类，创建RedisTemplate对象：

   ```java
   /**
    * Redis配置类
    */
   @Configuration
   @Slf4j
   public class RedisConfig {
   
       @Bean
       public RedisTemplate redisTemplate(RedisConnectionFactory factory){
           log.info("开始创建Redis模板对象...");
           RedisTemplate redisTemplate=new RedisTemplate();
   
           // 设置Redis的连接工厂对象
           redisTemplate.setConnectionFactory(factory);
           // 设置Redis key的序列化器
           redisTemplate.setKeySerializer(new StringRedisSerializer());
   
           return redisTemplate;
       }
   }
   ```

4. 通过RedisTemplate对象操作Redis：

   ```java
   @SpringBootTest
   public class SpringDataRedisTest {
   
       @Autowired
       private RedisTemplate redisTemplate;
   
       @Test
       public void testRedisTemplate(){
           System.out.println(redisTemplate);
       }
   
   
       // 操作字符串类型的数据
       @Test
       public void testString(){
           ValueOperations ops = redisTemplate.opsForValue();
   
           // set
           ops.set("city","Swatow");
   
           // get
           String city = (String) ops.get("city");
           System.out.println("city: "+city);
   
           // setex
           ops.set("code","123456",30, TimeUnit.SECONDS);
   
           // setnx
           ops.setIfAbsent("lock","1");
           ops.setIfAbsent("lock","2");// 设置失败
   
       }
   
       
       // 操作哈希类型的数据
       @Test
       public void testHash(){
           HashOperations ops = redisTemplate.opsForHash();
   
           // hset
           Object key="student";
           ops.put(key,"name","zjl");
           ops.put(key,"age","99");
           ops.put(key,"sex","male");
   
           // hget
           String name = (String) ops.get(key, "name");
           System.out.println("name: "+name);
   
           // hdel
           ops.delete(key,"age");
   
           // hkeys
           System.out.println(ops.keys(key));
   
           // hvals
           System.out.println(ops.values(key));
       }
   }
   ```



---



## 二、项目应用







