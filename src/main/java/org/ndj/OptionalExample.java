package org.ndj;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        // 사용자가 주문한 상품이 배송되었는지 여부를 옵셔널로 표현
        Optional<Boolean> isDelivered = getOrderDeliveryStatus(1234);

        // 상품이 배송되었는지 확인하고 상태를 출력
        if (isDelivered.isPresent()) {
            if (isDelivered.get()) {
                System.out.println("주문하신 상품이 성공적으로 배송되었습니다.");
            } else {
                System.out.println("주문하신 상품은 아직 배송되지 않았습니다.");
            }
        } else {
            System.out.println("주문하신 상품 정보를 찾을 수 없습니다.");
        }
    }

    // 사용자 주문에 대한 배송 상태를 반환하는 메서드
    public static Optional<Boolean> getOrderDeliveryStatus(int orderId) {
        // 여기서는 간단히 orderId가 홀수이면 true(배송됨), 짝수이면 false(배송안됨)를 반환하는 것으로 가정
        if (orderId % 2 == 0) {
            return Optional.of(false); // 주문이 짝수이면 배송되지 않음
        } else {
            return Optional.of(true); // 주문이 홀수이면 배송됨
        }
    }
}
