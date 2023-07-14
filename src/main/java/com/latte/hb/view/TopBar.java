package com.latte.hb.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.LayoutManager;

public class TopBar extends JPanel {

    public TopBar() {
        var bl = new BoxLayout(this, BoxLayout.LINE_AXIS);
        setLayout(bl);

        var wwToggle = new JToggleButton("ww");

        add(wwToggle);
    }

}
