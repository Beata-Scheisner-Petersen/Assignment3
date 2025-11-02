import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/* Todo
* Fix a reporting board that is easy to solve
* Fix Function for discover that user solved the board + win message
* Fix that buttons are in a random order
* Check if code need optimize
* Fix a border around buttons
* Fix button board + color */


public class GameBoard extends JFrame implements ActionListener {
    JPanel mainPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel northPanel = new JPanel(new GridLayout(4, 4));
    JLabel text = new JLabel(" ", JLabel.CENTER);
    List<JButton> buttonList = new ArrayList<>();


    public GameBoard() {


        this.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        int buttonNumbers = 15;

        boolean reportBoard = true;

        if (reportBoard) {
            for (int i = 1; i <= buttonNumbers - 1; i++) {
                JButton button = new JButton(String.valueOf(i));
                northPanel.add(button);
                button.addActionListener(this);
                buttonList.add(button);
            }
            JButton empty = new JButton(" ");
            buttonList.add(empty);
            northPanel.add(empty);
            empty.addActionListener(this);

            JButton b15 = new JButton("15");
            buttonList.add(b15);
            northPanel.add(b15);
            b15.addActionListener(this);
        }

        southPanel.add(text);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public boolean isEmpty(JButton button) {

        return button.getText().equals(" ");
    }

    public JButton findEmptyButton() {

        for (JButton emptyButton : buttonList) {
            if (isEmpty(emptyButton))
                return emptyButton;
        }
        return null;
    }


    public void moveButton(JButton clickedButton, JButton emptyButton) {
        String buttonNumber = clickedButton.getText();
        if (!isEmpty(clickedButton)) {
            emptyButton.setText(buttonNumber);
            clickedButton.setText(" ");
        }
    }

    public boolean checkIfMoveIsPossible(int i, JButton emptyButton) {
        boolean moveIsPossible = switch (i) {
            // button 1
            case 0 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 2 & 3
            case 1, 2 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 4
            case 3 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 5
            case 4 ->
                    buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 6 & 7
            case 5, 6 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 8
            case 7 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 9
            case 8 ->
                    buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 10 & 11
            case 9, 10 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 12
            case 11 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton) || buttonList.get(i + 4).equals(emptyButton);
            // button 13
            case 12 -> buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            // button 14 & 15
            case 13, 14 ->
                    buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i + 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            // button "16"
            case 15 -> buttonList.get(i - 1).equals(emptyButton) || buttonList.get(i - 4).equals(emptyButton);
            default -> false;
        };

        return moveIsPossible;
    }
    public boolean gameFinish() {
        boolean finish = true;
        for (int i = 0; i < buttonList.size(); i++) {

            if (!(buttonList.get(i).getText().equals(String.valueOf(i + 1)))) {
                if (i == 15 && (!(buttonList.get(i).getText().equals(String.valueOf(" "))))) {
                    finish = false;
                }
            }
        }
        return finish;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton emptyButton = findEmptyButton();
        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).equals(clicked)) {
                if (checkIfMoveIsPossible(i, emptyButton)) {
                    moveButton(clicked, emptyButton);
                    break;
                }
            }
        }
        if (gameFinish()) {
            text.setText(" Congratulation, you solve the board! ");
            text.setBorder(new EtchedBorder());
            text.setFont(new Font("Aptos", Font.BOLD, 12));
        }
    }


}

