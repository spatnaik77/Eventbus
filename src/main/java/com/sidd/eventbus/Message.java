package com.sidd.eventbus;

import java.util.List;

/**
 * Created by Siddharth on 2/22/18.
 */
public class Message<T> implements Runnable{

    private String channel;
    private T payload;
    List<EventListener>  listeners;

    public Message(String channel, T payload, List<EventListener> listeners)
    {
        this.channel = channel;
        this.payload = payload;
        this.listeners = listeners;
    }

    public String getChannel() {
        return channel;
    }

    public T getPayload() {
        return payload;
    }

    public List<EventListener> getListeners() {
        return listeners;
    }

    public void run()
    {
        for(EventListener listener : listeners)
        {
            listener.onMessage(channel, payload);
        }

    }
}
