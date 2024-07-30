package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    // 科目をコードと学校から取得するメソッド
    public Subject get(String cd, School school) throws Exception {
        Subject subject = new Subject();
        // データベースへのコネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        school.getName();
        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from subject where cd=?");
            // プリペアードステートメントに科目コードをバインド
            statement.setString(1, cd);
            // プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                // リザルトセットが存在する場合
                // 科目インスタンスに検索結果をセット
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                // SchoolDaoのインスタンスを生成
                SchoolDao schoolDao = new SchoolDao();
                // 学校フィールドには学校コードで検索した学校インスタンスをセット
                subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
                // リザルトセットが存在しない場合
                // 科目インスタンスにnullをセット
                subject = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return subject;
    }

    // リザルトセットをリストに変換するメソッド
    private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
        // リストを初期化
        List<Subject> list = new ArrayList<>();
        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                // 科目インスタンスを初期化
                Subject subject = new Subject();
                // 科目インスタンスに検索結果をセット
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                // リストに追加
                list.add(subject);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 学校コードで科目をフィルタリングするメソッド
    public List<Subject> filter(School school) throws Exception {
        // リストを初期化
        List<Subject> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文をソート
        String order = "order by cd asc";
        // TeacherDaoのインスタンスを初期化
        TeacherDao teacherDao = new TeacherDao();
        SchoolDao schoolDao = new SchoolDao();

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("select * from subject where school_cd = ?" + order);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // 学校情報を取得
            school = schoolDao.get(school.getCd());
            // リストへの格納処理を実行
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list;
    }

    // 科目を保存するメソッド
    public boolean save(Subject subject) throws Exception {
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // 実行数
        int count = 0;
        try {
            // subjectテーブルから科目を取得
            Subject old = get(subject.getCd(), subject.getSchool());
            if (old == null) {
                // 科目が存在しなかった場合
                // プリペアードステートメントにINSERT文をセット
                statement = connection.prepareStatement(
                        "insert into subject(cd, name) values(?, ?)");
                // プリペアードステートメントに値をバインド
                statement.setString(1, subject.getCd());
                statement.setString(2, subject.getName());
            } else {
                // 科目が存在した場合
                // プリペアードステートメントにUPDATE文をセット
                statement = connection.prepareStatement(
                        "update subject set name = ? where cd = ?");
                // プリペアードステートメントに値をバインド
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
            }
            // プリペアードステートメントを実行
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        // 実行件数が1件以上ある場合
        return count > 0;
    }

    // 科目を削除するメソッド
    public boolean delete(Subject subject) throws Exception {
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // 実行件数
        int count = 0;
        try {
            // subjectテーブルから科目を取得
            Subject old = get(subject.getCd(), subject.getSchool());
            if (old != null) {
                // プリペアードステートメントにDELETE文をセット
                statement = connection.prepareStatement(
                        "delete from subject where cd = ?");
                // プリペアードステートメントに値をバインド
                statement.setString(1, subject.getCd());
                count = statement.executeUpdate();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        // 実行件数が1件以上ある場合
        return count > 0;
    }
}
