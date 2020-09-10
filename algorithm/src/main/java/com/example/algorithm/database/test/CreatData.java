package com.example.algorithm.database.test;

import android.content.Context;

import androidx.room.Room;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.log.AppLog;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/4
 * Time: 10:58
 */
public class CreatData {
    private static final String TAG = "CreatData";
    private final ChatMsgDao chatMsgDao;
    private final ChatGroupDao chatGroupDao;
    int index = 0;

    public void test(Context context) {

//        AppLocalDatabase test_data = Room.databaseBuilder(context, AppLocalDatabase.class, "test_data").fallbackToDestructiveMigration().build();
    }


    public CreatData(Context context) {
        ChatDataBase chatDataBase = ChatDataBase.getI(context);
        chatMsgDao = chatDataBase.chatMsgDao();
        chatGroupDao = chatDataBase.chatGroupDao();
    }

    public void findData() {
        List<DataChatGroup> groupByGroupIdOrPlayerId = chatGroupDao.getGroupByGroupIdOrPlayerId(2, 1);
//        List<DataChatGroup> groupByGroupIdOrPlayerId = chatGroupDao.getAll();
//        List<DataChatGroup> groupByGroupIdOrPlayerId = chatGroupDao.getGroupByGroupId(1);
        AppLog.i(TAG, Arrays.toString(groupByGroupIdOrPlayerId.toArray()));
    }

    public void insert() {
        for (int i = 0; i < 10; i++) {
            DataChatGroup build = null;

            if (i % 2 == 0) {
                build = new DataChatGroup.Builder()
                        .channelType(DataChatMsg.CHANNEL_GROUP_VALUE)
                        .groupId(i)
                        .groupWeights(1)
                        .iconPath("")
                        .name("")
                        .build();
            } else {
                build = new DataChatGroup.Builder()
                        .channelType(DataChatMsg.CHANNEL_PRIVATE_VALUE)
                        .playerId(i)
                        .groupWeights(1)
                        .iconPath("")
                        .name("")
                        .build();
            }
            long[] insert = chatGroupDao.insert(build);
            AppLog.i(TAG, "----->" + Arrays.toString(insert));
        }

    }

    public void initData() {
        for (int i = 0; i < 50; i++) {
            DataChatMsg.Builder builder = new DataChatMsg.Builder()
                    .receiver(i)
                    .timeStamp(System.currentTimeMillis())
                    .sender(11);
//            if (i % 2 == 0) {
//                builder.channelType(DataChatMsg.CHANNEL_GROUP_VALUE)
//                        .groupId(i);
//
//            } else {
            builder.channelType(DataChatMsg.CHANNEL_PRIVATE_VALUE)
                    .playerId(1);
//            }
            int i1 = i % 3;
            builder.contentType(DataChatMsg.TEXT)
                    .text("测试数据" + i);

            if (i1 == 0) {

            } else if (i1 == 1) {
                builder.contentType(DataChatMsg.VOICE)
                        .dataVoice(new DataVoice.Builder().duration(111)
                                .isLocal(true)
                                .localPath("测试路径")
                                .remoteUrl("测试URI")
                                .size(123)
                                .build());

            } else {
                builder.contentType(DataChatMsg.PICTURE)
                        .dataPicture(new DataPicture.Builder()
                                .humbnail_remote_url("测试humbnail_remote_url")
                                .image_remote_url("image_remote_url")
                                .size(111)
                                .thumbnail_size(2222)
                                .build());
            }
            DataChatMsg build = builder.build();
            chatMsgDao.insert(build);
        }
    }

    public void getData() {
        List<DataChatMsg> privateFomeGroupId = chatMsgDao.getPrivateFomePlayerIdPage(0, index, 10);
//        List<DataChatMsg> privateFomeGroupId = chatMsgDao.getAll();
        index = index + 10;
        AppLog.i(TAG, "=====>" + Arrays.toString(privateFomeGroupId.toArray()));
        AppLog.i(TAG, "=====>" + privateFomeGroupId.size(), index);
    }
}
