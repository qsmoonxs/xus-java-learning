/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:58
 *
 */
public class MyTimer {

    static int count = 0;
    public static void main(String[] args) {

        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "bomb");
                new Timer().schedule(new MyTimerTask(), 2000+1000*(count++%2));
            }
        }
        //3s后开启定时器
        new Timer().schedule(new MyTimerTask(),3000);
    }
}
