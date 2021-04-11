package controllers.works;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Work;
import models.validators.WorkValidator;
import utils.DBUtil;

/**
 * Servlet implementation class WorksCreateServlet
 */
@WebServlet("/works/create")
public class WorksCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorksCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }



     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //ここから
         //なりすまし等のセキュリティ対策
            String _token = (String)request.getParameter("_token");
            if(_token != null && _token.equals(request.getSession().getId())) {
                EntityManager em = DBUtil.createEntityManager();

             // Worksのインスタンスを生成
                Work w = new Work();

                w.setContent(request.getParameter("content"));                                  //③
                w.setEmployee((Employee)request.getSession().getAttribute("login_employee"));   //②？


                w.setPlan_flag(Integer.parseInt(request.getParameter("plan_flag")));            //④
             // w.setAttend_flag(Integer.parseInt(request.getParameter("attend_flag")));        //⑤
             // ↓
                w.setAttend_flag(Integer.parseInt(request.getParameter("attends")));

                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                w.setSyussya_at(currentTime);                                                   //⑥
                w.setTaisya_at(currentTime);                                                    //⑦
                w.setUpdated_at(currentTime);                                                   //⑧
            //ここまで

                List<String> errors = WorkValidator.validate(w);
                if(errors.size() > 0) {
                    em.close();

                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("work", w);
                    request.setAttribute("errors", errors);

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/works/new.jsp");
                    rd.forward(request, response);
                } else {
                     // データベースに保存
                    em.getTransaction().begin();
                    em.persist(w);
                    em.getTransaction().commit();

                    request.getSession().setAttribute("flush", "登録が完了しました。");
                    em.close();

                    response.sendRedirect(request.getContextPath() + "/works/index");
                }
            }
        }

    }