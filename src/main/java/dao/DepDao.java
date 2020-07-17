package dao;

import pojo.Dep;

import java.util.List;

public interface DepDao {

    List<Dep> findDep(Integer depId);
}
