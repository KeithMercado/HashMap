import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class DepartmentDA {
    private HashMap<String, Employee> employeeMap;
    public HashMap<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public DepartmentDA(){

        try {
            Scanner departmentFile = new Scanner(new FileReader("dep.csv"));

            departmentFile.nextLine();

            while (departmentFile.hasNext()) {

                employeeMap = new HashMap<>();
                String departmentLineData;
                departmentLineData = departmentFile.nextLine();

                String[] departmentLineDataSpecific;
                departmentLineDataSpecific = departmentLineData.split(",");

                Department department = new Department();
                department.setDepCode(departmentLineDataSpecific[0].trim());
                department.setDepName(departmentLineDataSpecific[1].trim());

                readDepEmp(department);

                Double salary = 0.0;
                for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
                    salary += entryMap.getValue().getSalary();
                }
                department.setDepTotalSalary(salary);

                printDepartment(department);

            }
            departmentFile.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void printDepartment(Department department) {
        System.out.println("\nDepartment code: " + department.getDepCode());
        System.out.println("Department name: " + department.getDepName());
        System.out.printf("Department total salary: %.2f%n", department.getDepTotalSalary());
        System.out.println("---------------------Details -------------------------");
        System.out.println("EmpNo          Employee Name           Salary");
        for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
            Employee employee = entryMap.getValue();
            System.out.printf("%-15s%-23s%,10.2f%n", employee.getEmpNo(),
                    employee.getLastName() + ", " + employee.getFirstName(),
                    employee.getSalary());
        }

    }

    private void readDepEmp(Department department) {

        try {
            Scanner deptEmpFile = new Scanner(new FileReader("deptemp.csv"));
            deptEmpFile.nextLine();

            Integer key = 0;
            while (deptEmpFile.hasNext()) {

                String deptEmpLineData;
                deptEmpLineData = deptEmpFile.nextLine();

                String[] deptEmpLineDataSpecific;
                deptEmpLineDataSpecific = deptEmpLineData.split(",");

                if (department.getDepCode().equals(deptEmpLineDataSpecific[0].trim())) {


                    EmployeeDA employeeDA = new EmployeeDA(deptEmpLineDataSpecific[1].trim());
                    department.setEmployeeMap(getEmployeeMap());
                    Employee employee = employeeDA.getEmployee();

                    employee.setSalary(Double.parseDouble(deptEmpLineDataSpecific[2].trim()));

                    employeeMap.put(employee.getEmpNo()+key, employee);
                    key++;

                }
                

            }
            deptEmpFile.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}