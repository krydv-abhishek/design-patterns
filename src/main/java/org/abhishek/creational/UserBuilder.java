package org.abhishek.creational;

public class UserBuilder {

        private String name;
        private int age;
        private String email;

        // Private constructor to enforce object creation via Builder
        public UserBuilder(Builder builder) {
            this.name = builder.name;
            this.age = builder.age;
            this.email = builder.email;
        }

        // Getters (optional)
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }

        // Static nested Builder class
        public static class Builder {
            private String name;
            private int age;
            private String email;

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder age(int age) {
                this.age = age;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public UserBuilder build() {
                return new UserBuilder(this);
            }
        }

        // Optional: static method to start building
        public static Builder builder() {
            return new Builder();
        }

}
