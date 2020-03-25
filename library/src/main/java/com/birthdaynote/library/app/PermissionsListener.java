package com.birthdaynote.library.app;

public interface PermissionsListener {
    void onSuccessPermissions(String permission);

    void onFailurePermissions(String permission);

    void requestPermissions(String[] permissions);

}
