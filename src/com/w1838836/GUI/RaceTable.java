package com.w1838836.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RaceTable extends JTable {
    private final DefaultTableModel mModel = new DefaultTableModel();

    /**
     * Default constructor.
     */
    public RaceTable(String[][] rows) {
        setModel(mModel);

        // Set the column titles.
        String[] titles = {"Date", "Status (Type)"};
        for (String title : titles)
            mModel.addColumn(title);

        for (String[] row : rows)
            mModel.addRow(row);
    }
}
