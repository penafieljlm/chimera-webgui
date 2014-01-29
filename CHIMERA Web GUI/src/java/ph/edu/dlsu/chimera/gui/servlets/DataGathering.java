/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.edu.dlsu.chimera.gui.tasks.TaskGather;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorGathering;

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
    private TaskGather task;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            if (request.getParameter("action").equals("start")) {
                //set defaults
                String _output = null;
                String _protected = null;
                String _access = null;
                boolean _allow = false;
                String _training = null;
                boolean _attack = false;
                PhaseMonitorGathering _monitor = null;

                //set values
                if (request.getParameter("outputfile") != null) {
                    _output = request.getParameter("outputfile");
                }
                if (request.getParameter("interface") != null) {
                    _protected = request.getParameter("interface");
                }
                if (request.getParameter("packetfilter") != null) {
                    _access = request.getParameter("packetfilter");
                }
                if (request.getParameter("packetfilterswitch") != null) {
                    _allow = !request.getParameter("packetfilterswitch").isEmpty();
                }
                if (request.getParameter("trainingfilter") != null) {
                    _training = request.getParameter("trainingfilter");
                }
                if (request.getParameter("attackswitch") != null) {
                    _attack = !request.getParameter("attackswitch").isEmpty();
                }

                //create monitor
                _monitor = new PhaseMonitorGathering(200) {
                    @Override
                    protected void update() {
                    }
                };

                //create task
                this.task = new TaskGather(_output, _protected, _access, _allow, _training, _attack, _monitor);

                //run task
                this.task.start();
            }
            if (request.getParameter("action").equals("stop")) {
                if (this.task != null) {
                    //stop task
                    //TODO: create stop
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DataGathering.class.getName()).log(Level.SEVERE, null, ex);
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
