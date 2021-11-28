package com.w1838836.GUI;

import com.w1838836.Driver;
import com.w1838836.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchResultsTable extends JTable {
    private final DefaultTableModel mModel = new DefaultTableModel();

    /**
     * Default constructor.
     */
    public SearchResultsTable() {
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
     */
    public void searchAndDisplay(String name, ArrayList<Race> races) {
        mModel.setRowCount(0);

        for (Race race : races) {
            for (Driver driver : race.getDrivers()) {
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
    }
}
