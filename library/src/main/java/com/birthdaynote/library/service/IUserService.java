package com.birthdaynote.library.service;

import com.birthdaynote.library.data.entity.User;

public interface IUserService {
    User getUser();

    boolean setUser(User user);
}
