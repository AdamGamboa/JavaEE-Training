package com.training.javaee.jpa.web.actor;


import com.training.javaee.jpa.domain.Actor;
import com.training.javaee.jpa.services.ServicioActor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "ActorView", urlPatterns = {"/actor/list"})
public class ActorList extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista Actores</title>");
            out.println("<link rel='stylesheet' href='/jpa-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Listado de Actores</h1>");
            out.println("<br/><a href='create'>[Nuevo Actor]</a> <a href='/jpa-web/index.html'>[Regresar]</a>");
            out.println("<table cellspacing='0'><tr><th>Nombre</th><th>Apellido</th><th>Accion</th></tr>");
            try{
                List<Actor> actores = this.servicioActor.consultar();
                for(Actor actor : actores){
                    out.println("<tr>"
                                 + "<td>"+actor.getFirstName()+"</td>"
                                 + "<td>"+actor.getLastName()+"</td>"
                                 + "<td>"
                                    + "<a href='edit?actorId="+actor.getActorId()+"'>[Editar]</a>"
                                    + "<a href='delete?actorId="+actor.getActorId()+"'>[Eliminar]</a>"
                                + "</td>"
                            + "</tr>");
                }
            }catch(Exception ex){
                out.println("<tr><td colspan='2'>"+ex.getMessage()+"</td></tr>");
                ex.printStackTrace();
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
