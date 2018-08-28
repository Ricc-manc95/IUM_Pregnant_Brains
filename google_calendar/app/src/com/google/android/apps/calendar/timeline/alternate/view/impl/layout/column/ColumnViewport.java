// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.concurrent.TimeUnit;

final class ColumnViewport
{

    private static final int DEFAULT_TOP;
    public final ObservableReference changeObservable = new com.google.android.apps.calendar.util.concurrent.ObservableReferences._cls1(this);
    public long gridFp32PerVerticalPixel;
    public int gridHeightPx;
    public int gridMsPerVerticalPixel;
    public long gridTopFp16OfDay;
    public int gridTopMsOfDay;
    public int gridWidthPx;
    private final ObservableReference isRtl;
    public float oneDayRatio;
    public int snappedDays;
    public long startDayFp16;
    public int targetStartDay;
    public final TimeUtils timeUtils;
    public long widthDaysFp16;

    ColumnViewport(TimeUtils timeutils, ObservableReference observablereference)
    {
        timeUtils = timeutils;
        isRtl = observablereference;
    }

    final boolean clampGridTopOfDay()
    {
        int i;
        boolean flag;
        if (gridTopMsOfDay < 0)
        {
            gridTopMsOfDay = 0;
            gridTopFp16OfDay = 0L;
            flag = true;
        } else
        {
            flag = false;
        }
        i = gridTopMsOfDay + gridHeightPx * gridMsPerVerticalPixel;
        if (i >= TimeUtils.DAY_MS)
        {
            gridTopMsOfDay = Math.max(0, gridTopMsOfDay + (TimeUtils.DAY_MS - i));
            gridTopFp16OfDay = ((long)gridTopMsOfDay << 16 << 16) / TimeUtils.DAY_MS_FP16;
            flag = true;
        }
        if (gridTopMsOfDay + gridHeightPx * gridMsPerVerticalPixel > TimeUtils.DAY_MS)
        {
            gridTopMsOfDay = 0;
            gridTopFp16OfDay = 0L;
            gridMsPerVerticalPixel = TimeUtils.DAY_MS / gridHeightPx;
            gridFp32PerVerticalPixel = ((long)gridMsPerVerticalPixel << 32) / (long)TimeUtils.DAY_MS;
            return true;
        } else
        {
            return flag;
        }
    }

    final int getRightVisibleJulianDay()
    {
        boolean flag = false;
        long l = startDayFp16;
        l = widthDaysFp16 + l;
        int i;
        int j;
        if ((65535L & l) != 0L)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = (int)(l >> 16);
        if (i != 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 1;
        }
        return j - i;
    }

    final int getSnappedToClosestJulianDay()
    {
        if (snappedDays == 7)
        {
            int i = (int)(startDayFp16 + 0x38000L >> 16);
            TimeUtils timeutils = timeUtils;
            return (((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) / 7) * 7 - (2 - ((Integer)timeutils.firstDayOfWeek.get()).intValue());
        } else
        {
            return (int)(startDayFp16 + 32768L >> 16);
        }
    }

    final Optional gridPxToFp16(int i, int j)
    {
        int k = i;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            k = gridWidthPx - i;
        }
        if (k < 0 || j < 0 || k > gridWidthPx || j > gridHeightPx)
        {
            return Absent.INSTANCE;
        }
        Long long1 = Long.valueOf((startDayFp16 + (widthDaysFp16 * (long)k) / (long)gridWidthPx & 0xffffffffffff0000L) + ((long)(gridTopMsOfDay + gridMsPerVerticalPixel * j) << 16 << 16) / TimeUtils.DAY_MS_FP16);
        if (long1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(long1);
        }
    }

    final int julianDayToGridStartPx(int i)
    {
        return (int)(((((long)i << 16) - startDayFp16) * (((long)gridWidthPx << 16 << 16) / widthDaysFp16) >> 16) + 32768L >> 16);
    }

    public final void onShow(int i, int j)
    {
        snappedDays = j;
        targetStartDay = i;
        float f;
        int k;
        if (j == 7)
        {
            TimeUtils timeutils = timeUtils;
            k = (((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) / 7) * 7 - (2 - ((Integer)timeutils.firstDayOfWeek.get()).intValue());
        } else
        {
            k = i;
        }
        setStartDayAndWidthFp16(i, (long)k << 16, (long)j << 16);
        setGridTopMsOfDay(DEFAULT_TOP);
        if (j == 1)
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        oneDayRatio = f;
    }

    final void setGridMsPerVerticalPx(int i)
    {
        if (gridMsPerVerticalPixel == i)
        {
            return;
        } else
        {
            gridMsPerVerticalPixel = i;
            gridFp32PerVerticalPixel = ((long)i << 32) / (long)TimeUtils.DAY_MS;
            clampGridTopOfDay();
            changeObservable.set(this);
            return;
        }
    }

    final boolean setGridTopMsOfDay(int i)
    {
        if (gridTopMsOfDay == i)
        {
            return false;
        } else
        {
            gridTopMsOfDay = i;
            gridTopFp16OfDay = ((long)gridTopMsOfDay << 16 << 16) / TimeUtils.DAY_MS_FP16;
            boolean flag = clampGridTopOfDay();
            changeObservable.set(this);
            return flag;
        }
    }

    final void setStartDayAndWidthFp16(int i, long l, long l1)
    {
        if (targetStartDay == i && startDayFp16 == l && widthDaysFp16 == l1)
        {
            return;
        } else
        {
            targetStartDay = i;
            startDayFp16 = l;
            widthDaysFp16 = l1;
            changeObservable.set(this);
            return;
        }
    }

    final int snapJulianDay(int i)
    {
        int j = i;
        if (snappedDays == 7)
        {
            TimeUtils timeutils = timeUtils;
            j = (((2 - ((Integer)timeUtils.firstDayOfWeek.get()).intValue()) + i) / 7) * 7 - (2 - ((Integer)timeutils.firstDayOfWeek.get()).intValue());
        }
        return j;
    }

    static 
    {
        DEFAULT_TOP = (int)TimeUnit.HOURS.toMillis(8L);
    }
}
