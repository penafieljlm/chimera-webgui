/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import ph.edu.dlsu.chimera.core.nic.NicData;
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskGather;
import ph.edu.dlsu.chimera.gui.tasks.TaskProduction;
import ph.edu.dlsu.chimera.gui.tasks.TaskTraining;

/**
 *
 * @author User
 */
@WebServlet(name = "ServletInitialize", urlPatterns = {"/ServletInitialize"})
public class ServletInitialize extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
        } finally {
            out.close();
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
        try {
            NicData[] ifaces = Chimera.cifaces();
            
            if(Task.getTask() instanceof TaskGather){
                request.setAttribute("runningtask", "gathering");
            } else if(Task.getTask() instanceof TaskTraining){
                request.setAttribute("runningtask", "training");
            } else if(Task.getTask() instanceof TaskProduction){
                request.setAttribute("runningtask", "production");
            } else {
                request.setAttribute("runningtask", "none");
            }
            
            int i = 0;
            try {
                for (NicData n : ifaces) {
                    request.setAttribute("iface" + i, n.name);
                    request.setAttribute("ifacedesc" + i, n.description);
                    request.setAttribute("ifacehard" + i, n.hardwareAddress);
                    request.setAttribute("ifaceip" + i, (n.addresses.size() > 0) ? n.addresses.get(0).address : "none");
                    request.setAttribute("ifacesubnet" + i, (n.addresses.size() > 0) ? n.addresses.get(0).netmask : "none");

                    i++;
                }
            } catch (Exception e) {
            }

            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception ex) {
        }
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
