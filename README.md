# CS361-P3: Turing Machine
By Shane Ball and Austin Andersen
## Purpose
The purpose of this project is to create a program that takes a single command line argument that is a path to a text file, read from the text file, and create a Turing Machine based on the number of states provided, number of sigma characters, and use the number of states to assign the transitions between each of the non-final states, then test an optional input string and determine if it is in the language of the machine.

## Reflection
Overall this project was not too bad. There was a lot of coding involved. We ran into some issues with loading transitions for each state. We realized part of the problem was a check we had for finalState and so it was just not adding any of the transitions at all. We also had some time issues on the last text file, but were able to resolve those too by switching from a regular linked list to a doubly linked list, where we had a pointer value for the current node.

## Required Files
<p>TouringMachine.java<br>
TMSimulator.java<br>
Transition.java<br>
State.java<br>
Tape.java<br>
TapeNode.java<br>
ParseObject.java<br>
FileParser.java</p>

## Additional Files
<p>file0.txt<br>
file2.txt<br>
file5.txt</p>

## How to run
<p>To run this project, you must first make sure the project is compiled via the following command:<br>
javac TMSimulator.java<br>

<br>Then type in the following:<br>
java TMSimulator.java [file_path]<br>
where [file_path] is the path to a text file matching the correct format</p>

## File format
<p>In order to avoid errors, a given file must have the following format:<br>
[number of states]<br>
[number of alphabet characters]<br>
[transitions]<br>
[optional input string]<br>

<br>Note: the number of transitions is equal to (number of states - 1) * (number of characters)</p>
