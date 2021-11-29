package com.w1838836.GUI;

import com.w1838836.Driver;
import com.w1838836.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchResultsTable extends JTable {
    private Window mParent = null;
    private DefaultTableModel mModel = new DefaultTableModel();

    /**
     * Default constructor.
     */
    public SearchResultsTable(Window parent) {
        mParent = parent;
        setModel(mModel);

        // Set the titles.
        String[] titles = {"Race date", "Race status (type)", "Position"};
        for (String title : titles)
            mModel.addColumn(title);
    }

    /**
     * Search and display a driver using his/ her name.
     *
     * @param name  The name of the driver.
     * @param races The races that took place in this season.
     * @return Boolean value stating if a row was inserted.
     */
    public boolean searchAndDisplay(String name, ArrayList<Race> races) {
        clear();

        // Iterate through the races to search adn find the driver.
        for (Race race : races) {
            for (Driver driver : race.getDrivers()) {
                // If the driver was found, display his/ her information.
                if (driver.getName().compareTo(name) == 0) {
                    String[] row = {
                            race.getDate().toString(),
                            race.getStatus().toString(),
                            String.valueOf(driver.getRaceInfo(race.getID()))
                    };

                    mModel.addRow(row);
                    break;
                }
            }
        }

        if (mModel.getRowCount() > 0)
            return true;

        // Issue a confirmation dialog as a warning.
        JOptionPane.showConfirmDialog(mParent,
                "No search results found!", "Search Results",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE);

        return false;
    }

    /**
     * Clear the table.
     */
    public void clear() {
        mModel.setRowCount(0);
    }
}
