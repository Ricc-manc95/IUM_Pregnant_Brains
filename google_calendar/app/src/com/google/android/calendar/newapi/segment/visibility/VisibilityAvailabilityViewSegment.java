// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.visibility;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import com.google.android.calendar.newapi.model.AllDayHolder;
import com.google.android.calendar.newapi.model.AvailabilityHolder;
import com.google.android.calendar.newapi.model.GooglePlusHolder;
import com.google.android.calendar.newapi.model.HolidayHolder;
import com.google.android.calendar.newapi.model.VisibilityHolder;
import com.google.android.calendar.newapi.segment.common.ViewSegment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.tiles.view.TileView;

public class VisibilityAvailabilityViewSegment extends TextTileView
    implements ViewSegment
{

    private static final String TAG = com/google/android/calendar/newapi/segment/visibility/VisibilityAvailabilityViewSegment.getSimpleName();
    private final AllDayHolder model;

    public VisibilityAvailabilityViewSegment(Context context, AllDayHolder alldayholder)
    {
        super(context);
        model = alldayholder;
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        setIconDrawable(0x7f020210);
        super.denseMode = false;
        setFocusable(true);
    }

    public final void updateUi()
    {
        int i;
        int k;
        k = 0x7f1304b8;
        if (((HolidayHolder)model).isHolidayEvent() || ((GooglePlusHolder)model).isGooglePlusEvent())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L1
_L1:
        i = -1;
_L4:
        int j;
        if (i != -1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (this != null)
        {
            String s;
            String s1;
            if (j != 0)
            {
                k = 0;
            } else
            {
                k = 8;
            }
            setVisibility(k);
        }
        if (j != 0)
        {
            setPrimaryText(new CharSequence[] {
                getResources().getString(i, new Object[0])
            });
        }
        useTextAsContentDescription(0x7f130181);
        return;
_L2:
        i = k;
        if (((VisibilityHolder)model).getVisibility() == 3) goto _L4; else goto _L3
_L3:
        j = ((AvailabilityHolder)model).getAvailability();
        if (model.isAllDay())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (j != i) goto _L6; else goto _L5
_L5:
        j = 0;
_L14:
        i = k;
        ((VisibilityHolder)model).getVisibility();
        JVM INSTR tableswitch 0 3: default 212
    //                   0 355
    //                   1 412
    //                   2 384
    //                   3 42;
           goto _L7 _L8 _L9 _L10 _L11
_L7:
        continue; /* Loop/switch isn't completed */
_L11:
        continue; /* Loop/switch isn't completed */
_L22:
        i = ((VisibilityHolder)model).getVisibility();
        j = ((AvailabilityHolder)model).getAvailability();
        s = (new StringBuilder(24)).append(i).append(", ").append(j).toString();
        s1 = TAG;
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "Unknown visibility or availability: ".concat(s);
        } else
        {
            s = new String("Unknown visibility or availability: ");
        }
        Log.wtf(s1, s);
        i = -1;
        continue; /* Loop/switch isn't completed */
_L6:
        ((AvailabilityHolder)model).getAvailability();
        JVM INSTR tableswitch 0 1: default 340
    //                   0 343
    //                   1 349;
           goto _L5 _L12 _L13
_L12:
        j = 1;
          goto _L14
_L13:
        j = 2;
          goto _L14
_L8:
        j;
        JVM INSTR tableswitch 0 2: default 384
    //                   0 449
    //                   1 460
    //                   2 454;
           goto _L10 _L15 _L16 _L17
_L10:
        j;
        JVM INSTR tableswitch 0 2: default 412
    //                   0 466
    //                   1 478
    //                   2 472;
           goto _L9 _L18 _L19 _L20
_L9:
        switch (j)
        {
        default:
            break;

        case 0: // '\0'
            i = 0x7f1304b5;
            break;

        case 2: // '\002'
            i = 0x7f1304b6;
            break;

        case 1: // '\001'
            i = 0x7f1304b7;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L15:
        i = -1;
        continue; /* Loop/switch isn't completed */
_L17:
        i = 0x7f1304b0;
        continue; /* Loop/switch isn't completed */
_L16:
        i = 0x7f1304b1;
        continue; /* Loop/switch isn't completed */
_L18:
        i = 0x7f1304b2;
        continue; /* Loop/switch isn't completed */
_L20:
        i = 0x7f1304b3;
        continue; /* Loop/switch isn't completed */
_L19:
        i = 0x7f1304b4;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L22; else goto _L21
_L21:
        if (true) goto _L4; else goto _L23
_L23:
    }

}
