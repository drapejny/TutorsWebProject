package by.slizh.tutorsweb.model.entity;

import java.time.LocalDate;

public class Feedback extends Entity {
    private int feedbackId;
    private String text;
    private LocalDate date;
    private int rating;
    private int userId;
    private int tutorId;

    public Feedback() {
    }

    public Feedback(int feedbackId, String text, LocalDate date, int rating, int userId, int tutorId) {
        this.feedbackId = feedbackId;
        this.text = text;
        this.date = date;
        this.rating = rating;
        this.userId = userId;
        this.tutorId = tutorId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public static class FeedbackBuilder {
        Feedback feedback;

        public FeedbackBuilder() {
            feedback = new Feedback();
        }

        public FeedbackBuilder setFeedbackId(int feedbackId) {
            feedback.setFeedbackId(feedbackId);
            return this;
        }

        public FeedbackBuilder setText(String text) {
            feedback.setText(text);
            return this;
        }

        public FeedbackBuilder setDate(LocalDate date) {
            feedback.setDate(date);
            return this;
        }

        public FeedbackBuilder setRating(int rating) {
            feedback.setRating(rating);
            return this;
        }

        public FeedbackBuilder setUserId(int userId) {
            feedback.setUserId(userId);
            return this;
        }

        public FeedbackBuilder setTutorId(int tutorId) {
            feedback.setTutorId(tutorId);
            return this;
        }

        public Feedback createFeedback() {
            return feedback;
        }
    }
}