// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventSource;
import com.google.android.calendar.api.event.smartmail.EventReservation;
import com.google.android.calendar.api.event.smartmail.FlightReservation;
import com.google.android.calendar.api.event.smartmail.FlightSegment;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.newapi.model.CalendarHolder;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.activity.ActivityUtils;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.smartmail:
//            SourceMessageViewUtils

public final class SourceMessageViewSegment extends TextTileView
    implements android.view.View.OnClickListener, ViewSegment
{

    private Intent intent;
    private final EventHolder model;
    private final SourceMessageViewUtils.Viewer sourceEmailViewer;

    public SourceMessageViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        model = eventholder;
        sourceEmailViewer = SourceMessageViewUtils.getSourceEmailViewer(context);
        setIconDrawable(0x7f0201ea).treatAsButton(true).denseMode = false;
        setOnClickListener(this);
    }

    public final void onClick(View view)
    {
        view = AnalyticsLoggerHolder.instance;
        if (view == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        } else
        {
            ((AnalyticsLogger)view).trackEvent(getContext(), "event_action", "view_source_email");
            ActivityUtils.startActivity(getContext(), intent, "SourceMessageViewSegmen");
            return;
        }
    }

    public final void updateUi()
    {
        Object obj;
        boolean flag;
label0:
        {
            if (sourceEmailViewer == null)
            {
                break label0;
            }
            obj = model.getEvent();
            if (obj == null || ((Event) (obj)).getSource() == null)
            {
                obj = null;
            } else
            {
                obj = ((Event) (obj)).getSource().url;
                if (TextUtils.isEmpty(((CharSequence) (obj))) || !((String) (obj)).contains("mail.google.com"))
                {
                    obj = null;
                } else
                {
                    String s = Uri.parse(((String) (obj))).getQueryParameter("plid");
                    if (TextUtils.isEmpty(s))
                    {
                        break label0;
                    }
                    obj = sourceEmailViewer.getIntent(getContext(), ((String) (obj)), s, ((CalendarHolder)model).getAccount().name);
                    if (obj == null)
                    {
                        break label0;
                    }
                    ActivityUtils.prepareIntentToOpenLink(((Intent) (obj)));
                }
            }
        }
_L1:
        intent = ((Intent) (obj));
        if (intent != null)
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
        if (!flag)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_173;
        obj = null;
          goto _L1
label1:
        {
            obj = model.getEvent().getSmartMailInfo();
            if (obj == null)
            {
                break label1;
            }
            if (!((SmartMailInfo) (obj)).flightReservations.isEmpty() && !((FlightReservation)((SmartMailInfo) (obj)).flightReservations.get(0)).segments.isEmpty() && !TextUtils.isEmpty(((FlightSegment)((FlightReservation)((SmartMailInfo) (obj)).flightReservations.get(0)).segments.get(0)).bookingReference))
            {
                obj = ((FlightSegment)((FlightReservation)((SmartMailInfo) (obj)).flightReservations.get(0)).segments.get(0)).bookingReference;
            } else
            {
                if (((SmartMailInfo) (obj)).eventReservations.isEmpty() || TextUtils.isEmpty(((EventReservation)((SmartMailInfo) (obj)).eventReservations.get(0)).reservationNumber))
                {
                    break label1;
                }
                obj = ((EventReservation)((SmartMailInfo) (obj)).eventReservations.get(0)).reservationNumber;
            }
        }
_L2:
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            setPrimaryText(new CharSequence[] {
                getResources().getString(0x7f1304ab, new Object[0])
            });
            setSecondaryText(new CharSequence[] {
                null
            });
        } else
        {
            setPrimaryText(new CharSequence[] {
                getResources().getString(0x7f130135, new Object[] {
                    obj
                })
            });
            setSecondaryText(new CharSequence[] {
                getResources().getString(0x7f1304ab, new Object[0])
            });
        }
        useTextAsContentDescription(0x7f130168);
        return;
        obj = null;
          goto _L2
    }
}
