// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.LongSparseArray;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            HoursDrawable, ColumnDimens, ColumnViewport, DragGuideManager

public final class HoursDrawableImpl extends HoursDrawable
{

    private final LinearGradient blurGradient;
    private final Matrix blurMatrix = new Matrix();
    private final Paint blurPaint = new Paint();
    public Rect bounds;
    private final Context context;
    private final Date date = new Date();
    private final HourDrawer dayHourDrawers[] = new HourDrawer[25];
    public final ColumnDimens dimens;
    private final DimensConverter dimensConverter;
    private final DragGuideManager dragGuideManager;
    public final Point gridOffset;
    private final HourDrawer guideTextDrawer;
    private final SimpleDateFormat hourMinuteDateFormat = new SimpleDateFormat("h:mm", Locale.getDefault());
    private boolean is24HourMode;
    public final ObservableReference isRtl;
    private final Calendar systemTzCalendar = Calendar.getInstance(TimeZone.getDefault());
    private final TimeUtils timeUtils;
    public final Rect tmpRect = new Rect();
    private final Calendar uiTzCalendar;
    public final ColumnViewport viewport;

    HoursDrawableImpl(Context context1, ObservableReference observablereference, Point point, ColumnDimens columndimens, ColumnViewport columnviewport, DimensConverter dimensconverter, DragGuideManager dragguidemanager, 
            TimeUtils timeutils)
    {
        bounds = new Rect();
        context = context1;
        isRtl = observablereference;
        gridOffset = point;
        dimens = columndimens;
        viewport = columnviewport;
        dimensConverter = dimensconverter;
        dragGuideManager = dragguidemanager;
        timeUtils = timeutils;
        hourMinuteDateFormat.setTimeZone((TimeZone)timeutils.timeZone.get());
        point = new Paint();
        point.setAntiAlias(true);
        point.setColor(ContextCompat.getColor(context1, 0x7f0d021a));
        point.setTextSize(dimensconverter.spToPx(12F));
        if (((Boolean)observablereference.get()).booleanValue())
        {
            observablereference = android.graphics.Paint.Align.RIGHT;
        } else
        {
            observablereference = android.graphics.Paint.Align.LEFT;
        }
        point.setTextAlign(observablereference);
        for (int i = 0; i < dayHourDrawers.length; i++)
        {
            dayHourDrawers[i] = new HourDrawer(point);
        }

        observablereference = new Paint(point);
        observablereference.setColor(ContextCompat.getColor(context1, 0x7f0d01d7));
        guideTextDrawer = new HourDrawer(observablereference);
        blurGradient = new LinearGradient(0.0F, 0.0F, 0.0F, (int)dimensconverter.dpToPx(12F), -1, 0, android.graphics.Shader.TileMode.CLAMP);
        blurPaint.setDither(true);
        blurPaint.setShader(blurGradient);
        uiTzCalendar = Calendar.getInstance((TimeZone)timeutils.timeZone.get());
    }

    public final void draw(Canvas canvas)
    {
        bounds = getBounds();
        if (uiTzCalendar.getTimeZone() != (TimeZone)timeUtils.timeZone.get())
        {
            uiTzCalendar.setTimeZone((TimeZone)timeUtils.timeZone.get());
            hourMinuteDateFormat.setTimeZone((TimeZone)timeUtils.timeZone.get());
            systemTzCalendar.setTimeZone(TimeZone.getDefault());
        }
        boolean flag2 = DateFormat.is24HourFormat(context);
        if (is24HourMode != flag2 || dayHourDrawers[0].text == null)
        {
            systemTzCalendar.clear();
            int i = 0;
            while (i < dayHourDrawers.length) 
            {
                systemTzCalendar.set(11, i);
                HourDrawer hourdrawer = dayHourDrawers[i];
                String s = DateUtils.formatDateTime(context, systemTzCalendar.getTimeInMillis(), 16385);
                String s2 = hourdrawer.text;
                boolean flag;
                if (s2 == s || s2 != null && s2.equals(s))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    hourdrawer.text = s;
                    hourdrawer.paint.getTextBounds(s, 0, s.length(), hourdrawer._fld0.tmpRect);
                    hourdrawer.halfWidth = hourdrawer._fld0.tmpRect.width() / 2;
                    hourdrawer.halfHeight = hourdrawer._fld0.tmpRect.height() / 2;
                }
                i++;
            }
            is24HourMode = flag2;
        }
        int i1 = Math.max(dimens.allDayTopPx, gridOffset.y);
        canvas.clipRect(bounds.left, i1, bounds.right, bounds.bottom);
        for (int j = 1; j < 24; j++)
        {
            int k1 = gridOffset.y;
            Object obj = viewport;
            int i2 = (int)(((((long)j << 16 << 16) / ((long)24 << 16) & 65535L) - ((ColumnViewport) (obj)).gridTopFp16OfDay << 16) / ((ColumnViewport) (obj)).gridFp32PerVerticalPixel);
            obj = dayHourDrawers[j];
            float f3 = k1 + i2;
            HoursDrawableImpl hoursdrawableimpl = ((HourDrawer) (obj))._fld0;
            ColumnViewport columnviewport = ((HourDrawer) (obj))._fld0.viewport;
            k1 = Math.round((float)(((HourDrawer) (obj))._fld0.gridOffset.x / 2) - ((HourDrawer) (obj)).halfWidth);
            i2 = ((HourDrawer) (obj))._fld0.dimens.oneDayHeaderStartMarginPx;
            float f = Math.round(columnviewport.oneDayRatio * (float)(i2 - k1) + (float)k1);
            if (((Boolean)hoursdrawableimpl.isRtl.get()).booleanValue())
            {
                f = (float)hoursdrawableimpl.bounds.width() - f;
            }
            canvas.drawText(((HourDrawer) (obj)).text, f, f3 + ((HourDrawer) (obj)).halfHeight, ((HourDrawer) (obj)).paint);
        }

        int k = (int)dimensConverter.dpToPx(12F);
        blurMatrix.reset();
        blurMatrix.postTranslate(0.0F, i1);
        blurGradient.setLocalMatrix(blurMatrix);
        canvas.drawRect(0.0F, i1, bounds.width(), k + i1, blurPaint);
        int l1 = dragGuideManager.animators.size();
        if (l1 != 0)
        {
            int l = 0;
            while (l < l1) 
            {
                long l2 = dragGuideManager.animators.keyAt(l);
                HourDrawer hourdrawer1 = guideTextDrawer;
                float f1 = ((ValueAnimator)dragGuideManager.animators.valueAt(l)).getAnimatedFraction();
                hourdrawer1.paint.setAlpha(Math.round(f1 * 255F));
                long l3 = timeUtils.fp16ToMs(l2);
                uiTzCalendar.setTimeInMillis(l3);
                int j1;
                if (uiTzCalendar.get(12) == 0)
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                if (j1 != 0 || DateFormat.is24HourFormat(context))
                {
                    uiTzCalendar.setTimeInMillis(l3);
                    systemTzCalendar.clear();
                    systemTzCalendar.set(11, uiTzCalendar.get(11));
                    systemTzCalendar.set(12, uiTzCalendar.get(12));
                    Object obj1 = guideTextDrawer;
                    Object obj2 = DateUtils.formatDateTime(context, systemTzCalendar.getTimeInMillis(), 16385);
                    Object obj3 = ((HourDrawer) (obj1)).text;
                    float f2;
                    float f4;
                    int j2;
                    if (obj3 == obj2 || obj3 != null && obj3.equals(obj2))
                    {
                        j1 = 1;
                    } else
                    {
                        j1 = 0;
                    }
                    if (j1 == 0)
                    {
                        obj1.text = ((String) (obj2));
                        ((HourDrawer) (obj1)).paint.getTextBounds(((String) (obj2)), 0, ((String) (obj2)).length(), ((HourDrawer) (obj1))._fld0.tmpRect);
                        obj1.halfWidth = ((HourDrawer) (obj1))._fld0.tmpRect.width() / 2;
                        obj1.halfHeight = ((HourDrawer) (obj1))._fld0.tmpRect.height() / 2;
                    }
                } else
                {
                    date.setTime(l3);
                    HourDrawer hourdrawer2 = guideTextDrawer;
                    String s1 = hourMinuteDateFormat.format(date);
                    String s3 = hourdrawer2.text;
                    boolean flag1;
                    if (s3 == s1 || s3 != null && s3.equals(s1))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1)
                    {
                        hourdrawer2.text = s1;
                        hourdrawer2.paint.getTextBounds(s1, 0, s1.length(), hourdrawer2._fld0.tmpRect);
                        hourdrawer2.halfWidth = hourdrawer2._fld0.tmpRect.width() / 2;
                        hourdrawer2.halfHeight = hourdrawer2._fld0.tmpRect.height() / 2;
                    }
                }
                j1 = gridOffset.y;
                obj1 = viewport;
                j2 = (int)(((l2 & 65535L) - ((ColumnViewport) (obj1)).gridTopFp16OfDay << 16) / ((ColumnViewport) (obj1)).gridFp32PerVerticalPixel);
                obj1 = guideTextDrawer;
                f4 = j1 + j2;
                obj2 = ((HourDrawer) (obj1))._fld0;
                obj3 = ((HourDrawer) (obj1))._fld0.viewport;
                j1 = Math.round((float)(((HourDrawer) (obj1))._fld0.gridOffset.x / 2) - ((HourDrawer) (obj1)).halfWidth);
                j2 = ((HourDrawer) (obj1))._fld0.dimens.oneDayHeaderStartMarginPx;
                f2 = Math.round(((ColumnViewport) (obj3)).oneDayRatio * (float)(j2 - j1) + (float)j1);
                if (((Boolean)((HoursDrawableImpl) (obj2)).isRtl.get()).booleanValue())
                {
                    f2 = (float)((HoursDrawableImpl) (obj2)).bounds.width() - f2;
                }
                canvas.drawText(((HourDrawer) (obj1)).text, f2, f4 + ((HourDrawer) (obj1)).halfHeight, ((HourDrawer) (obj1)).paint);
                l++;
            }
        }
    }

    public final int getOpacity()
    {
        return -3;
    }

    public final void setAlpha(int i)
    {
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
    }

    private class HourDrawer
    {

        public float halfHeight;
        public float halfWidth;
        public final Paint paint;
        public String text;
        public final HoursDrawableImpl this$0;

        HourDrawer(Paint paint1)
        {
            this$0 = HoursDrawableImpl.this;
            super();
            paint = paint1;
        }
    }

}
