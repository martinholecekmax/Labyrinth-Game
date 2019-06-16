![labyrinth](https://github.com/martinholecekmax/Labyrinth-Game/blob/master/Labyrinth.png "Labyrinth")
# Description
In this project, a domain-specific programming language based on an imperative model has been developed which has been created in the Java environment. The goal of this language is to control the player through the maze by using specific commands that are written into the text area window. Several language features have been implemented such as statements, loops and functions which are typical for other languages such as Java or Python. This language is a dynamically typed language where the types are defined during the compilation. Similarly to arithmetic operations, the language implements the order of operations in infix notation, which is typical for modern languages. The newline character terminates every statement except the block of the function scope, which is only surrounded by the curly braces. The identifiers are case sensitive; however, the reserved words and commands are case insensitive.

# Syntax Rules
In this section, we will define syntax rules that have been implemented for this language. Several commands and implementations were inherited from the Sili language created by [Voorhis (2017)](https://github.com/DaveVoorhis/LDI) such as functions, variables, IF statement and others.

## Comments
This language implements three types of comments similarly to Java, such as double slash comments, multiple-lines comments and java doc comments.

```javascript
// This is an example of a single line comment.

/*
* This is an example of a multi-line comment.
*/

/**
* This is an example of Javadoc comment
*/

```

## If Statement
The IF statements consist of an expression surrounded by brackets and optionally with ELSE statement. This expression can be any Boolean expression which will determine if the following statement body will be executed. In the case of the expression to be false and the ELSE statement has been used, the ELSE statement body will be executed instead of IF statement body. 

```javascript
// Multiple lines statement
a = 10
IF ( a > 0 ) {
    write a
    a = a – 1
}
ELSE {
    write “Done”
}

// Single line statement
age = 21
IF ( age > 18 )
    write “You are an adult.”
ELSE
    write “You are still a child”
```

## For Loop
The FOR loop is implemented in the same way as the other popular languages such as Java or Python.

```javascript
// For Loop example
a = 10
FOR (i=1; i <= a ; i=i+1) {
    write i
}
```

## While Loop
The WHILE loop evaluates Boolean expression which is surrounded in the brackets. If this expression is true, then the statement body will be executed; otherwise, it will continue to process the source code after the statement.

```javascript
// While Loop example
x = 10
WHILE (x > 0) {
    write x
    x = x - 1
}
```

## Write Command
The WRITE command prints a text to the console and into the text area denoted as Message. This command can be beneficial for debugging and testing of the commands.

```javascript
// Write Command example
WRITE  21
WRITE  “Hello World”
```

## Functions
Functions are implemented in the same way as in Java language except that the keyword is denoted as FN instead of the function. Also, the parameters do not include the definition of the type, which is defined dynamically.

```javascript
// Using Functions example
FN moveLeft ( x ) {
    for (i=1; i <= x ; i=i+1) {
        LEFT
    }
}
```

## Arrays
Arrays are used to store values in a single structure. An array consists of an individual or multiple elements. Each element of this array can be any type of an expression such as Boolean, integer or string where the array can have a mix of these expressions. For example, an array can have two elements which one of them is an integer, and the other one is Boolean. This behaviour has been implemented intentionally in order to be able to use different types within a single array; however, this approach is probably not very practical.

```javascript
/*
* Example of using Arrays
*/
a[] = {1,3,7}
b[] = {true, false, 3}
c[] = {"hello", "bye", 2}

// Print elements on the screen
write a[1]
write b[0]
write b[2]
write c[0]
```

## Operators
This language implements several operators that can form an expression such as addition, subtraction, multiplication or division.

# Commands
There have been various commands to control the game environment, which not only allows a player to move through the maze but also other functions such as freezing of the enemies or enabling the keyboard.

## Moving Commands
These commands allow the player to navigate through the maze to finish the game. There have been four moving commands implemented such as move left, right, down and up. Applying these commands can be found inside the **test04.lyth** file.

```javascript
UP    // Move Up
DOWN  // Move Down
LEFT  // Move Left
RIGHT // Move Right
```

## Shooting Command
In order to move through the maze without being killed by the enemies, the player is able to shoot. There is a single command to shoot the enemies which takes a single parameter of the direction of the shot. This parameter is a single character of four values such as **L** for the left direction, **R** for the right direction, **D** for the down direction and **U** for the up direction. Application of these commands can be found in **test07.lyth** file.

```javascript
FIRE("U")   // Fire Up
FIRE("D")   // Fire Down
FIRE("L")   // Fire Left
FIRE("R")   // Fire Right
```

## Enable/Disable Keyboard
This command allows a user to control the player by using a keyboard. Initially, the user cannot control the player by the keyboard only by typing the source code into the text area. Arrows on the keyboard can control the movement of the player, and the **W, A, S, D** keys are used for shooting.

```javascript
// Enable Keyboard
KEYBOARD(ON)
// Disable Keyboard
KEYBOARD(OFF)
```

## Answer Command
Every time the player steps on the doors, the message will pop up, which has to be answered before the player can continue moving through the maze. For this reason, the answer command has been implemented, which takes a single argument of the string value, which is compared to the real answer and if these answers are same the game continues.

```javascript
// Using of Answer Command
ANSWER(“CPU”)
```

## Freeze Command
This command allows a user to freeze the movement of the enemies for several seconds.

```javascript
// Using of Freeze Command
FREEZE
```

## Quit Command
This command will terminate the game immediately.

```javascript
// Using of Quit Command
QUIT
```























