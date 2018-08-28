// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.calendar.newapi.model.GrooveEditScreenModel;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;

// Referenced classes of package com.google.android.calendar.newapi.logging:
//            MetricsUtils

public class GrooveEditLogMetrics
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public boolean notificationModified;
    public long screenLoadingFinishedTimestamp;
    public boolean timeModified;
    public boolean titleEmpty;
    public boolean titleModified;

    public GrooveEditLogMetrics()
    {
        screenLoadingFinishedTimestamp = -1L;
    }

    GrooveEditLogMetrics(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        screenLoadingFinishedTimestamp = -1L;
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        timeModified = flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        notificationModified = flag;
        if (parcel.readByte() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        titleModified = flag;
        screenLoadingFinishedTimestamp = parcel.readLong();
    }

    public int describeContents()
    {
        return 0;
    }

    public final void fillDimensions(Context context, GrooveEditScreenModel grooveeditscreenmodel)
    {
        boolean flag1 = grooveeditscreenmodel.isNew();
        String s2 = MetricsUtils.getCalendarType(grooveeditscreenmodel.getCalendarListEntry());
        String s;
        String s1;
        if (flag1)
        {
            String s3;
            boolean flag;
            if (!titleEmpty)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                s = "set";
            } else
            {
                s = "none";
            }
        } else
        if (timeModified)
        {
            s = "changed";
        } else
        {
            s = "unchanged";
        }
        if (flag1)
        {
            if (notificationModified)
            {
                s1 = "override";
            } else
            {
                s1 = "default";
            }
        } else
        if (notificationModified)
        {
            s1 = "changed";
        } else
        {
            s1 = "unchanged";
        }
        if (flag1)
        {
            if (timeModified)
            {
                grooveeditscreenmodel = String.valueOf("time");
                s3 = String.valueOf("Picker");
                if (s3.length() != 0)
                {
                    grooveeditscreenmodel = grooveeditscreenmodel.concat(s3);
                } else
                {
                    grooveeditscreenmodel = new String(grooveeditscreenmodel);
                }
            } else
            {
                grooveeditscreenmodel = String.valueOf("time");
                String s4 = String.valueOf("Preset");
                if (s4.length() != 0)
                {
                    grooveeditscreenmodel = grooveeditscreenmodel.concat(s4);
                } else
                {
                    grooveeditscreenmodel = new String(grooveeditscreenmodel);
                }
            }
        } else
        if (timeModified)
        {
            grooveeditscreenmodel = "changed";
        } else
        {
            grooveeditscreenmodel = "unchanged";
        }
        MetricsUtils.logSaveCustomDimensions(context, null, s2, 0, false, false, 1, "unchanged", s, s1, grooveeditscreenmodel, "unreachable");
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if (timeModified)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (notificationModified)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (titleModified)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        parcel.writeLong(screenLoadingFinishedTimestamp);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new GrooveEditLogMetrics(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new GrooveEditLogMetrics[i];
        }

        _cls1()
        {
        }
    }

}
