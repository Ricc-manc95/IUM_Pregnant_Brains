// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.CalendarApplication;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.calendar.utils.permission.PermissionsUtil;

public class RequestPermissionsActivity extends EventFragmentHostActivity
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/launch/RequestPermissionsActivity);
    private Button button;
    private View noPermissionLayout;
    private boolean restarting;

    public RequestPermissionsActivity()
    {
    }

    public static Intent createIntent(Context context)
    {
        if (!AndroidPermissionUtils.hasMandatoryPermissions(context))
        {
            Intent intent = new Intent();
            intent.setClass(context, com/google/android/calendar/launch/RequestPermissionsActivity);
            return intent;
        } else
        {
            return null;
        }
    }

    private final void onMandatoryPermissionsGranted()
    {
        setResult(-1);
        ((CalendarApplication)getApplication()).initializeMandatoryPermissionBasedComponents();
        if (!AndroidPermissionUtils.hasContactsPermissions(this))
        {
            AndroidPermissionUtils.requestContactsPermissions(this, 2);
            return;
        } else
        {
            finish();
            return;
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        i;
        JVM INSTR tableswitch 1003 1003: default 28
    //                   1003 29;
           goto _L1 _L2
_L1:
        return;
_L2:
        if (AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            onMandatoryPermissionsGranted();
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f050021);
        setCustomActionBar(getLayoutInflater().inflate(0x7f05001d, null));
        ActionBar actionbar = getActionBar();
        if (actionbar != null)
        {
            actionbar.setDisplayUseLogoEnabled(true);
        }
        noPermissionLayout = getLayoutInflater().inflate(0x7f0500f7, null);
        button = (Button)noPermissionLayout.findViewById(0x7f1002b3);
        boolean flag;
        if (bundle != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        restarting = flag;
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        createAllInOneMenu(menu, Utils.getTimeZoneId(this));
        MenuItem menuitem = menu.findItem(0x7f100426);
        if (menuitem != null)
        {
            menuitem.setEnabled(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        boolean flag1;
        flag1 = true;
        super.onRequestPermissionsResult(i, as, ai);
        i;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 53
    //                   2 235;
           goto _L1 _L2 _L3
_L1:
        LogUtils.e(TAG, "Unexpected permission request code: %d", new Object[] {
            Integer.valueOf(i)
        });
        return;
_L2:
        int j;
        if (AndroidPermissionUtils.hasMandatoryPermissions(this))
        {
            onMandatoryPermissionsGranted();
            return;
        }
        LogUtils.d(TAG, "Permissions denied", new Object[0]);
        setContentView(noPermissionLayout);
        as = AndroidPermissionUtils.PERMISSIONS_MANDATORY;
        j = as.length;
        i = 0;
_L8:
        boolean flag = flag1;
        if (i >= j) goto _L5; else goto _L4
_L4:
        ai = as[i];
        if (PermissionsUtil.checkSelfPermission(this, ai) == 0 || PermissionsUtil.shouldShowRationale(this, ai)) goto _L7; else goto _L6
_L6:
        flag = false;
_L5:
        if (flag)
        {
            button.setText(getString(0x7f1300e8));
            button.setOnClickListener(new _cls1());
            findViewById(0x7f1002b2).setVisibility(8);
        } else
        {
            button.setText(getString(0x7f1300e9));
            button.setOnClickListener(new _cls2());
            findViewById(0x7f1002b2).setVisibility(0);
        }
        setResult(0);
        noPermissionLayout.setVisibility(0);
        return;
_L7:
        i++;
          goto _L8
_L3:
        finish();
        return;
    }

    protected void onRestart()
    {
        restarting = true;
        super.onRestart();
    }

    protected void onStart()
    {
        boolean flag1;
        flag1 = true;
        super.onStart();
        if (!getSharedPreferences("com.google.android.calendar_preferences", 0).getBoolean("has_received_calendar_permissions_response", false)) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        int j;
        LogUtils.d(TAG, "We have prompted for calendar permissions before.", new Object[0]);
        setContentView(noPermissionLayout);
        as = AndroidPermissionUtils.PERMISSIONS_MANDATORY;
        j = as.length;
        i = 0;
_L10:
        boolean flag = flag1;
        if (i >= j) goto _L4; else goto _L3
_L3:
        String s = as[i];
        if (PermissionsUtil.checkSelfPermission(this, s) == 0 || PermissionsUtil.shouldShowRationale(this, s)) goto _L6; else goto _L5
_L5:
        flag = false;
_L4:
        if (flag)
        {
            button.setText(getString(0x7f1300e8));
            button.setOnClickListener(new _cls1());
            findViewById(0x7f1002b2).setVisibility(8);
        } else
        {
            button.setText(getString(0x7f1300e9));
            button.setOnClickListener(new _cls2());
            findViewById(0x7f1002b2).setVisibility(0);
        }
        setResult(0);
        noPermissionLayout.setVisibility(0);
_L8:
        return;
_L6:
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (restarting) goto _L8; else goto _L7
_L7:
        AndroidPermissionUtils.requestMandatoryPermissions(this, 1);
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final RequestPermissionsActivity this$0;

        public final void onClick(View view)
        {
            AndroidPermissionUtils.requestMandatoryPermissions(RequestPermissionsActivity.this, 1);
        }

        _cls1()
        {
            this$0 = RequestPermissionsActivity.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final RequestPermissionsActivity this$0;

        public final void onClick(View view)
        {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            view = String.valueOf(getPackageName());
            if (view.length() != 0)
            {
                view = "package:".concat(view);
            } else
            {
                view = new String("package:");
            }
            intent.setData(Uri.parse(view));
            startActivityForResult(intent, 1003);
        }

        _cls2()
        {
            this$0 = RequestPermissionsActivity.this;
            super();
        }
    }

}
