public class App {

    public static void main(String[] args) {
        Board board = new Board();
        while(board.isRunning())
        {
            board.drawBoard();
            board.makeMove();
            if (!board.isRunning()) board.finishGame();
        }
    }
}