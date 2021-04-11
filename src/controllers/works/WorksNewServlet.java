package controllers.works;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Work;

/**
 * Servlet implementation class WorksNewServlet
 */
@WebServlet("/works/new")
public class WorksNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ここから
        request.setAttribute("_token", request.getSession().getId());

        Work w = new Work();
        w.setSyussya_at(new Timestamp(System.currentTimeMillis()));
        request.setAttribute("work", w);

        //ここまで
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/works/new.jsp");
        rd.forward(request, response);


        response.getWriter().append("Served at: ").append(request.getContextPath());
    }



}
