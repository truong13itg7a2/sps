Các giá trị thường dùng

none
👉 Không động chạm gì đến database (bạn phải tự tạo bảng).

validate
👉 Chỉ kiểm tra xem Entity (class Java) có khớp với bảng trong database không.
👉 Nếu khác → báo lỗi, không sửa.

update ✅ (bạn đang hỏi)
👉 Hibernate sẽ so sánh Entity với schema trong DB, rồi cập nhật bảng cho khớp:

Thêm cột mới nếu Entity có field mới.

Đổi tên bảng/cột nếu có annotation thay đổi.

Không xóa dữ liệu cũ.

create
👉 Mỗi lần chạy app → Hibernate xóa hết bảng cũ và tạo lại mới (mất toàn bộ dữ liệu).

create-drop
👉 Giống create, nhưng khi stop app thì xóa luôn bảng.
