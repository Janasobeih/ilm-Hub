import java.util.Vector;

public class ProgressTracker
{
    Trainee T;
    public void ProgressTracking(Trainee t, Vector<Trainee> traineesCourses)
    {
        T=t;
        for(int i=0;i<traineesCourses.size();i++)
        {
            if(traineesCourses.get(i).getEmail().equals(T.getEmail())){
                int randomNumber = (int) (Math.random() * 100) + 1;
                System.out.println("You finished "+ randomNumber +"%"+" of course: "+traineesCourses.get(i).Courses.get(99).getName());

            }
        }

    }
}
