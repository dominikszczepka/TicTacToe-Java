public class AI {
    private String sign;
    private Board _board;

    public AI(Board board) {
        _board = board;
        sign = _board.getIsFirstPlayer() ? "X" : "O";
    }

    public void doBestMove() {
        int[] bestMove = getBestMove();
        _board.setPos(bestMove[0], bestMove[1], sign);
    }

    private int minimax(int depth, boolean maxPlayer) {
        String[][] temp_board = _board.getBoardSigns();
        String otherSign = sign.equals("O") ? "X" : "O";
        if (!_board.isRunning()) {
            if (_board.getWinner().equals(sign))
                return 1;
            if (_board.getWinner().equals(otherSign))
                return -1;
            else
                return 0;
        }

        if (maxPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < _board.length; i++) {
                for (int j = 0; j < _board.length; j++) {
                    if (temp_board[i][j].equals(" ")) {
                        temp_board[i][j] = sign;
                        int score = minimax(depth + 1, false);
                        temp_board[i][j] = " ";
                        bestScore = Integer.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < _board.length; i++) {
                for (int j = 0; j < _board.length; j++) {
                    if (temp_board[i][j].equals(" ")) {
                        temp_board[i][j] = otherSign;
                        int score = minimax(depth + 1, true);
                        temp_board[i][j] = " ";
                        bestScore = Integer.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private int[] getBestMove() {
        String[][] temp_board = _board.getBoardSigns();
        int bestX = 0, bestY = 0;
        int bestScore = Integer.MIN_VALUE;
        for (int x = 0; x < _board.length; x++) {
            for (int y = 0; y < _board.length; y++) {
                if (temp_board[x][y] == " ") {
                    temp_board[x][y] = sign;
                    int score = minimax(0, false);
                    temp_board[x][y] = " ";
                    if (score > bestScore) {
                        bestScore = score;
                        bestX = x;
                        bestY = y;
                    }
                }
            }
        }
        return new int[]{ bestX, bestY };
    }
}
