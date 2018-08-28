// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.database.Cursor;
import android.text.TextUtils;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            TimelyStoreUtils, BirthdaySetting

public final class AccountSettings
{

    public boolean autoAddHangouts;
    public BirthdaySetting birthdaySetting;
    public int defaultEventDuration;
    public int holidayColor;
    public int qualityOfService;
    public String smartmailSetting;
    public int taskColor;
    public boolean tasksVisible;
    public String timezoneId;

    public AccountSettings()
    {
        autoAddHangouts = false;
        qualityOfService = 0;
        tasksVisible = true;
        taskColor = 0xff4184f3;
    }

    static AccountSettings createFromCursor(Cursor cursor)
    {
        String s;
        int i;
        boolean flag;
        boolean flag1;
        flag1 = true;
        AccountSettings accountsettings = new AccountSettings();
        if (cursor.getInt(cursor.getColumnIndex("autoAddHangouts")) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        accountsettings.autoAddHangouts = flag;
        s = cursor.getString(cursor.getColumnIndex("qualityOfService"));
        if (TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        s.hashCode();
        JVM INSTR lookupswitch 4: default 108
    //                   -1039745817: 370
    //                   107348: 384
    //                   3202466: 356
    //                   3387192: 398;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        i = -1;
_L13:
        i;
        JVM INSTR tableswitch 0 3: default 140
    //                   0 412
    //                   1 417
    //                   2 422
    //                   3 427;
           goto _L2 _L8 _L9 _L10 _L11
_L2:
        i = 0;
_L17:
        accountsettings.qualityOfService = i;
        if (cursor.getInt(cursor.getColumnIndex("tasksselected")) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        accountsettings.tasksVisible = flag;
        accountsettings.taskColor = TimelyStoreUtils.colorHexToInt(cursor.getString(cursor.getColumnIndex("taskscolor")));
        accountsettings.timezoneId = cursor.getString(cursor.getColumnIndex("timezone"));
        s = cursor.getString(cursor.getColumnIndex("holidayscolor"));
        if (s != null)
        {
            accountsettings.holidayColor = TimelyStoreUtils.colorHexToInt(s);
        }
        if (cursor.getInt(cursor.getColumnIndex("defaultNoEndTime")) == 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = -1;
        } else
        {
            i = cursor.getInt(cursor.getColumnIndex("defaultEventLength"));
        }
        accountsettings.defaultEventDuration = i;
        if (cursor.getInt(cursor.getColumnIndex("settingBirthdayVisibility")) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || cursor.getInt(cursor.getColumnIndex("settingBirthdayIncludeGplus")) != 1)
        {
            flag1 = false;
        }
        accountsettings.birthdaySetting = new BirthdaySetting(flag, flag1);
        accountsettings.smartmailSetting = cursor.getString(cursor.getColumnIndex("smartMailDelivery"));
        return accountsettings;
_L6:
        if (!s.equals("high")) goto _L3; else goto _L12
_L12:
        i = 0;
          goto _L13
_L4:
        if (!s.equals("normal")) goto _L3; else goto _L14
_L14:
        i = 1;
          goto _L13
_L5:
        if (!s.equals("low")) goto _L3; else goto _L15
_L15:
        i = 2;
          goto _L13
_L7:
        if (!s.equals("none")) goto _L3; else goto _L16
_L16:
        i = 3;
          goto _L13
_L8:
        i = 1;
          goto _L17
_L9:
        i = 2;
          goto _L17
_L10:
        i = 3;
          goto _L17
_L11:
        i = 4;
          goto _L17
    }
}
