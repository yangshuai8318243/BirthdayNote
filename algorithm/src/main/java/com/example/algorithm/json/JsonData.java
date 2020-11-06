package com.example.algorithm.json;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/10/9
 * Time: 15:37
 */
public class JsonData<T> {
    private String name;
    private T content;

    private JsonData(Builder<T> builder) {
        setName(builder.name);
        setContent(builder.content);
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


    public static final class Builder<T> {
        private String name;
        private T content;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder content(T val) {
            content = val;
            return this;
        }

        public JsonData build() {
            return new JsonData(this);
        }
    }
}
