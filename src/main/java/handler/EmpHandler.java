package handler;

import core.MyRequest;
import dto.TransferObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Emp;
import service.EmpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class EmpHandler {
    @Autowired
    private EmpService empService;

    @GetMapping("login")
    public TransferObj login(String userName, String password, HttpServletRequest req){

//        System.out.println("进入了handler");
        TransferObj transferObj = new TransferObj();
//        System.out.println("login");
//        System.out.println(empService);
        Emp emp = empService.findUserByuserName(userName);
//        MyRequest request = (MyRequest) req;

        HttpSession session = req.getSession();
//        System.out.println(session.getId());
        if(emp != null){
            if(emp.getPassword().equals(password)){
                session.setAttribute("loginUser", emp);
                transferObj.setMessage("success");
            }
            else{
                transferObj.setCode(1);
                transferObj.setMessage("密码错误！");
            }
        }
        else{
            transferObj.setCode(1);
            transferObj.setMessage("用户名不存在！");
        }
        return transferObj;
    }

    @GetMapping("loginUser")
    public Emp showLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        Emp emp = (Emp) session.getAttribute("loginUser");
        return emp;
    }

}
