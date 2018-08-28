// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.newapi.common.net.LocationResolver;
import com.google.calendar.v2a.android.util.time.TimestampUtil;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.personalization.assist.annotate.api.EventTime;
import com.google.personalization.assist.annotate.api.Place;
import com.google.personalization.assist.annotate.api.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            ResultCreator, AutoValue_EventResult, AutoValue_EventResult_Timespan

public class EventResultCreator
    implements ResultCreator
{

    private static final AnnotationType CONTACT_CONNECTOR_TYPE;
    private static final AnnotationType CONTACT_TYPE;
    private static final Long DEFAULT_TIMED_DURATION = Long.valueOf(0x36ee80L);
    private static final AnnotationType LOCATION_CONNECTOR_TYPE;
    private static final AnnotationType LOCATION_CONNECTOR_TYPES[];
    private static final AnnotationType LOCATION_TYPE;
    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/quickcreate/EventResultCreator);
    private static final AnnotationType TIMESPAN_CONNECTOR_TYPE;
    private static final AnnotationType TIMESPAN_TYPE;
    private final LocationResolver locationResolver;
    public EventLocation resolvedEventLocation;
    public String targetLocationReference;

    public EventResultCreator(LocationResolver locationresolver)
    {
        locationResolver = locationresolver;
    }

    private static transient List getConnectorAndContent(List list, AnnotationType annotationtype, AnnotationType aannotationtype[])
    {
        class .Lambda._cls2
            implements Predicate
        {

            private final AnnotationType arg$1;

            public final boolean apply(Object obj1)
            {
                return EventResultCreator.lambda$findFragment$2$EventResultCreator(arg$1, (AnnotationFragment)obj1);
            }

            .Lambda._cls2(AnnotationType annotationtype)
            {
                arg$1 = annotationtype;
            }
        }

        Object obj;
        Iterator iterator;
        obj = new .Lambda._cls2(annotationtype);
        iterator = list.iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        annotationtype = ((AnnotationType) (iterator.next()));
        if (!((Predicate) (obj)).apply(annotationtype)) goto _L4; else goto _L3
_L3:
        int i;
        int j;
label0:
        {
            annotationtype = (AnnotationFragment)annotationtype;
            obj = new ArrayList();
            if (annotationtype != null && ((AnnotationFragment) (annotationtype)).beginPos_ > 0)
            {
                ((List) (obj)).add(annotationtype);
                j = aannotationtype.length;
                i = 0;
                break label0;
            }
        }
          goto _L5
_L2:
        annotationtype = null;
        continue; /* Loop/switch isn't completed */
_L6:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        .Lambda._cls2 _lcls2 = new .Lambda._cls2(aannotationtype[i]);
        Iterator iterator1 = list.iterator();
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        if (_lcls2 == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_217;
            }
            annotationtype = ((AnnotationType) (iterator1.next()));
        } while (!_lcls2.apply(annotationtype));
_L7:
        ((List) (obj)).add((AnnotationFragment)annotationtype);
        i++;
        if (true) goto _L6; else goto _L5
        annotationtype = null;
          goto _L7
_L5:
        return ((List) (obj));
        if (true) goto _L3; else goto _L8
_L8:
    }

    static final boolean lambda$findFragment$2$EventResultCreator(AnnotationType annotationtype, AnnotationFragment annotationfragment)
    {
        AnnotationType annotationtype1 = AnnotationType.forNumber(annotationfragment.annotationType_);
        annotationfragment = annotationtype1;
        if (annotationtype1 == null)
        {
            annotationfragment = AnnotationType.TEXT;
        }
        return annotationfragment == annotationtype;
    }

    static final int lambda$stripText$1$EventResultCreator(AnnotationFragment annotationfragment, AnnotationFragment annotationfragment1)
    {
        return annotationfragment1.beginPos_ - annotationfragment.beginPos_;
    }

    static final boolean lambda$wasConnectorUsed$3$EventResultCreator(List list, AnnotationFragment annotationfragment)
    {
        AnnotationType annotationtype = AnnotationType.forNumber(annotationfragment.annotationType_);
        annotationfragment = annotationtype;
        if (annotationtype == null)
        {
            annotationfragment = AnnotationType.TEXT;
        }
        return list.contains(annotationfragment);
    }

    public final Object createResult(String s, List list, boolean flag)
    {
        String s1;
        Object obj = new ArrayList();
        ((List) (obj)).addAll(getConnectorAndContent(list, LOCATION_TYPE, LOCATION_CONNECTOR_TYPES));
        ((List) (obj)).addAll(getConnectorAndContent(list, TIMESPAN_TYPE, new AnnotationType[] {
            TIMESPAN_CONNECTOR_TYPE
        }));
        ((List) (obj)).removeAll(Collections.singletonList(null));
        class .Lambda._cls1
            implements Comparator
        {

            public static final Comparator $instance = new .Lambda._cls1();

            public final int compare(Object obj4, Object obj5)
            {
                return EventResultCreator.lambda$stripText$1$EventResultCreator((AnnotationFragment)obj4, (AnnotationFragment)obj5);
            }


            private .Lambda._cls1()
            {
            }
        }

        Collections.sort(((List) (obj)), .Lambda._cls1..instance);
        s = new StringBuilder(s);
        obj = (ArrayList)obj;
        int k = ((ArrayList) (obj)).size();
        int i = 0;
        do
        {
            if (i >= k)
            {
                break;
            }
            Object obj2 = ((ArrayList) (obj)).get(i);
            int j = i + 1;
            obj2 = (AnnotationFragment)obj2;
            i = j;
            if (((AnnotationFragment) (obj2)).endPos_ > ((AnnotationFragment) (obj2)).beginPos_)
            {
                s.delete(((AnnotationFragment) (obj2)).beginPos_, ((AnnotationFragment) (obj2)).endPos_);
                i = j;
            }
        } while (true);
        s1 = s.toString().replaceAll("\\s+", " ").trim();
        obj = new .Lambda._cls2(LOCATION_TYPE);
        Iterator iterator = list.iterator();
        if (iterator == null)
        {
            throw new NullPointerException();
        }
        if (obj == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_303;
            }
            s = ((String) (iterator.next()));
        } while (!((Predicate) (obj)).apply(s));
_L1:
        Object obj3;
        Iterator iterator1;
        AnnotationFragment annotationfragment = (AnnotationFragment)s;
        if (annotationfragment == null)
        {
            s = null;
        } else
        {
            boolean flag1;
            if (annotationfragment.localBusiness_ == null)
            {
                s = Place.DEFAULT_INSTANCE;
            } else
            {
                s = annotationfragment.localBusiness_;
            }
            s = ((Place) (s)).placeReference_;
            if (resolvedEventLocation != null && s.equals(targetLocationReference))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                s = resolvedEventLocation;
            } else
            {
                obj3 = new com.google.android.calendar.api.event.location.EventLocation.Builder();
                if (annotationfragment.localBusiness_ == null)
                {
                    s = Place.DEFAULT_INSTANCE;
                } else
                {
                    s = annotationfragment.localBusiness_;
                }
                s = ((Place) (s)).name_;
                if (s == null)
                {
                    throw new NullPointerException();
                }
                obj3.name = (String)s;
                s = new EventLocation(((com.google.android.calendar.api.event.location.EventLocation.Builder) (obj3)));
            }
        }
        obj3 = new .Lambda._cls2(TIMESPAN_TYPE);
        iterator1 = list.iterator();
        if (iterator1 == null)
        {
            throw new NullPointerException();
        }
        break MISSING_BLOCK_LABEL_443;
        s = null;
          goto _L1
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
_L5:
        if (!iterator1.hasNext()) goto _L3; else goto _L2
_L2:
        Object obj1 = iterator1.next();
        if (!((Predicate) (obj3)).apply(obj1)) goto _L5; else goto _L4
_L4:
        boolean flag3;
        obj3 = (AnnotationFragment)obj1;
        if (obj3 != null)
        {
            class .Lambda._cls3
                implements Predicate
            {

                private final List arg$1;

                public final boolean apply(Object obj4)
                {
                    return EventResultCreator.lambda$wasConnectorUsed$3$EventResultCreator(arg$1, (AnnotationFragment)obj4);
                }

            .Lambda._cls3(List list)
            {
                arg$1 = list;
            }
            }

            boolean flag2;
            if (((AnnotationFragment) (obj3)).eventTime_ == null)
            {
                obj1 = EventTime.DEFAULT_INSTANCE;
            } else
            {
                obj1 = ((AnnotationFragment) (obj3)).eventTime_;
            }
            if ((((EventTime) (obj1)).bitField0_ & 1) == 1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                break MISSING_BLOCK_LABEL_643;
            }
        }
        obj1 = null;
_L6:
        obj3 = new .Lambda._cls3(Arrays.asList(new AnnotationType[] {
            LOCATION_TYPE, LOCATION_CONNECTOR_TYPE, TIMESPAN_TYPE, TIMESPAN_CONNECTOR_TYPE, CONTACT_TYPE, CONTACT_CONNECTOR_TYPE
        }));
        long l;
        long l1;
        if (Iterators.indexOf(list.iterator(), ((Predicate) (obj3))) != -1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        return new AutoValue_EventResult(s1, s, ((EventResult.Timespan) (obj1)), flag3, flag);
_L3:
        obj1 = null;
          goto _L4
        if (((AnnotationFragment) (obj3)).eventTime_ == null)
        {
            obj1 = EventTime.DEFAULT_INSTANCE;
        } else
        {
            obj1 = ((AnnotationFragment) (obj3)).eventTime_;
        }
        flag3 = ((EventTime) (obj1)).allDay_;
        if (((EventTime) (obj1)).startTime_ == null)
        {
            obj3 = Time.DEFAULT_INSTANCE;
        } else
        {
            obj3 = ((EventTime) (obj1)).startTime_;
        }
        l = ((Time) (obj3)).timeMs_;
        l1 = l;
        if (flag3)
        {
            l1 = TimestampUtil.roundToMidnight(l, null, true, "UTC");
        }
        if ((((EventTime) (obj1)).bitField0_ & 2) == 2)
        {
            if (((EventTime) (obj1)).endTime_ == null)
            {
                obj1 = Time.DEFAULT_INSTANCE;
            } else
            {
                obj1 = ((EventTime) (obj1)).endTime_;
            }
        } else
        {
            obj1 = null;
        }
        if (obj1 != null)
        {
            if (flag3)
            {
                l = TimestampUtil.roundToMidnight(((Time) (obj1)).timeMs_, null, true, "UTC");
            } else
            {
                l = ((Time) (obj1)).timeMs_;
            }
        } else
        {
            if (flag3)
            {
                l = 0x5265c00L;
            } else
            {
                l = DEFAULT_TIMED_DURATION.longValue();
            }
            l = l1 + l;
        }
        if (flag3)
        {
            if (!TimestampUtil.isTimestampUtcMidnight(l1))
            {
                throw new IllegalArgumentException();
            }
            if (!TimestampUtil.isTimestampUtcMidnight(l))
            {
                throw new IllegalArgumentException();
            }
        }
        obj1 = new AutoValue_EventResult_Timespan(l1, l, flag3);
          goto _L6
    }

    public final void processSelectedSuggestion(List list, String s, String s1)
    {
        s = new .Lambda._cls2(LOCATION_TYPE);
        s1 = list.iterator();
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        if (s == null)
        {
            throw new NullPointerException();
        }
        do
        {
            if (!s1.hasNext())
            {
                break MISSING_BLOCK_LABEL_181;
            }
            list = ((List) (s1.next()));
        } while (!s.apply(list));
_L1:
        list = (AnnotationFragment)list;
        if (list == null)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        class .Lambda._cls0
            implements Consumer
        {

            private final EventResultCreator arg$1;
            private final String arg$2;

            public final void accept(Object obj)
            {
                EventResultCreator eventresultcreator = arg$1;
                String s2 = arg$2;
                obj = (EventLocation)obj;
                if (eventresultcreator.targetLocationReference.equals(s2))
                {
                    eventresultcreator.resolvedEventLocation = ((EventLocation) (obj));
                }
            }

            .Lambda._cls0(String s)
            {
                arg$1 = EventResultCreator.this;
                arg$2 = s;
            }
        }

        if (((AnnotationFragment) (list)).localBusiness_ == null)
        {
            list = Place.DEFAULT_INSTANCE;
        } else
        {
            list = ((AnnotationFragment) (list)).localBusiness_;
        }
        s = ((Place) (list)).placeReference_;
        if (s.equals(targetLocationReference))
        {
            break MISSING_BLOCK_LABEL_210;
        }
        targetLocationReference = s;
        resolvedEventLocation = null;
        list = locationResolver;
        list = (FluentFuture)CalendarExecutor.NET.submit(new com.google.android.calendar.newapi.common.net.LocationResolver..Lambda._cls0(list, s));
        s = LogUtils.newFailureLoggingCallback(new .Lambda._cls0(s), TAG, "Resolving reference failed: %s", new Object[] {
            s
        });
        s1 = CalendarExecutor.MAIN;
        if (s == null)
        {
            throw new NullPointerException();
        }
        break MISSING_BLOCK_LABEL_194;
        list = null;
          goto _L1
        list.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(list, s), s1);
    }

    static 
    {
        LOCATION_TYPE = AnnotationType.LOCAL_BUSINESS;
        LOCATION_CONNECTOR_TYPE = AnnotationType.AT;
        LOCATION_CONNECTOR_TYPES = (new AnnotationType[] {
            AnnotationType.AT, AnnotationType.TO, AnnotationType.IN
        });
        TIMESPAN_TYPE = AnnotationType.EVENT_TIME;
        TIMESPAN_CONNECTOR_TYPE = AnnotationType.ON;
        CONTACT_TYPE = AnnotationType.CONTACT;
        CONTACT_CONNECTOR_TYPE = AnnotationType.WITH;
    }
}
