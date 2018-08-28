// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.NewViewScreenFactory;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.timely.TimelineItemUtil;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.event:
//            OnInfoChangedListener

public class EventInfoActivity extends EventFragmentHostActivity
    implements com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayListener, com.google.android.calendar.common.view.overlay.OverlayFragment.OverlaySetting, OnInfoChangedListener
{

    private GestureDetector gestureDetector;
    private final ContentObserver observer = new _cls1(new Handler());
    private FluentFuture viewScreenFuture;

    public EventInfoActivity()
    {
    }

    public final void dismissOverlay(final OverlayFragment fragment, boolean flag)
    {
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        fragmentmanagerimpl.executePendingTransactions();
        if (getFirstBackStackEntry(fragmentmanagerimpl, fragment) <= 0)
        {
            finish();
            return;
        }
        if (flag)
        {
            (new Handler()).postDelayed(new _cls3(), 300L);
            return;
        } else
        {
            finishDismissOverlay(fragment);
            return;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        return super.dispatchTouchEvent(motionevent) || gestureDetector != null && gestureDetector.onTouchEvent(motionevent);
    }

    public final Window getOverlaySettingWindow()
    {
        return getWindow();
    }

    public final void initOverlaySetting(android.view.GestureDetector.OnGestureListener ongesturelistener, boolean flag)
    {
        gestureDetector = new GestureDetector(this, ongesturelistener);
        setFinishOnTouchOutside(flag);
    }

    public void onBackPressed()
    {
        OverlayFragment overlayfragment = getOverlayFragment();
        if (overlayfragment != null)
        {
            finishDismissOverlay(overlayfragment);
            return;
        } else
        {
            super.onBackPressed();
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setTheme(0x7f14012b);
        setContentView(0x7f05015d);
        if (bundle != null)
        {
            bundle = PerformanceMetricCollectorHolder.instance;
            if (bundle == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)bundle).recordMemory("EventInfoActivity.Recreated");
        } else
        {
            bundle = PerformanceMetricCollectorHolder.instance;
            if (bundle == null)
            {
                throw new NullPointerException(String.valueOf("PrimesLogger not set"));
            }
            ((PerformanceMetricCollector)bundle).recordMemory("EventInfoActivity.Created");
        }
        getWindow().setStatusBarColor(0);
    }

    protected void onDestroy()
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory("EventInfoActivity.Destroyed");
            super.onDestroy();
            return;
        }
    }

    public final void onInfoBack(DialogFragment dialogfragment, boolean flag)
    {
        dismissOverlay((OverlayFragment)dialogfragment, false);
    }

    public final void onInfoCancel(DialogFragment dialogfragment, boolean flag)
    {
        dismissOverlay((OverlayFragment)dialogfragment, flag);
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    protected void onPause()
    {
        super.onPause();
        getContentResolver().unregisterContentObserver(observer);
    }

    protected void onResume()
    {
        super.onResume();
        getContentResolver().registerContentObserver(android.provider.CalendarContract.Events.CONTENT_URI, true, observer);
    }

    protected void onStart()
    {
        super.onStart();
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        boolean flag;
        if (fragmentmanagerimpl != null && fragmentmanagerimpl.findFragmentByTag("ViewScreenController") != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            Object obj = getIntent();
            Object obj2 = TimelineItemUtil.readTimelineItemFromIntent(this, ((Intent) (obj)));
            OverlayFragment overlayfragment;
            if (obj2 == null)
            {
                Toast.makeText(this, 0x7f1301d8, 0).show();
                finish();
            } else
            {
                LogUtils.d("EventInfoActivity", "Launching intent %s with action %s", new Object[] {
                    obj, ((Intent) (obj)).getAction()
                });
                if (viewScreenFuture != null)
                {
                    viewScreenFuture.cancel(true);
                    viewScreenFuture = null;
                }
                Object obj1 = Utils.getLaunchExtras(((Intent) (obj)));
                obj = obj1;
                if (!getResources().getBoolean(0x7f0c0016))
                {
                    obj = obj1;
                    if (obj1 == null)
                    {
                        obj = new Bundle();
                    }
                    ((Bundle) (obj)).putBoolean("animate_header", true);
                }
                viewScreenFuture = NewViewScreenFactory.createViewScreen(this, ((com.google.android.calendar.timely.TimelineItem) (obj2)), null, ((Bundle) (obj)));
                obj = viewScreenFuture;
                obj1 = new _cls2();
                obj2 = CalendarExecutor.MAIN;
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), ((FutureCallback) (obj1))), ((java.util.concurrent.Executor) (obj2)));
            }
        }
        obj = super.mFragments.mHost.mFragmentManager;
        overlayfragment = (OverlayFragment)((FragmentManager) (obj)).findFragmentByTag("ViewScreenController");
        if (overlayfragment != null && ((FragmentManager) (obj)).getBackStackEntryCount() == 0)
        {
            setTitle(overlayfragment.getTitle());
        }
    }

    protected void onStop()
    {
        super.onStop();
        if (viewScreenFuture != null)
        {
            viewScreenFuture.cancel(true);
            viewScreenFuture = null;
        }
    }

    public final void resetOverlaySetting()
    {
        gestureDetector = null;
    }

    private class _cls1 extends ContentObserver
    {

        private final EventInfoActivity this$0;

        public final boolean deliverSelfNotifications()
        {
            return true;
        }

        public final void onChange(boolean flag)
        {
            Object obj = EventInfoActivity.this;
            obj = (CalendarController)CalendarController.instances.getInstance(((android.app.Activity) (obj)));
            EventInfoActivity eventinfoactivity = EventInfoActivity.this;
            ((CalendarController) (obj)).executeCommand$5166KOBMC4NMOOBECSNKUOJACLHN8EQCCDNMQBR7DTNMER355TGMSP3IDTKM8BR3C5M6ARJ4C5P2UGR1DHIMSP31E91MURJKE9NMOR35E8I46RRDDLGMSP1R55B0____0(new com.google.android.calendar.CalendarController.Command(128L));
        }

        public final void onChange(boolean flag, Uri uri)
        {
            onChange(flag);
            AbstractEventViewScreenController.notifyContentProviderUpdateIfAvailable(mFragments.mHost.mFragmentManager, uri);
        }

        _cls1(Handler handler)
        {
            this$0 = EventInfoActivity.this;
            super(handler);
        }
    }


    private class _cls3
        implements Runnable
    {

        private final EventInfoActivity this$0;
        private final OverlayFragment val$fragment;

        public final void run()
        {
            finishDismissOverlay(fragment);
        }

        _cls3()
        {
            this$0 = EventInfoActivity.this;
            fragment = overlayfragment;
            super();
        }
    }


    private class _cls2
        implements FutureCallback
    {

        private final EventInfoActivity this$0;

        public final void onFailure(Throwable throwable)
        {
            if (!(throwable instanceof CancellationException))
            {
                Toast.makeText(EventInfoActivity.this, 0x7f1301d8, 0).show();
                finish();
            }
        }

        public final void onSuccess(Object obj)
        {
            obj = (OverlayFragment)obj;
            showOverlayFragment("ViewScreenController", ((OverlayFragment) (obj)));
        }

        _cls2()
        {
            this$0 = EventInfoActivity.this;
            super();
        }
    }

}
