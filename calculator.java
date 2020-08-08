package org.com;/* A Java program to evaluate a given expression where tokens are separated
by space. 
Test Cases: 
	"10 + 2 * 6"		 ---> 22 
	"100 * 2 + 12"		 ---> 212 
	"100 * ( 2 + 12 )"	 ---> 1400 
	"100 * ( 2 + 12 ) / 14" ---> 100	 
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import calculator.scientific; 

public class calculator
{
    public static double evaluate(String expression)
    {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sin", 1);
        map.put("cos", 1);
        map.put("tan", 1);
        map.put("cosec", 1);
        map.put("sec", 1);
        map.put("cot", 1);

        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
            }
            else if (Character.isLetter(tokens[i])) {
                StringBuffer buff = new StringBuffer();
                while (i < tokens.length && Character.isLowerCase(tokens[i])) {
                    buff.append(tokens[i++]);
                }
                String str = buff.toString();
                if (map.containsKey(str)) {
                    buff.delete(0, buff.length());
                    while (i < tokens.length && Character.isDigit(tokens[i])) {
                        buff.append(tokens[i++]);
                    }i--;
                    double deg=Double.parseDouble(buff.toString());

                    advanced ad = new advanced();
                    double val = ad.evaluate(str, deg);
                    //Handling infinity cases
                    if (val == Integer.MAX_VALUE)
                        return Integer.MAX_VALUE;

                    values.push(val);
                }
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

                // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static double applyOp(char op, double second, double first)
    {   Scanner sc= new Scanner(System.in); //System.in is a standard input stream

        double ans = sc.nextDouble();

        double result;

        switch(op)
        {
            case '+':
                result = first + second;
                if(result==ans)
                {
                    System.out.printf("Match");
                }
                break;

            case '-':
                result = first - second;
                if(result==ans)
                {
                    System.out.printf("Match");
                }
                break;

            case '*':
                result = first * second;
                if(result==ans)
                {
                    System.out.printf("Match");
                }
                break;

            case '/':
                if(second == 0)
                    System.out.printf("can not devide by zero");
                result = first / second;
                if(result==ans)
                {
                    System.out.printf("Match");
                }
                break;

            // operator doesn't match any case constant (+, -, *, /)
            default:
                System.out.printf("Error! operator is not correct");
                return 0;
        }
        return ans;
    }

    // Driver method to test above methods
    public static void main(String[] args)
    {
        while(true) {
            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
            String str= sc.nextLine();
            double ans = calculator.evaluate(str);
            System.out.println(ans);
			/*if (ans == Integer.MIN_VALUE) {
				System.out.println("Error");
			 } else if (ans == Integer.MAX_VALUE) {
				 System.out.println("Und");
			 }else {
				 System.out.println(ans);
			 }*/
        }
	/*	System.out.println(EvaluateString.evaluate("10 + 2 * 6")); 
		System.out.println(EvaluateString.evaluate("100 * 2 + 12")); 
		System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 )")); 
		System.out.println(EvaluateString.evaluate("100 * ( 2 + 12 ) / 14")); */
    }
}