## maven命令

----------

 - mvn package 打包 （如果pom有热部署好像会一直下载jar包停不下来，删掉这个依赖就可以打包成功）

 - mvn clean package 打包并且清理target文件夹
 

 - mvn clean package -Dmaven.test.skip=true 跳过测试代码打包
 - mvn install install也可以干package的活，如果有别人依赖了你的jar，那应该用install（install the package into the local repository, for use as a dependency in other projects locally）
 