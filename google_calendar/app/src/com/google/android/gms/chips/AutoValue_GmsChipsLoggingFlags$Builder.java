// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;


// Referenced classes of package com.google.android.gms.chips:
//            AutoValue_GmsChipsLoggingFlags, GmsChipsLoggingFlags

final class I extends I
{

    private Boolean loggingDataSourceEnabled;
    public Boolean loggingErrorsEnabled;

    public final GmsChipsLoggingFlags build()
    {
        String s = "";
        if (loggingErrorsEnabled == null)
        {
            s = String.valueOf("").concat(" loggingErrorsEnabled");
        }
        String s2 = s;
        if (loggingDataSourceEnabled == null)
        {
            s2 = String.valueOf(s).concat(" loggingDataSourceEnabled");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_GmsChipsLoggingFlags(loggingErrorsEnabled.booleanValue(), loggingDataSourceEnabled.booleanValue());
        }
    }

    public final loggingDataSourceEnabled setLoggingDataSourceEnabled(boolean flag)
    {
        loggingDataSourceEnabled = Boolean.valueOf(false);
        return this;
    }

    I()
    {
    }
}
