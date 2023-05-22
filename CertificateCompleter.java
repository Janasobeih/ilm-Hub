import java.io.File;
import java.util.Vector;

public class CertificateCompleter {
    public Boolean Completer(Trainee t, File file, Vector<Trainee> traineesCourses, String crsName) {
        FileVectorHandler Reader = new FileVectorHandler();
        Reader.readTraineesCourses(traineesCourses, file);
        Boolean exists = false;
        for (int i = 0; i < traineesCourses.size(); i++) {
            if (traineesCourses.get(i).getEmail().equals(t.getEmail()) && traineesCourses.get(i).Courses.get(99).getName().equals(crsName)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

}
