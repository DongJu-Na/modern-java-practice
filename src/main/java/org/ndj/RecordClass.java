package org.ndj;

public class RecordClass {
    // old way MemberDto 클래스를 외부 클래스로 변경
    public static class MemberDto {
        private String name;
        private int age;

        public MemberDto(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "MemberDto{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

    }

    // new way Record 클래스 선언
    record Member(String name, int age) {}

    public static void main(String[] args) {
        Member member = new Member("DJ",30);
        System.out.println(member.toString());
        System.out.println("name > " + member.name);
        System.out.println("age > " + member.age);

        MemberDto oldMember = new MemberDto("DJ",30);
        System.out.println(oldMember.toString());
        System.out.println("name > " + oldMember.name);
        System.out.println("age > " + oldMember.age);


    }

}
