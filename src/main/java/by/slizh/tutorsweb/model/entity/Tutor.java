package by.slizh.tutorsweb.model.entity;

import java.io.InputStream;
import java.math.BigDecimal;

public class Tutor extends User {
    private int tutorId;
    private String phone;
    private String education;
    private String info;
    private BigDecimal pricePerHour;
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

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

        public TutorBuilder setPhoto(InputStream photo) {
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

        public TutorBuilder setPhone(String phone){
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

        public TutorBuilder setPricePerHour(BigDecimal pricePerHour) {
            tutor.setPricePerHour(pricePerHour);
            return this;
        }

        public TutorBuilder setActive(boolean active) {
            tutor.setActive(active);
            return this;
        }

        public Tutor createTutor() {
            return tutor;
        }
    }
}
