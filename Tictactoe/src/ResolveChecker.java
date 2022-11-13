import java.util.ArrayList;
import java.util.List;

public class ResolveChecker {
    public boolean isRunning(Board board) {
        String[][] boardSigns = board.getBoardSigns();
        List<String> results = new ArrayList<>();
        boolean tmpIsSpace = false;
        String tmpString;
        board.resetDraw();

        for (int i = 0; i < board.length; i++) {
            tmpString = "";
            for (int j = 0; j < board.length; j++) {
                tmpString += boardSigns[i][j];
            }
            results.add(tmpString);
        }

        for (int i = 0; i < board.length; i++) {
            tmpString = "";
            for (int j = 0; j < board.length; j++) {
                tmpString += (boardSigns[j][i]);
            }
            results.add(tmpString);
        }

        tmpString = "";
        for (int i = 0; i < board.length; i++)
            tmpString += (boardSigns[i][i]);
        results.add(tmpString);

        tmpString = "";
        for (int i = 0; i < board.length; i++)
            tmpString += (boardSigns[i][board.length - 1 - i]);
        results.add(tmpString);

        // game ended
        if (results.contains("XXX") || results.contains("OOO")) {
            var val = results.contains("XXX") ? "X" : "O";
            board.setWinner(val);
            return false;
        }
        // check if there is any space left
        for (String result : results) {
            if (result.contains(" ")) {
                tmpIsSpace = true;
                break;
            }
        }
        // draw
        if (!tmpIsSpace) {
            board.setDraw();
            return false;
        }
        return true;
    }
}
