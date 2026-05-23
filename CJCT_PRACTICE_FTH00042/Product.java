package CJCT_PRACTICE_FTH00042;

// Lớp định nghĩa thực thể Sản phẩm trong hệ thống quản lý kho hàng và đặt mua sản phẩm.
// Đây là một lớp thực thể tuân thủ mô hình đóng gói dữ liệu JavaBean, chứa đầy đủ các thuộc tính
// mô tả thông tin sản phẩm bao gồm mã định danh, tên, ảnh minh họa, giá bán, số lượng tồn kho
// và mô tả chi tiết. Lớp này cung cấp các phương thức nghiệp vụ kiểm tra tồn kho và xử lý đặt hàng.
public class Product {
    // Mã định danh duy nhất của sản phẩm trong hệ thống
    private int id;

    // Tên gọi của sản phẩm
    private String name;

    // Đường dẫn tới ảnh minh họa sản phẩm
    private String thumbnail;

    // Giá bán của sản phẩm theo đơn vị tiền tệ
    private double price;

    // Số lượng sản phẩm hiện còn trong kho hàng
    private int qty;

    // Mô tả chi tiết thông tin và đặc điểm của sản phẩm
    private String description;

    // Phương thức khởi tạo mặc định không tham số, gán các giá trị mặc định an toàn cho sản phẩm
    public Product() {
        this.id = 0;
        this.name = "Unnamed Product";
        this.thumbnail = "default.png";
        this.price = 0.0;
        this.qty = 0;
        this.description = "No description";
    }

    // Phương thức khởi tạo đầy đủ tham số để thiết lập thông tin sản phẩm mới, có kiểm tra
    // ràng buộc nghiệp vụ đảm bảo giá bán và số lượng tồn kho không được phép mang giá trị âm
    public Product(int id, String name, String thumbnail, double price, int qty, String description) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative!");
        }
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity in stock cannot be negative!");
        }
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.qty = qty;
        this.description = description;
    }

    // Các phương thức Getter và Setter giúp truy xuất và cập nhật giá trị cho các thuộc tính của sản phẩm

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    // Cập nhật giá bán sản phẩm, kiểm tra ràng buộc giá không được phép mang giá trị âm
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative!");
        }
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    // Cập nhật số lượng tồn kho sản phẩm, kiểm tra ràng buộc số lượng không được phép mang giá trị âm
    public void setQty(int qty) {
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity in stock cannot be negative!");
        }
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Hiển thị toàn bộ thông tin chi tiết của sản phẩm ra màn hình Console theo định dạng bảng
    public void displayInfo() {
        System.out.println("\n==============================================");
        System.out.println("=== PRODUCT DETAILS ===");
        System.out.println("- Product ID:        " + this.id);
        System.out.println("- Product Name:      " + this.name);
        System.out.println("- Thumbnail Image:   " + this.thumbnail);
        System.out.printf("- Price:             %.2f\n", this.price);
        System.out.println("- Quantity in stock: " + this.qty);
        System.out.println("- Description:       " + this.description);
        System.out.println("==============================================");
    }

    // Kiểm tra xem số lượng hàng tồn kho thực tế có đủ đáp ứng số lượng khách hàng yêu cầu hay không
    public boolean checkAvailability(int expectedQty) {
        return this.qty >= expectedQty;
    }

    // Thực hiện xử lý đặt hàng sản phẩm: kiểm tra tồn kho trước khi trừ số lượng và tính tổng tiền.
    // Phương thức gọi checkAvailability trước khi chốt đơn hàng nhằm bảo vệ tính toàn vẹn dữ liệu,
    // tránh trường hợp số lượng tồn kho bị trừ thành giá trị âm gây lỗi logic nghiệp vụ.
    public double placeOrder(int orderQty) {
        // Kiểm tra số lượng đặt hàng phải là số dương lớn hơn 0
        if (orderQty <= 0) {
            System.out.println("[Error] Order quantity must be greater than 0!");
            return 0.0;
        }
        
        // Xác minh kho hàng còn đủ số lượng để đáp ứng đơn đặt hàng
        if (checkAvailability(orderQty)) {
            this.qty -= orderQty; // Trừ số lượng tồn kho thực tế sau khi xác nhận đơn hàng
            return orderQty * this.price; // Tính và trả về tổng giá trị đơn hàng
        } else {
            System.out.println("[Error] Order failed due to insufficient stock!");
            return 0.0;
        }
    }
}
