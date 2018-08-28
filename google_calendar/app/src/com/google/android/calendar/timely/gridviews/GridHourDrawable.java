// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.DateTimeFormatHelper;
import com.google.android.calendar.timely.settings.data.CalendarProperties;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.NumberUtils;
import com.google.android.calendar.utils.rtl.RtlUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            GridHourViewHighlightController

public class GridHourDrawable extends Drawable
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/gridviews/GridHourDrawable);
    private static final SparseIntArray recycleSparseIntArray = new SparseIntArray();
    private final Context context;
    private final int gridlineHeight;
    public final Paint highlightPaint = new Paint();
    public final List highlights = new ArrayList();
    private final int hoursTextSize;
    public int intervalHeight;
    public final Paint paint = new Paint();
    public int startMargin;

    public GridHourDrawable(Context context1, int i, int j)
    {
        context = context1;
        gridlineHeight = j;
        hoursTextSize = i;
        context1 = context1.getResources();
        intervalHeight = CalendarProperties.getIntProperty(10).intValue();
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setTextSize(hoursTextSize);
        paint.setStrokeWidth(gridlineHeight);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(context1.getColor(0x7f0d030b));
        paint.setTypeface(Typeface.create("sans-serif", 0));
        i = context1.getColor(0x7f0d01d7);
        j = ColorUtils.blend(i, context1.getColor(0x7f0d0313), 0.5F);
        highlightPaint.set(paint);
        highlightPaint.setColor(i);
        highlightPaint.setShadowLayer(3F, 0.0F, 0.0F, j);
    }

    private final void drawAlignedText(Canvas canvas, String s, float f, Paint paint1)
    {
        Rect rect = getBounds();
        if (rect.isEmpty())
        {
            LogUtils.wtf(TAG, "Expected non empty bounds", new Object[0]);
            return;
        }
        if (paint1.getTextAlign() == android.graphics.Paint.Align.CENTER)
        {
            int i = rect.left;
            canvas.drawText(s, rect.width() / 2 + i, f, paint1);
            return;
        }
        if (RtlUtils.isLayoutDirectionRtl(context))
        {
            paint1.setTextAlign(android.graphics.Paint.Align.RIGHT);
            canvas.drawText(s, rect.right - startMargin, f, paint1);
            return;
        } else
        {
            paint1.setTextAlign(android.graphics.Paint.Align.LEFT);
            canvas.drawText(s, rect.left + startMargin, f, paint1);
            return;
        }
    }

    public void draw(Canvas canvas)
    {
        int l1 = intervalHeight + gridlineHeight;
        int i2 = hoursTextSize / 2;
        recycleSparseIntArray.clear();
        for (Iterator iterator = highlights.iterator(); iterator.hasNext();)
        {
            GridHourViewHighlightController gridhourviewhighlightcontroller1 = (GridHourViewHighlightController)iterator.next();
            int i = 0;
            while (i < gridhourviewhighlightcontroller1.highlightFadeAnimators.size()) 
            {
                int i1 = gridhourviewhighlightcontroller1.highlightFadeAnimators.keyAt(i);
                int j2 = recycleSparseIntArray.get(i1, 0);
                int l2 = ((Integer)((ValueAnimator)gridhourviewhighlightcontroller1.highlightFadeAnimators.valueAt(i)).getAnimatedValue()).intValue();
                recycleSparseIntArray.put(i1, Math.max(j2, l2));
                i++;
            }
        }

        SparseIntArray sparseintarray = recycleSparseIntArray;
        Object obj = DateTimeFormatHelper.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
        }
        obj = ((DateTimeFormatHelper)obj).getHoursStrings();
        int j = 0;
        int j1 = i2 + l1;
        for (; j < ((List) (obj)).size() - 1; j++)
        {
            int k2 = NumberUtils.closestDistanceTo(sparseintarray, (j + 1) * 60);
            if (k2 == 0 || k2 > 5)
            {
                drawAlignedText(canvas, (String)((List) (obj)).get(j), j1, paint);
            }
            j1 += l1;
        }

        for (int k = 0; k < sparseintarray.size(); k++)
        {
            int k1 = sparseintarray.keyAt(k);
            Object obj1;
            boolean flag;
            if (k1 % 60 != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj1 = DateTimeFormatHelper.instance;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            obj1 = ((DateTimeFormatHelper)obj1).getShortMinuteString(k1, flag);
            float f = (float)k1 / 60F;
            float f1 = i2;
            float f2 = l1;
            highlightPaint.setAlpha(sparseintarray.valueAt(k));
            drawAlignedText(canvas, ((String) (obj1)), f * f2 + f1, highlightPaint);
        }

        canvas = highlights.iterator();
_L6:
        if (!canvas.hasNext()) goto _L2; else goto _L1
_L1:
        GridHourViewHighlightController gridhourviewhighlightcontroller;
        int l;
        gridhourviewhighlightcontroller = (GridHourViewHighlightController)canvas.next();
        l = 0;
_L7:
        if (l >= gridhourviewhighlightcontroller.highlightFadeAnimators.size())
        {
            break MISSING_BLOCK_LABEL_500;
        }
        if (!((ValueAnimator)gridhourviewhighlightcontroller.highlightFadeAnimators.valueAt(l)).isRunning()) goto _L4; else goto _L3
_L3:
        l = 1;
_L8:
        if (l == 0) goto _L6; else goto _L5
_L5:
        getCallback().invalidateDrawable(this);
_L2:
        return;
_L4:
        l++;
          goto _L7
        l = 0;
          goto _L8
    }

    public int getIntrinsicHeight()
    {
        return (intervalHeight + gridlineHeight) * 24;
    }

    public int getOpacity()
    {
        return -3;
    }

    public void setAlpha(int i)
    {
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

}
