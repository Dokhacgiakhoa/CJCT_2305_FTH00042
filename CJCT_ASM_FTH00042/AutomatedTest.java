package CJCT_ASM_FTH00042;

// Bộ kiểm thử tự động gồm 10 ca kiểm thử độc lập cho lớp Triangle trong bài Assignment.
// Bộ kiểm thử này đánh giá chính xác từng nghiệp vụ logic từ việc khởi tạo đối tượng tam giác
// với các trường hợp hợp lệ và không hợp lệ, tính toán chu vi diện tích, cho tới việc kiểm tra
// cơ chế bảo vệ dữ liệu trong các phương thức Setter.
public class AutomatedTest {
    
    // Biến đếm số lượng ca kiểm thử vượt qua và thất bại để tổng kết cuối cùng
    private static int passedCount = 0;
    private static int failedCount = 0;

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("=== RUNNING AUTOMATED TEST SUITE FOR TRIANGLE CLASS (10 TEST CASES) ===");
        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-55s | %-10s\n", "TEST ID", "TEST CASE DESCRIPTION", "STATUS");
        System.out.println("--------------------------------------------------------------------------");

        // Thực thi tuần tự toàn bộ 10 ca kiểm thử từ TC01 đến TC10
        testDefaultConstructor();
        testValidTriangleNormal();
        testValidTriangleEquilateral();
        testValidTriangleIsosceles();
        testInvalidTriangleZeroSide();
        testInvalidTriangleNegativeSide();
        testInvalidTriangleSumRule();
        testSetterValid();
        testSetterNegativeValue();
        testSetterInvalidTriangle();

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

    // Ca kiem thu 01: Xac minh constructor mac dinh khoi tao ba canh bang 1.0
    private static void testDefaultConstructor() {
        try {
            Triangle t = new Triangle();
            boolean passed = (t.getSide1() == 1.0 && t.getSide2() == 1.0 && t.getSide3() == 1.0);
            logResult("TC01", "Create Triangle with Default Constructor (1.0, 1.0, 1.0)", passed);
        } catch (Exception e) {
            logResult("TC01", "Create Triangle with Default Constructor (1.0, 1.0, 1.0)", false);
        }
    }

    // Ca kiem thu 02: Tao tam giac thuong hop le va kiem tra ket qua tinh chu vi dien tich
    private static void testValidTriangleNormal() {
        try {
            Triangle t = new Triangle(3.0, 4.0, 5.0);
            boolean pMatches = (t.calculatePerimeter() == 12.0);
            boolean aMatches = (t.calculateArea() == 6.0);
            logResult("TC02", "Create Valid Normal Triangle (3, 4, 5) and Check Perimeter/Area", pMatches && aMatches);
        } catch (Exception e) {
            logResult("TC02", "Create Valid Normal Triangle (3, 4, 5)", false);
        }
    }

    // Ca kiem thu 03: Tao tam giac deu va xac minh dien tich theo cong thuc Heron
    private static void testValidTriangleEquilateral() {
        try {
            Triangle t = new Triangle(5.0, 5.0, 5.0);
            double expectedArea = Math.sqrt(7.5 * 2.5 * 2.5 * 2.5);
            boolean pMatches = (t.calculatePerimeter() == 15.0);
            boolean aMatches = (Math.abs(t.calculateArea() - expectedArea) < 0.0001);
            logResult("TC03", "Create Valid Equilateral Triangle (5, 5, 5)", pMatches && aMatches);
        } catch (Exception e) {
            logResult("TC03", "Create Valid Equilateral Triangle (5, 5, 5)", false);
        }
    }

    // Ca kiem thu 04: Tao tam giac can va kiem tra tinh chinh xac cua chu vi va dien tich
    private static void testValidTriangleIsosceles() {
        try {
            Triangle t = new Triangle(5.0, 5.0, 8.0);
            boolean pMatches = (t.calculatePerimeter() == 18.0);
            boolean aMatches = (t.calculateArea() == 12.0);
            logResult("TC04", "Create Valid Isosceles Triangle (5, 5, 8)", pMatches && aMatches);
        } catch (Exception e) {
            logResult("TC04", "Create Valid Isosceles Triangle (5, 5, 8)", false);
        }
    }

    // Ca kiem thu 05: Kiem tra he thong nem ngoai le khi tao tam giac co canh bang 0
    private static void testInvalidTriangleZeroSide() {
        try {
            new Triangle(0.0, 4.0, 5.0);
            logResult("TC05", "Expect Error when creating Triangle with side = 0", false);
        } catch (IllegalArgumentException e) {
            logResult("TC05", "Expect Error when creating Triangle with side = 0", true);
        } catch (Exception e) {
            logResult("TC05", "Expect Error when creating Triangle with side = 0", false);
        }
    }

    // Ca kiem thu 06: Kiem tra he thong nem ngoai le khi tao tam giac co canh am
    private static void testInvalidTriangleNegativeSide() {
        try {
            new Triangle(-3.0, 4.0, 5.0);
            logResult("TC06", "Expect Error when creating Triangle with side < 0", false);
        } catch (IllegalArgumentException e) {
            logResult("TC06", "Expect Error when creating Triangle with side < 0", true);
        } catch (Exception e) {
            logResult("TC06", "Expect Error when creating Triangle with side < 0", false);
        }
    }

    // Ca kiem thu 07: Kiem tra he thong nem ngoai le khi ba canh vi pham bat dang thuc tam giac
    private static void testInvalidTriangleSumRule() {
        try {
            new Triangle(1.0, 2.0, 5.0); // 1 + 2 < 5 vi pham bat dang thuc tam giac
            logResult("TC07", "Expect Error when sides break triangle inequality rule", false);
        } catch (IllegalArgumentException e) {
            logResult("TC07", "Expect Error when sides break triangle inequality rule", true);
        } catch (Exception e) {
            logResult("TC07", "Expect Error when sides break triangle inequality rule", false);
        }
    }

    // Ca kiem thu 08: Kiem tra Setter cap nhat canh hop le ma khong phat sinh loi
    private static void testSetterValid() {
        try {
            Triangle t = new Triangle(3.0, 4.0, 5.0);
            t.setSide1(4.0);
            boolean passed = (t.getSide1() == 4.0);
            logResult("TC08", "Modify Side1 via Setter with a valid value", passed);
        } catch (Exception e) {
            logResult("TC08", "Modify Side1 via Setter with a valid value", false);
        }
    }

    // Ca kiem thu 09: Kiem tra Setter nem ngoai le khi gan gia tri am cho canh tam giac
    private static void testSetterNegativeValue() {
        try {
            Triangle t = new Triangle(3.0, 4.0, 5.0);
            t.setSide1(-1.0);
            logResult("TC09", "Expect Error when setting a side to a negative value", false);
        } catch (IllegalArgumentException e) {
            logResult("TC09", "Expect Error when setting a side to a negative value", true);
        } catch (Exception e) {
            logResult("TC09", "Expect Error when setting a side to a negative value", false);
        }
    }

    // Ca kiem thu 10: Kiem tra Setter nem ngoai le khi gia tri moi pha vo bat dang thuc tam giac
    private static void testSetterInvalidTriangle() {
        try {
            Triangle t = new Triangle(3.0, 4.0, 5.0);
            t.setSide1(10.0); // Canh moi qua lon lam mat dieu kien bat dang thuc tam giac
            logResult("TC10", "Expect Error when setting side breaks inequality rule", false);
        } catch (IllegalArgumentException e) {
            logResult("TC10", "Expect Error when setting side breaks inequality rule", true);
        } catch (Exception e) {
            logResult("TC10", "Expect Error when setting side breaks inequality rule", false);
        }
    }
}
