import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Vector<Trainee> trainees = new Vector<>();
        Vector<AdminStaff> Admins = new Vector<>();
        Vector<Trainee> traineesCourses = new Vector<>();
        FileVectorHandler Reader = new FileVectorHandler();
        FileVectorHandler Writer = new FileVectorHandler();
        File Coursesfile = new File("courses.txt");
        File traineefile = new File("trainees.txt");
        File traineesCoursesfile = new File("traineescourses.txt");
        File Adminsfile = new File("adminstaff.txt");
        FileWriter TraineeFileWriter = new FileWriter("trainees.txt", true);
        FileWriter TraineeCoursesFileWriter = new FileWriter("traineescourses.txt", true);
        FileWriter AdminsFileWriter = new FileWriter("adminstaff.txt", true);
        AdminStaff A = new AdminStaff();
        String UserType;
        String choice;
        boolean inLoop = true;
        Scanner sc = new Scanner(System.in);
        while (inLoop) {
            System.out.println("1-Trainee");
            System.out.println("2-Admin");
            System.out.println("3-Exit");
            UserType = sc.nextLine();
            if (UserType.equals("1")) {
                Trainee T = new Trainee();
                System.out.println("1-Sign up 2-Sign in");
                choice = sc.nextLine();
                if (choice.equals("1")) {
                    T.signUp();
                    try {
                        TraineeFileWriter.write(T.getName() + "\n");
                        TraineeFileWriter.write(T.getEmail() + "\n");
                        TraineeFileWriter.write(T.getPassword() + "\n");
                        TraineeFileWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                } else if (choice.equals("2")) {
                    if (T.signIn("trainees.txt")) {
                        System.out.println("Signed in!");
                        Reader.readTraineesIntoVector(trainees);
                        boolean loopMenu = true;
                        while (loopMenu) {
                            String choicee;
                            System.out.println("1-Edit profile");
                            System.out.println("2-View profile");
                            System.out.println("3-My courses");
                            System.out.println("4-search course");
                            System.out.println("5-Log out");
                            choicee = sc.nextLine();
                            if (choicee.equals("1")) {
                                System.out.println("1-Edit Name");
                                System.out.println("2-Edit Email");
                                System.out.println("3-Edit password");
                                choice = sc.nextLine();
                                ProfileEditor Editor = new ProfileEditor();
                                Editor.EditProfile(trainees, T, choice);
                                Writer.writeTraineesIntoFile(trainees, "trainees.txt");
                            } else if (choicee.equals("2")) {
                                ProfileViewer viewer = new ProfileViewer();
                                viewer.ViewProfile(T);
                            } else if (choicee.equals("3")) {
                                Boolean HaveCourses = false;
                                if (!(Coursesfile.length() == 0)) {
                                    Reader.readCoursesIntoVector(A.courses, Coursesfile);
                                }
                                if (!(traineesCoursesfile.length() == 0)) {
                                    Reader.readTraineesCourses(traineesCourses, traineesCoursesfile);
                                    for (int i = 0; i < traineesCourses.size(); i++) {
                                        if (traineesCourses.get(i).getEmail().equals(T.getEmail())) {
                                            Course course = traineesCourses.get(i).Courses.get(99);
                                            T.Courses.add(course);
                                            HaveCourses = true;
                                        }
                                    }
                                }
                                if (HaveCourses) {
                                    Scanner fileScanner = new Scanner(traineesCoursesfile);
                                    while (fileScanner.hasNextLine()) {
                                        String fileEmail = fileScanner.nextLine();
                                        String name = fileScanner.nextLine();
                                        if (fileEmail.equals(T.getEmail())) {
                                            System.out.println("Course name: " + name);
                                        }
                                    }
                                    System.out.println("1-Withdraw 2-Interact with course 3- Ask for certificate  4- view progress 5-Exit ");
                                    String mycoursechoice = sc.nextLine();
                                    String mycoursename;
                                    Reader.readTraineesCourses(traineesCourses, traineesCoursesfile);
                                    if (mycoursechoice.equals("1")) {
                                        System.out.println("write the name of the course you want to withdraw: ");
                                        mycoursename = sc.nextLine();
                                        CourseManager withdrawer = new CourseManager();
                                        Boolean flag = false;
                                        for (int i = 0; i < traineesCourses.size(); i++) {
                                            if (T.getEmail().equals(traineesCourses.get(i).getEmail()) && mycoursename.equals(traineesCourses.get(i).Courses.get(99).getName())) {
                                                withdrawer.WithdrawCourse(traineesCourses.get(i), traineesCourses);
                                                flag = true;
                                                Writer.writeTraineesCourses(traineesCourses, "traineescourses.txt");
                                                break;
                                            }
                                        }
                                        if (!flag) {
                                            System.out.println("You are not enrolled in this course.");
                                        }
                                    } else if (mycoursechoice.equals("2")) {
                                        Boolean flag = false;
                                        Reader.readCoursesIntoVector(A.courses, Coursesfile);
                                        Reader.readTraineesCourses(traineesCourses, traineesCoursesfile);
                                        System.out.println("Course name: ");
                                        mycoursename = sc.nextLine();
                                        for (int i = 0; i < traineesCourses.size(); i++) {
                                            if (T.getEmail().equals(traineesCourses.get(i).getEmail()) && mycoursename.equals(traineesCourses.get(i).Courses.get(99).getName())) {
                                                flag = true;
                                            }
                                        }
                                        if (flag == true) {
                                            CourseManager interactor = new CourseManager();
                                            interactor.InteractWithCourse(mycoursename, A.courses);
                                        } else {
                                            System.out.println("You have no access to this course.");
                                        }
                                    } else if (mycoursechoice.equals("3")) {
                                        System.out.println("Which course from your courses?");
                                        String crsname = sc.nextLine();
                                        CertificateCompleter C = new CertificateCompleter();
                                        if (C.Completer(T, traineesCoursesfile, traineesCourses, crsname)) {
                                            System.out.println("Congrats! Your certificate will be sent to you on: " + T.getEmail());
                                        } else {
                                            System.out.println("Can't issue your certificate.");
                                        }
                                    }
                                    else if (mycoursechoice.equals("4"))
                                    {
                                        Reader.readTraineesCourses(traineesCourses, traineesCoursesfile);
                                        ProgressTracker tracker = new ProgressTracker();
                                        tracker.ProgressTracking(T, traineesCourses);
                                    }
                                } else
                                {
                                    System.out.println("You have no courses yet.");
                                }
                            } else if (choicee.equals("4")) {
                                File file = new File("courses.txt");
                                Reader.readCoursesIntoVector(A.courses, file);
                                String search;
                                System.out.println("Search: ");
                                search = sc.nextLine();
                                CourseManager searcher = new CourseManager();
                                Course ReturnedCourse = searcher.SearchCourse(search, A.courses);
                                System.out.println("Result: ");
                                System.out.println(ReturnedCourse.getName());
                                System.out.println("Access Code: Hidden.");
                                System.out.println("Materials: You have no access.");
                                if (!(ReturnedCourse.getName().isEmpty())) {
                                    System.out.println("1-Enroll 2-Exit.");
                                    String courseChoice;
                                    courseChoice = sc.nextLine();
                                    if (courseChoice.equals("1")) {
                                        String accessCode;
                                        System.out.println("Access code: ");
                                        accessCode = sc.nextLine();
                                        if (accessCode.equals(ReturnedCourse.getAccessCode())) {
                                            CourseManager adder = new CourseManager();
                                            if (adder.addCourse(ReturnedCourse, T.getEmail()))
                                                System.out.println("Course added to your courses!");
                                            else
                                                System.out.println("Failed to add course.");
                                        } else {
                                            System.out.println("Invalid access code. Failed to enroll.");
                                        }
                                    }
                                }
                            } else if (choicee.equals("5")) {
                                loopMenu = false;
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                }
            } else if (UserType.equals("2")) {
                System.out.println("1-Sign up 2-Sign in");
                choice = sc.nextLine();
                if (choice.equals("1")) {
                    A.signUp();
                    try {
                        File file = new File("adminStaff.txt");
                        FileWriter adminsFile = new FileWriter(file, true);
                        adminsFile.write(A.getName() + "\n");
                        adminsFile.write(A.getEmail() + "\n");
                        adminsFile.write(A.getPassword() + "\n");
                        adminsFile.close();
                        System.out.println("Data appended to the file successfully.");
                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file: " + e.getMessage());
                    }
                } else if (choice.equals("2")) {
                    if (A.signIn("adminstaff.txt")) {
                        System.out.println("Signed in!");
                        Boolean loop = true;
                        while (loop) {
                            System.out.println("1-Add Course 2-Edit Course 3-Remove Course 4-Exit");
                            String choicee;
                            choicee = sc.nextLine();
                            Course course = new Course();
                            if (choicee.equals("1")) {
                                FileWriter CoursesWriter = new FileWriter("courses.txt", true);
                                A.AddCourse(course);
                                try {
                                    CoursesWriter.write(course.getName() + "\n");
                                    CoursesWriter.write(course.getAccessCode() + "\n");
                                    CoursesWriter.write(course.getMaterial().getLecsPdf() + "\n");
                                    CoursesWriter.write(course.getMaterial().getQuizzes() + "\n");
                                    CoursesWriter.write(course.getMaterial().getExam() + "\n");
                                    System.out.println("Course written to the file successfully.");
                                } catch (IOException e) {
                                    System.out.println("An error occurred while writing to the file: " + e.getMessage());
                                } finally {
                                    CoursesWriter.close();
                                }

                            } else if (choicee.equals("2")) {
                                File CoursesFile = new File("courses.txt");
                                FileVectorHandler ReaderToVector = new FileVectorHandler();
                                String EditChoice;
                                String AccessCode;
                                System.out.println("Enter Course Access Code: ");
                                AccessCode = sc.nextLine();
                                System.out.println("1-Edit course name\n2-Edit course Access Code\n3-Edit Material");
                                EditChoice = sc.nextLine();
                                if (EditChoice.equals("1")) {
                                    ReaderToVector.readCoursesIntoVector(A.courses, CoursesFile);
                                    System.out.println("Enter the new one: ");
                                    String CourseName = sc.nextLine();
                                    for (int i = 0; i < A.courses.size(); i++) {
                                        if (A.courses.get(i).getAccessCode().equals(AccessCode)) {
                                            A.courses.get(i).setName(CourseName);
                                            System.out.println("name Edited Succesfully!");
                                            Writer.writeCoursesIntoFile(A.courses, "courses.txt");
                                            break;

                                        }
                                    }
                                } else if (EditChoice.equals("2")) {
                                    ReaderToVector.readCoursesIntoVector(A.courses, CoursesFile);
                                    System.out.println("Enter the new one: ");
                                    String AccCode = sc.nextLine();
                                    for (int i = 0; i < A.courses.size(); i++) {
                                        if (A.courses.get(i).getAccessCode().equals(AccessCode)) {
                                            A.courses.get(i).setAccessCode(AccCode);
                                            Writer.writeCoursesIntoFile(A.courses, "courses.txt");
                                            break;
                                        }
                                    }
                                } else if (EditChoice.equals("3")) {
                                    String link;
                                    String ch;
                                    Material material = new Material();
                                    System.out.println("1.Edit Pdfs Link\n2.Edit Quizzes link\n3.Edit exams link\n ");
                                    ch = sc.nextLine();
                                    if (ch.equals("1")) {
                                        ReaderToVector.readCoursesIntoVector(A.courses, CoursesFile);
                                        System.out.println("Enter the link: ");
                                        link = sc.nextLine();
                                        for (int i = 0; i < A.courses.size(); i++) {
                                            if (A.courses.get(i).getAccessCode().equals(AccessCode)) {
                                                material.setExam(A.courses.get(i).getMaterial().getExam());
                                                material.setQuizzes(A.courses.get(i).getMaterial().getQuizzes());
                                                material.setLecsPdf(link);
                                                A.courses.get(i).setMaterial(material);
                                                System.out.println("link 1Edited Succesfully!");
                                                Writer.writeCoursesIntoFile(A.courses, "courses.txt");
                                                break;
                                            }
                                        }
                                    } else if (ch.equals("2")) {
                                        ReaderToVector.readCoursesIntoVector(A.courses, CoursesFile);
                                        System.out.println("Enter the link: ");
                                        link = sc.nextLine();
                                        for (int i = 0; i < A.courses.size(); i++) {
                                            if (A.courses.get(i).getAccessCode().equals(AccessCode)) {
                                                material.setExam(A.courses.get(i).getMaterial().getExam());
                                                material.setLecsPdf(A.courses.get(i).getMaterial().getLecsPdf());
                                                material.setQuizzes(link);
                                                A.courses.get(i).setMaterial(material);
                                                System.out.println("link2Edited Succesfully!");
                                                Writer.writeCoursesIntoFile(A.courses, "courses.txt");
                                                break;
                                            }
                                        }
                                    } else if (ch.equals("3")) {
                                        ReaderToVector.readCoursesIntoVector(A.courses, CoursesFile);
                                        System.out.println("Enter the link: ");
                                        link = sc.nextLine();
                                        for (int i = 0; i < A.courses.size(); i++) {
                                            if (A.courses.get(i).getAccessCode().equals(AccessCode)) {
                                                material.setLecsPdf(A.courses.get(i).getMaterial().getLecsPdf());
                                                material.setQuizzes(A.courses.get(i).getMaterial().getQuizzes());
                                                material.setExam(link);
                                                A.courses.get(i).setMaterial(material);
                                                System.out.println("link3 Edited Succesfully!");
                                                Writer.writeCoursesIntoFile(A.courses, "courses.txt");
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (choicee.equals("3")) {
                                File CoursesFile = new File("courses.txt");
                                FileVectorHandler ReaderToVector = new FileVectorHandler();
                                ReaderToVector.readCoursesIntoVector(A.courses, CoursesFile);
                                System.out.println("The access code of the course to remove:");
                                String AccCode = sc.nextLine();
                                for (int i = 0; i < A.courses.size(); i++) {
                                    if (A.courses.get(i).getAccessCode().equals(AccCode)) {
                                        A.courses.remove(A.courses.get(i));
                                        Writer.writeCoursesIntoFile(A.courses, "courses.txt");
                                        break;
                                    }
                                }
                            } else if (choicee.equals("4")) {
                                loop = false;
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                } else if (choice.equals("3")) {

                }
            } else if (UserType.equals("3")) {
                inLoop = false;
            }
        }
    }
}
