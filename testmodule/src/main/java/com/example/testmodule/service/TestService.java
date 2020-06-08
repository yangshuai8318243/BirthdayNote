package com.example.testmodule.service;

import com.birthdaynote.library.data.entity.User;
import com.birthdaynote.library.service.IUserService;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.sankuai.waimai.router.annotation.RouterService;

@RouterService(interfaces = IUserService.class, key = RouterConstants.TEST_SERVICE1)
public class TestService implements IUserService {
    private User data;

    @Override
    public User getUser() {
        if (data == null) {
            data = new User.UserBuilder().withId(123333333333L).withName("111111").build();
        }
        return data;
    }

    @Override
    public boolean setUser(User user) {
        if (user != null) {
            data = user;
            return true;
        }
        return false;
    }
}
