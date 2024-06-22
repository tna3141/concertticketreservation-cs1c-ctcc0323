import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates a window for capturing ticket holder information.
 * It includes fields for first name, last name, phone number, and email address,
 * with buttons to confirm or go back.
 */

public class TicketHolderInfo implements ActionListener {
    JFrame frame = new JFrame("Ticket Holder's Information");
    JLabel messageLabel1, messageLabel2, firstNameLabel, lastNameLabel, phoneNumberLabel, emailLabel;
    JTextField firstNameField, lastNameField, phoneNumberField, emailField;
    JButton backButton, confirmButton;
    String concert, venue, date_time, ticketDetails;
    int totalPrice;
            
    TicketHolderInfo(String concert, String venue, String date_time, String ticketDetails, Integer totalPrice) {
        this.concert = concert;
        this.venue = venue;
        this.date_time = date_time;
        this.ticketDetails = ticketDetails;
        this.totalPrice = totalPrice;
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 340);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // Labels to display a message
        messageLabel1 = new JLabel("Ticket Holder's Information");
        messageLabel1.setBounds(50, 30, 300, 20); // Set position and size
        messageLabel1.setFont(new Font("Bahnschrift", Font.BOLD,18)); 
        frame.add(messageLabel1); // Add label to the frame
        
        messageLabel2 = new JLabel("You cannot change details after reservation.");
        messageLabel2.setBounds(50, 50, 300, 20); // Set position and size
        frame.add(messageLabel2); // Add label to the frame

        // Labels and text fields for first name
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 90, 100, 20);
        frame.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(160, 90, 200, 20);
        frame.add(firstNameField);

        // Labels and text fields for last name
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 120, 100, 20);
        frame.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(160, 120, 200, 20);
        frame.add(lastNameField);

        // Labels and text fields for phone number
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(50, 150, 100, 20);
        frame.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(160, 150, 200, 20);
        frame.add(phoneNumberField);

        // Labels and text fields for email address
        emailLabel = new JLabel("Email Address:");
        emailLabel.setBounds(50, 180, 120, 20);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(160, 180, 200, 20);
        frame.add(emailField);

        // Button to go back to the previous window
        backButton = new JButton("Back");
        backButton.setBounds(50, 230, 80, 25);
        backButton.addActionListener(this);
        frame.add(backButton);

        // Button to confirm the entered information and proceed to the next window
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(150, 230, 80, 25);
        confirmButton.addActionListener(this);
        frame.add(confirmButton);
    }
    
    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton) {
            // Close current window and go back to Ticket Selection window
            frame.dispose();
            new TicketSelection(concert, venue, date_time);
        }
        if (e.getSource()==confirmButton) {
            // Confirm entered information
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();
            String fullName = firstName + " " + lastName;
                    
            // Check if any field is empty
            if (fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                // Display an error message if any field is empty
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
                                
            // If all fields are filled, close current window and proceed with Confirmation window
            frame.dispose();
            new Confirmation(fullName, concert, venue, date_time, ticketDetails, totalPrice); // Launch confirmation window with entered details
            
        }
    }
}

