package com.latte.hb;


import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.latte.hb.view.ScrollableLogview;
import com.latte.hb.view.tools.ToolBox;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.BorderLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainFrame {

    public static void main(String[] args) throws IOException, RuntimeException {
        LafManager.install(new DarculaTheme());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);

        var tabs = new JTabbedPane();

        for (String a : args) {
            var path = Path.of(a);
            var text = new String(Files.readAllBytes(path));
            tabs.add(path.toFile().getName(), createWrapper(text));
        }

        frame.setContentPane(tabs);
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel createWrapper(String text) {
        var slv = new ScrollableLogview(text);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(slv, BorderLayout.CENTER);

        JPanel toolBoxWrapper = new JPanel(new BorderLayout());
        var toolBox = new ToolBox();
        toolBoxWrapper.add(toolBox, BorderLayout.WEST);
        JPanel toolBoxHider = new JPanel();
        BoxLayout bl = new BoxLayout(toolBoxHider, BoxLayout.PAGE_AXIS);
        toolBoxHider.setLayout(bl);
        JButton hideBtn = new JButton("|");
        hideBtn.addActionListener(l -> {
            toolBox.setVisible(!toolBox.isVisible());
            toolBoxWrapper.revalidate();
            toolBoxWrapper.repaint();
        });
        toolBoxHider.add(hideBtn);
        toolBoxWrapper.add(toolBoxHider, BorderLayout.EAST);

        wrapper.add(toolBoxWrapper, BorderLayout.EAST);
        return wrapper;
    }

}
