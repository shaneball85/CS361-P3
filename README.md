# CS361-P3: Turing Machine
By Shane Ball and Austin Andersen
## Purpose
The purpose of this project is to create a program that takes a single command line argument that is a path to a text file, read from the text file, and create a Turing Machine based on the number of states provided, number of sigma characters, and use the number of states to assign the transitions between each of the non-final states, then test an optional input string and determine if it is in the language of the machine.

## Reflection
Overall this project was not too bad. There was a lot of coding involved. We ran into some issues with loading transitions for each state. We realized part of the problem was a check we had for finalState and so it was just not adding any of the transitions at all. We also had some time issues on the last text file, but were able to resolve those too by switching from a regular linked list to a doubly linked list, where we had a pointer value for the current node.

## Required Files
TouringMachine.java
TMSimulator.java
Transition.java
State.java
ParseObject.java
FileParser.java

## Additional Files
file0.txt
file2.txt
file5.5txt

## How to run
To run this project, you must first make sure the project is compiled via the following command:
javac TMSimulator.java

Then type in the following:
java TMSimulator.java [file_path]
where [file_path] is the path to a text file matching the correct format

## File format
In order to avoid errors, a given file must have the following format:
[number of states]
[number of alphabet characters]
[transitions]
[optional input string]

Note: the number of transitions is equal to (number of states - 1) * (number of characters)
