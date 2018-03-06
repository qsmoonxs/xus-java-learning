### ResultMap
- ```<id/>```标签很重要，之前都不知道，结果集映射的时候 是根据id标签来决定要不要映射数据，重复数据就不映射，照道理标签应该
对应数据库主键，但是发现对应普通列名也可以 
``` xml
<!-- 汇总单返回货品信息(用数据库查询解决)-->
	<resultMap id="GatherOrder" type="com.differ.jackyun.wms.dto.print.GatherOrderDTO">
		<id column="sku_id" property="skuId"/>
		<result column="unit" property="unit" jdbcType="VARCHAR" />
		<result column="trade_name" property="tradeName" jdbcType="VARCHAR" />
		<result column="trade_spec" property="tradeSpec" jdbcType="VARCHAR" />
		<result column="trade_goodsno" property="tradeGoodsNO" jdbcType="VARCHAR" />
		<collection property="posList" javaType="ArrayList"  ofType="com.differ.jackyun.wms.dto.print.GatherOrderDTO$Postion"
					fetchType="eager" resultMap="pos">
		</collection>
		<collection property="orderList" javaType="ArrayList"  ofType="com.differ.jackyun.wms.dto.print.GatherOrderDTO$Detail"
					fetchType="eager" resultMap="detail">
		</collection>
	</resultMap>
```
此处id标签为sku_id那么就会根据sku不同而插入数据，所以总共是三条数据返回

- collection标签关联有两种方式 
[resultMap之collection](http://blog.csdn.net/liaoxiaohua1981/article/details/6862466)