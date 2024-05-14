// game logic code

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game implements GameController {
    private BoardView boardView;
    private Board board;
    private Piece currentPiece;
    private int fromX, fromY;
    private boolean firstClick = false;
    private String currentColor = "Yellow";
    private int turnCount = 0;

    private Player bluePlayer = new Player("Blue");
    private Player yellowPlayer = new Player("Yellow");
    private Map<PieceType, PieceFactory> pieceFactories = new HashMap<>();

    // Chan Kar Kin
    // initilialize Game with boardView and board, set up mapping of piece with pieceType
    public Game(BoardView boardView, Board board) {
        this.boardView = boardView;
        this.board = board;
        pieceFactories.put(PieceType.PLUS, new PlusFactory());
        pieceFactories.put(PieceType.POINT, new PointFactory());
        pieceFactories.put(PieceType.SUN, new SunFactory());
        pieceFactories.put(PieceType.HOURGLASS, new HourglassFactory());
        pieceFactories.put(PieceType.TIME, new TimeFactory());
    }

    // Tai Qi Tong
    // set the current player color
    @Override
    public void setCurrentColor(String currentColor) {
        this.currentColor = currentColor;
    }

    // Tai Qi Tong
    // get the current player color
    @Override
    public String getCurrentColor() {
        return currentColor;
    }

    // Tai Qi Tong
    // set the current player turn
    @Override
    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    // Tai Qi Tong
    // get the current player turn
    @Override
    public int getTurnCount() {
        return turnCount;
    }

    // Chan Kar Kin
    // factory method to create new Piece based on provided player and pieceType
    @Override
    public Piece createPiece(Player player, PieceType pieceType) {
        PieceFactory factory = pieceFactories.get(pieceType);

        if (factory != null) {
            return factory.createPiece(player);
        } else {
            throw new IllegalArgumentException("Invalid pieceType: " + pieceType);
        }
    }

    // Ang Jin Nan
    // define each piece type for each player and add them to the board
    @Override
    public void initializePiece() {
        PieceType[] bluePieces = { PieceType.PLUS, PieceType.HOURGLASS, PieceType.TIME, PieceType.SUN, PieceType.TIME,
                PieceType.HOURGLASS, PieceType.PLUS };
        PieceType[] yellowPieces = { PieceType.PLUS, PieceType.HOURGLASS, PieceType.TIME, PieceType.SUN, PieceType.TIME,
                PieceType.HOURGLASS, PieceType.PLUS };

        for (int col = 0; col < bluePieces.length; col++) {
            Piece bluePiece = createPiece(bluePlayer, bluePieces[col]);
            board.addPiece(bluePiece, 0, col);
        }

        for (int col = 0; col < 7; col++) {
            board.addPiece(createPiece(bluePlayer, PieceType.POINT), 1, col);
        }

        for (int col = 0; col < yellowPieces.length; col++) {
            Piece yellowPiece = createPiece(yellowPlayer, yellowPieces[col]);
            board.addPiece(yellowPiece, 5, col);
        }

        for (int col = 0; col < 7; col++) {
            board.addPiece(createPiece(yellowPlayer, PieceType.POINT), 4, col);
        }
    }

    // Tai Qi Tong, Chan Kar Kin
    // check the click of user on square of board
    @Override
    public void handleButtonClick(Square square) {
        if (!firstClick) {
            if (square.getPiece() != null && getCurrentColor().equals(square.getPiece().getPlayer().getColor())) {
                fromX = square.getCol();
                fromY = square.getRow();
                currentPiece = square.getPiece();
                highlightLegalMove(currentPiece);
                firstClick = true;
            }
            else if (square.getPiece() == null) {
                JOptionPane.showMessageDialog(boardView, "First piece cannot be null", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(boardView,
                        getCurrentColor() + " player's turn. Please select a correct piece", "Error",
                        JOptionPane.ERROR_MESSAGE);
                firstClick = false;
            }
        }

        else {
            int toX = square.getCol();
            int toY = square.getRow();
            if (currentPiece != null && square.getPiece() != null
                    && currentPiece.getPlayer().getColor().equals(square.getPiece().getPlayer().getColor())) {
                fromX = square.getCol();
                fromY = square.getRow();
                currentPiece = square.getPiece();
                highlightLegalMove(currentPiece);
            } else {
                if (currentPiece != null) {
                    if (currentPiece.isValid(board, fromX, fromY, toX, toY)) {
                        currentPiece.move(board, fromX, fromY, toX, toY);
                    }
                    else {
                        firstClick = false;
                        JOptionPane.showMessageDialog(boardView, "Please move to a correct square", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        currentPiece.setMoved(false);
                    }
                }
                if (currentPiece != null && currentPiece.getMoved()) {
                    reverse();
                    setTurnCount(getTurnCount() + 1);
                    if (turnCount > 0 && turnCount % 4 == 0) {
                        transformPieces();
                        setTurnCount(0);
                    }

                    if (getCurrentColor().equals("Yellow")) {
                        setCurrentColor("Blue");
                    } else {
                        setCurrentColor("Yellow");
                    }
                    boardView.updateTurnLabel();
                    boardView.updateTurnCount();
                }

                capturedSun();
                refresh();
                firstClick = false;
            }
        }
    }

    // Ng Yun Shi, Chee Weng Kee
    // check whether the sun in the board has been captured by the opponent .
    // If a player captures the opponent's sun piece, it prompts a dialog to
    // announce the winner and gives the option to start a new game.
    @Override
    public void capturedSun() {
        String winnerMessage;

        if (isSunCaptured(yellowPlayer)) {
            winnerMessage = "Player blue wins!";
        } else if (isSunCaptured(bluePlayer)) {
            winnerMessage = "Player yellow wins!";
        } else {
            return;
        }

        int choice = JOptionPane.showConfirmDialog(boardView, winnerMessage + " Start new game?", "Game Over",
                JOptionPane.YES_NO_OPTION);
        boardView.handleGameOverChoice(choice);
    }

    // Ng Yun Shi , Chee Weng Kee
    // After 2 turns, all Time pieces will turn into Plus pieces, and all Plus
    // pieces will turn into Time pieces
    @Override
    public void transformPieces() {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                Square square = board.getSquare(row, col);
                Piece piece = square.getPiece();
                if (piece != null) {
                    // chg time plus
                    if (piece.getPieceType() == PieceType.TIME) {
                        Time timePiece = (Time) piece;
                        Plus newPlusPiece = new Plus(timePiece.getPlayer());
                        board.addPiece(newPlusPiece, row, col);
                    } else if (piece.getPieceType() == PieceType.PLUS) {
                        Plus plusPiece = (Plus) piece;
                        Time newTimePiece = new Time(plusPiece.getPlayer());
                        board.addPiece(newTimePiece, row, col);
                    }
                }
            }
        }
        refresh();
    }

    // Chan Kar Kin
    // set background of square to green for legal moves of a piece
    @Override
    public void highlightLegalMove(Piece piece) {
        boolean validMoveFound = false;

        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                JButton button = boardView.getButtonAt(col, row);
                if (piece.isValid(board, fromX, fromY, col, row)) {
                    button.setBackground(Color.GREEN);
                    validMoveFound = true;
                } else {
                    button.setBackground(Color.WHITE);
                }
            }
        }
        if (!validMoveFound) {
            JOptionPane.showMessageDialog(boardView, "No valid moves for this piece", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Chan Kar Kin
    // reverse the board and inverts images for point pieces
    @Override
    public void reverse() {
        Collections.reverse(board.getSquare());
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Square square = board.getSquare(i, j);
                if (square != null) {
                    square.setRow(i);
                    square.setCol(j);
                    if (square.getPiece() != null && square.getPiece().getPieceType() == PieceType.POINT) {
                        Point pointPiece = (Point) square.getPiece();
                        pointPiece.setImgPath(pointPiece.invertImage(pointPiece));
                    }
                }
            }
        }
        refresh();
    }

    // Ng Yun Shi, Chee Weng Kee
    // checks if the "Sun" piece has been captured on the game board, returning true
    // if it's captured
    @Override
    public boolean isSunCaptured(Player player) {
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                Square square = board.getSquare(row, col);
                Piece piece = square.getPiece();
                if (piece != null && piece.getPieceType() == PieceType.SUN && piece.getPlayer() == player) {
                    return false;
                }
            }
        }
        return true;
    }

    // Ng Yun Shi
    // reset game state, clear board, initialising pieces, reset turn count and start new game
    @Override
    public void restartGame() {
        SwingUtilities.invokeLater(() -> {
            board.clearBoard();
            initializePiece();
            setTurnCount(0);
            setCurrentColor("Yellow");
            boardView.updateTurnLabel();
            boardView.updateTurnCount();
            refresh();
        });
    }

    // Chan Kar Kin
    // refresh board, update display
    @Override
    public void refresh() {
        int currentWidth = boardView.getBoardPanel().getWidth();
        int currentHeight = boardView.getBoardPanel().getHeight();

        boardView.getBoardPanel().removeAll();
        boardView.InitializeImageOnButton();
        boardView.getBoardPanel().setSize(currentWidth, currentHeight);
        boardView.getBoardPanel().revalidate();
        boardView.getBoardPanel().repaint();
    }

    // Ang Jin Nan, Chan Kar Kin
    // clear existing default board, and load the saved game from file
    @Override
    public void loadGame() {
        try {
            File saveDirectory = new File("SAVE");

            if (!saveDirectory.exists() || !saveDirectory.isDirectory()) {
                JOptionPane.showMessageDialog(boardView, "No saved games found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser(saveDirectory);
            int option = fileChooser.showOpenDialog(boardView);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                board.clearBoard();
                try (Scanner scanner = new Scanner(file)) {
                    scanner.nextLine();
                    setCurrentColor(scanner.nextLine().split(": ")[1].trim());
                    boardView.updateTurnLabel();
                    setTurnCount(4 - Integer.parseInt(scanner.nextLine().split(": ")[1].trim()));
                    boardView.updateTurnCount();

                    for (int row = 0; row < board.getHeight(); row++) {
                        String line = scanner.nextLine();
                        String[] pieces = line.split(" ");

                        for (int col = 0; col < board.getWidth(); col++) {
                            String pieceInfo = pieces[col];

                            if (!pieceInfo.equals("NULL")) {
                                String playerColor = pieceInfo.substring(0, 1);
                                String pieceType;
                                if (pieceInfo.contains("inv")) {
                                    pieceType = pieceInfo.substring(1, pieceInfo.indexOf("inv")).toUpperCase();
                                } else {
                                    pieceType = pieceInfo.substring(1).toUpperCase();
                                }

                                Player player;

                                if (playerColor.equals("Y")) {
                                    player = yellowPlayer;
                                } else {
                                    player = bluePlayer;
                                }
                                Piece loadedPiece = createPiece(player, PieceType.valueOf(pieceType));
                                board.addPiece(loadedPiece, row, col);
                                if (loadedPiece instanceof Point && getCurrentColor().equals("Blue")
                                        && pieceInfo.contains("inv")) {
                                    Point pointPiece = (Point) loadedPiece;
                                    pointPiece.setImgPath(pointPiece.invertImage(pointPiece));
                                }
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(boardView, "Game loaded!");
                    refresh();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(boardView, "Error loading game", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(boardView, "Error loading game", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Ng Yun Shi, Ang Jin Nan
    // Save current player turn, piece type, and player color
    @Override
    public void saveGame() {
        try {
            String filename;

            while (true) {
                filename = JOptionPane.showInputDialog(boardView, "Enter the file name:").toLowerCase();

                if (filename == null) {
                    return;
                }

                if (filename.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(boardView, "Please enter a valid file name", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                if (!filename.endsWith(".txt")) {
                    filename += ".txt";
                }

                File file = new File("SAVE", filename);
                if (file.exists()) {
                    int replaceOption = JOptionPane.showConfirmDialog(
                            boardView,
                            "A file with the same name already exists. Do you want to replace it?",
                            "File Exists",
                            JOptionPane.YES_NO_OPTION);

                    if (replaceOption == JOptionPane.YES_OPTION) {
                        break;
                    }
                } else {
                    break;
                }

            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("SAVE", filename)))) {
                writer.write("Game Save");
                writer.newLine();
                writer.write("Current turn: " + getCurrentColor());
                writer.newLine();
                writer.write(
                        "Round left to exchange time plus pieces: " + String.valueOf(4 - getTurnCount()));
                writer.newLine();

                for (int row = 0; row < board.getHeight(); row++) {
                    for (int col = 0; col < board.getWidth(); col++) {
                        Square square = board.getSquare(row, col);
                        Piece piece = square.getPiece();

                        if (piece != null) {
                            String player;

                            if (piece.getPlayer().getColor().equalsIgnoreCase("yellow")) {
                                player = "Y";
                            } else {
                                player = "B";
                            }
                            writer.write(player + piece.getPieceType().toString());

                            if (piece.getPieceType() == PieceType.POINT && piece.getImgPath().contains("inverted")) {
                                writer.write("inv");
                            }
                        } else {
                            writer.write("NULL");
                        }

                        if (col < board.getWidth() - 1) {
                            writer.write(" ");
                        }
                    }
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(boardView, "Game saved to: " + filename);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(boardView, "ERROR OCCURRED", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(boardView, "ERROR OCCURRED", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
