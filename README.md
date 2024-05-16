# 智慧食堂-后端部分

这是智慧食堂项目的后端部分。有与其配合的[App前端部分](https://github.com/AlearXS/SmartCanteenApp)与[智能识别部分](https://github.com/AlearXS/SmartCanteenDetection)。

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
> 后端会在启动时自动为数据库创建数据库表，**但是**数据库需要你手动创建并配置。
> 
> 具体参看 `application.properties` 中 `spring.jpa.hibernate.ddl-auto=xxx` 这一配置项。

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

#### 增加食堂人数 - PATCH `/{canteenName}/increasePeopleNum`

#### 减少食堂人数 - PATCH `/{canteenName}/decreasePeopleNum`

如果对人数**已经为 0** 的食堂执行此操作，会返回 `400 Bad Request`

#### 获取全部食堂信息 - GET `/canteen/all`

响应体举例：

```json
[
    {
        "announcement": "xxx",
        "capacity": 10,
        "imageUrl": "",
        "name": "all",
        "peopleNum": 2,
        "saturation": 0.2,
        "state": "畅通"
    },
    {
        "announcement": "",
        "capacity": 20,
        "imageUrl": "https://i.ibb.co/2ZnPrCp/jyj.jpg",
        "name": "聚英园餐厅",
        "peopleNum": 16,
        "saturation": 0.8,
        "state": "爆满"
    },
    {
        "announcement": "",
        "capacity": 10,
        "imageUrl": "https://i.ibb.co/jzjTbGr/he1.jpg",
        "name": "荷园一餐厅",
        "peopleNum": 5,
        "saturation": 0.5,
        "state": "拥挤"
    },
    {
        "announcement": "荷园二食堂将于五月一日休息，届时将暂停营业，请各位同学提前安排好就餐时间。",
        "capacity": 10,
        "imageUrl": "https://i.ibb.co/0fL2nrR/he2.jpg",
        "name": "荷园二餐厅",
        "peopleNum": 8,
        "saturation": 0.8,
        "state": "爆满"
    },
    {
        "announcement": "",
        "capacity": 30,
        "imageUrl": "https://i.ibb.co/HBW5F7q/fhy.jpg",
        "name": "风华园餐厅",
        "peopleNum": 1,
        "saturation": 0.03333333333333333,
        "state": "畅通"
    }
]
```

#### 添加/更新食堂数据 - PUT `/canteen` 

请求体举例：

```json
{
    "name": "h1",
    "peopleNum": 2,
    "capacity": 10,
    "announcement": "xxx",
    "imageUrl": ""
}
```

带有以上请求体的 PUT `/canteen` 请求将向后端数据库添加一个名称为 *h1*，容量为 *10*，监控人数为 *2* 的食堂监控实体。

其中 `name` 为食堂监控实体的**唯一标识符 / ID**，也就是说数据库中各个食堂监控实体的名称唯一。

#### 获取食堂信息 - GET `/canteen/{canteenName}` 

例如 `http://localhost:8080/canteen/h1` 请求食堂名为 *h1* 的食堂的监控数据

返回的数据格式如下：

```json
{
  "announcement": "",
  "capacity": 10,
  "imageUrl": "https://i.ibb.co/jzjTbGr/he1.jpg",
  "name": "荷园一餐厅",
  "peopleNum": 5,
  "saturation": 0.5,
  "state": "拥挤"
}
```

> [!TIP]
> 数据库中初始化有餐厅名为 *荷园一餐厅* 的食堂监控实体，可供于测试。

#### 更新食堂监控人数信息 - PATCH `/canteen/{canteenName}?peopleNum={int}`

例如 `http://localhost:8080/canteen/h1?peopleNum=10` 将食堂名为 *h1* 的食堂的监控人数修改为 *10*

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




