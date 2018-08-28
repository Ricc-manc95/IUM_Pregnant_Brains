// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.caribou.smartmail;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.ProtobufArrayList;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.caribou.smartmail:
//            Time, Image, Person

public final class FlightReservation extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(FlightReservation.DEFAULT_INSTANCE);
        }
    }

    public static final class FlightSegment extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final FlightSegment DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public Time actualArrivalTime_;
        public Time actualDepartureTime_;
        public String airlineCode_;
        public String airlineName_;
        public String arrivalAirportCode_;
        public String arrivalCity_;
        public String arrivalGate_;
        public String arrivalTerminal_;
        public Time arrivalTime_;
        public int bitField0_;
        public String bookingReference_;
        public String departureAirportCode_;
        public String departureCity_;
        public String departureGate_;
        public String departureTerminal_;
        public Time departureTime_;
        public String flightNumber_;
        public Image image_;
        public com.google.protobuf.Internal.ProtobufList passengerInfo_;
        public int statusCode_;

        public static FlightSegment parseFrom(InputStream inputstream)
            throws IOException
        {
            Object obj = DEFAULT_INSTANCE;
            if (inputstream == null)
            {
                inputstream = Internal.EMPTY_BYTE_ARRAY;
                inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
            } else
            {
                inputstream = new com.google.protobuf.CodedInputStream.StreamDecoder(inputstream, 4096);
            }
            obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
            if (obj != null)
            {
                boolean flag1 = Boolean.TRUE.booleanValue();
                byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
                boolean flag;
                if (byte0 == 1)
                {
                    flag = true;
                } else
                if (byte0 == 0)
                {
                    flag = false;
                } else
                {
                    flag = Protobuf.INSTANCE.schemaFor(obj.getClass()).isInitialized(obj);
                    if (flag1)
                    {
                        int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                        if (flag)
                        {
                            inputstream = ((InputStream) (obj));
                        } else
                        {
                            inputstream = null;
                        }
                        ((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                    }
                }
                if (!flag)
                {
                    inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                    if (inputstream == null)
                    {
                        throw null;
                    } else
                    {
                        throw inputstream;
                    }
                }
            }
            return (FlightSegment)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 270
        //                       1 275
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 217
        //                       6 221;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new FlightSegment();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = StatusCode.internalVerifier;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\023\000\001\001\033\023\000\001\000\001\b\001\002\b\002\003\b\003\004\b\004\007\b\t\b\b\n\t\b\013\n\b\f\f\t\005\r\t\007\016\t\006\017\t\b\020\b\017\021\b\020\022\b\021\023\f\023\024\b\022\026\t\025\033\033", new Object[] {
                "bitField0_", "bookingReference_", "airlineName_", "airlineCode_", "flightNumber_", "departureAirportCode_", "departureCity_", "arrivalAirportCode_", "arrivalCity_", "departureTime_", 
                "arrivalTime_", "actualDepartureTime_", "actualArrivalTime_", "departureTerminal_", "arrivalTerminal_", "departureGate_", "statusCode_", obj, "arrivalGate_", "image_", 
                "passengerInfo_", com/google/caribou/smartmail/FlightReservation$FlightSegment$FlightPassengerInfo
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/caribou/smartmail/FlightReservation$FlightSegment;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/caribou/smartmail/FlightReservation$FlightSegment;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/caribou/smartmail/FlightReservation$FlightSegment;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new FlightSegment();
            FlightSegment flightsegment = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/caribou/smartmail/FlightReservation$FlightSegment, flightsegment);
        }

        private FlightSegment()
        {
            bookingReference_ = "";
            airlineName_ = "";
            airlineCode_ = "";
            flightNumber_ = "";
            departureAirportCode_ = "";
            departureCity_ = "";
            arrivalAirportCode_ = "";
            arrivalCity_ = "";
            departureTerminal_ = "";
            arrivalTerminal_ = "";
            departureGate_ = "";
            arrivalGate_ = "";
            passengerInfo_ = ProtobufArrayList.EMPTY_LIST;
        }
    }

    public static final class FlightSegment.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        FlightSegment.Builder()
        {
            super(FlightSegment.DEFAULT_INSTANCE);
        }
    }

    public static final class FlightSegment.FlightPassengerInfo extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final FlightSegment.FlightPassengerInfo DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public Person passenger_;
        public String seatNumber_;

        public static FlightSegment.FlightPassengerInfo parseFrom(InputStream inputstream)
            throws IOException
        {
            Object obj = DEFAULT_INSTANCE;
            if (inputstream == null)
            {
                inputstream = Internal.EMPTY_BYTE_ARRAY;
                inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
            } else
            {
                inputstream = new com.google.protobuf.CodedInputStream.StreamDecoder(inputstream, 4096);
            }
            obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
            if (obj != null)
            {
                boolean flag1 = Boolean.TRUE.booleanValue();
                byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
                boolean flag;
                if (byte0 == 1)
                {
                    flag = true;
                } else
                if (byte0 == 0)
                {
                    flag = false;
                } else
                {
                    flag = Protobuf.INSTANCE.schemaFor(obj.getClass()).isInitialized(obj);
                    if (flag1)
                    {
                        int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                        if (flag)
                        {
                            inputstream = ((InputStream) (obj));
                        } else
                        {
                            inputstream = null;
                        }
                        ((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                    }
                }
                if (!flag)
                {
                    inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                    if (inputstream == null)
                    {
                        throw null;
                    } else
                    {
                        throw inputstream;
                    }
                }
            }
            return (FlightSegment.FlightPassengerInfo)obj;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 155
        //                       1 160
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 102
        //                       6 106;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new FlightSegment.FlightPassengerInfo();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\001\002\000\001\001\002\002\000\000\000\001\t\000\002\b\001", new Object[] {
                "bitField0_", "passenger_", "seatNumber_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/caribou/smartmail/FlightReservation$FlightSegment$FlightPassengerInfo;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/caribou/smartmail/FlightReservation$FlightSegment$FlightPassengerInfo;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/caribou/smartmail/FlightReservation$FlightSegment$FlightPassengerInfo;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new FlightSegment.FlightPassengerInfo();
            FlightSegment.FlightPassengerInfo flightpassengerinfo = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/caribou/smartmail/FlightReservation$FlightSegment$FlightPassengerInfo, flightpassengerinfo);
        }

        private FlightSegment.FlightPassengerInfo()
        {
            seatNumber_ = "";
        }
    }

    public static final class FlightSegment.FlightPassengerInfo.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        FlightSegment.FlightPassengerInfo.Builder()
        {
            super(FlightSegment.FlightPassengerInfo.DEFAULT_INSTANCE);
        }
    }

    public static final class FlightSegment.StatusCode extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final FlightSegment.StatusCode $VALUES[];
        public static final FlightSegment.StatusCode CANCELLED;
        public static final FlightSegment.StatusCode DELAYED;
        public static final FlightSegment.StatusCode LANDED;
        public static final FlightSegment.StatusCode ON_TIME;
        public static final FlightSegment.StatusCode REDIRECTED;
        public static final FlightSegment.StatusCode SCHEDULED;
        public static final FlightSegment.StatusCode UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        private final int value;

        public static FlightSegment.StatusCode forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return SCHEDULED;

            case 2: // '\002'
                return ON_TIME;

            case 3: // '\003'
                return LANDED;

            case 4: // '\004'
                return DELAYED;

            case 5: // '\005'
                return CANCELLED;

            case 6: // '\006'
                return REDIRECTED;
            }
        }

        public static FlightSegment.StatusCode[] values()
        {
            return (FlightSegment.StatusCode[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new FlightSegment.StatusCode("UNKNOWN", 0, 0);
            SCHEDULED = new FlightSegment.StatusCode("SCHEDULED", 1, 1);
            ON_TIME = new FlightSegment.StatusCode("ON_TIME", 2, 2);
            LANDED = new FlightSegment.StatusCode("LANDED", 3, 3);
            DELAYED = new FlightSegment.StatusCode("DELAYED", 4, 4);
            CANCELLED = new FlightSegment.StatusCode("CANCELLED", 5, 5);
            REDIRECTED = new FlightSegment.StatusCode("REDIRECTED", 6, 6);
            $VALUES = (new FlightSegment.StatusCode[] {
                UNKNOWN, SCHEDULED, ON_TIME, LANDED, DELAYED, CANCELLED, REDIRECTED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return FlightSegment.StatusCode.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

        }

        private FlightSegment.StatusCode(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }


    public static final FlightReservation DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    public int bitField0_;
    public com.google.protobuf.Internal.ProtobufList flightSegment_;

    private FlightReservation()
    {
        flightSegment_ = ProtobufArrayList.EMPTY_LIST;
    }

    public static FlightReservation parseFrom(InputStream inputstream)
        throws IOException
    {
        Object obj = DEFAULT_INSTANCE;
        if (inputstream == null)
        {
            inputstream = Internal.EMPTY_BYTE_ARRAY;
            inputstream = CodedInputStream.newInstance(inputstream, 0, inputstream.length, false);
        } else
        {
            inputstream = new com.google.protobuf.CodedInputStream.StreamDecoder(inputstream, 4096);
        }
        obj = GeneratedMessageLite.parsePartialFrom(((GeneratedMessageLite) (obj)), inputstream, ExtensionRegistryLite.getEmptyRegistry());
        if (obj != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
            boolean flag;
            if (byte0 == 1)
            {
                flag = true;
            } else
            if (byte0 == 0)
            {
                flag = false;
            } else
            {
                flag = Protobuf.INSTANCE.schemaFor(obj.getClass()).isInitialized(obj);
                if (flag1)
                {
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        inputstream = ((InputStream) (obj));
                    } else
                    {
                        inputstream = null;
                    }
                    ((GeneratedMessageLite) (obj)).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
                }
            }
            if (!flag)
            {
                inputstream = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                if (inputstream == null)
                {
                    throw null;
                } else
                {
                    throw inputstream;
                }
            }
        }
        return (FlightReservation)obj;
    }

    protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
    {
        i - 1;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 155
    //                   1 160
    //                   2 70
    //                   3 52
    //                   4 62
    //                   5 102
    //                   6 106;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new UnsupportedOperationException();
_L5:
        obj = new FlightReservation();
_L10:
        return obj;
_L6:
        return new Builder();
_L4:
        return new RawMessageInfo(DEFAULT_INSTANCE, "\001\001\000\001\002\002\001\000\001\000\002\033", new Object[] {
            "bitField0_", "flightSegment_", com/google/caribou/smartmail/FlightReservation$FlightSegment
        });
_L7:
        return DEFAULT_INSTANCE;
_L8:
        Parser parser;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L10; else goto _L9
_L9:
        com/google/caribou/smartmail/FlightReservation;
        JVM INSTR monitorenter ;
        parser = PARSER;
        obj = parser;
        if (parser != null) goto _L12; else goto _L11
_L11:
        obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
        PARSER = ((Parser) (obj));
_L12:
        com/google/caribou/smartmail/FlightReservation;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        com/google/caribou/smartmail/FlightReservation;
        JVM INSTR monitorexit ;
        throw obj;
_L2:
        return Byte.valueOf((byte)1);
_L3:
        return null;
    }

    static 
    {
        DEFAULT_INSTANCE = new FlightReservation();
        FlightReservation flightreservation = DEFAULT_INSTANCE;
        GeneratedMessageLite.defaultInstanceMap.put(com/google/caribou/smartmail/FlightReservation, flightreservation);
    }
}
