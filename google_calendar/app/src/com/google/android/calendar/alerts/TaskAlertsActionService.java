// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.task.ArpTaskLoaderUtils;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.common.collect.CollectPreconditions;
import java.util.Collections;
import java.util.HashSet;

// Referenced classes of package com.google.android.calendar.alerts:
//            TaskAlertsManager

public class TaskAlertsActionService extends IntentService
{

    private static final boolean DEBUG;
    private static final String TAG;

    public TaskAlertsActionService()
    {
        this(null);
    }

    public TaskAlertsActionService(String s)
    {
        super(s);
        if (DEBUG)
        {
            LogUtils.d(TAG, " TaskAlertsActionService:%s", new Object[] {
                s
            });
        }
    }

    protected void onHandleIntent(Intent intent)
    {
        Object obj1 = intent.getAction();
        if (DEBUG)
        {
            LogUtils.d(TAG, "onHandleIntent %s", new Object[] {
                intent
            });
        }
        int i = intent.getIntExtra("intent.extra.notification_id", -1);
        if (i == -1)
        {
            LogUtils.e(TAG, "Unable to obtain a notification id", new Object[0]);
        } else
        {
            ((NotificationManager)getSystemService("notification")).cancel("tasks", i);
            if ("com.google.android.calendar.intent.action.ALERT_DISMISS".equals(obj1))
            {
                intent = AnalyticsLoggerHolder.instance;
                if (intent == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                } else
                {
                    ((AnalyticsLogger)intent).trackEvent(this, "notification", "dismiss");
                    return;
                }
            }
            Object obj = intent.getStringExtra("intent.extra.account_name");
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                intent = intent.getStringExtra("intent.extra.client_id");
                if (!TextUtils.isEmpty(intent))
                {
                    if (DEBUG)
                    {
                        LogUtils.d(TAG, " accountName=%s, clientId=%s", new Object[] {
                            obj, intent
                        });
                    }
                    if (!AndroidPermissionUtils.hasMandatoryPermissions(this))
                    {
                        Toast.makeText(this, 0x7f13034d, 1).show();
                        return;
                    }
                    if ("com.google.android.calendar.intent.action.MARK_TASK_AS_DONE".equals(obj1))
                    {
                        if (TaskDataFactory.instance == null)
                        {
                            TaskDataFactory.instance = new TaskDataFactory();
                        }
                        obj1 = TaskDataFactory.instance.getTaskConnection();
                        if (obj1 == null)
                        {
                            LogUtils.e(TAG, "Unable to obtain a task connection", new Object[0]);
                        } else
                        {
                            String as[] = new String[1];
                            as[0] = intent;
                            int j = as.length;
                            if (j < 3)
                            {
                                CollectPreconditions.checkNonnegative(j, "expectedSize");
                                j++;
                            } else
                            if (j < 0x40000000)
                            {
                                j = (int)((float)j / 0.75F + 1.0F);
                            } else
                            {
                                j = 0x7fffffff;
                            }
                            intent = new HashSet(j);
                            Collections.addAll(intent, as);
                            LogUtils.logOnFailure(((TaskConnection) (obj1)).updateTasksDoneStatus(this, ((String) (obj)), true, intent), TAG, "Unable to update task", new Object[0]);
                        }
                        intent = AnalyticsLoggerHolder.instance;
                        if (intent == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        } else
                        {
                            ((AnalyticsLogger)intent).trackEvent(this, "notification", "done");
                            return;
                        }
                    }
                    if ("com.google.android.calendar.intent.action.SHOW_CONTENT".equals(obj1))
                    {
                        if (TaskDataFactory.instance == null)
                        {
                            TaskDataFactory.instance = new TaskDataFactory();
                        }
                        obj = ArpTaskLoaderUtils.loadTimelineItem(this, TaskDataFactory.instance.getTaskConnection(), ((String) (obj)), intent);
                        if (obj == null)
                        {
                            LogUtils.e(TAG, "Unable to find Task %s", new Object[] {
                                intent
                            });
                        }
                        intent = new Intent("android.intent.action.VIEW");
                        intent.setDataAndType(CalendarContract.CONTENT_URI.buildUpon().appendEncodedPath("events/0").build(), "vnd.android.cursor.item/event");
                        intent.setPackage(getPackageName());
                        TimelineItemUtil.setLaunchTimelineItem(intent, ((com.google.android.calendar.timely.TimelineItem) (obj)));
                        intent.putExtra("intent_source", "notification");
                        intent.addFlags(0x10000000);
                        if (DEBUG)
                        {
                            LogUtils.d(TAG, "  showIntent=%s", new Object[] {
                                intent
                            });
                        }
                        try
                        {
                            startActivity(intent);
                        }
                        // Misplaced declaration of an exception variable
                        catch (Intent intent)
                        {
                            LogUtils.e(TAG, intent, "Unable to find activity for intent", new Object[0]);
                        }
                        intent = AnalyticsLoggerHolder.instance;
                        if (intent == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        } else
                        {
                            ((AnalyticsLogger)intent).trackEvent(this, "notification", "open_task");
                            return;
                        }
                    }
                }
            }
        }
    }

    static 
    {
        TAG = TaskAlertsManager.TAG;
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        } else
        {
            DEBUG = ((FeatureConfig)featureconfig).dogfood_features();
        }
    }
}
