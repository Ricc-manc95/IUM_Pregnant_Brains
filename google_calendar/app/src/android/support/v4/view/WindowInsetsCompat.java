// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;


public final class WindowInsetsCompat
{

    public final Object mInsets;

    public WindowInsetsCompat(Object obj)
    {
        mInsets = obj;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (WindowInsetsCompat)obj;
            if (mInsets == null)
            {
                if (((WindowInsetsCompat) (obj)).mInsets != null)
                {
                    return false;
                }
            } else
            {
                return mInsets.equals(((WindowInsetsCompat) (obj)).mInsets);
            }
        }
        return true;
    }

    public final int hashCode()
    {
        if (mInsets == null)
        {
            return 0;
        } else
        {
            return mInsets.hashCode();
        }
    }
}
