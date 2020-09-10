package com.example.algorithm.database.test;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 19:52
 */
public class MembersConverters {
    @TypeConverter
    public List<Integer> stringToList(String strings) {
        return new Gson().fromJson(strings, new TypeToken<List<Integer>>() {
        }.getType());
    }

    @TypeConverter
    public String listToString(List<Integer> integers) {
        return new Gson().toJson(integers);
    }

}
