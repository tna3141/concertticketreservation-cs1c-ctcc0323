import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class creates a window for selecting and reserving concert tickets.
 * It includes options for selecting ticket types, quantities, and viewing a summary of the selected tickets.
 */

public class TicketSelection implements ActionListener {
    JFrame frame = new JFrame();
    JLabel concertLabel, venueLabel, date_timeLabel, ticket_qtyLabel, summaryLabel, seatPlan, totalLabel;
    JTextArea ticketData;
    JButton addTicketButton, clearButton, cancelButton, reserveButton;
    JComboBox biniTickets, iveTickets, quantityComboBox;
    String concert, venue, date_time;
    int totalPrice = 0, ticketCount = 0;
    
    // Store available sections/tickets and their prices
    private static Map<String, Integer> biniSectionPrices = new HashMap<>();
    private static Map<String, Integer> iveSectionPrices = new HashMap<>();
    
    TicketSelection(String concert, String venue, String date_time) { 
        this.concert = concert;
        this.venue = venue;
        this.date_time = date_time;
        
        seatPlan = new JLabel();
        seatPlan.setBounds(35, 120, 290, 320);
        frame.add(seatPlan);
        
        // BINI concert ticket type selection
        biniTickets = new JComboBox<>(new String[]{"SVIP","VIP", "Orchestra", "Loge", "Balcony"}); 
        biniTickets.setBounds(365, 170, 150, 20);
        biniTickets.addActionListener(this);
        biniTickets.setVisible(false);
        frame.add(biniTickets);
            
        // IVE concert ticket type selection
        iveTickets = new JComboBox<>(new String[]{"VIP","LB A", "LB B", "Upper Box", "Gen Ad"}); 
        iveTickets.setBounds(365, 170, 150, 20);
        iveTickets.addActionListener(this);
        iveTickets.setVisible(false);
        frame.add(iveTickets);
        
        // Configure seat plan and ticket prices based on the concert
        if ("BINIverse: The First Solo Concert".equals(concert)) {
            // resize BINI concert seat plan image
            BufferedImage img1 = null;
            try {
                img1 = ImageIO.read(new File("binisp.png"));
            } catch (IOException ex) {
                Logger.getLogger(TicketSelection.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image scaledImg1 = img1.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon biniSP = new ImageIcon(scaledImg1);
            
            seatPlan.setIcon(biniSP);
            
            // Store BINI concert ticket type & prices
            biniSectionPrices = new HashMap<>();
            biniSectionPrices.put("SVIP", 5336);
            biniSectionPrices.put("VIP", 3733);
            biniSectionPrices.put("Orchestra", 2668);
            biniSectionPrices.put("Loge", 1067);
            biniSectionPrices.put("Balcony", 747);
            
            biniTickets.setVisible(true);
            
        } else if ("IVE SHOW WHAT i HAVE Concert".equals(concert)) {
            // Resize IVE concert seat plan image
            BufferedImage img2 = null;
            try {
                img2 = ImageIO.read(new File("ivesp.png"));
            } catch (IOException ex) {
                Logger.getLogger(TicketSelection.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image scaledImg2 = img2.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon iveSP = new ImageIcon(scaledImg2);
            
            seatPlan.setIcon(iveSP);
            
            // Store IVE concert ticket type & prices
            iveSectionPrices = new HashMap<>();
            iveSectionPrices.put("VIP", 13750);
            iveSectionPrices.put("LB A", 13250);
            iveSectionPrices.put("LB B", 12250);
            iveSectionPrices.put("Upper Box", 7250);
            iveSectionPrices.put("Gen Ad", 3000);

            iveTickets.setVisible(true);
        }
        
        // Label for concert name
        concertLabel = new JLabel(concert);
        concertLabel.setBounds(40, 30, 300, 20);
        concertLabel.setFont(new Font("Bahnschrift", Font.BOLD,18));
        frame.add(concertLabel);
        
        // Label for concert venue
        venueLabel = new JLabel(venue);
        venueLabel.setBounds(40, 55, 200, 20);
        venueLabel.setFont(new Font("Bahnschrift", Font.PLAIN,15));
        frame.add(venueLabel);
        
        // Label for concert date and time
        date_timeLabel = new JLabel(date_time);
        date_timeLabel.setBounds(40, 80, 200, 20);
        date_timeLabel.setFont(new Font("Bahnschrift", Font.PLAIN,15));
        frame.add(date_timeLabel);
        
        // Label for ticket type and quantity selection
        ticket_qtyLabel = new JLabel("Please select ticket type & quantity");
        ticket_qtyLabel.setBounds(365, 140, 250, 20);
        ticket_qtyLabel.setFont(new Font("Bahnschrift", Font.BOLD,15));
        frame.add(ticket_qtyLabel);
        
        // Ticket quantity selection
        Integer[] qty = {1, 2};
        quantityComboBox = new JComboBox(qty); 
        quantityComboBox.setBounds(525, 170, 50, 20);
        quantityComboBox.addActionListener(this);
        frame.add(quantityComboBox);
        
        // Button to add selected ticket
        addTicketButton = new JButton("Add Ticket");
        addTicketButton.setBounds(480, 200, 95, 20);
        addTicketButton.addActionListener(this);
        frame.add(addTicketButton);
        
        // Label for Summary
        summaryLabel = new JLabel("Summary");
        summaryLabel.setBounds(365, 250, 220, 20);
        summaryLabel.setFont(new Font("Bahnschrift", Font.BOLD,15));
        frame.add(summaryLabel);
        
        // Text area to display ticket summary
        ticketData = new JTextArea();
        ticketData.setBounds(365, 270, 250, 60);
        ticketData.setFont(new Font("Bahnschrift", Font.PLAIN,15));
        ticketData.setEditable(false);
        frame.add(ticketData);
        
        // Label for total price
        totalLabel = new JLabel("Total:   Php 0.00");
        totalLabel.setBounds(365, 350, 220, 20);
        totalLabel.setFont(new Font("Bahnschrift", Font.BOLD,15));
        frame.add(totalLabel);
        
        // Button to cancel ticket selection
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(365, 380, 80, 20);
        cancelButton.addActionListener(this);
        frame.add(cancelButton);
        
        // Button to clear ticket selection
        clearButton = new JButton("Clear");
        clearButton.setBounds(455, 380, 75, 20);
        clearButton.addActionListener(this);
        frame.add(clearButton);
        
        // Button to reserve selected tickets
        reserveButton = new JButton("Reserve Ticket");
        reserveButton.setBounds(540, 380, 120, 20);
        reserveButton.addActionListener(this);
        frame.add(reserveButton);
        
        frame.setSize(720, 560);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTicketButton) {
            String selectedSection = null;
            int price = 0;
            
            // Determine which concert ticket type is selected and get the price
            if (biniTickets.isVisible()) {
                selectedSection = (String) biniTickets.getSelectedItem();
                price = biniSectionPrices.get(selectedSection);
            } else if (iveTickets.isVisible()) {
                selectedSection = (String) iveTickets.getSelectedItem();
                price = iveSectionPrices.get(selectedSection);
            }
            
            int quantity = (int) quantityComboBox.getSelectedItem();
            
            // Check if the ticket limit is exceeded
            if (ticketCount + quantity > 2) {
                JOptionPane.showMessageDialog(frame, "You can only purchase up to 2 tickets.", "Ticket Limit Exceeded", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int totalSectionPrice = price * quantity;
            totalPrice += totalSectionPrice;
            ticketCount += quantity;
            
            // Update the ticket summary and total price
            ticketData.append(quantity + "  x  " + selectedSection + "\t-     Php " + totalSectionPrice + "\n");
            totalLabel.setText("Total:   Php " + String.format("%.2f", (double) totalPrice));
        }
        if (e.getSource()==cancelButton) {
            // Close current window and go back to events selection window
            frame.dispose();
            new Events();
        }
        if (e.getSource()==clearButton) {
            // Clear the ticket selection and reset the total price and ticket count
            ticketData.selectAll();
            ticketData.replaceSelection("");
            totalPrice = 0;
            ticketCount = 0;
            totalLabel.setText("Total:   Php 0.00");
        }
        if (e.getSource()==reserveButton) {
            if (ticketCount == 0) {
                // Display a message if there are no ticket/s added
                JOptionPane.showMessageDialog(frame, "Please add at least one ticket.", "No Tickets Added", JOptionPane.WARNING_MESSAGE);
            } else {
                // If at least one ticket is added, close current window and open Ticket Holder Info window
                String ticketDetails = ticketData.getText();
                frame.dispose();
                new TicketHolderInfo(concert, venue, date_time, ticketDetails, totalPrice);
            } 
        }
    }
}
