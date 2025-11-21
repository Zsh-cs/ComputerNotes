# 09-Network Communication(网络通信)

> 鸣谢：黑马程序员
>
> ![image-20251110175223940](images/image-20251110175223940.png)

> [!TIP]
>
> Previous Chapter(上一章)：08-Multithreading(多线程)
>
> + [Markdown](../08-Multithreading(多线程)/08-Multithreading(多线程).md)
> + [PDF](../08-Multithreading(多线程)/08-Multithreading(多线程).pdf)
> + [HTML](../08-Multithreading(多线程)/08-Multithreading(多线程).html)
>
> Next Chapter(下一章)：10-Reflection(反射)
>
> + [Markdown](../10-Reflection(反射)/10-Reflection(反射).md)
> + [PDF](../10-Reflection(反射)/10-Reflection(反射).pdf)
> + [HTML](../10-Reflection(反射)/10-Reflection(反射).html)



[TOC]

## 一、概述

+ 网络编程可以让设备中的程序和网络上其他设备中的程序进行网络通信，实现数据交互。
+ `java.net`包下提供了网络编程的解决方案。



## 二、前置知识

### 1.基本通信架构

+ **CS架构**：Client-Server（客户端-服务器）
+ **BS架构**：Browser-Server（浏览器-服务器）



### 2.网络通信三要素

#### 2.1 IP地址：设备在网络中的唯一标识	

##### P1 IP地址的两种形式

+ **IPv4**：32bits(4B)，一般采用点分十进制表示。如192.168.1.66。
+ **IPv6**：
  + 128bits(16B)，一般采用冒分十六进制表示法，即分成8段表示，每段有16bits，其中每4bits转成一个十六进制数表示，段之间用冒号隔开。如2001:0DB8:0000:0000:0008:0800:200C:417A。
  + 为了书写方便，IPv6还提供了压缩格式，具体压缩规则为：每组中的前导0都可以省略。因此上述地址可写为：2001:0DB8:0:0:8:800:200C:417A
  + 如果存在连续两个及以上均为0的段，可以用双冒号来代替，因此上述地址可进一步简写为：2001:0D88::8:800:200C:417A。
  + 需要注意的是，在一个IPv6地址中只能使用一次双冒号， 否则当计算机将压缩地址恢复成完整地址时，无法确定每个双冒号表示几个0。

##### P2 域名(Domain Name)

IP地址是数字形式，可读性差且难以记忆，因此人们发明了可读性较高且容易记忆的域名。

当用户想要访问某个网站时，他在计算机设备上输入域名，本地**DNS(Domain Name System)**服务器就会将域名解析成对应的IP地址并返回给设备，此时设备就能通过网站的IP地址访问网站。（注：此处没有考虑DNS缓存不命中的情况）

##### P3 公网和内网IP

+ **公网IP**：可以连接互联网的IP地址。
+ **内网IP**：也叫局域网IP，只能在组织或机构内部使用。192.168.开头的IP是常见的局域网IP。

##### P4 特殊IP

+ 127.0.0.1，别名`localhost`，代表本机IP，只会寻找当前所在的主机。

##### P5 常用的终端IP命令

+ 查看本机IP地址：`ipconfig(Windows)/ifconfig(Linux)`。
+ 检查本机与目标IP之间的网络是否连通：`ping 目标IP`。



---



#### 2.2 端口号：应用程序在设备中的唯一标识



#### 2.3 协议：设备连接和数据在网络中传输的规则



---



## 三、网络编程

### 1.`InetAddress`类：代表IP地址

| 序号 | 方法                                        | 说明                                                         |
| ---- | ------------------------------------------- | ------------------------------------------------------------ |
| 01   | `static InetAddress getLocalHost()`         | 将本机IP封装成一个`InetAddress`对象并返回。                  |
| 02   | `static InetAddress getByName(String host)` | 根据IP或域名，返回一个`InetAddress`对象。                    |
| 03   | `String getHostName()`                      | 获取该`InetAddress`对象的主机名（一般是域名）。              |
| 04   | `String getHostAddress()`                   | 获取该`InetAddress`对象的IP地址信息。                        |
| 05   | `boolean isReachable(int timeout)`          | 在指定时间（单位：毫秒）内，判断主机与该IP对应的主机之间是否连通。 |















