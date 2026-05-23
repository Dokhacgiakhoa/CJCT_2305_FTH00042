package CJCT_ASM_FTH00042;

import java.util.Scanner;
import java.util.InputMismatchException;

// Lớp khởi chạy chính của chương trình tính toán thông tin hình tam giác hiển thị trên giao diện Console.
// Chương trình thực hiện thu thập dữ liệu ba cạnh tam giác từ bàn phím người dùng, kiểm tra tính hợp lệ
// của dữ liệu đầu vào và tính toán chu vi, diện tích rồi trình bày kết quả ra màn hình.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TRIANGLE INFORMATION CALCULATOR ===");
        
        double a = 0, b = 0, c = 0;
        
        // Thực hiện vòng lặp nhập liệu cho đến khi người dùng cung cấp đúng ba cạnh tạo thành tam giác hợp lệ
        while (true) {
            try {
                System.out.print("Enter the length of the first side (side1): ");
                a = scanner.nextDouble();
                
                System.out.print("Enter the length of the second side (side2): ");
                b = scanner.nextDouble();
                
                System.out.print("Enter the length of the third side (side3): ");
                c = scanner.nextDouble();
                
                // Gọi phương thức kiểm tra bất đẳng thức tam giác trước khi chấp nhận dữ liệu
                if (Triangle.isValidTriangle(a, b, c)) {
                    break;
                } else {
                    System.out.println("\n[Error] The three sides entered do not form a valid triangle!");
                    System.out.println("-> Suggestion: The sum of any two sides must be greater than the remaining side, and all sides must be > 0.");
                    System.out.println("Please enter again.\n");
                }
            } catch (InputMismatchException e) {
                // Xử lý ngoại lệ khi người dùng vô tình nhập ký tự chữ thay vì số thực
                System.out.println("\n[Error] Invalid input data type! Please enter real numbers only.\n");
                scanner.next(); // Dọn sạch bộ đệm Scanner để tránh vòng lặp vô hạn do ký tự lỗi còn sót lại
            }
        }
        
        // Khởi tạo đối tượng tam giác sau khi dữ liệu ba cạnh đã được xác thực hợp lệ
        System.out.println("\nInitializing the triangle object...");
        Triangle triangle = new Triangle(a, b, c);
        
        // Hiển thị kết quả tính toán chu vi và diện tích ra màn hình Console
        System.out.println("\n----------------------------------------------");
        System.out.println("=== CALCULATION RESULTS ===");
        System.out.printf("- Side 1: %.2f\n", triangle.getSide1());
        System.out.printf("- Side 2: %.2f\n", triangle.getSide2());
        System.out.printf("- Side 3: %.2f\n", triangle.getSide3());
        System.out.printf("- Triangle Perimeter: %.2f\n", triangle.calculatePerimeter());
        System.out.printf("- Triangle Area: %.2f\n", triangle.calculateArea());
        System.out.println("----------------------------------------------");
        
        System.out.println("Thank you for using the program!");
        scanner.close();
    }
}
