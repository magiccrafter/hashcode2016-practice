package hashcode2016.processor;

import hashcode2016.model.Board;
import hashcode2016.model.Cell;
import hashcode2016.model.Square;
import hashcode2016.Utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nasko on 09/02/2016.
 */
public class SquareProcessor {

    public final int smallerSqSideSize;
    public final Board board;
    public final Map<String, Square> squareMap;


    public SquareProcessor(Board board) {
        this.board = board;
        this.squareMap = new TreeMap<>();
        this.smallerSqSideSize = (board.rowNum > board.colNum) ? board.colNum : board.rowNum;
    }


    public void process() {
        for (int sqSideSize = getClosestLowerOddNumber(smallerSqSideSize); sqSideSize >= 3; sqSideSize = sqSideSize - 2) {
            for (int row = 0; row <= board.rowNum - sqSideSize; row++) {
                for (int col = 0; col <= board.colNum - sqSideSize; col++) {
                    int sqEmptyCellNum = getSqEmptyCellNum(row, col, sqSideSize);
                    boolean isGoodCommand = (sqEmptyCellNum < sqSideSize);
                    Square sq = new Square(new Cell(row, col), sqSideSize, sqEmptyCellNum, isGoodCommand);
                    squareMap.put(sq.squareUID, sq);
                }
            }

            if (!squareMap.isEmpty()) {
                Square sq = Utils.sortByValue(squareMap).values().stream().findFirst().get();
                if (sq.isGoodCommand) {
                    System.out.println(sq);
                    removeSquareFromBoard(sq);
                    squareMap.clear();
                    process();
                    break;
                }
            }
        }

//        sortByValue(squareMap).values().stream().forEach(square -> {
//            System.out.println(square);
//        });
    }

    private int getClosestLowerOddNumber(int number) {
        return (number % 2 == 0) ? (number - 1) : number;
    }

    private void removeSquareFromBoard(Square sq) {
        for (int row = sq.leftCornerCell.row; row < sq.leftCornerCell.row + sq.sideLength; row++) {
            for (int col = sq.leftCornerCell.col; col < sq.leftCornerCell.col + sq.sideLength; col++) {
                board.cells[row][col] = '.';
            }
        }
    }

    private int getSqEmptyCellNum(int startRow, int startCol, int sqSideSize) {
        int emptyCellNum = 0;
        for (int row = startRow; row < startRow + sqSideSize; row++) {
            for (int col = startCol; col < startCol + sqSideSize; col++) {
                if (board.cells[row][col] == '.') {
                    emptyCellNum++;
                }
            }
        }
        return emptyCellNum;
    }
}
