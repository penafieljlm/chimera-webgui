/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskGather;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorGathering;

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "ServletGathering", urlPatterns = {"/ServletGathering"})
public class ServletGathering extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            if (request.getParameter("action").equals("start")) {
                //set defaults
                PhaseMonitorGathering _monitor = null;
                String _output = null;
                String _protected = null;
                String _access = null;
                boolean _allow = false;
                String _training = null;
                boolean _attack = false;

                //set values
                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                Date date = new Date();
                _output = dateFormat.format(date);
                if (request.getParameter("interface") != null) {
                    _protected = request.getParameter("interface");
                }
                if (request.getParameter("packetfilter") != null) {
                    _access = request.getParameter("packetfilter");
                }
                if (request.getParameter("packetfilterswitch") != null) {
                    _allow = request.getParameter("packetfilterswitch").equals("on");
                }
                if (request.getParameter("trainingfilter") != null) {
                    _training = request.getParameter("trainingfilter");
                }
                if (request.getParameter("attackswitch") != null) {
                    _attack = request.getParameter("attackswitch").equals("on");
                }

                //create monitor
                _monitor = new PhaseMonitorGathering(200) {
                    @Override
                    protected void update() {
                    }
                };

                //create task
                Task task = new TaskGather(_monitor, _output, _protected, _access, _allow, _training, _attack);
                Task.setTask(task);

                //run task
                task.start();
            } else if (request.getParameter("action").equals("stop")) {
                if (Task.getTask() != null) {
                    if (Task.getTask() instanceof TaskGather) {
                        Task.terminateTask();
                    }
                }
            }
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
        String text = (Task.getTask() != null) ? (Task.getTask() instanceof TaskGather) ? ("Number of instances gathered: " + ((TaskGather) (Task.getTask())).monitor.getInstancesGathered()) : "Running task is not Data Gathering" : "No task is running";

        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(text);
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
