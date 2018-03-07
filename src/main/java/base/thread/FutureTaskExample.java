/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

import java.util.concurrent.*;

/**
 *
 *
 * @author xus
 * @since 2018-03-07 15:48
 *
 */
public class FutureTaskExample {

    public static void main(String[] args) {
        MyCallable callable1 = new MyCallable(1000L);
        MyCallable callable2 = new MyCallable(2000L);

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(futureTask1);
        executorService.execute(futureTask2);
        while (true) {
            try {
                //  两个任务都完成
                if(futureTask1.isDone() && futureTask2.isDone()){
                    System.out.println("Done");
                    // 关闭线程池和服务
                    executorService.shutdown();
                    return;
                }
                // 任务1没有完成，会等待，直到任务完成
                if(!futureTask1.isDone()){
                    System.out.println("FutureTask1 output="+futureTask1.get());
                }

                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if(s !=null){
                    System.out.println("FutureTask2 output="+s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                //do nothing
            }
        }

    }
}
