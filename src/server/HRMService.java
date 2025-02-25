package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class HRMService extends UnicastRemoteObject implements HRMServiceInterface {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Employee> employees;

    // Constructor
    protected HRMService() throws RemoteException {
        super();
        employees = new ArrayList<>(); // Temporary in-memory storage
    }

    @Override
    public boolean authenticateEmployee(String empId, String password) throws RemoteException {
        for (Employee emp : employees) {
            if (emp.getEmployeeId().equals(empId) && emp.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean registerEmployee(String employeeId, String firstName, String lastName, String icPassport, String password, String familyDetails, int leaveBalance) throws RemoteException {
        Employee employee = new Employee(employeeId, firstName, lastName, icPassport, password, familyDetails, leaveBalance);
        employees.add(employee);
        return true;
    }

    @Override
    public Employee getEmployeeDetails(String employeeId) throws RemoteException {
        for (Employee emp : employees) {
            if (emp.getEmployeeId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    @Override
    public boolean updateEmployeeProfile(String employeeId, String newFirstName, String newLastName) throws RemoteException {
        for (Employee emp : employees) {
            if (emp.getEmployeeId().equals(employeeId)) {
                emp.setFirstName(newFirstName);
                emp.setLastName(newLastName);
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLeaveBalance(String employeeId) throws RemoteException {
        Employee emp = getEmployeeDetails(employeeId);
        return (emp != null) ? emp.getLeaveBalance() : -1;
    }

    @Override
    public boolean applyLeave(String empId, String startDate, String endDate) throws RemoteException{
        return false;
    }

    @Override
    public String getLeaveHistory(String empId) throws RemoteException{
        return null;
    }
}
