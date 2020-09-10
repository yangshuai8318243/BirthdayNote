package com.example.algorithm.database.test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 19:47
 */
@Entity(tableName = "data_group_info", indices = {@Index("group_id")})
@TypeConverters(MembersConverters.class)
public class DataGroupInfo {

    @Override
    public String toString() {
        return "DataGroupInfo{" +
                "groupId=" + groupId +
                ", groupAdmin=" + groupAdmin +
                ", groupName='" + groupName + '\'' +
                ", members=" + members +
                '}';
    }

    @PrimaryKey
    @ColumnInfo(name = "group_id")
    public long groupId;

    @ColumnInfo(name = "group_admin")
    public long groupAdmin;

    @ColumnInfo(name = "group_name")
    public String groupName;

    @ColumnInfo(name = "members")
    public List<Integer> members;

    public DataGroupInfo() {
    }

    private DataGroupInfo(Builder builder) {
        groupAdmin = builder.groupAdmin;
        groupName = builder.groupName;
        members = builder.members;
    }

    public static final class Builder {
        private long groupId;
        private long groupAdmin;
        private String groupName;
        private List<Integer> members;

        public Builder() {
        }

        public Builder groupId(long val) {
            groupId = val;
            return this;
        }

        public Builder groupAdmin(long val) {
            groupAdmin = val;
            return this;
        }

        public Builder groupName(String val) {
            groupName = val;
            return this;
        }

        public Builder members(List<Integer> val) {
            members = val;
            return this;
        }

        public DataGroupInfo build() {
            return new DataGroupInfo(this);
        }
    }
}
