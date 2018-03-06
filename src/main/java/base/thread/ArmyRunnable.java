/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:30
 *
 */
public class ArmyRunnable  implements Runnable {
    /** volatile保证线程可以争取读取其它线程的值 可见性 **/
    volatile boolean keepRunning = true;
    @Override
    public void run() {
        while(keepRunning) {
            /** 发动五连击**/
            for(int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方第【" + i + "】");

                /** 让出处理器时间**/
                /** yield和sleep的区别**/
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束战斗");
    }
}
