// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.net.Uri;

public final class ObservedUri
{

    public final int flags;
    public final Uri uri;

    public ObservedUri(Uri uri1, int i)
    {
        if (uri1 == null)
        {
            throw new IllegalArgumentException("URI must not be null.");
        } else
        {
            uri = uri1;
            flags = i;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (!(obj instanceof ObservedUri))
            {
                return false;
            }
            obj = (ObservedUri)obj;
            if (flags != ((ObservedUri) (obj)).flags || !uri.equals(((ObservedUri) (obj)).uri))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return uri.hashCode() ^ flags;
    }
}
