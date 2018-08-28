// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.annotation;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.Utils;
import com.google.android.calendar.newapi.quickcreate.QuickCreateType;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.ClientType;
import com.google.personalization.assist.annotate.DeviceType;
import com.google.personalization.assist.annotate.SuggestionType;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest;
import com.google.personalization.assist.annotate.api.Annotation;
import com.google.personalization.assist.annotate.api.GeoLocation;
import com.google.personalization.assist.annotate.api.SuggestionClick;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Locale;

public class RequestFactory
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/quickcreate/annotation/RequestFactory);
    private final String country;
    private final DeviceType deviceType;
    private final String language;
    private GeoLocation location;
    private final String timezone;
    private final QuickCreateType type;

    private RequestFactory(QuickCreateType quickcreatetype, DeviceType devicetype, String s, String s1, String s2)
    {
        type = quickcreatetype;
        deviceType = devicetype;
        language = s;
        country = s1;
        timezone = s2;
    }

    public static RequestFactory create(Context context, QuickCreateType quickcreatetype)
    {
        quickcreatetype = createImpl(context, quickcreatetype);
        context = (FluentFuture)CalendarExecutor.BACKGROUND.submit(new DeviceLocation..Lambda._cls0(context));
        class .Lambda._cls0
            implements Consumer
        {

            private final RequestFactory arg$1;

            public final void accept(Object obj)
            {
                RequestFactory.lambda$create$0$RequestFactory(arg$1, (Location)obj);
            }

            .Lambda._cls0()
            {
                arg$1 = RequestFactory.this;
            }
        }

        com.google.common.util.concurrent.FutureCallback futurecallback = LogUtils.newFailureLoggingCallback(quickcreatetype. new .Lambda._cls0(), TAG, "Couldn't load the device's location.", new Object[0]);
        CalendarExecutor calendarexecutor = CalendarExecutor.MAIN;
        if (futurecallback == null)
        {
            throw new NullPointerException();
        } else
        {
            context.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(context, futurecallback), calendarexecutor);
            return quickcreatetype;
        }
    }

    private static RequestFactory createImpl(Context context, QuickCreateType quickcreatetype)
    {
        String s;
        Locale locale;
        String s1 = null;
        DeviceType devicetype;
        if (context.getResources().getBoolean(0x7f0c0016))
        {
            devicetype = DeviceType.ANDROID_TABLET;
        } else
        {
            devicetype = DeviceType.ANDROID_PHONE;
        }
        locale = context.getResources().getConfiguration().locale;
        if (locale != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        if (locale != null)
        {
            s1 = locale.getCountry();
        }
        return new RequestFactory(quickcreatetype, devicetype, s, s1, Utils.getTimeZoneId(context));
_L2:
        String s2 = locale.getLanguage();
        s = s2;
        if (s2.length() > 2)
        {
            s = s2.substring(0, 2);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static AnnotatedSuggestRequest createWarmupRequest(Context context, QuickCreateType quickcreatetype, String s)
    {
        return createImpl(context, quickcreatetype).createRequest("", null, null, s);
    }

    static final void lambda$create$0$RequestFactory(RequestFactory requestfactory, Location location1)
    {
        if (location1 != null)
        {
            double d = Math.pow(10D, 7D);
            com.google.personalization.assist.annotate.api.GeoLocation.Builder builder = (com.google.personalization.assist.annotate.api.GeoLocation.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)GeoLocation.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            int i = (int)(location1.getLatitude() * d);
            builder.copyOnWrite();
            GeoLocation geolocation = (GeoLocation)builder.instance;
            geolocation.bitField0_ = geolocation.bitField0_ | 1;
            geolocation.latitudeE7_ = i;
            i = (int)(d * location1.getLongitude());
            builder.copyOnWrite();
            geolocation = (GeoLocation)builder.instance;
            geolocation.bitField0_ = geolocation.bitField0_ | 2;
            geolocation.longitudeE7_ = i;
            i = Math.round(location1.getAccuracy());
            builder.copyOnWrite();
            location1 = (GeoLocation)builder.instance;
            location1.bitField0_ = ((GeoLocation) (location1)).bitField0_ | 4;
            location1.accuracyRadius_ = i;
            requestfactory.location = (GeoLocation)(GeneratedMessageLite)builder.build();
        }
    }

    public final AnnotatedSuggestRequest createRequest(String s, Annotation annotation, SuggestionClick suggestionclick, String s1)
    {
        com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest.Builder builder = (com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)AnnotatedSuggestRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = ClientType.TIMELY;
        builder.copyOnWrite();
        AnnotatedSuggestRequest annotatedsuggestrequest = (AnnotatedSuggestRequest)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        annotatedsuggestrequest.bitField0_ = annotatedsuggestrequest.bitField0_ | 0x20;
        annotatedsuggestrequest.clientType_ = ((ClientType) (obj)).value;
        if (type == QuickCreateType.REMINDER)
        {
            obj = SuggestionType.REMINDER;
        } else
        {
            obj = SuggestionType.CALENDAR;
        }
        builder.copyOnWrite();
        annotatedsuggestrequest = (AnnotatedSuggestRequest)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        annotatedsuggestrequest.bitField0_ = annotatedsuggestrequest.bitField0_ | 0x4000;
        annotatedsuggestrequest.suggestionType_ = ((SuggestionType) (obj)).value;
        obj = deviceType;
        builder.copyOnWrite();
        annotatedsuggestrequest = (AnnotatedSuggestRequest)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        annotatedsuggestrequest.bitField0_ = annotatedsuggestrequest.bitField0_ | 0x40;
        annotatedsuggestrequest.deviceType_ = ((DeviceType) (obj)).value;
        obj = timezone;
        builder.copyOnWrite();
        annotatedsuggestrequest = (AnnotatedSuggestRequest)builder.instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        annotatedsuggestrequest.bitField0_ = annotatedsuggestrequest.bitField0_ | 0x100;
        annotatedsuggestrequest.timezone_ = ((String) (obj));
        if (location != null)
        {
            obj = location;
            builder.copyOnWrite();
            AnnotatedSuggestRequest annotatedsuggestrequest1 = (AnnotatedSuggestRequest)builder.instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            annotatedsuggestrequest1.location_ = ((GeoLocation) (obj));
            annotatedsuggestrequest1.bitField0_ = annotatedsuggestrequest1.bitField0_ | 0x80;
        }
        if (language != null)
        {
            obj = language;
            builder.copyOnWrite();
            AnnotatedSuggestRequest annotatedsuggestrequest2 = (AnnotatedSuggestRequest)builder.instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            annotatedsuggestrequest2.bitField0_ = annotatedsuggestrequest2.bitField0_ | 2;
            annotatedsuggestrequest2.language_ = ((String) (obj));
        }
        if (country != null)
        {
            obj = country;
            builder.copyOnWrite();
            AnnotatedSuggestRequest annotatedsuggestrequest3 = (AnnotatedSuggestRequest)builder.instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            annotatedsuggestrequest3.bitField0_ = annotatedsuggestrequest3.bitField0_ | 4;
            annotatedsuggestrequest3.country_ = ((String) (obj));
        }
        builder.copyOnWrite();
        obj = (AnnotatedSuggestRequest)builder.instance;
        if (s == null)
        {
            throw new NullPointerException();
        }
        obj.bitField0_ = ((AnnotatedSuggestRequest) (obj)).bitField0_ | 1;
        obj.query_ = s;
        if (annotation != null)
        {
            builder.copyOnWrite();
            s = (AnnotatedSuggestRequest)builder.instance;
            if (annotation == null)
            {
                throw new NullPointerException();
            }
            s.annotation_ = annotation;
            s.bitField0_ = ((AnnotatedSuggestRequest) (s)).bitField0_ | 0x10;
        }
        if (suggestionclick != null)
        {
            builder.copyOnWrite();
            s = (AnnotatedSuggestRequest)builder.instance;
            if (suggestionclick == null)
            {
                throw new NullPointerException();
            }
            s.suggestionClick_ = suggestionclick;
            s.bitField0_ = ((AnnotatedSuggestRequest) (s)).bitField0_ | 0x800;
        }
        if (s1 != null)
        {
            builder.copyOnWrite();
            s = (AnnotatedSuggestRequest)builder.instance;
            if (s1 == null)
            {
                throw new NullPointerException();
            }
            s.bitField0_ = ((AnnotatedSuggestRequest) (s)).bitField0_ | 0x400;
            s.sessionId_ = s1;
        }
        return (AnnotatedSuggestRequest)(GeneratedMessageLite)builder.build();
    }

}
