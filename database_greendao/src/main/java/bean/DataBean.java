package bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by SJD on 2017/10/19.
 * @author SJD
 */
/*@Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类;
 @nameInDb：在数据库中的名字，如不写则为实体中类名；
 @Id：选择一个long / Long属性作为实体ID。 在数据库方面，它是主键。 参数autoincrement是设置ID值自增；
 @NotNull：使该属性在数据库端成为“NOT NULL”列。 通常使用@NotNull标记原始类型（long，int，short，byte）是有意义的；
 @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化。
 */
@Entity
public class DataBean {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String msg;
    @Transient
    private int state;
    @Generated(hash = 532927832)
    public DataBean(Long id, @NotNull String msg) {
        this.id = id;
        this.msg = msg;
    }
    @Generated(hash = 908697775)
    public DataBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
