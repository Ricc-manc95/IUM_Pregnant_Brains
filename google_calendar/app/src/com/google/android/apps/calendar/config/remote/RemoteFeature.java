// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.config.remote;

import com.android.calendarcommon2.LogUtils;
import com.google.android.gms.phenotype.PhenotypeFlag;

public class RemoteFeature
{

    public final String code;
    private Boolean enabled;
    public final PhenotypeFlag enabledFlag;
    public final com.google.android.gms.phenotype.PhenotypeFlag.Factory flagBuilder;

    RemoteFeature(String s, String s1, boolean flag)
    {
        code = s1;
        s1 = new com.google.android.gms.phenotype.PhenotypeFlag.Factory("phenotype_preferences");
        s = String.format("%s__", new Object[] {
            s
        });
        flagBuilder = new com.google.android.gms.phenotype.PhenotypeFlag.Factory(((com.google.android.gms.phenotype.PhenotypeFlag.Factory) (s1)).zzcaO, ((com.google.android.gms.phenotype.PhenotypeFlag.Factory) (s1)).zzagh, ((com.google.android.gms.phenotype.PhenotypeFlag.Factory) (s1)).zzcaQ, s);
        com.google.android.gms.phenotype.PhenotypeFlag.Factory factory = flagBuilder;
        s = String.valueOf(factory.zzcaQ);
        s1 = String.valueOf("enabled");
        String s2;
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        s1 = String.valueOf(factory.zzcaR);
        s2 = String.valueOf("enabled");
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        enabledFlag = PhenotypeFlag.zzb(s, s1, factory.zzcaO, factory.zzagh, flag);
    }

    protected final PhenotypeFlag buildFlag(String s, int i)
    {
        com.google.android.gms.phenotype.PhenotypeFlag.Factory factory = flagBuilder;
        String s1 = String.valueOf(factory.zzcaQ);
        String s2 = String.valueOf(s);
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        s2 = String.valueOf(factory.zzcaR);
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s2.concat(s);
        } else
        {
            s = new String(s2);
        }
        return PhenotypeFlag.zzb(s1, s, factory.zzcaO, factory.zzagh, i);
    }

    protected final PhenotypeFlag buildFlag(String s, boolean flag)
    {
        com.google.android.gms.phenotype.PhenotypeFlag.Factory factory = flagBuilder;
        String s1 = String.valueOf(factory.zzcaQ);
        String s2 = String.valueOf(s);
        if (s2.length() != 0)
        {
            s1 = s1.concat(s2);
        } else
        {
            s1 = new String(s1);
        }
        s2 = String.valueOf(factory.zzcaR);
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = s2.concat(s);
        } else
        {
            s = new String(s2);
        }
        return PhenotypeFlag.zzb(s1, s, factory.zzcaO, factory.zzagh, flag);
    }

    public final boolean enabled()
    {
        if (enabled == null)
        {
            Object obj = code;
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
            enabled = ((Boolean) (obj));
        }
        if (enabled == null)
        {
            enabled = (Boolean)enabledFlag.get();
        }
        return enabled.booleanValue();
    }

    public String getCode()
    {
        return code;
    }

    protected final Boolean getFlagOverride(String s)
    {
        s = String.format("%s__%s", new Object[] {
            code, s
        });
        if (LogUtils.isLoggableFixed(s, 2))
        {
            return Boolean.valueOf(true);
        }
        if (!LogUtils.isLoggableFixed(s, 4))
        {
            return Boolean.valueOf(false);
        } else
        {
            return null;
        }
    }
}
