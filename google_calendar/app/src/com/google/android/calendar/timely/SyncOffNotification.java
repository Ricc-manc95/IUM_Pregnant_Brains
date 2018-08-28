// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.sharedprefs.SharedPrefs;
import com.google.android.calendar.time.clock.Clock;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.timely:
//            BottomSheet, SyncOffNotificationsManager

public final class SyncOffNotification extends BottomSheet
    implements android.view.View.OnClickListener
{

    public SyncOffNotification(Context context)
    {
        super(context, null, 0);
    }

    private final void inflateView()
    {
        if (findViewById(0x7f1001e4) != null)
        {
            return;
        } else
        {
            tweakLayout();
            inflate(mContext, 0x7f05016b, this);
            findViewById(0x7f1001e4).setOnClickListener(this);
            findViewById(0x7f1001e5).setOnClickListener(this);
            return;
        }
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        Object obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        obj = SyncOffNotificationsManager.instance;
        obj.notification = this;
        if (((SyncOffNotificationsManager) (obj)).needsRefresh)
        {
            ((SyncOffNotificationsManager) (obj)).onAppOpen();
        }
        if (((SyncOffNotificationsManager) (obj)).isShowing)
        {
            boolean flag;
            if (!ContentResolver.getMasterSyncAutomatically())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag && (((SyncOffNotificationsManager) (obj)).account != null || ((SyncOffNotificationsManager) (obj)).syncOffAccounts.size() <= 0) && (((SyncOffNotificationsManager) (obj)).account == null || !((SyncOffNotificationsManager) (obj)).syncOffAccounts.contains(((SyncOffNotificationsManager) (obj)).account)))
            {
                ((SyncOffNotificationsManager) (obj)).notification.hide(false, true);
            }
        }
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        if (SyncOffNotificationsManager.instance.isShowing)
        {
            inflateView();
            obj = mContext;
            if (SyncOffNotificationsManager.instance == null)
            {
                SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
            }
            obj = SyncOffNotificationsManager.instance.text;
            ((TextView)findViewById(0x7f100371)).setText(((CharSequence) (obj)));
            setVisibility(0);
        } else
        {
            setVisibility(8);
        }
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        SyncOffNotificationsManager.instance.onAppOpen();
    }

    public final void onClick(View view)
    {
        boolean flag;
        if (view.getId() == 0x7f1001e4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hide(true, flag);
    }

    protected final void onHide()
    {
        Context context = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(context);
        }
        SyncOffNotificationsManager.instance.isShowing = false;
    }

    protected final void onNegativeButtonClicked()
    {
        Context context = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(context);
        }
        SyncOffNotificationsManager.instance.showReason;
        JVM INSTR tableswitch 1 1: default 48
    //                   1 72;
           goto _L1 _L2
_L1:
        Object obj = "other";
_L4:
        Object obj1;
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj = "app_open";
        if (true) goto _L4; else goto _L3
_L3:
        ((AnalyticsLogger)obj1).trackEvent(mContext, "sync_off_notification", ((String) (obj)), "dismissed", null);
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        obj = SyncOffNotificationsManager.instance;
        obj1 = ((SyncOffNotificationsManager) (obj)).context;
        ((SyncOffNotificationsManager) (obj)).checkDismissCycle();
        SharedPrefs.setSharedPreference(((Context) (obj1)), "com.android.calendar.timely.syncOffNotification.numDismisses", ((SyncOffNotificationsManager) (obj)).context.getSharedPreferences("com.google.android.calendar_preferences", 0).getInt("com.android.calendar.timely.syncOffNotification.numDismisses", 0) + 1);
        if (((SyncOffNotificationsManager) (obj)).showReason == 1)
        {
            Context context1 = ((SyncOffNotificationsManager) (obj)).context;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            SharedPrefs.setSharedPreference(context1, "com.android.calendar.timely.syncOffNotification.lastShown", l);
        }
        obj.showReason = 0;
        return;
    }

    protected final void onPositiveButtonClicked()
    {
        Context context = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(context);
        }
        SyncOffNotificationsManager.instance.showReason;
        JVM INSTR tableswitch 1 1: default 48
    //                   1 72;
           goto _L1 _L2
_L1:
        Object obj = "other";
_L4:
        Object obj1;
        obj1 = AnalyticsLoggerHolder.instance;
        if (obj1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        break; /* Loop/switch isn't completed */
_L2:
        obj = "app_open";
        if (true) goto _L4; else goto _L3
_L3:
        ((AnalyticsLogger)obj1).trackEvent(mContext, "sync_off_notification", ((String) (obj)), "enabled", null);
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        obj = SyncOffNotificationsManager.instance;
        obj1 = new HashSet();
        boolean flag;
        if (!ContentResolver.getMasterSyncAutomatically())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ((Set) (obj1)).addAll(((SyncOffNotificationsManager) (obj)).allAccounts);
        } else
        if (((SyncOffNotificationsManager) (obj)).account != null)
        {
            ((Set) (obj1)).add(((SyncOffNotificationsManager) (obj)).account);
        } else
        {
            ((Set) (obj1)).addAll(((SyncOffNotificationsManager) (obj)).syncOffAccounts);
        }
        (new SyncOffNotificationsManager._cls1(((SyncOffNotificationsManager) (obj)), ((Set) (obj1)))).execute(new Void[0]);
        obj.showReason = 0;
        return;
    }

    protected final void onShow()
    {
        Object obj;
        super.onShow();
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        SyncOffNotificationsManager.instance.isShowing = true;
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        obj = SyncOffNotificationsManager.instance.text;
        ((TextView)findViewById(0x7f100371)).setText(((CharSequence) (obj)));
        announceForAccessibility(((CharSequence) (obj)));
        obj = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(((Context) (obj)));
        }
        switch (SyncOffNotificationsManager.instance.showReason)
        {
        default:
            obj = "other";
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_152;
        }
_L1:
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)analyticslogger).trackEvent(mContext, "sync_off_notification", ((String) (obj)), "displayed", null);
            return;
        }
        obj = "app_open";
          goto _L1
    }

    public final void show()
    {
        Context context = mContext;
        if (SyncOffNotificationsManager.instance == null)
        {
            SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(context);
        }
        if (!SyncOffNotificationsManager.instance.isShowing)
        {
            Context context1 = mContext;
            if (SyncOffNotificationsManager.instance == null)
            {
                SyncOffNotificationsManager.instance = new SyncOffNotificationsManager(context1);
            }
            if (!SyncOffNotificationsManager.instance.shouldNotShow)
            {
                inflateView();
                showDelayed(0x7f110033);
                return;
            }
        }
    }
}
