# **主题**

文本、图片、视频、音频

健身（识别是否标准）、历史（图片鉴定）

## 技术栈

前端：vue3+TS 、Three.js + WebGL、WebRTC

后端：Spring Boot + Spring Data JPA
Spring Task/Quartz

数据库：MySQL+MongoDB+Redis

动作识别：MediaPipe Pose + TensorFlow

修改为基础框架使用ruoyi框架二次开发。

### 创建项目

#### 一、创建前端项目

##### 1.1搭建vite项目

node版本20.16.0

##### 2.2创建目录

创建存放目录，cmd进入命令行，执行命令npm create vite@latest

![image-20250224112016922](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250224112016922.png)

![image-20250224112110860](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250224112110860.png)

按照提示安装依赖

![image-20250224112452898](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250224112452898.png)

在VScode中打开运行项目 npm run dev

![image-20250224112916743](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250224112916743.png)

##  RuoYi 

开源项目二次开发

官网

```
https://ruoyi.vip/
```

![image-20250308161159428](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308161159428.png)

admin：后端，UI：前端，其余为工具类。

配置数据库：

![image-20250308162239784](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308162239784.png)

在Navicat中建立链接

![image-20250308165226789](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308165226789.png)

![image-20250308165415101](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308165415101.png)

配置数据库数据源：

![image-20250308170305684](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308170305684.png)

配置Redis，安装好Redis环境后确保端口号一致即可。

Redis运行命令

```
redis-server.exe redis.windows.conf
```

运行，访问8080出现以下文字：

##### ![image-20250308172250671](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308172250671.png) 配置前端 

![image-20250308172713294](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308172713294.png)

运行前端：

![image-20250308175559582](C:\Users\18480\AppData\Roaming\Typora\typora-user-images\image-20250308175559582.png)

修改图标、Tiltle

