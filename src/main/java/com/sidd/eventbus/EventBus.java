package com.sidd.eventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Siddharth on 2/22/18.
 */
public class EventBus<T> {

    Map<String, List<EventListener>> registrations;

    ExecutorService executorService;

    public EventBus()
    {
        registrations = new HashMap<String, List<EventListener>>();
        executorService = Executors.newFixedThreadPool(1);
    }

    public void subscribe(String channel, EventListener listener)
    {
        if(registrations.containsKey(channel))
        {
            registrations.get(channel).add(listener);
        }
        else
        {
            List<EventListener> listeners = new ArrayList<EventListener>();
            listeners.add(listener);
            registrations.put(channel, listeners);
        }
    }

    public void unSubscribe(String channel, EventListener listener)
    {
        if(registrations.containsKey(channel))
        {
            List<EventListener> listeners = registrations.get(channel);
            listeners.remove(listener);
        }
    }

    public void send(String channel, T t)
    {
        executorService.submit(new Message(channel, t, registrations.get(channel)));
    }

}

