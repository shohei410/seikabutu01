package controllers.works;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class WorksIndexServlet
 */
@WebServlet("/works/index")
public class WorksIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//ここから
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Work> works = em.createNamedQuery("getAllWorks", Work.class)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long works_count = (long)em.createNamedQuery("getWorksCount", Long.class)
                                     .getSingleResult();

        em.close();


        request.setAttribute("works", works);
        request.setAttribute("works_count", works_count);
      //ここまで
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }




        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/works/index.jsp");
        rd.forward(request, response);

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }



}
