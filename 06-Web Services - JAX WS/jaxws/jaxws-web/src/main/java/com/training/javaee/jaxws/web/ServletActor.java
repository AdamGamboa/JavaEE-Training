/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.javaee.jaxws.web;

import com.training.javaee.jaxws.webservices.client.actorws.Actor;
import com.training.javaee.jaxws.webservices.client.actorws.ActorWS;
import com.training.javaee.jaxws.webservices.client.actorws.WebserviceActor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author training
 */
@WebServlet(name = "ServletActor", urlPatterns = {"/actor/list"})
public class ServletActor extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Actores</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Listado de Actores </h1>");
            out.println("<table> <tr><th>Nombre</th><th>Apellido</th></tr>");
            
            try{
                ActorWS actorWS = new ActorWS();
                WebserviceActor wsActor = actorWS.getWebserviceActorPort();
                List<Actor> actores = wsActor.consultar();
                for(Actor actor : actores){
                    out.println("<tr><td>"+actor.getNombre()+"</td><td>"+actor.getApellido()+"</td></tr>");
                }
            }catch(Exception ex){
                ex.printStackTrace();
                out.println("<tr><td colspan='2'>Ha ocurrido un problema</td></tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
