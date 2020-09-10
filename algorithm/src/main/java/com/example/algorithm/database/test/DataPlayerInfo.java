package com.example.algorithm.database.test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 20:02
 */
@Entity(tableName = "data_player_info", indices = {@Index("player_id")})
public class DataPlayerInfo {
    /**
     * 玩家角色id
     */
    @PrimaryKey
    @ColumnInfo(name = "player_id")
    public long playerId;
    /**
     * 玩家角色名称
     */
    @ColumnInfo(name = "name")
    public String name;
    /**
     * 玩家角色等级
     */
    @ColumnInfo(name = "level")
    public int level;
    /**
     * 玩家头像url
     */
    @ColumnInfo(name = "head_url")
    public String headUrl;
    /**
     * 玩家头像框url
     */
    @ColumnInfo(name = "head_frame_url")
    public String headFrameUrl;
    /**
     * 聊天开启完成任务的id
     */
    @ColumnInfo(name = "task_id")
    public int taskId;

    public DataPlayerInfo() {
    }

    private DataPlayerInfo(Builder builder) {
        playerId = builder.playerId;
        name = builder.name;
        level = builder.level;
        headUrl = builder.headUrl;
        headFrameUrl = builder.headFrameUrl;
        taskId = builder.taskId;
    }

    @Override
    public String toString() {
        return "DataPlayerInfo{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", headUrl='" + headUrl + '\'' +
                ", headFrameUrl='" + headFrameUrl + '\'' +
                ", taskId=" + taskId +
                '}';
    }

    public static final class Builder {
        private long playerId;
        private String name;
        private int level;
        private String headUrl;
        private String headFrameUrl;
        private int taskId;

        public Builder() {
        }

        public Builder playerId(long val) {
            playerId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder level(int val) {
            level = val;
            return this;
        }

        public Builder headUrl(String val) {
            headUrl = val;
            return this;
        }

        public Builder headFrameUrl(String val) {
            headFrameUrl = val;
            return this;
        }

        public Builder taskId(int val) {
            taskId = val;
            return this;
        }

        public DataPlayerInfo build() {
            return new DataPlayerInfo(this);
        }
    }
}
