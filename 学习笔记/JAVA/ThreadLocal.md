### threadlocal

----------
- 其实就是 `Map<Thread,Object>`把这次线程中要用到的变量可以放到threadlocal中，每一个线程拿到的都是新的对象，不会有数据同步的问题。 spring事务管理的注解 就是通过threadlocal来实现

----------


- 使用实例 哈哈 今天工作中用到了 上代码
```java
 /**
     * 用来set来自pc还是pda字段，如果这里直接用一个静态的枚举变量来记录，那么会产生线程竞争这个变量的情况，就会出错，所以这就是threadlocal的好处
     */
    public static final ThreadLocal<UrlSourceEnum> SOURCE_LOCAL_ADDPACK = new ThreadLocal<>();
/**
     * 追加物流包裹
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/add/{source}", method = RequestMethod.POST)
    public JackYunResponse add(String data, @PathVariable UrlSourceEnum source) {
        SOURCE_LOCAL_ADDPACK.set(source);
        OrderPack pack = DeserializationConverter.jsonToEntity(data, OrderPack.class);
        OrderPack res = packService.generateOnePackage(pack);
        SOURCE_LOCAL_ADDPACK.remove();
        return super.sendSuccessData(res);
    }
```
``` java
  /** 增加装箱和追加包裹日志**/
insertPackLog(packId, MemberHolder.getMemberInfo().getUserName(), "追加包裹【"+SOURCE_LOCAL_ADDPACK.get() + "】");
```
