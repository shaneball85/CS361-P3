package tm;

public class Main {
    public static void main(String[] args) {
        FileParser test = new FileParser("/Users/shaneball/Desktop/CS361/Projects/Project 3/test/file5.txt");
        ParseObject textParse = test.parseFile();
        System.out.println(textParse);
        TouringMachine file0Machine = new TouringMachine(textParse);
        System.out.println(file0Machine);
        while (file0Machine.getStatePointer() < (file0Machine.getNumberOfStates() - 1)) {
            file0Machine.takeTransition();
        }
        System.out.println(file0Machine);
        System.out.println("output length: " + file0Machine.getTape().tapeLength());
        System.out.println("sum of symbols: " + file0Machine.getTape().tapeSum());
    }
}