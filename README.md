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

### ~~如何认证？~~

~~进行任何 API 调用都需要在请求头中添加 **Basic Auth** 认证信息，方式如下：~~

~~1. 根据 Basic Auth 规则生成密钥，如 `Basic YWRtaW46MTIzNDU2`~~
~~2. 在请求头添加 `Authorization: Basic YWRtaW46MTIzNDU2`~~

> [!TIP]
> ~~后端内置了管理员账号，测试时可以选用此账号生成 Basic Auth :~~
> 
> ~~账号: Admin~~
> 
> ~~密码: 123456~~

> [!NOTE]
> 此功能暂时关闭

## API 文档

### 食堂监控

#### 添加/更新食堂监控数据 - PUT `/monitoring` 

请求体举例：

```json
{
    "name": "h1",
    "capacity": 10,
    "peopleNum": 2
}
```

带有以上请求体的 PUT `/monitoring` 请求将向后端数据库添加一个名称为 *h1*，容量为 *10*，监控人数为 *2* 的食堂监控实体。

其中 `name` 为食堂监控实体的**唯一标识符 / ID**，也就是说数据库中各个食堂监控实体的名称唯一。

#### 获取食堂监控信息 - GET `/monitoring/{canteenName}` 

例如 `http://localhost:8080/monitoring/h1` 请求食堂名为 *h1* 的食堂的监控数据

返回的数据格式如下：

```json
{
    "capacity": 10,
    "name": "h1",
    "peopleNum": 10,
    "saturation": 1.0,
    "state": "爆满"
}
```

> [!TIP]
> 数据库中初始化有餐厅名为 *荷园一餐厅* 的食堂监控实体，可供于测试。

#### 更新食堂监控人数信息 - PATCH `/monitoring/{canteenName}?peopleNum={int}`

例如 `http://localhost:8080/monitoring/h1?peopleNum=10` 将食堂名为 *h1* 的食堂的监控人数修改为 *10*

### 订单

#### 增加订单 - POST `/orders`

订单自动与请求的用户关联

请求体样例：

```json
{
  "dishIds": [1, 2, 3]
}
```

请求体中的 `dishIds` 列表中的数字 *1, 2, 3* 为菜品的 ID




