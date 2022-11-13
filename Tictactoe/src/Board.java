import java.util.Scanner;

public class Board {

    private final BoardDisplay boardDisplay = new BoardDisplay();
    private AI ai;
    private final MoveMaker moveMaker = new MoveMaker(this);
    private final ResolveChecker resolveChecker = new ResolveChecker();
    private final Scanner scanner = new Scanner(System.in);
    private boolean isSinglePlayer;
    private boolean firstPlayer;
    private boolean isDraw;
    private final String[][] boardSigns;
    private String whoWon;
    final int length;


    public Board() {
        length = 3;
        boardSigns = new String[length][length];
        boardReset();
    }
    public void setPos(int x, int y,String sign){
        boardSigns[x][y]=sign;
    }
    public void resetDraw(){
        whoWon = "";
        isDraw=false;
    }
    public void setDraw(){
        whoWon="Draw";
        isDraw = true;
    }
    public void setWinner(String winner){
        whoWon=winner;
    }
    public String getWinner(){
        return whoWon;
    }
    public String[][] getBoardSigns(){
        return boardSigns;
    }
    public boolean getIsFirstPlayer(){
        return firstPlayer;
    }
    public void setIsFirstPlayer(boolean value){
        firstPlayer=value;
    }
    public boolean getIsSinglePlayer(){
        return isSinglePlayer;
    }
    public void makeMove() {
        moveMaker.makeMove();
    }
    public void doAiMove(){
        if(ai==null) ai=new AI(this);
        ai.doBestMove();
    }
    public boolean isRunning() {
        return resolveChecker.isRunning(this);
    }
    public void finishGame() {
        drawBoard();
        if (isDraw) {
            System.out.println("It's a draw!");
        }
        // if next turn should be for player1, but game ended, player 2 won
        else
            System.out.println((whoWon.equals("O") ? "Player1" : "Player2") + " won the game!");

        if (!willPlayAgain()) {
            System.out.println("Thank you for playing!");
            scanner.close();
        }
    }
    private void boardReset() {
        clearBoard();
        getSettings();
        isDraw = false;
    }
    private void clearBoard() {

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                boardSigns[i][j] = " ";
        }
    }
    private boolean isPositionValid(String pos) {

        String letters = "abcdefghijklmnoprstuvwxyz";
        try {
            int y = Integer.parseInt(pos.substring(2)) - 1;
            int x = letters.indexOf(Character.toString(pos.charAt(0)).toLowerCase());
            if (boardSigns[x][y].equals(" "))
                return true;
            else {
                System.out.println("Position already taken");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Incorrect position, try again exception");
            return false;
        }
    }
    public void drawBoard() {
        boardDisplay.drawBoard(this.boardSigns);
    }
    String getPosition() {
        String position;
        do {
            position = scanner.nextLine();
        } while (!isPositionValid(position));
        return position;
    }

    private boolean willPlayAgain() {
        String answer;
        do {
            System.out.println("Do you want to play again? Type yes or no");
            answer = scanner.nextLine();
        } while (!answer.equals("yes") && !answer.equals("no"));
        if (answer.equals("yes")) {
            boardReset();
            return true;
        } else
            return false;
    }

    private void getSettings() {
        String answer;
        do {
            System.out.println("Type for 1 single player mode or 2 for multiplayer mode");
            answer = scanner.nextLine();
        } while (!answer.equals("1") && !answer.equals("2"));
        isSinglePlayer = answer.equals("1");
        if (isSinglePlayer) {
            do {
                System.out.println("Type O if you want to start first and X if you want to start second");
                answer = scanner.nextLine();
            } while (!answer.equals("X") && !answer.equals("O"));
            firstPlayer = answer.equals("O");
            ai=new AI(this);
        } else
            firstPlayer = true;
    }
}
