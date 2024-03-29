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

/**
 *
 * @author Emerson Chua
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUser at " + request.getContextPath() + "</h1>");

            if (request.getParameter("first-name") == null) {
                out.println("Please enter your first name.");
            } else {
                out.println("First name is <b>" + request.getParameter("first-name") + "</b>!");
            }

            if (request.getParameter("last-name") == null) {
                out.println("Please enter your last name.");
            } else {
                out.println("Last name is <b>" + request.getParameter("last-name") + "</b>!");
            }

            if (request.getParameter("username") == null) {
                out.println("Please enter your username.");
            } else {
                out.println("Username is <b>" + request.getParameter("username") + "</b>!");
            }

            if (request.getParameter("password") == null) {
                out.println("Please enter your password.");
            } else {
                out.println("Password is <b>" + request.getParameter("password") + "</b>!");
            }

            if (request.getParameter("role") == null || request.getParameter("role").equals("")) {
                out.println("No role selected.");
            } else {
                out.println("Role is <b>" + request.getParameter("role") + "</b>!");
            }

            if (request.getParameter("terms") == null) {
                out.println("You did not agree with the terms.");
            } else {
                out.println("Terms are <b>" + request.getParameter("terms") + "</b>!");
            }

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
