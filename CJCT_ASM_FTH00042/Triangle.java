package CJCT_ASM_FTH00042;

// Lớp định nghĩa thực thể hình học Tam giác trong chương trình tính toán thông tin hình tam giác.
// Lớp này đóng gói toàn bộ dữ liệu ba cạnh và cung cấp các phương thức tính toán chu vi, diện tích
// theo công thức Heron, đồng thời tích hợp cơ chế kiểm tra ràng buộc bất đẳng thức tam giác ngay
// tại thời điểm khởi tạo đối tượng và cập nhật thuộc tính để đảm bảo tính toàn vẹn dữ liệu.
public class Triangle {
    // Độ dài cạnh thứ nhất của tam giác
    private double side1;

    // Độ dài cạnh thứ hai của tam giác
    private double side2;

    // Độ dài cạnh thứ ba của tam giác
    private double side3;

    // Phương thức khởi tạo mặc định không tham số, gán giá trị mặc định 1.0 cho cả ba cạnh
    public Triangle() {
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }

    // Phương thức khởi tạo đầy đủ tham số cho ba cạnh tam giác, có kiểm tra điều kiện hợp lệ
    // trước khi cho phép tạo đối tượng trong bộ nhớ nhằm đảm bảo tam giác luôn tồn tại hợp lệ
    public Triangle(double side1, double side2, double side3) {
        // Kiểm tra xem ba cạnh nhập vào có thỏa mãn bất đẳng thức tam giác hay không
        if (!isValidTriangle(side1, side2, side3)) {
            // Ném ngoại lệ ngăn việc tạo đối tượng tam giác không hợp lệ trong hệ thống
            throw new IllegalArgumentException("The lengths of the 3 sides are invalid to form a triangle!");
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // Các phương thức Getter và Setter giúp truy xuất và cập nhật giá trị cho các thuộc tính cạnh tam giác

    public double getSide1() {
        return side1;
    }

    // Cập nhật giá trị cạnh thứ nhất, kiểm tra giá trị dương và bất đẳng thức tam giác trước khi gán
    public void setSide1(double side1) {
        if (side1 <= 0) {
            throw new IllegalArgumentException("Side length must be greater than 0!");
        }
        if (!isValidTriangle(side1, this.side2, this.side3)) {
            throw new IllegalArgumentException("Invalid modification because the new sides cannot form a triangle!");
        }
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    // Cập nhật giá trị cạnh thứ hai, kiểm tra giá trị dương và bất đẳng thức tam giác trước khi gán
    public void setSide2(double side2) {
        if (side2 <= 0) {
            throw new IllegalArgumentException("Side length must be greater than 0!");
        }
        if (!isValidTriangle(this.side1, side2, this.side3)) {
            throw new IllegalArgumentException("Invalid modification because the new sides cannot form a triangle!");
        }
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    // Cập nhật giá trị cạnh thứ ba, kiểm tra giá trị dương và bất đẳng thức tam giác trước khi gán
    public void setSide3(double side3) {
        if (side3 <= 0) {
            throw new IllegalArgumentException("Side length must be greater than 0!");
        }
        if (!isValidTriangle(this.side1, this.side2, side3)) {
            throw new IllegalArgumentException("Invalid modification because the new sides cannot form a triangle!");
        }
        this.side3 = side3;
    }

    // Tính chu vi tam giác bằng tổng độ dài ba cạnh
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    // Tính diện tích tam giác theo công thức Heron sử dụng nửa chu vi làm trung gian
    public double calculateArea() {
        double s = calculatePerimeter() / 2.0; // Tính nửa chu vi (semi-perimeter) phục vụ công thức Heron
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    // Kiểm tra xem ba độ dài cạnh có thỏa mãn điều kiện bất đẳng thức tam giác và đều dương hay không
    public static boolean isValidTriangle(double a, double b, double c) {
        return (a > 0 && b > 0 && c > 0) && 
               (a + b > c) && 
               (b + c > a) && 
               (c + a > b);
    }
}
