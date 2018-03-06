### 什么是docker
镜像：集装箱
仓库：超级码头
容器：运行程序的地方
去仓库吧镜像拉倒本地，用命令吧镜像运行起来变成容器

----------
### docker镜像
docker ps -a 查看所有容器
docker images 查看镜像
docker build -t dockerproduct:latest .  制作镜像
docker run -d -p 0.0.0.0::8080 dockerproduct 运行容器，自动分配未占用的端口

----------


[学习视频](http://www.imooc.com/video/14625)