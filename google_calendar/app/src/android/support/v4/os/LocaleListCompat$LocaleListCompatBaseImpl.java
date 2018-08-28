// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import java.util.Locale;

// Referenced classes of package android.support.v4.os:
//            LocaleListInterface, LocaleListHelper, LocaleListCompat

final class mLocaleList
    implements LocaleListInterface
{

    private LocaleListHelper mLocaleList;

    public final boolean equals(Object obj)
    {
        return mLocaleList.equals(LocaleListCompat.IMPL.getLocaleList());
    }

    public final Locale get(int i)
    {
        LocaleListHelper localelisthelper = mLocaleList;
        if (i >= 0 && i < localelisthelper.mList.length)
        {
            return localelisthelper.mList[i];
        } else
        {
            return null;
        }
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
        mLocaleList = new LocaleListHelper(alocale);
    }

    public final String toString()
    {
        return mLocaleList.toString();
    }

    ()
    {
        mLocaleList = new LocaleListHelper(new Locale[0]);
    }
}
