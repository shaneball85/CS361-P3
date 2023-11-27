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

    private int lineCount() {
        long lineCountLong = 0;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            lineCountLong = stream.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lineCountInt = (int) lineCountLong - 2;
        return lineCountInt;
    }

    public ParseObject parseFile() {
        int lineCount = lineCount();
        ParseObject parseObject = new ParseObject(lineCount);

        File file = new File(filePath);
        int onLine = 0;
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (onLine == 0) {
                    parseObject.setNumberOfStates(Integer.parseInt(line));
                }
                if (onLine == 1) {
                    parseObject.setSigma(Integer.parseInt(line));
                }
                if (onLine > 1) {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(",");

                    int commaCount = 0;
                    int nextState = 0;
                    int writeSymbol = 0;
                    char move = '0';

                    while (lineScanner.hasNext()) {
                        String segment = lineScanner.next();

                        if (commaCount == 0) {
                            nextState = Integer.parseInt(segment);
                        }
                        if (commaCount == 1) {
                            writeSymbol = Integer.parseInt(segment);
                        }
                        if (commaCount == 2) {
                            move = segment.charAt(0);
                        }
                        commaCount++;
                    }
                    Transition tempTransition = new Transition(nextState, writeSymbol, move);
                    parseObject.addTransition(tempTransition);
                }
                onLine++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parseObject;
    }

}
