//package com.cos.jwt.filter;
//
//import javax.servlet.*;
//import java.io.IOException;
//
//
//public class MyFilter1 implements Filter {
//
//
//    // 시큐리티가 동작하기 전에 필터1이 동작해야 함
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("필터1");
//        chain.doFilter(request, response); // 이렇게해야 프로그램이 끝나지 않고 체인에 걸려서 넘어감
//    }
//}
