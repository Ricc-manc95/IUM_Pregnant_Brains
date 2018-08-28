// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.text.TextUtils;

final class mName
{

    public String mName;
    public int mResId;
    public int mWidgetResId;

    public final boolean equals(Object obj)
    {
        if (obj instanceof mName)
        {
            if (mResId == ((mResId) (obj = (mResId)obj)).mResId && mWidgetResId == ((mWidgetResId) (obj)).mWidgetResId && TextUtils.equals(mName, ((mName) (obj)).mName))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return ((mResId + 527) * 31 + mWidgetResId) * 31 + mName.hashCode();
    }

    ()
    {
    }

    ( )
    {
        mResId = .mResId;
        mWidgetResId = .mWidgetResId;
        mName = .mName;
    }
}
