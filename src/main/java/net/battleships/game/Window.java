package net.battleships.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

public class Window {
    private int width;
    private int height;
    private String name;
    private JFrame frame;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Window(String name) throws NoSuchFieldException {
        this.width = 100;
        this.height = 100;
        this.name = name;
        this.startup();
    }

    public Window(String name, int width, int height) throws NoSuchFieldException {
        this.width = width;
        this.height = height;
        this.name = name;
        this.startup();
    }

    private void startup() throws NoSuchFieldException {
        //New Frame
        this.frame = new JFrame(this.name);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Toolkit xToolkit = Toolkit.getDefaultToolkit();
        Field awtAppClassNameField = xToolkit.getClass().getDeclaredField("awtAppClassName");
        try {
            awtAppClassNameField.setAccessible(true);
        } catch (SecurityException e) {

        }
        try {
            awtAppClassNameField.set(xToolkit, this.name);
        } catch (IllegalAccessException e) {
        }
        frame.setVisible(true);
        frame.setSize(width, height);
        //closes the window if you press on the X, but with a confirmation Dialog, to confirm that the user wants to close it
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if(JOptionPane.showConfirmDialog(frame, "If you quit you will lose the game", "Are you sure you want to quit?", JOptionPane.YES_NO_OPTION)==0){
                    frame.dispose();
                }
            }
        } );
    }
}
