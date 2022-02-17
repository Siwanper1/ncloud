### 启动命令
```
echo "启动nacos"
cd /Users/chenjie/Documents/nacos/bin
sh startup.sh -m standalone

cd /
echo "启动redis"
redis-server /usr/local/redis-6.0.4

echo "启动rabbitmq"
cd /
rabbitmq-server
```

[Nacos](http://localhost:8848/nacos/)
[Swagger](http://localhost:9001/swagger-ui.html)
[用户管理](http://localhost:9001/)

# 这是一级标题
## 这是二级标题
### 这是三级标题
#### 这是四级标题
##### 这是五级标题
###### 这是六级标题

**这是加粗的文字**
*这是倾斜的文字*`
***这是斜体加粗的文字***
~~这是加删除线的文字~~

>这是引用的内容
>>这是引用的内容
>>>>>>>>>>这是引用的内容


---
----
***
*****

![图片alt](图片地址 ''图片title'')

图片alt就是显示在图片下面的文字，相当于对图片内容的解释。
图片title是图片的标题，当鼠标移到图片上时显示的内容。title可加可不加

[超链接名](超链接地址 "超链接title")
title可加可不加

[百度](http://baidu.com)


- 列表内容
+ 列表内容
* 列表内容

注意：- + * 跟内容之间都要有一个空格

1. 列表内容
2. 列表内容
3. 列表内容

注意：序号跟内容之间要有空格


`代码内容`


```
代码...
代码...
代码...
```