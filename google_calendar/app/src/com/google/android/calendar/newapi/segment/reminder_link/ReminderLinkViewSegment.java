// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.reminder_link;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Parcel;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.newapi.screen.reminder.ReminderViewScreenModel;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.accounts.api.AccountData;
import com.google.android.gms.identity.accounts.api.AccountDataUtil;
import com.google.android.gms.identity.accounts.api.zzb;
import com.google.android.gms.reminders.model.ExternalApplicationLink;
import com.google.android.gms.reminders.model.Task;

public class ReminderLinkViewSegment extends TextTileView
    implements ViewSegment
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/reminder_link/ReminderLinkViewSegment);
    private final ReminderViewScreenModel model;

    public ReminderLinkViewSegment(Context context, ReminderViewScreenModel reminderviewscreenmodel)
    {
        super(context);
        model = reminderviewscreenmodel;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        super.denseMode = false;
        setFocusable(true);
    }

    public final void updateUi()
    {
        Object obj;
        byte abyte0[];
        byte byte1 = 8;
        obj = model.task.getExternalApplicationLink();
        boolean flag;
        if (obj == null || ((ExternalApplicationLink) (obj)).getApplication() == null || ((ExternalApplicationLink) (obj)).getId() == null)
        {
            flag = false;
        } else
        if (((ExternalApplicationLink) (obj)).getApplication().intValue() == 1 || ((ExternalApplicationLink) (obj)).getApplication().intValue() == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (this != null)
            {
                setVisibility(8);
            }
            return;
        }
        if (this != null)
        {
            byte byte0 = byte1;
            if (true)
            {
                byte0 = 0;
            }
            setVisibility(byte0);
        }
        abyte0 = ((ExternalApplicationLink) (obj)).getId();
        ((ExternalApplicationLink) (obj)).getApplication().intValue();
        JVM INSTR tableswitch 1 3: default 160
    //                   1 170
    //                   2 160
    //                   3 448;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new IllegalStateException("Invalid application.");
_L2:
        Object obj1;
        int i;
        int j;
        Context context = getContext();
        Object obj2 = model.account.name;
        PackageManager packagemanager = context.getPackageManager();
        obj1 = packagemanager.getLaunchIntentForPackage("com.google.android.apps.bigtop");
        obj = obj1;
        if (obj1 == null)
        {
            obj = packagemanager.getLaunchIntentForPackage("com.google.android.apps.inbox");
        }
        if (obj != null)
        {
            ((Intent) (obj)).setAction("com.google.android.apps.bigtop.intent.VIEW_EMAIL");
            ((Intent) (obj)).putExtra("plid", abyte0);
            obj2 = AccountData.forAccount(((String) (obj2)));
            zzb zzb1 = AccountDataUtil.zzbxL;
            if (context == null)
            {
                throw new NullPointerException(String.valueOf("Context must not be null."));
            }
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("Intent must not be null."));
            }
            if (obj2 == null)
            {
                throw new NullPointerException(String.valueOf("Account data must not be null."));
            }
            obj1 = ((Intent) (obj)).getComponent();
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final ReminderLinkViewSegment arg$1;
                private final Intent arg$2;
                private final String arg$3;

                public final void onClick(View view)
                {
                    Object obj4 = arg$1;
                    Object obj3 = arg$2;
                    view = arg$3;
                    obj4 = ((ReminderLinkViewSegment) (obj4)).getContext();
                    if (obj4 != null)
                    {
                        ActivityUtils.startActivity(((Context) (obj4)), ((Intent) (obj3)), ReminderLinkViewSegment.TAG);
                        obj3 = AnalyticsLoggerHolder.instance;
                        if (obj3 == null)
                        {
                            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                        }
                        ((AnalyticsLogger)obj3).trackEvent(((Context) (obj4)), "event_action", view);
                    }
                }

            .Lambda._cls0(Intent intent, String s)
            {
                arg$1 = ReminderLinkViewSegment.this;
                arg$2 = intent;
                arg$3 = s;
            }
            }

            if (obj1 != null)
            {
                abyte0 = ((ComponentName) (obj1)).getPackageName();
            } else
            {
                abyte0 = ((Intent) (obj)).getPackage();
            }
            obj1 = obj;
            if (abyte0 != null)
            {
                obj1 = obj;
                if (zzb1.zzbxM.zzG(context, abyte0))
                {
                    obj1 = Parcel.obtain();
                    ((SafeParcelable) (obj2)).writeToParcel(((Parcel) (obj1)), 0);
                    abyte0 = ((Parcel) (obj1)).marshall();
                    ((Parcel) (obj1)).recycle();
                    ((Intent) (obj)).putExtra("com.google.android.gms.accounts.ACCOUNT_DATA", abyte0);
                    obj1 = obj;
                }
            }
        } else
        {
            obj1 = null;
        }
        i = 0x7f1304ac;
        j = 0x7f0201ea;
        obj = "view_in_inbox";
_L5:
        setOnClickListener(new .Lambda._cls0(((Intent) (obj1)), ((String) (obj))));
        setIconDrawable(j);
        setPrimaryText(new CharSequence[] {
            getResources().getString(i, new Object[0])
        });
        useTextAsContentDescription(0x7f130167);
        return;
_L3:
        obj = String.valueOf("https://keep.google.com/?reminder=");
        obj1 = String.valueOf(abyte0);
        if (((String) (obj1)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj1)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        obj1 = new Intent("android.intent.action.VIEW", Uri.parse(((String) (obj))));
        j = 0x7f0201e7;
        i = 0x7f1304ad;
        obj = "view_in_keep";
        if (true) goto _L5; else goto _L4
_L4:
    }

}
