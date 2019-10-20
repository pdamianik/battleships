package net.battleships.game;

import javax.swing.*;
import java.awt.event.*;

public class Window {
    private int width;
    private int heigth;
    private String name;
    private JFrame frame;
    private boolean closeFrame = false;

    public Window(String name) {
        this.width = 100;
        this.heigth = 100;
        this.name = name;
        this.startup();
    }

    public Window(String name, int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
        this.name = name;
        this.startup();
    }

    private void startup(){
        //New Frame
        this.frame = new JFrame(this.name);
        frame.setVisible(true);
        frame.setSize(width, heigth);
        //closes the window if you press on the X
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Are you sure you want to quit?", JOptionPane.YES_NO_OPTION)==0){
                    frame.dispose();
                }
            }
        } );
    }
}
