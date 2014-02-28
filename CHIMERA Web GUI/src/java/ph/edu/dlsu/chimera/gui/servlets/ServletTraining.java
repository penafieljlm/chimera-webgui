/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

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
@WebServlet(name = "ServletTraining", urlPatterns = {"/ServletTraining"})
public class ServletTraining extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action").equals("state")) {
            String text;

            if (Task.getTask() != null) {
                if (Task.getTask() instanceof TaskTraining) {
                    text = "[" + String.format("%03d", (int) (((TaskTraining) (Task.getTask())).monitor.getProgress() * 100)) + "%] - " + ((TaskTraining) (Task.getTask())).monitor.getStatus();
                } else {
                    text = "Running task is not Training";
                }
            } else {
                text = "No task is running";
            }

            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(text);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("action").equals("start")) {
                //resources
                Part filePart = request.getPart("trainingfile");
                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

                //set defaults
                PhaseMonitorTraining _monitor = new PhaseMonitorTraining(200);
                InputStream _input = filePart.getInputStream();
                String _output = dateFormat.format(new Date());
                String _filter = null;
                boolean _exclude = false;

                if (request.getParameter("enablefilter").equals("on") && request.getParameter("filter") != null) {
                    _filter = request.getParameter("filter");
                }
                if (request.getParameter("exlude") == null) {
                    _exclude = request.getParameter("exclude").equals("on");
                }
                
                //create task
                Task task = new TaskTraining(_monitor, _input, _output, _filter, _exclude);
                Task.setTask(task);

                //run task
                task.start();

                String site = "index.jsp";
                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            } else if (request.getParameter("action").equals("stop")) {
                String text = null;

                if (Task.getTask() != null) {
                    if (Task.getTask() instanceof TaskTraining) {
                        System.out.println("attempting to stop training");
                        Task.getTask().monitor.terminate();
                        Task.getTask().join();
                        text = ((TaskTraining) (Task.getTask())).getOutputFile().getAbsolutePath();
                        Task.setTask(null);
                        System.out.println("stopped training");
                    }
                }

                response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
                response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
                response.getWriter().write(text);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
