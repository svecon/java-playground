package calculator;

/** INPUT
3 + 2
2 * ( 3 + 3 )
( 2 - 3 * ( 1 + 2) )
2 * 3
last - 3
10 * last
1.2 + 4.3
1 / 3
1000000000 * 1000000000
precision 30
1 / 3
3 ** 5
last * 3

/** OUTPUT 
5
12
-7
6
3
30
5.5
0.33333333333333333333
1000000000000000000
0.333333333333333333333333333333
CHYBA
0
*/

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Ondrej Svec
 */
public class CodEx {

    public static void main(String[] argv) {

        InfixCalculator ic = new InfixCalculator(20);

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ((line = input.readLine()) != null) {

                ExpressionParser ep = new ExpressionParser(input);
                String[] expression = ep.parse(line);
//                System.out.println(java.util.Arrays.toString(expression));

                try {
                    BigDecimal num = ic.evaluate(expression);
                    if (num == null) {
                        continue;
                    }
                    if (num.compareTo(new BigDecimal(0)) == 0) {
                        System.out.println("0");
                    } else {
                        System.out.println(num.stripTrailingZeros().toPlainString());
                    }
                } catch (EmptyStackException | NumberFormatException e) {
                    System.out.println("CHYBA");
                    ic.last = new BigDecimal("0");
                }

            }

        } catch (IOException ex) {
            System.out.println("Nastala IOException" + ex);
        }

    }
}

class InfixCalculator {

    BigDecimal last;
    int precision;

    public InfixCalculator(int precision) {
        this.precision = precision;
        last = new BigDecimal(0);
    }

    int precedence(String op) {
        switch (op) {
            case "+":
                return 5;
            case "-":
                return 5;
            case "*":
                return 6;
            case "/":
                return 6;
            case "~":
                return 7;
            case "(":
                return 11;
            case ")":
                return 0;
        }

        return 0;
    }

    public BigDecimal evaluate(String[] expression) {
        if (expression[0].equals("precision")) {
            precision = Integer.parseInt(expression[1]);
            last = new BigDecimal(0);
            return null;
        }

        Stack<String> operators = new Stack<>();
        Stack<BigDecimal> numbers = new Stack<>();

        for (String ex : expression) {
            if (ex == null) {
                break;
            }

            if (ex.length() == 1 && Helper.isOperation(ex.charAt(0))) {

                while (!operators.isEmpty()
                        && !Helper.isParenthesis(operators.peek().charAt(0))
                        && precedence(ex) <= precedence(operators.peek())) {
                    calculate(numbers, operators);
                }

                if (ex.equals(")")) {
                    if (operators.peek().equals("(")) {
                        operators.pop();
                    }
                } else {
                    operators.push(ex);
                }

            } else {
                if (ex.equals("last")) {
                    numbers.add(last);
                } else {
                    numbers.add(new BigDecimal(ex).setScale(precision, RoundingMode.HALF_UP));
                }
            }
        }

        while (!operators.isEmpty()) {
            calculate(numbers, operators);
        }
        
        if (numbers.size() > 1) {
            throw new NumberFormatException();
        }

        last = numbers.pop();
        return last;
    }

    void calculate(Stack<BigDecimal> numbers, Stack<String> operators) {
        BigDecimal left;
        BigDecimal right;
        switch (operators.pop()) {

            case "+":
                right = numbers.pop();
                left = numbers.pop();
                numbers.push(left.add(right));
                break;
            case "-":
                right = numbers.pop();
                left = numbers.pop();
                numbers.push(left.subtract(right));
                break;
            case "~":
                left = numbers.pop();
                numbers.push(left.negate());
                break;
            case "*":
                right = numbers.pop();
                left = numbers.pop();
                numbers.push(left.multiply(right));
                break;
            case "/":
                right = numbers.pop();
                left = numbers.pop();
                numbers.push(left.divide(right, precision, RoundingMode.HALF_UP));
                break;
        }
    }
}

class Helper {

    static boolean isWhitespace(char c) {
        return (c == ' ') || (c == '\t');
    }

    static boolean isOperation(char c) {
        return (c == '(')
                || (c == ')')
                || (c == '+')
                || (c == '-')
                || (c == '*')
                || (c == '/')
                || (c == '~');
    }
    
    static boolean isNumber(char c){
        return (c >= '0') && (c <= '9'); 
    }

    static boolean isParenthesis(char c) {
        return (c == '(') || (c == ')');
    }
}

class ExpressionParser {

    Reader reader;
    String[] expression;
    int used;

    public ExpressionParser(Reader r) {
        reader = r;
        used = 0;
        expression = new String[4];
    }

    public String[] parse(String line) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {

            if (used >= expression.length - 1) {
                String[] temp = new String[expression.length * 2 + 2];
                for (int j = 0; j < expression.length; j++) {
                    temp[j] = expression[j];
                }
                expression = temp;
            }

            if (Helper.isOperation(line.charAt(i)) || Helper.isWhitespace(line.charAt(i))) {

                if (sb.length() > 0) {
                    expression[used++] = sb.toString();
                    sb = new StringBuilder();
                }

                if (Helper.isOperation(line.charAt(i))) {
                    expression[used++] = Character.toString(line.charAt(i));
                }

                continue;
            }
            
            if (i > 0 && Helper.isNumber(line.charAt(i)) && line.charAt(i-1) == '-') {
                if (! expression[used - 1].equals("-")) {
                    throw new NumberFormatException();
                }
                expression[used - 1] = "~";
            }

            sb.append(line.charAt(i));

        }

        if (sb.length() > 0) {
            expression[used++] = sb.toString();
        }

        return expression;
    }
}
