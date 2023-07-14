package com.latte.hb.view.tools.search;

import com.latte.hb.msg.Message;
import com.latte.hb.msg.MessageBus;
import com.latte.hb.msg.MessageType;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchPane extends JPanel {

    private JTextField tx = new JTextField();

    private JButton colorBtn = new JButton("C");

    private JButton findBtn = new JButton("F");

    private JButton addBtn = new JButton("+");

    private JButton removeBtn = new JButton("-");

    private JButton prevBtn = new JButton("<");
    private JButton nextBtn = new JButton(">");

    public SearchPane() {
        var l = new BoxLayout(this, BoxLayout.LINE_AXIS);
        setLayout(l);
        add(tx);
        add(colorBtn);
        colorBtn.setForeground(Color.ORANGE);
        add(findBtn);

        add(prevBtn);
        add(nextBtn);

        add(addBtn);
        add(removeBtn);


        tx.setMaximumSize(new Dimension(700, findBtn.getMaximumSize().height));
        tx.setPreferredSize(new Dimension(200, 0));
        tx.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    find();
                }
            }
        });
        add(Box.createGlue());

        findBtn.addActionListener(a -> find());

        colorBtn.addActionListener(listener -> setColor(colorBtn.getForeground()));
    }

    private void find() {
        var msg = new Message(MessageType.SEARCH, tx.getText());
        msg.addAttribute("color", colorBtn.getForeground());
        MessageBus.INSTANCE.publish(msg);
    }

    private ActionListener addBtnListener;

    public void addAddBtnListener(ActionListener l) {
        addBtnListener = l;
        addBtn.addActionListener(l);
    }

    private void setColor(Color defaultColor) {
        var newColor = JColorChooser
                .showDialog(this, "Highlight color", defaultColor);
        colorBtn.setForeground(newColor);
    }

}
