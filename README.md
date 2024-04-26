# 智慧食堂-后端部分

## 开发者须知

### 如何配置数据库？

在 `application.properties` 文件中配置以下四项以连接你自己的数据库:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_canteen
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 如何认证？

进行任何 API 调用都需要在请求头中添加 **Basic Auth** 认证信息，方式如下：

1. 根据 Basic Auth 规则生成密钥，如 Basic YWRtaW46MTIzNDU2
2. 在请求头添加 Authorization: Basic YWRtaW46MTIzNDU2

> [!TIP]
> 后端内置了管理员账号，测试时可以选用此账号生成 Basic Auth :
> 
> 账号: Admin
> 
> 密码: 123456

### 人流检测测试 API

- 更新人流数量 - PATCH http://localhost:8080/monitoring?peopleNumber=int
- 获取人流数量 - GET http://localhost:8080/monitoring