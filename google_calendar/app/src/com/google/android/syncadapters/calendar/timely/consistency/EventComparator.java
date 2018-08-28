// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.consistency;

import com.google.android.calendar.time.clock.Clock;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public final class EventComparator
{

    private static final ImmutableList CLASSES = ImmutableList.of(new OldEtagFormat(), new MismatchedRemindersNumericEtags(), new NullClientEtag());
    public static final Result UNCLASSIFIED = new Result();

    public static Result compareEvents(Event event, Event event1)
    {
        boolean flag = false;
        Object obj3 = new com.google.common.collect.ImmutableList.Builder();
        Object obj = event.etag;
        Object obj1 = event1.etag;
        Object obj2;
        int i;
        int j;
        boolean flag1;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("ETAG");
        }
        obj = event.status;
        obj1 = event1.status;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("STATUS");
        }
        obj1 = event.summary;
        obj = obj1;
        if (obj1 == null)
        {
            obj = "";
        }
        obj2 = event1.summary;
        obj1 = obj2;
        if (obj2 == null)
        {
            obj1 = "";
        }
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("SUMMARY");
        }
        obj1 = event.description;
        obj = obj1;
        if (obj1 == null)
        {
            obj = "";
        }
        obj2 = event1.description;
        obj1 = obj2;
        if (obj2 == null)
        {
            obj1 = "";
        }
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("DESCRIPTION");
        }
        if (!equalEventDateTimes(event.start, event1.start))
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("START");
        }
        if (!equalEventDateTimes(event.end, event1.end))
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("END");
        }
        obj = event.recurringEventId;
        obj1 = event1.recurringEventId;
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("RECURRING_EVENT_ID");
        }
        obj1 = event.location;
        obj = obj1;
        if (obj1 == null)
        {
            obj = "";
        }
        obj2 = event1.location;
        obj1 = obj2;
        if (obj2 == null)
        {
            obj1 = "";
        }
        if (obj == obj1 || obj != null && obj.equals(obj1))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj = (com.google.common.collect.ImmutableList.Builder)((com.google.common.collect.ImmutableCollection.Builder) (obj3)).add("LOCATION");
        }
        obj3.forceCopy = true;
        obj1 = ImmutableList.asImmutableList(((com.google.common.collect.ImmutableList.Builder) (obj3)).contents, ((com.google.common.collect.ImmutableList.Builder) (obj3)).size);
        if (!((ImmutableList) (obj1)).isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj2 = (ImmutableList)CLASSES;
        j = ((ImmutableList) (obj2)).size();
        obj = (UnmodifiableIterator)null;
        i = ((flag) ? 1 : 0);
        if (i >= j) goto _L2; else goto _L1
_L1:
        obj = ((ImmutableList) (obj2)).get(i);
        i++;
        obj = (InconsistencyClass)obj;
        obj3 = ((InconsistencyClass) (obj)).classify(((List) (obj1)), event, event1);
        if (!((ClassificationResult) (obj3)).belongsToClass)
        {
            break MISSING_BLOCK_LABEL_455;
        }
        flag1 = ((ClassificationResult) (obj3)).shouldReport;
        event = ((Event) (obj));
_L4:
        if (event != null)
        {
            event = event.getClass().getSimpleName();
        } else
        {
            event = "Unclassified";
        }
        return new Result(((ImmutableList) (obj1)), event, flag1);
_L2:
        event = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean equalDateTimes(DateTime datetime, DateTime datetime1)
    {
        boolean flag;
        if (datetime == null && datetime1 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            boolean flag1;
            if (datetime != null && datetime1 != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                return false;
            }
            datetime = Long.valueOf(datetime.value);
            datetime1 = Long.valueOf(datetime1.value);
            if (datetime != datetime1 && (datetime == null || !datetime.equals(datetime1)))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean equalEventDateTimes(EventDateTime eventdatetime, EventDateTime eventdatetime1)
    {
        boolean flag;
        if (eventdatetime == null && eventdatetime1 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            boolean flag1;
            if (eventdatetime != null && eventdatetime1 != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                return false;
            }
            if (!equalDateTimes(eventdatetime.dateTime, eventdatetime1.dateTime) || !equalDateTimes(eventdatetime.date, eventdatetime1.date))
            {
                return false;
            }
        }
        return true;
    }

    static boolean wasRecentlyUpdated(Event event)
    {
        long l1 = event.updated.value;
        long l;
        if (Clock.mockedTimestamp > 0L)
        {
            l = Clock.mockedTimestamp;
        } else
        {
            l = System.currentTimeMillis();
        }
        return l1 >= l - 0x337f9800L;
    }


    private class InconsistencyClass
    {

        public abstract ClassificationResult classify(List list, Event event, Event event1);
    }


    private class ClassificationResult
    {

        public boolean belongsToClass;
        public boolean shouldReport;

        ClassificationResult()
        {
            belongsToClass = false;
            shouldReport = false;
        }
    }


    private class Result
    {

        public final ImmutableList differentFields;
        public final String inconsistencyClass;
        public final boolean shouldReport;

        Result()
        {
            differentFields = ImmutableList.of();
            inconsistencyClass = "Unclassified";
            shouldReport = true;
        }

        Result(ImmutableList immutablelist, String s, boolean flag)
        {
            differentFields = immutablelist;
            inconsistencyClass = s;
            shouldReport = flag;
        }
    }


    private class OldEtagFormat
        implements InconsistencyClass
    {

        public final ClassificationResult classify(List list, Event event, Event event1)
        {
            ClassificationResult classificationresult = new ClassificationResult();
            if (list.size() == 1 && ((String)list.get(0)).equals("ETAG") && event.etag != null && event1.etag != null)
            {
                list = event.etag.replace("\"", "");
                event = event1.etag.replace("\"", "");
                boolean flag;
                if (list.equals(event) || list.endsWith(Base64.encodeToString(event.getBytes(), 3)))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                classificationresult.belongsToClass = flag;
            }
            return classificationresult;
        }

        OldEtagFormat()
        {
        }
    }


    private class MismatchedRemindersNumericEtags
        implements InconsistencyClass
    {

        public final ClassificationResult classify(List list, Event event, Event event1)
        {
            ClassificationResult classificationresult = new ClassificationResult();
            if (list.size() != 1 || !((String)list.get(0)).equals("ETAG") || event.etag == null || event1.etag == null)
            {
                break MISSING_BLOCK_LABEL_131;
            }
            list = event.etag.replace("\"", "");
            event = event1.etag.replace("\"", "");
            long l;
            long l1;
            long l2;
            try
            {
                l = Long.parseLong(list);
                l1 = Long.parseLong(event);
                l2 = event1.updated.value;
            }
            // Misplaced declaration of an exception variable
            catch (List list)
            {
                return classificationresult;
            }
            if (l1 == l || l != 2000L * l2)
            {
                break MISSING_BLOCK_LABEL_131;
            }
            classificationresult.belongsToClass = true;
            classificationresult.shouldReport = EventComparator.wasRecentlyUpdated(event1);
            return classificationresult;
        }

        MismatchedRemindersNumericEtags()
        {
        }
    }


    private class NullClientEtag
        implements InconsistencyClass
    {

        public final ClassificationResult classify(List list, Event event, Event event1)
        {
            ClassificationResult classificationresult = new ClassificationResult();
            if (list.size() == 1 && ((String)list.get(0)).equals("ETAG") && event.etag == null)
            {
                classificationresult.belongsToClass = true;
                classificationresult.shouldReport = EventComparator.wasRecentlyUpdated(event1);
            }
            return classificationresult;
        }

        NullClientEtag()
        {
        }
    }

}
