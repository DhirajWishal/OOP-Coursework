package com.w1838836.GUI;

import javax.swing.*;
import java.awt.*;

public class UILayout extends GroupLayout {

    /**
     * Constructor.
     *
     * @param host The host container.
     */
    public UILayout(final Container host) {
        super(host);

        setAutoCreateGaps(true);
        setAutoCreateContainerGaps(true);
    }
}
