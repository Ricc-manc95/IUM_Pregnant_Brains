// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class Utils
{

    public static String filterCampaign(String s)
    {
        int i = 0;
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        Object obj = s;
        if (s.contains("?"))
        {
            obj = s.split("[\\?]")[1];
        }
        StringBuilder stringbuilder;
        if (((String) (obj)).contains("%3D"))
        {
            try
            {
                s = URLDecoder.decode(((String) (obj)), "UTF-8");
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return null;
            }
        } else
        {
            s = ((String) (obj));
            if (!((String) (obj)).contains("="))
            {
                return null;
            }
        }
        s = parseURLParameters(s);
        obj = new String[9];
        obj[0] = "dclid";
        obj[1] = "utm_source";
        obj[2] = "gclid";
        obj[3] = "utm_campaign";
        obj[4] = "utm_medium";
        obj[5] = "utm_term";
        obj[6] = "utm_content";
        obj[7] = "utm_id";
        obj[8] = "gmob_t";
        stringbuilder = new StringBuilder();
        for (; i < 9; i++)
        {
            if (!TextUtils.isEmpty((CharSequence)s.get(obj[i])))
            {
                if (stringbuilder.length() > 0)
                {
                    stringbuilder.append("&");
                }
                stringbuilder.append(obj[i]).append("=").append((String)s.get(obj[i]));
            }
        }

        return stringbuilder.toString();
    }

    static String getLanguage(Locale locale)
    {
        while (locale == null || TextUtils.isEmpty(locale.getLanguage())) 
        {
            return null;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(locale.getLanguage().toLowerCase());
        if (!TextUtils.isEmpty(locale.getCountry()))
        {
            stringbuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringbuilder.toString();
    }

    public static String getSlottedModelField(String s, int i)
    {
        return (new StringBuilder()).append(s).append("*").append(i).toString();
    }

    static byte[] hexDecode(String s)
    {
        byte abyte0[] = new byte[s.length() / 2];
        for (int i = 0; i < abyte0.length; i++)
        {
            int k = s.charAt(i * 2) - 48;
            int j = k;
            if (k > 9)
            {
                j = k - 7;
            }
            int l = s.charAt(i * 2 + 1) - 48;
            k = l;
            if (l > 9)
            {
                k = l - 7;
            }
            abyte0[i] = (byte)(k | j << 4);
        }

        return abyte0;
    }

    public static Map parseURLParameters(String s)
    {
        HashMap hashmap = new HashMap();
        s = s.split("&");
        int j = s.length;
        int i = 0;
        while (i < j) 
        {
            String as[] = s[i].split("=");
            if (as.length > 1)
            {
                hashmap.put(as[0], as[1]);
            } else
            if (as.length == 1 && as[0].length() != 0)
            {
                hashmap.put(as[0], null);
            }
            i++;
        }
        return hashmap;
    }

    public static boolean safeParseBoolean(String s)
    {
        return Boolean.parseBoolean(s);
    }

    public static double safeParseDouble(String s)
    {
        if (s == null)
        {
            return 0.0D;
        }
        double d;
        try
        {
            d = Double.parseDouble(s);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return 0.0D;
        }
        return d;
    }
}
