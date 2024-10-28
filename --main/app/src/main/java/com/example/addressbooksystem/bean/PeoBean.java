package com.example.addressbooksystem.bean;

public class PeoBean {

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String num;
    /**
     * 性别
     */
    private String sex;

    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "PeoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", sex='" + sex + '\'' +
                ", remark='" + remark + '\'' +
                ", beginZ='" + beginZ + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeginZ() {
        return beginZ;
    }

    public void setBeginZ(String beginZ) {
        this.beginZ = beginZ;
    }

    public PeoBean() {
    }

    public PeoBean(String id, String name, String num, String sex, String remark, String beginZ) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sex = sex;
        this.remark = remark;
        this.beginZ = beginZ;
    }

    public PeoBean(String id, String name, String num, String sex, String remark) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.sex = sex;
        this.remark = remark;
    }

    /**
     * 字母
     */
    private String beginZ;
}
