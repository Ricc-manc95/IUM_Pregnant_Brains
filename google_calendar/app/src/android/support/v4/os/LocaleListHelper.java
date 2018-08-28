// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;

import java.util.HashSet;
import java.util.Locale;

final class LocaleListHelper
{

    private static final Locale sEmptyList[];
    public final Locale mList[];

    transient LocaleListHelper(Locale alocale[])
    {
        if (alocale.length == 0)
        {
            mList = sEmptyList;
            return;
        }
        Locale alocale1[] = new Locale[alocale.length];
        HashSet hashset = new HashSet();
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < alocale.length; i++)
        {
            Locale locale = alocale[i];
            if (locale == null)
            {
                throw new NullPointerException((new StringBuilder("list[")).append(i).append("] is null").toString());
            }
            if (hashset.contains(locale))
            {
                throw new IllegalArgumentException((new StringBuilder("list[")).append(i).append("] is a repetition").toString());
            }
            locale = (Locale)locale.clone();
            alocale1[i] = locale;
            StringBuilder stringbuilder1 = new StringBuilder();
            stringbuilder1.append(locale.getLanguage());
            String s = locale.getCountry();
            if (s != null && !s.isEmpty())
            {
                stringbuilder1.append("-");
                stringbuilder1.append(locale.getCountry());
            }
            stringbuilder.append(stringbuilder1.toString());
            if (i < alocale.length - 1)
            {
                stringbuilder.append(',');
            }
            hashset.add(locale);
        }

        mList = alocale1;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (obj != this) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (obj instanceof LocaleListHelper)
        {
            obj = ((LocaleListHelper)obj).mList;
            flag = flag1;
            if (mList.length == obj.length)
            {
                int i = 0;
label0:
                do
                {
label1:
                    {
                        if (i >= mList.length)
                        {
                            break label1;
                        }
                        flag = flag1;
                        if (!mList[i].equals(obj[i]))
                        {
                            break label0;
                        }
                        i++;
                    }
                } while (true);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        return true;
    }

    public final int hashCode()
    {
        int j = 1;
        for (int i = 0; i < mList.length; i++)
        {
            j = j * 31 + mList[i].hashCode();
        }

        return j;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        for (int i = 0; i < mList.length; i++)
        {
            stringbuilder.append(mList[i]);
            if (i < mList.length - 1)
            {
                stringbuilder.append(',');
            }
        }

        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    static 
    {
        sEmptyList = new Locale[0];
        new LocaleListHelper(new Locale[0]);
        new Locale("en", "XA");
        new Locale("ar", "XB");
        if (!"en-Latn".contains("-")) goto _L2; else goto _L1
_L1:
        String as[] = "en-Latn".split("-", -1);
        if (as.length <= 2) goto _L4; else goto _L3
_L3:
        new Locale(as[0], as[1], as[2]);
_L6:
        new Object();
        return;
_L4:
        if (as.length > 1)
        {
            new Locale(as[0], as[1]);
            continue; /* Loop/switch isn't completed */
        }
        if (as.length != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        new Locale(as[0]);
        continue; /* Loop/switch isn't completed */
_L2:
        if ("en-Latn".contains("_"))
        {
            String as1[] = "en-Latn".split("_", -1);
            if (as1.length > 2)
            {
                new Locale(as1[0], as1[1], as1[2]);
                continue; /* Loop/switch isn't completed */
            }
            if (as1.length > 1)
            {
                new Locale(as1[0], as1[1]);
                continue; /* Loop/switch isn't completed */
            }
            if (as1.length != 1)
            {
                break; /* Loop/switch isn't completed */
            }
            new Locale(as1[0]);
        } else
        {
            new Locale("en-Latn");
        }
        if (true) goto _L6; else goto _L5
_L5:
        throw new IllegalArgumentException((new StringBuilder("Can not parse language tag: [")).append("en-Latn").append("]").toString());
    }
}
