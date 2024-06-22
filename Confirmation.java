import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

/**
 * This class creates a confirmation window for displaying reservation completion details.
 * It includes labels for thank you message, reservation status, and buttons to view details or close.
 */

public class Confirmation implements ActionListener {
    JFrame frame = new JFrame("Confirmation");
    JLabel thankYouLabel, messageLabel1, messageLabel2, messageLabel3, messageLabel4;
    JButton viewDetailsButton, eventsButton;
    String fullName, email, concert, venue, date_time, ticketDetails;
    int totalPrice;
    
    Confirmation(String fullName, String concert, String venue, String date_time, String ticketDetails, Integer totalPrice) {
        this.fullName = fullName;
        this.concert = concert;
        this.venue = venue;
        this.date_time = date_time;
        this.ticketDetails = ticketDetails;
        this.totalPrice = totalPrice;
        
        frame = new JFrame("Confirmation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        frame.setSize(465, 330);// Set dimensions of the window
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null); // Use null layout for absolute positioning
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Label to display "Thank you!"
        thankYouLabel = new JLabel("Thank you!");
        thankYouLabel.setBounds(50, 35, 350, 20); // Set position and size
        thankYouLabel.setFont(new Font("Bahnschrift", Font.BOLD,18));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(thankYouLabel);// Add label to the frame

        // Labels for reservation status messages
        messageLabel1 = new JLabel("Your reservation has been completed.");
        messageLabel1.setBounds(50, 70, 350, 20);
        messageLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(messageLabel1);

        messageLabel2 = new JLabel("The payment details will be sent to your email.");
        messageLabel2.setBounds(50, 100, 350, 20);
        messageLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(messageLabel2);

        messageLabel3 = new JLabel("Please complete your payment within the next 15 minutes");
        messageLabel3.setBounds(50, 130, 350, 20);
        messageLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(messageLabel3);
        
        messageLabel4 = new JLabel("or your reservation will be automatically cancelled.");
        messageLabel4.setBounds(50, 150, 350, 20);
        messageLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(messageLabel4);
        
        // Empty border for viewDetailsButton
        Border emptyBorder = BorderFactory.createEmptyBorder();
        
        // Button to view reservation details
        viewDetailsButton = new JButton("View Reservation Details");
        viewDetailsButton.setBounds(125, 180, 200, 20);
        viewDetailsButton.setBorder(emptyBorder);
        viewDetailsButton.setForeground(Color.BLUE);
        viewDetailsButton.setBackground(Color.WHITE);
        viewDetailsButton.setFocusable(false);
        viewDetailsButton.addActionListener(this);
        frame.add(viewDetailsButton);

        // Button to go back to Events window
        eventsButton = new JButton("Go Back to Events");
        eventsButton.setBounds(155, 230, 140, 23);
        eventsButton.addActionListener(this);
        frame.add(eventsButton);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==viewDetailsButton) {
            // Close current window and open Reservation Details window
            frame.dispose();
            new ReservationDetails(fullName, concert, venue, date_time, ticketDetails, totalPrice);
        }
        if (e.getSource()==eventsButton) {
            // Close current window and go back to Events window
            frame.dispose();
            new Events();
        }
    }
}

