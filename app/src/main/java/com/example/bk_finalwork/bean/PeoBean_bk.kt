package com.example.bk_finalwork.bean

class PeoBean_bk {
    var id: String? = null
    var name: String? = null
    var num: String? = null
    var sex: String? = null
    var remark: String? = null
    var beginZ: String? = null
    var isFavorite: String? = null

    constructor() {}

    override fun toString(): String {
        return "PeoBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", sex='" + sex + '\'' +
                ", remark='" + remark + '\'' +
                ", beginZ='" + beginZ + '\'' +
                ", isFavorite='" + isFavorite + '\'' +
                '}'
    }

    constructor(
        id: String?,
        name: String?,
        num: String?,
        sex: String?,
        remark: String?,
        beginZ: String?,
        isFavorite:String?
    ) {
        this.id = id
        this.name = name
        this.num = num
        this.sex = sex
        this.remark = remark
        this.beginZ = beginZ
        this.isFavorite = isFavorite
    }

    constructor(id: String?, name: String?, num: String?, sex: String?, remark: String?,isFavorite: String?) {
        this.id = id
        this.name = name
        this.num = num
        this.sex = sex
        this.remark = remark
        this.isFavorite = isFavorite
    }
}