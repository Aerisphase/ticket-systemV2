package com.ticketclient;

import com.ticketclient.gui.MainFrame;
import javax.swing.*;

public class TicketClientApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
