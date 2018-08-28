// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Method;

// Referenced classes of package com.google.protobuf:
//            SchemaFactory, MessageInfoFactory, GeneratedMessageInfoFactory, Internal, 
//            SchemaUtil, MessageInfo, GeneratedMessageLite, MessageSetSchema, 
//            ExtensionSchemas, NewInstanceSchemas, ListFieldSchema, MapFieldSchemas, 
//            MessageSchema, Schema

final class ManifestSchemaFactory
    implements SchemaFactory
{

    private static final MessageInfoFactory EMPTY_FACTORY = new _cls1();
    private final MessageInfoFactory messageInfoFactory;

    public ManifestSchemaFactory()
    {
        this(((MessageInfoFactory) (new CompositeMessageInfoFactory(new MessageInfoFactory[] {
            GeneratedMessageInfoFactory.instance, getDescriptorMessageInfoFactory()
        }))));
    }

    private ManifestSchemaFactory(MessageInfoFactory messageinfofactory)
    {
        messageInfoFactory = (MessageInfoFactory)Internal.checkNotNull(messageinfofactory, "messageInfoFactory");
    }

    private static MessageInfoFactory getDescriptorMessageInfoFactory()
    {
        MessageInfoFactory messageinfofactory;
        try
        {
            messageinfofactory = (MessageInfoFactory)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        }
        catch (Exception exception)
        {
            return EMPTY_FACTORY;
        }
        return messageinfofactory;
    }

    public final Schema createSchema(Class class1)
    {
        SchemaUtil.requireGeneratedMessage(class1);
        MessageInfo messageinfo = messageInfoFactory.messageInfoFor(class1);
        if (messageinfo.isMessageSetWireFormat())
        {
            if (com/google/protobuf/GeneratedMessageLite.isAssignableFrom(class1))
            {
                return new MessageSetSchema(SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, ExtensionSchemas.LITE_SCHEMA, messageinfo.getDefaultInstance());
            }
            class1 = SchemaUtil.PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
            if (ExtensionSchemas.FULL_SCHEMA == null)
            {
                throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
            } else
            {
                return new MessageSetSchema(class1, ExtensionSchemas.FULL_SCHEMA, messageinfo.getDefaultInstance());
            }
        }
        if (com/google/protobuf/GeneratedMessageLite.isAssignableFrom(class1))
        {
            if (messageinfo.getSyntax$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UK3IDTQ6UKRPDPQ62U1R0() == android.support.v4.content.ModernAsyncTask.Status.PROTO2$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0)
            {
                return MessageSchema.newSchema$5166KOBMC4NMOOBECSNK6R31EDPJMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQDCLPN6OB7CL4MSPJF7D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKSPBN95N76T31DPHMAKR3D1IMQO9R9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T66ISRK8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULBEDDN6UTRE8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UHBOEHIMSSR9DTN56OR8CLMM2EQCCDNMQBR7DTNMER355TO74RRKDTH7APHF9LGN0HJ9CLM68KR3D1IMQO9R55666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQPBJEDGMEPAJCDK6ARB17C______0(messageinfo, NewInstanceSchemas.LITE_SCHEMA, ListFieldSchema.LITE_INSTANCE, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, ExtensionSchemas.LITE_SCHEMA, MapFieldSchemas.LITE_SCHEMA);
            } else
            {
                return MessageSchema.newSchema$5166KOBMC4NMOOBECSNK6R31EDPJMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQDCLPN6OB7CL4MSPJF7D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKSPBN95N76T31DPHMAKR3D1IMQO9R9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T66ISRK8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULBEDDN6UTRE8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UHBOEHIMSSR9DTN56OR8CLMM2EQCCDNMQBR7DTNMER355TO74RRKDTH7APHF9LGN0HJ9CLM68KR3D1IMQO9R55666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQPBJEDGMEPAJCDK6ARB17C______0(messageinfo, NewInstanceSchemas.LITE_SCHEMA, ListFieldSchema.LITE_INSTANCE, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA, null, MapFieldSchemas.LITE_SCHEMA);
            }
        }
        if (messageinfo.getSyntax$50KKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UK3IDTQ6UKRPDPQ62U1R0() == android.support.v4.content.ModernAsyncTask.Status.PROTO2$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T874RRKDT9NIRJKC5S3M___0)
        {
            class1 = NewInstanceSchemas.FULL_SCHEMA;
            ListFieldSchema listfieldschema = ListFieldSchema.FULL_INSTANCE;
            UnknownFieldSchema unknownfieldschema = SchemaUtil.PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
            if (ExtensionSchemas.FULL_SCHEMA == null)
            {
                throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
            } else
            {
                return MessageSchema.newSchema$5166KOBMC4NMOOBECSNK6R31EDPJMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQDCLPN6OB7CL4MSPJF7D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKSPBN95N76T31DPHMAKR3D1IMQO9R9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T66ISRK8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULBEDDN6UTRE8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UHBOEHIMSSR9DTN56OR8CLMM2EQCCDNMQBR7DTNMER355TO74RRKDTH7APHF9LGN0HJ9CLM68KR3D1IMQO9R55666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQPBJEDGMEPAJCDK6ARB17C______0(messageinfo, class1, listfieldschema, unknownfieldschema, ExtensionSchemas.FULL_SCHEMA, MapFieldSchemas.FULL_SCHEMA);
            }
        } else
        {
            return MessageSchema.newSchema$5166KOBMC4NMOOBECSNK6R31EDPJMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQDCLPN6OB7CL4MSPJF7D666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKSPBN95N76T31DPHMAKR3D1IMQO9R9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T66ISRK8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2ULBEDDN6UTRE8PKMAR34ADHMGPBDC4TKOORFDKNMERRFCTM6ABRGE9NN8RR2ELJ2UHBOEHIMSSR9DTN56OR8CLMM2EQCCDNMQBR7DTNMER355TO74RRKDTH7APHF9LGN0HJ9CLM68KR3D1IMQO9R55666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKQPBJEDGMEPAJCDK6ARB17C______0(messageinfo, NewInstanceSchemas.FULL_SCHEMA, ListFieldSchema.FULL_INSTANCE, SchemaUtil.PROTO3_UNKNOWN_FIELD_SET_SCHEMA, null, MapFieldSchemas.FULL_SCHEMA);
        }
    }


    private class CompositeMessageInfoFactory
        implements MessageInfoFactory
    {

        private MessageInfoFactory factories[];

        public final boolean isSupported(Class class1)
        {
            boolean flag1 = false;
            MessageInfoFactory amessageinfofactory[] = factories;
            int j = amessageinfofactory.length;
            int i = 0;
            do
            {
label0:
                {
                    boolean flag = flag1;
                    if (i < j)
                    {
                        if (!amessageinfofactory[i].isSupported(class1))
                        {
                            break label0;
                        }
                        flag = true;
                    }
                    return flag;
                }
                i++;
            } while (true);
        }

        public final MessageInfo messageInfoFor(Class class1)
        {
            MessageInfoFactory amessageinfofactory[] = factories;
            int j = amessageinfofactory.length;
            for (int i = 0; i < j; i++)
            {
                MessageInfoFactory messageinfofactory = amessageinfofactory[i];
                if (messageinfofactory.isSupported(class1))
                {
                    return messageinfofactory.messageInfoFor(class1);
                }
            }

            class1 = String.valueOf(class1.getName());
            if (class1.length() != 0)
            {
                class1 = "No factory is available for message type: ".concat(class1);
            } else
            {
                class1 = new String("No factory is available for message type: ");
            }
            throw new UnsupportedOperationException(class1);
        }

        transient CompositeMessageInfoFactory(MessageInfoFactory amessageinfofactory[])
        {
            factories = amessageinfofactory;
        }
    }


    private class _cls1
        implements MessageInfoFactory
    {

        public final boolean isSupported(Class class1)
        {
            return false;
        }

        public final MessageInfo messageInfoFor(Class class1)
        {
            throw new IllegalStateException("This should never be called.");
        }

        _cls1()
        {
        }
    }

}
