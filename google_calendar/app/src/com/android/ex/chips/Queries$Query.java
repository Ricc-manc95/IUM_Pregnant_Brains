// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ex.chips;

import android.net.Uri;

abstract class contentFilterUri
{

    public final Uri contentFilterUri;
    public final String projection[];

    public (String as[], Uri uri, Uri uri1)
    {
        projection = as;
        contentFilterUri = uri;
    }
}
