package com.example.algorithm.database.test;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/4
 * Time: 10:23
 */
@Dao
public abstract class ChatMsgDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insert(DataChatMsg... data);

    @Update
    public abstract void update(DataChatMsg... data);

    @Delete
    public abstract void delete(DataChatMsg... data);

    @Query("select * from data_chat_msg  WHERE player_id=:playerId")
    public abstract List<DataChatMsg> getPrivateFomePlayerId(long playerId);

    @Query("select * from data_chat_msg")
    public abstract List<DataChatMsg> getAll();


    @Query("select * from data_chat_msg  WHERE player_id=:playerId  ORDER BY time_stamp DESC LIMIT :startIndex,:num")
    public abstract List<DataChatMsg> getPrivateFomePlayerIdPage(long playerId, int startIndex, int num);

    @Query("select * from data_chat_msg  WHERE group_id=:groupId")
    public abstract List<DataChatMsg> getPrivateFomeGroupId(long groupId);

}
