import java.util.regex.*;

public class Tokenizer {
    public static void main(String[] args) {
        // Chuỗi đầu vào
        String input = "12345";

        // Bảng chuyển đổi DFA
        int[][] transitionTable = {
                // Trạng thái 0 (bắt đầu)
                { 1, -1 },
                // Trạng thái 1 (chấp nhận)
                { 1, -1 }
        };

        // Trạng thái ban đầu
        int currentState = 0;

        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);

            // Chuyển đổi trạng thái dựa trên ký tự đầu vào
            if (inputChar >= '0' && inputChar <= '9') {
                currentState = transitionTable[currentState][0];
            } else {
                currentState = transitionTable[currentState][1];
            }

            // Kiểm tra trạng thái sau mỗi bước
            if (currentState == -1) {
                break; // Trạng thái không hợp lệ, dừng kiểm tra
            }
        }

        // Kiểm tra trạng thái cuối cùng
        if (currentState == 1) {
            System.out.println("Chuỗi là số nguyên.");
        } else {
            System.out.println("Chuỗi không phải là số nguyên.");
        }
    }
}