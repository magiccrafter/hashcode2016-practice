package hashcode2016.model;

/**
 * Created by nasko on 09/02/2016.
 */
public class Cell {

    public final int row;
    public final int col;
    public final String uid;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.uid = row + "-" + col;
    }

    @Override
    public String toString() {
        return "hashtag2016.model.Cell{" +
                "row=" + row +
                ", col=" + col +
                ", uid='" + uid + '\'' +
                '}';
    }
}
