package com.w1838836.GUI;

import java.util.Comparator;

public class DriverDataComparator implements Comparator<String> {

    /**
     * Compare function override.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return The result after comparing the two arguments. 1 if the first argument is greater than the other, -1 if the
     * first argument is less than the second and 0 if the two arguments are equal.
     */
    @Override
    public int compare(String s1, String s2) {
        try {
            int n1 = Integer.parseInt(s1);
            int n2 = Integer.parseInt(s2);

            if (n1 > n2)
                return 1;
            else if (n1 < n2)
                return -1;

            return 0;
        } catch (NumberFormatException e) {
            return s1.compareTo(s2);
        }
    }
}
