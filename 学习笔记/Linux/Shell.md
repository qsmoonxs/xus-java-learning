### shell提示符

----------
[xus@localhost ~]$ 
xus：启动shell的用户名
localhost：主机名
~ ： 当前目录（~代表主目录）
$ ： shell等待输入命令（root用户的提示符是#）

----------
### echo -- 输出命令
echo "Hello World"

----------
### java启动到后台进程
nohup java -jar web-container-0.0.1-SNAPSHOT.jar > web-container.log 2>&1 & 
web-container.log日志文件 2>&1 错误流重定向到输出流
jobs 调出所有用nohup执行的程序
fg 1 调1程序到前台
tail -200f web-container.log 看日志
ps ax|grep java 找java进程


