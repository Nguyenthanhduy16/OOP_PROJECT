# HỆ THỐNG QUẢN LÝ ĐIỂM RÈN LUYỆN 

## Note về các đặc thù từng phần:
### Khánh:
- Cần cập nhật Build Path của project chính: bấm vào ICTSV -> chuột phải vào build path -> bấm vào libraries -> thêm thư viện JavaFX vào phần Modulepath
- Lỗi khi mở scene builder: ![image](https://github.com/user-attachments/assets/bc144e31-8218-4d3f-b3df-79c0ca541a44)
  - Cách sửa:  
  B1 tải và cài đặt Scene Builder - Link tutorial: https://www.youtube.com/watch?v=qtrFfNqTBWk  
  B2: setup: https://www.youtube.com/watch?v=7VXGzd3H51M
- Lỗi không chạy được file main của StudentController:
  - Cách sửa: Bấm Run -> Run Configurations -> Java Application -> Arguments -> VM arguments -> Thêm --module-path "C:\Users\ADMIN\Downloads\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\lib" --add-modules javafx.controls,javafx.fxml và chạy
  ![image](https://github.com/user-attachments/assets/70582ecf-507a-4c2d-8b89-39e0acd9f14c)
  Tiếp theo cần tải các file font awesomefx vào build path ![image](https://github.com/user-attachments/assets/ad7fd3f5-c0b7-4603-a3d7-0aaa49ccc124)



### Long:
- Chuột phải vào pj -> Properties -> Java build path -> Classpath -> Add JARs... -> thêm toàn bộ thư viện trong lib vào như hình là được
  ![image](https://github.com/user-attachments/assets/51e3f0d8-7286-4743-a6c9-6c5e43a1027e)

  

