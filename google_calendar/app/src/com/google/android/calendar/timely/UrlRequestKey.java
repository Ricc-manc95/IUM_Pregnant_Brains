// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import com.android.bitmap.RequestKey;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.image.BitmapCallbacks;
import com.google.android.calendar.volley.VolleyRequests;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlRequestKey
    implements RequestKey
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/UrlRequestKey);
    private final URL url;

    private UrlRequestKey(URL url1)
    {
        if (url1 == null)
        {
            throw new NullPointerException();
        } else
        {
            url = (URL)url1;
            return;
        }
    }

    public static UrlRequestKey fromUrlString$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D66KOBMC4NMOOBECSNL6T3ID5N6EEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TAN4R2ICLONAPBJEH5MAU9R0(String s)
    {
        if (s == null)
        {
            try
            {
                throw new NullPointerException();
            }
            catch (MalformedURLException malformedurlexception)
            {
                LogUtils.w(TAG, malformedurlexception, "Invalid icon URL: %s", new Object[] {
                    s
                });
            }
            return null;
        }
        UrlRequestKey urlrequestkey = new UrlRequestKey(new URL((String)s));
        return urlrequestkey;
    }

    public final com.android.bitmap.RequestKey.Cancelable createFileDescriptorFactoryOrByteArrayAsync$51666RRD5TGMSP3IDTKM8BR2D5Q6QOBG5T96ASBLCLPN8IR5F4TKOORFDKNM2RJ4E9NMIP1FC9KN8RB1E0NL4PBHELIN6T2BCLSI8GR1DHM64OB3DCTIIJ33DTMIUOBECHP6UQB45TH6IT3DC5O2UKJ5E5QMASRK9DINI923C5N66PBCC5H6OP9R0(com.android.bitmap.RequestKey.Callback callback)
    {
        return BitmapCallbacks.listen(this, callback, VolleyRequests.loadBytesAsync(url.toString()));
    }

    public final InputStream createInputStream()
        throws IOException
    {
        return null;
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof UrlRequestKey))
            {
                return false;
            }
            Object obj1 = (UrlRequestKey)obj;
            obj = url;
            obj1 = ((UrlRequestKey) (obj1)).url;
            if (obj != obj1 && (obj == null || !obj.equals(obj1)))
            {
                return false;
            }
        }
        return true;
    }

    public final boolean hasOrientationExif()
        throws IOException
    {
        return false;
    }

    public int hashCode()
    {
        return url.hashCode();
    }

}
