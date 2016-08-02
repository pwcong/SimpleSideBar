package me.pwcong.simplesidebar.entity;

/**
 * Created by pwcong on 2016/8/2.
 */
public class SimpleData {

    int type;
    String string;


    public SimpleData(int type, String string) {
        this.type = type;
        this.string = string;
    }

    @Override
    public String toString() {
        return "SimpleData{" +
                "type=" + type +
                ", string='" + string + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
