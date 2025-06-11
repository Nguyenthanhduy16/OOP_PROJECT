# 🎓HỆ THỐNG QUẢN LÝ ĐIỂM RÈN LUYỆN 
Mục tiêu dự án:
Dự án "Hệ thống Quản lý Điểm Rèn Luyện" được phát triển với mục tiêu xây dựng một ứng dụng phần mềm hỗ trợ quản lý và đánh giá điểm rèn luyện của sinh viên một cách hiệu quả. Dự án tập trung vào việc áp dụng và minh họa các nguyên lý cốt lõi của Lập trình Hướng Đối tượng (OOP), bao gồm: Lớp (Class), Kế thừa (Inheritance), Đa hình (Polymorphism), Đóng gói (Encapsulation).
Tính năng hệ thống:
- Người dùng (Sinh viên):
Thêm, xóa các hoạt động tham gia.
Xem bảng tổng kết điểm rèn luyện theo từng học kỳ.
- Quản trị viên (Admin):
• Quản lý danh sách sinh viên.
• Tạo, chỉnh sửa, và xóa các hoạt động.
• Nhập và cập nhật điểm rèn luyện cho sinh viên.
Giao diện người dùng:
Hệ thống được xây dựng bằng JavaFX, cung cấp giao diện đồ họa trực quan, thân thiện và dễ sử dụng, hỗ trợ người dùng thao tác nhanh chóng và hiệu quả với các chức năng của hệ thống.
## 📁 Repo
> GitHub: [OOP_PROJECT](https://github.com/Nguyenthanhduy16/OOP_PROJECT.git)
## 🔧 Yêu Cầu & Cài Đặt
### 1. Yêu cầu môi trường
- Java JDK 21 trở lên (nên dùng JDK 23)
- JavaFX SDK (bản tương ứng với JDK đang dùng)
- Scene Builder (tùy chọn để chỉnh sửa file FXML)
- IDE: Eclipse hoặc IntelliJ IDEA
### 2. Cài đặt JavaFX trong IDE(Eclipse/IntelliJ)
Với Eclipse: 
- Mở Eclipse, trên Menu bar -> Help -> Eclipse MarketPlace -> search e(fx)clipse chọn Install
- Cài đặt JavaFX: Truy cập [JavaFX](https://gluonhq.com/products/javafx/) tải phiên bản phù hợp với hệ điều hành.
- Thêm JavaFX vào Eclipse: Window → Preferences → search User Libraries → New → Name it as “JavaFX”
  Chọn “Add External JARs” và di chuyển tới thư mục JavaFx ở bước trước, chọn "lib" và thêm tất cả file .jar -> “Apply and Close”.
- Setup cấu hình chạy: Chuột phải vào project ->  Run As → Run Configurations → Arguments → VM arguments
  Add the following command: --module-path "YOUR\PATH\lib" --add-modules javafx.controls,javafx.fxml -> chọn Apply.
  E.g: --module-path "C:\javafx\openjfx-16_windows-x64_bin-sdk\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml
![image](https://github.com/user-attachments/assets/ad7fd3f5-c0b7-4603-a3d7-0aaa49ccc124)
### 3. Cấu hình trong IDE(Eclipse/IntelliJ)
Để chạy được dự án cần thêm một số thư viện:
- Vào Project -> Build Path -> Configure Build Path -> Libraries-> Add External JARs và thêm tất cả file .jar ở đường dẫn ..\OOP_PROJECT\ICTSV\src\view\login\lib  
![image](https://github.com/user-attachments/assets/51e3f0d8-7286-4743-a6c9-6c5e43a1027e)
## Note về các đặc thù từng phần:
  
