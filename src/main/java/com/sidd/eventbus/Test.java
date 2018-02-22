package com.sidd.eventbus;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Siddharth on 2/22/18.
 */
public class Test {

    public static void main(String[] args) throws Exception
    {
        EventBus bus = new EventBus();
        DemoListener demoListener = new DemoListener();
        String channel = "demochannel";
        bus.subscribe(channel, demoListener);

        int msgCount = 1000000;
        CountDownLatch countDownLatch = new CountDownLatch(msgCount);
        long startTime = System.currentTimeMillis();
        for(int c = 0; c < msgCount; c++)
        {
            bus.send(channel, new DemoPayload("DummyMessage", countDownLatch));
        }
        System.out.println("sent " + msgCount + " messages");

        try
        {
            countDownLatch.await();
            long endTime   = System.currentTimeMillis();
            long timeTaken = (endTime-startTime);
            System.out.println("processing time: " + timeTaken + " Ms");
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
class DemoListener implements EventListener<DemoPayload>
{
    public void onMessage(String channel, DemoPayload demoPayload)
    {
        //System.out.println(demoPayload.getMsg());
        demoPayload.getCountDownLatch().countDown();
    }
}
class DemoPayload
{
    private String msg;
    private CountDownLatch countDownLatch;

    public DemoPayload(String msg, CountDownLatch countDownLatch)
    {
        this.msg = msg;
        this.countDownLatch = countDownLatch;
    }

    public String getMsg() {
        return msg;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}