package hashcode2016.model;

/**
 * Created by nasko on 09/02/2016.
 */
public class Board {

    public final int rowNum;
    public final int colNum;
    public final char cells[][];

    public Board(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.cells = new char[rowNum][colNum];
    }

    public void prettyPrint() {
        System.out.println();
        System.out.println("-----------------------");
        System.out.println();

        for (int r = 0; r < rowNum; r++) {
            for (int c = 0; c < colNum; c++) {
                System.out.print(cells[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
