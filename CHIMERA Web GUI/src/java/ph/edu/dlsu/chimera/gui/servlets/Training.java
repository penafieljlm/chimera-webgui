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
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskTraining;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorTraining;

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "Training", urlPatterns = {"/Training"})
public class Training extends HttpServlet {

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
            if (request.getParameter("action").equals("start")) {
                PhaseMonitorTraining _monitor = null;
                String _input = null;
                String _output = null;
                String _filter = null;
                boolean _exclude = false;

                if (request.getParameter("trainingfile") != null) {
                    _input = request.getParameter("trainingfile");
                }
                if (request.getParameter("outputfile") != null) {
                    _output = request.getParameter("outputfile");
                }
                if (request.getParameter("filter") != null) {
                    _filter = request.getParameter("filter");
                }
                if (request.getParameter("exlude") != null) {
                    _exclude = request.getParameter("exclude").equals("on");
                }

                //create monitor
                _monitor = new PhaseMonitorTraining(200) {
                    @Override
                    protected void update() {
                    }
                };

                //create task
                Task task = new TaskTraining(_monitor, _input, _output, _filter, _exclude);
                Task.setTask(task);

                //run task
                task.start();
            } else if (request.getParameter("action").equals("stop")) {
                if (Task.getTask() != null) {
                    if (Task.getTask() instanceof TaskTraining) {
                        Chimera.cquit();
                        Task.setTask(null);
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
        String text = (Task.getTask() != null) ? (Task.getTask() instanceof TaskTraining) ? "Training..." : "Running task is not Training" : "No task is running";

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
