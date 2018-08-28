// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import android.content.res.Configuration;
import android.os.LocaleList;
import java.util.Locale;

// Referenced classes of package android.support.v4.os:
//            LocaleListCompat, LocaleListInterface

public final class ConfigurationCompat
{

    public static LocaleListCompat getLocales(Configuration configuration)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            configuration = configuration.getLocales();
            LocaleListCompat localelistcompat = new LocaleListCompat();
            if (configuration instanceof LocaleList)
            {
                LocaleListCompat.setLocaleList((LocaleList)configuration);
            }
            return localelistcompat;
        } else
        {
            configuration = configuration.locale;
            LocaleListCompat localelistcompat1 = new LocaleListCompat();
            LocaleListCompat.IMPL.setLocaleList(new Locale[] {
                configuration
            });
            return localelistcompat1;
        }
    }
}
