/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.chimera.gui.servlets;

import com.cedarsoftware.util.io.JsonWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.edu.dlsu.chimera.gui.tasks.Task;
import ph.edu.dlsu.chimera.monitors.PhaseMonitorStatistics;

/**
 *
 * @author AMD
 */
public class ServletDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action").equals("state")) {
            if (Task.getTask() != null) {
                if (Task.getTask().monitor instanceof PhaseMonitorStatistics) {
                    PhaseMonitorStatistics pms = (PhaseMonitorStatistics) Task.getTask().monitor;
                    if (pms.getStatsMonitor() != null) {
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(JsonWriter.toJson(pms.getStatsMonitor()));
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
