## CẤU TRÚC THƯ MỤC BÀI LÀM
Thư mục dự án được tổ chức khoa học và phân chia rõ ràng thành hai phần dự án độc lập tương ứng với hai đề thi:

```text
CJCT_2305_FTH00042/
│
├── CJCT_ASM_FTH00042/                # Phần 1: Dự án Tính toán Tam giác (Console)
│   ├── Triangle.java                 # Lớp thực thể đối tượng Tam giác kèm kiểm tra bất đẳng thức
│   ├── Main.java                     # Lớp chứa hàm main khởi chạy chương trình nhập liệu tương tác
│   └── AutomatedTest.java            # Bộ 10 ca kiểm thử tự động kiểm chứng nghiệp vụ Triangle
│
├── CJCT_PRACTICE_FTH00042/           # Phần 2: Dự án Quản lý Kho sản phẩm và Đặt mua hàng (Console)
│   ├── Product.java                  # Lớp thực thể đối tượng Sản phẩm kèm nghiệp vụ đặt hàng
│   ├── Main.java                     # Lớp chứa hàm main khởi chạy chương trình quản lý đặt hàng
│   └── AutomatedTest.java            # Bộ 10 ca kiểm thử tự động kiểm chứng nghiệp vụ Product
│
├── .gitignore                        # Cấu hình bỏ qua các tệp tin biên dịch dư thừa khi lưu trữ
└── README.md                         # Tệp tin báo cáo nộp bài thi này
```

## HƯỚNG DẪN CHẠY VÀ CHẤM BÀI CHI TIẾT

### 1. Phần 1: Chương trình Tính toán Thông tin Tam giác (Console)
Ứng dụng thực hiện nhập dữ liệu ba cạnh tam giác từ bàn phím người dùng, kiểm tra tính hợp lệ của dữ liệu đầu vào theo bất đẳng thức tam giác, tính toán chu vi và diện tích theo công thức Heron rồi hiển thị kết quả ra màn hình Console.

* **Biên dịch và khởi chạy bằng Terminal**:
  ```powershell
  javac CJCT_ASM_FTH00042/*.java
  java CJCT_ASM_FTH00042.Main
  ```

* **Chạy bộ kiểm thử tự động bài Tam giác**:
  Để chấm điểm nhanh tính chính xác của 10 ca kiểm thử nghiệp vụ bài tam giác:
  ```powershell
  java CJCT_ASM_FTH00042.AutomatedTest
  ```
  Kết quả mong đợi: Chương trình sẽ hiển thị đầy đủ thông tin vượt qua `10/10` ca kiểm thử từ TC01 đến TC10 bao gồm khởi tạo đối tượng, tính chu vi diện tích, kiểm tra ngoại lệ cạnh không hợp lệ và bảo vệ dữ liệu Setter.

### 2. Phần 2: Chương trình Quản lý Kho sản phẩm và Đặt mua hàng (Console)
Ứng dụng thực hiện thu thập thông tin sản phẩm đầy đủ từ bàn phím người dùng, khởi tạo đối tượng sản phẩm trong bộ nhớ, hiển thị chi tiết thông tin sản phẩm và mô phỏng quy trình đặt mua hàng với kiểm tra tồn kho thực tế.

* **Biên dịch và khởi chạy bằng Terminal**:
  ```powershell
  javac CJCT_PRACTICE_FTH00042/*.java
  java CJCT_PRACTICE_FTH00042.Main
  ```

* **Chạy bộ kiểm thử tự động bài Sản phẩm**:
  Bộ kiểm thử thực hiện chạy tuần tự 10 ca kiểm thử để kiểm tra toàn bộ logic nghiệp vụ từ khởi tạo đối tượng, ràng buộc dữ liệu đầu vào, kiểm tra tồn kho cho tới xử lý đặt hàng:
  ```powershell
  java CJCT_PRACTICE_FTH00042.AutomatedTest
  ```
  Kết quả mong đợi: Chương trình vượt qua `10/10` ca kiểm thử từ TC11 đến TC20 (khởi tạo mặc định, khởi tạo đầy đủ, ràng buộc giá âm, ràng buộc số lượng âm, Setter hợp lệ, kiểm tra tồn kho đủ/thiếu, đặt hàng thành công/thất bại).

## TÓM TẮT CÁC CÔNG NGHỆ VÀ KỸ THUẬT ĐÃ ÁP DỤNG
Qua việc hoàn thành bài thi này, em đã áp dụng thành công các kiến thức cốt lõi của ngôn ngữ lập trình Java Core bao gồm:
1. **Lập trình hướng đối tượng (OOP)**: Thiết kế các lớp thực thể đóng gói thuộc tính khoa học theo mô hình JavaBean, thiết lập quan hệ cấu trúc dữ liệu rõ ràng giữa lớp thực thể và lớp điều khiển.
2. **Kiểm tra và ràng buộc dữ liệu đầu vào**: Xử lý ngoại lệ `NumberFormatException` và `InputMismatchException` khi người dùng nhập sai kiểu dữ liệu, sử dụng `.trim()` để loại bỏ khoảng trắng thừa, kiểm tra giá trị âm và điều kiện nghiệp vụ trước khi chấp nhận dữ liệu.
3. **Kỹ thuật tính toán hình học**: Áp dụng công thức Heron để tính diện tích tam giác, kiểm tra bất đẳng thức tam giác để đảm bảo tính hợp lệ của dữ liệu hình học.
4. **Quản lý trạng thái đối tượng**: Sử dụng cơ chế ném ngoại lệ `IllegalArgumentException` trong Constructor và Setter để ngăn chặn việc tạo hoặc cập nhật đối tượng với dữ liệu vi phạm ràng buộc nghiệp vụ.
5. **Kiểm thử tự động (Automated Testing)**: Xây dựng bộ kiểm thử 20 ca kiểm thử tự động tự kiểm chứng toàn bộ nghiệp vụ logic của cả hai bài thi mà không cần thư viện kiểm thử bên ngoài.
