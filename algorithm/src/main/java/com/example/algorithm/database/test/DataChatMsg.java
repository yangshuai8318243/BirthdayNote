package com.example.algorithm.database.test;

import androidx.annotation.IntDef;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 20:13
 */
@Entity(tableName = "data_chat_msg")
public class DataChatMsg {

    @IntDef({CHANNEL_WORLD_VALUE, CHANNEL_GROUP_VALUE, CHANNEL_PRIVATE_VALUE})
    public @interface ChannelType {
    }

    public final static int CHANNEL_WORLD_VALUE = 1;
    public final static int CHANNEL_GROUP_VALUE = 2;
    public final static int CHANNEL_PRIVATE_VALUE = 3;

    @IntDef({TEXT, EMOJI, BIAOQING, PICTURE, VOICE})
    public @interface ContentType {
    }

    public final static int TEXT = 1;
    public final static int EMOJI = 2;
    public final static int BIAOQING = 3;
    public final static int PICTURE = 4;
    public final static int VOICE = 5;

    @ContentType
    @ColumnInfo(name = "content_type")
    public int contentType;//内容类型

    public DataChatMsg() {
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id")
    public long messageId;//内容类型

    @ChannelType
    @ColumnInfo(name = "channel_type")
    public int channelType;//内容类型

    @ColumnInfo(name = "sender")
    public long sender;//发送者(玩家ID)

    @ColumnInfo(name = "receiver")
    public long receiver;//接受者(私聊频道:玩家ID,群频道:组ID，其它频道不进行设置)

    @ColumnInfo(name = "time_stamp")
    public long timeStamp;//发送时间戳

    @Override
    public String toString() {
        return "DataChatMsg{" +
                "contentType=" + contentType +
                ", messageId=" + messageId +
                ", channelType=" + channelType +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", timeStamp=" + timeStamp +
                ", playerId=" + playerId +
                ", groupId=" + groupId +
                ", dataPicture=" + dataPicture +
                ", dataVoice=" + dataVoice +
                ", text='" + text + '\'' +
                '}';
    }

    @ColumnInfo(name = "player_id")
    public int playerId;//玩家角色基本信息(客户端请求不设置,服务器推送会进行设置)

    @ColumnInfo(name = "group_id")
    public long groupId;//群组id

    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded
    public DataPicture dataPicture;//图片发送

    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded
    public DataVoice dataVoice;//语音发送

    @ColumnInfo(name = "text")
    public String text;//文字、表情发送

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    private DataChatMsg(Builder builder) {
        setContentType(builder.contentType);
        messageId = builder.messageId;
        channelType = builder.channelType;
        sender = builder.sender;
        receiver = builder.receiver;
        timeStamp = builder.timeStamp;
        groupId = builder.groupId;
        dataPicture = builder.dataPicture;
        dataVoice = builder.dataVoice;
        text = builder.text;
    }


    public static final class Builder {
        private int contentType;
        private long messageId;
        private int channelType;
        private long sender;
        private long receiver;
        private long timeStamp;
        private int playerId;
        private long groupId;
        private DataPicture dataPicture;
        private DataVoice dataVoice;
        private String text;

        public Builder() {
        }

        public Builder contentType(int val) {
            contentType = val;
            return this;
        }

        public Builder messageId(long val) {
            messageId = val;
            return this;
        }

        public Builder channelType(int val) {
            channelType = val;
            return this;
        }

        public Builder sender(long val) {
            sender = val;
            return this;
        }

        public Builder receiver(long val) {
            receiver = val;
            return this;
        }

        public Builder timeStamp(long val) {
            timeStamp = val;
            return this;
        }

        public Builder playerId(int val) {
            playerId = val;
            return this;
        }

        public Builder groupId(long val) {
            groupId = val;
            return this;
        }

        public Builder dataPicture(DataPicture val) {
            dataPicture = val;
            return this;
        }

        public Builder dataVoice(DataVoice val) {
            dataVoice = val;
            return this;
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public DataChatMsg build() {
            return new DataChatMsg(this);
        }
    }
}
