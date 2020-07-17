package service;

import dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Emp;

@Service
public class EmpService {
    @Autowired
    private EmpDao empDao;
    @Transactional
    public Emp findUserByuserName(String userName) throws RuntimeException{
        Emp emp = empDao.findUserByuserName(userName);
        return emp;
    }
}
