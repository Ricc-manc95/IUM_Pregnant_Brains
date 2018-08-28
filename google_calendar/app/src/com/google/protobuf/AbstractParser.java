// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            Parser, ExtensionRegistryLite, InvalidProtocolBufferException, MessageLite, 
//            AbstractMessageLite, UninitializedMessageException, AbstractMutableMessageLite, CodedInputStream

public abstract class AbstractParser
    implements Parser
{

    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    public AbstractParser()
    {
    }

    private final MessageLite checkMessageInitialized(MessageLite messagelite)
        throws InvalidProtocolBufferException
    {
        if (messagelite != null && !messagelite.isInitialized())
        {
            if (messagelite instanceof AbstractMessageLite)
            {
                messagelite = (AbstractMessageLite)messagelite;
                messagelite = new UninitializedMessageException();
            } else
            if (messagelite instanceof AbstractMutableMessageLite)
            {
                messagelite = (AbstractMutableMessageLite)messagelite;
                messagelite = new UninitializedMessageException();
            } else
            {
                messagelite = new UninitializedMessageException();
            }
            messagelite = new InvalidProtocolBufferException(messagelite.getMessage());
            if (messagelite == null)
            {
                throw null;
            } else
            {
                throw messagelite;
            }
        } else
        {
            return messagelite;
        }
    }

    private final MessageLite parsePartialFrom(byte abyte0[], int i, int j, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException
    {
        try
        {
            abyte0 = CodedInputStream.newInstance(abyte0, i, j, false);
            extensionregistrylite = (MessageLite)parsePartialFrom(((CodedInputStream) (abyte0)), extensionregistrylite);
        }
        // Misplaced declaration of an exception variable
        catch (byte abyte0[])
        {
            throw abyte0;
        }
        abyte0.checkLastTagWas(0);
        return extensionregistrylite;
        abyte0;
        throw abyte0;
    }

    public final Object parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        throws InvalidProtocolBufferException
    {
        return checkMessageInitialized((MessageLite)parsePartialFrom(codedinputstream, extensionregistrylite));
    }

    public final Object parseFrom(byte abyte0[])
        throws InvalidProtocolBufferException
    {
        ExtensionRegistryLite extensionregistrylite = EMPTY_REGISTRY;
        return (MessageLite)checkMessageInitialized(parsePartialFrom(abyte0, 0, abyte0.length, extensionregistrylite));
    }

}
