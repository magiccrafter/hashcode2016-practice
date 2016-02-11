package hashcode2016.model;

/**
 * Created by nasko on 10/02/2016.
 */
public class Square implements Comparable<Square> {

    public final int sideLength;
    public final Cell topLeftCell;
    public final Cell bottomRightCell;
    public final Cell topRightCell;
    public final Cell bottomLeftCell;
    public final SquareInfo squareInfo;
    public final String squareUID;

    public Square(Cell topLeftCell, int sideLength, SquareInfo squareInfo) {
        this.sideLength = sideLength;
        this.topLeftCell = topLeftCell;
        this.topRightCell = new Cell(topLeftCell.row, topLeftCell.col + (sideLength - 1));
        this.bottomLeftCell = new Cell(topLeftCell.row + (sideLength - 1), topLeftCell.col);
        this.bottomRightCell = new Cell(topLeftCell.row + (sideLength - 1), topLeftCell.col + (sideLength - 1));
        this.squareUID = sideLength + "-" + topLeftCell.uid;
        this.squareInfo = squareInfo;
    }

    @Override
    public String toString() {
        return "hashtag2016.model.Square{" +
                "sideLength=" + sideLength +
                ", leftCornerCell=" + topLeftCell +
                ", squareInfo=" + squareInfo +
                ", squareUID='" + squareUID + '\'' +
                '}';
    }

    @Override
    public int compareTo(Square o) {
        return this.squareInfo.emptyCellNum - o.squareInfo.emptyCellNum;
    }
}
