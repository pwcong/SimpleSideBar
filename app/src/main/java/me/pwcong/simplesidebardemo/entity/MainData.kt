package me.pwcong.simplesidebardemo.entity

class MainData(var type: Int, var string: String) {
    override fun toString(): String {
        return "MainData{" +
                "type=" + type +
                ", string='" + string + '\'' +
                '}'
    }
}
