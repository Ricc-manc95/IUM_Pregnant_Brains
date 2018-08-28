// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;


// Referenced classes of package com.google.analytics.tracking.android:
//            Log

public final class mFormatter
{

    public final String mDefaultValue;
    public final  mFormatter;
    private final String mUrlParam;

    public final String getUrlParam(String s)
    {
        Object obj = null;
        if (s.contains("*"))
        {
            String s1 = mUrlParam;
            String as[] = s.split("\\*");
            s = obj;
            if (as.length > 1)
            {
                int i;
                try
                {
                    i = Integer.parseInt(as[1]);
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    Log.w((new StringBuilder("Unable to parse slot for url parameter ")).append(s1).toString());
                    return null;
                }
                s = (new StringBuilder()).append(s1).append(i).toString();
            }
            return s;
        } else
        {
            return mUrlParam;
        }
    }

    public (String s, String s1,  )
    {
        mUrlParam = s;
        mDefaultValue = s1;
        mFormatter = ;
    }
}
