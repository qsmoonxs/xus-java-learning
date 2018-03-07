### web结构
mail---------------------------Web应用所在目录
           |----html、jsp、css、js等文件，根目录下的文件外界可以直接访问
           |----WEB-INF目录
                    |---------classes目录(java类)
                    |---------lib目录(java类运行所需的jar包)
                    |---------web.xml(web应用的配置文件)
             WEB-INF 这个目录下的文件外界无法直接访问，由web服务器负责调用