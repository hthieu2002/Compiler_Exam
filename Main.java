import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        String code = ReadTextFile();
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
        // Tạo các biến Pattern cho các loại lexeme cần phân tích
        Pattern keywordPattern = Pattern.compile("\\b(void|int|for)\\b");
        Pattern identifierPattern = Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");
        Pattern numPattern = Pattern.compile("-?\\b\\d+(\\.\\d+)?([Ee][-+]?\\d+)?\\b");
        Pattern operatorPattern = Pattern.compile("[-+*/=(){}<;]");
        Pattern errorPattern = Pattern.compile("[^a-zA-Z_0-9\\s+\\-*/=(){}<>;]\r\n" + //
                "");

        // Tạo danh sách các lexeme
        List<String> lexemes = new ArrayList<>();

        // Tách các lexeme từ đoạn mã
        Matcher matcher = Pattern.compile("\\s+").matcher(code);
        code = matcher.replaceAll(" ");
        matcher = Pattern.compile("(\\b|\\s)([-+*/=(){}<>;])(\\b|\\s)").matcher(code);
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
                lexemes.add(token);
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
