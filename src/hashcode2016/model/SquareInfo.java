package hashcode2016.model;

/**
 * @author vasilevn
 */
public class SquareInfo {

    public final int emptyCellNum;

    public SquareInfo(int emptyCellNum) {
        this.emptyCellNum = emptyCellNum;
    }

    @Override
    public String toString() {
        return "SquareInfo{" +
                "emptyCellNum=" + emptyCellNum +
                '}';
    }
}
