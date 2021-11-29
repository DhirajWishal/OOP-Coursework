package com.w1838836.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DriverStatisticsTable extends JTable {
    private DefaultTableModel mModel = new DefaultTableModel();
    private TableRowSorter<DefaultTableModel> mSorter = new TableRowSorter<>(mModel);
    private boolean bSwitchSortingOrder = true;

    /**
     * Default constructor.
     */
    public DriverStatisticsTable() {
        setModel(mModel);

        // Set the column titles.
        String[] titles = {"Name", "Location", "Team", "Points", "# First places", "# Second places", "# Third places", "# Of races"};
        for (String title : titles)
            mModel.addColumn(title);

        DriverDataComparator comparator = new DriverDataComparator();

        // Set the comparators for the relevant rows.
        mSorter.setComparator(3, comparator);
        mSorter.setComparator(4, comparator);
        mSorter.setComparator(5, comparator);
        mSorter.setComparator(6, comparator);
        mSorter.setComparator(7, comparator);

        // Set the row sorter.
        setRowSorter(mSorter);

        // Setup mouse listener to sort the table rows.
        getTableHeader().addMouseListener(new MouseAdapter() {

            /**
             * Mouse click event override.
             * @param event The mouse event.
             */
            @Override
            public void mouseClicked(MouseEvent event) {
                int col = columnAtPoint(event.getPoint());
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();

                // Check and set the sorting order.
                if (bSwitchSortingOrder) {
                    sortKeys.add(new RowSorter.SortKey(col, SortOrder.ASCENDING));
                    bSwitchSortingOrder = false;
                } else {
                    sortKeys.add(new RowSorter.SortKey(col, SortOrder.DESCENDING));
                    bSwitchSortingOrder = true;
                }

                mSorter.setSortKeys(sortKeys);
            }
        });
    }

    /**
     * Set the table rows with data.
     *
     * @param rows The row data to set.
     */
    public void setRows(String[][] rows) {
        mModel.setRowCount(0);
        for (String[] row : rows)
            mModel.addRow(row);
    }
}
