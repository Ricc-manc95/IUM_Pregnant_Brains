// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.timely.FindTimeAttendee;
import com.google.android.calendar.timely.FindTimeControllerFragment;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event:
//            FindTimeSuggestionUi

public class FindTimeActivity extends CalendarSupportActivity
    implements com.google.android.calendar.timely.FindTimeControllerFragment.OnFinishListener
{

    private FindTimeControllerFragment controllerFragment;

    public FindTimeActivity()
    {
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        boolean flag1;
        boolean flag2;
        flag1 = false;
        flag2 = true;
        if (accessibilityevent.getEventType() == 32) goto _L2; else goto _L1
_L1:
        flag2 = super.dispatchPopulateAccessibilityEvent(accessibilityevent);
_L4:
        return flag2;
_L2:
        boolean flag;
        int i = controllerFragment.state;
        if (i == 2 || i == 3 || i == 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            accessibilityevent.getText().add(getString(0x7f13005c));
            return true;
        }
        int j = controllerFragment.state;
        if (j != 6)
        {
            flag = flag1;
            if (j != 7)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        flag = true;
        if (flag) goto _L4; else goto _L3
_L3:
        if (controllerFragment.state == 9)
        {
            accessibilityevent.getText().add(getString(0x7f13005b));
            return true;
        }
        if (controllerFragment.state == 1)
        {
            accessibilityevent.getText().add(controllerFragment.suggestionUi.getLoadingString());
            return true;
        } else
        {
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }
    }

    public void finish()
    {
        super.finish();
        overridePendingTransition(0, 0);
        View view = findViewById(0x1020002);
        if (view != null)
        {
            ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void onBackPressed()
    {
        controllerFragment.transitionBack();
    }

    protected void onCreate(Bundle bundle)
    {
        String s;
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl;
        String s3;
        ArrayList arraylist;
        Object obj2;
        Account account;
        int i;
        int j;
        long l;
        long l1;
        boolean flag1;
        super.onCreate(bundle);
        setContentView(0x7f050068);
        obj2 = getIntent();
        fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        controllerFragment = (FindTimeControllerFragment)fragmentmanagerimpl.findFragmentByTag("find_time_controller_fragment");
        if (controllerFragment != null)
        {
            break MISSING_BLOCK_LABEL_746;
        }
        l = ((Intent) (obj2)).getLongExtra("start_millis", 0L);
        l1 = ((Intent) (obj2)).getLongExtra("end_millis", 0L);
        Object obj = ((Intent) (obj2)).getStringExtra("timezone");
        s = ((String) (obj));
        if (obj == null)
        {
            s = Utils.getTimeZoneId(this);
        }
        j = ((Intent) (obj2)).getIntExtra("event_color", getResources().getColor(0x7f0d0084));
        s3 = ((Intent) (obj2)).getStringExtra("event_reference_id");
        flag1 = ((Intent) (obj2)).getBooleanExtra("is_recurring_event", false);
        ArrayList arraylist1 = ((Intent) (obj2)).getStringArrayListExtra("attendees");
        ArrayList arraylist2 = ((Intent) (obj2)).getStringArrayListExtra("attendee_display_names");
        arraylist = new ArrayList();
        obj = ((Intent) (obj2)).getStringExtra("account_type");
        Object obj1;
        if (obj == null)
        {
            obj = Absent.INSTANCE;
        } else
        {
            obj = new Present(obj);
        }
        obj1 = AccountDataUtil.zzbxL;
        if (this == null)
        {
            throw new NullPointerException(String.valueOf("Context must not be null."));
        }
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("Intent must not be null."));
        }
        if (this == null)
        {
            throw new NullPointerException(String.valueOf("Context must not be null."));
        }
        if (obj2 == null)
        {
            throw new NullPointerException(String.valueOf("Intent must not be null."));
        }
        Account aaccount[];
        FindTimeAttendee findtimeattendee;
        String s4;
        Object obj3;
        int k;
        boolean flag2;
        if (!((Intent) (obj2)).hasExtra("com.google.android.gms.accounts.ACCOUNT_DATA"))
        {
            obj1 = null;
        } else
        {
            obj1 = AccountData.CREATOR;
            account = ((Intent) (obj2)).getByteArrayExtra("com.google.android.gms.accounts.ACCOUNT_DATA");
            if (account == null)
            {
                obj1 = null;
            } else
            {
                if (obj1 == null)
                {
                    throw new NullPointerException("null reference");
                }
                Parcel parcel = Parcel.obtain();
                parcel.unmarshall(account, 0, account.length);
                parcel.setDataPosition(0);
                obj1 = (SafeParcelable)((android.os.Parcelable.Creator) (obj1)).createFromParcel(parcel);
                parcel.recycle();
            }
            obj1 = (AccountData)obj1;
        }
        aaccount = AccountManager.get(this).getAccounts();
        k = aaccount.length;
        i = 0;
_L7:
        if (i >= k) goto _L2; else goto _L1
_L1:
        account = aaccount[i];
        if (!account.name.equals(((AccountData) (obj1)).zzajr))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!((Optional) (obj)).isPresent()) goto _L4; else goto _L3
_L3:
        s4 = account.type;
        obj3 = ((Optional) (obj)).get();
        boolean flag;
        if (s4 == obj3 || s4 != null && s4.equals(obj3))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
        obj = ((Intent) (obj2)).getStringExtra("organizer");
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = account.name;
        }
        for (i = 0; i < arraylist1.size(); i++)
        {
            s4 = (String)arraylist1.get(i);
            aaccount = null;
            obj1 = aaccount;
            if (arraylist2 != null)
            {
                obj1 = aaccount;
                if (i < arraylist2.size())
                {
                    obj1 = (String)arraylist2.get(i);
                }
            }
            flag2 = ((String) (obj)).equals(s4);
            findtimeattendee = new FindTimeAttendee(account.name, s4, flag2);
            findtimeattendee.displayName = ((String) (obj1));
            arraylist.add(findtimeattendee);
        }

        break MISSING_BLOCK_LABEL_652;
_L4:
        if (AccountUtil.isGoogleType(account.type) || AccountUtil.isGoogleExchangeType(account.type) || AccountUtil.isGoogleExchangeGoAccount(account.type)) goto _L6; else goto _L5
_L5:
        i++;
          goto _L7
_L2:
        throw new IllegalArgumentException("Invalid Account");
        String s1 = ((Intent) (obj2)).getStringExtra("existing_event_id");
        String s2 = ((Intent) (obj2)).getStringExtra("existing_event_calendar_id");
        FindTimeControllerFragment findtimecontrollerfragment = new FindTimeControllerFragment();
        obj2 = new Bundle(7);
        FindTimeControllerFragment.addArguments(((Bundle) (obj2)), l, l1, j, arraylist, account, s, s3, flag1, false, s1, s2);
        findtimecontrollerfragment.setArguments(((Bundle) (obj2)));
        controllerFragment = findtimecontrollerfragment;
        fragmentmanagerimpl.beginTransaction().add(controllerFragment, "find_time_controller_fragment").commit();
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        performancemetriccollector = (PerformanceMetricCollector)performancemetriccollector;
        if (bundle == null)
        {
            bundle = "FindTimeActivity.Created";
        } else
        {
            bundle = "FindTimeActivity.Recreated";
        }
        performancemetriccollector.recordMemory(bundle);
        return;
    }

    public void onDestroy()
    {
        controllerFragment = null;
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory("FindTimeActivity.Destroyed");
            super.onDestroy();
            return;
        }
    }

    public final void onFinishedWithCancel()
    {
        setResult(0);
        finish();
    }

    public final void onFinishedWithSlot$5154KJ3AC5R62BRCC5N6EBQJEHP6IRJ77D66KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0(long l, long l1, String s)
    {
        setResult(-1, (new Intent()).putExtra("start_millis", l).putExtra("end_millis", l1).putExtra("timezone", s));
        finish();
    }
}
