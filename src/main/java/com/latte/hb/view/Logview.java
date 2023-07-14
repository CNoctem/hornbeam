package com.latte.hb.view;

import com.latte.hb.MainFrame;

import javax.swing.JTextPane;
import javax.swing.text.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Logview extends JTextPane {

    private Font ijFont;

    private String text;
    private DefaultStyledDocument document;

    public Logview(String text) {
        this.text = text;
        init();
    }

    private void init() {
        setEditable(false);
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();

            var fontFileName = "/home/gerger/tmp/fonts/ttf/JetBrainsMono-Light.ttf";
            var fontFile = new File(fontFileName);
//            ijFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));

            ijFont = new Font("JetBrainsMono-Light", Font.PLAIN, 14);
            setFont(ijFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }



        setBackground(new Color(43,43,43));
        try {
            final StyleContext cont = StyleContext.getDefaultStyleContext();
            final AttributeSet defaultAttribute =
                    cont.addAttribute(
                            cont.getEmptySet(),
                            StyleConstants.Foreground,
                            new Color(163,178,185));

            document = new DefaultStyledDocument();

            document.insertString(0, text, defaultAttribute);
            setDocument(document);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }


}
