// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widgetmonth.model;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import com.google.android.calendar.utils.intent.IntentUtils;
import com.google.android.calendar.widget.WidgetUtils;
import com.google.android.calendar.widgetmonth.MonthViewWidgetProvider;
import com.google.android.calendar.widgetmonth.MonthViewWidgetUtils;

// Referenced classes of package com.google.android.calendar.widgetmonth.model:
//            MonthViewWidgetModel

public class MonthViewWidgetModelRefresher extends BroadcastReceiver
{

    public MonthViewWidgetModelRefresher()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
label0:
        {
            intent = intent.getAction();
            if (!"android.intent.action.LOCALE_CHANGED".equals(intent) && !IntentUtils.hasProviderChanged(intent) && !"com.google.android.timely.intent.action.TASK_SETTINGS_CHANGED".equals(intent))
            {
                String s = WidgetUtils.getWidgetCallerIsSyncAdapterAction(context);
                int i;
                if (s == intent || s != null && s.equals(intent))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    String s1 = WidgetUtils.getWidgetTaskChanged(context);
                    int j;
                    int k;
                    if (s1 == intent || s1 != null && s1.equals(intent))
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        break label0;
                    }
                }
            }
            intent = MonthViewWidgetUtils.getWidgetIds(context);
            j = intent.length;
            i = 0;
            while (i < j) 
            {
                k = intent[i];
                MonthViewWidgetModel monthviewwidgetmodel = (MonthViewWidgetModel)MonthViewWidgetModel.modelForAppWidgetId.get(k);
                if (monthviewwidgetmodel == null)
                {
                    AppWidgetManager.getInstance(context);
                    MonthViewWidgetProvider.performUpdate$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FC5O70TR9CHJMAT1F85O70LR9CHJMAT2DC5N62PR5E8TKIAAM0(context, k);
                } else
                {
                    monthviewwidgetmodel.refreshData(true);
                }
                i++;
            }
        }
    }
}
