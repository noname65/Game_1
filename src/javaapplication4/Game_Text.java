package javaapplication4;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Game_Text {

    public volatile transient String text;
    public volatile transient boolean isSingleRow;
    public volatile transient boolean omit;
    public volatile transient int width;
    public volatile transient int height;
    public volatile transient Font f;
    

    Game_Text(int w, int h) {
        isSingleRow = true;
        width = w;
        height = h;
        omit = true;
        f=Game_System.font;
    }
    public int getTextWidth(String text) {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics().getFontMetrics(f).stringWidth(text);
    }
    public BufferedImage getImage(String text) {
        BufferedImage bi = new BufferedImage(Game_System.drawX, Game_System.drawY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setFont(f);
        FontMetrics a = g2d.getFontMetrics();
        int row = 0;
        int xx = 16;
        int yy = 32;
        char[] s = text.toCharArray();
        int w = 0;
        int h = a.getHeight();
        if (isSingleRow) {
            int ellipsis=a.charWidth(".".charAt(0))*3;
            if(getTextWidth(text)>width){
                if (omit){
                    for (int i = 0; i < text.length() && w+a.charWidth(text.charAt(i))<=width-ellipsis; i++) {
                        g2d.drawString(text.substring(i, i + 1), xx + w, h);
                        w+=a.charWidth(text.charAt(i));
                    }
                    g2d.drawString("...", xx + w, h);
                }else for (int i = 0; i < text.length(); i++) {
                    g2d.drawString(text.substring(i, i + 1), xx + w, h);
                    w+=a.charWidth(text.charAt(i));
                }
            }else for (int i = 0; i < text.length(); i++) {
                g2d.drawString(text.substring(i, i + 1), xx + w, h);
                w+=a.charWidth(text.charAt(i));
            }
        } else {
            for (int i = 0; i < text.length(); i++) {
                if ("\\".equals(text.substring(i, i + 1))) {
                    if (null == text.substring(i + 1, i + 2)) {
                        i++;
                    } else {
                        switch (text.substring(i + 1, i + 2)) {
                            case "n":
                                row++;
                                w = 0;
                                i += 2;
                                break;
                            case "\\":
                                i++;
                                g2d.drawString(text.substring(i, i + 1), xx + w, yy + h * row);
                                w += a.charWidth(text.charAt(i));
                                break;
                            case "V":
                                if ("[".equals(text.substring(i + 2, i + 3))) {
                                    String id = "";
                                    i += 3;
                                    for (int j = i; j < text.length(); j++) {
                                        if ("]".equals(text.substring(j, j + 1))) {
                                            String b = Game_System.Variables[Integer.parseInt(id)].toString();
                                            for (int ii = 0; ii < b.length(); ii++) {
                                                if (w + a.charWidth(b.charAt(ii)) > width - xx * 2) {
                                                    row++;
                                                    w = 0;
                                                    g2d.drawString(b.substring(ii, ii + 1), xx + w, yy + h * row);
                                                    w += a.charWidth(b.charAt(ii));
                                                } else {
                                                    g2d.drawString(b.substring(ii, ii + 1), xx + w, yy + h * row);
                                                    w += a.charWidth(b.charAt(ii));
                                                }
                                            }
                                        } else {
                                            id += text.substring(j, j + 1);
                                        }
                                        i += 1;
                                    }
                                }
                                break;
                            case "S":
                                if ("[".equals(text.substring(i + 2, i + 3))) {
                                    for (int j = 0; j < text.length(); j++) {
                                    }
                                }
                                break;
                            case "C":
                                if ("[".equals(text.substring(i + 2, i + 3))) {
                                    for (int j = 0; j < text.length(); j++) {
                                    }
                                }
                                break;
                            case "c":
                                if ("[".equals(text.substring(i + 2, i + 3))) {
                                    for (int j = 0; j < text.length(); j++) {
                                    }
                                }
                                break;
                            default:
                                i++;
                                break;
                        }
                    }
                } else if ("\n".equals(text.substring(i, i + 1))) {
                    row++;
                    w = 0;
                    i += 1;
                }
                if (i >= text.length()) {
                    break;
                }
                if (w + a.charWidth(text.charAt(i)) > width - xx * 2) {
                    row++;
                    w = 0;
                    g2d.drawString(text.substring(i, i + 1), xx + w, yy + h * row);
                    w += a.charWidth(text.charAt(i));
                } else {
                    g2d.drawString(text.substring(i, i + 1), xx + w, yy + h * row);
                    w += a.charWidth(text.charAt(i));
                }
            }
        }
        g2d.dispose();
        return bi;
    }
}
