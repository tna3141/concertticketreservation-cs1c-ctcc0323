import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * This class creates a window to display reservation details.
 * It shows information such as concert details, ticket holder's name, ticket type, and price.
 */

public class ReservationDetails {
    JFrame frame = new JFrame("Reservation Details");
    JLabel poster, concertLabel, venueLabel, dateTimeLabel, nameLabel, ticketDetailsLabel, noteLabel, priceLabel; 
    JButton okButton;
        
    ReservationDetails(String fullName, String concert, String venue, String date_time, String ticketDetails, Integer totalPrice) {     
        // Display concert poster image
        poster = new JLabel();
        poster.setBounds(30, 30, 230, 230);
        frame.add(poster);
        
        // Set poster image based on selected concert
        if ("BINIverse: The First Solo Concert".equals(concert)) {
            // resize BINI poster image
            BufferedImage img1 = null;
            try {
                img1 = ImageIO.read(new File("biniverse.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(TicketSelection.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image scaledImg1 = img1.getScaledInstance(230, 230, Image.SCALE_SMOOTH);
            ImageIcon biniSP = new ImageIcon(scaledImg1);
            
            poster.setIcon(biniSP); // Set BINI concert poster image
            
        } else if ("IVE SHOW WHAT i HAVE Concert".equals(concert)) {
            // resize IVE concert seat plan image
            BufferedImage img2 = null;
            try {
                img2 = ImageIO.read(new File("ive.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(TicketSelection.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image scaledImg2 = img2.getScaledInstance(230, 230, Image.SCALE_SMOOTH);
            ImageIcon iveSP = new ImageIcon(scaledImg2);
            
            poster.setIcon(iveSP); // Set IVE concert poster image
        }
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 370); // Set dimensions of the window
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(null); // Use null layout for absolute positioning
        frame.setVisible(true);// Make the frame visible
        frame.setLocationRelativeTo(null);

        // Label for concert name
        concertLabel = new JLabel(concert);
        concertLabel.setBounds(290, 30, 300, 20); // Set position and size
        concertLabel.setFont(new Font("Bahnschrift", Font.BOLD,15));
        frame.add(concertLabel);// Add label to the frame

        // Label for concert venue
        venueLabel = new JLabel(venue);
        venueLabel.setBounds(290, 55, 200, 20); // Set position and size
        venueLabel.setFont(new Font("Bahnschrift", Font.PLAIN,14));
        frame.add(venueLabel);// Add label to the frame

        // Label for concert date and time
        dateTimeLabel = new JLabel(date_time);
        dateTimeLabel.setBounds(290, 80, 200, 20);
        dateTimeLabel.setFont(new Font("Bahnschrift", Font.PLAIN,14)); // Set position and size
        frame.add(dateTimeLabel);// Add label to the frame

        // Label for ticket holder's name
        nameLabel = new JLabel("<html><b> Ticket Holder Name: </b><br/>" + fullName);
        nameLabel.setBounds(290, 110, 400, 40); // Set position and size
        nameLabel.setFont(new Font("Bahnschrift", Font.PLAIN,14));
        frame.add(nameLabel);// Add label to the frame

        // Label for ticket details
        ticketDetailsLabel = new JLabel("<html><b>Ticket/s:</b><br/>" + ticketDetails.replace("\n", "<br/>") + "</html>");
        ticketDetailsLabel.setBounds(290, 160, 300, 60); // Set position and size
        ticketDetailsLabel.setFont(new Font("Bahnschrift", Font.PLAIN,14));
        frame.add(ticketDetailsLabel);// Add label to the frame
        
        // Label for total price
        priceLabel = new JLabel("<html><b> Total: </b>  Php " + String.format("%.2f", (double) totalPrice));
        priceLabel.setBounds(290, 230, 300, 40); // Set position and size
        priceLabel.setFont(new Font("Bahnschrift", Font.PLAIN,14));
        frame.add(priceLabel);// Add label to the frame

        // Label for note
        noteLabel = new JLabel("*You cannot change details on this ticket.");
        noteLabel.setBounds(30, 260, 300, 20); // Set position and size
        frame.add(noteLabel);// Add label to the frame

        // Button to close the window
        okButton = new JButton("OK");
        okButton.setBounds(450, 280, 80, 25); // Set position and size
        frame.add(okButton);// Add button to the frame

        // ActionListener for OK button
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close current window and go back to Confirmation window
                frame.dispose();
                new Confirmation(fullName, concert, venue, date_time, ticketDetails, totalPrice);
            }
        });
    }
}

