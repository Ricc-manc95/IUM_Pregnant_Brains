// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.config.remote.RemoteFeature;
import com.google.android.apps.calendar.config.remote.RemoteFeatureConfig;
import com.google.android.apps.calendar.util.string.StringUtils;
import com.google.android.calendar.activity.daybutton.DayOfMonthDrawable;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.launch.LaunchInfoActivity;
import com.google.android.calendar.launch.oobe.WhatsNewFactory;
import com.google.android.calendar.time.Time;
import com.google.android.calendar.time.clock.Clock;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.permission.PermissionsUtil;
import com.google.android.calendar.utils.phone.PhoneUtil;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

// Referenced classes of package com.google.android.calendar:
//            Utils, DateTimeFormatHelper

public class EventFragmentHostActivity extends CalendarSupportActivity
    implements PhoneUtil
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/EventFragmentHostActivity);
    public String overlayFragmentTag;
    private Uri uriForDial;

    public EventFragmentHostActivity()
    {
        overlayFragmentTag = null;
    }

    public static int getFirstBackStackEntry(FragmentManager fragmentmanager, Fragment fragment)
    {
        fragment = fragment.mTag;
        if (!TextUtils.isEmpty(fragment))
        {
            int j = fragmentmanager.getBackStackEntryCount();
            for (int i = 0; i < j; i++)
            {
                if (fragment.equals(fragmentmanager.getBackStackEntryAt(i).getName()))
                {
                    return i;
                }
            }

        }
        return -1;
    }

    public final boolean createAllInOneMenu(Menu menu, String s)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(0x7f150000, menu);
        menu = menu.findItem(0x7f100425);
        if (menu != null)
        {
            LayerDrawable layerdrawable = (LayerDrawable)menu.getIcon();
            menu = layerdrawable.findDrawableByLayerId(0x7f100424);
            int i;
            long l;
            if (menu instanceof DayOfMonthDrawable)
            {
                menu = (DayOfMonthDrawable)menu;
            } else
            {
                menu = new DayOfMonthDrawable(this);
            }
            s = new Time(s);
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            ((Time) (s)).impl.timezone = ((Time) (s)).timezone;
            ((Time) (s)).impl.set(l);
            ((Time) (s)).impl.toMillis(true);
            s.copyFieldsFromImpl();
            s.writeFieldsToImpl();
            ((Time) (s)).impl.normalize(false);
            s.copyFieldsFromImpl();
            i = ((Time) (s)).monthDay;
            menu.dayOfMonth = NumberFormat.getNumberInstance().format(i);
            menu.invalidateSelf();
            layerdrawable.mutate();
            layerdrawable.setDrawableByLayerId(0x7f100424, menu);
            layerdrawable.setAlpha(Color.alpha(getResources().getColor(0x7f0d021b)));
            menu = Features.instance;
            if (menu == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)menu).alternate_timeline();
            boolean flag = RemoteFeatureConfig.ALTERNATE_TIMELINE.enabled();
            menu = Features.instance;
            if (menu == null)
            {
                throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
            }
            ((FeatureConfig)menu).experimental_options();
            if (flag && android.os.Build.VERSION.SDK_INT >= 23)
            {
                menu = Features.instance;
                if (menu == null)
                {
                    throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                }
                ((FeatureConfig)menu).dogfood_features();
            }
        }
        return true;
    }

    public final void finishDismissOverlay(OverlayFragment overlayfragment)
    {
        int i;
        if (((Fragment) (overlayfragment)).mHost != null && ((Fragment) (overlayfragment)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!i || ((Fragment) (overlayfragment)).mFragmentManager == null)
        {
            return;
        }
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        i = getFirstBackStackEntry(fragmentmanagerimpl, overlayfragment);
        if (i > 0)
        {
            overlayFragmentTag = fragmentmanagerimpl.getBackStackEntryAt(i - 1).getName();
        } else
        {
            overlayFragmentTag = null;
        }
        overlayfragment.dismissAllowingStateLoss();
    }

    public final OverlayFragment getOverlayFragment()
    {
        if (TextUtils.isEmpty(overlayFragmentTag))
        {
            return null;
        } else
        {
            return (OverlayFragment)super.mFragments.mHost.mFragmentManager.findFragmentByTag(overlayFragmentTag);
        }
    }

    public final void initializeActionBar(boolean flag)
    {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = getResources().getColor(0x7f0d0313);
            }
            actionbar.setBackgroundDrawable(new ColorDrawable(i));
            actionbar.setIcon(new ColorDrawable(0x106000d));
            actionbar.setDisplayUseLogoEnabled(false);
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public final void makeCall(Uri uri)
    {
label0:
        {
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                uriForDial = uri;
                if (!AndroidPermissionUtils.hasCallPermissions(this))
                {
                    break label0;
                }
                makeCall(true, uri);
                uriForDial = null;
            }
            return;
        }
        Utils.requestCallPermissions(this);
    }

    public final void makeCall(boolean flag, Uri uri)
    {
        if (uri == null)
        {
            throw new NullPointerException();
        }
        Object obj;
        boolean flag1;
        if (flag && getPackageManager().hasSystemFeature("android.hardware.telephony"))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            obj = "android.intent.action.CALL";
        } else
        {
            obj = "android.intent.action.DIAL";
        }
        obj = new Intent(((String) (obj)));
        ((Intent) (obj)).setData(uri);
        if (((Intent) (obj)).resolveActivity(getPackageManager()) == null)
        {
            Toast.makeText(this, 0x7f13034b, 1).show();
            return;
        } else
        {
            startActivity(((Intent) (obj)));
            return;
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        if (i == WhatsNewFactory.WHATS_NEW_REQUEST_CODE)
        {
            if (j == 0)
            {
                finish();
                return;
            }
            LaunchInfoActivity.handleCompleteFullOobe(this);
        }
        super.onActivityResult(i, j, intent);
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        SparseBooleanArray sparsebooleanarray;
        int j;
        if (ai == null || ai.length == 0 || as == null || as.length == 0)
        {
            LogUtils.e(TAG, "Empty request permissions result for request %d", new Object[] {
                Integer.valueOf(i)
            });
            return;
        }
        LogUtils.v(PermissionsUtil.TAG, "Clearing cached permissions", new Object[0]);
        PermissionsUtil.grantedPermissions.clear();
        sparsebooleanarray = new SparseBooleanArray();
        j = 0;
_L12:
        if (j >= as.length) goto _L2; else goto _L1
_L1:
        int l = AndroidPermissionUtils.groupOfPermission(as[j]);
        if (sparsebooleanarray.get(l, false)) goto _L4; else goto _L3
_L3:
        Object obj1;
        int k;
        obj1 = as[j];
        k = ai[j];
        l;
        JVM INSTR tableswitch 0 3: default 144
    //                   0 199
    //                   1 432
    //                   2 458
    //                   3 482;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        sparsebooleanarray.put(l, true);
_L4:
        if (i == 1004 && l == 3)
        {
            String s;
            String s1;
            Object obj;
            String s2;
            String s3;
            boolean flag;
            long l1;
            long l2;
            boolean flag1;
            if (ai[j] == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            makeCall(flag1, uriForDial);
            uriForDial = null;
        }
        j++;
        continue; /* Loop/switch isn't completed */
_L6:
        s = "calendar";
        obj = "has_granted_calendar_permissions";
        s2 = "has_received_calendar_permissions_response";
        s3 = "calendar_permissions_request_count";
        s1 = "calendar_permissions_never_ask_detected";
        break MISSING_BLOCK_LABEL_224;
_L7:
        s = "contacts";
        obj = "has_granted_contacts_permissions";
        s2 = null;
        s3 = "contacts_permissions_request_count";
        s1 = "contacts_permissions_never_ask_detected";
          goto _L10
_L8:
        s = "location";
        obj = null;
        s2 = null;
        s3 = "location_permissions_request_count";
        s1 = "location_permissions_never_ask_detected";
          goto _L10
_L9:
        s = "call";
        obj = null;
        s2 = null;
        s3 = "call_permissions_request_count";
        s1 = "call_permissions_never_ask_detected";
_L10:
        if (k == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = PermissionsUtil.shouldShowRationale(this, ((String) (obj1)));
        if (!flag && !flag1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj1 = getSharedPreferences("com.google.android.calendar_preferences", 0);
        if (flag1 && ((SharedPreferences) (obj1)).getBoolean(s1, false))
        {
            continue; /* Loop/switch isn't completed */
        }
        l2 = ((SharedPreferences) (obj1)).getLong(s3, 0L);
        if (flag)
        {
            l1 = 0L;
        } else
        {
            l1 = 1L;
        }
        l1 = l2 + l1;
        if (flag)
        {
            l2 = 0L;
        } else
        {
            l2 = l1;
        }
        obj1 = ((SharedPreferences) (obj1)).edit();
        ((android.content.SharedPreferences.Editor) (obj1)).putLong(s3, l2);
        ((android.content.SharedPreferences.Editor) (obj1)).putBoolean(s1, flag1);
        if (flag && obj != null)
        {
            ((android.content.SharedPreferences.Editor) (obj1)).putBoolean(((String) (obj)), true);
        }
        if (s2 != null)
        {
            ((android.content.SharedPreferences.Editor) (obj1)).putBoolean(s2, true);
        }
        ((android.content.SharedPreferences.Editor) (obj1)).apply();
        if (flag)
        {
            s1 = "granted";
        } else
        {
            s1 = "denied";
        }
        if (!flag)
        {
            if (flag1)
            {
                l1 = 1L;
            } else
            {
                l1 = 0L;
            }
        }
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)obj).trackEvent(this, "permissions", s, s1, Long.valueOf(l1));
        if (true) goto _L5; else goto _L2
_L2:
        super.onRequestPermissionsResult(i, as, ai);
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        uriForDial = (Uri)bundle.getParcelable("uri_for_dial");
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("uri_for_dial", uriForDial);
    }

    public final void openDialer(Uri uri)
    {
        makeCall(false, uri);
    }

    public final void setCustomActionBar(final View customActionBar)
    {
        setToolbarAsActionBarIfAble(true);
        if (getSupportActionBar() != null)
        {
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayOptions(18);
            final TextView textView = (TextView)customActionBar.findViewById(0x7f100102);
            DateTimeFormatHelper datetimeformathelper;
            long l;
            if (Clock.mockedTimestamp > 0L)
            {
                l = Clock.mockedTimestamp;
            } else
            {
                l = System.currentTimeMillis();
            }
            datetimeformathelper = DateTimeFormatHelper.instance;
            if (datetimeformathelper == null)
            {
                throw new NullPointerException(String.valueOf("DateTimeFormatHelper#initialize(...) must be called first"));
            }
            textView.setText(StringUtils.capitalizeStandalone(((DateTimeFormatHelper)datetimeformathelper).getDateRangeText(l, l, 52), Locale.getDefault()));
            actionbar.setCustomView(customActionBar, new android.support.v7.app.ActionBar.LayoutParams(-1, -2));
            final int titleStartPadding = getResources().getDimensionPixelSize(0x7f0e0111);
            customActionBar.getViewTreeObserver().addOnPreDrawListener(new _cls1());
        }
    }

    public final void setToolbarAsActionBarIfAble(boolean flag)
    {
        Toolbar toolbar = (Toolbar)findViewById(0x7f100113);
        if (toolbar != null)
        {
            toolbar.setVisibility(0);
            if (flag)
            {
                String s = getString(0x7f130057);
                if (!TextUtils.isEmpty(s))
                {
                    toolbar.ensureNavButtonView();
                }
                if (toolbar.mNavButtonView != null)
                {
                    toolbar.mNavButtonView.setContentDescription(s);
                }
            }
            setSupportActionBar(toolbar);
        }
    }

    public void showOverlayFragment(String s, OverlayFragment overlayfragment)
    {
        Object obj = super.mFragments.mHost.mFragmentManager;
        if (((FragmentManager) (obj)).isStateSaved())
        {
            return;
        }
        ((FragmentManager) (obj)).executePendingTransactions();
        FragmentTransaction fragmenttransaction = ((FragmentManager) (obj)).beginTransaction();
        obj = (OverlayFragment)((FragmentManager) (obj)).findFragmentByTag(s);
        if (obj != null)
        {
            finishDismissOverlay(((OverlayFragment) (obj)));
        }
        fragmenttransaction.addToBackStack(s);
        overlayfragment.show(fragmenttransaction, s);
        overlayFragmentTag = s;
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        private final View val$customActionBar;
        private final TextView val$textView;
        private final int val$titleStartPadding;

        public final boolean onPreDraw()
        {
            boolean flag = false;
            ViewTreeObserver viewtreeobserver = customActionBar.getViewTreeObserver();
            if (!viewtreeobserver.isAlive())
            {
                flag = true;
            } else
            {
                viewtreeobserver.removeOnPreDrawListener(this);
                int i = Utils.getStartCoordinate(customActionBar);
                if (i < titleStartPadding)
                {
                    textView.setPaddingRelative(titleStartPadding - i, 0, 0, 0);
                    return false;
                }
            }
            return flag;
        }

        _cls1()
        {
            customActionBar = view;
            titleStartPadding = i;
            textView = textview;
            super();
        }
    }

}
