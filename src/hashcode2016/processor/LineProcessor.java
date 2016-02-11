package hashcode2016.processor;

import hashcode2016.*;
import hashcode2016.command.PaintLineCommand;
import hashcode2016.model.Board;
import hashcode2016.model.Cell;
import hashcode2016.model.Line;
import hashcode2016.model.LineOrientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by nasko on 11/02/2016.
 */
public class LineProcessor {

    public final Board board;
    public final Map<String, Line> lineMap;
    public final List<PaintLineCommand> lineCommands;


    public LineProcessor(Board board) {
        this.board = board;
        this.lineMap = new TreeMap<>();
        this.lineCommands = new ArrayList<>();
    }

    public void process() {
        // Horizontal lines check
        processLineType(LineOrientation.HORIZONTAL);
        processLineType(LineOrientation.VERTICAL);

        if (!lineMap.isEmpty()) {
            Line line = Utils.sortByValue(lineMap).values().stream().findFirst().get();
            //System.out.println(line);
            lineCommands.add(new PaintLineCommand(line));
            removeLineFromBoard(line);
            lineMap.clear();
            process();
        }


//        Utils.sortByValue(lineMap).values().stream().forEach(square -> {
//            System.out.println(square);
//        });
    }

    private void removeLineFromBoard(Line line) {
        boolean isHorizontalLine = LineOrientation.HORIZONTAL.equals(line.orientation);
        if (isHorizontalLine) {
            for (int col = line.startCell.col; col < line.startCell.col + line.length; col++) {
                board.cells[line.startCell.row][col] = '.';
            }
        } else {
            for (int row = line.startCell.row; row < line.startCell.row + line.length; row++) {
                board.cells[row][line.startCell.col] = '.';
            }
        }

    }


    private void processLineType(LineOrientation orientation) {

        boolean isHorizontalLine = LineOrientation.HORIZONTAL.equals(orientation);
        int iLength, jLength;
        if (isHorizontalLine) {
            iLength = board.rowNum;
            jLength = board.colNum;
        } else {
            iLength = board.colNum;
            jLength = board.rowNum;
        }

        for (int i = 0; i < iLength; i++) {
            Cell startLineCell = null;
            int lineLength = 0;
            for (int j = 0; j < jLength; j++) {
                int row = isHorizontalLine ? i : j;
                int col = isHorizontalLine ? j : i;

                if (board.cells[row][col] == '#') {
                    if (startLineCell == null) { // beginning of a new line
                        startLineCell = new Cell(row, col);
                    }

                    lineLength++;
                } else if (startLineCell != null) {
                    if (lineLength > 1) {
                        Line line = new Line(startLineCell, orientation, lineLength);
                        lineMap.put(line.uid, line);
                    }
                    startLineCell = null;
                    lineLength = 0;
                }
            }

            if (startLineCell != null && lineLength > 1) {
                Line line = new Line(startLineCell, orientation, lineLength);
                lineMap.put(line.uid, line);
            }
        }
    }
}
