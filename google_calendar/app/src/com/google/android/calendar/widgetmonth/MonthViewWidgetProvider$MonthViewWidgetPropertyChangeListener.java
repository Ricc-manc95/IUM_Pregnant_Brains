// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import android.content.Context;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.timely.settings.common.OnPropertyChangedListener;
import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetProvider

public static final class context
    implements OnPropertyChangedListener
{

    public final Context context;

    public final void onCalendarPropertyChanged(int i, Object obj)
    {
        class .Lambda._cls0
            implements Runnable
        {

            private final MonthViewWidgetProvider.MonthViewWidgetPropertyChangeListener arg$1;
            private final int arg$2;

            public final void run()
            {
                MonthViewWidgetProvider.MonthViewWidgetPropertyChangeListener monthviewwidgetpropertychangelistener;
label0:
                {
label1:
                    {
label2:
                        {
                            monthviewwidgetpropertychangelistener = arg$1;
                            int j = arg$2;
                            if (MonthViewWidgetProvider.propertyChangeListener == monthviewwidgetpropertychangelistener)
                            {
                                switch (j)
                                {
                                default:
                                    LogUtils.w(MonthViewWidgetProvider.TAG, "Ignoring property change notification for '%d'", new Object[] {
                                        Integer.valueOf(j)
                                    });
                                    break;

                                case 0: // '\0'
                                    break label0;

                                case 5: // '\005'
                                case 7: // '\007'
                                case 13: // '\r'
                                    break label2;

                                case 14: // '\016'
                                    break label1;
                                }
                            }
                            return;
                        }
                        MonthViewWidgetProvider.performUpdateForAll(monthviewwidgetpropertychangelistener.context);
                        return;
                    }
                    MonthViewWidgetModel.removeAllModels();
                    MonthViewWidgetProvider.performUpdateForAll(monthviewwidgetpropertychangelistener.context);
                    return;
                }
                MonthViewWidgetProvider.onDateTimeChanged(monthviewwidgetpropertychangelistener.context);
            }

            .Lambda._cls0(int i)
            {
                arg$1 = MonthViewWidgetProvider.MonthViewWidgetPropertyChangeListener.this;
                arg$2 = i;
            }
        }

        (new com.google.android.apps.calendar.util.concurrent..arg._cls2(CalendarExecutor.MAIN)).execute(new .Lambda._cls0(i));
    }

    public .Lambda._cls0(Context context1)
    {
        context = context1.getApplicationContext();
    }
}
