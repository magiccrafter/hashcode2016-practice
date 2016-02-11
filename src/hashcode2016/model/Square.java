package hashcode2016.model;

/**
 * Created by nasko on 10/02/2016.
 */
public class Square implements Comparable<Square> {

    public final int sideLength;
    public final Cell leftCornerCell;
    public final int emptyCellNum;
    public final boolean isGoodCommand;
    public final String squareUID;

    public Square(Cell leftCornerCell, int sideLength, int emptyCellNum, boolean isGoodCommand) {
        this.sideLength = sideLength;
        this.leftCornerCell = leftCornerCell;
        this.emptyCellNum = emptyCellNum;
        this.squareUID = sideLength + "-" + leftCornerCell.uid;
        this.isGoodCommand = isGoodCommand;
    }

    @Override
    public String toString() {
        return "hashtag2016.model.Square{" +
                "sideLength=" + sideLength +
                ", leftCornerCell=" + leftCornerCell +
                ", emptyCellNum=" + emptyCellNum +
                ", isGoodCommand=" + isGoodCommand +
                ", squareUID='" + squareUID + '\'' +
                '}';
    }

    @Override
    public int compareTo(Square o) {
        return this.emptyCellNum - o.emptyCellNum;
    }
}
