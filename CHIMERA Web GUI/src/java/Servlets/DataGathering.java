/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Functions.runDataGathering;

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "DataGathering", urlPatterns = {"/DataGathering"})
public class DataGathering extends HttpServlet {

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
    
    String outputfile = "";
    String iface = "";
    String attack = "";
    String packetfilter = "";
    String packetfilterswitch = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataGathering</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DataGathering at " + request.getContextPath() + "</h1>");
            
            if (request.getParameter("outputfile") == null || request.getParameter("outputfile") == "") {
                out.println("Please specify output filename.");
            } else {
                outputfile = request.getParameter("outputfile");
                out.println("Output filename is <b>"+request. getParameter("outputfile")+"</b>!");
            }
            
            if (request.getParameter("interface") == null || request.getParameter("interface") == "") {
                out.println("You did not specify interface.");
            } else {
                iface = request.getParameter("interface");
                out.println("Interface selected is <b>"+request. getParameter("interface")+"</b>!");
            }
            
            if (request.getParameter("attackswitch") == null || request.getParameter("attackswitch") == "") {
                out.println("Attack switch is off.");
            } else {
                attack = request.getParameter("attackswitch");
                out.println("Attack switch is <b>"+request. getParameter("attackswitch")+"</b>!");
            }
            
            if (request.getParameter("packetfilter") == null || request.getParameter("packetfilter") == "") {
                out.println("You did not specify packet filter.");
            } else {
                packetfilter = request.getParameter("packetfilter");
                out.println("Packet filter is <b>"+request. getParameter("packetfilter")+"</b>!");
            }

            if (request.getParameter("packetfilterswitch") == null || request.getParameter("packetfilterswitch") == "") {
                out.println("Packet filter switch is off.");
            } else {
                packetfilterswitch = request.getParameter("packetfilterswitch");
                out.println("Packet filter switch is <b>"+request. getParameter("packetfilterswitch")+"</b>!");
            }
            
            new runDataGathering(outputfile, iface, attack, packetfilter, packetfilterswitch);

            out.println("</body>");
            out.println("</html>");
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
