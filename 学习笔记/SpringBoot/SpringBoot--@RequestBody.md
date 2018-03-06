### @requestbody

----------

@RequestBody

作用： 
      i) 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
      ii) 再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上。
使用时机：
A) GET、POST方式提时， 根据request header Content-Type的值来判断:
    application/x-www-form-urlencoded， 可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，当然@RequestBody也能处理，但是@requestBody不能拿到对象里的值，用String接收字符串时，是post过来的数据，比如key为a，value为xus时 拿到的数据是a=xus）；
    multipart/form-data, 不能处理（即使用@RequestBody不能处理这种格式的数据）；
    其他格式， 必须（其他格式包括application/json, application/xml等。这些格式的数据，必须使用@RequestBody来处理）；
B) PUT方式提交时， 根据request header Content-Type的值来判断:
    application/x-www-form-urlencoded， 必须；
    multipart/form-data, 不能处理；
    其他格式， 必须；
说明：request的body部分的数据编码格式由header部分的Content-Type指定；

表单提交一般是post，数据会在body里，一般是键值对，/x-www-form-urlencoded，如果键值对套json，那需要手动解析json为对象（对这种方式 我有点迷~）