// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protos.communication.meetings.proto;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
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

public final class RegionConfig
{
    public static final class AllRegionConfig extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final AllRegionConfig DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public MapFieldLite config_;

        public static AllRegionConfig parseFrom(InputStream inputstream)
            throws IOException
        {
            GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(DEFAULT_INSTANCE, CodedInputStream.newInstance(inputstream), ExtensionRegistryLite.getEmptyRegistry());
            if (generatedmessagelite != null)
            {
                boolean flag1 = Boolean.TRUE.booleanValue();
                byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    flag = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
                    if (flag1)
                    {
                        int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                        if (flag)
                        {
                            inputstream = generatedmessagelite;
                        } else
                        {
                            inputstream = null;
                        }
                        generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
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
            return (AllRegionConfig)generatedmessagelite;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 153
        //                       1 158
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 100
        //                       6 104;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new AllRegionConfig();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            obj = ConfigDefaultEntryHolder.defaultEntry;
            return new RawMessageInfo(DEFAULT_INSTANCE, "\000\001\000\000\001\001\001\001\000\000\0012", new Object[] {
                "config_", obj
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/communication/meetings/proto/RegionConfig$AllRegionConfig;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/communication/meetings/proto/RegionConfig$AllRegionConfig;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/communication/meetings/proto/RegionConfig$AllRegionConfig;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new AllRegionConfig();
            AllRegionConfig allregionconfig = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/communication/meetings/proto/RegionConfig$AllRegionConfig, allregionconfig);
        }

        private AllRegionConfig()
        {
            config_ = MapFieldLite.EMPTY_MAP_FIELD;
        }
    }

    public static final class AllRegionConfig.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        AllRegionConfig.Builder()
        {
            super(AllRegionConfig.DEFAULT_INSTANCE);
        }
    }

    static final class AllRegionConfig.ConfigDefaultEntryHolder
    {

        public static final MapEntryLite defaultEntry;

        static 
        {
            defaultEntry = new MapEntryLite(com.google.protobuf.WireFormat.FieldType.STRING, "", com.google.protobuf.WireFormat.FieldType.MESSAGE, RegionalConfig.DEFAULT_INSTANCE);
        }
    }

    public static final class RegionalConfig extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final RegionalConfig DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int bitField0_;
        public String fallbackRegion_;
        public com.google.protobuf.Internal.ProtobufList phoneNumbers_;

        public static RegionalConfig parseFrom(InputStream inputstream)
            throws IOException
        {
            GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(DEFAULT_INSTANCE, CodedInputStream.newInstance(inputstream), ExtensionRegistryLite.getEmptyRegistry());
            if (generatedmessagelite != null)
            {
                boolean flag1 = Boolean.TRUE.booleanValue();
                byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    flag = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
                    if (flag1)
                    {
                        int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                        if (flag)
                        {
                            inputstream = generatedmessagelite;
                        } else
                        {
                            inputstream = null;
                        }
                        generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
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
            return (RegionalConfig)generatedmessagelite;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 160
        //                       1 165
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 107
        //                       6 111;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new RegionalConfig();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\000\002\000\001\001\002\002\000\001\000\001\033\002\u0208", new Object[] {
                "bitField0_", "phoneNumbers_", com/google/protos/communication/meetings/proto/RegionConfig$UniversalPstnNumberForRegion, "fallbackRegion_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/communication/meetings/proto/RegionConfig$RegionalConfig;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/communication/meetings/proto/RegionConfig$RegionalConfig;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/communication/meetings/proto/RegionConfig$RegionalConfig;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new RegionalConfig();
            RegionalConfig regionalconfig = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/communication/meetings/proto/RegionConfig$RegionalConfig, regionalconfig);
        }

        private RegionalConfig()
        {
            phoneNumbers_ = ProtobufArrayList.EMPTY_LIST;
            fallbackRegion_ = "";
        }
    }

    public static final class RegionalConfig.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        RegionalConfig.Builder()
        {
            super(RegionalConfig.DEFAULT_INSTANCE);
        }
    }

    public static final class UniversalPstnNumberForRegion extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public static final UniversalPstnNumberForRegion DEFAULT_INSTANCE;
        private static volatile Parser PARSER;
        public int availability_;
        public String e164_;

        public static UniversalPstnNumberForRegion parseFrom(InputStream inputstream)
            throws IOException
        {
            GeneratedMessageLite generatedmessagelite = GeneratedMessageLite.parsePartialFrom(DEFAULT_INSTANCE, CodedInputStream.newInstance(inputstream), ExtensionRegistryLite.getEmptyRegistry());
            if (generatedmessagelite != null)
            {
                boolean flag1 = Boolean.TRUE.booleanValue();
                byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    flag = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
                    if (flag1)
                    {
                        int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                        if (flag)
                        {
                            inputstream = generatedmessagelite;
                        } else
                        {
                            inputstream = null;
                        }
                        generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, inputstream);
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
            return (UniversalPstnNumberForRegion)generatedmessagelite;
        }

        protected final Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj)
        {
            i - 1;
            JVM INSTR tableswitch 0 6: default 44
        //                       0 150
        //                       1 155
        //                       2 70
        //                       3 52
        //                       4 62
        //                       5 97
        //                       6 101;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
            throw new UnsupportedOperationException();
_L5:
            obj = new UniversalPstnNumberForRegion();
_L10:
            return obj;
_L6:
            return new Builder();
_L4:
            return new RawMessageInfo(DEFAULT_INSTANCE, "\000\002\000\000\001\004\002\000\000\000\001\u0208\004\f", new Object[] {
                "e164_", "availability_"
            });
_L7:
            return DEFAULT_INSTANCE;
_L8:
            Parser parser;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L10; else goto _L9
_L9:
            com/google/protos/communication/meetings/proto/RegionConfig$UniversalPstnNumberForRegion;
            JVM INSTR monitorenter ;
            parser = PARSER;
            obj = parser;
            if (parser != null) goto _L12; else goto _L11
_L11:
            obj = new com.google.protobuf.GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            PARSER = ((Parser) (obj));
_L12:
            com/google/protos/communication/meetings/proto/RegionConfig$UniversalPstnNumberForRegion;
            JVM INSTR monitorexit ;
            return obj;
            obj;
            com/google/protos/communication/meetings/proto/RegionConfig$UniversalPstnNumberForRegion;
            JVM INSTR monitorexit ;
            throw obj;
_L2:
            return Byte.valueOf((byte)1);
_L3:
            return null;
        }

        static 
        {
            DEFAULT_INSTANCE = new UniversalPstnNumberForRegion();
            UniversalPstnNumberForRegion universalpstnnumberforregion = DEFAULT_INSTANCE;
            GeneratedMessageLite.defaultInstanceMap.put(com/google/protos/communication/meetings/proto/RegionConfig$UniversalPstnNumberForRegion, universalpstnnumberforregion);
        }

        private UniversalPstnNumberForRegion()
        {
            e164_ = "";
        }
    }

    public static final class UniversalPstnNumberForRegion.Availability extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final UniversalPstnNumberForRegion.Availability $VALUES[];
        private static final UniversalPstnNumberForRegion.Availability AVAILABILITY_UNSPECIFIED;
        public static final UniversalPstnNumberForRegion.Availability NONE;
        public static final UniversalPstnNumberForRegion.Availability PSTN_DOGFOOD;
        public static final UniversalPstnNumberForRegion.Availability PSTN_FISHFOOD;
        public static final UniversalPstnNumberForRegion.Availability PUBLIC;
        public static final UniversalPstnNumberForRegion.Availability UNRECOGNIZED;
        private final int value;

        public static UniversalPstnNumberForRegion.Availability forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return AVAILABILITY_UNSPECIFIED;

            case 1: // '\001'
                return NONE;

            case 2: // '\002'
                return PSTN_FISHFOOD;

            case 3: // '\003'
                return PSTN_DOGFOOD;

            case 4: // '\004'
                return PUBLIC;
            }
        }

        public static UniversalPstnNumberForRegion.Availability[] values()
        {
            return (UniversalPstnNumberForRegion.Availability[])$VALUES.clone();
        }

        public final int getNumber()
        {
            if (this == UNRECOGNIZED)
            {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            } else
            {
                return value;
            }
        }

        static 
        {
            AVAILABILITY_UNSPECIFIED = new UniversalPstnNumberForRegion.Availability("AVAILABILITY_UNSPECIFIED", 0, 0);
            NONE = new UniversalPstnNumberForRegion.Availability("NONE", 1, 1);
            PSTN_FISHFOOD = new UniversalPstnNumberForRegion.Availability("PSTN_FISHFOOD", 2, 2);
            PSTN_DOGFOOD = new UniversalPstnNumberForRegion.Availability("PSTN_DOGFOOD", 3, 3);
            PUBLIC = new UniversalPstnNumberForRegion.Availability("PUBLIC", 4, 4);
            UNRECOGNIZED = new UniversalPstnNumberForRegion.Availability("UNRECOGNIZED", 5, -1);
            $VALUES = (new UniversalPstnNumberForRegion.Availability[] {
                AVAILABILITY_UNSPECIFIED, NONE, PSTN_FISHFOOD, PSTN_DOGFOOD, PUBLIC, UNRECOGNIZED
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
                    return UniversalPstnNumberForRegion.Availability.forNumber(i) != null;
                }

                _cls2()
                {
                }
            }

            new _cls2();
        }

        private UniversalPstnNumberForRegion.Availability(String s, int i, int j)
        {
            super(s, i);
            value = j;
        }
    }

    public static final class UniversalPstnNumberForRegion.Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        UniversalPstnNumberForRegion.Builder()
        {
            super(UniversalPstnNumberForRegion.DEFAULT_INSTANCE);
        }
    }

}
