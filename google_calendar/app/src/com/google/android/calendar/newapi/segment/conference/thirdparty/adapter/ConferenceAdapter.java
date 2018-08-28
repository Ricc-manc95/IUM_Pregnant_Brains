// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.text.BidiFormatter;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.utils.phone.PhoneUtil;
import com.google.android.calendar.utils.snackbar.SnackbarShower;
import com.google.common.base.Platform;

public abstract class ConferenceAdapter
    implements android.view.View.OnClickListener, android.view.View.OnLongClickListener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/conference/thirdparty/adapter/ConferenceAdapter);
    public final String accessCode;
    public final Context context;
    public final String label;
    public final String meetingCode;
    public final String passcode;
    public final String password;
    public final String pin;
    private final SnackbarShower snackbarShower;
    public final Uri uri;

    public ConferenceAdapter(Context context1, SnackbarShower snackbarshower, Conference conference)
    {
        context = context1;
        snackbarShower = snackbarshower;
        uri = Uri.parse(conference.getUri()).normalizeScheme();
        snackbarshower = conference.getLabel();
        String s = conference.getName();
        context1 = snackbarshower;
        if (Platform.stringIsNullOrEmpty(snackbarshower))
        {
            context1 = Platform.nullToEmpty(s);
        }
        label = context1;
        meetingCode = conference.getMeetingCode();
        accessCode = conference.getAccessCode();
        passcode = conference.getPasscode();
        password = conference.getPassword();
        context1 = conference.getPin();
        snackbarshower = conference.getPassCode();
        if (context1.isEmpty())
        {
            context1 = Platform.nullToEmpty(snackbarshower);
        }
        pin = context1;
    }

    public final void addAccessInfo(String s, int i, com.google.common.collect.ImmutableList.Builder builder)
    {
        if (!Platform.stringIsNullOrEmpty(s))
        {
            s = BidiFormatter.getInstance(context.getResources().getConfiguration().locale).unicodeWrap(s, TextDirectionHeuristicsCompat.LTR);
            s = (com.google.common.collect.ImmutableList.Builder)builder.add(context.getResources().getString(i, new Object[] {
                s
            }));
        }
    }

    protected String getClipText()
    {
        return uri.toString();
    }

    protected int getOnLongClickSuccessString()
    {
        return 0x7f130139;
    }

    public String getPrimaryText()
    {
        String s;
        if (!Platform.stringIsNullOrEmpty(label))
        {
            s = label;
        } else
        {
            s = uri.toString();
        }
        return BidiFormatter.getInstance(context.getResources().getConfiguration().locale).unicodeWrap(s, TextDirectionHeuristicsCompat.LTR);
    }

    public boolean onLongClick(View view)
    {
        view = (ClipboardManager)context.getSystemService("clipboard");
        if (view != null)
        {
            view.setPrimaryClip(ClipData.newPlainText(context.getString(0x7f13010e), getClipText()));
            snackbarShower.showSnackbar(context.getResources().getString(getOnLongClickSuccessString()), 0);
        } else
        {
            snackbarShower.showSnackbar(context.getString(0x7f1301e7), 0);
            LogUtils.w(TAG, "Failed to copy conference link to clipboard.", new Object[0]);
        }
        return true;
    }

    protected final void startCall(PhoneUtil phoneutil)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            phoneutil.makeCall(uri);
            return;
        } else
        {
            phoneutil.openDialer(uri);
            return;
        }
    }

}
