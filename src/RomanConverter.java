public class RomanConverter {
  //Create a public 2D Array to hold the values of roman numerals. Everything above 5000 is supposed to have an overline, but since I could not find the
  //right hexademical code, I found close equivalents to make it work.
  public static String[][] romanArray = {{"\u1e40" + "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40", "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40", "\u1e40" + "\u1e40" + "\u1e40" + "\u1e40", "\u1e40"}, //9M, 5M, 4M, 1M
      {"\u010a" + "\u1e40", "\u1e0a", "\u010a" + "\u1e0a", "\u010a"},     //900K, 500K, 400K, 100K
      {"\u1e8a" + "\u010a", "\u1e38", "\u1e8a" + "\u1e38", "\u1e8a"},    //90,000 , 50,000, 40,000, 10,000
      {"M" + "\u1e8a", "\u1e7c", "M" + "\u1e7c", "M"},       //9000, 5000, 4000, 1000
      {"CM", "D", "CD", "C"},         //900, 500, 400, 100
      {"XC", "L", "XL", "X"},         //90, 50, 40, 10
      {"IX", "V", "IV", "I"}};        //9, 5, 4, 1

  public static void main(String[] args) {
    for (int i = 0; i <= 100; i++) {
      System.out.println(finalCal(i));
    }

  }

  //Uses the romanValue1 to run through each number given.
  //Starting with 1,000,000 then working down, each value is converted to a roman numeral.
  public static String finalCal(int userNumber) {
    String output = "";
    int decidingNumber = 1_000_000;

    while (decidingNumber != 0) {
      output += romanValue1(userNumber, decidingNumber);
      userNumber %= decidingNumber;
      decidingNumber = decidingNumber / 10;
    }
    return output;
  }

  //Run through an iteration of calculations. Depending on the size of the userNumber, the relevant array will be used
  //When running through these, the biggest ones to look out for are 9,5,4, and 1. Everything else is built around these numbers
  public static String romanValue1(int userNumber, int decidingNumber) {
    String output = "";
    int array1 = 0;

    if (userNumber >= 1_000_000) {
      array1 = 0;
    } else if (userNumber >= 100_000) {
      array1 = 1;
    } else if (userNumber >= 10_000) {
      array1 = 2;
    } else if (userNumber >= 1000) {
      array1 = 3;
    } else if (userNumber >= 100) {
      array1 = 4;
    } else if (userNumber >= 10) {
      array1 = 5;
    } else {
      array1 = 6;
    }

    int tempNum = 0;
    tempNum = userNumber / decidingNumber;
    if (tempNum == 9) {
      output += romanArray[array1][0];
    } else if (tempNum == 5) {
      output += romanArray[array1][1];
    } else if (tempNum == 4) {
      output += romanArray[array1][2];
    } else if (tempNum == 1) {
      output += romanArray[array1][3];
    } else if (tempNum > 5 && tempNum < 9) {
      output += romanArray[array1][1];
      tempNum -= 5;
      for (int i = 0; i < tempNum; i++) {
        output += romanArray[array1][3];
      }
    } else {
      for (int i = 0; i < tempNum; i++) {
        output += romanArray[array1][3];
      }
    }

    return output;
  }


}