import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
  public static void main(String[] args) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("./File/Input.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        processLine(line);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void processLine(String line) {
    // Define regular expressions for various token types

    String keyword = "while|if|else|return|break|continue|int|float|void|for";

    String letter = "[A-Z|a-z]";
    String digit = "[0-9]";
    String underscore = "_";
    String identifier = letter + "(" + letter + "|" + digit + "|" + underscore + ")*";

    String digits = digit + "(" + digit + ")*";
    String optionalFraction = "(.(" + digits + "))?";
    String optionalExponent = "(\\.)?([Ee][+-]?" + digits + ")?";
    String num = digits + optionalFraction + optionalExponent;

    String keywordsRegex = "\\b(" + keyword + ")\\b";
    String identifierRegex = identifier;
    String numRegex = num;
    String operatorsRegex = "[=+<>]";
    String symbolsRegex = "[(){};]";
    String errorRegex = "\\.";

    String regex = String.format(
        "(%s)|(%s)|(%s)|(%s)|(%s)|(%s)",
        keywordsRegex, identifierRegex, numRegex, operatorsRegex, symbolsRegex, errorRegex, "\\S+");

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(line);

    while (matcher.find()) {
      String token = matcher.group();
      if (token.matches(keywordsRegex)) {
        System.out.println("keyword : " + token);
      } else if (token.matches(identifierRegex)) {
        System.out.println("identifier : " + token);
      } else if (token.matches(numRegex)) {
        System.out.println("num : " + token);
      } else if (token.matches(operatorsRegex)) {
        System.out.println(token + " : " + token);
      } else if (token.matches(symbolsRegex)) {
        System.out.println(token + " : " + token);
      } else if (token.matches(errorRegex)) {
        System.out.println("Error : " + token);
      }
    }
  }
}
