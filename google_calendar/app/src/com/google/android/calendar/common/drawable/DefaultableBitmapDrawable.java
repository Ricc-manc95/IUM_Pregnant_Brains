// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.LruCache;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.ReusableBitmap;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.bitmap.drawable.CircularBitmapDrawable;

// Referenced classes of package com.google.android.calendar.common.drawable:
//            CalendarCircularBitmapDrawable

public final class DefaultableBitmapDrawable extends CalendarCircularBitmapDrawable
{

    private static final LruCache defaultPhotoCache = new LruCache(1);
    private final Bitmap defaultDrawable;
    private final Listener listener;
    private final Rect recycleRect = new Rect();

    public DefaultableBitmapDrawable(Resources resources, BitmapCache bitmapcache, boolean flag, Listener listener1, int i)
    {
        super(resources, bitmapcache, false);
        listener = listener1;
        listener1 = (Bitmap)defaultPhotoCache.get(Integer.valueOf(i));
        bitmapcache = listener1;
        if (listener1 == null)
        {
            bitmapcache = listener1;
            if (i != 0)
            {
                bitmapcache = BitmapFactory.decodeResource(resources, i);
                defaultPhotoCache.put(Integer.valueOf(i), bitmapcache);
            }
        }
        defaultDrawable = bitmapcache;
    }

    public final void draw(Canvas canvas)
    {
        Rect rect = getBounds();
        if (!rect.isEmpty())
        {
            ReusableBitmap reusablebitmap = getBitmap();
            if (listener == null && (reusablebitmap == null || reusablebitmap.bmp == null))
            {
                if (defaultDrawable != null)
                {
                    recycleRect.set(0, 0, defaultDrawable.getWidth(), defaultDrawable.getHeight());
                    onDrawCircularBitmap(defaultDrawable, canvas, recycleRect, rect, 1.0F);
                    return;
                }
            } else
            {
                super.draw(canvas);
                return;
            }
        }
    }

    public final void setBitmap(ReusableBitmap reusablebitmap)
    {
        super.setBitmap(reusablebitmap);
        if ((reusablebitmap == null || (reusablebitmap instanceof com.android.bitmap.ReusableBitmap.NullReusableBitmap)) && listener != null)
        {
            listener.onEmptyBitmapSet(this);
        }
    }


    private class Listener
    {

        public abstract void onEmptyBitmapSet(DefaultableBitmapDrawable defaultablebitmapdrawable);
    }

}
