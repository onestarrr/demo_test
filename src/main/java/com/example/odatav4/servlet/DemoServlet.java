/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.example.odatav4.servlet;

import com.example.odatav4.data.Storage;
import com.example.odatav4.service.*;
import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DemoServlet", urlPatterns = "/demo")
public class DemoServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);


  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    OData odata = OData.newInstance();
    ServiceMetadata edm = odata.createServiceMetadata(new DemoEdmProvider(), new ArrayList<EdmxReference>());

    try {
      HttpSession session = req.getSession(true);
      Storage storage = (Storage) session.getAttribute(Storage.class.getName());
      if (storage == null) {
        storage = new Storage(odata, edm.getEdm());
        session.setAttribute(Storage.class.getName(), storage);
      }

      // create odata handler and configure it with EdmProvider and Processor
      ODataHttpHandler handler = odata.createHandler(edm);
      handler.register(new DemoEntityCollectionProcessor(storage));
      handler.register(new DemoEntityProcessor(storage));
      handler.register(new DemoPrimitiveProcessor(storage));
      handler.register(new DemoActionProcessor(storage));
      handler.register(new DemoBatchProcessor(storage));

      // let the handler do the work
      handler.process(req, resp);
    } catch (RuntimeException e) {
      LOG.error("Server Error occurred in ExampleServlet", e);
      throw new ServletException(e);
    }

  }

}