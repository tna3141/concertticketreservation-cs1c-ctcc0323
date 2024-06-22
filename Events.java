import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 * This program creates a window for concert ticket reservation.
 * It includes two buttons for upcoming events: a BINI concert and an IVE concert.
 * When a button is clicked, it opens a new window for ticket selection.
 */

public class Events implements ActionListener {
    JFrame frame = new JFrame("Concert Ticket Reservation");
    JButton iveConcertButton, biniConcertButton;
    JLabel label;
    
    Events() {      
        // Resize BINI concert poster
        BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File("biniverse.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image scaledImg1 = img1.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon biniIcon = new ImageIcon(scaledImg1);
        
        // Resize IVE concert poster
        BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File("ive.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image scaledImg2 = img2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iveIcon = new ImageIcon(scaledImg2);
        
        // Empty border for buttons
        Border emptyBorder = BorderFactory.createEmptyBorder();
        
        // Label for upcoming events
        label = new JLabel("Upcoming Events");
        label.setBounds(45, 40, 200, 20);
        label.setFont(new Font("Bahnschrift", Font.BOLD,18));
        frame.add(label);
        
        // Button for BINI concert
        biniConcertButton = new JButton();
        biniConcertButton.setBounds(45, 85, 200, 240);
        biniConcertButton.setIcon(biniIcon);
        biniConcertButton.setText("<html> BINIverse: The First Solo Concert <br/> <center>June 28, 2024 | 7PM </center><html>");
        biniConcertButton.setHorizontalTextPosition(JButton.CENTER);
        biniConcertButton.setVerticalTextPosition(JButton.BOTTOM);
        biniConcertButton.setBackground(Color.WHITE);
        biniConcertButton.setFocusable(false);
        biniConcertButton.setBorder(emptyBorder);
        frame.add(biniConcertButton);
        biniConcertButton.addActionListener(this);
        
        // Button for IVE concert
        iveConcertButton = new JButton();
        iveConcertButton.setBounds(270, 85, 200, 240);
        iveConcertButton.setIcon(iveIcon);
        iveConcertButton.setText("<html> IVE SHOW WHAT i HAVE <br/><center> July 17, 2024 | 7PM </center><html>");
        iveConcertButton.setHorizontalTextPosition(JButton.CENTER);
        iveConcertButton.setVerticalTextPosition(JButton.BOTTOM);
        iveConcertButton.setBackground(Color.WHITE);
        iveConcertButton.setFocusable(false);
        iveConcertButton.setBorder(emptyBorder);
        frame.add(iveConcertButton);
        iveConcertButton.addActionListener(this);
        
        frame.setSize(540, 440);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== biniConcertButton) {
            // Close current window and open Ticket Selection window
            frame.setVisible(false);
            new TicketSelection("BINIverse: The First Solo Concert", "New Frontier Theater", "June 28, 2024 | 7PM");   
        } else if (e.getSource()== iveConcertButton) {
            // Close current window and open Ticket Selection window
            frame.setVisible(false); // close current window
            new TicketSelection("IVE SHOW WHAT i HAVE Concert", "SM MOA Arena", "July 17, 2024 | 7PM");
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Events();
    }
}
