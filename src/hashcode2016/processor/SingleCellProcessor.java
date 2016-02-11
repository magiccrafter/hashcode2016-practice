package hashcode2016.processor;

import hashcode2016.model.Board;
import hashcode2016.model.SingleCell;

import java.util.*;

/**
 * Created by nasko on 11/02/2016.
 */
public class SingleCellProcessor {

    public Board board;
    public final List<SingleCell> singleCellList;

    public SingleCellProcessor(Board board) {
        this.board = board;
        this.singleCellList = new ArrayList<>();
    }

    public void process() {
        for (int row = 0; row < board.rowNum; row++) {
            for (int col = 0; col < board.colNum; col++) {
                if (board.cells[row][col] == '#') {
                    SingleCell singleCell = new SingleCell(row, col);
                    singleCellList.add(singleCell);
                    System.out.println(singleCell);

                    // remove from board
                    board.cells[row][col] = '.';
                }
            }
        }
    }
}
