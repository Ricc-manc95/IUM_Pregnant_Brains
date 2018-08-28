// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.content.Context;
import com.google.android.apps.calendar.timebox.EventsApiImpl;
import com.google.android.apps.calendar.timebox.V2AEventsApi;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.calendarlist.CalendarKey;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.UncommittedEventKey;
import com.google.android.calendar.api.event.V2AEventKey;
import com.google.calendar.v2a.shared.storage.AsyncEventService;
import com.google.calendar.v2a.shared.storage.proto.GetEventRequest;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.GeneratedMessageLite;

public abstract class EventImageResolver
    implements EventImage.Resolver
{

    public EventImageResolver()
    {
    }

    abstract CalendarKey calendarId();

    abstract EventKey id();

    abstract String location();

    public final ListenableFuture resolveAsync(Context context, int i, int j)
    {
        class .Lambda._cls0
            implements Supplier
        {

            private final Context arg$1;

            public final Object get()
            {
                Object obj2 = arg$1;
                com.google.android.calendar.time.TimeUtils.TimeZoneUtils timezoneutils = TimeUtils.tZUtils;
                if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.firstTZRequest)
                {
                    com.google.android.calendar.time.TimeUtils.TimeZoneUtils.getTimeZone(((Context) (obj2)), null, false);
                }
                if (com.google.android.calendar.time.TimeUtils.TimeZoneUtils.useHomeTZ)
                {
                    obj2 = com.google.android.calendar.time.TimeUtils.TimeZoneUtils.homeTZ;
                } else
                {
                    obj2 = Time.getCurrentTimezone();
                }
                return TimeZone.getTimeZone(((String) (obj2)));
            }

            .Lambda._cls0(Context context)
            {
                arg$1 = context;
            }
        }

        Object obj = new EventsApiImpl(context, new .Lambda._cls0(context));
        CalendarKey calendarkey = calendarId();
        Object obj1 = id();
        class .Lambda._cls1
            implements AsyncFunction
        {

            private final EventImageResolver arg$1;
            private final Context arg$2;
            private final int arg$3;
            private final int arg$4;

            public final ListenableFuture apply(Object obj2)
            {
                EventImageDetails eventimagedetails;
                Object obj3;
                Cache cache;
                boolean flag;
                int l;
                int i1;
                boolean flag1;
                flag = false;
                obj3 = arg$1;
                Context context1 = arg$2;
                l = arg$3;
                i1 = arg$4;
                eventimagedetails = (EventImageDetails)obj2;
                obj3 = ((EventImageResolver) (obj3)).title();
                if (eventimagedetails == null)
                {
                    obj2 = EventImage.DEFAULT_INSTANCE;
                    if (obj2 == null)
                    {
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj2);
                    }
                }
                obj2 = VolleyQueueHolder.requestQueue;
                if (obj2 == null)
                {
                    throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
                }
                cache = ((RequestQueue)obj2).mCache;
                if (context1 != null && NetworkUtil.isConnectedToInternet(context1))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                obj2 = EventImage.newInstanceIfAvailable(cache, flag1, eventimagedetails.getGooglePlusImageUrl(), RtlMirroring.DO_NOT_MIRROR);
                if (obj2 == null) goto _L2; else goto _L1
_L1:
                class .Lambda._cls2
                    implements Function
                {

                    private final Cache arg$1;
                    private final boolean arg$2;
                    private final String arg$3;

                    public final Object apply(Object obj4)
                    {
                        Object obj5;
                        Cache cache1;
                        PlacePageOrMapUrl placepageormapurl;
                        boolean flag2;
                        cache1 = arg$1;
                        flag2 = arg$2;
                        obj5 = arg$3;
                        placepageormapurl = (PlacePageOrMapUrl)obj4;
                        if (placepageormapurl == null) goto _L2; else goto _L1
_L1:
                        obj4 = EventImage.newInstanceIfAvailable(cache1, flag2, placepageormapurl.placePageImageUrl, RtlMirroring.DO_NOT_MIRROR);
                        if (obj4 == null) goto _L2; else goto _L3
_L3:
                        obj5 = obj4;
                        if (obj4 == null)
                        {
                            obj5 = EventImage.DEFAULT_INSTANCE;
                        }
                        return obj5;
_L2:
                        obj5 = EventImage.newInstanceIfAvailable(cache1, flag2, FlairAllocatorFactory.getFlairUrlString(((String) (obj5))), RtlMirroring.MIRROR_IN_RTL);
                        obj4 = obj5;
                        if (obj5 == null)
                        {
                            if (placepageormapurl == null)
                            {
                                obj4 = null;
                            } else
                            {
                                obj4 = EventImage.newInstanceIfAvailable(cache1, flag2, placepageormapurl.staticMapUrl, RtlMirroring.DO_NOT_MIRROR);
                            }
                        }
                        if (true) goto _L3; else goto _L4
_L4:
                    }

                        .Lambda._cls2(Cache cache, boolean flag, String s)
                        {
                            arg$1 = cache;
                            arg$2 = flag;
                            arg$3 = s;
                        }
                }

                int k;
                if (obj2 != null)
                {
                    if (obj2 == null)
                    {
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(obj2);
                    }
                } else
                {
                    return AbstractTransformFuture.create(PlacePageOrMapUrl.getPlacePageOrMapsUrl(eventimagedetails.getEventLocation(), l, i1), new .Lambda._cls2(cache, flag1, ((String) (obj3))), CalendarExecutor.DISK);
                }
_L2:
                obj2 = eventimagedetails.getSmartMailImageUrl();
                k = ((flag) ? 1 : 0);
                if (eventimagedetails.getType() == null) goto _L4; else goto _L3
_L3:
                eventimagedetails.getType().ordinal();
                JVM INSTR tableswitch 0 3: default 196
            //                           0 248
            //                           1 227
            //                           2 234
            //                           3 241;
                   goto _L5 _L6 _L7 _L8 _L9
_L6:
                break MISSING_BLOCK_LABEL_248;
_L5:
                k = ((flag) ? 1 : 0);
_L4:
                if (EventImage.newInstanceIfAvailable(cache, flag1, ((String) (obj2)), RtlMirroring.DO_NOT_MIRROR) != null)
                {
                    obj2 = EventImage.newInstance(((String) (obj2)), k, RtlMirroring.DO_NOT_MIRROR);
                } else
                if (k != 0)
                {
                    obj2 = EventImage.newInstance(null, k, RtlMirroring.MIRROR_IN_RTL);
                } else
                {
                    obj2 = eventimagedetails.getSmartMailAddress();
                    if (obj2 == null || ((SmartMailAddress) (obj2)).latitude == null || ((SmartMailAddress) (obj2)).longitude == null)
                    {
                        obj2 = null;
                    } else
                    {
                        obj2 = TimelyUtils.getStaticMapsUrl(((SmartMailAddress) (obj2)).latitude, ((SmartMailAddress) (obj2)).longitude, ((SmartMailAddress) (obj2)).longitude, l, i1);
                    }
                    obj2 = EventImage.newInstanceIfAvailable(cache, flag1, ((String) (obj2)), RtlMirroring.DO_NOT_MIRROR);
                }
                  goto _L1
_L7:
                k = 0x7f02025f;
                  goto _L4
_L8:
                k = 0x7f020260;
                  goto _L4
_L9:
                k = 0x7f020261;
                  goto _L4
                k = 0x7f020262;
                  goto _L4
            }

            .Lambda._cls1(Context context, int i, int j)
            {
                arg$1 = EventImageResolver.this;
                arg$2 = context;
                arg$3 = i;
                arg$4 = j;
            }
        }

        if (obj1 instanceof CpEventKey)
        {
            obj1 = (CpEventKey)obj1;
            obj = (FluentFuture)CalendarExecutor.EVENTS.submit(new com.google.android.apps.calendar.timebox.EventsApiImpl..Lambda._cls7(((EventsApiImpl) (obj)), ((CpEventKey) (obj1)), calendarkey));
        } else
        if ((obj1 instanceof V2AEventKey) && ((EventsApiImpl) (obj)).v2aEventsApi != null)
        {
            obj = ((EventsApiImpl) (obj)).v2aEventsApi;
            obj1 = (V2AEventKey)obj1;
            obj = ((V2AEventsApi) (obj)).asyncEventService;
            com.google.calendar.v2a.shared.storage.proto.GetEventRequest.Builder builder = (com.google.calendar.v2a.shared.storage.proto.GetEventRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)GetEventRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            obj1 = ((V2AEventKey) (obj1)).getV2Key();
            builder.copyOnWrite();
            GetEventRequest geteventrequest = (GetEventRequest)builder.instance;
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            geteventrequest.eventKey_ = ((com.google.calendar.v2a.shared.storage.proto.EventKey) (obj1));
            geteventrequest.bitField0_ = geteventrequest.bitField0_ | 1;
            obj = ((AsyncEventService) (obj)).getEvent((GetEventRequest)(GeneratedMessageLite)builder.build());
            if (obj instanceof FluentFuture)
            {
                obj = (FluentFuture)obj;
            } else
            {
                obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
            }
            obj = (FluentFuture)AbstractTransformFuture.create(((ListenableFuture) (obj)), com.google.android.apps.calendar.timebox.V2AEventsApi..Lambda._cls5.$instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        } else
        if (obj1 instanceof UncommittedEventKey)
        {
            if (true)
            {
                obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
            if (obj instanceof FluentFuture)
            {
                obj = (FluentFuture)obj;
            } else
            {
                obj = new ForwardingFluentFuture(((ListenableFuture) (obj)));
            }
        } else
        {
            context = String.valueOf(obj1);
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(context).length() + 21)).append("Unhandled event key: ").append(context).toString());
        }
        return AbstractTransformFuture.create(((ListenableFuture) (obj)), new .Lambda._cls1(context, i, j), CalendarExecutor.DISK);
    }

    abstract String title();
}
