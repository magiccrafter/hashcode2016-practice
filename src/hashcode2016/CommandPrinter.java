package hashcode2016;

import hashcode2016.command.Command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author vasilevn
 */
public class CommandPrinter {

    public void print(List<Command> commands, String filenameAndPath) {
        Path path = Paths.get(filenameAndPath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.CREATE)) {
            writer.write(Integer.toString(commands.size()));
            writer.write("\n");
            for (Command command : commands) {
                writer.write(command.getCommand());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
