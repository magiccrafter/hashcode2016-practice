package hashcode2016.command;

import hashcode2016.model.Cell;

/**
 * @author vasilevn
 */
public class PaintCellCommand implements Command {

    private final Cell cell;

    public PaintCellCommand(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String getCommand() {
        return new StringBuilder()
                .append(CommandsEnum.PAINT_SQUARE).append(" ")
                .append(this.cell.row).append(" ")
                .append(this.cell.col).append(" ")
                .append("0").toString();
    }
}
