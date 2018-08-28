// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail.restaurant;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.calendar.EventFragmentHostActivity;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.smartmail.Organization;
import com.google.android.calendar.api.event.smartmail.Restaurant;
import com.google.android.calendar.api.event.smartmail.RestaurantReservation;
import com.google.android.calendar.api.event.smartmail.SmartMailActionTarget;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.activity.ActivityUtils;
import java.util.List;
import java.util.Locale;

public final class RestaurantInformationViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private final SmartMailInfo model;

    public RestaurantInformationViewSegment(Activity activity, EventHolder eventholder)
    {
        super(activity);
        model = eventholder.getEvent().getSmartMailInfo();
        setOrientation(1);
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
        Object obj2 = null;
        removeAllViews();
        Object obj = model;
        boolean flag;
        if (obj == null || ((SmartMailInfo) (obj)).restaurantReservations.isEmpty())
        {
            obj = null;
        } else
        {
            obj = (RestaurantReservation)((SmartMailInfo) (obj)).restaurantReservations.get(0);
        }
        if (obj != null && ((RestaurantReservation) (obj)).foodEstablishment != null && ((RestaurantReservation) (obj)).foodEstablishment.organization != null)
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
            Organization organization;
            TextTileView texttileview1;
            TileView tileview1;
            if (obj1 == null || ((SmartMailInfo) (obj1)).restaurantReservations.isEmpty())
            {
                obj1 = null;
            } else
            {
                obj1 = (RestaurantReservation)((SmartMailInfo) (obj1)).restaurantReservations.get(0);
            }
            organization = ((RestaurantReservation) (obj1)).foodEstablishment.organization;
            texttileview1 = new TextTileView(getContext());
            tileview1 = texttileview1.setIconDrawable(0x7f02022b);
            tileview1.denseMode = false;
            tileview1.setContentDescription(getResources().getString(0x7f13017d));
            texttileview1.setTextAdaptively(((RestaurantReservation) (obj1)).foodEstablishment.organization.name, SmartMailUtils.formatToLocalDateTime(getContext(), ((RestaurantReservation) (obj1)).startTime));
            texttileview1 = SmartMailUtils.showTileIfContainsContent(texttileview1);
            if (texttileview1 != null)
            {
                addView(texttileview1);
            }
            if (((RestaurantReservation) (obj1)).partySize == null)
            {
                obj1 = obj2;
            } else
            {
                TextTileView texttileview = new TextTileView(getContext());
                TileView tileview = texttileview.indentContent();
                tileview.denseMode = false;
                tileview.setImportantForAccessibility(1);
                obj1 = ((RestaurantReservation) (obj1)).partySize;
                texttileview.setPrimaryText(new CharSequence[] {
                    texttileview.getResources().getString(0x7f1303f7, new Object[] {
                        obj1
                    })
                });
                obj1 = texttileview;
            }
            if (obj1 != null)
            {
                addView(((View) (obj1)));
            }
            obj1 = SmartMailUtils.createLocationTile(getContext(), organization.address, this);
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
