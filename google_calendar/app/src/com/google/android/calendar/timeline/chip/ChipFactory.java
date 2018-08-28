// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.view.ViewConfiguration;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.utils.ActivitySingletonCache;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipConfig, Chip

public class ChipFactory
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timeline/chip/ChipFactory);
    private static final int foregroundDrawableProjection[] = {
        0x101030e
    };
    private static final ActivitySingletonCache instances = new _cls1();
    private final ChipConfig chipConfig;
    private final Context context;

    ChipFactory(Context context1)
    {
        context = context1;
        chipConfig = new ChipConfig(context1);
    }

    public static Chip createChipWithContext(Context context1)
    {
        Object obj = context1;
_L3:
        if (!(obj instanceof ContextWrapper))
        {
            break MISSING_BLOCK_LABEL_61;
        }
        if (!(obj instanceof Activity)) goto _L2; else goto _L1
_L1:
        obj = (Activity)obj;
_L4:
        if (obj == null)
        {
            LogUtils.i(TAG, "Creating TimelyChip without activity", new Object[0]);
            return (new ChipFactory(context1)).create();
        } else
        {
            CalendarExecutor.MAIN.checkOnThread();
            return ((ChipFactory)instances.getInstance(((Activity) (obj)))).create();
        }
_L2:
        obj = ((ContextWrapper)obj).getBaseContext();
          goto _L3
        obj = null;
          goto _L4
    }

    public static ChipFactory getInstance(Activity activity)
    {
        CalendarExecutor.MAIN.checkOnThread();
        return (ChipFactory)instances.getInstance(activity);
    }

    public final Chip create()
    {
        TypedArray typedarray = context.obtainStyledAttributes(foregroundDrawableProjection);
        android.graphics.drawable.Drawable drawable = typedarray.getDrawable(0);
        typedarray.recycle();
        return new Chip(context, chipConfig, ViewConfiguration.get(context), drawable);
    }


    private class _cls1 extends ActivitySingletonCache
    {

        protected final Object createInstance(Activity activity)
        {
            return new ChipFactory(activity);
        }

        _cls1()
        {
        }
    }

}
