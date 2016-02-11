package hashcode2016;

import hashcode2016.command.Command;
import hashcode2016.model.Board;
import hashcode2016.processor.LineProcessor;
import hashcode2016.processor.SingleCellProcessor;
import hashcode2016.processor.SquareProcessor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author vasilevn
 */
public class App {

    public Board board;


    public static void main(String[] args) {
        App app = new App();
        CommandPrinter commandPrinter = new CommandPrinter();

        String[] fileNames = {"learn_and_teach", "logo", "right_angle"};

        for (String fileName: fileNames) {
            try (Scanner scanner = new Scanner(Paths.get("test/" + fileName + ".in"))) {
                String line;

                int row = -1;
                while (scanner.hasNext()){
                    line = scanner.nextLine();

                    if (row == -1) {
                        String[] sizeNums = line.trim().split(" ");
                        int rowNum = Integer.parseInt(sizeNums[0]);
                        int colNum = Integer.parseInt(sizeNums[1]);
                        app.board = new Board(rowNum, colNum);
                    } else {
                        for (int col = 0; col < app.board.colNum; col++) {
                            app.board.cells[row][col] = line.charAt(col);
                        }
                    }

                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Command> allCommands = new ArrayList<>();

            app.board.prettyPrint();

            // Processing / Computation
            SquareProcessor squareProcessor = new SquareProcessor(app.board);
            squareProcessor.process();
            allCommands.addAll(squareProcessor.squareCommands);
            allCommands.addAll(squareProcessor.eraseCellCommands);
            app.board.prettyPrint();

            LineProcessor lineProcessor = new LineProcessor(app.board);
            lineProcessor.process();
            allCommands.addAll(lineProcessor.lineCommands);
            app.board.prettyPrint();

            SingleCellProcessor singleCellProcessor = new SingleCellProcessor(app.board);
            singleCellProcessor.process();
            allCommands.addAll(singleCellProcessor.cellCommands);
            app.board.prettyPrint();

            System.out.println(allCommands.size());
//        allCommands.stream().forEach((Command command) -> {
//            System.out.println(command);
//        });

           commandPrinter.print(allCommands, "test/" + fileName + ".out");
        }
    }
}
