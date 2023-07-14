package com.latte.hb.view.tools.search;

import javax.swing.*;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class SearchTab extends JPanel {

    private static Component rigid = Box.createRigidArea(
            new Dimension(0, Integer.MAX_VALUE));

    private List<JPanel> panels = new ArrayList<>();

    public SearchTab() {
        var layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);
        addLine(null);
    }

    private void addLine(Component source) {
        remove(rigid);
        var sp = new SearchPane();
        sp.addAddBtnListener(l -> addLine(sp));

        if (source == null)
            add(sp);
        else
            add(sp, getIndex(source) + 1);

        add(rigid);
        revalidate();
        repaint();
    }

    private int getIndex(Component cmp) {
        var cmps = getComponents();
        for (int i = 0; i < cmps.length; i++) {
            if (cmp.equals(cmps[i])) return i;
        }
        return -1;
    }

}
