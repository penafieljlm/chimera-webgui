/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

import com.cedarsoftware.util.io.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ph.edu.dlsu.chimera.core.Statistics;
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.gui.tasks.TaskProduction;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorProduction;

/**
 *
 * @author Emerson Chua
 */
@MultipartConfig
@WebServlet(name = "ServletProduction", urlPatterns = {"/ServletProduction"})
public class ServletProduction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action").equals("state")) {
            String text;

            if (Task.getTask() != null) {
                if (Task.getTask() instanceof TaskProduction) {
                    text = "In Production";
                } else {
                    text = "Running task is not Production";
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
                Part filePart = request.getPart("modelfile");

                //set defaults
                PhaseMonitorProduction _monitor = new PhaseMonitorProduction(200);
                InputStream _input = filePart.getInputStream();
                String _syslog = null;
                String _syslogport = null;
                boolean _active = false;

                if (request.getParameter("enablesyslog").equals("on") && request.getParameter("syslog") != null) {
                    _syslog = request.getParameter("syslog");
                }
                if (request.getParameter("enablesyslogport").equals("on") && request.getParameter("syslogport") != null) {
                    _syslogport = request.getParameter("syslogport");
                }
                if (request.getParameter("firewall") != null) {
                    _active = request.getParameter("firewall").equals("on");
                }

                //create task
                Task task = new TaskProduction(_monitor, _input, _syslog, (_syslog == null) ? null : Integer.parseInt(_syslogport), _active);
                Task.setTask(task);

                //run task
                task.start();

                String site = "index.jsp";
                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            } else if (request.getParameter("action").equals("stop")) {
                if (Task.getTask() != null) {
                    if (Task.getTask() instanceof TaskProduction) {
                        Task.getTask().monitor.terminate();
                        Task.getTask().join();
                        Task.setTask(null);
                    }
                }
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
