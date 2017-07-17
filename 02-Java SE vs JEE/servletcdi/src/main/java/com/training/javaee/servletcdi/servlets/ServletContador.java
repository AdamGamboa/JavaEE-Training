/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.javaee.servletcdi.servlets;

import com.training.javaee.servletcdi.managedbeans.BeanContador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author training
 */
@WebServlet(urlPatterns = "/contador")
public class ServletContador extends HttpServlet{
    
    @Inject
    private BeanContador beanContador;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Contador de imagenes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Contador de Visualizaciones</h1>");
            out.println("Se han visualizado un total de "+beanContador.getContador()+" imagenes");
            out.println("<br/><a href='index.html'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        this.processRequest(request, response);
    }
    
}
