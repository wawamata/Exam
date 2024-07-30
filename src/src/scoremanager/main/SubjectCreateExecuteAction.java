package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言 1
        SubjectDao sDao = new SubjectDao(); // 科目Dao

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        String school = req.getParameter("school");

        HttpSession session = req.getSession();//セッション
        Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

        Map<String, String> errors = new HashMap<>(); // エラーメッセージ


        if (!errors.isEmpty()) {
            // リクエスト属性をセット

            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.setAttribute("school", school);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        bean.Subject subject = sDao.get(cd, teacher.getSchool());


      
        // JSPへフォワード 7
        if (subject != null) {
            // 科目が存在していた場合
            // インスタンスに値をセット
            subject.setCd(cd);
            subject.setName(name);

            //
            sDao.save(subject);
        } else {
            errors.put("cd", "存在しません。");
        }

        if (!errors.isEmpty()) {
            // リクエスト属性をセット
            req.setAttribute("errors", errors);
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            req.setAttribute("school", school);
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }
        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}

// Placeholder for SubjectDao class

// Placeholder for Subject class

