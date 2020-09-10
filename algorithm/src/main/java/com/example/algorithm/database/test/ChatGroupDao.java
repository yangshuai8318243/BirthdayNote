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
 * Time: 10:34
 */
@Dao
public abstract class ChatGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insert(DataChatGroup... data);

    @Update
    public abstract void update(DataChatGroup... data);

    @Delete
    public abstract void delete(DataChatGroup... data);

    @Query("select * from data_chat_group")
    public abstract List<DataChatGroup> getAll();

    @Query("select * from data_chat_group WHERE group_id=:groupId OR player_id=:playerId ORDER BY group_weights DESC")
    public abstract List<DataChatGroup> getGroupByGroupIdOrPlayerId(long groupId, long playerId);


    @Query("select * from data_chat_group WHERE player_id=:playerId ORDER BY group_weights DESC")
    public abstract List<DataChatGroup> getGroupByPlayerId(long playerId);

    @Query("select * from data_chat_group WHERE group_id=:groupId ORDER BY group_weights DESC")
    public abstract List<DataChatGroup> getGroupByGroupId(long groupId);
}
