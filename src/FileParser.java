import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.stream.Stream;

public class FileParser {
    String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    private int countLines() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return (int) stream.filter(line -> line.contains(",")).count();
        }
    }

    public ParseObject parseFile() {
        int numberOfLines;
        try {
            numberOfLines = countLines();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        ParseObject parseObject = new ParseObject(numberOfLines);

        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            parseObject.setNumberOfStates(Integer.parseInt(scanner.nextLine()));
            parseObject.setSigma(Integer.parseInt(scanner.nextLine()));

            for (int i = 0; i < numberOfLines; i++) {
                String line = scanner.nextLine();
                String[] segments = line.split(",");
                int nextState = Integer.parseInt(segments[0]);
                int writeSymbol = Integer.parseInt(segments[1]);
                char move = segments[2].charAt(0);
                Transition tempTransition = new Transition(nextState, writeSymbol, move);
                parseObject.addTransition(tempTransition);
            }

            if (scanner.hasNextLine()) {
                parseObject.setInputString(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parseObject;
    }
}
