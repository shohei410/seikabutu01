package controllers.works;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Work;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class WorksUpdateServlet
 */
@WebServlet("/works/update")
public class WorksUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ReportUpdateServlet参照　ここから

        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Work w = em.find(Work.class, (Integer)(request.getSession().getAttribute("work_id")));
          //ここまで
            w.setReport_date(Date.valueOf(request.getParameter("report_date")));
            w.setTitle(request.getParameter("title"));
            r.setContent(request.getParameter("content"));
            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = ReportValidator.validate(w);
            if(errors.size() > 0) {
                em.close();

                //ここから
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("work", w);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/works/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("work_id");

                response.sendRedirect(request.getContextPath() + "/works/index");
                //ここまで
            }
        }
    }

}