// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.location.Address;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.event.ConferenceCallUtils;
import com.google.android.calendar.event.ConferenceCallView;
import com.google.android.calendar.newapi.model.ConferenceEvent;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.model.LocationHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.segment.note.ConferenceTileView;
import com.google.android.calendar.newapi.segment.room.RoomUtils;
import com.google.android.calendar.newapi.utils.LocationUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.activity.ActivityUtils;

public class LocationViewSegment extends ConferenceTileView
    implements android.view.View.OnClickListener, com.google.android.calendar.event.ConferenceCallSpan.OnConferenceNumberClickListener, ViewSegment
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/location/LocationViewSegment);
    private final FragmentManager fragmentManager;
    private final EventHolder model;

    public LocationViewSegment(Context context, FragmentManager fragmentmanager, EventHolder eventholder)
    {
        super(context);
        fragmentManager = fragmentmanager;
        model = eventholder;
    }

    protected final Spannable createOneClickDialingText(CharSequence charsequence)
    {
        boolean flag;
        if (((LocationHolder)model).getLocation().address != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return SpannableString.valueOf(charsequence);
        } else
        {
            return super.createOneClickDialingText(charsequence);
        }
    }

    protected final String getAnalyticsSegmentDescription()
    {
        return "in_segment_location";
    }

    public void onClick(View view)
    {
        view = AnalyticsLoggerHolder.instance;
        if (view == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        ((AnalyticsLogger)view).trackEvent(getContext(), "event_action", "open_location");
        view = ((LocationHolder)model).getLocation();
        if (TextUtils.isEmpty(((EventLocation) (view)).url))
        {
            if (view == null)
            {
                view = null;
            } else
            {
                view = RoomUtils.removeRoomsFromLocation(((EventLocation) (view)).name, RoomUtils.getRooms(model.getEvent()));
            }
            view = LocationUtils.createGeoLink(view);
        } else
        {
            view = ((EventLocation) (view)).url;
        }
        ActivityUtils.startActivityForUrl(getContext(), view, TAG);
    }

    public final void onClick(String s)
    {
        ConferenceCallUtils.logAction(getContext(), "conference_link_tapped", "in_segment_location");
        ConferenceCallUtils.dialConferenceCall(getContext(), fragmentManager, s, ((ConferenceEvent)model).getConferenceAccessTokens());
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        indentContent();
        super.denseMode = false;
        setIconDrawable(0x7f02022c);
        treatAsButton(true);
        setContentDescription(getResources().getString(0x7f130178));
        ((ConferenceCallView)super.primaryLine).listener = this;
        class .Lambda._cls0
            implements android.view.View.OnLongClickListener
        {

            private final LocationViewSegment arg$1;

            public final boolean onLongClick(View view1)
            {
                Object obj;
                Context context;
                LocationViewSegment locationviewsegment;
                obj = null;
                locationviewsegment = arg$1;
                context = locationviewsegment.getContext();
                if (TextUtils.isEmpty(((TextTileView) (locationviewsegment)).primaryLine.getText())) goto _L2; else goto _L1
_L1:
                boolean flag;
                if (((TextTileView) (locationviewsegment)).secondaryLine != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    view1 = locationviewsegment.getSecondaryTextView().getText();
                } else
                {
                    view1 = null;
                }
                if (!TextUtils.isEmpty(view1)) goto _L3; else goto _L2
_L2:
                view1 = ((TextTileView) (locationviewsegment)).primaryLine.getText();
_L5:
                ((ClipboardManager)context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Text", view1));
                Toast.makeText(context, 0x7f13009a, 0).show();
                return true;
_L3:
                String s = String.valueOf(((TextTileView) (locationviewsegment)).primaryLine.getText());
                boolean flag1;
                if (((TextTileView) (locationviewsegment)).secondaryLine != null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                view1 = obj;
                if (flag1)
                {
                    view1 = locationviewsegment.getSecondaryTextView().getText();
                }
                view1 = String.valueOf(view1);
                view1 = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(view1).length())).append(s).append(" ").append(view1).toString();
                if (true) goto _L5; else goto _L4
_L4:
            }

            .Lambda._cls0()
            {
                arg$1 = LocationViewSegment.this;
            }
        }

        super.contentView.setOnLongClickListener(new .Lambda._cls0());
        setOnClickListener(this);
    }

    public final void updateUi()
    {
        Object obj1 = null;
        EventLocation eventlocation = ((LocationHolder)model).getLocation();
        Object obj;
        if (eventlocation == null)
        {
            obj = null;
        } else
        {
            obj = RoomUtils.removeRoomsFromLocation(eventlocation.name, RoomUtils.getRooms(model.getEvent()));
        }
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        obj = (ConferenceTileView)setPrimaryText(new CharSequence[] {
            obj
        });
        boolean flag;
        if (((LocationHolder)model).getLocation().address != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = obj1;
        if (flag)
        {
            obj = eventlocation.address.formattedAddress;
        }
        setSecondaryText(new CharSequence[] {
            obj
        });
    }

}
