package com.example.algorithm.tablayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/8/31
 * Time: 18:04
 */
public class ExpressionEntity {
    private int id;
    private int type;
    private List<ExperssionItem> experssionItems;

    public int getId() {
        return id;
    }

    public ExpressionEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public ExpressionEntity setType(int type) {
        this.type = type;
        return this;
    }


    public List<ExperssionItem> getExperssionItems() {
        return experssionItems;
    }

    public ExpressionEntity setExperssionItems(List<ExperssionItem> experssionItems) {
        this.experssionItems = experssionItems;
        return this;
    }

    public ExpressionEntity addExperssionItem(ExperssionItem experssionItem) {
        if (this.experssionItems == null) {
            this.experssionItems = new ArrayList<>();
        }
        this.experssionItems.add(experssionItem);
        return this;
    }

    public ExpressionEntity addAllExperssionItem(List<ExperssionItem> experssionItems) {
        if (this.experssionItems == null) {
            this.experssionItems = new ArrayList<>();
        }
        this.experssionItems.addAll(experssionItems);
        return this;
    }

    public static final class ExperssionItem {
        private String name;
        private String key;

        @Override
        public String toString() {
            return "ExpressionEntity{" +
                    "name='" + name + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        private ExperssionItem(Builder builder) {
            name = builder.name;
            key = builder.key;
        }

        public static final class Builder {
            private String name;
            private String key;

            public Builder() {
            }

            public Builder name(String val) {
                name = val;
                return this;
            }

            public Builder key(String val) {
                key = val;
                return this;
            }

            public ExperssionItem build() {
                return new ExperssionItem(this);
            }
        }
    }
}
