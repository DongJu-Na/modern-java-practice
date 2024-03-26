package org.ndj;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpression {

    public static void main(String[] args) {
        // 사용자 리스트 생성
        List<User> userList = new ArrayList<>();
        userList.add(new User("홍길동", 30));
        userList.add(new User("김아무개", 25));
        userList.add(new User("박철수", 35));
        userList.add(new User("홍수아", 28));

        // 1. 나이가 30 이상인 사용자를 필터링하는 예제
        List<User> usersOver30 = filterUsers(userList, user -> user.getAge() >= 30);
        System.out.println("나이가 30 이상인 사용자: " + usersOver30);

        // 2. 이름이 특정 문자열을 포함하는 사용자를 필터링하는 예제
        List<User> usersWithNameContainingKim = filterUsers(userList, user -> user.getName().contains("김"));
        System.out.println("이름에 '김' 이 들어가 있는 사용자: " + usersWithNameContainingKim);
    }

    // Predicate를 활용하여 사용자 리스트를 필터링하는 메서드
    public static List<User> filterUsers(List<User> users, Predicate<User> predicate) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (predicate.test(user)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    // 사용자 클래스
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
