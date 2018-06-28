package base.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.UUID;

/**
 *  redis 做分布式锁 重要的是key  value其实无所谓 一般时间戳或者uuid
 *  redis 做缓存 重要的是value 存储的东西
 *  xus
 */
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";


    /**
     * 尝试获取分布式锁
     * @param jedis
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public static Boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
       String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
       System.out.println(result);
       /**如果成功那么得到锁**/
       if (LOCK_SUCCESS.equals(result)) {
           /** 做业务处理  **/
           /** 把这个库存从数据库取出来 然后减去1000 更新到数据库**/
           /** 做完以后释放锁 在这个if里释放锁就肯定是 得到锁的客户端释放锁，为了防止程序运行到这一步之前崩溃导致死锁 所以要对锁加失效时间**/
           return true;
       } else {
           /** 不能做处理 其实redis锁是一个约定 并不是代码强制性**/
       }
       return false;
    }

    @Autowired
    @Qualifier(value = "jedis")
    Jedis jedis;


    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String uuid = UUID.randomUUID().toString();
        tryGetDistributedLock( jedis, "xus", uuid, 10000);
    }
}
