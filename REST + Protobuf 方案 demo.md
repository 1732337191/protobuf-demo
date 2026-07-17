REST + Protobuf 方案
作为 REST API 的数据交换格式（替代 JSON，Content-Type 为 application/x-protobuf）
# .proto生成.java
使用protobuf-maven-plugin插件生成.java文件，具体步骤如下：
1. 已在pom.xml文件中添加protobuf-maven-plugin插件配置
2. 运行mvn compile命令即可生成.java文件
3. 生成的.java文件位于 由outputDirectory + proto文件指定的java_package拼接而成
   ${project.basedir}/src/main/java/com.ltgame.protobufdemo.proto.UserProto.java

# 测试
- 测试 getUser 返回 Protobuf 格式
`curl http://localhost:8888/api/users/1 -H "Accept: application/x-protobuf" -o response.bin`
- 测试 createUser 接收 Protobuf 请求体
`curl -X POST http://localhost:8888/api/users -H "Content-Type: application/x-protobuf" --data-binary @request.bin -o response.bin`
- 测试 getUserByBin 接收 Protobuf 请求体
`curl -X POST http://localhost:8888/api/users/check -H "Content-Type: application/x-protobuf" --data-binary @request.bin`

⚠️ 为什么必须用 --data-binary 而不是 -d？
这是最容易踩坑的地方：
-d / --data：会对数据进行 URL 编码处理，遇到 \n、\r、\0 等字节会转义或截断，彻底破坏 Protobuf 二进制结构，导致服务端 parseFrom 抛出 InvalidProtocolBufferException。
--data-binary：原封不动地按字节发送文件内容，不做任何编码转换，保证二进制完整性

```aiignore
查询用户: 2

用户ID: 2
收到用户: 张三
用户邮箱: zhangsan@example.com

用户ID: 100
收到用户: 张三
用户邮箱: zhangsan@example.com
```

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

