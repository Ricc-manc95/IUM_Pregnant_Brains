// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.smartmail.flight;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.LinearLayout;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.smartmail.FlightEndpoint;
import com.google.android.calendar.api.event.smartmail.FlightReservation;
import com.google.android.calendar.api.event.smartmail.FlightSegment;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.api.event.smartmail.SmartMailTime;
import com.google.android.calendar.newapi.model.EventHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.newapi.utils.LocationUtils;
import com.google.android.calendar.newapi.utils.SmartMailUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import com.google.android.calendar.utils.activity.ActivityUtils;
import java.util.Iterator;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FlightInformationViewSegment extends LinearLayout
    implements android.view.View.OnClickListener, ViewSegment
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/smartmail/flight/FlightInformationViewSegment);
    private final SmartMailInfo model;

    public FlightInformationViewSegment(Context context, EventHolder eventholder)
    {
        super(context);
        model = eventholder.getEvent().getSmartMailInfo();
        setOrientation(1);
    }

    private static CharSequence colorize(CharSequence charsequence, int i)
    {
        SpannableString spannablestring = new SpannableString(charsequence);
        spannablestring.setSpan(new ForegroundColorSpan(0xffff0000), 0, charsequence.length(), 0);
        return spannablestring;
    }

    private final TextTileView createFlightEndpointTile(FlightEndpoint flightendpoint, String s, String s1, int i)
    {
        Object obj1;
        TextTileView texttileview = new TextTileView(getContext());
        Object obj = texttileview.indentContent().treatAsButton(true);
        obj.denseMode = false;
        ((TileView) (obj)).setTag(flightendpoint);
        texttileview.setOnClickListener(this);
        obj = flightendpoint.city;
        obj1 = flightendpoint.airportCode;
        texttileview.setPrimaryText(new CharSequence[] {
            texttileview.getResources().getString(i, new Object[] {
                obj, obj1
            })
        });
        if (TextUtils.isEmpty(s1) || s.equals(s1))
        {
            s1 = getResources().getString(0x7f13032a, new Object[] {
                s
            });
        } else
        {
            s1 = getResources().getString(0x7f13032a, new Object[] {
                s1
            });
            SpannableString spannablestring = new SpannableString(s);
            spannablestring.setSpan(new StrikethroughSpan(), 0, s.length(), 17);
            s1 = TextUtils.concat(new CharSequence[] {
                spannablestring, " ", colorize(s1, 0xffff0000)
            });
        }
        obj1 = getContext();
        if (!TextUtils.isEmpty(flightendpoint.terminal)) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        texttileview.setSecondaryText(new CharSequence[] {
            s1, s
        });
        return texttileview;
_L2:
        String s2 = ((Context) (obj1)).getString(0x7f13045c, new Object[] {
            flightendpoint.terminal
        });
        s = s2;
        if (!TextUtils.isEmpty(flightendpoint.gate))
        {
            s = ((Context) (obj1)).getString(0x7f13045d, new Object[] {
                s2, ((Context) (obj1)).getString(0x7f13045a, new Object[] {
                    flightendpoint.gate
                })
            });
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void onClick(View view)
    {
        if (view.getTag() instanceof FlightEndpoint)
        {
            view = (FlightEndpoint)view.getTag();
            view = LocationUtils.createGeoLink(getResources().getString(0x7f13044f, new Object[] {
                ((FlightEndpoint) (view)).airportCode
            }));
            ActivityUtils.startActivityForUrl(getContext(), view, TAG);
        }
    }

    public final void updateUi()
    {
        boolean flag;
        if (model != null && !model.flightReservations.isEmpty())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (this != null)
        {
            int j1;
            if (flag)
            {
                j1 = 0;
            } else
            {
                j1 = 8;
            }
            setVisibility(j1);
        }
        if (flag) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Iterator iterator;
        removeAllViews();
        iterator = model.flightReservations.iterator();
_L5:
        if (!iterator.hasNext()) goto _L1; else goto _L3
_L3:
        Iterator iterator1 = ((FlightReservation)iterator.next()).segments.iterator();
_L11:
        if (!iterator1.hasNext()) goto _L5; else goto _L4
_L4:
        Object obj;
        Object obj4;
        FlightSegment flightsegment = (FlightSegment)iterator1.next();
        TextTileView texttileview = new TextTileView(getContext());
        obj = texttileview.indentContent().setIconDrawable(0x7f0201fe);
        obj.denseMode = false;
        ((TileView) (obj)).setContentDescription(getResources().getString(0x7f13016f));
        Object obj3 = flightsegment.airlineName;
        obj = String.valueOf(flightsegment.airlineCode);
        obj4 = String.valueOf(flightsegment.flightNumber);
        int i;
        int k1;
        if (((String) (obj4)).length() != 0)
        {
            obj = ((String) (obj)).concat(((String) (obj4)));
        } else
        {
            obj = new String(((String) (obj)));
        }
        texttileview.setPrimaryText(new CharSequence[] {
            texttileview.getResources().getString(0x7f13044e, new Object[] {
                obj3, obj
            })
        });
        obj3 = SmartMailUtils.formatToLocalDate(getContext(), flightsegment.departure.time);
        obj4 = getContext();
        obj = flightsegment.departure.time;
        k1 = (int)((float)Math.abs(flightsegment.arrival.time.timeMillis - ((SmartMailTime) (obj)).timeMillis) / 1000F);
        i = k1 / 3600;
        k1 = (k1 % 3600) / 60;
        if (k1 == 0)
        {
            obj = ((Context) (obj4)).getString(0x7f130454, new Object[] {
                Integer.valueOf(i)
            });
        } else
        if (i == 0)
        {
            obj = ((Context) (obj4)).getString(0x7f130456, new Object[] {
                Integer.valueOf(k1)
            });
        } else
        {
            obj = ((Context) (obj4)).getString(0x7f130455, new Object[] {
                Integer.valueOf(i), Integer.valueOf(k1)
            });
        }
        obj3 = getContext().getString(0x7f130453, new Object[] {
            obj3, obj
        });
        flightsegment.statusCode;
        JVM INSTR tableswitch 3 6: default 408
    //                   3 671
    //                   4 657
    //                   5 638
    //                   6 685;
           goto _L6 _L7 _L8 _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_685;
_L6:
        obj = null;
_L12:
        texttileview.setSecondaryText(new CharSequence[] {
            obj3, obj
        });
        addView(texttileview);
        obj3 = flightsegment.departure;
        obj = flightsegment.departure.time;
        Object obj1;
        SmartMailTime smartmailtime;
        if (obj == null)
        {
            obj = null;
        } else
        if (true)
        {
            obj = SmartMailUtils.formatToLocalTime(getContext(), ((SmartMailTime) (obj)));
        } else
        {
            obj1 = getContext();
            ((Context) (obj1)).getResources();
            if (false)
            {
                throw new NullPointerException();
            }
            if (TextUtils.isEmpty(null))
            {
                obj = SmartMailUtils.formatToLocalTime(((Context) (obj1)), ((SmartMailTime) (obj)));
            } else
            {
                obj = SmartMailUtils.formatToLocalTime(((Context) (obj1)), ((SmartMailTime) (obj)));
                obj = (new StringBuilder(String.valueOf(obj).length() + 1 + String.valueOf(null).length())).append(((String) (obj))).append(" ").append(null).toString();
            }
        }
        obj4 = flightsegment.departure.actualTime;
        if (obj4 == null)
        {
            obj1 = null;
        } else
        if (true)
        {
            obj1 = SmartMailUtils.formatToLocalTime(getContext(), ((SmartMailTime) (obj4)));
        } else
        {
            obj1 = getContext();
            ((Context) (obj1)).getResources();
            if (false)
            {
                throw new NullPointerException();
            }
            if (TextUtils.isEmpty(null))
            {
                obj1 = SmartMailUtils.formatToLocalTime(((Context) (obj1)), ((SmartMailTime) (obj4)));
            } else
            {
                obj1 = SmartMailUtils.formatToLocalTime(((Context) (obj1)), ((SmartMailTime) (obj4)));
                obj1 = (new StringBuilder(String.valueOf(obj1).length() + 1 + String.valueOf(null).length())).append(((String) (obj1))).append(" ").append(null).toString();
            }
        }
        addView(createFlightEndpointTile(((FlightEndpoint) (obj3)), ((String) (obj)), ((String) (obj1)), 0x7f130459));
        obj3 = flightsegment.arrival;
        smartmailtime = flightsegment.departure.time;
        obj4 = flightsegment.arrival.time;
        if (obj4 == null)
        {
            obj = null;
        } else
        if (smartmailtime == null)
        {
            obj = SmartMailUtils.formatToLocalTime(getContext(), ((SmartMailTime) (obj4)));
        } else
        {
            obj1 = getContext();
            Object obj5 = ((Context) (obj1)).getResources();
            if (smartmailtime == null || smartmailtime.equals(obj4))
            {
                obj = null;
            } else
            {
                obj = new SimpleTimeZone((int)TimeUnit.MILLISECONDS.convert(smartmailtime.timeZoneOffsetMinutes, TimeUnit.MINUTES), "");
                SimpleTimeZone simpletimezone = new SimpleTimeZone((int)TimeUnit.MILLISECONDS.convert(((SmartMailTime) (obj4)).timeZoneOffsetMinutes, TimeUnit.MINUTES), "");
                long l2 = smartmailtime.timeMillis;
                int j = Time.getJulianDay(l2, (long)((TimeZone) (obj)).getOffset(l2) / 1000L);
                l2 = ((SmartMailTime) (obj4)).timeMillis;
                int l1 = Time.getJulianDay(l2, (long)simpletimezone.getOffset(l2) / 1000L) - j;
                if (l1 == 0)
                {
                    obj = null;
                } else
                {
                    int k = Math.abs(l1);
                    if (l1 < 0)
                    {
                        obj = "-";
                    } else
                    {
                        obj = "+";
                    }
                    obj5 = ((Resources) (obj5)).getQuantityString(0x7f120049, k, new Object[] {
                        Integer.valueOf(k)
                    });
                    obj = String.valueOf(obj);
                    obj5 = String.valueOf(obj5);
                    if (((String) (obj5)).length() != 0)
                    {
                        obj = ((String) (obj)).concat(((String) (obj5)));
                    } else
                    {
                        obj = new String(((String) (obj)));
                    }
                }
            }
            if (TextUtils.isEmpty(((CharSequence) (obj))))
            {
                obj = SmartMailUtils.formatToLocalTime(((Context) (obj1)), ((SmartMailTime) (obj4)));
            } else
            {
                obj1 = SmartMailUtils.formatToLocalTime(((Context) (obj1)), ((SmartMailTime) (obj4)));
                obj = (new StringBuilder(String.valueOf(obj1).length() + 1 + String.valueOf(obj).length())).append(((String) (obj1))).append(" ").append(((String) (obj))).toString();
            }
        }
        obj1 = flightsegment.departure.actualTime;
        obj4 = flightsegment.arrival.actualTime;
        if (obj4 == null)
        {
            obj1 = null;
        } else
        if (obj1 == null)
        {
            obj1 = SmartMailUtils.formatToLocalTime(getContext(), ((SmartMailTime) (obj4)));
        } else
        {
            Object obj2 = getContext();
            Object obj6 = ((Context) (obj2)).getResources();
            if (obj1 == null || ((SmartMailTime) (obj1)).equals(obj4))
            {
                obj1 = null;
            } else
            {
                SimpleTimeZone simpletimezone2 = new SimpleTimeZone((int)TimeUnit.MILLISECONDS.convert(((SmartMailTime) (obj1)).timeZoneOffsetMinutes, TimeUnit.MINUTES), "");
                SimpleTimeZone simpletimezone1 = new SimpleTimeZone((int)TimeUnit.MILLISECONDS.convert(((SmartMailTime) (obj4)).timeZoneOffsetMinutes, TimeUnit.MINUTES), "");
                long l3 = ((SmartMailTime) (obj1)).timeMillis;
                int l = Time.getJulianDay(l3, (long)simpletimezone2.getOffset(l3) / 1000L);
                l3 = ((SmartMailTime) (obj4)).timeMillis;
                int i2 = Time.getJulianDay(l3, (long)simpletimezone1.getOffset(l3) / 1000L) - l;
                if (i2 == 0)
                {
                    obj1 = null;
                } else
                {
                    int i1 = Math.abs(i2);
                    if (i2 < 0)
                    {
                        obj1 = "-";
                    } else
                    {
                        obj1 = "+";
                    }
                    obj6 = ((Resources) (obj6)).getQuantityString(0x7f120049, i1, new Object[] {
                        Integer.valueOf(i1)
                    });
                    obj1 = String.valueOf(obj1);
                    obj6 = String.valueOf(obj6);
                    if (((String) (obj6)).length() != 0)
                    {
                        obj1 = ((String) (obj1)).concat(((String) (obj6)));
                    } else
                    {
                        obj1 = new String(((String) (obj1)));
                    }
                }
            }
            if (TextUtils.isEmpty(((CharSequence) (obj1))))
            {
                obj1 = SmartMailUtils.formatToLocalTime(((Context) (obj2)), ((SmartMailTime) (obj4)));
            } else
            {
                obj2 = SmartMailUtils.formatToLocalTime(((Context) (obj2)), ((SmartMailTime) (obj4)));
                obj1 = (new StringBuilder(String.valueOf(obj2).length() + 1 + String.valueOf(obj1).length())).append(((String) (obj2))).append(" ").append(((String) (obj1))).toString();
            }
        }
        addView(createFlightEndpointTile(((FlightEndpoint) (obj3)), ((String) (obj)), ((String) (obj1)), 0x7f130457));
          goto _L11
_L9:
        obj = colorize(getContext().getString(0x7f130231), 0xffff0000);
          goto _L12
_L8:
        obj = getContext().getString(0x7f130458);
          goto _L12
_L7:
        obj = getContext().getString(0x7f13045b);
          goto _L12
        obj = getContext().getString(0x7f130232);
          goto _L12
    }

}
