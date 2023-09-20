import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        String code = ReadTextFile();
        System.out.println(code);
        analyzeCode(code);
    }

    // ham import file input.txt
    public static String ReadTextFile() {
        String filePath = "./File/Input.txt";
        StringBuilder codeBuilder = new StringBuilder();

        try {
            // Mở file
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Đọc từng dòng trong file và nối vào chuỗi codeBuilder
            while ((line = bufferedReader.readLine()) != null) {
                codeBuilder.append(line).append("\n");
            }

            // Đóng file
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Trả về nội dung đọc được từ file dưới dạng chuỗi
        return codeBuilder.toString();
    }

    public static void analyzeCode(String code) {
        // setup keyword
        String keyword = "while|if|else|return|break|continue|int|float|void|for";

        String letter = "[A-Za-z]";
        String digit = "[0-9]";
        String underscore = "_";
        String identifier = letter + "(" + letter + "|" + digit + "|" + underscore + ")*";

        String digits = digit + "(" + digit + ")*";
        String optionalFraction = "(\\." + digits + ")?";
        String optionalExponent = "(.)?(E[+|-]?" + digits + ")?";
        String num = "^" + digits + optionalFraction + optionalExponent + "$";

        // Tạo các biến Pattern cho các loại lexeme cần phân tích
        Pattern keywordPattern = Pattern.compile("\\b" + "(" + keyword + ")" + "\\b");
        Pattern identifierPattern = Pattern.compile("\\b" + identifier + "\\b");
        Pattern numPattern = Pattern.compile("\\b" + num + "\\b");
        Pattern operatorPattern = Pattern.compile("[-+*/=(){}<>;]");
        Pattern errorPattern = Pattern.compile("[\\.^a-zA-Z_0-9\\-*/=(){}<>;]");

        // Tạo danh sách các lexeme
        List<String> lexemes = new ArrayList<>();

        // Tách các lexeme từ đoạn mã
        Matcher matcher = Pattern.compile("\\s").matcher(code);
        code = matcher.replaceAll(" ");
        matcher = Pattern.compile("(\\b|\\s*)([-+*/=(){}<>;])(\\b|\\s*)").matcher(code);
        code = matcher.replaceAll("$1 $2 $3");
        String[] tokens = code.split("\\s");

        for (String token : tokens) {
            Matcher keywordMatcher = keywordPattern.matcher(token);
            Matcher identifierMatcher = identifierPattern.matcher(token);
            Matcher numMatcher = numPattern.matcher(token);
            Matcher operatorMatcher = operatorPattern.matcher(token);
            Matcher errorMatcher = errorPattern.matcher(token);

            if (keywordMatcher.matches()) {
                lexemes.add("keyword : " + token);
            } else if (identifierMatcher.matches()) {
                lexemes.add("identifier : " + token);
            } else if (numMatcher.matches()) {
                lexemes.add("num : " + token);
            } else if (operatorMatcher.matches()) {
                lexemes.add(token + " : " + token);
            } else if (errorMatcher.find()) {
                lexemes.add("Error : " + token);
            }
        }

        // In kết quả
        System.out.println("Class : Lexeme");
        for (String lexeme : lexemes) {
            System.out.println(lexeme);
        }
    }
}
