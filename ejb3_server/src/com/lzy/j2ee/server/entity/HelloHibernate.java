package com.lzy.j2ee.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by laizhiyuan on 2017/8/21.
 *
 * <p>
 *     第一个ORM实体
 * </p>
 */
@Entity
@Table(name = "t_hello_hibernate")
@NamedQueries(
        {
                @NamedQuery(name = "findById", query = "SELECT hh FROM HelloHibernate AS hh WHERE hh.id =:id"),
                @NamedQuery(name = "updateById", query = "UPDATE HelloHibernate AS hh SET hh.name =:name " +
                        ", hh.address =:address ,hh.age =:age , hh.birthday =:birthday , hh.gender =:gender " +
                        " , hh.updateTime =:updateTime WHERE hh.id =:id")
        }
)
public class HelloHibernate implements Serializable {

    static final long serialVersionUID = 9826349L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE , generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "hello_hibernate_seq")
    private Long id;

    private String name;

    private String address;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private Integer age;

    private Date birthday;

    private Integer gender;

    public HelloHibernate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "HelloHibernate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", age=" + age +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
