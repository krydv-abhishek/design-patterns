package org.abhishek.creational;

public class BuilderII {


    public static void main(String[] args) {
        UserBuilder user = UserBuilder.builder()
                .name("Alice")
                .age(30)
                .email("alice@example.com")
                .build();

        System.out.println(user.getName() + " - " + user.getEmail());
    }

}
