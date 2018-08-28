// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;


// Referenced classes of package com.google.android.gms.chips:
//            GmsChipsLoggingFlags

final class AutoValue_GmsChipsLoggingFlags extends GmsChipsLoggingFlags
{

    private final boolean loggingDataSourceEnabled;
    private final boolean loggingErrorsEnabled;

    AutoValue_GmsChipsLoggingFlags(boolean flag, boolean flag1)
    {
        loggingErrorsEnabled = flag;
        loggingDataSourceEnabled = flag1;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof GmsChipsLoggingFlags)
            {
                if (loggingErrorsEnabled != ((GmsChipsLoggingFlags) (obj = (GmsChipsLoggingFlags)obj)).loggingErrorsEnabled() || loggingDataSourceEnabled != ((GmsChipsLoggingFlags) (obj)).loggingDataSourceEnabled())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        char c1 = '\u04CF';
        char c;
        if (loggingErrorsEnabled)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (!loggingDataSourceEnabled)
        {
            c1 = '\u04D5';
        }
        return (c ^ 0xf4243) * 0xf4243 ^ c1;
    }

    public final boolean loggingDataSourceEnabled()
    {
        return loggingDataSourceEnabled;
    }

    public final boolean loggingErrorsEnabled()
    {
        return loggingErrorsEnabled;
    }

    public final String toString()
    {
        boolean flag = loggingErrorsEnabled;
        boolean flag1 = loggingDataSourceEnabled;
        return (new StringBuilder(80)).append("GmsChipsLoggingFlags{loggingErrorsEnabled=").append(flag).append(", loggingDataSourceEnabled=").append(flag1).append("}").toString();
    }
}
