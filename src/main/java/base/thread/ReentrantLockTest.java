/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @author xus
 * @since 2018-03-07 14:54
 *
 */
public class ReentrantLockTest {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    /** lock要设置为成员变量，如果是局部变量，那么每次线程进去都会拿到一个副本**/
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();
        new Thread("A") {
            @Override
          public void run() {
              test.insert(Thread.currentThread());
          }
        }.start();
        new Thread("B") {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

    }
    public void insert(Thread thread) {

        lock.lock();
        try {
            System.out.println("线程" + thread.getName() + "得到了锁...");
            for(int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程" + thread.getName() + "释放了锁...");
            lock.unlock();
        }
    }
}
