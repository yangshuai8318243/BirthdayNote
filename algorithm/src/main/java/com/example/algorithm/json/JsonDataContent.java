package com.example.algorithm.json;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/10/9
 * Time: 15:39
 */
public class JsonDataContent {
    private String tag;
    private String type;
    private int age;

    private JsonDataContent(Builder builder) {
        setTag(builder.tag);
        setType(builder.type);
        setAge(builder.age);
    }

    @Override
    public String toString() {
        return "JsonDataContent{" +
                "tag='" + tag + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static final class Builder {
        private String tag;
        private String type;
        private int age;

        public Builder() {
        }

        public Builder tag(String val) {
            tag = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public JsonDataContent build() {
            return new JsonDataContent(this);
        }
    }
}
