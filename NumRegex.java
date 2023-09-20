import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.*;

class NumRegex {
    public static void main(String[] args) {
        String code = ReadTextFile();
        System.out.println(code);
        analyzeCode(code);
    }

    // Hàm import file input.txt
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

    }
}
