// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineEvent, FindTimeAttendee, AttendeeExplanation, TimelineItem

public final class TimelineSuggestion extends TimelineEvent
    implements Parcelable
{

    public static final Comparator ATTENDEE_COMPARATOR = new _cls1();
    public static final android.os.Parcelable.Creator CREATOR = new _cls2();
    public ImmutableList attendeeExplanations;
    public ImmutableList attendees;
    private String description;
    public String explanationMessage;
    public boolean isCustom;
    public String suggestionId;
    public double totalPenalty;

    public TimelineSuggestion()
    {
    }

    public TimelineSuggestion(Parcel parcel)
    {
        super(parcel);
        attendees = ImmutableList.copyOf(parcel.createTypedArrayList(FindTimeAttendee.CREATOR));
        description = parcel.readString();
        attendeeExplanations = ImmutableList.copyOf(parcel.createTypedArrayList(AttendeeExplanation.CREATOR));
        suggestionId = parcel.readString();
        explanationMessage = parcel.readString();
    }

    public TimelineSuggestion(TimelineSuggestion timelinesuggestion)
    {
        super(timelinesuggestion);
        description = timelinesuggestion.description;
        totalPenalty = timelinesuggestion.totalPenalty;
        attendees = timelinesuggestion.attendees;
        attendeeExplanations = timelinesuggestion.attendeeExplanations;
        isCustom = timelinesuggestion.isCustom;
        explanationMessage = timelinesuggestion.explanationMessage;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof TimelineSuggestion))
        {
            return false;
        }
        obj = (TimelineSuggestion)obj;
        if (totalPenalty != ((TimelineSuggestion) (obj)).totalPenalty)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = description;
        Object obj2 = ((TimelineSuggestion) (obj)).description;
        boolean flag;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = attendees;
        obj2 = ((TimelineSuggestion) (obj)).attendees;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = attendeeExplanations;
        obj2 = ((TimelineSuggestion) (obj)).attendeeExplanations;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = super.timeRange;
        obj2 = ((TimelineEvent) (obj)).timeRange;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || getColor() != ((TimelineEvent) (obj)).getColor())
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = super.title;
        obj2 = ((TimelineEvent) (obj)).title;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = suggestionId;
        obj2 = ((TimelineSuggestion) (obj)).suggestionId;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = explanationMessage;
        obj2 = ((TimelineSuggestion) (obj)).explanationMessage;
        if (obj1 == obj2 || obj1 != null && obj1.equals(obj2))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && isCustom == ((TimelineSuggestion) (obj)).isCustom) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final boolean hasDeclinedStatus()
    {
        return false;
    }

    public final boolean hasInvitedStatus()
    {
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            description, attendees, super.timeRange, super.title, Integer.valueOf(getColor()), suggestionId, Boolean.valueOf(isCustom), explanationMessage
        });
    }

    public final boolean isAllDay()
    {
        return false;
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        return equals(timelineitem);
    }

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        throw new UnsupportedOperationException();
    }

    public final boolean showEndTime()
    {
        return super.timeRange.getUtcEndMillis() != 0L;
    }

    public final boolean spansAtLeastOneDay()
    {
        return false;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(attendees);
        parcel.writeString(description);
        parcel.writeTypedList(attendeeExplanations);
        parcel.writeString(suggestionId);
        parcel.writeString(explanationMessage);
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            boolean flag = false;
            obj = (FindTimeAttendee)obj;
            obj1 = (FindTimeAttendee)obj1;
            int i;
            int j;
            if (((FindTimeAttendee) (obj)).isOrganizer)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (((FindTimeAttendee) (obj1)).isOrganizer)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (i + j == 1)
            {
                return j - i;
            }
            if (((FindTimeAttendee) (obj)).email.endsWith("resource.calendar.google.com"))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = ((flag) ? 1 : 0);
            if (((FindTimeAttendee) (obj1)).email.endsWith("resource.calendar.google.com"))
            {
                j = 1;
            }
            if (i + j == 1)
            {
                return i - j;
            }
            Comparator comparator = String.CASE_INSENSITIVE_ORDER;
            if (TextUtils.isEmpty(((FindTimeAttendee) (obj)).displayName))
            {
                obj = ((FindTimeAttendee) (obj)).email;
            } else
            {
                obj = ((FindTimeAttendee) (obj)).displayName;
            }
            if (TextUtils.isEmpty(((FindTimeAttendee) (obj1)).displayName))
            {
                obj1 = ((FindTimeAttendee) (obj1)).email;
            } else
            {
                obj1 = ((FindTimeAttendee) (obj1)).displayName;
            }
            return comparator.compare(obj, obj1);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineSuggestion(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineSuggestion[i];
        }

        _cls2()
        {
        }
    }

}
