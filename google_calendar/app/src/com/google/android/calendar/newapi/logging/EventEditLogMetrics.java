// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.logging;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.StructuredLocation;
import com.google.android.calendar.api.event.location.StructuredLocationModifications;
import com.google.android.calendar.api.event.notification.NotificationModifications;
import com.google.android.calendar.newapi.model.edit.BasicEditScreenModel;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.model.edit.EventEditScreenModel;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.google.android.calendar.newapi.logging:
//            MetricsUtils

public class EventEditLogMetrics
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/logging/EventEditLogMetrics);
    public boolean didChangeNotification;
    public boolean didChangeTime;
    private boolean didQuickCreateSetContacts;
    public boolean didQuickCreateSetLocation;
    public boolean didQuickCreateSetTime;
    public boolean findTimeButtonClicked;
    public boolean findTimeButtonShown;
    public long loadedTime;
    public int quickCreateStatus;

    public EventEditLogMetrics()
    {
        loadedTime = -1L;
    }

    EventEditLogMetrics(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        loadedTime = -1L;
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        didChangeTime = flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        didChangeNotification = flag;
        loadedTime = parcel.readLong();
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        findTimeButtonShown = flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        findTimeButtonClicked = flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        didQuickCreateSetTime = flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        didQuickCreateSetLocation = flag;
        if (parcel.readByte() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        didQuickCreateSetContacts = flag;
        quickCreateStatus = parcel.readByte();
    }

    public int describeContents()
    {
        return 0;
    }

    public final void logSaveCustomDimensions(Context context, EventEditScreenModel eventeditscreenmodel)
    {
        Object obj1;
        String s;
        String s1;
        boolean flag;
        boolean flag1;
        flag1 = true;
        Object obj = ((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications;
        String s2;
        String s3;
        int i;
        int j;
        boolean flag2;
        boolean flag3;
        if (eventeditscreenmodel.extras == null)
        {
            s2 = null;
        } else
        {
            s2 = eventeditscreenmodel.extras.getString("createEditSource");
        }
        s3 = MetricsUtils.getCalendarType(eventeditscreenmodel.getCalendarListEntry());
        i = ((EventModifications) (obj)).getAttachments().size();
        flag2 = findTimeButtonShown;
        flag3 = findTimeButtonClicked;
        j = ((EventModifications) (obj)).getAttendees().size();
        obj = ((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications;
        if (eventeditscreenmodel.isNew())
        {
            if (didQuickCreateSetLocation)
            {
                obj = "QC";
            } else
            {
label0:
                {
                    if (((EventModifications) (obj)).getLocation() == null || ((EventModifications) (obj)).getLocation().getEventLocations().isEmpty())
                    {
                        break label0;
                    }
                    obj = (EventLocation)((EventModifications) (obj)).getLocation().getEventLocations().iterator().next();
                    if (!TextUtils.isEmpty(((EventLocation) (obj)).placeId) || !TextUtils.isEmpty(((EventLocation) (obj)).mapsClusterId))
                    {
                        obj = "structured";
                    } else
                    {
                        if (TextUtils.isEmpty(((EventLocation) (obj)).name))
                        {
                            break label0;
                        }
                        obj = "text";
                    }
                }
            }
        } else
        if (didQuickCreateSetLocation)
        {
            obj = "changedQC";
        } else
        if (((EventModifications) (obj)).getLocationModification().isModified())
        {
            obj = "changed";
        } else
        {
            obj = "unchanged";
        }
        obj1 = ((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications;
        if (didQuickCreateSetTime || didQuickCreateSetLocation || didQuickCreateSetContacts)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (eventeditscreenmodel.isNew())
        {
            if (flag)
            {
                obj1 = "QC";
            } else
            {
                if (!TextUtils.isEmpty(((EventModifications) (obj1)).getSummary()))
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj1 = "set";
                } else
                {
                    obj1 = "none";
                }
            }
        } else
        if (((EventModifications) (obj1)).isSummaryModified())
        {
            if (flag)
            {
                obj1 = "changedQC";
            } else
            {
                obj1 = "changed";
            }
        } else
        {
            obj1 = "unchanged";
        }
        if (eventeditscreenmodel.isNew())
        {
            if (didChangeNotification)
            {
                s = "override";
            } else
            {
                s = "default";
            }
        } else
        if (((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications.getNotificationModifications().isModified())
        {
            s = "changed";
        } else
        {
            s = "unchanged";
        }
        if (eventeditscreenmodel.isNew())
        {
            if (((BasicEditScreenModel) (eventeditscreenmodel)).eventModifications.isAllDayEvent())
            {
                eventeditscreenmodel = "allDay";
            } else
            {
                eventeditscreenmodel = "time";
            }
            if (didChangeTime)
            {
                eventeditscreenmodel = String.valueOf(eventeditscreenmodel);
                s1 = String.valueOf("Picker");
                if (s1.length() != 0)
                {
                    eventeditscreenmodel = eventeditscreenmodel.concat(s1);
                } else
                {
                    eventeditscreenmodel = new String(eventeditscreenmodel);
                }
            } else
            if (didQuickCreateSetTime)
            {
                eventeditscreenmodel = String.valueOf(eventeditscreenmodel);
                s1 = String.valueOf("QC");
                if (s1.length() != 0)
                {
                    eventeditscreenmodel = eventeditscreenmodel.concat(s1);
                } else
                {
                    eventeditscreenmodel = new String(eventeditscreenmodel);
                }
            } else
            {
                eventeditscreenmodel = String.valueOf(eventeditscreenmodel);
                s1 = String.valueOf("Preset");
                if (s1.length() != 0)
                {
                    eventeditscreenmodel = eventeditscreenmodel.concat(s1);
                } else
                {
                    eventeditscreenmodel = new String(eventeditscreenmodel);
                }
            }
        } else
        if (didChangeTime)
        {
            eventeditscreenmodel = "changed";
        } else
        if (didQuickCreateSetTime)
        {
            eventeditscreenmodel = "changedQC";
        } else
        {
            eventeditscreenmodel = "unchanged";
        }
        quickCreateStatus;
        JVM INSTR tableswitch 0 2: default 232
    //                   0 685
    //                   1 232
    //                   2 678;
           goto _L1 _L2 _L1 _L3
_L2:
        break MISSING_BLOCK_LABEL_685;
_L1:
        s1 = "disabled";
_L5:
        MetricsUtils.logSaveCustomDimensions(context, s2, s3, i, flag2, flag3, j, ((String) (obj)), ((String) (obj1)), s, eventeditscreenmodel, s1);
        return;
        obj = "none";
        break MISSING_BLOCK_LABEL_83;
_L3:
        s1 = "active";
        continue; /* Loop/switch isn't completed */
        s1 = "unreachable";
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if (didChangeTime)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (didChangeNotification)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        parcel.writeLong(loadedTime);
        if (findTimeButtonShown)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (findTimeButtonClicked)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (didQuickCreateSetTime)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (didQuickCreateSetLocation)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        if (didQuickCreateSetContacts)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeByte((byte)i);
        parcel.writeByte((byte)quickCreateStatus);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new EventEditLogMetrics(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new EventEditLogMetrics[i];
        }

        _cls1()
        {
        }
    }

}
