package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllWorks",
        query = "SELECT w FROM Work AS w ORDER BY w.id DESC"
            ),
    @NamedQuery(
        name = "getWorksCount",
        query = "SELECT COUNT(w) FROM Work AS w"
    )
})
@Table(name = "works")
public class Work {
    @Id
    @Column(name = "id")                                //①リソース内連番のid
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne                                          //②社員番号
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Lob
    @Column(name = "content", nullable = false)  //③「本日の予定」（テキスト型）
    private String content;

    @Column(name = "plan_flag", nullable = false)   //④「勤務予定時間」（0:日勤/1:夜勤/2:その他）
    private Integer plan_flag;

    @Column(name = "attend_flag", nullable = false)   //⑤「出社確認」（0:出社/1:退社）
    private Integer attend_flag;

    @Column(name = "syussya_at", nullable = false)  //⑥「出社時間」
    private Timestamp syussya_at;

    @Column(name = "taisya_at", nullable = false)  //⑦「退社時間」
    private Timestamp taisya_at;

    @Column(name = "updated_at", nullable = false) //⑧更新日時
    private Timestamp updated_at;

    //setter getter以下

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPlan_flag() {
        return plan_flag;
    }

    public void setPlan_flag(Integer plan_flag) {
        this.plan_flag = plan_flag;
    }

    public Integer getAttend_flag() {
        return attend_flag;
    }

    public void setAttend_flag(Integer attend_flag) {
        this.attend_flag = attend_flag;
    }

    public Timestamp getSyussya_at() {
        return syussya_at;
    }

    public void setSyussya_at(Timestamp syussya_at) {
        this.syussya_at = syussya_at;
    }

    public Timestamp getTaisya_at() {
        return taisya_at;
    }

    public void setTaisya_at(Timestamp taisya_at) {
        this.taisya_at = taisya_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }




}


