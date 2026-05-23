package CJCT_PRACTICE_FTH00042;

import java.util.Scanner;

// Lớp khởi chạy chính của chương trình quản lý kho sản phẩm và mô phỏng đặt mua hàng trên Console.
// Tích hợp luồng điều khiển nhập liệu tuần tự thu thập thông tin sản phẩm từ bàn phím người dùng,
// đồng thời xử lý chặt chẽ việc kiểm tra tính hợp lệ của dữ liệu đầu vào bằng kỹ thuật
// đọc nextLine kết hợp ép kiểu để ngăn chặn mọi lỗi trôi lệnh Scanner gây crash chương trình.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== PRODUCT INVENTORY AND ORDER SYSTEM ===");
        
        int id = 0;
        String name = "";
        String thumbnail = "";
        double price = 0.0;
        int qty = 0;
        String description = "";
        
        // Thu thập thông tin sản phẩm từ bàn phím sử dụng nextLine kết hợp ép kiểu số
        // để dọn sạch hoàn toàn ký tự xuống dòng trong bộ đệm Scanner, giải quyết triệt để
        // lỗi trôi lệnh nhảy bước nhập chuỗi kinh điển khi dùng xen kẽ nextInt và nextLine
        
        // Nhập và xác thực mã định danh sản phẩm là số nguyên dương
        while (true) {
            try {
                System.out.print("Enter Product ID (integer): ");
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id <= 0) {
                    System.out.println("[Error] Product ID must be a positive integer!\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid integer format!\n");
            }
        }
        
        // Nhập và kiểm tra bắt buộc đối với tên sản phẩm không được để trống
        while (true) {
            System.out.print("Enter Product name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("[Error] Product name cannot be empty!\n");
                continue;
            }
            break;
        }
        
        // Nhập đường dẫn ảnh minh họa sản phẩm, gán giá trị mặc định nếu bỏ trống
        System.out.print("Enter path to illustration image (thumbnail): ");
        thumbnail = scanner.nextLine().trim();
        if (thumbnail.isEmpty()) {
            thumbnail = "default.png";
        }
        
        // Nhập và xác thực giá bán sản phẩm là số thực không âm
        while (true) {
            try {
                System.out.print("Enter Price (double >= 0): ");
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price < 0) {
                    System.out.println("[Error] Price cannot be negative!\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid double format!\n");
            }
        }
        
        // Nhập và xác thực số lượng tồn kho là số nguyên không âm
        while (true) {
            try {
                System.out.print("Enter Quantity in stock (integer >= 0): ");
                qty = Integer.parseInt(scanner.nextLine().trim());
                if (qty < 0) {
                    System.out.println("[Error] Quantity cannot be negative!\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid integer format!\n");
            }
        }
        
        // Nhập mô tả chi tiết sản phẩm, gán giá trị mặc định nếu người dùng bỏ trống
        System.out.print("Enter Product description: ");
        description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = "No description available";
        }
        
        // Khởi tạo đối tượng sản phẩm sau khi toàn bộ dữ liệu đầu vào đã được xác thực hợp lệ
        System.out.println("\nInitializing product details...");
        Product product = new Product(id, name, thumbnail, price, qty, description);
        
        // Hiển thị thông tin chi tiết sản phẩm vừa được khởi tạo ra màn hình Console
        product.displayInfo();
        
        // Thực hiện mô phỏng quy trình đặt mua hàng theo số lượng người dùng yêu cầu
        System.out.println("\n--- PLACE AN ORDER ---");
        int orderQty = 0;
        
        while (true) {
            try {
                System.out.print("Enter desired order quantity (integer > 0): ");
                orderQty = Integer.parseInt(scanner.nextLine().trim());
                
                if (orderQty <= 0) {
                    System.out.println("[Error] Order quantity must be greater than 0!\n");
                    continue;
                }
                
                // Kiểm tra xem kho hàng hiện tại có đủ số lượng để đáp ứng đơn đặt hàng hay không
                if (product.checkAvailability(orderQty)) {
                    System.out.println("-> Stock available. Processing order...");
                    double totalPrice = product.placeOrder(orderQty);
                    
                    System.out.println("\n==============================================");
                    System.out.println("=== ORDER RECEIPT (SUCCESS) ===");
                    System.out.println("- Product Name:       " + product.getName());
                    System.out.println("- Ordered Quantity:   " + orderQty);
                    System.out.printf("- Unit Price:         %.2f\n", product.getPrice());
                    System.out.printf("- TOTAL PRICE:        %.2f\n", totalPrice);
                    System.out.println("----------------------------------------------");
                    System.out.println("- Remaining quantity in stock: " + product.getQty());
                    System.out.println("==============================================");
                    break;
                } else {
                    System.out.println("\n[Notification] Insufficient stock available!");
                    System.out.println("- Current stock: only " + product.getQty() + " item(s) left.");
                    System.out.println("Please enter a suitable quantity.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid integer format!\n");
            }
        }
        
        System.out.println("\nThank you for using the ordering system!");
        scanner.close();
    }
}
