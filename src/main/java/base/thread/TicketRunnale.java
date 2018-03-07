/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:32
 *
 */
public class TicketRunnale implements Runnable {
    private   int ticket =100;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            if(ticket>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"卖票--->"+(this.ticket--));
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {

        TicketRunnale mt=new TicketRunnale();
        /**
         * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，
         * 如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
         */
        ExecutorService executorService = Executors.newFixedThreadPool(15, Executors.defaultThreadFactory());
        for( int i = 0; i < 100; i++) {
            executorService.execute(mt);
        }
        //executorService.execute(mt);

//        Thread t1 =new Thread(mt,"一号窗口");
//        Thread t2 =new Thread(mt,"二号窗口");
//        t1.start();
//        t2.start();



    }



}


//一号窗口卖票--->10
//        二号窗口卖票--->9
//        一号窗口卖票--->8
//        二号窗口卖票--->7
//        一号窗口卖票--->6
//        二号窗口卖票--->5
//        一号窗口卖票--->4
//        二号窗口卖票--->3
//        一号窗口卖票--->2
//        二号窗口卖票--->1
//        一号窗口卖票--->0
