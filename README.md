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
![Image](https://github.com/user-attachments/assets/d603037f-ab17-475c-affd-785e2efcc8db)
### 3. Cấu hình trong IDE(Eclipse/IntelliJ)
Để chạy được dự án cần thêm một số thư viện:
- Vào Project -> Build Path -> Configure Build Path -> Libraries-> Add External JARs và thêm tất cả file .jar ở đường dẫn ..\OOP_PROJECT\ICTSV\src\view\login\lib  
![Image](https://github.com/user-attachments/assets/b58f043e-6520-4d07-b237-9b5be98fa005)
##🧩 Kiến trúc & Cấu trúc thư mục
```
## Cấu trúc dự án
OOP_PROJECT/
├─ src/                 → Code nguồn (Java, MVC: Controller, Model, View FXML)
  ├── controller/
  ├── data/              # Chữa file json lưu thông tin sinh viên, admin và danh sách hoạt động của admin        
  ├── entity/            # Các lớp thực thể (model) như SinhVien, HoatDong,...
  ├── handle/            # Xử lý logic chính (tính điểm, kiểm tra hợp lệ, ...)
  ├── screen/            # Màn hình chính và các giao diện phụ
  ├── style/             # Tệp CSS và cấu hình giao diện JavaFX
  ├── view/              # Quản lý giao diện người dùng và tương tác UI
├─ DESIGN/              → UML, sơ đồ thiết kế
├─ .project / .idea     → File cấu hình IDE
```
## 🚀 Hướng Dẫn Sử Dụng
1. Clone repo về máy:
```bash
git clone https://github.com/Nguyenthanhduy16/OOP_PROJECT.git
```
2. Mở project trong IDE
3. Thiết lập môi trường JavaFX như trên
4. Chạy file ICTSV
