package com.latte.hb.view;

import com.latte.hb.view.tools.search.SearchPane;

import javax.swing.*;
import java.awt.*;

public class LogviewWrapper extends JPanel {

    public LogviewWrapper(String text) {
        setLayout(new BorderLayout());
        add(new SearchPane(), BorderLayout.NORTH);
        add(new ScrollableLogview(text), BorderLayout.CENTER);
    }

}
