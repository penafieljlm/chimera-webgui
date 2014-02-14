/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ph.edu.dlsu.chimera.Chimera;
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskProduction;
import ph.edu.dlsu.chimera.gui.tasks.TaskTraining;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorProduction;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorTraining;

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "ServletProduction", urlPatterns = {"/ServletProduction"})
public class ServletProduction extends HttpServlet {

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
            if (request.getParameter("action").equals("start")) {
                Part filePart = request.getPart("modelfile");

                PhaseMonitorProduction _monitor = null;
                InputStream _input = filePart.getInputStream();
                String _syslog = null;
                String _syslogport = null;
                boolean _active = false;

                if (request.getParameter("syslog") != null) {
                    _syslog = request.getParameter("syslog");
                }
                if (request.getParameter("syslogport") != null) {
                    _syslogport = request.getParameter("syslogport");
                }
                if (request.getParameter("firewall") != null) {
                    _active = request.getParameter("firewall").equals("on");
                }

                //create monitor
                _monitor = new PhaseMonitorProduction(200) {
                    @Override
                    protected void update() {
                    }
                };

                //create task
                Task task = new TaskProduction(_monitor, _input, _syslog, Integer.parseInt(_syslogport), _active);
                Task.setTask(task);

                //run task
                task.start();
            } else if (request.getParameter("action").equals("stop")) {
                if (Task.getTask() != null) {
                    if (Task.getTask() instanceof TaskProduction) {
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
        String text = (Task.getTask() != null) ? (Task.getTask() instanceof TaskTraining) ? "In Production..." : "Running task is not Production" : "No task is running";

        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(text);
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
