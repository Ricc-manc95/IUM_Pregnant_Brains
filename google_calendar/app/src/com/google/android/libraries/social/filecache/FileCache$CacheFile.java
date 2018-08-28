// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.filecache;

import java.io.File;

public final class size
    implements Comparable
{

    public File file;
    public boolean recent;
    public long size;
    public long timestamp;

    public final int compareTo(Object obj)
    {
        byte byte0;
        byte0 = -1;
        obj = (size)obj;
        if (!recent) goto _L2; else goto _L1
_L1:
        if (((recent) (obj)).recent) goto _L4; else goto _L3
_L3:
        int i = 1;
_L6:
        return i;
_L4:
        i = byte0;
        if (timestamp >= ((timestamp) (obj)).timestamp)
        {
            return timestamp <= ((timestamp) (obj)).timestamp ? 0 : 1;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        i = byte0;
        if (!((timestamp) (obj)).recent)
        {
            i = byte0;
            if (((recent) (obj)).size >= size)
            {
                return ((size) (obj)).size <= size ? 0 : 1;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public (File file1)
    {
        file = file1;
        timestamp = file1.lastModified();
        size = file1.length();
    }
}
