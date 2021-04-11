package controllers.works;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Work;
import utils.DBUtil;

/**
 * Servlet implementation class WorksEditServlet
 */
@WebServlet("/works/edit")
public class WorksEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RepotEditServlet参照　　ここから

        EntityManager em = DBUtil.createEntityManager();

        Work w = em.find(Work.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        if(w != null && login_employee.getId() == w.getEmployee().getId()) {
            request.setAttribute("work", w);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("work_id", w.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/works/edit.jsp");
        rd.forward(request, response);
        //ここまで
    }

}