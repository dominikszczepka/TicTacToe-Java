public class MoveMaker {
    private Board _board;
    public MoveMaker(Board board){
        _board=board;
    }
    public void makeMove() {
        if (_board.getIsSinglePlayer()) {
            if (!_board.getIsFirstPlayer()) {
                _board.doAiMove();
                _board.drawBoard();
            }

            if (_board.getIsFirstPlayer() || (!_board.getIsFirstPlayer() && _board.isRunning())) {
                System.out.println("Please make your next move");
                updatePositionOnBoard(_board.getPosition());
            }

            if (_board.getIsFirstPlayer() && _board.isRunning())
                _board.doAiMove();
        }

        else {
            System.out.println((_board.getIsFirstPlayer() ? "Player1" : "Player2") + " please make your next move");
            updatePositionOnBoard(_board.getPosition());
            _board.setIsFirstPlayer(!_board.getIsFirstPlayer());
        }
    }
    void updatePositionOnBoard(String pos) {

        String letters = "abcdefghijklmnoprstuvwxyz";
        int y = Integer.parseInt(pos.substring(2)) - 1;
        int x = letters.indexOf(Character.toString(pos.charAt(0)).toLowerCase());
        String sign = _board.getIsFirstPlayer() ? "O" : "X";
        _board.setPos(x,y,sign);
    }
}
