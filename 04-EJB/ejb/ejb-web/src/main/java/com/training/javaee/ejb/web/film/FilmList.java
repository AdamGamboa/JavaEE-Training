package com.training.javaee.ejb.web.film;

import com.training.javaee.ejb.domain.Film;
import com.training.javaee.ejb.services.ServicioFilm;
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
@WebServlet(name = "FilmList", urlPatterns = {"/film/list"})
public class FilmList extends HttpServlet {
    
    
    @Inject
    private ServicioFilm servicioFilm;
    
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
            out.println("<title>Listado de Peliculas</title>");            
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Listado de Peliculas</h1>");
            out.println("<br/><a href='/ejb-web/index.html'>Regresar</a>");
            out.println("<table cellspacing='0'><tr><th>Titulo</th><th>Descripcion</th><th>Ano</th><th>Acción</th></tr>");
            try{
                List<Film> films = this.servicioFilm.consultar();
                for(Film film : films){
                    out.println("<tr>"
                            + "<td>"+film.getTitle()+"</td>"
                            + "<td>"+film.getDescription()+"</td>"
                            + "<td>"+film.getReleaseYear()+"</td>"
                            + "<td> <a href='view?filmId="+film.getFilmId()+"'>Ver Detalle</a></td>"
                            + "</tr>");
                }
            }catch(Exception ex){
                out.println("<tr><td colspan='3'>"+ex.getMessage()+"</td></tr>");
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
