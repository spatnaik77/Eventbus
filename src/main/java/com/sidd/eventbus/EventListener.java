package com.sidd.eventbus;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sr250345 on 2/22/18.
 */
public interface EventListener<T> {

    public void onMessage(String channel, T payload);


}
