package by.slizh.tutorsweb.model.entity;

public class Tutor extends User {
    private int tutorId;
    private String phone;
    private String city;
    private String education;
    private String info;
    private int pricePerHour;
    private boolean isActive;

    public Tutor() {
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Tutor tutor = (Tutor) o;
        return tutorId == tutor.tutorId &&
                tutor.phone == null ? phone == null : phone.equals(tutor.phone) &&
                tutor.city == null ? city == null : city.equals(tutor.city) &&
                tutor.education == null ? education == null : education.equals(tutor.education) &&
                tutor.info == null ? info == null : info.equals(tutor.info) &&
                pricePerHour == tutor.pricePerHour &&
                isActive == tutor.isActive;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tutorId;
        result = 31 * result + (phone == null ? 0 : phone.hashCode());
        result = 31 * result + (city == null ? 0 : city.hashCode());
        result = 31 * result + (education == null ? 0 : education.hashCode());
        result = 31 * result + (info == null ? 0 : info.hashCode());
        result = 31 * result + pricePerHour;
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("Tutor{");
        stringBuilder.append("userId=").append(getUserId());
        stringBuilder.append(", firstName='").append(getFirstName()).append("'");
        stringBuilder.append(", lastName='").append(getLastName()).append("'");
        stringBuilder.append(", email='").append(getEmail()).append("'");
        stringBuilder.append(", photo='").append(getPhoto()).append("'");
        stringBuilder.append(", role=").append(getRole());
        stringBuilder.append(", status=").append(getStatus());
        stringBuilder.append(", tutorId=").append(tutorId);
        stringBuilder.append(", phone='").append(phone).append("'");
        stringBuilder.append(", city='").append(city).append("'");
        stringBuilder.append(", education='").append(education).append("'");
        stringBuilder.append(", info='").append(info).append("'");
        stringBuilder.append(", pricePerHour=").append(pricePerHour);
        stringBuilder.append(", isActive=").append(isActive).append("}");
        return stringBuilder.toString();
    }


    public static class TutorBuilder {

        Tutor tutor;

        public TutorBuilder() {
            tutor = new Tutor();
        }

        public TutorBuilder setUserId(int userId) {
            tutor.setUserId(userId);
            return this;
        }

        public TutorBuilder setFirstName(String firstName) {
            tutor.setFirstName(firstName);
            return this;
        }

        public TutorBuilder setLastName(String lastName) {
            tutor.setLastName(lastName);
            return this;
        }

        public TutorBuilder setEmail(String email) {
            tutor.setEmail(email);
            return this;
        }

        public TutorBuilder setCity(String city) {
            tutor.setCity(city);
            return this;
        }

        public TutorBuilder setPhoto(String photo) {
            tutor.setPhoto(photo);
            return this;
        }

        public TutorBuilder setRole(Role role) {
            tutor.setRole(role);
            return this;
        }

        public TutorBuilder setStatus(Status status) {
            tutor.setStatus(status);
            return this;
        }

        public TutorBuilder setTutorId(int tutorId) {
            tutor.setTutorId(tutorId);
            return this;
        }

        public TutorBuilder setPhone(String phone) {
            tutor.setPhone(phone);
            return this;
        }

        public TutorBuilder setEducation(String education) {
            tutor.setEducation(education);
            return this;
        }

        public TutorBuilder setInfo(String info) {
            tutor.setInfo(info);
            return this;
        }

        public TutorBuilder setPricePerHour(int pricePerHour) {
            tutor.setPricePerHour(pricePerHour);
            return this;
        }

        public TutorBuilder setIsActive(boolean isActive) {
            tutor.setIsActive(isActive);
            return this;
        }

        public Tutor createTutor() {
            return tutor;
        }
    }
}
