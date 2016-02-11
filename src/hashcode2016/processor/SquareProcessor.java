package hashcode2016.processor;

import hashcode2016.Utils;
import hashcode2016.model.*;

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
        _process(getClosestLowerOddNumber(smallerSqSideSize));
    }

    public void _process(int sqSideSize) {
        for (int row = 0; row <= board.rowNum - sqSideSize; row++) {
            for (int col = 0; col <= board.colNum - sqSideSize; col++) {
                SquareInfo sqInfo = getSqEmptyCellNum(row, col, sqSideSize);
                boolean isGoodCommand = (sqInfo.emptyCellNum < sqSideSize);
                if (isGoodCommand) {
                    Square sq = new Square(new Cell(row, col), sqSideSize, sqInfo);
                    squareMap.put(sq.squareUID, sq);
                }
            }
        }

        if (!squareMap.isEmpty()) {
            boolean intersectingSq = false;
            Square lastSq = null;
            for (Square sq : Utils.sortByValue(squareMap).values()) {
                boolean squaresIntersect = (lastSq != null && squaresIntersect(lastSq, sq));
                if (!squaresIntersect) {
                    System.out.println(sq);
                    removeSquareFromBoard(sq);
                } else {
                    intersectingSq = true;
                    break;
                }
                lastSq = sq;
            }
//                Square sq = Utils.sortByValue(squareMap).values().stream().findFirst().get();
//                System.out.println(sq);
//                removeSquareFromBoard(sq);
            squareMap.clear();
            if (intersectingSq) {
                _process(sqSideSize);
            }
        }

        if (sqSideSize >= 3) {
            _process(sqSideSize - 2);
        }

//        sortByValue(squareMap).values().stream().forEach(square -> {
//            System.out.println(square);
//        });
    }

    private int getClosestLowerOddNumber(int number) {
        return (number % 2 == 0) ? (number - 1) : number;
    }

    private void removeSquareFromBoard(Square sq) {
        for (int row = sq.topLeftCell.row; row < sq.topLeftCell.row + sq.sideLength; row++) {
            for (int col = sq.topLeftCell.col; col < sq.topLeftCell.col + sq.sideLength; col++) {
                board.cells[row][col] = '.';
            }
        }
    }

    private SquareInfo getSqEmptyCellNum(int startRow, int startCol, int sqSideSize) {
        int emptyCellNum = 0;
        for (int row = startRow; row < startRow + sqSideSize; row++) {
            for (int col = startCol; col < startCol + sqSideSize; col++) {
                if (board.cells[row][col] == '.') {
                    emptyCellNum++;
                }
            }
        }

        return new SquareInfo(emptyCellNum);
    }

    private static boolean squaresIntersect(Square sq1, Square sq2) {
        boolean overlaps = true;

        // if one rectangle is on left side of other (include when their corner lines are on top of each other)
        if (sq1.topLeftCell.row > sq2.bottomRightCell.row || sq2.topLeftCell.row > sq1.bottomRightCell.row)
            overlaps = false;

        // if one rectangle is above other
        if (sq1.topLeftCell.col < sq2.bottomRightCell.col || sq2.topLeftCell.col < sq1.bottomRightCell.col)
            overlaps = false;

        if (lineIntersect(sq1.topLeftCell, sq1.topRightCell, sq2.bottomLeftCell, sq2.bottomRightCell)
                || lineIntersect(sq1.topLeftCell, sq1.bottomLeftCell, sq2.topRightCell, sq2.bottomRightCell)
                || lineIntersect(sq1.bottomLeftCell, sq1.bottomRightCell, sq2.topLeftCell, sq2.topRightCell)
                || lineIntersect(sq1.topRightCell, sq1.bottomRightCell, sq2.topLeftCell, sq2.bottomLeftCell)
            ) {
            overlaps = true;
        }

        return overlaps;
    }

    /**
     * Given three colinear points p, q, r, the function checks if point q lies on line segment 'pr'
     */
    public static boolean segmentIntersect(Cell p, Cell q, Cell r) {
        if (q.row <= Math.max(p.row, r.row) && q.row >= Math.min(p.row, r.row) &&
                q.col <= Math.max(p.col, r.col) && q.col >= Math.min(p.col, r.col))
            return true;

        return false;
    }

    public static boolean lineIntersect(Cell p1, Cell q1, Cell p2, Cell q2) {
        if (segmentIntersect(p1, p2, q1)) {
            return true;
        }

        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (segmentIntersect(p1, q2, q1)) {
            return true;
        }

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (segmentIntersect(p2, p1, q2)) {
            return true;
        }

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (segmentIntersect(p2, q1, q2)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Square sq1 = new Square(new Cell(0,0), 3, null);
        Square sq2 = new Square(new Cell(2,2), 3, null);

        System.out.println(squaresIntersect(sq1, sq2)); // true

        Square sq3 = new Square(new Cell(0,0), 2, null);
        Square sq4 = new Square(new Cell(2,2), 2, null);

        System.out.println(squaresIntersect(sq3, sq4)); // false

        Square sq5 = new Square(new Cell(2,0), 2, null);
        Square sq6 = new Square(new Cell(0,1), 2, null);

        System.out.println(squaresIntersect(sq5, sq6)); // false

        Square sq7 = new Square(new Cell(3,0), 2, null);
        Square sq8 = new Square(new Cell(2,1), 2, null);

        System.out.println(squaresIntersect(sq7, sq8)); // true

        Square sq9 = new Square(new Cell(3,1), 2, null);
        Square sq10 = new Square(new Cell(3,0), 2, null);

        System.out.println(squaresIntersect(sq9, sq10)); // true

        Square sq11 = new Square(new Cell(2,2), 2, null);
        Square sq12 = new Square(new Cell(1,2), 2, null);

        System.out.println(squaresIntersect(sq11, sq12)); // true

        Square sq13 = new Square(new Cell(2,2), 2, null);
        Square sq14 = new Square(new Cell(3,2), 2, null);

        System.out.println(squaresIntersect(sq13, sq14)); // true
    }
}
