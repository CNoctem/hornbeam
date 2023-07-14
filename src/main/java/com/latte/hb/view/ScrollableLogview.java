package com.latte.hb.view;

import com.latte.hb.msg.Message;
import com.latte.hb.msg.MessageBus;
import com.latte.hb.msg.MessageSubscriber;
import com.latte.hb.msg.MessageType;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrollableLogview extends JScrollPane implements MessageSubscriber {

    private Logview logview;

    public ScrollableLogview(String text) {
        logview = new Logview(text);
        setViewportView(logview);

        MessageBus.INSTANCE.subscribe(MessageType.SEARCH, this);
    }

    public void search(String s, Color hilite) {
        Pattern pattern = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(logview.getText());
        while (matcher.find()) {
            final StyleContext cont = StyleContext.getDefaultStyleContext();
            final AttributeSet attr =
                    cont.addAttribute(
                            cont.getEmptySet(),
                            StyleConstants.Foreground,
                            hilite);
            ((DefaultStyledDocument)logview.getDocument())
                    .setCharacterAttributes(
                            matcher.start(),
                            matcher.end() - matcher.start(),
                            attr,
                            true);
//            scrollToVisible(i);
        }


    }

    public void scrollToVisible(int offset) {
        Element root = logview.getDocument().getDefaultRootElement();
        int line = root.getElementIndex(offset);

        scrollLineToVisible(line);
    }

    public void scrollLineToVisible(int line) {
        FontMetrics fm = logview.getFontMetrics(logview.getFont());
        int fontHeight = fm.getHeight();
        getViewport().setViewPosition(
                new Point(0, fontHeight * line));
    }


    @Override
    public void messageReceived(Message m) {
        search(m.content, (Color) m.getAttribute("color"));
    }
}
