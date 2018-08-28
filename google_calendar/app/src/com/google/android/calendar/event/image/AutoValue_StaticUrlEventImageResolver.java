// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;


// Referenced classes of package com.google.android.calendar.event.image:
//            StaticUrlEventImageResolver

public final class AutoValue_StaticUrlEventImageResolver extends StaticUrlEventImageResolver
{

    private final String url;

    public AutoValue_StaticUrlEventImageResolver(String s)
    {
        if (s == null)
        {
            throw new NullPointerException("Null url");
        } else
        {
            url = s;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof StaticUrlEventImageResolver)
        {
            obj = (StaticUrlEventImageResolver)obj;
            return url.equals(((StaticUrlEventImageResolver) (obj)).url());
        } else
        {
            return false;
        }
    }

    public final int hashCode()
    {
        return 0xf4243 ^ url.hashCode();
    }

    public final String toString()
    {
        String s = url;
        return (new StringBuilder(String.valueOf(s).length() + 33)).append("StaticUrlEventImageResolver{url=").append(s).append("}").toString();
    }

    final String url()
    {
        return url;
    }
}
