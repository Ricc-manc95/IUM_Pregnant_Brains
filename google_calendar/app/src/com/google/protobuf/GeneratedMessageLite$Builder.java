// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.protobuf:
//            GeneratedMessageLite, Protobuf, CodedInputStream, Schema, 
//            CodedInputStreamReader, InvalidProtocolBufferException, UninitializedMessageException, ExtensionRegistryLite, 
//            MessageLite, AbstractMessageLite

public static class isBuilt extends isBuilt
{

    private final GeneratedMessageLite defaultInstance;
    public GeneratedMessageLite instance;
    public boolean isBuilt;

    private final isBuilt mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        copyOnWrite();
        Schema schema;
        GeneratedMessageLite generatedmessagelite;
        schema = Protobuf.INSTANCE.schemaFor(instance.getClass());
        generatedmessagelite = instance;
        if (codedinputstream.wrapper == null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        codedinputstream = codedinputstream.wrapper;
_L1:
        schema.mergeFrom(generatedmessagelite, codedinputstream, extensionregistrylite);
        return this;
        try
        {
            codedinputstream = new CodedInputStreamReader(codedinputstream);
        }
        // Misplaced declaration of an exception variable
        catch (CodedInputStream codedinputstream)
        {
            if (codedinputstream.getCause() instanceof IOException)
            {
                throw (IOException)codedinputstream.getCause();
            } else
            {
                throw codedinputstream;
            }
        }
          goto _L1
    }

    private final  mergeFrom(byte abyte0[], int i, int j)
        throws InvalidProtocolBufferException
    {
        copyOnWrite();
        try
        {
            Protobuf.INSTANCE.schemaFor(instance.getClass()).mergeFrom(instance, abyte0, i, i + j, new >());
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw abyte0;
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw new RuntimeException("Reading from byte array should not throw IOException.", abyte0);
        }
        return this;
    }

    public final GeneratedMessageLite build()
    {
        GeneratedMessageLite generatedmessagelite1 = (GeneratedMessageLite)buildPartial();
        boolean flag1 = Boolean.TRUE.booleanValue();
        byte byte0 = ((Byte)generatedmessagelite1.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.MOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
            flag = Protobuf.INSTANCE.schemaFor(generatedmessagelite1.getClass()).isInitialized(generatedmessagelite1);
            if (flag1)
            {
                int i = android.support.v4.content.MOIZED_IS_INITIALIZED._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                GeneratedMessageLite generatedmessagelite;
                if (flag)
                {
                    generatedmessagelite = generatedmessagelite1;
                } else
                {
                    generatedmessagelite = null;
                }
                generatedmessagelite1.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, generatedmessagelite);
            }
        }
        if (!flag)
        {
            throw new UninitializedMessageException();
        } else
        {
            return generatedmessagelite1;
        }
    }

    public volatile MessageLite build()
    {
        return build();
    }

    public GeneratedMessageLite buildPartial()
    {
        if (isBuilt)
        {
            return instance;
        } else
        {
            instance.makeImmutable();
            isBuilt = true;
            return instance;
        }
    }

    public volatile MessageLite buildPartial()
    {
        return buildPartial();
    }

    public final volatile buildPartial clone()
    {
        return (buildPartial)lone();
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        lone lone = (lone)((GeneratedMessageLite)defaultInstance).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ILDER._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        GeneratedMessageLite generatedmessagelite = (GeneratedMessageLite)buildPartial();
        lone.copyOnWrite();
        GeneratedMessageLite generatedmessagelite1 = lone.instance;
        Protobuf.INSTANCE.schemaFor(generatedmessagelite1.getClass()).mergeFrom(generatedmessagelite1, generatedmessagelite);
        return lone;
    }

    public void copyOnWrite()
    {
        if (isBuilt)
        {
            GeneratedMessageLite generatedmessagelite = (GeneratedMessageLite)instance.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.TABLE_INSTANCE._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            GeneratedMessageLite generatedmessagelite1 = instance;
            Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, generatedmessagelite1);
            instance = generatedmessagelite;
            isBuilt = false;
        }
    }

    public final MessageLite getDefaultInstanceForType()
    {
        return defaultInstance;
    }

    protected final defaultInstance internalMergeFrom(AbstractMessageLite abstractmessagelite)
    {
        abstractmessagelite = (GeneratedMessageLite)abstractmessagelite;
        copyOnWrite();
        GeneratedMessageLite generatedmessagelite = instance;
        Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, abstractmessagelite);
        return this;
    }

    public final boolean isInitialized()
    {
        return GeneratedMessageLite.isInitialized(instance, false);
    }

    public final volatile instance mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws IOException
    {
        return mergeFrom(codedinputstream, extensionregistrylite);
    }

    public final volatile mergeFrom mergeFrom(byte abyte0[], int i, int j)
        throws InvalidProtocolBufferException
    {
        return mergeFrom(abyte0, 0, j);
    }

    public final mergeFrom mergeFrom(GeneratedMessageLite generatedmessagelite)
    {
        copyOnWrite();
        GeneratedMessageLite generatedmessagelite1 = instance;
        Protobuf.INSTANCE.schemaFor(generatedmessagelite1.getClass()).mergeFrom(generatedmessagelite1, generatedmessagelite);
        return this;
    }

    public n(GeneratedMessageLite generatedmessagelite)
    {
        defaultInstance = generatedmessagelite;
        instance = (GeneratedMessageLite)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.TABLE_INSTANCE._fld9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        isBuilt = false;
    }
}
