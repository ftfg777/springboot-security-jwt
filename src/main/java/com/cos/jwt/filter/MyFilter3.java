package com.cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");

        // 토큰 : cos 이걸 만들어줘야 함. id,password정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 그걸 응답을 해준다
        // 요청할 때 마다 header에 Authorization value값으로 토큰을 가지고 옴
        // 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 됨. (RSA, HS256)

        if(req.getMethod().equals("POST")){
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("필터1");

            if(headerAuth.equals("cos")){
                chain.doFilter(req, res); // 이렇게해야 프로그램이 끝나지 않고 체인에 걸려서 넘어감
            }else{
                PrintWriter outPrintWriter = res.getWriter();
                outPrintWriter.println("인증안됨");
            }
        }
    }
}
