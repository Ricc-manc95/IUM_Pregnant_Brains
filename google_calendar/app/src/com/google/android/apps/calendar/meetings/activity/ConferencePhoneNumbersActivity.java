// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.activity;

import android.accounts.Account;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementAttacher;
import com.google.android.apps.calendar.loggers.visualelements.VisualElementHolder;
import com.google.android.apps.calendar.meetings.MeetingsPstnFinder;
import com.google.android.apps.calendar.meetings.impl.MeetingsPstnFinderImpl;
import com.google.android.apps.calendar.meetings.util.MeetingsUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.calendar.event.conference.PhoneNumberDetails;
import com.google.android.calendar.utils.ColorUtils;
import com.google.android.calendar.utils.statusbar.StatusbarAnimatorCompat;
import com.google.common.base.Platform;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.meetings.activity:
//            PhoneClickListener, PhoneNumberInfoAdapter

public class ConferencePhoneNumbersActivity extends CalendarSupportActivity
    implements PhoneClickListener
{

    public PhoneNumberInfoAdapter adapter;
    public int eventColor;
    private boolean fetchFromGstatic;
    private String passCode;
    public Uri url;

    public ConferencePhoneNumbersActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f050031);
        bundle = VisualElementHolder.instance;
        if (bundle == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        }
        ((VisualElementAttacher)bundle).attachPhoneNumbersActivity(findViewById(0x1020002));
        bundle = getSupportActionBar();
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        Object obj = (ActionBar)bundle;
        ((ActionBar) (obj)).setDisplayHomeAsUpEnabled(true);
        bundle = getIntent();
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        Object obj1 = (Intent)bundle;
        bundle = (Account)((Intent) (obj1)).getParcelableExtra("account");
        if (bundle == null)
        {
            throw new NullPointerException();
        }
        bundle = (Account)bundle;
        boolean flag = ((Intent) (obj1)).getBooleanExtra("has_interop", false);
        fetchFromGstatic = ((Intent) (obj1)).getBooleanExtra("use_gstatic", true);
        url = (Uri)((Intent) (obj1)).getParcelableExtra("conference_uri");
        passCode = Platform.nullToEmpty(url.getQueryParameter("pin"));
        if (((Intent) (obj1)).hasExtra("event_color"))
        {
            eventColor = ((Intent) (obj1)).getIntExtra("event_color", 0xff888888);
            obj1 = StatusbarAnimatorCompat.createInstance(getWindow());
            ((StatusbarAnimatorCompat) (obj1)).setStatusbarColor(ColorUtils.blend(eventColor, 0x33000000));
            ((StatusbarAnimatorCompat) (obj1)).setLightStatusbar(false);
            ((ActionBar) (obj)).setBackgroundDrawable(new ColorDrawable(eventColor));
        }
        obj = (TextView)findViewById(0x7f100138);
        ((TextView) (obj)).setText(getString(0x7f13012e, new Object[] {
            MeetingsUtils.formatPin(getResources().getConfiguration().locale, passCode)
        }));
        ((TextView) (obj)).setContentDescription(getString(0x7f13012e, new Object[] {
            String.valueOf(passCode.toString().replace(" ", "").replace("", " ").trim()).concat(" #")
        }));
        if (flag)
        {
            obj = (TextView)findViewById(0x7f10013a);
            ((TextView) (obj)).setVisibility(0);
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final ConferencePhoneNumbersActivity arg$1;

                public final void onClick(View view)
                {
                    view = arg$1;
                    ConferenceCallUtils.showInteropInstructions(view, ((ConferencePhoneNumbersActivity) (view)).url, ((ConferencePhoneNumbersActivity) (view)).eventColor);
                }

            .Lambda._cls0()
            {
                arg$1 = ConferencePhoneNumbersActivity.this;
            }
            }

            ((TextView) (obj)).setOnClickListener(new .Lambda._cls0());
        }
        obj = (RecyclerView)findViewById(0x7f100139);
        adapter = new PhoneNumberInfoAdapter(this, bundle);
        ((RecyclerView) (obj)).setAdapter(adapter);
        ((RecyclerView) (obj)).setLayoutManager(new LinearLayoutManager(this));
        bundle = (new MeetingsPstnFinderImpl(this, fetchFromGstatic)).listPhones(passCode);
        obj = new _cls1();
        obj1 = CalendarExecutor.MAIN;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            bundle.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(bundle, ((FutureCallback) (obj))), ((java.util.concurrent.Executor) (obj1)));
            return;
        }
    }

    public final void onDial(PhoneNumberDetails phonenumberdetails)
    {
        Intent intent = new Intent("android.intent.action.DIAL");
        Object obj = phonenumberdetails.phoneNumber();
        phonenumberdetails = phonenumberdetails.passCode();
        obj = new StringBuilder(((String) (obj)));
        if (!TextUtils.isEmpty(phonenumberdetails))
        {
            ((StringBuilder) (obj)).append(';');
            ((StringBuilder) (obj)).append(phonenumberdetails);
            ((StringBuilder) (obj)).append('#');
        }
        intent.setData(Uri.fromParts("tel", ((StringBuilder) (obj)).toString(), null));
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch (menuitem.getItemId())
        {
        default:
            return super.onOptionsItemSelected(menuitem);

        case 16908332: 
            finish();
            break;
        }
        return true;
    }

    protected void onStart()
    {
        super.onStart();
        VisualElementAttacher visualelementattacher = VisualElementHolder.instance;
        if (visualelementattacher == null)
        {
            throw new NullPointerException(String.valueOf("VisualElementHolder must receive an instance first"));
        } else
        {
            ((VisualElementAttacher)visualelementattacher).recordImpression(this, findViewById(0x1020002));
            return;
        }
    }

    private class _cls1
        implements FutureCallback
    {

        private final ConferencePhoneNumbersActivity this$0;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e("PhoneNumbersActivity", throwable, "No phone number to display", new Object[0]);
            finish();
        }

        public final void onSuccess(Object obj)
        {
            Object obj1 = (List)obj;
            obj = adapter;
            ((PhoneNumberInfoAdapter) (obj)).phoneList.clear();
            ArrayList arraylist = new ArrayList();
            obj1 = ((List) (obj1)).iterator();
            do
            {
                if (!((Iterator) (obj1)).hasNext())
                {
                    break;
                }
                PhoneNumberDetails phonenumberdetails = (PhoneNumberDetails)((Iterator) (obj1)).next();
                if (phonenumberdetails.availability() != Availability.NONE)
                {
                    arraylist.add(phonenumberdetails);
                }
            } while (true);
            Collections.sort(arraylist, PhoneNumberInfoAdapter..Lambda._cls0.$instance);
            ((PhoneNumberInfoAdapter) (obj)).phoneList.addAll(arraylist);
            ((android.support.v7.widget.RecyclerView.Adapter) (obj)).mObservable.notifyChanged();
        }

        _cls1()
        {
            this$0 = ConferencePhoneNumbersActivity.this;
            super();
        }
    }

}
