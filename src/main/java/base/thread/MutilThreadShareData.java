/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

/**
 *
 *
 * @author xus
 * @since 2018-03-07 12:03
 *
 */
public class MutilThreadShareData {

    private static MutiShareData mutiShareData = new MutiShareData();

    public static void main(String[] args) {

        for(int i=0;i<3;i++){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread()+":{j from "+ mutiShareData.getJ()+" + to: "+mutiShareData.increment()+"}");
                        }
                    }
            ).start();
        }

        for(int i=0;i<2;i++){
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread()+":{j from "+ mutiShareData.getJ()+" - to: "+mutiShareData.decrement()+"}");
                        }
                    }
            ).start();
        }
    }

}

/**
 * 将共享数据封装在另一对象中（操作数据的方法也在该对象完成）
 */
class MutiShareData{
    private int j = 0;
    public synchronized  int increment(){
        return  ++j;
    }
    public synchronized int  decrement(){
        return --j;
    }

    public synchronized int getJ() {
        return j;
    }

    public synchronized void setJ(int j) {
        this.j = j;
    }
}
