package com.example.odatav4.controller;

import com.example.odatav4.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/odata")
@Slf4j
public class Odata4Controller extends DispatcherServlet {

    private DemoService service;

    public Odata4Controller(DemoService service) {
        this.service = service;
    }

    @RequestMapping(value = "/*")
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service.executeService(request, response);
    }

}
