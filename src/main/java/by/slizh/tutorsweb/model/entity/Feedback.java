package by.slizh.tutorsweb.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Feedback extends Entity {
    private int feedbackId;
    private String text;
    private LocalDate date;
    private int rating;
    private int userId;
    private int tutorId;

    public Feedback() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Feedback feedback = (Feedback) o;
        return feedback.feedbackId == feedbackId &&
                feedback.text == null ? text == null : feedback.text.equals(text) &&
                feedback.date == null ? date == null : feedback.date.equals(date) &&
                feedback.rating == rating &&
                feedback.userId == userId &&
                feedback.tutorId == tutorId;
    }

    @Override
    public int hashCode() {
        int result = feedbackId;
        result = 31 * result + (text == null ? 0 : text.hashCode());
        result = 31 * result + (date == null ? 0 : date.hashCode());
        result = 31 * result + rating;
        result = 31 * result + userId;
        result = 31 * result + tutorId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Feedback{");
        stringBuilder.append("feedbackId=").append(feedbackId);
        stringBuilder.append(", text='").append(text).append("'");
        stringBuilder.append(", date=").append(date);
        stringBuilder.append(", rating=").append(rating);
        stringBuilder.append(", userId=").append(userId);
        stringBuilder.append(", tutorId=").append(tutorId).append("}");
        return stringBuilder.toString();
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
