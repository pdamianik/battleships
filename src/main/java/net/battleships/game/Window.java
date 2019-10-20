package net.battleships.game;

import java.awt.*;
import java.awt.event.*;

public class Window {
    private int width;
    private int height;
    private String name;

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
        Frame f = new Frame(this.name);
        f.setVisible(true);
        f.setSize(this.width, this.height);
        //closes the window if you press on the X
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                f.dispose();
            }
        } );
    }
}
