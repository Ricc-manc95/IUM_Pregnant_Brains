// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import android.os.LocaleList;
import java.util.Locale;

// Referenced classes of package android.support.v4.os:
//            LocaleListInterface

public final class LocaleListCompat
{

    public static final LocaleListInterface IMPL;

    LocaleListCompat()
    {
    }

    static void setLocaleList(LocaleList localelist)
    {
        int j = localelist.size();
        if (j > 0)
        {
            Locale alocale[] = new Locale[j];
            for (int i = 0; i < j; i++)
            {
                alocale[i] = localelist.get(i);
            }

            IMPL.setLocaleList(alocale);
        }
    }

    public final boolean equals(Object obj)
    {
        return IMPL.equals(obj);
    }

    public final int hashCode()
    {
        return IMPL.hashCode();
    }

    public final String toString()
    {
        return IMPL.toString();
    }

    static 
    {
        new LocaleListCompat();
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            IMPL = new LocaleListCompatApi24Impl();
        } else
        {
            IMPL = new LocaleListCompatBaseImpl();
        }
    }

    private class LocaleListCompatApi24Impl
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

        LocaleListCompatApi24Impl()
        {
            mLocaleList = new LocaleList(new Locale[0]);
        }
    }


    private class LocaleListCompatBaseImpl
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

        LocaleListCompatBaseImpl()
        {
            mLocaleList = new LocaleListHelper(new Locale[0]);
        }
    }

}
