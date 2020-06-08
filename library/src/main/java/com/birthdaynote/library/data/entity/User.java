package com.birthdaynote.library.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    String name;
    Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class UserBuilder {
        private User user;

        public UserBuilder() {
            user = new User();
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withName(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder withId(Long id) {
            user.setId(id);
            return this;
        }

        public User build() {
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.id);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
