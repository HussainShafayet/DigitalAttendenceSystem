package ahmed.kawsar.firebase_data;

public class Student {

    private String date,subject,attendance,deptsession;

    public Student(){

    }

    public Student(String date,String subject,String deptsession, String attendance) {

        this.date = date;
        this.attendance = attendance;
        this.subject = subject;
       this.deptsession=deptsession;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getDeptsession() {
        return deptsession;
    }

    public void setDeptsession(String deptsession) {
        this.deptsession = deptsession;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
