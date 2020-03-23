package com.birthdaynote.library.data.entity;

public class ErrorData extends Exception {
    private int code;
    private String mesage;

    private ErrorData(Builder builder) {
        code = builder.code;
        mesage = builder.mesage;
    }

    public int getCode() {
        return code;
    }

    public String getMesage() {
        return mesage;
    }

    public static final class Builder {
        private int code = -1;
        private String mesage = "";

        public Builder() {
        }

        public Builder code(int val) {
            code = val;
            return this;
        }

        public Builder mesage(String val) {
            mesage = val;
            return this;
        }

        public ErrorData build() {
            return new ErrorData(this);
        }
    }
}
