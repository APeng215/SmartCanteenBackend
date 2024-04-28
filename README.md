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

> [!NOTE]
> 后端会在启动时自动为数据库创建**数据库表**。**但是**你需要为后端创建并配置**数据库**，数据库表无需手动创建。

### 如何认证？

进行任何 API 调用都需要在请求头中添加 **Basic Auth** 认证信息，方式如下：

1. 根据 Basic Auth 规则生成密钥，如 `Basic YWRtaW46MTIzNDU2`
2. 在请求头添加 `Authorization: Basic YWRtaW46MTIzNDU2`

> [!TIP]
> 后端内置了管理员账号，测试时可以选用此账号生成 Basic Auth :
> 
> 账号: Admin
> 
> 密码: 123456

## API 文档

### 人流监测测试

- 更新人流数量 - PATCH `/monitoring?peopleNumber=int`
- 获取人流数量 - GET `/monitoring`

### 订单

#### 增加

增加订单，订单自动与请求的用户关联

POST `/orders`

请求体样例：

列表中的数字 `1, 2, 3` 为菜品的 ID

```json
{
  "dishIds": [1, 2, 3]
}
```




