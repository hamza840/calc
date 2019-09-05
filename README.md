# calc
Java program which takes a string expression and evaluates it on the basis of bracket precedence and operator precedence 
There is an Operation.java class which implements interface MathExpressions.java havving methods which are further defined 
in Operation.java. Operation class have following expressions:

setExpressionArray():
  This method takesstring input and convert it into the string array;

exploreExpression():
  THis method checks the brackets in the expression and create small chunks from array without brackets
  
evaluateExpression(String exp):
  This method takes the chunks and solve the expression by checking the operator precedence.
  
void trim():
  In the original expression array all the operand and operators which are solved are replaced with & sign, this function trims the array
  by eleminating the $ values.
  
checkBrackets():
  to check the bracket precedence we have to first search the array if there is any high precedence bracket present in the expression.
  
Test Cases:
  In the test.java file there is a two methos define Test1() and Test2() which uses predefined regular expression and then results are
  printed.
