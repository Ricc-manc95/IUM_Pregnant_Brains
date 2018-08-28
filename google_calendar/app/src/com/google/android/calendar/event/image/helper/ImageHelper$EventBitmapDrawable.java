// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.android.bitmap.BitmapCache;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.apps.calendar.graphics.RtlMirroring;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import com.google.android.calendar.event.image.EventImageRequestKey;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.event.image.helper:
//            ImageHelper

public static final class isRtl extends ListenableBitmapDrawable
{

    private boolean isRtl;

    protected final Executor getExecutor()
    {
        return CalendarExecutor.BACKGROUND;
    }

    protected final void onDrawBitmap(Canvas canvas, Rect rect, Rect rect1)
    {
        Object obj = super.mCurrKey;
        boolean flag;
        if (!isRtl || !(obj instanceof EventImageRequestKey))
        {
            flag = false;
        } else
        {
            flag = ((EventImageRequestKey)obj).rtlMirroring.mirrorInRtl();
        }
        if (flag)
        {
            canvas.save();
            obj = new Matrix();
            ((Matrix) (obj)).setScale(-1F, 1.0F);
            ((Matrix) (obj)).postTranslate(canvas.getWidth(), 0.0F);
            canvas.concat(((Matrix) (obj)));
            super.onDrawBitmap(canvas, rect, rect1);
            canvas.restore();
            return;
        } else
        {
            super.onDrawBitmap(canvas, rect, rect1);
            return;
        }
    }

    public (Context context, BitmapCache bitmapcache, boolean flag, com.android.bitmap.drawable.ptions ptions)
    {
        super(context.getResources(), bitmapcache, false, ptions);
        isRtl = false;
        isRtl = RtlUtils.isLayoutDirectionRtl(context);
    }
}
