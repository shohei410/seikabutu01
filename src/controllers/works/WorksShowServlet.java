package controllers.works;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Work;
import utils.DBUtil;

/**
 * Servlet implementation class WorksShowServlet
 */
@WebServlet("/works/show")
public class WorksShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Work w = em.find(Work.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("work", w);
        request.setAttribute("_token", request.getSession().getId());


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/works/show.jsp");
        rd.forward(request, response);

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }



}
