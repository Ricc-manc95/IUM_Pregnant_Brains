// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.net.Uri;

public final class mResultCode
{

    public final boolean mItalic;
    public final int mResultCode;
    public final int mTtcIndex;
    public final Uri mUri;
    public final int mWeight;

    public (Uri uri, int i, int j, boolean flag, int k)
    {
        if (uri == null)
        {
            throw new NullPointerException();
        } else
        {
            mUri = (Uri)uri;
            mTtcIndex = i;
            mWeight = j;
            mItalic = flag;
            mResultCode = k;
            return;
        }
    }
}
