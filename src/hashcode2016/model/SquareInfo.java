package hashcode2016.model;

import java.util.List;

/**
 * @author vasilevn
 */
public class SquareInfo {

    public final int emptyCellNum;
    public final List<Cell> cellsToErase;
    public final boolean isGoodCommand;

    public SquareInfo(int emptyCellNum, List<Cell> cellsToErase, boolean isGoodCommand) {
        this.emptyCellNum = emptyCellNum;
        this.cellsToErase = cellsToErase;
        this.isGoodCommand = isGoodCommand;
    }

    @Override
    public String toString() {
        return "SquareInfo{" +
                "emptyCellNum=" + emptyCellNum +
                '}';
    }
}
