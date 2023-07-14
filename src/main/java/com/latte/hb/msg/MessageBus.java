package com.latte.hb.msg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum MessageBus {

    INSTANCE;

    private Map<MessageType, Set<MessageSubscriber>> subscribers = new HashMap<>();

    public void subscribe(MessageType messageType, MessageSubscriber subscriber) {
        Set<MessageSubscriber> set = subscribers.computeIfAbsent(messageType, k -> new HashSet<>());
        set.add(subscriber);
    }

    public void publish(Message m) {
        subscribers.get(m.type).forEach(s -> s.messageReceived(m));
    }

}
