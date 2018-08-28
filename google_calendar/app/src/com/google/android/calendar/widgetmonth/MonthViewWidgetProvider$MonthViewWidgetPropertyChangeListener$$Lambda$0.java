// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth;

import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.widgetmonth.model.MonthViewWidgetModel;

// Referenced classes of package com.google.android.calendar.widgetmonth:
//            MonthViewWidgetProvider

final class arg._cls2
    implements Runnable
{

    private final arg._cls2 arg$1;
    private final int arg$2;

    public final void run()
    {
        arg._cls2 _lcls2;
label0:
        {
label1:
            {
label2:
                {
                    _lcls2 = arg$1;
                    int i = arg$2;
                    if (MonthViewWidgetProvider.propertyChangeListener == _lcls2)
                    {
                        switch (i)
                        {
                        default:
                            LogUtils.w(MonthViewWidgetProvider.TAG, "Ignoring property change notification for '%d'", new Object[] {
                                Integer.valueOf(i)
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
                MonthViewWidgetProvider.performUpdateForAll(_lcls2._fld2);
                return;
            }
            MonthViewWidgetModel.removeAllModels();
            MonthViewWidgetProvider.performUpdateForAll(_lcls2._fld2);
            return;
        }
        MonthViewWidgetProvider.onDateTimeChanged(_lcls2._fld2);
    }

    ( , int i)
    {
        arg$1 = ;
        arg$2 = i;
    }
}
