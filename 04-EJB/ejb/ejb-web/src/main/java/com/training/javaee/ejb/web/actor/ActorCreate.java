package com.training.javaee.ejb.web.actor;

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
@WebServlet(name = "ActorCreate", urlPatterns = {"/actor/create"})
public class ActorCreate extends HttpServlet {
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crear Actor</title>");   
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Crear actor</h1>");
            out.println("<form method='post' action='create' >");
            out.println("Nombre: <input type='text' name='name'><br/>");
            out.println("Apellido: <input type='text' name='lastname'><br/>");
            out.println("<input type='submit' value='Guardar'>");
            out.println("</form>");
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
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Crear Actor</title>");   
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Crear actor</h1>");
            if(name == null || lastname == null || name.isEmpty() || lastname.isEmpty()){
                out.println("<span style='color:red'> Nombre y Apellido son requeridos</span>");
            }
            else{
                try{
                    this.servicioActor.guardar(name, lastname);
                    out.println("<span style='color:green'>Actor creado correctamente</span>");
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
