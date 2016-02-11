package hashcode2016.command;

import hashcode2016.model.Line;

/**
 * @author vasilevn
 */
public class PaintLineCommand implements Command {
    private final Line line;

    public PaintLineCommand(Line line) {
        this.line = line;
    }

    @Override
    public String getCommand() {
        return new StringBuilder()
                .append(CommandsEnum.PAINT_LINE).append(" ")
                .append(this.line.startCell.row).append(" ")
                .append(this.line.startCell.col).append(" ")
                .append(this.line.endCell.row).append(" ")
                .append(this.line.endCell.col).toString();
    }
}
