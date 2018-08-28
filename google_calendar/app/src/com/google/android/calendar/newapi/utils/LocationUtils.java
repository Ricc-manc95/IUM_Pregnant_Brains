// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.utils;

import android.text.TextUtils;
import android.webkit.WebView;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.location.StructuredLocationPermissions;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class LocationUtils
{

    public static boolean allowsUpdateLocation(EventPermissions eventpermissions)
    {
        eventpermissions = eventpermissions.getStructuredLocationPermissions();
        return !eventpermissions.isReadOnly() && eventpermissions.canAddLocations() && eventpermissions.canRemoveLocation();
    }

    public static String createGeoLink(String s)
    {
        String s1;
        String s2;
        s1 = WebView.findAddress(s);
        s2 = String.valueOf("geo:0,0?q=");
        if (!TextUtils.isEmpty(s1))
        {
            s = s1;
        }
        s = String.valueOf(URLEncoder.encode(s, "UTF-8"));
        if (s.length() != 0)
        {
            return s2.concat(s);
        }
        s = new String(s2);
        return s;
        s;
        return null;
    }
}
