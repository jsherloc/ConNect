package com.example.james.connect;

import android.provider.BaseColumns;

/**
 * Created by james on 02/03/2018.
 */

public final class ContactManagementContract {

    private ContactManagementContract(){}

    public static class User implements BaseColumns{
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_USER_NAME = "Name";
        public static final String COLUMN_USER_NUMBER = "Number";
    }

    public static class ReportedNumbers implements BaseColumns{
        public static final String TABLE_NAME = "ReportedNumbers";
        public static final String COLUMN_REPORTED_NUMBER = "Number";
    }

    public static class BlockedNumbers implements BaseColumns{
        public static final String TABLE_NAME = "BlockedNumbers";
        public static final String COLUMN_BLOCKED_NUMBER = "Number";
    }

    public static class PermittedCountries implements BaseColumns{
        public static final String TABLE_NAME = "BlockedCountries";
        public static final String COLUMN_COUNTRY_NAME = "CountryName";
        public static final String COLUMN_COUNTRY_CALLING_CODE = "CallingCode";
        public static final String COLUMN_INTERNATIONAL_DIALING_PREFIX = "IDD";
    }

    public static class User_ReportedNumbers implements BaseColumns{
        public static final String TABLE_NAME = "UserReportedNumbers";
        public static final String COLUMN_USER_ID = "UserID";
        public static final String COLUMN_REPORTED_ID = "ReportedID";
    }

    public static class User_BlockedNumbers implements BaseColumns{
        public static final String TABLE_NAME = "BlockedNumbers";
        public static final String COLUMN_USER_ID = "UserID";
        public static final String COLUMN_BLOCKED_ID = "BlockedID";
    }

    public static class User_PermittedCountries implements BaseColumns{
        public static final String TABLE_NAME = "PermittedCountries";
        public static final String COLUMN_USER_ID = "UserID";
        public static final String COLUMN_COUNTRY_ID = "CountryID";
    }
}
