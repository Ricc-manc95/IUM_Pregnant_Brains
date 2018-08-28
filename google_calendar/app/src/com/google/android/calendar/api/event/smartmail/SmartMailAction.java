// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.smartmail;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.event.smartmail:
//            SmartMailTime, SmartMailActionTarget

public final class SmartMailAction
    implements Parcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    private final SmartMailTime expirationTime;
    public final SmartMailActionTarget goToAction;

    SmartMailAction(Parcel parcel)
    {
        this((SmartMailTime)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailTime.getClassLoader()), (SmartMailActionTarget)parcel.readParcelable(com/google/android/calendar/api/event/smartmail/SmartMailActionTarget.getClassLoader()));
    }

    public SmartMailAction(SmartMailTime smartmailtime, SmartMailActionTarget smartmailactiontarget)
    {
        expirationTime = smartmailtime;
        goToAction = smartmailactiontarget;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (SmartMailAction)obj;
            if (expirationTime == null ? ((SmartMailAction) (obj)).expirationTime != null : !expirationTime.equals(((SmartMailAction) (obj)).expirationTime))
            {
                return false;
            }
            if (goToAction != null)
            {
                return goToAction.equals(((SmartMailAction) (obj)).goToAction);
            }
            if (((SmartMailAction) (obj)).goToAction != null)
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int j = 0;
        int i;
        if (expirationTime != null)
        {
            i = expirationTime.hashCode();
        } else
        {
            i = 0;
        }
        if (goToAction != null)
        {
            j = goToAction.hashCode();
        }
        return i * 31 + j;
    }

    public final String toString()
    {
        return String.format("SmartMailAction{expirationTime=%s, goToAction=%s}", new Object[] {
            expirationTime, goToAction
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(expirationTime, i);
        parcel.writeParcelable(goToAction, i);
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new SmartMailAction(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new SmartMailAction[i];
        }

        _cls1()
        {
        }
    }

}
