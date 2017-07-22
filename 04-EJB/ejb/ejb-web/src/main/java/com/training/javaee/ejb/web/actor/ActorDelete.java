package com.training.javaee.ejb.web.actor;

import com.training.javaee.ejb.domain.Actor;
import com.training.javaee.ejb.services.ServicioActor;
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
@WebServlet(name = "ActorDelete", urlPatterns = {"/actor/delete"})
public class ActorDelete extends HttpServlet {

    @Inject
    private ServicioActor servicioActor;
    
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
        Object parametro = request.getParameter("actorId");
        int actorId = parametro != null? Integer.parseInt(parametro.toString()): -1;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Eliminar actor</title>");     
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Eliminar Actor</h1>");
            Actor actor =  this.servicioActor.buscar(actorId);
            if(actor == null){
                out.println("<span style='color:red'>No se ha encontrado el actor para ser eliminado</span>");
            }else{
                try{
                    this.servicioActor.eliminar(actorId);
                    out.println("Se ha eliminado el actor");
                }catch(Exception ex){
                    out.println("<span style='color:red'>"+ex.getMessage()+"</span>");
                }
            }
            out.println("<br/><br/><a href='list'>Regresar</a>");
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
