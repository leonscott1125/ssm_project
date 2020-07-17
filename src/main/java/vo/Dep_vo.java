package vo;

public class Dep_vo {
    private int depId;
    private int empAmount;
    private String depName;
    private double avgSalary;

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getEmpAmount() {
        return empAmount;
    }

    public void setEmpAmount(int empAmount) {
        this.empAmount = empAmount;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }
}
