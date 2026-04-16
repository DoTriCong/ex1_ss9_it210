Phần 1 -- Phân tích logic

Trong code hiện tại, bạn lưu giỏ hàng bằng request.setAttribute(\"myCart\", cart).

> Vấn đề: HttpServletRequest chỉ tồn tại trong vòng đời của một request duy nhất. Khi bạn redirect sang /checkout, trình duyệt gửi một request mới hoàn toàn. Do đó, attribute gắn trong request cũ không còn nữa -\> giỏ hàng "mất trí nhớ".
>
> Kết luận: Lỗi không phải do lệnh redirect mà do bạn dùng sai công cụ lưu trữ. Request chỉ sống trong một lần gọi, không giữ dữ liệu xuyên suốt nhiều request.
