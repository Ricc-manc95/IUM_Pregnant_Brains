// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.os.Parcel;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.flair.FlairAllocatorFactory;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.calendar.api.calendarlist.CalendarAccessLevel;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.UncommittedEventKey;
import com.google.android.calendar.event.image.AutoValue_EventImageResolver;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.common.base.Platform;
import java.util.TimeZone;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem, TimelineItemOperation

public class TimelineEvent
    implements TimelineItem
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public String accountType;
    public CalendarAccessLevel calendarAccessLevel;
    public CalendarKey calendarId;
    public int color;
    public boolean endTimeUnspecified;
    public EventKey eventKey;
    public boolean hasImageData;
    public boolean hasSmartMail;
    public boolean hasTimeProposals;
    public boolean isInstanceModifiable;
    public boolean isOutOfOffice;
    public boolean isTransparent;
    public String location;
    public String organizer;
    public EventKey originalEventKey;
    public String ownerAccount;
    public com.google.android.calendar.api.event.attendee.Response.ResponseStatus selfAttendeeStatus;
    public boolean showEveryoneDeclined;
    public String sourceAccount;
    public TimeRange timeRange;
    public String title;

    public TimelineEvent()
    {
        eventKey = new UncommittedEventKey();
        calendarId = CalendarKey.NONE;
        title = null;
        color = 0;
        location = null;
        ownerAccount = null;
        organizer = null;
        sourceAccount = null;
        accountType = null;
        timeRange = TimeRange.newInstanceUnsafe(TimeZone.getDefault(), false, 0L, 0, 0, 0L, 0, 0);
        endTimeUnspecified = false;
        selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
        hasSmartMail = false;
        hasImageData = false;
        calendarAccessLevel = CalendarAccessLevel.NONE;
        isTransparent = false;
        isOutOfOffice = false;
        hasTimeProposals = false;
    }

    public TimelineEvent(Parcel parcel)
    {
        boolean flag1 = true;
        super();
        eventKey = (EventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader());
        originalEventKey = (EventKey)parcel.readParcelable(com/google/android/calendar/api/event/EventKey.getClassLoader());
        calendarId = (CalendarKey)parcel.readParcelable(com/google/android/calendar/api/calendarlist/CalendarKey.getClassLoader());
        color = parcel.readInt();
        title = parcel.readString();
        location = parcel.readString();
        ownerAccount = parcel.readString();
        sourceAccount = parcel.readString();
        accountType = parcel.readString();
        organizer = parcel.readString();
        timeRange = (TimeRange)parcel.readParcelable(com/google/android/apps/calendar/timebox/TimeRange.getClassLoader());
        boolean flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        endTimeUnspecified = flag;
        selfAttendeeStatus = com.google.android.calendar.api.event.attendee.Response.ResponseStatus.createFromInteger(parcel.readInt());
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isInstanceModifiable = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hasSmartMail = flag;
        if (parcel.readInt() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hasImageData = flag;
        calendarAccessLevel = (CalendarAccessLevel)CalendarAccessLevel.CREATOR.createFromParcel(parcel);
        if (parcel.readByte() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isTransparent = flag;
        if (parcel.readByte() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        showEveryoneDeclined = flag;
        if (parcel.readByte() == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isOutOfOffice = flag;
        if (parcel.readByte() == 1)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        hasTimeProposals = flag;
    }

    public TimelineEvent(TimelineEvent timelineevent)
    {
        eventKey = timelineevent.eventKey;
        originalEventKey = timelineevent.originalEventKey;
        calendarId = timelineevent.calendarId;
        color = timelineevent.color;
        title = timelineevent.title;
        location = timelineevent.location;
        ownerAccount = timelineevent.ownerAccount;
        organizer = timelineevent.organizer;
        sourceAccount = timelineevent.sourceAccount;
        timeRange = timelineevent.timeRange;
        endTimeUnspecified = timelineevent.endTimeUnspecified;
        selfAttendeeStatus = timelineevent.selfAttendeeStatus;
        isInstanceModifiable = timelineevent.isInstanceModifiable;
        hasSmartMail = timelineevent.hasSmartMail;
        hasImageData = timelineevent.hasImageData;
        calendarAccessLevel = timelineevent.calendarAccessLevel;
        isTransparent = timelineevent.isTransparent;
        isOutOfOffice = timelineevent.isOutOfOffice;
        hasTimeProposals = timelineevent.hasTimeProposals;
    }

    public final TimelineEvent clone()
    {
        TimelineEvent timelineevent;
        try
        {
            timelineevent = (TimelineEvent)super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new IllegalStateException(clonenotsupportedexception);
        }
        return timelineevent;
    }

    public final volatile TimelineItem clone()
    {
        return clone();
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return (TimelineEvent)clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getColor()
    {
        return color;
    }

    public final int getEndDay()
    {
        return timeRange.getEndDay();
    }

    public final long getEndMillis()
    {
        return timeRange.getUtcEndMillis();
    }

    public final int getEndTime()
    {
        return timeRange.getEndMinute();
    }

    public com.google.android.calendar.event.image.EventImage.Resolver getEventImageResolver()
    {
        String s = title;
        EventKey eventkey = (EventKey)eventKey;
        CalendarKey calendarkey = calendarId;
        String s1 = location;
        return new AutoValue_EventImageResolver(Platform.nullToEmpty(s), eventkey, calendarkey, Platform.nullToEmpty(s1));
    }

    public final Object getId()
    {
        return eventKey;
    }

    public final String getLocation()
    {
        return location;
    }

    public final String getOrganizer()
    {
        return organizer;
    }

    public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus getSelfAttendeeStatus()
    {
        return selfAttendeeStatus;
    }

    public final long getSortId()
    {
        return calendarId.getLocalId();
    }

    public TimelineItem.SortType getSortType()
    {
        return TimelineItem.SortType.EVENT;
    }

    public final String getSourceAccountName()
    {
        return sourceAccount;
    }

    public final int getStartDay()
    {
        return timeRange.getStartDay();
    }

    public final long getStartMillis()
    {
        return timeRange.getUtcStartMillis();
    }

    public final int getStartTime()
    {
        return timeRange.getStartMinute();
    }

    public final TimeRange getTimeRange()
    {
        return timeRange;
    }

    public final String getTitle()
    {
        return title;
    }

    public boolean hasDeclinedStatus()
    {
        return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.DECLINED.equals(selfAttendeeStatus);
    }

    public boolean hasHeadlineImage()
    {
        return hasImage();
    }

    public boolean hasImage()
    {
        return hasImageData || !AccountUtil.isGoogleExchangeType(accountType) && FlairAllocatorFactory.getFlairUrlString(title) != null;
    }

    public boolean hasInvitedStatus()
    {
        if (com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION.equals(selfAttendeeStatus))
        {
            String s = organizer;
            String s1 = ownerAccount;
            boolean flag;
            if (s != null && s.equalsIgnoreCase(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAllDay()
    {
        return timeRange.isAllDay();
    }

    public boolean isIdentical(TimelineItem timelineitem)
    {
        if (this != timelineitem) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (timelineitem == null || getClass() != timelineitem.getClass())
        {
            return false;
        }
        timelineitem = (TimelineEvent)timelineitem;
        Object obj = eventKey;
        Object obj1 = ((TimelineEvent) (timelineitem)).eventKey;
        boolean flag;
        if (obj == obj1 || obj != null && obj.equals(obj1))
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
        obj = originalEventKey;
        obj1 = ((TimelineEvent) (timelineitem)).originalEventKey;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || calendarId != ((TimelineEvent) (timelineitem)).calendarId || color != ((TimelineEvent) (timelineitem)).color)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = timeRange;
        obj1 = ((TimelineEvent) (timelineitem)).timeRange;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || endTimeUnspecified != ((TimelineEvent) (timelineitem)).endTimeUnspecified || selfAttendeeStatus != ((TimelineEvent) (timelineitem)).selfAttendeeStatus || showEveryoneDeclined != ((TimelineEvent) (timelineitem)).showEveryoneDeclined || hasTimeProposals != ((TimelineEvent) (timelineitem)).hasTimeProposals)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = calendarAccessLevel;
        obj1 = ((TimelineEvent) (timelineitem)).calendarAccessLevel;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || hasSmartMail != ((TimelineEvent) (timelineitem)).hasSmartMail || hasImageData != ((TimelineEvent) (timelineitem)).hasImageData || isTransparent != ((TimelineEvent) (timelineitem)).isTransparent)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = title;
        obj1 = ((TimelineEvent) (timelineitem)).title;
        if (obj == obj1 || obj != null && obj.equals(obj1))
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
        obj = location;
        obj1 = ((TimelineEvent) (timelineitem)).location;
        if (obj == obj1 || obj != null && obj.equals(obj1))
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
        obj = ownerAccount;
        obj1 = ((TimelineEvent) (timelineitem)).ownerAccount;
        if (obj == obj1 || obj != null && obj.equals(obj1))
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
        obj = organizer;
        obj1 = ((TimelineEvent) (timelineitem)).organizer;
        if (obj == obj1 || obj != null && obj.equals(obj1))
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
        obj = sourceAccount;
        obj1 = ((TimelineEvent) (timelineitem)).sourceAccount;
        if (obj == obj1 || obj != null && obj.equals(obj1))
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
        obj = accountType;
        timelineitem = ((TimelineEvent) (timelineitem)).accountType;
        if (obj == timelineitem || obj != null && obj.equals(timelineitem))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public final boolean isOutOfOffice()
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        return ((FeatureConfig)featureconfig).ooo() && isOutOfOffice;
    }

    public boolean isSameInstance(TimelineItem timelineitem)
    {
        if (timelineitem == null || !timelineitem.getClass().equals(getClass()))
        {
            return equals(timelineitem);
        }
        Object obj = (TimelineEvent)timelineitem;
        if (originalEventKey != null)
        {
            timelineitem = originalEventKey;
        } else
        {
            timelineitem = eventKey;
        }
        if (((TimelineEvent) (obj)).originalEventKey != null)
        {
            obj = ((TimelineEvent) (obj)).originalEventKey;
        } else
        {
            obj = ((TimelineEvent) (obj)).eventKey;
        }
        return timelineitem == obj || timelineitem != null && timelineitem.equals(obj);
    }

    public transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onAnyEvent(this, aobj);
    }

    public final boolean shouldShowOrganizerImage()
    {
        String s = organizer;
        String s1 = ownerAccount;
        boolean flag;
        if (s != null && s.equalsIgnoreCase(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return !flag;
    }

    public final boolean shouldShowTimeProposalIcon()
    {
        if (hasTimeProposals)
        {
            String s = organizer;
            String s1 = ownerAccount;
            boolean flag;
            if (s != null && s.equalsIgnoreCase(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && calendarAccessLevel.isGreaterOrEqual(CalendarAccessLevel.WRITER))
            {
                return true;
            }
        }
        return false;
    }

    public boolean showEndTime()
    {
        return !endTimeUnspecified;
    }

    public boolean spansAtLeastOneDay()
    {
        TimeRange timerange = timeRange;
        boolean flag = showEndTime();
        return timerange.isAllDay() || flag && timerange.getUtcEndMillis() - timerange.getUtcStartMillis() >= 0x5265c00L;
    }

    public String toString()
    {
        String s = getClass().getSimpleName();
        String s1 = title;
        String s2 = String.valueOf(eventKey);
        String s3 = String.valueOf(originalEventKey);
        String s4 = String.valueOf(timeRange);
        String s5 = location;
        return (new StringBuilder(String.valueOf(s).length() + 68 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length())).append("[type=").append(s).append(", title=").append(s1).append(", eventKey=").append(s2).append(", originalEventKey=").append(s3).append(", timeRange=").append(s4).append(", location=").append(s5).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeParcelable(eventKey, i);
        parcel.writeParcelable(originalEventKey, i);
        parcel.writeParcelable(calendarId, i);
        parcel.writeInt(color);
        parcel.writeString(title);
        parcel.writeString(location);
        parcel.writeString(ownerAccount);
        parcel.writeString(sourceAccount);
        parcel.writeString(accountType);
        parcel.writeString(organizer);
        parcel.writeParcelable(timeRange, i);
        byte byte0;
        int j;
        if (endTimeUnspecified)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        com.google.android.calendar.api.event.attendee.Response.ResponseStatus.writeToParcel(parcel, selfAttendeeStatus);
        if (isInstanceModifiable)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (hasSmartMail)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        if (hasImageData)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        parcel.writeInt(j);
        calendarAccessLevel.writeToParcel(parcel, i);
        if (isTransparent)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        parcel.writeByte(byte0);
        if (showEveryoneDeclined)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        parcel.writeByte(byte0);
        if (isOutOfOffice)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        parcel.writeByte(byte0);
        if (hasTimeProposals)
        {
            byte0 = flag;
        } else
        {
            byte0 = 0;
        }
        parcel.writeByte(byte0);
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/timely/TimelineEvent);
    }

    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineEvent(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineEvent[i];
        }

        _cls1()
        {
        }
    }

}
