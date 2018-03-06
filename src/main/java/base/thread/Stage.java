/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:31
 *
 */
public class Stage extends Thread{


    /** 什么时候用Thread 什么时候用Runnable**/
    @Override
    public void run() {

        System.out.println("欢迎观看");
        try {
            /**哪个thread sleep了**/
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArmyRunnable armyTaskOfDynasty = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

        Thread armyOfDynasty = new Thread(armyTaskOfDynasty, "隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "农民起义军");

        armyOfDynasty.start();
        armyOfRevolt.start();

        /** 舞台线程休眠**/
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正当双方激战 半路杀出个陈咬金");
        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("陈咬金");
        System.out.println("陈咬金闪亮登场");

        armyTaskOfDynasty.keepRunning = false;
        armyTaskOfRevolt.keepRunning = false;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /** 历史大戏留给关键人物**/
        mrCheng.start();
        /** 其它线程等待陈咬金**/
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("战争结束");

    }

    /**
     * 高山变成深谷 沧海化为桑田 夏冬的枯荣 国家的兴衰 人的生死 真的是神秘莫测？
     * 十年可见春去秋来 百年可证生老病死 千年可叹王朝更替 万年可见斗转星移
     * 凡人如果用一天的视野 去窥探百万年的天地 是否就如果井底之蛙
     * @param args
     */
    public static void main(String[] args) {

        Thread stage = new Stage();
        stage.start();

    }
}
