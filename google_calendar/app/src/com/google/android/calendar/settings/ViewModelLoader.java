// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings;

import android.content.Context;
import android.view.ContextThemeWrapper;
import com.google.android.apps.calendar.util.api.CalendarListEntryCache;
import com.google.android.apps.calendar.util.api.ListenableFutureCache;
import com.google.android.apps.calendar.util.api.SettingsCache;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.calendar.settings:
//            SettingsShims, SettingsActivity

public final class ViewModelLoader
{

    public final SettableFuture future = new SettableFuture();

    ViewModelLoader(final Context context)
    {
        Object obj = CalendarListEntryCache.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj = ((ListenableFutureCache)obj).getValueAsync();
        Object obj1 = SettingsCache.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("Not initialized"));
        }
        obj1 = ((ListenableFutureCache)obj1).getValueAsync();
        class .Lambda._cls0
            implements Callable
        {

            private final Context arg$1;

            public final Object call()
            {
                Context context1 = arg$1;
                return SettingsShims.instance.loadDirectory(context1);
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        FluentFuture fluentfuture = (FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls0(context));
        ListenableFuture listenablefuture = SettingsShims.instance.hasHabitsWithEnabledIntegration$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T66ISRKCLN62OJCCL37AT3LE9IJMAACCDNMQBR7DTNMER355THMURBDDTN2UTBKD5M2UORFDPHNASJICLN78BQCD5PN8PBEC5H6OPA6ELQ7ASJ57C______0(((ListenableFuture) (obj1)));
        com.google.common.util.concurrent.CollectionFuture.ListFuture listfuture = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new ListenableFuture[] {
            obj, obj1, fluentfuture, listenablefuture
        }), true);
        class .Lambda._cls1
            implements Function
        {

            private final ListenableFuture arg$1;
            private final ListenableFuture arg$2;
            private final ListenableFuture arg$3;
            private final ListenableFuture arg$4;
            private final Context arg$5;

            public final Object apply(Object obj2)
            {
                Object obj4 = arg$1;
                Object obj5 = arg$2;
                Object obj3 = arg$3;
                obj2 = arg$4;
                Context context1 = arg$5;
                obj4 = Arrays.asList((com.google.android.calendar.api.calendarlist.CalendarListEntry[])Futures.getUnchecked(((java.util.concurrent.Future) (obj4))));
                obj5 = (ImmutableMap)Futures.getUnchecked(((java.util.concurrent.Future) (obj5)));
                obj3 = (com.google.calendar.v2.libs.proto.DirectoryProto.Directory)Futures.getUnchecked(((java.util.concurrent.Future) (obj3)));
                boolean flag = ((Boolean)Futures.getUnchecked(((java.util.concurrent.Future) (obj2)))).booleanValue();
                return new HomeViewModel(context1, ((java.util.List) (obj4)), ((ImmutableMap) (obj5)), SettingsShims.instance, new SmartMailViewModel(context1, ((ImmutableMap) (obj5)), ((java.util.List) (obj4))), new BirthdayViewModel(context1, ((ImmutableMap) (obj5))), new HolidayViewModel(context1, ((ImmutableMap) (obj5)), ((com.google.calendar.v2.libs.proto.DirectoryProto.Directory) (obj3)), ((java.util.List) (obj4))), new GeneralPreferenceViewModel(context1, ((ImmutableMap) (obj5)), flag, SettingsShims.instance.shouldShowFlingingSettings(context1)));
            }

            .Lambda._cls1(ListenableFuture listenablefuture, ListenableFuture listenablefuture1, ListenableFuture listenablefuture2, ListenableFuture listenablefuture3, Context context)
            {
                arg$1 = listenablefuture;
                arg$2 = listenablefuture1;
                arg$3 = listenablefuture2;
                arg$4 = listenablefuture3;
                arg$5 = context;
            }
        }

        future.setFuture(AbstractTransformFuture.create(listfuture, new .Lambda._cls1(((ListenableFuture) (obj)), ((ListenableFuture) (obj1)), fluentfuture, listenablefuture, context), CalendarExecutor.BACKGROUND));
        obj = future;
        context = new _cls1();
        obj1 = CalendarExecutor.BACKGROUND;
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), context), ((java.util.concurrent.Executor) (obj1)));
            return;
        }
    }

    public static ListenableFuture getViewModelAsync(Context context)
    {
        do
        {
            if (context instanceof SettingsActivity)
            {
                return ((SettingsActivity)context).viewModelLoader.future;
            }
            if (context instanceof ContextThemeWrapper)
            {
                context = ((ContextThemeWrapper)context).getBaseContext();
            } else
            {
                throw new IllegalArgumentException();
            }
        } while (true);
    }

    private class _cls1
        implements FutureCallback
    {

        private final Context val$context;

        public final void onFailure(Throwable throwable)
        {
            if (!(throwable instanceof CancellationException))
            {
                throwable = AnalyticsLoggerHolder.instance;
                if (throwable == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                ((AnalyticsLogger)throwable).trackEvent(context, "settings_view_model_failure", "null");
            }
        }

        public final void onSuccess(Object obj)
        {
            obj = AnalyticsLoggerHolder.instance;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
            } else
            {
                ((AnalyticsLogger)obj).trackEvent(context, "settings_view_model", "null");
                return;
            }
        }

        _cls1()
        {
            context = context1;
            super();
        }
    }

}
