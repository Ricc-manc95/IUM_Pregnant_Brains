// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.growthkit;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.TimeBoxUtil;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.growthkit:
//            AssistantXPromo

public class GrowthKitCallback
    implements GrowthKitCallbacks
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/growthkit/GrowthKitCallback);
    private final WeakReference activityRef;
    public boolean isShowing;

    public GrowthKitCallback(AllInOneCalendarActivity allinonecalendaractivity)
    {
        isShowing = false;
        activityRef = new WeakReference(allinonecalendaractivity);
    }

    static final TimeZone lambda$getNumberOfVisibleEventsToday$2$GrowthKitCallback(TimeZone timezone)
    {
        return timezone;
    }

    static final com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse lambda$onAppStateNeeded$0$GrowthKitCallback(String s, Integer integer)
    {
        return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse(s, new com.google.android.libraries.internal.growth.growthkit.lifecycle.AutoOneOf_GrowthKitCallbacks_AppStateValue.Impl_integer(integer.intValue()));
    }

    static final com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse lambda$onAppStateNeeded$1$GrowthKitCallback(String s, Boolean boolean1)
    {
        int i;
        if (boolean1.booleanValue())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse(s, new com.google.android.libraries.internal.growth.growthkit.lifecycle.AutoOneOf_GrowthKitCallbacks_AppStateValue.Impl_integer(i));
    }

    public final ListenableFuture onAppStateNeeded$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFADQ74QBECSTIIJ33DTMIUPRFDTJMOP9FCDNMQRBFDONNAT39DGNM6RRECDQN4SJ5DPQ2UJ39EDQ6ARJ1C9M6AHJLEHQN4P9R0(String s)
    {
        if ("number_of_visible_events_today".equals(s))
        {
            Context context = (Context)activityRef.get();
            TimeZone timezone = Utils.getTimeZone(context);
            class .Lambda._cls2
                implements Supplier
            {

                private final TimeZone arg$1;

                public final Object get()
                {
                    return GrowthKitCallback.lambda$getNumberOfVisibleEventsToday$2$GrowthKitCallback(arg$1);
                }

            .Lambda._cls2(TimeZone timezone)
            {
                arg$1 = timezone;
            }
            }

            class .Lambda._cls3
                implements Function
            {

                public static final Function $instance = new .Lambda._cls3();

                public final Object apply(Object obj)
                {
                    return Integer.valueOf(((List)obj).size());
                }


            private .Lambda._cls3()
            {
            }
            }

            class .Lambda._cls0
                implements Function
            {

                private final String arg$1;

                public final Object apply(Object obj)
                {
                    return GrowthKitCallback.lambda$onAppStateNeeded$0$GrowthKitCallback(arg$1, (Integer)obj);
                }

            .Lambda._cls0(String s)
            {
                arg$1 = s;
            }
            }

            int i;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            i = TimeBoxUtil.msToJulianDay(timezone, l);
            return (FluentFuture)AbstractTransformFuture.create((FluentFuture)AbstractTransformFuture.create((new EventsApiImpl(context, new .Lambda._cls2(timezone))).getAsync(i, i, Utils.getHideDeclinedEvents(context)), .Lambda._cls3..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE), new .Lambda._cls0(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        class .Lambda._cls1
            implements Function
        {

            private final String arg$1;

            public final Object apply(Object obj)
            {
                return GrowthKitCallback.lambda$onAppStateNeeded$1$GrowthKitCallback(arg$1, (Boolean)obj);
            }

            .Lambda._cls1(String s)
            {
                arg$1 = s;
            }
        }

        if ("assistant_calendar_xpromo_enabled".equals(s))
        {
            return (FluentFuture)AbstractTransformFuture.create(AssistantXPromo.isEnabled((Context)activityRef.get()), new .Lambda._cls1(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        s = new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.AppStateResponse(s, new com.google.android.libraries.internal.growth.growthkit.lifecycle.AutoOneOf_GrowthKitCallbacks_AppStateValue.Impl_invalid(-1));
        if (s == null)
        {
            return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(s);
        }
    }

    public final com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse onPromoReady$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H874RRDDTA7IS357D66KOBMC4NMOOBECSNL6T3ID5N6EEP99HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFA9IN6S3FDPPMAEO_0(int i, String s)
    {
        AllInOneCalendarActivity allinonecalendaractivity;
        allinonecalendaractivity = (AllInOneCalendarActivity)activityRef.get();
        if (allinonecalendaractivity == null)
        {
            LogUtils.wtf(TAG, "REJECT: activity is already destroyed", new Object[0]);
            return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse(false, android.support.v4.content.ModernAsyncTask.Status.CLIENT_REJECT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        }
        if (allinonecalendaractivity.areAnyBottomSheetsShown())
        {
            LogUtils.d(TAG, "REJECT: other bottom sheets shown", new Object[0]);
            return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse(false, android.support.v4.content.ModernAsyncTask.Status.CLIENT_REJECT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        }
        if (allinonecalendaractivity.drawerLayout == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        DrawerLayout drawerlayout = allinonecalendaractivity.drawerLayout;
        View view = drawerlayout.findDrawerWithGravity(3);
        boolean flag1;
        if (view != null)
        {
            flag1 = drawerlayout.isDrawerVisible(view);
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L2; else goto _L3
_L3:
        flag = true;
_L5:
        if (flag)
        {
            LogUtils.d(TAG, "REJECT: drawer is visible", new Object[0]);
            return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse(false, android.support.v4.content.ModernAsyncTask.Status.CLIENT_REJECT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        }
        break; /* Loop/switch isn't completed */
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        com.google.android.calendar.AllInOneCalendarActivity.MainFabStack mainfabstack = allinonecalendaractivity.createFabStack;
        if ((mainfabstack instanceof com.google.android.calendar.AllInOneCalendarActivity.MainFabStack) && ((com.google.android.calendar.AllInOneCalendarActivity.MainFabStack)mainfabstack).isSpeedDialExpanded())
        {
            LogUtils.d(TAG, "REJECT: speed dial is open", new Object[0]);
            return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse(false, android.support.v4.content.ModernAsyncTask.Status.CLIENT_REJECT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
        }
        if (i == android.support.v4.content.ModernAsyncTask.Status.FEATURE_HIGHLIGHT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0 && allinonecalendaractivity.getResources().getResourceEntryName(0x7f100146).equals(s))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            s = allinonecalendaractivity.getFloatingActionButton();
            if (s == null || !s.isShown() || s.getTranslationY() != 0.0F)
            {
                LogUtils.d(TAG, "REJECT: fab not shown / animating", new Object[0]);
                return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse(false, android.support.v4.content.ModernAsyncTask.Status.ELEMENT_NOT_VISIBLE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP48HIMSUAICLGN6RRE7C______0);
            }
        }
        isShowing = true;
        LogUtils.d(TAG, "APPROVE", new Object[0]);
        return new com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitCallbacks.PromoResponse(true, allinonecalendaractivity);
    }

}
