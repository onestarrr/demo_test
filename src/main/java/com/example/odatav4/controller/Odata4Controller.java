package com.example.odatav4.controller;

import com.example.odatav4.service.*;
import com.example.odatav4.servlet.DemoServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@RestController
public class Odata4Controller implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);

    private final Odata4Service service;

    public Odata4Controller(Odata4Service service) {
        this.service = service;
    }

    @RequestMapping(value = "/odata4")
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.service(req, resp);
        } catch (RuntimeException e) {
            LOG.error("Server Error occurred in ExampleServlet", e);
            throw new ServletException(e);
        }

    }

}
