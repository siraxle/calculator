import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException, InvalidExpressionException {
    //считываем с консоли
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String expression = reader.readLine();

    //разделяем выражение
    String[] operands = expression.split("[+*/\\-]");

    //парсим операнды
    int[] intOperands = parseOperands(operands);
    int a = intOperands[0];
    int b = intOperands[1];

    //парсим оператор
    String operator = "";
    for (char op : "+-/*".toCharArray()) {
      String opStr = Character.toString(op);
      if (expression.contains(opStr)) {
        operator = opStr;
      }
    }

    //вычисляем результат
    int result = 0;
    switch (operator) {
      case "+":
        result = a + b;
        break;
      case "-":
        result = a - b;
        break;
      case "*":
        result = a * b;
        break;
      case "/":
        result = a / b;
        break;
    }

    if (isRomanNumbers(expression)) {
      if (result < 1) {
        throw new IllegalArgumentException("roman number result is less then I (1)");
      }
      System.out.println(RomanConverter.finalCal(result));
    } else {
      System.out.println(result);
    }


  }

  static int[] parseOperands(String[] operands) throws IllegalArgumentException {
    for (int i = 0; i < operands.length; i++) {
      operands[i] = operands[i].trim();
    }

    int a, b;
    if (isRomanNumbers(operands[0] + operands[1])) {
      //парсим римские цифры
      try {
        a = RomanNumber.valueOf(operands[0]).ordinal() + 1;
        b = RomanNumber.valueOf(operands[1]).ordinal() + 1;
      } catch (IllegalArgumentException exception) {
        throw new IllegalArgumentException("operand is not a Roman number");
      }
    } else {
      //парсим арабские цифры
      try {
        a = Integer.parseInt(operands[0].trim());
        b = Integer.parseInt(operands[1].trim());
      } catch (NumberFormatException exception) {
        throw new IllegalArgumentException("wrong expression");
      }
    }
    if (a > 10 || b > 10) {
      throw new IllegalArgumentException("value of operand is greater 10");
    }
    return new int[]{a, b};
  }

  static boolean isRomanNumbers(String argument) {
    for (char c : "0123456789".toCharArray()) {
      if (argument.indexOf(c) >= 0) {
        return false;
      }
    }
    return true;
  }

}
