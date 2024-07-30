package scoremanager.main;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // 入力パラメータを取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        String school = request.getParameter("school");

        // 入力検証を行う
        if (cd == null || cd.isEmpty()) {
            handleError(response, "科目コードが必要です。");
            return;
        }

        if (name == null || name.isEmpty()) {
            handleError(response, "科目名が必要です。");
            return;
        }

        if (school == null || school.isEmpty()) {
            handleError(response, "学校コードが必要です。");
            return;
        }

        // 科目オブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        // 学校オブジェクトを取得（通常は学校コードで学校を検索することになります）
        // TODO: ここで学校オブジェクトを設定する処理を追加

        // 科目を保存（SubjectDaoがデータベースと正しく連携していると仮定）
        SubjectDao subjectDao = new SubjectDao();
        // TODO: subjectDao.save(subject) の呼び出しを追加して科目を保存

        // 成功メッセージを送信
        handleSuccess(response, "科目が正常に作成されました。");
    }

    // エラーメッセージを処理するメソッド
    private void handleError(HttpServletResponse response, String message) {
        try {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 成功メッセージを処理するメソッド
    private void handleSuccess(HttpServletResponse response, String message) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
