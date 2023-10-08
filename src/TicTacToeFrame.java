import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame
{
    // Panels
    JPanel mainPanel;
    JPanel titlePanel;
    JPanel gamePanel;
    JPanel buttonPanel;


    // Panel Assets

    // Title Panel
    JLabel title;

    // Game Panel
    static TicTacToeTile topLeftTile;
    static TicTacToeTile topMiddleTile;
    static TicTacToeTile topRightTile;

    static TicTacToeTile centerLeftTile;
    static TicTacToeTile centerMiddleTile;
    static TicTacToeTile centerRightTile;

    static TicTacToeTile bottomLeftTile;
    static TicTacToeTile bottomMiddleTile;
    static TicTacToeTile bottomRightTile;


    // Button Panel
    JButton quitButton;
    static JOptionPane dialogBox;
    private static int playAgain;

    // Backend Game Logic
    private static final int ROW = 3;
    private static final int COL = 3;
    private static int turnCounter = 0;
    private static boolean isXMove = true;
    private static boolean isOMove = false;
    private static String[][] board = new String[ROW][COL];


    public TicTacToeFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        createGamePanel();
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        resetBoard();
    }



    // Create Panels
    private void createTitlePanel()
    {
        titlePanel = new JPanel();
        title = new JLabel("Tic Tac Toe");
        title.setFont(new Font("Montserrat", Font.PLAIN, 48));
        titlePanel.add(title);
    }

    private void createGamePanel()
    {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        dialogBox = new JOptionPane();

        topLeftTile = new TicTacToeTile(0, 0);
        topMiddleTile = new TicTacToeTile(0, 1);
        topRightTile = new TicTacToeTile(0, 2);

        centerLeftTile = new TicTacToeTile(1, 0);
        centerMiddleTile = new TicTacToeTile(1, 1);
        centerRightTile = new TicTacToeTile(1, 2);

        bottomLeftTile = new TicTacToeTile(2, 0);
        bottomMiddleTile = new TicTacToeTile(2, 1);
        bottomRightTile = new TicTacToeTile(2, 2);


        gamePanel.add(topLeftTile);
        gamePanel.add(topMiddleTile);
        gamePanel.add(topRightTile);

        gamePanel.add(centerLeftTile);
        gamePanel.add(centerMiddleTile);
        gamePanel.add(centerRightTile);

        gamePanel.add(bottomLeftTile);
        gamePanel.add(bottomMiddleTile);
        gamePanel.add(bottomRightTile);

    }

    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
        buttonPanel.add(quitButton);
    }

    private static void resetBoard()
    {
        turnCounter = 0;
        isOMove = false;
        isXMove = true;

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = " ";
            }
        }

        topLeftTile.setText("");
        topMiddleTile.setText("");
        topRightTile.setText("");

        centerLeftTile.setText("");
        centerMiddleTile.setText("");
        centerRightTile.setText("");

        bottomLeftTile.setText("");
        bottomMiddleTile.setText("");
        bottomRightTile.setText("");

    }

    public static void markSpace(TicTacToeTile tile)
    {
        if (board[tile.getRow()][tile.getCol()].equalsIgnoreCase(" "))
        {

            turnCounter++;

            if (isXMove)
            {
                tile.setText("X");
                board[tile.getRow()][tile.getCol()] = "X";
                showBoard();

                if (turnCounter >= 5)
                {
                    if (isWin("X"))
                    {
                        playAgain = dialogBox.showConfirmDialog(null, "X wins.\nPlay Again?", "Play Again?", JOptionPane.YES_NO_OPTION);

                        if (playAgain == dialogBox.YES_OPTION)
                        {
                            resetBoard();
                        }
                        else
                        {
                            System.exit(0);
                        }
                    }
                    else if (turnCounter >= 7)
                    {
                        if (isTie())
                        {
                            playAgain = dialogBox.showConfirmDialog(null, "Tie.\nPlay Again?", "Play Again?", JOptionPane.YES_NO_OPTION);

                            if (playAgain == dialogBox.YES_OPTION)
                            {
                                resetBoard();
                            }
                            else
                            {
                                System.exit(0);
                            }
                        }
                    }
                }
            }

            if (isOMove)
            {
                tile.setText("O");
                board[tile.getRow()][tile.getCol()] = "O";
                showBoard();

                if (turnCounter >= 5)
                {
                    if (isWin("O"))
                    {
                        playAgain = dialogBox.showConfirmDialog(null, "O wins.\nPlay Again?", "Play Again?", JOptionPane.YES_NO_OPTION);

                        if (playAgain == dialogBox.YES_OPTION)
                        {
                            resetBoard();
                        }
                        else
                        {
                            System.exit(0);
                        }
                    }
                    else if (turnCounter >= 7)
                    {
                        if (isTie())
                        {
                            playAgain = dialogBox.showConfirmDialog(null, "Tie.\nPlay Again?", "Play Again?", JOptionPane.YES_NO_OPTION);

                            if (playAgain == dialogBox.YES_OPTION)
                            {
                                resetBoard();
                            }
                            else
                            {
                                System.exit(0);
                            }
                        }
                    }
                }
            }
            if (turnCounter != 0)
            {
                changeTurn();
            }
        }
        else
        {
            dialogBox.showMessageDialog(null, "Illegal Move.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void changeTurn()
    {
        if (isXMove)
        {
            isXMove = false;
            isOMove = true;
        }

        else if (isOMove)
        {
            isXMove = true;
            isOMove = false;
        }
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private static boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }

    // Method for validation and testing, outputs current board to console
    // Ensures variable consistency w/ GUI
    public static void showBoard()
    {
        System.out.println();
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println("X move: " + isXMove);
        System.out.println("O move: " + isOMove);
        System.out.println("Turn: " + turnCounter);
    }
}
