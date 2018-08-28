// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.proposenewtime;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollector;
import com.google.android.apps.calendar.primes.api.PerformanceMetricCollectorHolder;
import com.google.android.apps.calendar.proposenewtime.net.ProposeNewTimeRendezvousClient;
import com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState;
import com.google.android.apps.calendar.proposenewtime.state.TimeProposal;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import java.util.Locale;

// Referenced classes of package com.google.android.apps.calendar.proposenewtime:
//            ProposeNewTimeFragment

public class ProposeNewTimeActivity extends CalendarSupportActivity
{

    public ProposeNewTimeActivity()
    {
    }

    public void onBackPressed()
    {
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f05012e);
        ProposeNewTimeState proposenewtimestate = (ProposeNewTimeState)getIntent().getParcelableExtra("propose_new_time_initial_state");
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragments.mHost.mFragmentManager;
        Context context = getApplicationContext();
        Object obj1 = proposenewtimestate.getAccount();
        Object obj = (ProposeNewTimeRendezvousClient)fragmentmanagerimpl.findFragmentByTag("propose_new_time_client_fragment");
        ProposeNewTimeFragment proposenewtimefragment;
        if (obj == null)
        {
            String s = ((Account) (obj1)).name;
            obj1 = new ProposeNewTimeRendezvousClient();
            Bundle bundle2 = new Bundle();
            obj = context.getResources().getConfiguration().locale;
            if (obj != null)
            {
                obj = ((Locale) (obj)).getLanguage();
            } else
            {
                obj = null;
            }
            bundle2.putString("language", ((String) (obj)));
            bundle2.putString("account_email", s);
            ((Fragment) (obj1)).setArguments(bundle2);
            fragmentmanagerimpl.beginTransaction().add(((Fragment) (obj1)), "propose_new_time_client_fragment").commit();
            obj = obj1;
        }
        proposenewtimefragment = (ProposeNewTimeFragment)fragmentmanagerimpl.findFragmentByTag("propose_new_time_controller_fragment");
        obj1 = proposenewtimefragment;
        if (proposenewtimefragment == null)
        {
            obj1 = new ProposeNewTimeFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putParcelable("initial_state", proposenewtimestate);
            ((Fragment) (obj1)).setArguments(bundle1);
            fragmentmanagerimpl.beginTransaction().add(0x7f10013c, ((Fragment) (obj1)), "propose_new_time_controller_fragment").commit();
        }
        obj1.rendezvousClient = ((ProposeNewTimeRendezvousClient) (obj));
        obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        obj1 = (AnalyticsLogger)obj;
        if (proposenewtimestate.getMode() == com.google.android.apps.calendar.proposenewtime.state.ProposeNewTimeState.Mode.PROPOSE)
        {
            obj = "start_propose";
        } else
        {
            obj = "start_review";
        }
        ((AnalyticsLogger) (obj1)).trackEvent(this, "propose_new_time", ((String) (obj)));
        obj = PerformanceMetricCollectorHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        }
        obj = (PerformanceMetricCollector)obj;
        if (bundle == null)
        {
            bundle = "ProposeNewTimeActivity.Created";
        } else
        {
            bundle = "ProposeNewTimeActivity.Recreated";
        }
        ((PerformanceMetricCollector) (obj)).recordMemory(bundle);
    }

    public void onDestroy()
    {
        PerformanceMetricCollector performancemetriccollector = PerformanceMetricCollectorHolder.instance;
        if (performancemetriccollector == null)
        {
            throw new NullPointerException(String.valueOf("PrimesLogger not set"));
        } else
        {
            ((PerformanceMetricCollector)performancemetriccollector).recordMemory("ProposeNewTimeActivity.Destroyed");
            super.onDestroy();
            return;
        }
    }

    public final void onProposalAccepted(TimeProposal timeproposal, com.google.android.calendar.api.event.attendee.Response.ResponseStatus responsestatus, boolean flag)
    {
        Object obj = AnalyticsLoggerHolder.instance;
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        AnalyticsLogger analyticslogger = (AnalyticsLogger)obj;
        if (flag)
        {
            obj = "send_proposal";
        } else
        {
            obj = "accept_proposal";
        }
        analyticslogger.trackEvent(this, "propose_new_time", ((String) (obj)));
        obj = getIntent();
        ((Intent) (obj)).putExtra("propose_new_time_proposal", timeproposal);
        ((Intent) (obj)).putExtra("propose_new_time_response_status", responsestatus);
        setResult(-1, ((Intent) (obj)));
        finish();
    }
}
