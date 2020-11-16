package com.example.modelpaper;

import android.provider.BaseColumns;

public class UserProfile {
    private UserProfile() {

    }

    public class Users implements BaseColumns {
        public static final String TABLE_NAME = "Model";
        //cols
        public static final String COLS_1 = "ID";
        public static final String COLS_2 = "UN";
        public static final String COLS_3 = "DOB";
        public static final String COLS_4 = "PW";
        public static final String COLS_5 = "Gender";
    }
}
