// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timebox.TimeRange;
import com.google.android.apps.calendar.util.concurrent.UncancelableFuture;
import com.google.android.apps.calendar.util.preference.PreferenceUtils;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.timebox.Birthday;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineItem, TimelineEvent, BirthdayCache, TimelineItemOperation

public class TimelineBirthday
    implements TimelineItem
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final String TAG = com/google/android/calendar/timely/TimelineBirthday.getSimpleName();
    public List birthdays;
    private ListenableFuture birthdaysLoader;
    private int color;
    public String singleLineTitle;
    public String sourceAccountName;
    public String subtitle;
    private final TimeRange timeRange;
    private String title;
    public boolean titlesValid;

    public TimelineBirthday(Parcel parcel)
    {
        titlesValid = false;
        int j = parcel.readInt();
        birthdays = new ArrayList(j);
        for (int i = 0; i < j; i++)
        {
            birthdays.add((Birthday)Birthday.CREATOR.createFromParcel(parcel));
        }

        color = parcel.readInt();
        title = parcel.readString();
        subtitle = parcel.readString();
        singleLineTitle = parcel.readString();
        boolean flag;
        if (parcel.readByte() != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture immediatesuccessfulfuture;
            if (true)
            {
                immediatesuccessfulfuture = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                immediatesuccessfulfuture = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
            birthdaysLoader = immediatesuccessfulfuture;
        }
        timeRange = (TimeRange)parcel.readParcelable(com/google/android/apps/calendar/timebox/TimeRange.getClassLoader());
        sourceAccountName = parcel.readString();
    }

    public TimelineBirthday(TimelineEvent timelineevent)
    {
        titlesValid = false;
        birthdays = new ArrayList();
        title = "";
        subtitle = "";
        singleLineTitle = "";
        timeRange = timelineevent.timeRange;
        sourceAccountName = timelineevent.sourceAccount;
        addEvent(timelineevent);
    }

    private final TimelineBirthday clone()
    {
        TimelineBirthday timelinebirthday;
        try
        {
            timelinebirthday = (TimelineBirthday)super.clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new IllegalStateException(clonenotsupportedexception);
        }
        return timelinebirthday;
    }

    private final String getNames()
    {
        StringBuilder stringbuilder = new StringBuilder();
        Birthday birthday;
        for (Iterator iterator = birthdays.iterator(); iterator.hasNext(); stringbuilder.append(birthday.fullName()))
        {
            birthday = (Birthday)iterator.next();
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append(", ");
            }
        }

        return stringbuilder.toString();
    }

    public final void addEvent(TimelineEvent timelineevent)
    {
        List list = birthdays;
        EventKey eventkey = (EventKey)timelineevent.eventKey;
        CalendarKey calendarkey = timelineevent.calendarId;
        String s = timelineevent.sourceAccount;
        timelineevent = timelineevent.title;
        list.add((new com.google.android.calendar.timebox.AutoValue_Birthday.Builder()).eventId(eventkey).calendarId(calendarkey).originalTitle(timelineevent).fullName(timelineevent).email("").isContactAvailable(false).isSelfBirthday(false).isBirthday(true).isGPlusUser(false).profileId(null).photoUrl(null).sourceAccount(s).build());
    }

    public final volatile TimelineItem clone()
    {
        return clone();
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return (TimelineBirthday)clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public final int getColor()
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

    public final com.google.android.calendar.event.image.EventImage.Resolver getEventImageResolver()
    {
        return null;
    }

    public final Object getId()
    {
        return null;
    }

    public final String getLocation()
    {
        return null;
    }

    public final String getOrganizer()
    {
        for (Iterator iterator = birthdays.iterator(); iterator.hasNext();)
        {
            Birthday birthday = (Birthday)iterator.next();
            if (birthday.isContactAvailable())
            {
                return birthday.email();
            }
        }

        return "";
    }

    public final com.google.android.calendar.api.event.attendee.Response.ResponseStatus getSelfAttendeeStatus()
    {
        return com.google.android.calendar.api.event.attendee.Response.ResponseStatus.NEEDS_ACTION;
    }

    public final long getSortId()
    {
        if (birthdays.isEmpty())
        {
            return -1L;
        } else
        {
            return ((Birthday)birthdays.get(0)).calendarId().getLocalId();
        }
    }

    public final TimelineItem.SortType getSortType()
    {
        return TimelineItem.SortType.BIRTHDAY;
    }

    public final String getSourceAccountName()
    {
        return sourceAccountName;
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

    public final boolean hasDeclinedStatus()
    {
        return false;
    }

    public final boolean hasHeadlineImage()
    {
        return false;
    }

    public final boolean hasImage()
    {
        return false;
    }

    public final boolean hasInvitedStatus()
    {
        return false;
    }

    public final boolean isAllDay()
    {
        return true;
    }

    public final boolean isIdentical(TimelineItem timelineitem)
    {
        if (this != timelineitem) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (timelineitem == null || getClass() != timelineitem.getClass())
        {
            return false;
        }
        timelineitem = (TimelineBirthday)timelineitem;
        if (color != ((TimelineBirthday) (timelineitem)).color)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj = timeRange;
        Object obj1 = ((TimelineBirthday) (timelineitem)).timeRange;
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
        obj = birthdays;
        obj1 = ((TimelineBirthday) (timelineitem)).birthdays;
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
        obj = title;
        obj1 = ((TimelineBirthday) (timelineitem)).title;
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
        obj = subtitle;
        obj1 = ((TimelineBirthday) (timelineitem)).subtitle;
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
        obj = singleLineTitle;
        obj1 = ((TimelineBirthday) (timelineitem)).singleLineTitle;
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
        obj = sourceAccountName;
        timelineitem = ((TimelineBirthday) (timelineitem)).sourceAccountName;
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

    public final boolean isSameInstance(TimelineItem timelineitem)
    {
        while (timelineitem == null || !timelineitem.getClass().equals(getClass()) || timeRange.getStartDay() != timelineitem.getStartDay()) 
        {
            return false;
        }
        return true;
    }

    public final void loadBirthdays(Context context)
    {
        try
        {
            loadBirthdaysAsync(context).get();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            LogUtils.wtf(TAG, context, "Unable to load birthdays", new Object[0]);
        }
    }

    public final ListenableFuture loadBirthdaysAsync(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if (birthdaysLoader == null)
        {
            context = BirthdayCache.loadBirthdaysAsync(context, Collections.unmodifiableList(birthdays));
            SettableFuture settablefuture = new SettableFuture();
            birthdaysLoader = UncancelableFuture.uncancelable(settablefuture);
            class .Lambda._cls0
                implements Runnable
            {

                private final TimelineBirthday arg$1;
                private final ListenableFuture arg$2;
                private final SettableFuture arg$3;

                public final void run()
                {
                    TimelineBirthday timelinebirthday;
                    ListenableFuture listenablefuture;
                    timelinebirthday = arg$1;
                    listenablefuture = arg$2;
                    SettableFuture settablefuture1 = arg$3;
                    timelinebirthday;
                    JVM INSTR monitorenter ;
                    timelinebirthday.birthdays = (List)listenablefuture.get();
                    timelinebirthday.titlesValid = false;
                    timelinebirthday;
                    JVM INSTR monitorexit ;
_L2:
                    settablefuture1.set(null);
                    return;
                    Exception exception1;
                    exception1;
                    timelinebirthday;
                    JVM INSTR monitorexit ;
                    try
                    {
                        throw exception1;
                    }
                    catch (Exception exception)
                    {
                        LogUtils.wtf(TimelineBirthday.TAG, exception, "Unable to load birthdays", new Object[0]);
                    }
                    if (true) goto _L2; else goto _L1
_L1:
                }

            .Lambda._cls0(ListenableFuture listenablefuture, SettableFuture settablefuture)
            {
                arg$1 = TimelineBirthday.this;
                arg$2 = listenablefuture;
                arg$3 = settablefuture;
            }
            }

            context.addListener(new .Lambda._cls0(context, settablefuture), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
        this;
        JVM INSTR monitorexit ;
        return birthdaysLoader;
        context;
        this;
        JVM INSTR monitorexit ;
        throw context;
    }

    public final void onInitialBirthdaysAdded()
    {
        boolean flag1 = true;
        boolean flag;
        if (birthdaysLoader == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (!titlesValid)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        List list = BirthdayCache.getBirthdaysIfLoaded(birthdays);
        if (list != null)
        {
            com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture immediatesuccessfulfuture;
            if (true)
            {
                immediatesuccessfulfuture = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                immediatesuccessfulfuture = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
            birthdaysLoader = immediatesuccessfulfuture;
            birthdays = list;
        }
    }

    public final transient Object perform(TimelineItemOperation timelineitemoperation, Object aobj[])
    {
        return timelineitemoperation.onBirthdayBundle(this, aobj);
    }

    public final boolean shouldShowOrganizerImage()
    {
        return true;
    }

    public final boolean showEndTime()
    {
        return false;
    }

    public final boolean spansAtLeastOneDay()
    {
        return true;
    }

    public String toString()
    {
        String s = getClass().getSimpleName();
        String s1 = title;
        int i = birthdays.size();
        String s2 = getNames();
        String s3 = String.valueOf(timeRange);
        String s4 = sourceAccountName;
        return (new StringBuilder(String.valueOf(s).length() + 69 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length())).append("[type=").append(s).append(", title=").append(s1).append(", count=").append(i).append(", name=").append(s2).append(", timeRange=").append(s3).append(", sourceAccount=").append(s4).append("]").toString();
    }

    public final void validate(Context context)
    {
        this;
        JVM INSTR monitorenter ;
        if (titlesValid) goto _L2; else goto _L1
_L1:
        Birthday birthday;
        String s;
        int i;
        title = getNames();
        i = birthdays.size();
        birthday = (Birthday)Collections.unmodifiableList(birthdays).get(0);
        s = context.getResources().getQuantityString(0x7f120007, i, new Object[] {
            Integer.valueOf(i)
        });
        subtitle = s;
        if (i <= 1)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        singleLineTitle = s;
_L4:
        titlesValid = true;
_L2:
        color = PreferenceUtils.getBirthdayColor(context);
        this;
        JVM INSTR monitorexit ;
        return;
        singleLineTitle = birthday.originalTitle();
        if (true) goto _L4; else goto _L3
_L3:
        context;
        this;
        JVM INSTR monitorexit ;
        throw context;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag;
        boolean flag1;
        flag1 = true;
        parcel.writeInt(birthdays.size());
        ArrayList arraylist;
        Object obj;
        int k;
        int l;
        if (birthdaysLoader != null && birthdaysLoader.isDone())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorenter ;
        arraylist = new ArrayList(birthdays);
        this;
        JVM INSTR monitorexit ;
        arraylist = (ArrayList)arraylist;
        l = arraylist.size();
        for (k = 0; k < l;)
        {
            obj = arraylist.get(k);
            k++;
            ((Birthday)obj).writeToParcel(parcel, i);
        }

        break MISSING_BLOCK_LABEL_113;
        parcel;
        this;
        JVM INSTR monitorexit ;
        throw parcel;
        parcel.writeInt(color);
        parcel.writeString(title);
        parcel.writeString(subtitle);
        parcel.writeString(singleLineTitle);
        int j;
        if (flag)
        {
            j = ((flag1) ? 1 : 0);
        } else
        {
            j = 0;
        }
        parcel.writeByte((byte)j);
        parcel.writeParcelable(timeRange, i);
        parcel.writeString(sourceAccountName);
        return;
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new TimelineBirthday(parcel);
        }

        public final Object[] newArray(int i)
        {
            return new TimelineBirthday[i];
        }

        _cls1()
        {
        }
    }

}
