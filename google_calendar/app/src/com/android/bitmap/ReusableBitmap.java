// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;

import android.graphics.Bitmap;

// Referenced classes of package com.android.bitmap:
//            Poolable

public class ReusableBitmap
    implements Poolable
{

    public final Bitmap bmp;
    public int height;
    public int orientation;
    public int refCount;
    public final boolean reusable;
    public int width;

    public ReusableBitmap(Bitmap bitmap)
    {
        this(bitmap, true);
    }

    public ReusableBitmap(Bitmap bitmap, boolean flag)
    {
        refCount = 0;
        bmp = bitmap;
        reusable = flag;
    }

    public void acquireReference()
    {
        refCount = refCount + 1;
    }

    public int getByteCount()
    {
        return bmp.getByteCount();
    }

    public final int getRefCount()
    {
        return refCount;
    }

    public final boolean isEligibleForPooling()
    {
        return reusable;
    }

    public void releaseReference()
    {
        if (refCount == 0)
        {
            throw new IllegalStateException();
        } else
        {
            refCount = refCount - 1;
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("[");
        stringbuilder.append(super.toString());
        stringbuilder.append(" refCount=");
        stringbuilder.append(refCount);
        stringbuilder.append(" reusable=");
        stringbuilder.append(reusable);
        stringbuilder.append(" bmp=");
        stringbuilder.append(bmp);
        stringbuilder.append(" logicalW/H=");
        stringbuilder.append(width);
        stringbuilder.append("/");
        stringbuilder.append(height);
        if (bmp != null)
        {
            stringbuilder.append(" sz=");
            stringbuilder.append(bmp.getByteCount() >> 10);
            stringbuilder.append("KB");
        }
        stringbuilder.append("]");
        return stringbuilder.toString();
    }
}
