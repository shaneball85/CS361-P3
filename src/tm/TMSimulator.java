package tm;

/**
  * @author Shane Ball, Austin Andersen
  *
  * This is the driver class for the touring machine project and takes a single command line argument for a text file, which pulls
  * elements from the text file and uses them to create a touring machine, printing out the results
*/
public class TMSimulator {

  String fileName = args[0]; //pull first command line argument to get file path

  FileParser test = new FileParser(fileName); //Create file parser using file path

  ParseObject textParse = test.parseFile(); //parse the text to get each element of the touring machine
  //System.out.println(textParse);

  TouringMachine fileMachine = new TouringMachine(textParse);
  System.out.println(fileMachine);
  
}
