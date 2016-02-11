package hashcode2016.command;

import hashcode2016.model.Square;

/**
 * @author vasilevn
 */
public class PaintSquareCommand implements Command {
    private int centerRow;
    private int centerCol;
    private int size;

    public PaintSquareCommand(Square square) {
        this.size = (square.sideLength - 1) / 2;
        this.centerRow = square.topLeftCell.row + size;
        this.centerCol = square.topLeftCell.col + size;
    }

    @Override
    public String getCommand() {
        return new StringBuilder()
                .append(CommandsEnum.PAINT_SQUARE).append(" ")
                .append(this.centerRow).append(" ")
                .append(this.centerCol).append(" ")
                .append(this.size).toString();
    }
}
