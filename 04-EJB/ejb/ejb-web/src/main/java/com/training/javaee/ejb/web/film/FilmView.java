/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.javaee.ejb.web.film;

import com.training.javaee.ejb.domain.Film;
import com.training.javaee.ejb.domain.FilmCategory;
import com.training.javaee.ejb.services.ServicioFilm;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
@WebServlet(name = "FilmView", urlPatterns = {"/film/view"})
public class FilmView extends HttpServlet {
    
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
        Object parameter= request.getParameter("filmId");
        short filmId = parameter != null ? Short.parseShort(parameter.toString()) : -1;
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Detalle pelicula</title>"); 
            out.println("<link rel='stylesheet' href='/ejb-web/resources/css/theme.css'/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Detalle de Pelicula</h1>");
            Film film = this.servicioFilm.buscar(filmId);
            
            if(film == null){
                out.println("<span style='color:red'>No se ha encontrado el film indicado</span>");
            }else{
                out.println("<br><b>Titulo:</b>"+film.getTitle());
                out.println("<br><b>Descripcion:</b>"+film.getDescription());
                out.println("<br><b>Ano:</b>"+film.getReleaseYear());
                out.println("<br><b>Caracteristicas Especiales:</b>"+film.getSpecialFeatures());
                out.println("<br><b>Rating:</b>"+film.getRating());
                out.println("<br><b>Ganancias:</b>"+film.getRentalRate());
                out.println("<br><b>Duración:</b>"+film.getLength());
                out.println("<br><b>Idioma:</b>"+film.getLanguageId().getName());
                
                out.println("<br><b>Categorias:</b>");
                Iterator<FilmCategory> it = film.getFilmCategoryList().iterator();
                while(it.hasNext()){
                    out.print(it.next().getCategory().getName());
                    if(it.hasNext()){
                        out.print(", ");
                    }
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
