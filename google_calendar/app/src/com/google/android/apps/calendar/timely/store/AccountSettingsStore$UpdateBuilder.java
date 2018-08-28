// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.content.ContentValues;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            BirthdaySetting

public final class triggerSyncAdapterUpdate
{

    public String jsonFlags;
    public boolean triggerSyncAdapterUpdate;
    public final ContentValues values = new ContentValues();

    public final triggerSyncAdapterUpdate setBirthdaySetting(BirthdaySetting birthdaysetting)
    {
        boolean flag = false;
        ContentValues contentvalues = values;
        int i;
        if (birthdaysetting.visibility)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("settingBirthdayVisibility", Integer.valueOf(i));
        if (birthdaysetting.visibility)
        {
            ContentValues contentvalues1 = values;
            i = ((flag) ? 1 : 0);
            if (birthdaysetting.includeGplusBirthday)
            {
                i = 1;
            }
            contentvalues1.put("settingBirthdayIncludeGplus", Integer.valueOf(i));
        }
        triggerSyncAdapterUpdate = true;
        return this;
    }

    public ()
    {
        triggerSyncAdapterUpdate = false;
    }
}
