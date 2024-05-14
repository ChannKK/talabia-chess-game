
// board GUI, handle user interactions with board
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardView extends JFrame {
    private Board board = Board.getBoardInstance();
    private Piece piece;
    private JPanel boardPanel;
    private Dimension boardSize = new Dimension(650, 650);
    boolean firstClick = false;
    Piece pieceMove;
    private JLabel turnLabel;
    private JLabel turnCount;
    GameController gameControl;
    ImageInverter invert_image;

    // Chan Kar Kin, Ng Yun Shi
    // initialize BoardView instance, set up board
    public BoardView(Board board) {
        this.board = board;
        gameControl = new Game(this, board);
        gameControl.initializePiece();
        initialize();
        createMenu(); // menu

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
    }

    // Ng Yun Shi, Chan Kar Kin
    // let user to confirm they want to exit game or not
    private void confirmExit() {
        int exitChoice = JOptionPane.showConfirmDialog(this,
                "Confirm exit game?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (exitChoice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            return;
        }
    }

    // Chan Kar Kin
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    // Ang Jin Nan
    // creates panel for game board and sets up GUI
    public void initialize() {
        boardPanel = new JPanel(new GridLayout(board.getHeight(), board.getWidth()));
        add(boardPanel);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(boardSize);
        setTitle("Talabia chess");
        setVisible(true);

        SwingUtilities.invokeLater(() -> {
            InitializeImageOnButton();
            revalidate();
            repaint();
        });
    }

    // Ang Jin Nan, Chan Kar Kin
    // make a board panel with buttons representing squares, add scaled images on
    // buttons based on pieces of the squares
    public void InitializeImageOnButton() {
        int buttonWidth = boardPanel.getWidth() / board.getWidth();
        int buttonHeight = boardPanel.getHeight() / board.getHeight();
        for (Square sq : board.getSquare()) {
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameControl.handleButtonClick(sq);
                }
            });

            piece = sq.getPiece();

            if (piece != null && piece.getImgPath() != null && !piece.getImgPath().isEmpty()) {

                ImageIcon originalIcon = new ImageIcon(piece.getImgPath());
                Image originalImage = originalIcon.getImage();

                button.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        int newHeight = button.getHeight();
                        int newWidth = button.getWidth();

                        if (boardPanel.getWidth() != boardSize.width || boardPanel.getHeight() != boardSize.height) {
                            double widthScale = (double) newWidth / originalImage.getWidth(null);
                            double heightScale = (double) newHeight / originalImage.getHeight(null);
                            double scale = Math.min(widthScale, heightScale);

                            int scaledWidth = (int) (originalImage.getWidth(null) * scale);
                            int scaledHeight = (int) (originalImage.getHeight(null) * scale);
                            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight,
                                    Image.SCALE_SMOOTH);

                            ImageIcon scaledIcon = new ImageIcon(scaledImage);
                            button.setIcon(scaledIcon);
                        } else {
                            button.setIcon(originalIcon);
                        }
                    }
                });
            }
            boardPanel.add(button);
        }
    }

    // Chan Kar Kin
    public void handleGameOverChoice(int choice) {
        if (choice == JOptionPane.YES_OPTION) {
            gameControl.restartGame();
        } else {
            System.exit(0);
        }
    }

    // Ng Yun Shi, Ang Jin Nan,Tai Qi Tong
    // Create a menuBar at the top left corner, user can get save and load buttons
    // from there
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("MENU");
        fileMenu.setOpaque(true);
        fileMenu.setBackground(Color.BLACK);
        fileMenu.setForeground(Color.WHITE);
        turnLabel = new JLabel("Turn: " + gameControl.getCurrentColor() + " player");
        turnCount = new JLabel("Remaining turns to exchange Time & Plus pieces: " + (4 - gameControl.getTurnCount()));
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControl.saveGame();
            }
        });

        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameControl.loadGame();
            }
        });
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(turnCount);
        centerPanel.add(turnLabel);

        menuBar.add(centerPanel);
        menuBar.add(rightPanel);
        setJMenuBar(menuBar);
    }

    // Chan Kar Kin
    public void updateTurnLabel() {
        turnLabel.setText("Turn: " + gameControl.getCurrentColor() + " player"); // Update the text of the turn label
    }

    // Tai Qi Tong
    // Display message of round left to exchange time pieces
    public void updateTurnCount() {
        turnCount.setText("Remaining turns to exchange Time & Plus pieces: " + (4 - gameControl.getTurnCount()));
    }

    // Chan Kar Kin
    // get button on board using row & col
    public JButton getButtonAt(int col, int row) {
        int index = row * board.getWidth() + col;
        return (JButton) ((JPanel) getContentPane().getComponent(0)).getComponent(index);
    }
}