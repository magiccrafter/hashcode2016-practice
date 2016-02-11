package hashcode2016.model;

/**
 * Created by nasko on 09/02/2016.
 */
public class Line implements Comparable<Line> {

    public final Cell startCell;
    public final LineOrientation orientation;
    public final int length;
    public final String uid;


    public Line(Cell startCell, LineOrientation orientation, int length) {
        this.startCell = startCell;
        this.orientation = orientation;
        this.length = length;
        this.uid = length + "-" + orientation + "-" + startCell.uid;
    }

    @Override
    public String toString() {
        return "hashtag2016.model.Line{" +
                "startCell=" + startCell +
                ", orientation=" + orientation +
                ", length=" + length +
                ", uid=" + uid +
                '}';
    }

    @Override
    public int compareTo(Line o) {
        return o.length - this.length;
    }
}
