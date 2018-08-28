// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail.event;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.smartmail.EventReservation;
import com.google.android.calendar.api.event.smartmail.SeatingInformation;
import com.google.android.calendar.api.event.smartmail.SmartMailActionTarget;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;
import com.google.android.calendar.api.event.smartmail.SmartMailEvent;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.activity.ActivityUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class EventInformationViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private final SmartMailInfo model;

    public EventInformationViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        model = eventholder.getEvent().getSmartMailInfo();
        setOrientation(1);
    }

    public final void onClick(View view)
    {
        if (view.getTag() instanceof SmartMailAddress)
        {
            Context context = getContext();
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
            if (!TextUtils.isEmpty(view))
            {
                ActivityUtils.startActivityForUrl(context, view, "EventInformation");
            }
        }
    }

    public final void updateUi()
    {
        removeAllViews();
        SmartMailEvent smartmailevent = SmartMailUtils.getEvent(model);
        boolean flag;
        if (smartmailevent != null && !TextUtils.isEmpty(smartmailevent.name))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (this != null)
        {
            int j;
            if (flag)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            setVisibility(j);
        }
        if (flag)
        {
            SmartMailEvent smartmailevent1 = SmartMailUtils.getEvent(model);
            Object obj = new TextTileView(getContext());
            Object obj1 = ((TileView) (obj)).setIconDrawable(0x7f020128);
            obj1.denseMode = false;
            ((TileView) (obj1)).setContentDescription(getResources().getString(0x7f13016d));
            obj1 = smartmailevent1.name;
            String s = SmartMailUtils.formatToLocalDateTime(getContext(), smartmailevent1.startTime);
            ((TextTileView) (obj)).setPrimaryText(new CharSequence[] {
                obj1
            });
            ((TextTileView) (obj)).setSecondaryText(new CharSequence[] {
                s
            });
            obj = SmartMailUtils.showTileIfContainsContent(((TextTileView) (obj)));
            if (obj != null)
            {
                addView(((View) (obj)));
            }
            for (Iterator iterator = model.eventReservations.iterator(); iterator.hasNext();)
            {
                EventReservation eventreservation = (EventReservation)iterator.next();
                Iterator iterator1 = eventreservation.seatingInformationList.iterator();
                while (iterator1.hasNext()) 
                {
                    Object obj3 = (SeatingInformation)iterator1.next();
                    obj = eventreservation.seatCount;
                    TextTileView texttileview = new TextTileView(getContext());
                    texttileview.indentContent().denseMode = false;
                    if (obj != null)
                    {
                        ((Integer) (obj)).intValue();
                    }
                    Object obj2;
                    Object obj4;
                    int i;
                    if (obj == null)
                    {
                        i = 2;
                    } else
                    {
                        i = ((Integer) (obj)).intValue();
                    }
                    if (((SeatingInformation) (obj3)).seat == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = getResources().getQuantityString(0x7f120048, i, new Object[] {
                            ((SeatingInformation) (obj3)).seat
                        });
                    }
                    obj4 = getContext();
                    if (((SeatingInformation) (obj3)).section == null)
                    {
                        obj2 = null;
                    } else
                    {
                        obj2 = ((Context) (obj4)).getResources().getString(0x7f13045f, new Object[] {
                            ((SeatingInformation) (obj3)).section
                        });
                    }
                    if (((SeatingInformation) (obj3)).row == null)
                    {
                        obj3 = null;
                    } else
                    {
                        obj3 = ((Context) (obj4)).getResources().getString(0x7f13045e, new Object[] {
                            ((SeatingInformation) (obj3)).row
                        });
                    }
                    obj3 = SmartMailUtils.concatenate(", ", ((String) (obj2)), ((String) (obj3)));
                    if (TextUtils.isEmpty(null))
                    {
                        if (TextUtils.isEmpty(((CharSequence) (obj))))
                        {
                            obj2 = obj3;
                        } else
                        {
                            obj2 = obj;
                        }
                        obj4 = obj2;
                        if (TextUtils.isEmpty(((CharSequence) (obj))))
                        {
                            obj3 = null;
                            obj4 = obj2;
                        }
                    } else
                    {
                        obj3 = SmartMailUtils.concatenate(", ", ((String) (obj3)), ((String) (obj)));
                        obj4 = null;
                    }
                    if (TextUtils.isEmpty(((CharSequence) (obj4))) && TextUtils.isEmpty(((CharSequence) (obj3))))
                    {
                        obj = null;
                    } else
                    {
                        texttileview.setPrimaryText(new CharSequence[] {
                            SmartMailUtils.capitalize(((String) (obj4)))
                        });
                        texttileview.setSecondaryText(new CharSequence[] {
                            SmartMailUtils.capitalize(((String) (obj3)))
                        });
                        texttileview.setImportantForAccessibility(1);
                        obj = SmartMailUtils.showTileIfContainsContent(texttileview);
                    }
                    if (obj != null)
                    {
                        addView(((View) (obj)));
                    }
                }
            }

            obj = SmartMailUtils.createLocationTile(getContext(), smartmailevent1.address, this);
            if (obj != null)
            {
                addView(((View) (obj)));
                return;
            }
        }
    }
}
