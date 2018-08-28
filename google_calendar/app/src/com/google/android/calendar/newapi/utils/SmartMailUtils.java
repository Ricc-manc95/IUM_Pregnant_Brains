// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.widget.TextView;
import com.google.android.calendar.Utils;
import com.google.android.calendar.api.event.smartmail.EventReservation;
import com.google.android.calendar.api.event.smartmail.FlightReservation;
import com.google.android.calendar.api.event.smartmail.FlightSegment;
import com.google.android.calendar.api.event.smartmail.LodgingReservation;
import com.google.android.calendar.api.event.smartmail.Organization;
import com.google.android.calendar.api.event.smartmail.Restaurant;
import com.google.android.calendar.api.event.smartmail.RestaurantReservation;
import com.google.android.calendar.api.event.smartmail.SmartMailAddress;
import com.google.android.calendar.api.event.smartmail.SmartMailEvent;
import com.google.android.calendar.api.event.smartmail.SmartMailImage;
import com.google.android.calendar.api.event.smartmail.SmartMailInfo;
import com.google.android.calendar.api.event.smartmail.SmartMailTime;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class SmartMailUtils
{

    public static String capitalize(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return s;
        } else
        {
            char c = Character.toUpperCase(s.charAt(0));
            s = s.substring(1);
            return (new StringBuilder(String.valueOf(s).length() + 1)).append(c).append(s).toString();
        }
    }

    public static String concatenate(String s, String s1, String s2)
    {
        if (TextUtils.isEmpty(s1))
        {
            s = s2;
            if (TextUtils.isEmpty(s2))
            {
                s = null;
            }
            return s;
        }
        if (!TextUtils.isEmpty(s2))
        {
            s1 = String.valueOf(s1);
            return (new StringBuilder(String.valueOf(s1).length() + String.valueOf(s).length() + String.valueOf(s2).length())).append(s1).append(s).append(s2).toString();
        } else
        {
            return s1;
        }
    }

    public static TextTileView createLocationTile(Context context, SmartMailAddress smartmailaddress, android.view.View.OnClickListener onclicklistener)
    {
        if (smartmailaddress == null)
        {
            return null;
        } else
        {
            TextTileView texttileview = new TextTileView(context);
            TileView tileview = texttileview.setIconDrawable(0x7f02022c);
            tileview.denseMode = false;
            tileview.treatAsButton(true).setContentDescription(context.getString(0x7f130178));
            texttileview.setOnClickListener(onclicklistener);
            texttileview.setTag(smartmailaddress);
            texttileview.setTextAdaptively(smartmailaddress.name, formatAddress(smartmailaddress));
            return showTileIfContainsContent(texttileview);
        }
    }

    public static TextTileView createPhoneTile(Context context, Organization organization, android.view.View.OnClickListener onclicklistener)
    {
        if (organization.phoneNumbers.isEmpty())
        {
            return null;
        } else
        {
            organization = (String)organization.phoneNumbers.get(0);
            TextTileView texttileview = new TextTileView(context);
            TileView tileview = texttileview.setIconDrawable(0x7f02021f);
            tileview.denseMode = false;
            tileview.treatAsButton(true).setContentDescription(context.getString(0x7f13017b));
            texttileview.setOnClickListener(onclicklistener);
            texttileview.setTag(organization);
            texttileview.setPrimaryText(new CharSequence[] {
                organization
            });
            return showTileIfContainsContent(texttileview);
        }
    }

    public static String formatAddress(SmartMailAddress smartmailaddress)
    {
        Object obj;
        int i;
        obj = null;
        i = 1;
        if (smartmailaddress != null) goto _L2; else goto _L1
_L1:
        return ((String) (obj));
_L2:
        String as[];
        as = new String[4];
        as[0] = smartmailaddress.streetAddress;
        as[1] = smartmailaddress.postalCode;
        as[2] = smartmailaddress.locality;
        as[3] = smartmailaddress.region;
        if (as.length > 1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (as.length != 0)
        {
            return as[0];
        }
        if (true) goto _L1; else goto _L3
_L3:
        smartmailaddress = as[0];
        do
        {
            obj = smartmailaddress;
            if (i >= as.length)
            {
                continue;
            }
            smartmailaddress = concatenate(", ", smartmailaddress, as[i]);
            i++;
        } while (true);
        if (true) goto _L1; else goto _L4
_L4:
    }

    public static String formatToLocalDate(Context context, SmartMailTime smartmailtime)
    {
        SimpleTimeZone simpletimezone;
        long l;
        simpletimezone = new SimpleTimeZone((int)TimeUnit.MILLISECONDS.convert(smartmailtime.timeZoneOffsetMinutes, TimeUnit.MINUTES), "");
        l = smartmailtime.timeMillis;
        Time.getJulianDay(l, TimeUnit.SECONDS.convert(simpletimezone.getOffset(l), TimeUnit.MILLISECONDS)) - Utils.getTodayJulianDay(context);
        JVM INSTR tableswitch 0 1: default 80
    //                   0 154
    //                   1 160;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_160;
_L1:
        int i = 0;
_L4:
        switch (i)
        {
        default:
            smartmailtime = new StringBuilder(50);
            Formatter formatter = new Formatter(smartmailtime, Locale.getDefault());
            smartmailtime.setLength(0);
            return DateUtils.formatDateRange(context, formatter, l, l, 18, simpletimezone.getDisplayName()).toString();

        case 1: // '\001'
            return context.getString(0x7f130488);

        case 2: // '\002'
            return context.getString(0x7f130490);
        }
_L2:
        i = 1;
          goto _L4
        i = 2;
          goto _L4
    }

    public static String formatToLocalDateTime(Context context, SmartMailTime smartmailtime)
    {
        String s;
        if (smartmailtime == null)
        {
            s = null;
        } else
        {
            String s1 = formatToLocalDate(context, smartmailtime);
            s = s1;
            if (!smartmailtime.dateOnly)
            {
                smartmailtime = formatToLocalTime(context, smartmailtime);
                return concatenate(", ", s1, context.getResources().getString(0x7f13032a, new Object[] {
                    smartmailtime
                }));
            }
        }
        return s;
    }

    public static String formatToLocalTime(Context context, SmartMailTime smartmailtime)
    {
        SimpleTimeZone simpletimezone = new SimpleTimeZone((int)TimeUnit.MILLISECONDS.convert(smartmailtime.timeZoneOffsetMinutes, TimeUnit.MINUTES), "");
        StringBuilder stringbuilder = new StringBuilder(50);
        Formatter formatter = new Formatter(stringbuilder, Locale.getDefault());
        stringbuilder.setLength(0);
        return DateUtils.formatDateRange(context, formatter, smartmailtime.timeMillis, smartmailtime.timeMillis, 16385, simpletimezone.getDisplayName()).toString();
    }

    public static SmartMailEvent getEvent(SmartMailInfo smartmailinfo)
    {
        if (smartmailinfo != null)
        {
            if (!smartmailinfo.events.isEmpty())
            {
                return (SmartMailEvent)smartmailinfo.events.get(0);
            }
            if (!smartmailinfo.eventReservations.isEmpty())
            {
                return ((EventReservation)smartmailinfo.eventReservations.get(0)).event;
            }
        }
        return null;
    }

    public static SmartMailImage getImage(SmartMailInfo smartmailinfo)
    {
        Object obj = getEvent(smartmailinfo);
        if (obj != null)
        {
            return ((SmartMailEvent) (obj)).image;
        }
        if (smartmailinfo == null || smartmailinfo.restaurantReservations.isEmpty())
        {
            obj = null;
        } else
        {
            obj = (RestaurantReservation)smartmailinfo.restaurantReservations.get(0);
        }
        if (obj != null && ((RestaurantReservation) (obj)).foodEstablishment != null)
        {
            return ((RestaurantReservation) (obj)).foodEstablishment.image;
        }
        if (smartmailinfo == null || smartmailinfo.lodgingReservations.isEmpty())
        {
            obj = null;
        } else
        {
            obj = (LodgingReservation)smartmailinfo.lodgingReservations.get(0);
        }
        if (obj != null)
        {
            return ((LodgingReservation) (obj)).image;
        }
        if (smartmailinfo == null || smartmailinfo.flightReservations.isEmpty())
        {
            smartmailinfo = null;
        } else
        {
            smartmailinfo = (FlightReservation)smartmailinfo.flightReservations.get(0);
        }
        if (smartmailinfo != null && !((FlightReservation) (smartmailinfo)).segments.isEmpty())
        {
            return ((FlightSegment)((FlightReservation) (smartmailinfo)).segments.get(0)).image;
        } else
        {
            return null;
        }
    }

    public static boolean isSupportedSmartMailType(SmartMailInfo smartmailinfo)
    {
label0:
        {
            Object obj;
            if (smartmailinfo == null || smartmailinfo.flightReservations.isEmpty())
            {
                obj = null;
            } else
            {
                obj = (FlightReservation)smartmailinfo.flightReservations.get(0);
            }
            if (obj == null)
            {
                if (smartmailinfo == null || smartmailinfo.lodgingReservations.isEmpty())
                {
                    obj = null;
                } else
                {
                    obj = (LodgingReservation)smartmailinfo.lodgingReservations.get(0);
                }
                if (obj == null)
                {
                    if (smartmailinfo == null || smartmailinfo.restaurantReservations.isEmpty())
                    {
                        obj = null;
                    } else
                    {
                        obj = (RestaurantReservation)smartmailinfo.restaurantReservations.get(0);
                    }
                    if (obj == null && getEvent(smartmailinfo) == null)
                    {
                        break label0;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static TextTileView showTileIfContainsContent(TextTileView texttileview)
    {
        boolean flag;
        if (TextUtils.isEmpty(texttileview.primaryLine.getText()))
        {
            CharSequence charsequence;
            if (texttileview.secondaryLine != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                charsequence = texttileview.getSecondaryTextView().getText();
            } else
            {
                charsequence = null;
            }
            if (TextUtils.isEmpty(charsequence))
            {
                break MISSING_BLOCK_LABEL_59;
            }
        }
        flag = true;
_L1:
        if (flag)
        {
            return texttileview;
        } else
        {
            return null;
        }
        flag = false;
          goto _L1
    }
}
