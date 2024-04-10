import java.util.*;
import java.io.*;

public class EmployeeDA {
    private HashMap<String, Employee> employeeMap;
    private Employee employee;


    public EmployeeDA(String empNo){

        try {
            Scanner employeeFile = new Scanner(new FileReader("emp.csv"));
            employeeMap = new HashMap<>();
            employeeFile.nextLine();

            while (employeeFile.hasNext()) {

                String employeeLineData;
                employeeLineData = employeeFile.nextLine();

                String[] employeeLineDataSpecific;
                employeeLineDataSpecific = employeeLineData.split(",");

                if (empNo.equals(employeeLineDataSpecific[0].trim())) {

                    employee = new Employee();
                    employee.setEmpNo(employeeLineDataSpecific[0].trim());
                    employee.setLastName(employeeLineDataSpecific[1].trim());
                    employee.setFirstName(employeeLineDataSpecific[2].trim());



                }

            }
            employeeFile.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Employee getEmployee() {
        return employee;
    }
}