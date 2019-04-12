package threadChanger;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.lang.Thread.*;

public class threadChanger {
    static JButton button;

    // 4x2 grid
    static int rows = 4;
    static int columns = 2;

    static Vector<JButton> vectorOfButtons = new Vector<JButton>();

    public static void main(String[] args) {
        JFrame jf = new JFrame("threadChanger");
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(rows, columns));
        addButtons(mainPanel, vectorOfButtons);

        ButtonThread theThread = new ButtonThread();
        theThread.start();

        jf.add(mainPanel);
        jf.setVisible(true);
    }

    // Creates a random color scheme with randomColor, passing in 3 randomInt.nextInt values for the (r,g,b) values.
    // nextInt uses the int passed in as a bound, [0, bound)
    // Then creates a button with the text of the Button and the number of the button, and sets backgroundColor to randomColor.
    // NEW sets name of the button to "Running", for checking with the thread later
    // Adds the action listener, ButtonListener which is defined later. Then adds the new button to the vob given as a parameter
    // Then after all the buttons are added, iterates through the vector of buttons and adds them to the JPanel passed in
    static void addButtons(JPanel jp, Vector<JButton> vob) {
        Random randomInt = new Random();
        for (int i = 0; i < (rows * columns); i++) {
            Color randomColor = new Color(randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256));
            button = new JButton("Button " + (i + 1));
            button.setName("Running");
            button.setBackground(randomColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setContentAreaFilled(true);
            button.addActionListener(new ButtonListener());
            vob.add(button);
        }
        for (int j = 0; j < vob.size(); j++) {
            jp.add(vob.elementAt(j));
        }
    }

    // ButtonListener class which implements ActionListener. Overrides the actionPerformed function
    // Gets the button from the button that got clicked.
    // Iterates through the vectorOfButtons using a for each loop, again creates a random color for each button
    // If the button was the button that was clicked, it'll check to see what the name of the button is
    // If the button is running it'll change it to sleeping, and if sleeping it'll make it running
    static class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent click) {
            JButton action = (JButton) click.getSource();
            for (JButton button : vectorOfButtons) {
                if (button == action) {
                    if ((button.getName()).equals("Running")) {
                        button.setName("Sleeping");
                    }
                    else{
                        button.setName("Running");
                    }
                }
            }
        }
    }

    // Thread class
    // Iterates through the vectorOfButtons, and checks the name of each button. If the button is "Running",
    // creates another random color for it to switch to every 1 second (1000 milliseconds)
    // If it's "Sleeping" or anything other than "Running" it'll just stop the thread (if and when it's clicked)
    static class ButtonThread extends Thread {
        public void run() {
            while (true) {
                for (JButton button : vectorOfButtons) {
                    if (button.getName().equals("Running")) {
                        Random ranInt = new Random();
                        Color changingColor = new Color(ranInt.nextInt(256), ranInt.nextInt(256), ranInt.nextInt(256));
                        button.setBackground(changingColor);
                    }
                }
                try {
                    sleep(1000);
                } catch (InterruptedException ie) {

                }
            }
        }
    }
}