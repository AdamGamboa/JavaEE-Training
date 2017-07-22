package com.training.javaee.ejb.web.actor;

import com.training.javaee.ejb.domain.Actor;
import com.training.javaee.ejb.services.ServicioActor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "ActorEdit", urlPatterns = {"/actor/edit"})
public class ActorEdit extends HttpServlet {
    
    @Inject
    private ServicioActor servicioActor;


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
        Object id = request.getParameter("actorId");
        Integer actorId = id == null ? -1 : Integer.parseInt(id.toString());
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Editar Actor</title>");   
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Editar actor</h1>");
            try{
                Actor actor = this.servicioActor.buscar(actorId);
                if(actor != null){
                    out.println("<form method='post' action='edit' >");
                    out.println("<input type='hidden' name='actorId' value='"+actor.getActorId()+"'>");
                    out.println("Nombre: <input type='text' name='name' value='"+actor.getFirstName()+"'><br/>");
                    out.println("Apellido: <input type='text' name='lastname' value='"+actor.getLastName()+"'><br/>");
                    out.println("<input type='submit' value='Guardar'>");
                    out.println("</form>");
                }else{
                    out.println("<span style='color:red'>No se ha encontrado el actor seleccionado</span>");
                }
            }catch(Exception ex){
                out.println("<span style='color:red'>"+ex.getMessage()+"</span>");
            }
            
            out.println("<br/><br/><a href='list'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        }
        response.setContentType("text/html;charset=UTF-8");
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
        String id = request.getParameter("actorId");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Editar Actor</title>");   
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Editar actor</h1>");
            if(name == null || lastname == null || name.isEmpty() || lastname.isEmpty()){
                out.println("<span style='color:red'> Nombre y Apellido son requeridos</span>");
            }
            else{
                try{
                    Actor actorEditar = new Actor();
                    actorEditar.setActorId(Integer.parseInt(id));
                    actorEditar.setFirstName(name);
                    actorEditar.setLastName(lastname);
                    actorEditar.setLastUpdate(new Date());
                    this.servicioActor.modificar(actorEditar);
                    out.println("<span style='color:green'>Actor modificado correctamente</span>");
                }catch(Exception ex){
                    out.println("<span style='color:red'>"+ex.getMessage()+"</span>");
                }
            }
            out.println("<br/><br/><a href='list'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
        }
        response.setContentType("text/html;charset=UTF-8");
        
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
