import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class FileVectorHandler {
    public void readTraineesIntoVector(Vector<Trainee> vector) {
        File file= new File("trainees.txt");
            try {
                vector.clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String name = scanner.nextLine();
                    String email = scanner.nextLine();
                    String password = scanner.nextLine();
                    Trainee trainee = new Trainee();
                    trainee.setName(name);
                    trainee.setEmail(email);
                    trainee.setPassword(password);
                    vector.add(trainee);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + file.getName());
            }
        }
        public void writeTraineesIntoFile(Vector<Trainee> vector, String filename) {
        try {
            // Clear the file's contents
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");
            raf.setLength(0);
            raf.close();

            // Write the new data
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            for (Trainee trainee : vector) {
                writer.write(trainee.getName() + "\n");
                writer.write(trainee.getEmail() + "\n");
                writer.write(trainee.getPassword() + "\n");
            }
            writer.close();
            System.out.println("Data written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public void readTraineesCourses(Vector<Trainee> vector, File file) {
        vector.clear();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Course traineecourse=new Course();
                String email = scanner.nextLine();
                String CourseName = scanner.nextLine();
                traineecourse.setName(CourseName);
                Trainee trainee = new Trainee();
                trainee.Courses.add(99,traineecourse);
                trainee.setEmail(email);
                vector.add(trainee);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
        }
    }

    public void writeTraineesCourses(Vector<Trainee> vector, String filename) {
        try {
            // Clear the file's contents
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");
            raf.setLength(0);
            raf.close();

            // Write the new data
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            for (Trainee trainee : vector) {

                writer.write(trainee.getEmail() + "\n");
                writer.write(trainee.Courses.get(99).getName() + "\n");
            }
            writer.close();
            System.out.println("Data written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void readCoursesIntoVector(Vector<Course> courses, File file) {
        courses.clear();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Material material = new Material();
                String name = scanner.nextLine();
                String AcCode = scanner.nextLine();
                String Link1 = scanner.nextLine();
                String Link2 = scanner.nextLine();
                String Link3 = scanner.nextLine();
                material.setLecsPdf(Link1);
                material.setQuizzes(Link2);
                material.setExam(Link3);
                Course course = new Course();
                course.setName(name);
                course.setAccessCode(AcCode);
                course.setMaterial(material);
                courses.add(course);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
        }
    }

    public void writeCoursesIntoFile(Vector<Course> vector, String filename) {
        try {
            // Clear the file's contents
            RandomAccessFile raf = new RandomAccessFile(filename, "rw");
            raf.setLength(0);
            raf.close();

            // Write the new data
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            for (Course course : vector) {
                writer.write(course.getName() + "\n");
                writer.write(course.getAccessCode() + "\n");
                writer.write(course.getMaterial().getLecsPdf() + "\n");
                writer.write(course.getMaterial().getQuizzes() + "\n");
                writer.write(course.getMaterial().getExam() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

