package com.latte.hb.view;

import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Logview extends JTextPane {

    private Font ijFont;

    private String text;
    private DefaultStyledDocument document;

    public Logview(String text) {
        this.text = text;
        init();
    }

      @Override
    public boolean getScrollableTracksViewportWidth() {
          Container parent = SwingUtilities.getUnwrappedParent(this);
          if (parent instanceof JViewport) {
              return parent.getWidth() > getLongestLineWidth();
          }
          return false;
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

    private int getLongestLineWidth() {
        return (int) ijFont
                .getStringBounds(
                        getLongestLine(),
                        ((Graphics2D)getGraphics())
                                .getFontRenderContext())
                .getWidth();
    }

    private String getLongestLine() {
        int max = 0;
        String str = "";
        for (String l : text.split(System.lineSeparator())) {
            if (l.length() > max) {
                max = l.length();
                str = l;
            }
        }
        return str;
    }

}
