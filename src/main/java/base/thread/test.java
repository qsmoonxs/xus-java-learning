/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:32
 *
 */
public class test {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,20,
                TimeUnit.MILLISECONDS,  new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory());
        for(int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
            executor.shutdown();
        }

    }
}
