package javaapplication4;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
class Window_Message extends Window {

    Window_Message(String content) {
        message = content;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        windowskin = Game_System.windowskin_name;
        window_opacity = 1.00f;
        skin_opacity = 1.00f;
        content_opacity = 1.00f;
        c = Game_System.normal_color;
        font = Game_System.font;
    }

    public void setFontSize(int size) {
        font = new Font(font.getFontName(), font.getStyle(), size);
    }

    public void setFontStyle(int style) {
        font = new Font(font.getFontName(), style, font.getSize());
    }

    public void setWindowType(int window_type) {
        switch (window_type) {
            case 0:
                x = 16;
                y = 314;
                width = 608;
                height = 149;
                break;
            case 1:
                x = 16;
                y = 165;
                width = 608;
                height = 149;
                break;
            case 2:
                x = 16;
                y = 16;
                width = 608;
                height = 149;
                break;
            case 3:
                x = 16;
                y = 16;
                width = 608;
                height = 448;
                break;
        }
    }
    public BufferedImage getImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(getwindow(), 0, 0, null);
        int row = 0;
        g2d.setFont(font);
        FontMetrics a = g2d.getFontMetrics();
        g2d.getFontMetrics().charWidth(0);
        int xx = 16;
        int yy = 32;
        char[] s = message.toCharArray();
        int w = 0;
        int h = a.getHeight();
        for (int i = 0; i < message.length(); i++) {
            if ("\\".equals(message.substring(i, i + 1))) {
                if (null == message.substring(i + 1, i + 2)) {
                    i++;
                } else {
                    switch (message.substring(i + 1, i + 2)) {
                        case "n":
                            row++;
                            w = 0;
                            i += 2;
                            break;
                        case "\\":
                            i++;
                            g2d.drawString(message.substring(i, i + 1), xx + w, yy + h * row);
                            w += a.charWidth(message.charAt(i));
                            break;
                        case "V":
                            if ("[".equals(message.substring(i + 2, i + 3))) {
                                String id = "";
                                i += 3;
                                for (int j = i; j < message.length(); j++) {
                                    i += 1;
                                    if ("]".equals(message.substring(j, j + 1))) {
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
                                        break;
                                    } else {
                                        id += message.substring(j, j + 1);
                                    }
                                }
                            }
                            break;
                        case "S":
                            if ("[".equals(message.substring(i + 2, i + 3))) {
                                String id = "";
                                i += 3;
                                for (int j = i; j < message.length(); j++) {
                                    i += 1;
                                    if ("]".equals(message.substring(j, j + 1))) {
                                        String b = ""+Game_System.Switches[Integer.parseInt(id)];
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
                                        break;
                                    } else {
                                        id += message.substring(j, j + 1);
                                    }
                                }
                            }
                            break;
                        case "c":
                        case "C":
                            if ("[".equals(message.substring(i + 2, i + 3))) {
                                String iid="";
                                i += 3;
                                for (int j = i; j < message.length(); j++) {
                                    i += 1;
                                    if ("]".equals(message.substring(j, j + 1))) {
                                        break;
                                    }else {
                                        iid += message.substring(j, j + 1);
                                    }
                                }
                                String[] id = iid.split(":");
                                g2d.setColor(new Color(Integer.parseInt(id[0]), Integer.parseInt(id[1]), Integer.parseInt(id[2]), Integer.parseInt(id[3])));
                            }
                            break;
                        default:
                            i++;
                            break;
                    }
                }
            }
            if (i >= message.length()) {
                break;
            }
            if (w + a.charWidth(message.charAt(i)) > width - xx * 2) {
                row++;
                w = 0;
            }
            g2d.drawString(message.substring(i, i + 1), xx + w, yy + h * row);
            w += a.charWidth(message.charAt(i));
            
        }
        g2d.dispose();
        return bufferedImage;
    }
    public volatile transient Font font;
    public volatile transient Color c;
    public volatile transient String message = null;
    public volatile transient int window_type;
    public volatile transient boolean Visible = false;
}
