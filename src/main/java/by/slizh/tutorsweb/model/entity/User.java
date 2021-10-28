package by.slizh.tutorsweb.model.entity;

/**
 * The User entity class.
 */
public class User extends AbstractEntity {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private Role role;
    private Status status;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Role {
        ADMIN("admin", 1),
        USER("user", 2),
        TUTOR("tutor", 3),
        GUEST("guest", 0);

        private String value;
        private int id;

        Role(String value, int id) {
            this.value = value;
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public int getId() {
            return id;
        }
    }

    public enum Status {
        ACTIVATED("activated", 1),
        NON_ACTIVATED("non_activated", 2),
        BLOCKED("blocked", 3);

        private String value;
        private int id;

        Status(String value, int id) {
            this.value = value;
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public int getId() {
            return id;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        User user = (User) o;

        return user.userId == userId &&
                user.firstName == null ? firstName == null : firstName.equals(user.firstName) &&
                user.lastName == null ? lastName == null : lastName.equals(user.lastName) &&
                user.email == null ? email == null : email.equals(user.email) &&
                user.photo == null ? photo == null : photo.equals(user.photo) &&
                user.role == null ? role == null : role.equals(user.role) &&
                user.status == null ? status == null : status.equals(user.status);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        result = 31 * result + (email == null ? 0 : email.hashCode());
        result = 31 * result + (photo == null ? 0 : photo.hashCode());
        result = 31 * result + (role == null ? 0 : role.hashCode());
        result = 31 * result + (status == null ? 0 : status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("User{");
        stringBuilder.append("userId=").append(userId);
        stringBuilder.append(", firstName='").append(firstName).append("'");
        stringBuilder.append(", lastName='").append(lastName).append("'");
        stringBuilder.append(", email='").append(email).append("'");
        stringBuilder.append(", photo='").append(photo).append("'");
        stringBuilder.append(", role=").append(role);
        stringBuilder.append(", status=").append(status).append("}");
        return stringBuilder.toString();
    }

    public static class UserBuilder {
        User user;

        public UserBuilder() {
            user = new User();
        }

        public UserBuilder setUserId(int userId) {
            user.setUserId(userId);
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder setPhoto(String photo) {
            user.setPhoto(photo);
            return this;
        }

        public UserBuilder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public UserBuilder setStatus(Status status) {
            user.setStatus(status);
            return this;
        }

        public User createUser() {
            return user;
        }
    }
}
