/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.thread;

import java.util.concurrent.Callable;

/**
 *
 *
 * @author xus
 * @since 2018-03-07 15:47
 *
 */
public class MyCallable implements Callable<String> {

    private Long waitTime;

    public MyCallable(Long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName();
    }
}
