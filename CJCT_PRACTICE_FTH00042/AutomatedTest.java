package CJCT_PRACTICE_FTH00042;

// Bộ kiểm thử tự động gồm 10 ca kiểm thử độc lập cho lớp Product trong bài thực hành.
// Bộ kiểm thử này đánh giá chính xác từng nghiệp vụ logic từ việc khởi tạo đối tượng sản phẩm,
// kiểm tra ràng buộc dữ liệu đầu vào âm, cập nhật thuộc tính qua Setter, cho tới việc mô phỏng
// toàn bộ quy trình kiểm tra tồn kho và xử lý đặt hàng thành công lẫn thất bại.
public class AutomatedTest {
    
    // Biến đếm số lượng ca kiểm thử vượt qua và thất bại để tổng kết cuối cùng
    private static int passedCount = 0;
    private static int failedCount = 0;

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("=== RUNNING AUTOMATED TEST SUITE FOR PRODUCT CLASS (10 TEST CASES) ===");
        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-55s | %-10s\n", "TEST ID", "TEST CASE DESCRIPTION", "STATUS");
        System.out.println("--------------------------------------------------------------------------");

        // Thực thi tuần tự toàn bộ 10 ca kiểm thử từ TC11 đến TC20
        testDefaultConstructor();
        testParameterizedConstructorValid();
        testConstructorNegativePrice();
        testConstructorNegativeQty();
        testSetterValid();
        testSetterNegativePrice();
        testAvailabilitySufficient();
        testAvailabilityInsufficient();
        testPlaceOrderSuccess();
        testPlaceOrderFailure();

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("=== TEST SUITE SUMMARY ===");
        System.out.println("Total Cases Executed: 10");
        System.out.println("Passed: " + passedCount + " / 10");
        System.out.println("Failed: " + failedCount + " / 10");
        System.out.println("==========================================================================");
    }

    // Ghi nhận và hiển thị kết quả kiểm thử cho từng ca kiểm thử lên bảng Console
    private static void logResult(String testId, String description, boolean passed) {
        if (passed) {
            passedCount++;
            System.out.printf("%-10s | %-55s | %-10s\n", testId, description, "PASSED");
        } else {
            failedCount++;
            System.out.printf("%-10s | %-55s | %-10s\n", testId, description, "FAILED");
        }
    }

    // Ca kiem thu 11: Xac minh constructor mac dinh khoi tao san pham voi cac gia tri an toan
    private static void testDefaultConstructor() {
        try {
            Product p = new Product();
            boolean passed = (p.getId() == 0 && 
                              p.getName().equals("Unnamed Product") && 
                              p.getPrice() == 0.0 && 
                              p.getQty() == 0);
            logResult("TC11", "Create Product with Default Constructor", passed);
        } catch (Exception e) {
            logResult("TC11", "Create Product with Default Constructor", false);
        }
    }

    // Ca kiem thu 12: Tao san pham hop le voi du cac tham so va xac minh du lieu duoc luu dung
    private static void testParameterizedConstructorValid() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            boolean passed = (p.getId() == 1 && 
                              p.getName().equals("Laptop") && 
                              p.getPrice() == 1200.0 && 
                              p.getQty() == 10);
            logResult("TC12", "Create Valid Product with parameters", passed);
        } catch (Exception e) {
            logResult("TC12", "Create Valid Product with parameters", false);
        }
    }

    // Ca kiem thu 13: Kiem tra he thong nem ngoai le khi khoi tao san pham voi gia ban am
    private static void testConstructorNegativePrice() {
        try {
            new Product(1, "Laptop", "laptop.png", -1200.0, 10, "High end laptop");
            logResult("TC13", "Expect Error when creating Product with negative price", false);
        } catch (IllegalArgumentException e) {
            logResult("TC13", "Expect Error when creating Product with negative price", true);
        } catch (Exception e) {
            logResult("TC13", "Expect Error when creating Product with negative price", false);
        }
    }

    // Ca kiem thu 14: Kiem tra he thong nem ngoai le khi khoi tao san pham voi so luong am
    private static void testConstructorNegativeQty() {
        try {
            new Product(1, "Laptop", "laptop.png", 1200.0, -10, "High end laptop");
            logResult("TC14", "Expect Error when creating Product with negative qty", false);
        } catch (IllegalArgumentException e) {
            logResult("TC14", "Expect Error when creating Product with negative qty", true);
        } catch (Exception e) {
            logResult("TC14", "Expect Error when creating Product with negative qty", false);
        }
    }

    // Ca kiem thu 15: Kiem tra Setter cap nhat gia va so luong hop le ma khong phat sinh loi
    private static void testSetterValid() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            p.setPrice(1300.0);
            p.setQty(15);
            boolean passed = (p.getPrice() == 1300.0 && p.getQty() == 15);
            logResult("TC15", "Modify Product price and qty via valid Setters", passed);
        } catch (Exception e) {
            logResult("TC15", "Modify Product price and qty via valid Setters", false);
        }
    }

    // Ca kiem thu 16: Kiem tra Setter nem ngoai le khi gan gia tri am cho gia ban san pham
    private static void testSetterNegativePrice() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            p.setPrice(-5.0);
            logResult("TC16", "Expect Error when setting product price to a negative value", false);
        } catch (IllegalArgumentException e) {
            logResult("TC16", "Expect Error when setting product price to a negative value", true);
        } catch (Exception e) {
            logResult("TC16", "Expect Error when setting product price to a negative value", false);
        }
    }

    // Ca kiem thu 17: Xac minh ham kiem tra ton kho tra ve dung khi so luong yeu cau nho hon ton kho
    private static void testAvailabilitySufficient() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            boolean passed = p.checkAvailability(5); // Yeu cau mua 5, kho hien co 10
            logResult("TC17", "Check Availability when stock is sufficient (Need 5, Has 10)", passed);
        } catch (Exception e) {
            logResult("TC17", "Check Availability when stock is sufficient", false);
        }
    }

    // Ca kiem thu 18: Xac minh ham kiem tra ton kho tra ve sai khi so luong yeu cau vuot qua ton kho
    private static void testAvailabilityInsufficient() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            boolean passed = !p.checkAvailability(15); // Yeu cau mua 15, kho chi co 10
            logResult("TC18", "Check Availability when stock is insufficient (Need 15, Has 10)", passed);
        } catch (Exception e) {
            logResult("TC18", "Check Availability when stock is insufficient", false);
        }
    }

    // Ca kiem thu 19: Mo phong dat hang thanh cong va xac minh tong tien cung so luong ton kho con lai
    private static void testPlaceOrderSuccess() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            double totalPrice = p.placeOrder(3); // Dat mua 3 san pham, kho con lai 7
            boolean pricePassed = (totalPrice == 3600.0);
            boolean qtyPassed = (p.getQty() == 7);
            logResult("TC19", "Place a valid order and verify remaining stock & price", pricePassed && qtyPassed);
        } catch (Exception e) {
            logResult("TC19", "Place a valid order", false);
        }
    }

    // Ca kiem thu 20: Mo phong dat hang that bai do so luong yeu cau vuot qua ton kho hien co
    private static void testPlaceOrderFailure() {
        try {
            Product p = new Product(1, "Laptop", "laptop.png", 1200.0, 10, "High end laptop");
            double totalPrice = p.placeOrder(12); // Dat mua 12, kho chi co 10 nen that bai
            boolean pricePassed = (totalPrice == 0.0);
            boolean qtyPassed = (p.getQty() == 10); // So luong kho khong thay doi khi don hang that bai
            logResult("TC20", "Expect order failure when quantity exceeds current stock", pricePassed && qtyPassed);
        } catch (Exception e) {
            logResult("TC20", "Expect order failure when quantity exceeds current stock", false);
        }
    }
}
