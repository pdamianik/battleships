package net.battleships.game;

import javax.swing.*;
import java.awt.event.*;

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

    public Window(String name) {
        this.width = 100;
        this.height = 100;
        this.name = name;
        this.startup();
    }

    public Window(String name, int width, int height) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.startup();
    }

    private void startup(){
        //New Frame
        this.frame = new JFrame(this.name);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
