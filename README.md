1. Maven lifecycle là gì?

Trong Maven, lifecycle (vòng đời) là một tập hợp các phase (giai đoạn) được chạy theo thứ tự cố định khi bạn build project.

Khi bạn chạy mvn install, Maven sẽ lần lượt chạy qua nhiều phase từ đầu → đến install.

Mỗi phase sẽ kích hoạt các goal (nhiệm vụ cụ thể của plugin).

👉 Hiểu đơn giản:

Lifecycle = quy trình build.

Phase = từng bước trong quy trình.

Goal = hành động chi tiết mà plugin thực hiện.

2. Các loại lifecycle trong Maven

Maven định nghĩa sẵn 3 lifecycle chính:

🔹 1. default lifecycle → dùng để build và deploy project.

Các phase quan trọng theo thứ tự:

validate → kiểm tra project hợp lệ, có đủ thông tin chưa.

compile → biên dịch mã nguồn Java (src/main/java).

test → chạy test unit (src/test/java) với framework như JUnit/TestNG.

package → đóng gói (JAR/WAR).

verify → kiểm tra chất lượng, integration test.

install → copy artifact (JAR/WAR) vào local repo (~/.m2/repository).

deploy → upload artifact lên remote repo (Nexus, Artifactory, Maven Central…).

👉 Thường dùng: mvn clean install hoặc mvn clean package.

🔹 2. clean lifecycle → dọn dẹp project.

pre-clean

clean → xóa thư mục target/.

post-clean

👉 Dùng: mvn clean

🔹 3. site lifecycle → tạo tài liệu, báo cáo project.

pre-site

site → generate site document.

post-site

site-deploy → publish site.

👉 Dùng ít hơn trong thực tế.
4. Tóm gọn dễ nhớ

clean → dọn dẹp target.

validate → kiểm tra project ok chưa.

compile → biên dịch code.

test → chạy test.

package → đóng gói JAR/WAR.

install → đưa vào local repo (~/.m2).

deploy → đẩy lên server Nexus/Maven Central.
