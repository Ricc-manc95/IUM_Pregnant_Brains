// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.attendee.Response;
import com.google.android.calendar.material.Material;
import net.fortuna.ical4j.model.Content;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.Attendee;

public class ICalUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalUtils);

    public ICalUtils()
    {
    }

    public static Response getAttendeeResponse(Attendee attendee)
    {
        com.google.android.calendar.api.event.attendee.Response.Builder builder;
        byte byte1 = 3;
        builder = new com.google.android.calendar.api.event.attendee.Response.Builder();
        attendee = ((Property) (attendee)).parameters.getParameter("PARTSTAT");
        byte byte0;
        if (attendee == null)
        {
            byte0 = byte1;
        } else
        {
            attendee = attendee.getValue();
            byte0 = byte1;
            if (!"NEEDS-ACTION".equals(attendee))
            {
                if ("ACCEPTED".equals(attendee))
                {
                    byte0 = 1;
                } else
                if ("DECLINED".equals(attendee))
                {
                    byte0 = 2;
                } else
                if ("TENTATIVE".equals(attendee))
                {
                    byte0 = 4;
                } else
                if ("UNINVITED".equals(attendee))
                {
                    byte0 = 0;
                } else
                if ("X-UNINVITED".equals(attendee))
                {
                    byte0 = 0;
                } else
                {
                    LogUtils.w(TAG, "Bad event response status: %s, defaulting to INVITED", new Object[] {
                        attendee
                    });
                    byte0 = byte1;
                }
            }
        }
        byte0;
        JVM INSTR tableswitch 0 4: default 60
    //                   0 227
    //                   1 197
    //                   2 207
    //                   3 227
    //                   4 217;
           goto _L1 _L2 _L3 _L4 _L2 _L5
_L1:
        LogUtils.w(TAG, "Unknown attendee status: %d", new Object[] {
            Integer.valueOf(byte0)
        });
_L7:
        return new Response(builder);
_L3:
        builder.status = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.ACCEPTED;
        continue; /* Loop/switch isn't completed */
_L4:
        builder.status = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED;
        continue; /* Loop/switch isn't completed */
_L5:
        builder.status = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.TENTATIVE;
        continue; /* Loop/switch isn't completed */
_L2:
        builder.status = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static boolean isUpdate(int i)
    {
        switch (i)
        {
        case 5: // '\005'
        case 6: // '\006'
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 7: // '\007'
            return true;
        }
    }

    public static void showSnackbar(Activity activity, int i, int j)
    {
        String s = activity.getResources().getString(i);
        View view = activity.getLayoutInflater().inflate(0x7f05017e, (ViewGroup)activity.findViewById(0x7f10038b));
        if (view != null)
        {
            TextView textview = (TextView)view.findViewById(0x7f100042);
            if (textview != null)
            {
                Object obj;
                int k;
                if (Material.robotoMedium != null)
                {
                    obj = Material.robotoMedium;
                } else
                {
                    obj = Typeface.create("sans-serif-medium", 0);
                    Material.robotoMedium = ((Typeface) (obj));
                }
                if (obj != null)
                {
                    textview.setTypeface(((Typeface) (obj)));
                }
                textview.setText(s);
                obj = new Toast(activity.getApplicationContext());
                k = activity.getResources().getDimensionPixelSize(0x7f0e036a);
                if (activity.getResources().getBoolean(0x7f0c0016))
                {
                    i = 0x800003;
                } else
                {
                    i = 7;
                }
                ((Toast) (obj)).setGravity(i | 0x50, k, k);
                ((Toast) (obj)).setDuration(j);
                ((Toast) (obj)).setView(view);
                ((Toast) (obj)).show();
            }
        }
    }

}
