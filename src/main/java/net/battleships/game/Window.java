package net.battleships.game;

import java.awt.*;
import java.awt.event.*;

public class Window {
    private int width;
    private int heigth;
    private String name;

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
        Frame f = new Frame(this.name);
        f.setVisible(true);
        f.setSize(width, heigth);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                f.dispose();
            }
        } );
    }
}
