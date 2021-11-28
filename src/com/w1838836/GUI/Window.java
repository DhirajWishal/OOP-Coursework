package com.w1838836.GUI;

import javax.swing.*;

public class Window extends JFrame {

    /**
     * Default constructor.
     */
    public Window() {
        super("Championship Manager");
        
        // This will make sure that the frame will get disposed when closed.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Create a window with a custom title.
     *
     * @param title The title of the window.
     */
    public Window(final String title) {
        super(title);

        // This will make sure that the frame will get disposed when closed.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
