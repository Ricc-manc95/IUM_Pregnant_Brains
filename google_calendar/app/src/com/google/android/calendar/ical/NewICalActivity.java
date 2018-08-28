// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.PrePageUtils;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.OnInfoChangedListener;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.android.calendar.timely.animations.EventInfoAnimationData;
import com.google.android.calendar.timely.callbacks.OnLaunchDetailsHandler;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventListFragment, ICalImportFragment

public class NewICalActivity extends EventFragmentHostActivity
    implements OnInfoChangedListener, com.google.android.calendar.newapi.screen.ics.IcsViewScreenController.DataSetChangedListener, OnLaunchDetailsHandler
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/NewICalActivity);

    public NewICalActivity()
    {
    }

    private final void dismissDialog(DialogFragment dialogfragment, boolean flag)
    {
label0:
        {
            boolean flag2 = true;
            boolean flag1;
            if (super.mFragments.mHost.mFragmentManager.findFragmentByTag(ICalEventListFragment.TAG) != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                finish();
            }
            if (((Fragment) (dialogfragment)).mHost != null && ((Fragment) (dialogfragment)).mAdded)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1 && (dialogfragment instanceof OverlayFragment))
            {
                if (!flag)
                {
                    break label0;
                }
                class .Lambda._cls0
                    implements Runnable
                {

                    private final NewICalActivity arg$1;
                    private final DialogFragment arg$2;

                    public final void run()
                    {
                        arg$1.finishDismissOverlay((OverlayFragment)arg$2);
                    }

            .Lambda._cls0(DialogFragment dialogfragment)
            {
                arg$1 = NewICalActivity.this;
                arg$2 = dialogfragment;
            }
                }

                (new Handler(Looper.getMainLooper())).postDelayed(new .Lambda._cls0(dialogfragment), 300L);
            }
            return;
        }
        finishDismissOverlay((OverlayFragment)dialogfragment);
    }

    private final void initializeFragments()
    {
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }
        android.net.Uri uri = getIntent().getData();
        if (uri == null)
        {
            LogUtils.e(TAG, "Null file Uri!", new Object[0]);
            finish();
            return;
        }
        setContentView(0x7f0500a6);
        findViewById(0x7f10010c).setVisibility(0);
        Object obj = super.mFragments.mHost.mFragmentManager;
        FragmentTransaction fragmenttransaction = ((FragmentManager) (obj)).beginTransaction();
        for (obj = ((FragmentManager) (obj)).getFragments().iterator(); ((Iterator) (obj)).hasNext(); fragmenttransaction.remove((Fragment)((Iterator) (obj)).next())) { }
        fragmenttransaction.add(ICalImportFragment.newInstance(uri), ICalImportFragment.TAG);
        fragmenttransaction.commit();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        PrePageUtils.showPrePages(this);
        initializeFragments();
    }

    public final void onDataSetChanged()
    {
        ICalImportFragment icalimportfragment = (ICalImportFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(ICalImportFragment.TAG);
        if (icalimportfragment != null)
        {
            boolean flag;
            if (super.mFragments.mHost.mFragmentManager.findFragmentByTag(ICalEventListFragment.TAG) != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                icalimportfragment.scheduleRefresh();
            }
        }
    }

    public final void onInfoBack(DialogFragment dialogfragment, boolean flag)
    {
        dismissDialog(dialogfragment, false);
    }

    public final void onInfoCancel(DialogFragment dialogfragment, boolean flag)
    {
        dismissDialog(dialogfragment, flag);
    }

    public final void onLaunchDetails(TimelineItem timelineitem, EventInfoAnimationData eventinfoanimationdata)
    {
        if (TimelineItemUtil.isReminderBundle(timelineitem))
        {
            LogUtils.e(TAG, "Unable to launch bundle", new Object[0]);
            Toast.makeText(this, 0x7f130475, 0).show();
        } else
        {
            Fragment fragment = super.mFragments.mHost.mFragmentManager.findFragmentByTag(ICalImportFragment.TAG);
            if (fragment instanceof OnLaunchDetailsHandler)
            {
                ((OnLaunchDetailsHandler)fragment).onLaunchDetails(timelineitem, eventinfoanimationdata);
                return;
            }
        }
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        initializeFragments();
    }

}
