// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.meetings.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.google.android.apps.calendar.meetings.MeetingsPstnFinder;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.corp.android.volley.proto.MessageLiteVolleyRequest;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;

public final class MeetingsPstnFinderImpl
    implements MeetingsPstnFinder
{

    public final Context context;
    private final boolean fetchFromGstatic;

    public MeetingsPstnFinderImpl(Context context1, boolean flag)
    {
        context = context1.getApplicationContext();
        fetchFromGstatic = flag;
    }

    public final ListenableFuture findLocalPhoneNumber(String s, String s1)
    {
        if (fetchFromGstatic)
        {
            Object obj = MessageLiteVolleyRequest.builder((Parser)com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null));
            Object obj1 = Uri.parse("http://www.gstatic.com/meet/phone/public_regional_config.bin");
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            obj.requestUrl = (Uri)obj1;
            if (obj == null)
            {
                throw null;
            }
            if (((com.google.corp.android.volley.proto.MessageLiteVolleyRequest.Builder) (obj)).requestUrl == null)
            {
                throw new NullPointerException(String.valueOf("Requires a URL to be passed in"));
            }
            obj = new MessageLiteVolleyRequest(((com.google.corp.android.volley.proto.MessageLiteVolleyRequest.Builder) (obj)));
            obj1 = VolleyQueueHolder.requestQueue;
            if (obj1 == null)
            {
                throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
            }
            ((RequestQueue)obj1).add(((com.android.volley.Request) (obj)));
        }
        class .Lambda._cls0
            implements Callable
        {

            private final MeetingsPstnFinderImpl arg$1;

            public final Object call()
            {
                return arg$1.lambda$readInternationalPhoneNumberListProto$0$MeetingsPstnFinderImpl();
            }

            .Lambda._cls0()
            {
                arg$1 = MeetingsPstnFinderImpl.this;
            }
        }

        return AbstractTransformFuture.create((FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls0()), new LocalPhoneNumberFunction(s, s1), CalendarExecutor.BACKGROUND);
    }

    final com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig lambda$readInternationalPhoneNumberListProto$0$MeetingsPstnFinderImpl()
        throws Exception
    {
        Object obj;
        if (fetchFromGstatic)
        {
            obj = VolleyQueueHolder.requestQueue;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
            }
            obj = ((RequestQueue)obj).mCache.get("http://www.gstatic.com/meet/phone/public_regional_config.bin");
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            boolean flag;
            if (((com.android.volley.Cache.Entry) (obj)).ttl < System.currentTimeMillis())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                return (com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig)((Parser)com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).parseFrom(((com.android.volley.Cache.Entry) (obj)).data);
            }
        }
        obj = context.getAssets().open("regional_config.bin");
        com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig allregionconfig = com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig.parseFrom(((InputStream) (obj)));
        ((InputStream) (obj)).close();
        return allregionconfig;
        Exception exception;
        exception;
        ((InputStream) (obj)).close();
        throw exception;
    }

    public final ListenableFuture listPhones(String s)
    {
        return AbstractTransformFuture.create((FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls0()), new ListNumbersFunction(s), CalendarExecutor.BACKGROUND);
    }

    private class LocalPhoneNumberFunction
        implements Function
    {

        private final String excludedRegionCode;
        private final String pin;
        private final MeetingsPstnFinderImpl this$0;

        private final Optional findPhoneForCountry(String s, com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig allregionconfig, boolean flag, LocalPhoneSource localphonesource)
        {
            String s1;
            String s2;
            String s3;
            do
            {
                if (TextUtils.isEmpty(s))
                {
                    return Absent.INSTANCE;
                }
                s1 = s.toUpperCase(Locale.ENGLISH);
                s = excludedRegionCode;
                boolean flag1;
                if (s1 == s || s1 != null && s1.equals(s))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    return Absent.INSTANCE;
                }
                if (s1 == null)
                {
                    throw new NullPointerException();
                }
                s = allregionconfig.config_;
                if (s.containsKey(s1))
                {
                    s = (com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig)s.get(s1);
                } else
                {
                    s = null;
                }
                if (s == null)
                {
                    s = Absent.INSTANCE;
                } else
                {
                    s = new Present(s);
                }
                if (!s.isPresent())
                {
                    return Absent.INSTANCE;
                }
                if (!flag)
                {
                    break;
                }
                s = ((com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig)s.get()).fallbackRegion_;
                flag = false;
            } while (true);
            if (((com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig)s.get()).phoneNumbers_.size() == 0)
            {
                return Absent.INSTANCE;
            }
            com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion universalpstnnumberforregion = (com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion)((com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig)s.get()).phoneNumbers_.get(0);
            allregionconfig = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.forNumber(universalpstnnumberforregion.availability_);
            s = allregionconfig;
            if (allregionconfig == null)
            {
                s = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.UNRECOGNIZED;
            }
            if (s == com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.NONE)
            {
                return Absent.INSTANCE;
            }
            s2 = universalpstnnumberforregion.e164_;
            s3 = pin;
            s = MeetingsPstnFinderImpl.this;
            allregionconfig = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.forNumber(universalpstnnumberforregion.availability_);
            s = allregionconfig;
            if (allregionconfig == null)
            {
                s = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.UNRECOGNIZED;
            }
            s.ordinal();
            JVM INSTR tableswitch 2 4: default 300
        //                       2 342
        //                       3 342
        //                       4 349;
               goto _L1 _L2 _L2 _L3
_L3:
            break MISSING_BLOCK_LABEL_349;
_L1:
            s = Availability.NONE;
_L4:
            s = new AutoValue_PhoneNumberDetails(s2, s3, new Locale("", s1), s, localphonesource);
            if (s == null)
            {
                throw new NullPointerException();
            } else
            {
                return new Present(s);
            }
_L2:
            s = Availability.INTERNAL_ONLY;
              goto _L4
            s = Availability.PUBLIC;
              goto _L4
        }

        public final Object apply(Object obj)
        {
            obj = (com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig)obj;
            Object obj2 = (TelephonyManager)context.getSystemService("phone");
            Object obj1 = ((TelephonyManager) (obj2)).getNetworkCountryIso();
            obj2 = ((TelephonyManager) (obj2)).getSimCountryIso();
            Optional optional = findPhoneForCountry(((String) (obj1)), ((com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig) (obj)), false, LocalPhoneSource.LOCAL_NETWORK);
            if (optional.isPresent())
            {
                return (PhoneNumberDetails)optional.get();
            }
            optional = findPhoneForCountry(((String) (obj2)), ((com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig) (obj)), false, LocalPhoneSource.LOCAL_SIM);
            if (optional.isPresent())
            {
                return (PhoneNumberDetails)optional.get();
            }
            obj1 = findPhoneForCountry(((String) (obj1)), ((com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig) (obj)), true, LocalPhoneSource.REGIONAL_NETWORK);
            if (((Optional) (obj1)).isPresent())
            {
                return (PhoneNumberDetails)((Optional) (obj1)).get();
            }
            obj = findPhoneForCountry(((String) (obj2)), ((com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig) (obj)), true, LocalPhoneSource.REGIONAL_SIM);
            if (((Optional) (obj)).isPresent())
            {
                return (PhoneNumberDetails)((Optional) (obj)).get();
            } else
            {
                return null;
            }
        }

        LocalPhoneNumberFunction(String s, String s1)
        {
            this$0 = MeetingsPstnFinderImpl.this;
            super();
            excludedRegionCode = Platform.nullToEmpty(s).toUpperCase();
            pin = s1;
        }
    }


    private class ListNumbersFunction
        implements Function
    {

        private final String pin;
        private final MeetingsPstnFinderImpl this$0;

        public final Object apply(Object obj)
        {
            ArrayList arraylist;
            Iterator iterator;
            obj = (com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig)obj;
            arraylist = new ArrayList();
            if (obj == null)
            {
                break MISSING_BLOCK_LABEL_256;
            }
            iterator = Collections.unmodifiableMap(((com.google.protos.communication.meetings.proto.RegionConfig.AllRegionConfig) (obj)).config_).entrySet().iterator();
_L2:
            String s;
            String s1;
            String s2;
            com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion universalpstnnumberforregion;
            do
            {
                do
                {
                    if (!iterator.hasNext())
                    {
                        break MISSING_BLOCK_LABEL_256;
                    }
                    obj = (java.util.Map.Entry)iterator.next();
                    s = (String)((java.util.Map.Entry) (obj)).getKey();
                    obj = (com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig)((java.util.Map.Entry) (obj)).getValue();
                } while (((com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig) (obj)).phoneNumbers_.size() == 0);
                universalpstnnumberforregion = (com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion)((com.google.protos.communication.meetings.proto.RegionConfig.RegionalConfig) (obj)).phoneNumbers_.get(0);
                com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability availability = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.forNumber(universalpstnnumberforregion.availability_);
                obj = availability;
                if (availability == null)
                {
                    obj = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.UNRECOGNIZED;
                }
            } while (obj == com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.NONE);
            s1 = universalpstnnumberforregion.e164_;
            s2 = pin;
            obj = MeetingsPstnFinderImpl.this;
            com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability availability1 = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.forNumber(universalpstnnumberforregion.availability_);
            obj = availability1;
            if (availability1 == null)
            {
                obj = com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability.UNRECOGNIZED;
            }
            switch (((com.google.protos.communication.meetings.proto.RegionConfig.UniversalPstnNumberForRegion.Availability) (obj)).ordinal())
            {
            default:
                obj = Availability.NONE;
                break;

            case 2: // '\002'
            case 3: // '\003'
                break; /* Loop/switch isn't completed */

            case 4: // '\004'
                break MISSING_BLOCK_LABEL_249;
            }
_L3:
            LocalPhoneSource localphonesource = LocalPhoneSource.FULL_LIST;
            arraylist.add(new AutoValue_PhoneNumberDetails(s1, s2, new Locale("", s), ((Availability) (obj)), localphonesource));
            if (true) goto _L2; else goto _L1
_L1:
            obj = Availability.INTERNAL_ONLY;
              goto _L3
            obj = Availability.PUBLIC;
              goto _L3
            return arraylist;
        }

        ListNumbersFunction(String s)
        {
            this$0 = MeetingsPstnFinderImpl.this;
            super();
            pin = s;
        }
    }

}
