package ua.petstore.client.models;

public class User implements ApiModel {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

    public static class Builder {
        private final User newUser;

        private Builder() {
            newUser = new User();
        }

        public User.Builder withId(Integer id) {
            newUser.id = id;
            return this;
        }

        public User.Builder withUserName(String userName) {
            newUser.username = userName;
            return this;
        }

        public User.Builder withFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        public User.Builder withLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public User.Builder withEmail(String email) {
            newUser.email = email;
            return this;
        }

        public User.Builder withPassword(String password) {
            newUser.password = password;
            return this;
        }

        public User.Builder withPhone(String phone) {
            newUser.phone = phone;
            return this;
        }

        public User.Builder withUserStatus(Integer userStatus) {
            newUser.userStatus = userStatus;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
