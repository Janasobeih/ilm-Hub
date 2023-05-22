import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class CourseManager
{
    public Boolean addCourse(Course course,String Email) throws IOException
    {
        try
        {
        FileWriter traineecourses = new FileWriter("traineescourses.txt", true);
        traineecourses.write(Email+ "\n");
        traineecourses.write(course.getName() + "\n");
        traineecourses.close();
        return true;
        }
        catch(IOException ex)
        {
            return false;
        }
    }
    public void WithdrawCourse(Trainee tr,  Vector<Trainee>traineesCourses)
    {


            traineesCourses.remove(tr);

    }
    public void InteractWithCourse(String courseName,Vector<Course>AdminCourses)
    {
            for(int i=0;i<AdminCourses.size();i++)
            {
                if(AdminCourses.get(i).getName().equals(courseName))
                {
                    System.out.println("Course materials: ");
                    System.out.println("Lectures link: "+AdminCourses.get(i).getMaterial().getLecsPdf());
                    System.out.println("Quizzes link: "+AdminCourses.get(i).getMaterial().getQuizzes());
                    System.out.println("Exams link: "+AdminCourses.get(i).getMaterial().getExam());
                    break;
                }
        }
    }

    public Course SearchCourse(String search,Vector<Course>courses)
    {
        Boolean found=false;
        Course course=new Course();
        for(int i=0;i< courses.size();i++)
        {
            if(courses.get(i).getName().equals(search)){
                found=true;
                course = courses.get(i);

            }
        }
        if(!found){
            System.out.println("No course found.");
        }
        return course;
    }
}
