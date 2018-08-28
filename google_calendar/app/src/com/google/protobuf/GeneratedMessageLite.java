// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.google.protobuf:
//            AbstractMessageLite, UnknownFieldSetLite, ProtobufArrayList, Protobuf, 
//            Schema, InvalidProtocolBufferException, UninitializedMessageException, CodedInputStream, 
//            CodedInputStreamReader, Parser, MessageLiteToString, CodedOutputStream, 
//            CodedOutputStreamWriter, ExtensionLite, MessageLite, ExtensionRegistryLite, 
//            AbstractParser, MessageLiteOrBuilder, FieldSet, SmallSortedMap, 
//            MutableMessageLite

public abstract class GeneratedMessageLite extends AbstractMessageLite
{
    public static class Builder extends AbstractMessageLite.Builder
    {

        private final GeneratedMessageLite defaultInstance;
        public GeneratedMessageLite instance;
        public boolean isBuilt;

        private final Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
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

        private final Builder mergeFrom(byte abyte0[], int i, int j)
            throws InvalidProtocolBufferException
        {
            copyOnWrite();
            try
            {
                Protobuf.INSTANCE.schemaFor(instance.getClass()).mergeFrom(instance, abyte0, i, i + j, new ArrayDecoders.Registers());
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
            byte byte0 = ((Byte)generatedmessagelite1.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
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

        public final volatile AbstractMessageLite.Builder clone()
        {
            return (Builder)clone();
        }

        public Object clone()
            throws CloneNotSupportedException
        {
            Builder builder = (Builder)((GeneratedMessageLite)defaultInstance).dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            GeneratedMessageLite generatedmessagelite = (GeneratedMessageLite)buildPartial();
            builder.copyOnWrite();
            GeneratedMessageLite generatedmessagelite1 = builder.instance;
            Protobuf.INSTANCE.schemaFor(generatedmessagelite1.getClass()).mergeFrom(generatedmessagelite1, generatedmessagelite);
            return builder;
        }

        public void copyOnWrite()
        {
            if (isBuilt)
            {
                GeneratedMessageLite generatedmessagelite = (GeneratedMessageLite)instance.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_MUTABLE_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
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

        protected final AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractmessagelite)
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

        public final volatile AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public final volatile AbstractMessageLite.Builder mergeFrom(byte abyte0[], int i, int j)
            throws InvalidProtocolBufferException
        {
            return mergeFrom(abyte0, 0, j);
        }

        public final Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            copyOnWrite();
            GeneratedMessageLite generatedmessagelite1 = instance;
            Protobuf.INSTANCE.schemaFor(generatedmessagelite1.getClass()).mergeFrom(generatedmessagelite1, generatedmessagelite);
            return this;
        }

        public Builder(GeneratedMessageLite generatedmessagelite)
        {
            defaultInstance = generatedmessagelite;
            instance = (GeneratedMessageLite)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_MUTABLE_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            isBuilt = false;
        }
    }

    public static final class DefaultInstanceBasedParser extends AbstractParser
    {

        private final GeneratedMessageLite defaultInstance;

        public final Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return GeneratedMessageLite.parsePartialFrom(defaultInstance, codedinputstream, extensionregistrylite);
        }

        public DefaultInstanceBasedParser(GeneratedMessageLite generatedmessagelite)
        {
            defaultInstance = generatedmessagelite;
        }
    }

    public static class ExtendableBuilder extends Builder
        implements MessageLiteOrBuilder
    {

        public volatile GeneratedMessageLite buildPartial()
        {
            return (ExtendableMessage)buildPartial();
        }

        public MessageLite buildPartial()
        {
            if (isBuilt)
            {
                return (ExtendableMessage)instance;
            }
            FieldSet fieldset = ((ExtendableMessage)instance).extensions;
            if (!fieldset.isImmutable)
            {
                fieldset.fields.makeImmutable();
                fieldset.isImmutable = true;
            }
            return (ExtendableMessage)super.buildPartial();
        }

        protected final void copyOnWrite()
        {
            if (!isBuilt)
            {
                return;
            } else
            {
                super.copyOnWrite();
                ((ExtendableMessage)instance).extensions = (FieldSet)((ExtendableMessage)instance).extensions.clone();
                return;
            }
        }

        public ExtendableBuilder(ExtendableMessage extendablemessage)
        {
            super(extendablemessage);
        }
    }

    public static abstract class ExtendableMessage extends GeneratedMessageLite
        implements MessageLiteOrBuilder
    {

        public FieldSet extensions;

        public ExtendableMessage()
        {
            extensions = FieldSet.DEFAULT_INSTANCE;
        }
    }

    public static final class ExtensionDescriptor
        implements FieldSet.FieldDescriptorLite
    {

        public final Internal.EnumLiteMap enumTypeMap;
        public final boolean isPacked = false;
        public final boolean isRepeated = false;
        public final int number;
        public final WireFormat.FieldType type;

        public final int compareTo(Object obj)
        {
            obj = (ExtensionDescriptor)obj;
            return number - ((ExtensionDescriptor) (obj)).number;
        }

        public final WireFormat.JavaType getLiteJavaType()
        {
            return type.javaType;
        }

        public final WireFormat.FieldType getLiteType()
        {
            return type;
        }

        public final int getNumber()
        {
            return number;
        }

        public final MessageLite.Builder internalMergeFrom(MessageLite.Builder builder, MessageLite messagelite)
        {
            return ((Builder)builder).mergeFrom((GeneratedMessageLite)messagelite);
        }

        public final MutableMessageLite internalMergeFrom$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQTBKC5H6OPADCLPN6OB7CL66IT357D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQTBKC5H6OPADCLPN6OB7CL66IT357CKKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UJBLEHGM4R359LIN6SR1CTIKOQBKCKTG____0()
        {
            throw new UnsupportedOperationException();
        }

        public final boolean isPacked()
        {
            return isPacked;
        }

        public final boolean isRepeated()
        {
            return isRepeated;
        }

        ExtensionDescriptor(Internal.EnumLiteMap enumlitemap, int i, WireFormat.FieldType fieldtype, boolean flag, boolean flag1)
        {
            enumTypeMap = enumlitemap;
            number = i;
            type = fieldtype;
        }
    }

    public static final class GeneratedExtension extends ExtensionLite
    {

        public final MessageLite containingTypeDefaultInstance;
        public final Object defaultValue;
        public final ExtensionDescriptor descriptor;
        public final MessageLite messageDefaultInstance;

        public final Object singularFromFieldSetType(Object obj)
        {
            Object obj1 = obj;
            if (descriptor.type.javaType == WireFormat.JavaType.ENUM)
            {
                obj1 = descriptor.enumTypeMap.findValueByNumber(((Integer)obj).intValue());
            }
            return obj1;
        }

        GeneratedExtension(MessageLite messagelite, Object obj, MessageLite messagelite1, ExtensionDescriptor extensiondescriptor)
        {
            if (messagelite == null)
            {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if (extensiondescriptor.type == WireFormat.FieldType.MESSAGE && messagelite1 == null)
            {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else
            {
                containingTypeDefaultInstance = messagelite;
                defaultValue = obj;
                messageDefaultInstance = messagelite1;
                descriptor = extensiondescriptor;
                return;
            }
        }
    }


    public static Map defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize;
    public UnknownFieldSetLite unknownFields;

    public GeneratedMessageLite()
    {
        unknownFields = UnknownFieldSetLite.DEFAULT_INSTANCE;
        memoizedSerializedSize = -1;
    }

    public static GeneratedExtension checkIsLite(ExtensionLite extensionlite)
    {
        return (GeneratedExtension)extensionlite;
    }

    public static Internal.ProtobufList emptyProtobufList()
    {
        return ProtobufArrayList.EMPTY_LIST;
    }

    static GeneratedMessageLite getDefaultInstance(Class class1)
    {
        GeneratedMessageLite generatedmessagelite1 = (GeneratedMessageLite)defaultInstanceMap.get(class1);
        GeneratedMessageLite generatedmessagelite = generatedmessagelite1;
        if (generatedmessagelite1 == null)
        {
            try
            {
                Class.forName(class1.getName(), true, class1.getClassLoader());
            }
            // Misplaced declaration of an exception variable
            catch (Class class1)
            {
                throw new IllegalStateException("Class initialization cannot fail.", class1);
            }
            generatedmessagelite = (GeneratedMessageLite)defaultInstanceMap.get(class1);
        }
        if (generatedmessagelite == null)
        {
            class1 = String.valueOf(class1.getName());
            if (class1.length() != 0)
            {
                class1 = "Unable to get default instance for: ".concat(class1);
            } else
            {
                class1 = new String("Unable to get default instance for: ");
            }
            throw new IllegalStateException(class1);
        } else
        {
            return generatedmessagelite;
        }
    }

    static transient Object invokeOrDie(Method method, Object obj, Object aobj[])
    {
        try
        {
            method = ((Method) (method.invoke(obj, aobj)));
        }
        // Misplaced declaration of an exception variable
        catch (Method method)
        {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", method);
        }
        // Misplaced declaration of an exception variable
        catch (Method method)
        {
            method = method.getCause();
            if (method instanceof RuntimeException)
            {
                throw (RuntimeException)method;
            }
            if (method instanceof Error)
            {
                throw (Error)method;
            } else
            {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", method);
            }
        }
        return method;
    }

    protected static final boolean isInitialized(GeneratedMessageLite generatedmessagelite, boolean flag)
    {
        byte byte0 = ((Byte)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
        if (byte0 == 1)
        {
            return true;
        }
        if (byte0 == 0)
        {
            return false;
        }
        boolean flag1 = Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).isInitialized(generatedmessagelite);
        if (flag)
        {
            int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
            GeneratedMessageLite generatedmessagelite1;
            if (flag1)
            {
                generatedmessagelite1 = generatedmessagelite;
            } else
            {
                generatedmessagelite1 = null;
            }
            generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, generatedmessagelite1);
        }
        return flag1;
    }

    public static Internal.IntList mutableCopy(Internal.IntList intlist)
    {
        int i = intlist.size();
        if (i == 0)
        {
            i = 10;
        } else
        {
            i <<= 1;
        }
        return intlist.mutableCopyWithCapacity(i);
    }

    public static Internal.LongList mutableCopy(Internal.LongList longlist)
    {
        int i = longlist.size();
        if (i == 0)
        {
            i = 10;
        } else
        {
            i <<= 1;
        }
        return longlist.mutableCopyWithCapacity(i);
    }

    public static Internal.ProtobufList mutableCopy(Internal.ProtobufList protobuflist)
    {
        int i = protobuflist.size();
        if (i == 0)
        {
            i = 10;
        } else
        {
            i <<= 1;
        }
        return protobuflist.mutableCopyWithCapacity(i);
    }

    public static GeneratedExtension newSingularGeneratedExtension(MessageLite messagelite, Object obj, MessageLite messagelite1, Internal.EnumLiteMap enumlitemap, int i, WireFormat.FieldType fieldtype, Class class1)
    {
        return new GeneratedExtension(messagelite, obj, messagelite1, new ExtensionDescriptor(null, 1000, fieldtype, false, false));
    }

    public static GeneratedMessageLite parseFrom(GeneratedMessageLite generatedmessagelite, byte abyte0[])
        throws InvalidProtocolBufferException
    {
        abyte0 = parsePartialFrom(generatedmessagelite, abyte0);
        if (abyte0 != null)
        {
            boolean flag1 = Boolean.TRUE.booleanValue();
            byte byte0 = ((Byte)abyte0.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
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
                flag = Protobuf.INSTANCE.schemaFor(abyte0.getClass()).isInitialized(abyte0);
                if (flag1)
                {
                    int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
                    if (flag)
                    {
                        generatedmessagelite = abyte0;
                    } else
                    {
                        generatedmessagelite = null;
                    }
                    abyte0.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, generatedmessagelite);
                }
            }
            if (!flag)
            {
                generatedmessagelite = new InvalidProtocolBufferException((new UninitializedMessageException()).getMessage());
                if (generatedmessagelite == null)
                {
                    throw null;
                } else
                {
                    throw generatedmessagelite;
                }
            }
        }
        return abyte0;
    }

    public static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite generatedmessagelite, CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException
    {
        GeneratedMessageLite generatedmessagelite1 = (GeneratedMessageLite)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_MUTABLE_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Schema schema;
        schema = Protobuf.INSTANCE.schemaFor(generatedmessagelite1.getClass());
        if (codedinputstream.wrapper == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        generatedmessagelite = codedinputstream.wrapper;
_L1:
        schema.mergeFrom(generatedmessagelite1, generatedmessagelite, extensionregistrylite);
        generatedmessagelite1.makeImmutable();
        return generatedmessagelite1;
        try
        {
            generatedmessagelite = new CodedInputStreamReader(codedinputstream);
        }
        // Misplaced declaration of an exception variable
        catch (GeneratedMessageLite generatedmessagelite)
        {
            if (generatedmessagelite.getCause() instanceof InvalidProtocolBufferException)
            {
                throw (InvalidProtocolBufferException)generatedmessagelite.getCause();
            } else
            {
                throw new InvalidProtocolBufferException(generatedmessagelite.getMessage());
            }
        }
        // Misplaced declaration of an exception variable
        catch (GeneratedMessageLite generatedmessagelite)
        {
            if (generatedmessagelite.getCause() instanceof InvalidProtocolBufferException)
            {
                throw (InvalidProtocolBufferException)generatedmessagelite.getCause();
            } else
            {
                throw generatedmessagelite;
            }
        }
          goto _L1
    }

    public static GeneratedMessageLite parsePartialFrom(GeneratedMessageLite generatedmessagelite, byte abyte0[])
        throws InvalidProtocolBufferException
    {
        generatedmessagelite = (GeneratedMessageLite)generatedmessagelite.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_MUTABLE_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        try
        {
            Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, abyte0, 0, abyte0.length, new ArrayDecoders.Registers());
            generatedmessagelite.makeImmutable();
            if (generatedmessagelite.memoizedHashCode != 0)
            {
                throw new RuntimeException();
            }
        }
        // Misplaced declaration of an exception variable
        catch (GeneratedMessageLite generatedmessagelite)
        {
            if (generatedmessagelite.getCause() instanceof InvalidProtocolBufferException)
            {
                throw (InvalidProtocolBufferException)generatedmessagelite.getCause();
            } else
            {
                throw new InvalidProtocolBufferException(generatedmessagelite.getMessage());
            }
        }
        // Misplaced declaration of an exception variable
        catch (GeneratedMessageLite generatedmessagelite)
        {
            generatedmessagelite = new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            if (generatedmessagelite == null)
            {
                throw null;
            } else
            {
                throw generatedmessagelite;
            }
        }
        return generatedmessagelite;
    }

    public abstract Object dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(int i, Object obj);

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!((GeneratedMessageLite)dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_DEFAULT_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).getClass().isInstance(obj))
        {
            return false;
        } else
        {
            return Protobuf.INSTANCE.schemaFor(getClass()).equals(this, (GeneratedMessageLite)obj);
        }
    }

    public final MessageLite getDefaultInstanceForType()
    {
        return (GeneratedMessageLite)dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_DEFAULT_INSTANCE$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
    }

    final int getMemoizedSerializedSize()
    {
        return memoizedSerializedSize;
    }

    public final Parser getParserForType()
    {
        return (Parser)dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_PARSER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
    }

    public final int getSerializedSize()
    {
        if (memoizedSerializedSize == -1)
        {
            memoizedSerializedSize = Protobuf.INSTANCE.schemaFor(getClass()).getSerializedSize(this);
        }
        return memoizedSerializedSize;
    }

    public int hashCode()
    {
        if (memoizedHashCode != 0)
        {
            return memoizedHashCode;
        } else
        {
            memoizedHashCode = Protobuf.INSTANCE.schemaFor(getClass()).hashCode(this);
            return memoizedHashCode;
        }
    }

    public final boolean isInitialized()
    {
        boolean flag = Boolean.TRUE.booleanValue();
        byte byte0 = ((Byte)dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.GET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null)).byteValue();
        if (byte0 == 1)
        {
            return true;
        }
        if (byte0 == 0)
        {
            return false;
        }
        boolean flag1 = Protobuf.INSTANCE.schemaFor(getClass()).isInitialized(this);
        if (flag)
        {
            int i = android.support.v4.content.ModernAsyncTask.Status.SET_MEMOIZED_IS_INITIALIZED$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0;
            GeneratedMessageLite generatedmessagelite;
            if (flag1)
            {
                generatedmessagelite = this;
            } else
            {
                generatedmessagelite = null;
            }
            dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(i, generatedmessagelite);
        }
        return flag1;
    }

    protected void makeImmutable()
    {
        Protobuf.INSTANCE.schemaFor(getClass()).makeImmutable(this);
    }

    public final MessageLite.Builder newBuilderForType()
    {
        return (Builder)dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
    }

    final void setMemoizedSerializedSize(int i)
    {
        memoizedSerializedSize = i;
    }

    public final MessageLite.Builder toBuilder()
    {
        Builder builder = (Builder)dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        builder.copyOnWrite();
        GeneratedMessageLite generatedmessagelite = builder.instance;
        Protobuf.INSTANCE.schemaFor(generatedmessagelite.getClass()).mergeFrom(generatedmessagelite, this);
        return builder;
    }

    public String toString()
    {
        String s = super.toString();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("# ").append(s);
        MessageLiteToString.reflectivePrintWithIndent(this, stringbuilder, 0);
        return stringbuilder.toString();
    }

    public final void writeTo(CodedOutputStream codedoutputstream)
        throws IOException
    {
        Schema schema = Protobuf.INSTANCE.schemaFor(getClass());
        if (codedoutputstream.wrapper != null)
        {
            codedoutputstream = codedoutputstream.wrapper;
        } else
        {
            codedoutputstream = new CodedOutputStreamWriter(codedoutputstream);
        }
        schema.writeTo(this, codedoutputstream);
    }

}
