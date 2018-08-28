// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.content.Intent;
import android.net.Uri;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUriHandler
{

    private Collection hosts;
    public final Intent mIntent;
    private Collection paths;
    private Collection schemes;

    public BaseUriHandler(Intent intent, Collection collection, Collection collection1, Collection collection2)
    {
        mIntent = intent;
        schemes = collection;
        hosts = collection1;
        paths = collection2;
    }

    public boolean matches()
    {
        Object obj;
        Object obj1;
        if (mIntent == null || mIntent.getData() == null)
        {
            return false;
        }
        obj = mIntent.getData();
        obj1 = ((Uri) (obj)).getScheme();
        if (!schemes.contains(obj1)) goto _L2; else goto _L1
_L1:
        obj1 = ((Uri) (obj)).getHost();
        if (!hosts.contains(obj1)) goto _L2; else goto _L3
_L3:
        obj = ((Uri) (obj)).getPath();
        obj1 = paths.iterator();
_L7:
        if (!((Iterator) (obj1)).hasNext()) goto _L5; else goto _L4
_L4:
        boolean flag;
        String s = (String)((Iterator) (obj1)).next();
        boolean flag1;
        if (obj == null)
        {
            flag1 = false;
        } else
        {
            flag1 = Pattern.compile(s).matcher(((CharSequence) (obj))).matches();
        }
        if (!flag1) goto _L7; else goto _L6
_L6:
        flag = true;
_L8:
        if (flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L5:
        flag = false;
        if (true) goto _L8; else goto _L2
_L2:
        return false;
    }
}
