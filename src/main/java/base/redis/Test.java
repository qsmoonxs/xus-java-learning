/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 *
 * @author xus
 * @since 2018-03-30 10:55
 *
 */
public class Test {

    public static void main(String[] args) {

        JedisPool pool = null;
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        /**redis默认端口是6379 ， 这里的timeout是**/
        pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
        Jedis conn = pool.getResource();
        conn.setnx("name","xus");

        Service service = new Service();

        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
