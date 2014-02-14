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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskTraining;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorTraining;

/**
 *
 * @author Emerson Chua
 */
@MultipartConfig
/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "ServletTraining", urlPatterns = {"/ServletTraining"})
public class ServletTraining extends HttpServlet {

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
                Part filePart = request.getPart("trainingfile");

                PhaseMonitorTraining _monitor = null;
                InputStream _input = filePart.getInputStream();
                String _output = null;
                String _filter = null;
                boolean _exclude = false;

                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                Date date = new Date();
                _output = dateFormat.format(date);
                if (request.getParameter("enablefilter") != null && request.getParameter("filter") != null) {
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
                        Task.terminateTask();
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
        String text = (Task.getTask() != null)
                ? (Task.getTask() instanceof TaskTraining)
                ? (((TaskTraining) (Task.getTask())).getUploadProgress() >= 1.0)
                ? "[" + String.format("%03d", (int) (((TaskTraining) (Task.getTask())).monitor.getProgress() * 100)) + "%] - " + ((TaskTraining) (Task.getTask())).monitor.getStatus()
                : String.format("%03d", (int) (((TaskTraining) (Task.getTask())).getUploadProgress() * 100)) + "%] - " + "Uploading Training Set"
                : "Running task is not Training"
                : "No task is running";

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

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
