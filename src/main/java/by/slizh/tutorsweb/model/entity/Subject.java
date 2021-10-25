package by.slizh.tutorsweb.model.entity;

public class Subject extends AbstractEntity {
    private int subjectId;
    private String subjectName;

    public Subject() {
    }

    public Subject(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Subject subject = (Subject) o;
        return subject.subjectId == subjectId &&
                subject.subjectName == null ? subjectName == null : subject.subjectName.equals(subjectName);
    }

    @Override
    public int hashCode() {
        int result = subjectId;
        result = 31 * result + (subjectName == null ? 0 : subjectName.hashCode());
        return result;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("Subject{");
        stringBuilder.append("subjectId=").append(subjectId);
        stringBuilder.append(", subjectName='").append(subjectName).append("'}");
        return stringBuilder.toString();
    }
}
