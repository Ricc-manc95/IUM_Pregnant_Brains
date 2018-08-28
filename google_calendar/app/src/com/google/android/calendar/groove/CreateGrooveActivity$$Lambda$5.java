// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.google.android.calendar.Utils;
import com.google.android.calendar.api.settings.SettingsUtils;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls2
    implements Function
{

    private final CreateGrooveActivity arg$1;
    private final String arg$2;

    public final Object apply(Object obj)
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        String s = arg$2;
        boolean flag;
        if (((Integer)obj).intValue() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            SettingsUtils.updateTimezoneSettings(AccountUtil.newGoogleAccount(s), Utils.getTimeZoneId(creategrooveactivity));
        }
        return null;
    }

    (CreateGrooveActivity creategrooveactivity, String s)
    {
        arg$1 = creategrooveactivity;
        arg$2 = s;
    }
}
