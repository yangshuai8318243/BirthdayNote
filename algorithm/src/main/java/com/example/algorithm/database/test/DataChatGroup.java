package com.example.algorithm.database.test;

import androidx.annotation.IntDef;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 19:22
 */
@Entity(tableName = "data_chat_group", indices = {@Index("id_chat_group")})
public class DataChatGroup {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_chat_group")
    public long idChatGroup;

    @ColumnInfo(name = "group_weights")
    public int groupWeights;

    @Override
    public String toString() {
        return "DataChatGroup{" +
                "idChatGroup=" + idChatGroup +
                ", groupWeights=" + groupWeights +
                ", name='" + name + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", playerId=" + playerId +
                ", groupId=" + groupId +
                ", channelType=" + channelType +
                '}';
    }

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "icon_path")
    public String iconPath;

    @ColumnInfo(name = "player_id")
    public long playerId;

    @ColumnInfo(name = "group_id")
    public long groupId;

    @ColumnInfo(name = "channel_type")
    @DataChatMsg.ChannelType
    public int channelType;

    public DataChatGroup() {
    }

    private DataChatGroup(Builder builder) {
        idChatGroup = builder.idChatGroup;
        groupWeights = builder.groupWeights;
        name = builder.name;
        iconPath = builder.iconPath;
        playerId = builder.playerId;
        groupId = builder.groupId;
        channelType = builder.channelType;
    }


    public static final class Builder {
        private long idChatGroup;
        private int groupWeights;
        private String name;
        private String iconPath;
        private long playerId;
        private long groupId;
        private int channelType;

        public Builder() {
        }

        public Builder idChatGroup(long val) {
            idChatGroup = val;
            return this;
        }

        public Builder groupWeights(int val) {
            groupWeights = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder iconPath(String val) {
            iconPath = val;
            return this;
        }

        public Builder playerId(long val) {
            playerId = val;
            return this;
        }

        public Builder groupId(long val) {
            groupId = val;
            return this;
        }

        public Builder channelType(int val) {
            channelType = val;
            return this;
        }

        public DataChatGroup build() {
            return new DataChatGroup(this);
        }
    }
}
