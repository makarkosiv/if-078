import java.lang.reflect.Array;
import java.util.*;

public class RegistrationMedicalBook {
    private static List<Patient> medicalBook = new ArrayList<>();
    private static List<Doctor> personnel = new ArrayList<>();
    static {
        personnel = Arrays.asList(new Doctor("Petro", Department.SURGICAL),
                new Doctor("Danylo", Department.INFECTIOUS), new Doctor("Pavlo", Department.TRAUMA),
                new Doctor("Ivan", Department.GYNECOLOGICAL), new Doctor("Mykola", Department.NEUROLOGICAL),
                new Doctor("Myhailo", Department.THERAPEUTIC));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = "START";
        while (!command.equals("END")){
            System.out.println("Input your command: ");
            command = scanner.nextLine();
            Operation operation = null;
            try {
                operation = Operation.valueOf(command);
            } catch (IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
            }
            try {
                switch (operation) {
                    case ADD:
                        System.out.println("Input name of the patient: ");
                        Patient patient = new Patient();
                        patient.setName(scanner.nextLine());
                        System.out.println("Input diagnosis of the patient: ");
                        patient.setDiagnosis(scanner.nextLine());
                        System.out.println("Input the name of the designated doctor: ");
                        String doctorName = scanner.nextLine();
                        for (Doctor doctor : personnel) {
                            if (doctor.getName().equals(doctorName)) {
                                patient.setDoctor(doctor);
                                break;
                            }
                        }
                        if (patient.getDoctor() == null) {
                            System.out.println("The name of the designated doctor is invalid.");
                        } else {
                            medicalBook.add(patient);
                        }
                        break;
                    case REMOVE:
                        System.out.println("Input the name of the patient for remove: ");
                        String name = scanner.nextLine();
                        boolean removing = false;
                        for (Patient patient1 : medicalBook) {
                            if (patient1.getName().equals(name)) {
                                removing = medicalBook.remove(patient1);
                                break;
                            }
                        }
                        if (!removing) {
                            System.out.println("The name of the patient is invalid.");
                        }
                        break;
                    case CLEAN:
                        medicalBook.clear();
                        break;
                    case LIST:
                        System.out.println(medicalBook.toString());
                        break;
                    default:
                        System.out.println("There was mistake.");
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                e.printStackTrace();
            }
            System.out.println("Number of the position in the registration medical book is: " + medicalBook.size());
        }
    }
}
