// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.common.rtc;

import com.google.common.base.Platform;

public final class ConferenceDescriptionUtils
{

    public static String removeAutoGeneratedDescription(String s)
    {
        int i;
        int j;
        i = s.indexOf("-::~:~::~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~::~:~::-");
        j = -1;
        do
        {
            int k = s.indexOf("-::~:~::~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~:~::~:~::-", i + 87);
            if (k == -1)
            {
                break;
            }
            j = i;
            i = k;
        } while (true);
        if (i != -1 && j != -1) goto _L2; else goto _L1
_L1:
        return s;
_L2:
        String s2;
        String as[];
        int l;
        s2 = s.substring(0, j);
        as = new String[4];
        as[0] = "\n\n";
        as[1] = "<br><br>";
        as[2] = "\n";
        as[3] = "<br>";
        l = as.length;
        j = 0;
_L4:
label0:
        {
            String s1 = s2;
            if (j < l)
            {
                s1 = as[j];
                if (!s2.endsWith(s1))
                {
                    break label0;
                }
                s1 = s2.substring(0, s2.length() - s1.length());
            }
            s2 = s.substring(i + 87).trim();
            s = s1;
            if (!Platform.stringIsNullOrEmpty(s2))
            {
                if (s1.endsWith("\n"))
                {
                    s = String.valueOf(s1);
                    s1 = String.valueOf(s2);
                    if (s1.length() != 0)
                    {
                        return s.concat(s1);
                    } else
                    {
                        return new String(s);
                    }
                } else
                {
                    return (new StringBuilder(String.valueOf(s1).length() + 1 + String.valueOf(s2).length())).append(s1).append("\n").append(s2).toString();
                }
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        j++;
          goto _L4
    }
}