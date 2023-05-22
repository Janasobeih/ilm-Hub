import java.util.*;
public class Trainee extends User {
    public Vector<Course> Courses= new Vector<>();
    public Trainee()
    {

        Courses.setSize(100);
    }

    public Vector <Course> getCourses(){ return Courses;}
}
