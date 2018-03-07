/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 17:06
 *
 */
public class TraditionalThreadCommunication {
    public static void main(String[] args) {
        Business business = new Business();
        /**这里有两个线程一个是new出来的称之为A，另一个主线程称之为B**/
        /**比如A去执行sub方法，当bShouldSub为true，那么打印5次，然后去notify但是现在waitset里还没有线程**/
        /**A又抢占了cpu去执行sub这时候他会进入while，会wait，释放锁，A被阻塞进入waitset**/
        /**B枪战cpu执行main，打印十次，并且改变了bShouldSub，唤醒线程A，两线程又开始抢占，如果B抢占cpu执行main，则会wait，如此交替循环打印**/
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(int i=1;i<=50;i++){
                            business.sub(i);
                        }
                    }
                }
        ).start();

        for(int i=1;i<=50;i++){
            business.main(i);
        }

    }
}

class Business{
    private boolean bShouldSub = true;

    public synchronized void sub(int i){
        while(!bShouldSub){
            try {
                /**这里wait了 不代表退出这个方法了，而是线程在这里被阻塞进入了waitset唤醒以后如果拿到锁 他会继续往下执行
                 * 然而会有虚假唤醒的情况，JDK官方也说了，就是除了notify唤醒的情况，那么为了防止虚假唤醒，这里要用while去循环判断**/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=5;j++){
            System.out.println("sub thread count "+j+","+i+"/50");
        }
        bShouldSub = false;
        this.notify();
    }
    public synchronized void main(int i){
        while(bShouldSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int j=1;j<=10;j++){
            System.out.println("main thread count "+j+","+i+"/50");
        }
        bShouldSub = true;
        this.notify();
    }
}