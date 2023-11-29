package com.example.odatav4.controller;

import com.example.odatav4.service.Odata4Service;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@RestController
@RequestMapping(value = "/odata")
@Slf4j
public class Odata4Controller implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(Odata4Service.class);

    private final Odata4Service service;

    public Odata4Controller(Odata4Service service) {
        this.service = service;
    }

    @GetMapping(value = "/1")
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.service(req, resp);
        } catch (RuntimeException e) {
            LOG.error("Server Error occurred in ExampleServlet", e);
            throw new ServletException(e);
        }
    }

    @GetMapping(value = "/2")
    public void test() {
        log.info("test");
    }

}
