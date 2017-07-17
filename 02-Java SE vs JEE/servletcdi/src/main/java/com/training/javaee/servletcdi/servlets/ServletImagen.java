package com.training.javaee.servletcdi.servlets;


import com.training.javaee.servletcdi.managedbeans.BeanContador;
import com.training.javaee.servletcdi.managedbeans.ControlImagen;
import com.training.javaee.servletcdi.managedbeans.ServicioStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author training
 */
@WebServlet(name = "ServletImagen", urlPatterns = {"/imagen"})
public class ServletImagen extends HttpServlet {

    @Inject
    private ControlImagen ci;

    @Inject
    private ServicioStreams ss;
    
    @Inject
    private BeanContador contador;

    @Override
    public void doGet(HttpServletRequest rq, HttpServletResponse re) throws IOException {
        Object parametro = rq.getParameter("imagenId");
        int indice = parametro == null ? -1 : Integer.parseInt(parametro.toString());

        InputStream is = ci.obtenerImagen(indice);

        if (is == null) {
            re.setContentType("text/html");
            try (PrintWriter out = re.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Not Found</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>La imagen no ha sido encontrada</h1>");
                out.println("<span style='color:red'>No se ha encontrado la imagen</span>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            contador.aumentarContador();
            ss.copiarInputStreamAOutStream(is, re.getOutputStream());
            re.setContentType("image/png");
        }
    }

}
