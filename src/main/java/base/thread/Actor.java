/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:12
 *
 */
public class Actor extends Thread{

    @Override
    public void run() {
        System.out.println(getName() + "是一个演员！");
        int count = 0;
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println(getName() + "登台演出：" + (++count));
            if(count == 100) {
                keepRunning = false;
            }
            try {
                if(count % 10 == 0) {
                    /** 线程休眠的时候 另外的线程获得了cpu使用权 所以actress和actor交替演出**/
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getName() + "演出结束了");
    }
    public static void main(String[] args) {
        Thread actor = new Actor();
        actor.setName("Mr.Thread");
        /** start跟run的关系**/
        actor.start();
        Thread actressThread = new Thread(new Actress(), "Ms.Runnable");
        actressThread.start();
    }

}

class Actress implements Runnable {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "是一个演员！");
        int count = 0;
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println(Thread.currentThread().getName() + "登台演出：" + (++count));
            if (count == 100) {
                keepRunning = false;
            }
            try {
                if (count % 10 == 0) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "演出结束了");
    }
}
