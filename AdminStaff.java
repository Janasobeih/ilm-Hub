import java.util.Vector;
import java.util.*;
//course search,course enroll,course withdraw,display my courses and course interaction
public class AdminStaff extends User
{
    Vector<Course> courses=new Vector<>();
    public void AddCourse(Course course)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Course Name: ");
        String crsname=sc.nextLine();
        course.setName(crsname);
        System.out.println("Course Code: ");
        String crsCode=sc.nextLine();
        course.setAccessCode(crsCode);
        System.out.println("Would you like to add the course Materials now? 1.Yes 2.No");
        String choice=sc.nextLine();
        if (choice.equals("1"))
        {
            Material material= new Material();
            String Link,Choice;
            Choice="0";
            while(!(Choice.equals("4"))) {
                System.out.println("1-To add letures Links \n 2-To add Quiz links \n 3-To add Exam links \n 4-Exit");
                Choice=sc.nextLine();
                if (Choice.equals("1"))
                {
                    System.out.println("Enter the link: ");
                    Link = sc.nextLine();
                    material.setLecsPdf(Link);

                }
                else if (Choice.equals("2"))
                {
                    System.out.println("Enter the link: ");
                    Link = sc.nextLine();
                    material.setQuizzes(Link);

                }
                else if (Choice.equals("3"))
                {
                    System.out.println("Enter the link: ");
                    Link = sc.nextLine();
                    material.setExam(Link);
                }
                System.out.println("Link Added successfully!");
                course.setMaterial(material);
            }
            System.out.println("Course Added!");
        }
        else if(choice.equals("2")){
            Material material=new Material();
            course.setMaterial(material);
        }
    }
    public Vector<Course> getCourses(){
        return courses;
    }
}
