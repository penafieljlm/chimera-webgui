/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.edu.dlsu.chimera.Chimera;

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "ServletConfig", urlPatterns = {"/ServletConfig"})
public class ServletConfig extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String _port = null;
            String _protected = null;
            String _statetimeout = null;
            String _statstimeout = null;
            String _syslogport = null;

            if (request.getParameter("tcptimeout") != null) {
                _statetimeout = request.getParameter("tcptimeout");
            }

            if (request.getParameter("criteriatimeout") != null) {
                _statstimeout = request.getParameter("criteriatimeout");
            }

            if (request.getParameter("controlport") != null) {
                _port = request.getParameter("controlport");
            }

            if (request.getParameter("interface") != null) {
                _protected = request.getParameter("interface");
            }
            
            if (request.getParameter("syslogport") != null) {
                _syslogport = request.getParameter("syslogport");
            }
            
            Chimera.cconfig(Integer.parseInt(_port), _protected, Long.parseLong(_statetimeout), Long.parseLong(_statstimeout), Integer.parseInt(_syslogport));
        } catch (Exception ex) {
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
