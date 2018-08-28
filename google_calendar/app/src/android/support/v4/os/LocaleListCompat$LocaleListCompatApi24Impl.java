// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import android.os.LocaleList;
import java.util.Locale;

// Referenced classes of package android.support.v4.os:
//            LocaleListInterface, LocaleListCompat

final class mLocaleList
    implements LocaleListInterface
{

    private LocaleList mLocaleList;

    public final boolean equals(Object obj)
    {
        return mLocaleList.equals(LocaleListCompat.IMPL.getLocaleList());
    }

    public final Locale get(int i)
    {
        return mLocaleList.get(i);
    }

    public final Object getLocaleList()
    {
        return mLocaleList;
    }

    public final int hashCode()
    {
        return mLocaleList.hashCode();
    }

    public final transient void setLocaleList(Locale alocale[])
    {
        mLocaleList = new LocaleList(alocale);
    }

    public final String toString()
    {
        return mLocaleList.toString();
    }

    ()
    {
        mLocaleList = new LocaleList(new Locale[0]);
    }
}
