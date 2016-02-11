package hashcode2016.command;

import hashcode2016.model.Cell;

/**
 * @author vasilevn
 */
public class EraseCellCommand implements Command {
    private Cell cell;

    public EraseCellCommand(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String getCommand() {
        return new StringBuilder()
                .append(CommandsEnum.ERASE_CELL).append(" ")
                .append(this.cell.row).append(" ")
                .append(this.cell.col).toString();
    }
}
