package core;

import javax.servlet.http.*;
import java.util.UUID;

public class MyRequest extends HttpServletRequestWrapper {
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    public MyRequest(HttpServletRequest request, HttpServletResponse response) {
        super(request);
        this.httpServletRequest = request;
        this.httpServletResponse = response;
    }

    @Override
    public HttpSession getSession() {
        //先获取cookies；
        Cookie[] cookies =httpServletRequest.getCookies();
        String sessionId = null;
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mySessionId")){
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        if (sessionId==null){
            sessionId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("mySessionId",sessionId);
            httpServletResponse.addCookie(cookie);
        }
        MySession session = new MySession(sessionId);
        return session;
    }
}
