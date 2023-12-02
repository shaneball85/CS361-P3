package tm;

public class TMSimulator {

  String fileName = args[0];

        FileParser test = new FileParser(fileName);

        ParseObject textParse = test.parseFile();

        System.out.println(textParse);

        TouringMachine file0Machine = new TouringMachine(textParse);

        System.out.println(file0Machine);
  
}
