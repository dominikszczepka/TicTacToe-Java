public class BoardDisplay {
    public void drawBoard(String[][] boardSigns) {
        System.out.println("  1 2 3");
        System.out.println("A " + boardSigns[0][0] + "|" + boardSigns[0][1] + "|" + boardSigns[0][2]);
        System.out.println("  -----");
        System.out.println("B " + boardSigns[1][0] + "|" + boardSigns[1][1] + "|" + boardSigns[1][2]);
        System.out.println("  -----");
        System.out.println("C " + boardSigns[2][0] + "|" + boardSigns[2][1] + "|" + boardSigns[2][2]);
    }
}
