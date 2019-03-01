package colorchanger;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class ColorChanger {
    static JButton button;

    // 4x2 grid
    static int rows = 4;
    static int columns = 2;

    static Vector<JButton> vectorOfButtons = new Vector<JButton>();

    public static void main(String[] args) {
        JFrame jf = new JFrame("ColorChanger");
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(rows, columns));
        addButtons(mainPanel, vectorOfButtons);

        jf.add(mainPanel);
        jf.setVisible(true);
    }

    // Creates a random color scheme with randomColor, passing in 3 randomInt.nextInt values for the (r,g,b) values.
    // nextInt uses the int passed in as a bound, [0, bound)
    // Then creates a button with the text of the Button and the number of the button, and sets backgroundColor to randomColor.
    // Adds the action listener, ButtonListener which is defined later. Then adds the new button to the vob given as a parameter
    // Then after all the buttons are added, iterates through the vector of buttons and adds them to the JPanel passed in.
    static void addButtons(JPanel jp, Vector<JButton> vob){
        Random randomInt = new Random();
        for(int i = 0; i < (rows * columns); i++){
            Color randomColor = new Color(randomInt.nextInt(256), randomInt.nextInt(256), randomInt.nextInt(256));
            button = new JButton("Button " + (i+1));
            button.setBackground(randomColor);
            button.setOpaque(true);
            button.addActionListener(new ButtonListener());
            vob.add(button);
        }
        for (int j = 0; j < vob.size(); j++){
            jp.add(vob.elementAt(j));
        }
    }

    // ButtonListener class which implements ActionListener. Overrides the actionPerformed function
    // Gets the button from the button that got clicked.
    // Iterates through the vectorOfButtons using a for each loop, again creates a random color for each button
    // If the button wasn't the button that was clicked, it'll change its color to the new random color.
    // Effectively changes the color of every other button that wasn't the initial clicked one. Clicked color remains the same.
    static class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent click) {
            JButton action = (JButton)click.getSource();
            Random ranInt = new Random();
            for (JButton button : vectorOfButtons){
                Color clickedColor = new Color(ranInt.nextInt(256), ranInt.nextInt(256), ranInt.nextInt(256));
                if (button != action){
                    button.setBackground(clickedColor);
                }
            }
        }
    }
}