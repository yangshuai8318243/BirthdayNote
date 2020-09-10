package com.example.algorithm.database.test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 20:24
 */
@Entity(tableName = "data_voice")
public class DataVoice {
    @Override
    public String toString() {
        return "DataVoice{" +
                "voiceId=" + voiceId +
                ", sizeVoice=" + sizeVoice +
                ", remoteUrl='" + remoteUrl + '\'' +
                ", localPath='" + localPath + '\'' +
                ", duration=" + duration +
                ", isLocal=" + isLocal +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "voice_id")
    public long voiceId;

    @ColumnInfo(name = "voice_size")
    public int sizeVoice;//语音大小(单位:字节)
    @ColumnInfo(name = "remote_url")
    public String remoteUrl;//语音url
    @ColumnInfo(name = "local_path")
    public String localPath;//本地语音路径
    @ColumnInfo(name = "duration")
    public int duration;//播放时长(单位:毫秒)
    @ColumnInfo(name = "is_local")
    public boolean isLocal = false;//是否是本地资源

    public DataVoice() {
    }

    private DataVoice(Builder builder) {
        sizeVoice = builder.sizeVoice;
        remoteUrl = builder.remoteUrl;
        localPath = builder.localPath;
        duration = builder.duration;
        isLocal = builder.isLocal;
    }

    public static final class Builder {
        private int size;
        private String remoteUrl;
        private String localPath;
        private int duration;
        private boolean isLocal;
        private int sizeVoice;

        public Builder() {
        }

        public Builder size(int val) {
            size = val;
            return this;
        }

        public Builder remoteUrl(String val) {
            remoteUrl = val;
            return this;
        }

        public Builder localPath(String val) {
            localPath = val;
            return this;
        }

        public Builder duration(int val) {
            duration = val;
            return this;
        }

        public Builder isLocal(boolean val) {
            isLocal = val;
            return this;
        }

        public DataVoice build() {
            return new DataVoice(this);
        }

        public Builder sizeVoice(int val) {
            sizeVoice = val;
            return this;
        }
    }
}
