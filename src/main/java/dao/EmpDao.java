package dao;

import pojo.Emp;

import java.util.List;

public interface EmpDao {

    Emp findUserByuserName(String userName);

    List<Emp> findEmpByDepId(int depId);
}
