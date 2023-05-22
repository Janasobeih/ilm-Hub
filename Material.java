import java.util.Vector;

public class Material {
    private String lecsPdf;
    private String quizzes;
    private String exam;
public Material(){
    lecsPdf="N/A";
    quizzes="N/A";
    exam="N/A";
}
    public String getLecsPdf() {
        return lecsPdf;
    }

    public void setLecsPdf(String lecpdf) {
        lecsPdf=lecpdf;
    }

    public String getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(String quiz) {
      quizzes=quiz;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String Exam) {
       exam=Exam;
    }
}
