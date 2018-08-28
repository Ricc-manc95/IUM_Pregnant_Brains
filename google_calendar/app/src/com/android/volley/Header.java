// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.text.TextUtils;

public final class Header
{

    public final String mName;
    public final String mValue;

    public Header(String s, String s1)
    {
        mName = s;
        mValue = s1;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (Header)obj;
            if (!TextUtils.equals(mName, ((Header) (obj)).mName) || !TextUtils.equals(mValue, ((Header) (obj)).mValue))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return mName.hashCode() * 31 + mValue.hashCode();
    }

    public final String toString()
    {
        String s = mName;
        String s1 = mValue;
        return (new StringBuilder(String.valueOf(s).length() + 20 + String.valueOf(s1).length())).append("Header[name=").append(s).append(",value=").append(s1).append("]").toString();
    }
}
