package tm;

public class Main {
    public static void main(String[] args) {
        FileParser test = new FileParser("/Users/shaneball/Desktop/CS361/Projects/Project 3/test/file0.txt");
        ParseObject textParse = test.parseFile();
        System.out.println(textParse);
        TouringMachine file0Machine = new TouringMachine(textParse);
        System.out.println(file0Machine);
    }
}