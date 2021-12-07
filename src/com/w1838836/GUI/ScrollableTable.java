package com.w1838836.GUI;

import javax.swing.*;

public class ScrollableTable extends JTable {
    protected JScrollPane mScrollPane;

    /**
     * Default constructor.
     */
    public ScrollableTable() {
        mScrollPane = new JScrollPane(this);
    }

    /**
     * Get the scroll pane of the object.
     *
     * @return The scroll pane.
     */
    public JScrollPane getPane() {
        return mScrollPane;
    }
}
