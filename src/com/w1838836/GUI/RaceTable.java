package com.w1838836.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RaceTable extends JTable {
    /**
     * Default constructor.
     */
    public RaceTable(String[][] rows) {
        DefaultTableModel model = new DefaultTableModel();
        setModel(model);

        // Set the column titles.
        String[] titles = {"Date", "Status (Type)"};
        for (String title : titles)
            model.addColumn(title);

        // Insert the rows.
        for (String[] row : rows)
            model.addRow(row);
    }
}
