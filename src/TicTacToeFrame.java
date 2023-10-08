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
    TicTacToeTile topLeftTile;
    TicTacToeTile topMiddleTile;
    TicTacToeTile topRightTile;

    TicTacToeTile centerLeftTile;
    TicTacToeTile centerMiddleTile;
    TicTacToeTile centerRightTile;

    TicTacToeTile bottomLeftTile;
    TicTacToeTile bottomMiddleTile;
    TicTacToeTile bottomRightTile;


    // Button Panel
    JButton quitButton;
    JOptionPane dialogBox;

    // Backend Game Logic
    private static final int ROW = 3;
    private static final int COL = 3;
    private int turnCounter = 0;
    private boolean isXMove = true;
    private boolean isOMove = false;
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
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
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

        topLeftTile = new TicTacToeTile(0, 0);
        topMiddleTile = new TicTacToeTile(0, 1);
        topRightTile = new TicTacToeTile(0, 2);

        centerLeftTile = new TicTacToeTile(1, 0);
        centerMiddleTile = new TicTacToeTile(1, 1);
        centerRightTile = new TicTacToeTile(1, 2);

        bottomLeftTile = new TicTacToeTile(2, 0);
        bottomMiddleTile = new TicTacToeTile(2, 1);
        bottomRightTile = new TicTacToeTile(2, 2);


        topLeftTile.addActionListener((ActionEvent ae) ->
        {
            markSpace(topLeftTile);
            changeTurn();
        });

        topMiddleTile.addActionListener((ActionEvent ae) ->
        {
            markSpace(topMiddleTile);
            changeTurn();
        });

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

    private void resetBoard()
    {
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private void markSpace(TicTacToeTile tile)
    {
        if (board[tile.getRow()][tile.getCol()].equalsIgnoreCase(" "))
        {
            if (isXMove)
            {
                tile.setText("X");
                board[tile.getRow()][tile.getCol()] = "X";
            }

            if (isOMove)
            {
                tile.setText("O");
                board[tile.getRow()][tile.getCol()] = "O";
            }
        }
        else
        {
            dialogBox = new JOptionPane("Invalid Move");
        }
    }

    private void changeTurn()
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
}
