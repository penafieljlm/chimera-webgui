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
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskGathering;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorGathering;

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "ServletGathering", urlPatterns = {"/ServletGathering"})
public class ServletGathering extends HttpServlet {

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
        if (request.getParameter("action").equals("state")) {
            String text;

            if (Task.getTask() != null) {
                if (Task.getTask() instanceof TaskGathering) {
                    text = "Number of instances gathered: " + ((TaskGathering) (Task.getTask())).monitor.getInstancesGathered();
                } else {
                    text = "Running task is not Data Gathering";
                }
            } else {
                text = "No task is running";
            }

            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(text);
        } else if (request.getParameter("action").equals("file")) {
            String text = null;

            if (Task.getTask() != null) {
                if (Task.getTask() instanceof TaskGathering) {
                    text = ((TaskGathering) (Task.getTask())).getOutputFile().getAbsolutePath();
                }
            }

            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(text);
        }
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            if (request.getParameter("action").equals("start")) {
                //resources
                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                PhaseMonitorGathering _monitor = new PhaseMonitorGathering(200) {
                    @Override
                    protected void update() {
                    }
                };

                //set defaults
                String _output = dateFormat.format(new Date());
                String _protected = null;
                String _access = null;
                boolean _allow = false;
                String _training = null;
                boolean _attack = false;

                //set values
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

                //create task
                Task task = new TaskGathering(_monitor, _output, _protected, _access, _allow, _training, _attack);
                Task.setTask(task);

                //run task
                task.start();
            } else if (request.getParameter("action").equals("stop")) {
                if (Task.getTask() != null) {
                    if (Task.getTask() instanceof TaskGathering) {
                        Task.terminateTask();
                    }
                }
            }
        } catch (Exception ex) {
        } finally {
            out.close();
        }
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
