// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcelable;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            ConferenceSolution, AutoValue_ConferenceSolution_Key

public static abstract class AddOnId
    implements Parcelable
{
    public static abstract class AddOnId
        implements Parcelable
    {

        public abstract String getDeploymentId();

        public abstract String getSolutionId();

        public AddOnId()
        {
        }
    }


    public static AddOnId create(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        s = "unknownConferenceSolution";
_L15:
        return new AutoValue_ConferenceSolution_Key(s, null);
_L2:
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 5: default 72
    //                   -972730403: 153
    //                   -721107809: 125
    //                   92659296: 181
    //                   774960958: 167
    //                   1601152418: 139;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        byte0;
        JVM INSTR tableswitch 0 4: default 108
    //                   0 195
    //                   1 221
    //                   2 247
    //                   3 273
    //                   4 299;
           goto _L9 _L10 _L11 _L12 _L13 _L14
_L9:
        s = Absent.INSTANCE;
_L16:
        s = (String)s.or("unknownConferenceSolution");
          goto _L15
_L5:
        if (s.equals("unknownConferenceSolution"))
        {
            byte0 = 0;
        }
          goto _L3
_L8:
        if (s.equals("eventHangout"))
        {
            byte0 = 1;
        }
          goto _L3
_L4:
        if (s.equals("eventNamedHangout"))
        {
            byte0 = 2;
        }
          goto _L3
_L7:
        if (s.equals("hangoutsMeet"))
        {
            byte0 = 3;
        }
          goto _L3
_L6:
        if (s.equals("addOn"))
        {
            byte0 = 4;
        }
          goto _L3
_L10:
        if ("unknownConferenceSolution" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("unknownConferenceSolution");
          goto _L16
_L11:
        if ("eventHangout" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("eventHangout");
          goto _L16
_L12:
        if ("eventNamedHangout" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("eventNamedHangout");
          goto _L16
_L13:
        if ("hangoutsMeet" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("hangoutsMeet");
          goto _L16
_L14:
        if ("addOn" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("addOn");
          goto _L16
    }

    public static AddOnId create(String s, AddOnId addonid)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        s = "unknownConferenceSolution";
_L15:
        return new AutoValue_ConferenceSolution_Key(s, addonid);
_L2:
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 5: default 72
    //                   -972730403: 153
    //                   -721107809: 125
    //                   92659296: 181
    //                   774960958: 167
    //                   1601152418: 139;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        byte0;
        JVM INSTR tableswitch 0 4: default 108
    //                   0 195
    //                   1 221
    //                   2 247
    //                   3 273
    //                   4 299;
           goto _L9 _L10 _L11 _L12 _L13 _L14
_L9:
        s = Absent.INSTANCE;
_L16:
        s = (String)s.or("unknownConferenceSolution");
          goto _L15
_L5:
        if (s.equals("unknownConferenceSolution"))
        {
            byte0 = 0;
        }
          goto _L3
_L8:
        if (s.equals("eventHangout"))
        {
            byte0 = 1;
        }
          goto _L3
_L4:
        if (s.equals("eventNamedHangout"))
        {
            byte0 = 2;
        }
          goto _L3
_L7:
        if (s.equals("hangoutsMeet"))
        {
            byte0 = 3;
        }
          goto _L3
_L6:
        if (s.equals("addOn"))
        {
            byte0 = 4;
        }
          goto _L3
_L10:
        if ("unknownConferenceSolution" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("unknownConferenceSolution");
          goto _L16
_L11:
        if ("eventHangout" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("eventHangout");
          goto _L16
_L12:
        if ("eventNamedHangout" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("eventNamedHangout");
          goto _L16
_L13:
        if ("hangoutsMeet" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("hangoutsMeet");
          goto _L16
_L14:
        if ("addOn" == null)
        {
            throw new NullPointerException();
        }
        s = new Present("addOn");
          goto _L16
    }

    public abstract AddOnId getAddOnId();

    public abstract String getType();

    public AddOnId()
    {
    }
}
