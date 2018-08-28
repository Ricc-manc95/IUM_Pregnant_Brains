// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gsf;

import android.net.Uri;
import android.provider.BaseColumns;

public final class 
    implements BaseColumns
{

    public static final Uri CONTENT_URI = Uri.parse("content://subscribedfeeds/feeds");

    static 
    {
        Uri.parse("content://subscribedfeeds/deleted_feeds");
    }
}
