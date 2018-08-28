// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.phenotype.PhenotypeFlag;

// Referenced classes of package com.google.android.apps.calendar.config.remote:
//            RemoteFeature

public final class NewNotificationsFeature extends RemoteFeature
{

    private String overriddenCode;
    private Boolean supportsHabits;
    private final PhenotypeFlag supportsHabitsFlag;

    public NewNotificationsFeature()
    {
        super("NewNotifications", "NNTZ", false);
        com.google.android.gms.phenotype.PhenotypeFlag.Factory factory = super.flagBuilder;
        String s = String.valueOf(factory.zzcaQ);
        String s1 = String.valueOf("supports_habits");
        String s2;
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        s1 = String.valueOf(factory.zzcaR);
        s2 = String.valueOf("supports_habits");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        supportsHabitsFlag = PhenotypeFlag.zzb(s, s1, factory.zzcaO, factory.zzagh, false);
    }

    public final String getCode()
    {
        if (overriddenCode == null)
        {
            StringBuilder stringbuilder = new StringBuilder(super.getCode());
            if (getSupportsHabits().booleanValue())
            {
                stringbuilder.append("H");
            }
            overriddenCode = stringbuilder.toString();
        }
        return overriddenCode;
    }

    public final Boolean getSupportsHabits()
    {
        if (supportsHabits == null)
        {
            Object obj = String.format("%s__%s", new Object[] {
                super.code, "supports_habits"
            });
            if (LogUtils.isLoggableFixed(((String) (obj)), 2))
            {
                obj = Boolean.valueOf(true);
            } else
            if (!LogUtils.isLoggableFixed(((String) (obj)), 4))
            {
                obj = Boolean.valueOf(false);
            } else
            {
                obj = null;
            }
            supportsHabits = ((Boolean) (obj));
        }
        if (supportsHabits == null)
        {
            supportsHabits = (Boolean)supportsHabitsFlag.get();
        }
        return supportsHabits;
    }
}
