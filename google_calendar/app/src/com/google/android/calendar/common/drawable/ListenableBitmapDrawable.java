// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.res.Resources;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.RequestKey;
import com.android.bitmap.ReusableBitmap;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;

public class ListenableBitmapDrawable extends ExtendedBitmapDrawable
{

    public OnLoadCompleteListener onLoadCompleteListener;

    public ListenableBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag, com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions)
    {
        super(resources, bitmapcache, flag, extendedoptions);
    }

    public final void bind(RequestKey requestkey)
    {
        if (mCurrKey != null && mCurrKey.equals(requestkey) && onLoadCompleteListener != null)
        {
            onLoadCompleteListener.onLoadComplete(this);
        }
        super.bind(requestkey);
    }

    protected final int getDecodeStrategy()
    {
        return 2;
    }

    public final void setBitmap(ReusableBitmap reusablebitmap)
    {
        super.setBitmap(reusablebitmap);
        while (reusablebitmap == null || onLoadCompleteListener == null) 
        {
            return;
        }
        onLoadCompleteListener.onLoadComplete(this);
    }

    private class OnLoadCompleteListener
    {

        public abstract void onLoadComplete(ListenableBitmapDrawable listenablebitmapdrawable);
    }

}
