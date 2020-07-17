package handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Dep;
import pojo.Emp;
import service.DepService;
import vo.Dep_vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("deps")
public class DepHandler {
    @Autowired
    private DepService depService;

    @GetMapping("list")
    public List<Dep> showDep(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session.getId()+"handler");
        List<Dep> deps = null;
        System.out.println("进入了handler");
        deps = (List<Dep>) session.getAttribute("depList");
        System.out.println(deps);
        if (deps==null) {
            deps = depService.findDep(null);
            for (Dep dep : deps) {
                session.setMaxInactiveInterval(60*60);
                System.out.println(dep.getDepId()+"  "+dep.getEmps()+"数据库的dep");
//                List<Emp> emps = dep.getEmps();
//                    for (Emp emp : emps) {
//                    System.out.println(emp.getUserName());
//                }
                session.setAttribute(dep.getDepId()+"",dep.getEmps());
            }
            session.setMaxInactiveInterval(60 * 60 * 24);
            session.setAttribute("depList", deps);
        }else {

            for (Dep dep : deps) {
                System.out.println(dep.getDepId() + "  " + dep.getEmps()+"redis中的dep");
            }
        }
        return deps;
    }

    @GetMapping("info/{depId}")
    public Dep_vo showDeoInfo(int depId,HttpServletRequest request){
        Dep_vo dep_vo =null;
        HttpSession session = request.getSession();
        dep_vo = (Dep_vo) session.getAttribute(""+depId);
        if (dep_vo ==null){

        }
        return dep_vo;
    }
}
