package com.latte.hb.msg;

import java.util.HashMap;
import java.util.Map;

public class Message {

    public final MessageType type;

    public final String content;

    private Map<String, Object> attributes = new HashMap<>();

    public Message(MessageType type, String content) {
        this.type = type;
        this.content = content;
    }

    public void addAttribute(String name, Object attribute) {
        attributes.put(name, attribute);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

}
