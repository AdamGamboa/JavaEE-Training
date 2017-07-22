package com.training.javaee.ejb.web.language;

import com.training.javaee.ejb.domain.Language;
import com.training.javaee.ejb.services.ServicioConAsincronos;
import com.training.javaee.ejb.services.ServicioLanguage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LanguageList", urlPatterns = {"/language/list"})
public class LanguageList extends HttpServlet {

    @Inject
    private ServicioLanguage servicioLanguage;
    
    @Inject 
    private ServicioConAsincronos servicioConAsincronos;
    
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
        
        //Metodo asincrono sin return
        this.servicioConAsincronos.longExecuteMethod();
        //Metodo asincrono con return
        Future<String> futuro = this.servicioConAsincronos.longExecuteMethodWithReturn();
        //Los metodos siguen ejecutandose en segundo plano
        //mientras se continua con el flujo de este metodo en el servlet.
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Idiomas</title>"); 
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Idiomas</h1>");
            out.println("<table cellspacing='0'><tr><th>Nombre</th></tr>");
            try{
                List<Language> lenguajes = this.servicioLanguage.consultar();
                for(Language l : lenguajes){
                    out.println("<tr><td>"+l.getName()+"</td></tr>");
                }
            }catch(Exception ex){
                out.println("<tr><td>"+ex.getMessage()+"</td></tr>");
                ex.printStackTrace();
            }
            out.println("</table>");
            out.println("<a href='/ejb-web/index.html'>Regresar</a>");
            try {
                String mensaje = futuro.get();
                out.println("<br/>"+mensaje);
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(LanguageList.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
        }
        response.setContentType("text/html;charset=UTF-8");
        
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
