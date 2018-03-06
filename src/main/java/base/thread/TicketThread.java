/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

/**
 *
 *
 * @author xus
 * @since 2018-03-06 16:32
 *
 */
public class TicketThread extends Thread {
    private  int ticket =10;

    private String name;

    public TicketThread(String name){
        this.name=name;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            if(this.ticket>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(this.name+"卖票--->"+(this.ticket--));
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TicketThread th1=new TicketThread("一号窗口");
        TicketThread th2=new TicketThread("二号窗口");
        th1.start();
        th2.start();
    }
}
//一号窗口卖票--->10
//        二号窗口卖票--->10
//        一号窗口卖票--->9
//        二号窗口卖票--->9
//        一号窗口卖票--->8
//        二号窗口卖票--->8
//        二号窗口卖票--->7
//        一号窗口卖票--->7
//        二号窗口卖票--->6
//        一号窗口卖票--->6
//        二号窗口卖票--->5
//        一号窗口卖票--->5
//        二号窗口卖票--->4
//        一号窗口卖票--->4
//        一号窗口卖票--->3
//        二号窗口卖票--->3
//        一号窗口卖票--->2
//        二号窗口卖票--->2
//        一号窗口卖票--->1
//        二号窗口卖票--->1
