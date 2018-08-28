// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail.lodging;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.smartmail.LodgingReservation;
import com.google.android.calendar.api.event.smartmail.Organization;
import com.google.android.calendar.api.event.smartmail.SmartMailActionTarget;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.api.event.smartmail.SmartMailTime;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.activity.ActivityUtils;
import java.util.List;
import java.util.Locale;

public final class LodgingInformationViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private final SmartMailInfo model;

    public LodgingInformationViewSegment(Activity activity, EventHolder eventholder)
    {
        super(activity);
        model = eventholder.getEvent().getSmartMailInfo();
        setOrientation(1);
    }

    private final TextTileView createCheckInOutTile(SmartMailTime smartmailtime, int i)
    {
        TextTileView texttileview = new TextTileView(getContext());
        texttileview.indentContent().denseMode = false;
        texttileview.setPrimaryText(new CharSequence[] {
            texttileview.getResources().getString(i, new Object[0])
        });
        texttileview.setSecondaryText(new CharSequence[] {
            SmartMailUtils.formatToLocalDateTime(getContext(), smartmailtime)
        });
        texttileview.setImportantForAccessibility(1);
        return SmartMailUtils.showTileIfContainsContent(texttileview);
    }

    public final void onClick(View view)
    {
        if (!(view.getTag() instanceof SmartMailAddress)) goto _L2; else goto _L1
_L1:
        android.content.Context context = getContext();
        AnalyticsLogger analyticslogger = AnalyticsLoggerHolder.instance;
        if (analyticslogger == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger).trackEvent(context, "event_action", "open_location");
        view = (SmartMailAddress)view.getTag();
        context = getContext();
        if (((SmartMailAddress) (view)).mapLink != null && !TextUtils.isEmpty(((SmartMailAddress) (view)).mapLink.uri))
        {
            view = ((SmartMailAddress) (view)).mapLink.uri;
        } else
        if (!TextUtils.isEmpty(((SmartMailAddress) (view)).latitude) && !TextUtils.isEmpty(((SmartMailAddress) (view)).longitude))
        {
            view = String.format(Locale.ENGLISH, "geo:%f,%f", new Object[] {
                Float.valueOf(((SmartMailAddress) (view)).latitude), Float.valueOf(((SmartMailAddress) (view)).longitude)
            });
        } else
        if (TextUtils.isEmpty(((SmartMailAddress) (view)).streetAddress))
        {
            view = ((SmartMailAddress) (view)).name;
        } else
        {
            view = ((SmartMailAddress) (view)).streetAddress;
        }
_L6:
        if (!TextUtils.isEmpty(view))
        {
            ActivityUtils.startActivityForUrl(context, view, "RestaurantSegment");
        }
_L4:
        return;
_L2:
        if (!(view.getTag() instanceof String)) goto _L4; else goto _L3
_L3:
        android.content.Context context1 = getContext();
        AnalyticsLogger analyticslogger1 = AnalyticsLoggerHolder.instance;
        if (analyticslogger1 == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)analyticslogger1).trackEvent(context1, "event_action", "tap_contact_number");
        ((EventFragmentHostActivity)getContext()).makeCall(false, ConferenceCallUtils.buildUri((String)view.getTag(), null));
        return;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void updateUi()
    {
        Object obj3 = null;
        removeAllViews();
        Object obj = model;
        boolean flag;
        if (obj == null || ((SmartMailInfo) (obj)).lodgingReservations.isEmpty())
        {
            obj = null;
        } else
        {
            obj = (LodgingReservation)((SmartMailInfo) (obj)).lodgingReservations.get(0);
        }
        if (obj != null && ((LodgingReservation) (obj)).lodging != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (this != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            setVisibility(i);
        }
        if (flag)
        {
            Object obj1 = model;
            Object obj2;
            Organization organization;
            TextTileView texttileview;
            String s;
            SmartMailAddress smartmailaddress;
            String s1;
            if (obj1 == null || ((SmartMailInfo) (obj1)).lodgingReservations.isEmpty())
            {
                obj1 = null;
            } else
            {
                obj1 = (LodgingReservation)((SmartMailInfo) (obj1)).lodgingReservations.get(0);
            }
            organization = ((LodgingReservation) (obj1)).lodging;
            texttileview = new TextTileView(getContext());
            obj2 = texttileview.setIconDrawable(0x7f020209);
            obj2.denseMode = false;
            ((TileView) (obj2)).setContentDescription(getResources().getString(0x7f130172));
            s = ((LodgingReservation) (obj1)).lodging.name;
            smartmailaddress = ((LodgingReservation) (obj1)).lodging.address;
            s1 = SmartMailUtils.formatAddress(smartmailaddress);
            obj2 = obj3;
            if (smartmailaddress != null)
            {
                if (smartmailaddress.mapLink == null)
                {
                    obj2 = obj3;
                } else
                {
                    obj2 = smartmailaddress.mapLink.uri;
                }
            }
            if (!TextUtils.isEmpty(s1) || !TextUtils.isEmpty(((CharSequence) (obj2))))
            {
                texttileview.setTag(smartmailaddress);
                texttileview.treatAsButton(true).setOnClickListener(this);
            }
            texttileview.setTextAdaptively(s, s1);
            obj2 = SmartMailUtils.showTileIfContainsContent(texttileview);
            if (obj2 != null)
            {
                addView(((View) (obj2)));
            }
            obj2 = createCheckInOutTile(((LodgingReservation) (obj1)).checkinDate, 0x7f130105);
            if (obj2 != null)
            {
                addView(((View) (obj2)));
            }
            obj1 = createCheckInOutTile(((LodgingReservation) (obj1)).checkoutDate, 0x7f130106);
            if (obj1 != null)
            {
                addView(((View) (obj1)));
            }
            obj1 = SmartMailUtils.createPhoneTile(getContext(), organization, this);
            if (obj1 != null)
            {
                addView(((View) (obj1)));
                return;
            }
        }
    }
}
