import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* Todo
 * Fix a reporting board that is easy to solve - Done // Beata
 * Fix Function for discover that user solved the board + win message - Done // Beata
 * Fix that buttons are in a random order + new game - Done // Valeria
 * Check if code need optimize // Beata
 * Fix bigger buttons - Done // Beata
 * Fix a border around buttons - Done // Beata
 * Fix button board + color - Done // Beata
 * Check if board is solvable
 */


public class GameBoard extends JFrame implements ActionListener {

    private final static int BUTTON_NUMBERS = 15;
    Random random = new Random();

    JPanel mainPanel = new JPanel();
    JPanel boardPanel = new JPanel(new GridLayout(4, 4));
    JPanel northPanel = new JPanel(new BorderLayout());
    JPanel southPanel = new JPanel(new BorderLayout());
    JPanel southSouthPanel = new JPanel();
    JPanel southNorthPanel = new JPanel();
    JPanel northNorthPanel = new JPanel();
    JPanel northSouthPanel = new JPanel();
    JPanel northEastPanel = new JPanel();
    JPanel northWestPanel = new JPanel();

    JLabel text = new JLabel(" ", JLabel.CENTER);

    List<JButton> buttonList = new ArrayList<>();
    List<Integer> randomNumbers = new ArrayList<>();

    JButton newGame = new JButton("New game");

    public GameBoard() {

        this.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        northPanel.add(boardPanel, BorderLayout.CENTER);
        northPanel.add(northNorthPanel, BorderLayout.NORTH);
        northPanel.add(northSouthPanel, BorderLayout.SOUTH);
        northPanel.add(northEastPanel, BorderLayout.EAST);
        northPanel.add(northWestPanel, BorderLayout.WEST);
        northPanel.setBorder(new LineBorder(Color.BLACK, 3));

        northNorthPanel.setPreferredSize(new Dimension(10, 10));
        northSouthPanel.setPreferredSize(new Dimension(10, 10));
        northEastPanel.setPreferredSize(new Dimension(10, 10));
        northWestPanel.setPreferredSize(new Dimension(10, 10));

        northNorthPanel.setBackground(Color.RED);
        northSouthPanel.setBackground(Color.RED);
        northWestPanel.setBackground(Color.RED);
        northEastPanel.setBackground(Color.RED);

        southPanel.setPreferredSize(new Dimension(100, 100));
        southPanel.add(southSouthPanel, BorderLayout.SOUTH);
        southPanel.add(southNorthPanel, BorderLayout.NORTH);

        boardPanel.setBorder(new LineBorder(Color.BLACK, 1));
        boardPanel.setMaximumSize(new Dimension(100, 100));



        newGame.addActionListener(this);
        newGame.setPreferredSize(new Dimension(200, 50));
        newGame.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        newGame.setBorder(new LineBorder(Color.BLACK, 1));

        boolean reportBoard = false;

        if (reportBoard) {
            for (int i = 1; i <= BUTTON_NUMBERS - 1; i++) {
                JButton button = new JButton(String.valueOf(i));
                addStyledButton(button);
            }
            JButton empty = new JButton(" ");
            addStyledButton(empty);

            JButton b15 = new JButton("15");
            addStyledButton(b15);

        } else {

            // Create empty board
            for (int i = 0; i <= BUTTON_NUMBERS; i++) {
                JButton button = new JButton();
                addStyledButton(button);
                randomNumbers.add(i);
            }
            setNewGameBoard();
        }

        southSouthPanel.add(text);
        southNorthPanel.add(newGame);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void setNewGameBoard() {
        do {
            Collections.shuffle(randomNumbers);//flyttar om elementen i listan
        } while (!isSolvable(randomNumbers));
        for (int i = 0; i <= BUTTON_NUMBERS; i++) {
            int number = randomNumbers.get(i);
            JButton button = buttonList.get(i);
            if (number == 0) {
                button.setText(" ");
            } else {
                button.setText(String.valueOf(number));
            }
        }
    }


    public void addStyledButton(JButton button) {
        button.setPreferredSize(new Dimension(80, 80));
        button.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        button.setBorder(new LineBorder(Color.BLACK, 1));
        boardPanel.add(button);
        buttonList.add(button);
        button.addActionListener(this);
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
            String s1 = buttonList.get(i).getText();
            String s2 = String.valueOf(i + 1);
            boolean buttonNumberCheck = buttonList.get(i).getText().equals(String.valueOf(i + 1));
            boolean button16IndexCheck = buttonList.get(15).getText().equals(" ");
            if (i == 15 && !button16IndexCheck) {
                finish = false;
                break;
            } else if (i != 15 && !buttonNumberCheck) {
                finish = false;
            }
        }
        return finish;
    }

    public boolean isSolvable (List<Integer> numbers){
        int inversions = 0;
        int gridWidth = 4;

        List<Integer> nums = new ArrayList<>(numbers);
        nums.remove(Integer.valueOf(0));
        // Räkna inversioner
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) > nums.get(j)) {
                    inversions++;
                }
            }
        }
        // Hitta positionen av tomrutan (0)
        int blankIndex = numbers.indexOf(0);
        int blankRowFromBottom = gridWidth - (blankIndex / gridWidth);
                // Regler för 4x4
        if (gridWidth % 2 == 0) {
            if (blankRowFromBottom % 2 == 0) {
                return inversions % 2 != 0;
            } else {
                return inversions % 2 == 0;
            }
        } else {
            return inversions % 2 == 0;
        }


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton emptyButton = findEmptyButton();
        JButton clicked = (JButton) e.getSource();

        if (newGame == clicked) {
            text.setVisible(false);
            setNewGameBoard();
            return;
        }

        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).equals(clicked)) {
                if (checkIfMoveIsPossible(i, emptyButton)) {
                    moveButton(clicked, emptyButton);
                    break;
                }
            }
        }
        if (newGame == clicked) {
            setNewGameBoard();
        } else if (gameFinish()) {
            text.setVisible(true);
            text.setSize(20, 20);
            text.setBorder(new EtchedBorder());
            text.setFont(new Font(Font.SERIF, Font.PLAIN, 22));
            text.setText(" \nCongratulation, you solve the board! ");
        }
    }


}

