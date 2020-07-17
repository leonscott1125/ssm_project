package service;

import dao.DepDao;
import dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Dep;

import java.util.List;

@Service
public class DepService {
    @Autowired
    private DepDao depDao;

    @Transactional
    public List<Dep> findDep(Integer depId){
        List<Dep> list = depDao.findDep(null);
        return list;
    }
}
