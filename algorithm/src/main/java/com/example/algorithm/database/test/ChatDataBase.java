package com.example.algorithm.database.test;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/4
 * Time: 10:35
 */

@TypeConverters(value = {MembersConverters.class})
@Database(entities = {DataChatGroup.class, DataChatMsg.class, DataGroupInfo.class, DataPicture.class, DataPlayerInfo.class, DataVoice.class}, version = 5, exportSchema = false)
public abstract class ChatDataBase extends RoomDatabase {
    public abstract ChatGroupDao chatGroupDao();

    public abstract ChatMsgDao chatMsgDao();

    private static ChatDataBase mAppDataBase;

    public static ChatDataBase getI(Context context) { //实现单例模式
        if (mAppDataBase == null) {
            //data.db 是你的数据库名称
            mAppDataBase = Room.databaseBuilder(context, ChatDataBase.class, "chat_data").build();
        }
        return mAppDataBase;
    }
}
