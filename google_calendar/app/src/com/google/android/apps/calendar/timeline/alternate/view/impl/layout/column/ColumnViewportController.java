// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import android.graphics.Point;
import com.google.android.apps.calendar.timeline.alternate.util.DimensConverter;
import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.impl.TimelineHostView;
import com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnViewport

public final class ColumnViewportController
{

    private final Calendar calendar = Calendar.getInstance();
    public final ObservableReference gridMsPerVerticalPx;
    public final Point gridOffset;
    public final ObservableReference isRtl;
    public Integer lastGridY;
    public final int maxMsPerPixel;
    public final int minMsPerPixel;
    public final TimeUtils timeUtils;
    public final TimelineHostView view;
    public final ColumnViewport viewport;
    public final ViewportAnimator viewportAnimator;

    public ColumnViewportController(ColumnViewport columnviewport, Point point, TimelineHostView timelinehostview, DimensConverter dimensconverter, ObservableReference observablereference, TimeUtils timeutils, ViewportAnimator viewportanimator, 
            ObservableReference observablereference1)
    {
        viewport = columnviewport;
        gridOffset = point;
        view = timelinehostview;
        isRtl = observablereference;
        timeUtils = timeutils;
        viewportAnimator = viewportanimator;
        gridMsPerVerticalPx = observablereference1;
        minMsPerPixel = (int)((float)TimeUnit.HOURS.toMillis(1L) / dimensconverter.dpToPx(64F));
        maxMsPerPixel = (int)((float)TimeUnit.HOURS.toMillis(1L) / dimensconverter.dpToPx(32F));
        columnviewport.setGridMsPerVerticalPx(((Integer)observablereference1.get()).intValue());
    }

    final FluentFuture animateDragPage(int i)
    {
        byte byte0 = 7;
        if (viewport.snappedDays != 7)
        {
            byte0 = 1;
        }
        return animateSetStartDay(byte0 * i + viewport.getSnappedToClosestJulianDay());
    }

    final FluentFuture animateGoToDay(int i)
    {
        ColumnViewport columnviewport = viewport;
        int j = (int)(columnviewport.startDayFp16 >> 16);
        if (columnviewport.snappedDays == 7)
        {
            TimeUtils timeutils = columnviewport.timeUtils;
            j = (((2 - ((Integer)columnviewport.timeUtils.firstDayOfWeek.get()).intValue()) + j) / 7) * 7 - (2 - ((Integer)timeutils.firstDayOfWeek.get()).intValue());
        }
        i = viewport.snapJulianDay(i);
        j = i - j;
        if (Math.abs(j) / viewport.snappedDays > 2)
        {
            j = i - (Integer.signum(j) * viewport.snappedDays << 1);
            viewport.setStartDayAndWidthFp16(j, (long)j << 16, (long)viewport.snappedDays << 16);
        }
        return animateSetStartDay(i);
    }

    final FluentFuture animateSetStartDay(int i)
    {
        long l = (long)i << 16;
        long l1 = viewport.startDayFp16;
        long l2 = l - l1;
        long l3 = viewport.widthDaysFp16;
        long l4 = (long)viewport.snappedDays << 16;
        long l5 = l4 - l3;
        float f1 = viewport.oneDayRatio;
        float f;
        if (viewport.snappedDays == 1)
        {
            f = 1.0F;
        } else
        {
            f = 0.0F;
        }
        class .Lambda._cls3
            implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator.ChangeAnimationListener
        {

            private final ColumnViewportController arg$1;
            private final int arg$2;
            private final long arg$3;
            private final long arg$4;
            private final long arg$5;
            private final long arg$6;
            private final float arg$7;
            private final float arg$8;

            public final void onStep(float f2)
            {
                ColumnViewportController columnviewportcontroller = arg$1;
                int j = arg$2;
                long l6 = arg$3;
                long l7 = arg$4;
                long l8 = arg$5;
                long l9 = arg$6;
                float f3 = arg$7;
                float f4 = arg$8;
                long l10 = (long)(65536F * f2);
                columnviewportcontroller.viewport.setStartDayAndWidthFp16(j, l6 + (l7 * l10 >> 16), (l9 * l10 >> 16) + l8);
                columnviewportcontroller.viewport.oneDayRatio = f4 * f2 + f3;
            }

            .Lambda._cls3(int i, long l, long l1, long l2, long l3, float f, float f1)
            {
                arg$1 = ColumnViewportController.this;
                arg$2 = i;
                arg$3 = l;
                arg$4 = l1;
                arg$5 = l2;
                arg$6 = l3;
                arg$7 = f;
                arg$8 = f1;
            }
        }

        class .Lambda._cls4
            implements Function
        {

            private final ColumnViewportController arg$1;
            private final int arg$2;
            private final long arg$3;
            private final long arg$4;
            private final float arg$5;

            public final Object apply(Object obj1)
            {
                ColumnViewportController columnviewportcontroller = arg$1;
                int j = arg$2;
                long l6 = arg$3;
                long l7 = arg$4;
                float f2 = arg$5;
                columnviewportcontroller.viewport.setStartDayAndWidthFp16(j, l6, l7);
                columnviewportcontroller.viewport.oneDayRatio = f2;
                return obj1;
            }

            .Lambda._cls4(int i, long l, long l1, float f)
            {
                arg$1 = ColumnViewportController.this;
                arg$2 = i;
                arg$3 = l;
                arg$4 = l1;
                arg$5 = f;
            }
        }

        if (l2 == 0L && l5 == 0L)
        {
            Object obj = new Object();
            if (obj == null)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj);
            }
            if (obj instanceof FluentFuture)
            {
                return (FluentFuture)obj;
            } else
            {
                return new ForwardingFluentFuture(((com.google.common.util.concurrent.ListenableFuture) (obj)));
            }
        } else
        {
            return (FluentFuture)AbstractTransformFuture.create(viewportAnimator.animateViewportChange(new .Lambda._cls3(i, l1, l2, l3, l5, f1, f - f1)), new .Lambda._cls4(i, l, l4, f), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }

    final Optional animateShowRange(int i, long l, long l1)
    {
        Object obj;
        FluentFuture fluentfuture;
        int j;
        int k;
        int i1;
        int j1;
        obj = null;
        ColumnViewport columnviewport;
        if ((viewport.startDayFp16 & 65535L) == 0L && (int)(viewport.startDayFp16 >> 16) <= i && (int)(viewport.startDayFp16 >> 16) + viewport.snappedDays > i)
        {
            fluentfuture = null;
        } else
        {
            fluentfuture = animateGoToDay(i);
        }
        l = Math.max((long)i << 16, l);
        l1 = Math.min((long)(i + 1) << 16, l1);
        i = viewport.gridTopMsOfDay;
        columnviewport = viewport;
        j = columnviewport.gridTopMsOfDay;
        k = columnviewport.gridHeightPx;
        j = columnviewport.gridMsPerVerticalPixel * k + j;
        k = j - i;
        i1 = TimeUtils.fp16ToDisplayMsOfDay(l, false);
        j1 = TimeUtils.fp16ToDisplayMsOfDay(l1, true);
        break MISSING_BLOCK_LABEL_146;
        if ((i1 < i || j1 > j) && (i1 > i || j1 < j))
        {
            if (i > i1)
            {
                obj = Integer.valueOf(Math.max(i1, j1 - k));
            } else
            {
                obj = Integer.valueOf(Math.min(i1, j1 - k));
            }
        }
        if (fluentfuture != null)
        {
            break MISSING_BLOCK_LABEL_272;
        }
        if (obj == null)
        {
            return Absent.INSTANCE;
        }
        break MISSING_BLOCK_LABEL_238;
        obj = animateVerticalViewportChange(((Integer) (obj)).intValue());
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(obj);
        }
        if (obj == null)
        {
            if (fluentfuture == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(fluentfuture);
            }
        }
        class .Lambda._cls7
            implements AsyncFunction
        {

            private final ColumnViewportController arg$1;
            private final Integer arg$2;

            public final ListenableFuture apply(Object obj1)
            {
                return arg$1.animateVerticalViewportChange(arg$2.intValue());
            }

            .Lambda._cls7(Integer integer)
            {
                arg$1 = ColumnViewportController.this;
                arg$2 = integer;
            }
        }

        obj = (FluentFuture)AbstractTransformFuture.create(fluentfuture, new .Lambda._cls7(((Integer) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(obj);
        }
    }

    final FluentFuture animateVerticalViewportChange(int i)
    {
        int j = viewport.gridTopMsOfDay;
        i -= j;
        class .Lambda._cls1
            implements com.google.android.apps.calendar.timeline.alternate.view.impl.layout.animation.ViewportAnimator.ChangeAnimationListener
        {

            private final ColumnViewportController arg$1;
            private final int arg$2;
            private final int arg$3;

            public final void onStep(float f)
            {
                ColumnViewportController columnviewportcontroller = arg$1;
                int k = arg$2;
                int l = arg$3;
                columnviewportcontroller.viewport.setGridTopMsOfDay((int)((float)k + (float)l * f));
            }

            .Lambda._cls1(int i, int j)
            {
                arg$1 = ColumnViewportController.this;
                arg$2 = i;
                arg$3 = j;
            }
        }

        class .Lambda._cls2
            implements Function
        {

            private final ColumnViewportController arg$1;
            private final int arg$2;
            private final int arg$3;

            public final Object apply(Object obj)
            {
                ColumnViewportController columnviewportcontroller = arg$1;
                int k = arg$2;
                int l = arg$3;
                columnviewportcontroller.view.stopScroll();
                columnviewportcontroller.viewport.setGridTopMsOfDay(k + l);
                return obj;
            }

            .Lambda._cls2(int i, int j)
            {
                arg$1 = ColumnViewportController.this;
                arg$2 = i;
                arg$3 = j;
            }
        }

        return (FluentFuture)AbstractTransformFuture.create(viewportAnimator.animateViewportChange(new .Lambda._cls1(j, i)), new .Lambda._cls2(j, i), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    final void goToTime(long l)
    {
        int i = viewport.snapJulianDay(timeUtils.msToJulianDate(l));
        viewport.setStartDayAndWidthFp16(i, (long)i << 16, viewport.widthDaysFp16);
        viewport.setGridTopMsOfDay(targetMsToGridTop(l));
    }

    final void scrollHorizontallyPx(int i)
    {
        long l = viewport.widthDaysFp16;
        int j;
        long l1;
        long l2;
        if (((Boolean)isRtl.get()).booleanValue())
        {
            j = -1;
        } else
        {
            j = 1;
        }
        l1 = j;
        l2 = ((long)i * l) / (long)viewport.gridWidthPx;
        l1 = viewport.startDayFp16 + l1 * l2;
        viewport.setStartDayAndWidthFp16((int)(l1 >> 16), l1, l);
    }

    final int targetMsToGridTop(long l)
    {
        timeUtils.initCalendar(calendar);
        calendar.setTimeInMillis(l);
        l = TimeUnit.HOURS.toMillis(calendar.get(11));
        long l1 = TimeUnit.MINUTES.toMillis(calendar.get(12));
        long l2 = TimeUnit.HOURS.toMillis(1L);
        long l3 = TimeUnit.DAYS.toMillis(1L) - (long)(viewport.gridHeightPx * viewport.gridMsPerVerticalPixel) - 1L;
        boolean flag;
        if (1L <= l3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", new Object[] {
                Long.valueOf(1L), Long.valueOf(l3)
            }));
        } else
        {
            return (int)Math.min(Math.max((l + l1) - l2, 1L), l3);
        }
    }
}
