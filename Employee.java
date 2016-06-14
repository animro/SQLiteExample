package employee.tcs.com.employeedetails;

/**
 * Created by 1256104 on 6/14/2016.
 */
public class Employee {

    String name;
    String extn;
    String dept;
    String mobileno;

    public Employee(String dept, String extn, String mobileno, String name) {
        this.dept = dept;
        this.extn = extn;
        this.mobileno = mobileno;
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setExtn(String extn) {
        this.extn = extn;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public String getExtn() {
        return extn;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getName() {
        return name;
    }
}
