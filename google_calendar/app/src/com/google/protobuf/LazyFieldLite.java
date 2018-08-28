// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;


// Referenced classes of package com.google.protobuf:
//            ExtensionRegistryLite, InvalidProtocolBufferException, ByteString, MessageLite

public class LazyFieldLite
{

    public ByteString delayedBytes;
    public volatile ByteString memoizedBytes;
    public volatile MessageLite value;

    public LazyFieldLite()
    {
    }

    private final void ensureInitialized(MessageLite messagelite)
    {
        if (value != null)
        {
            return;
        }
        this;
        JVM INSTR monitorenter ;
        if (value == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        messagelite;
        this;
        JVM INSTR monitorexit ;
        throw messagelite;
        value = messagelite;
        memoizedBytes = ByteString.EMPTY;
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        InvalidProtocolBufferException invalidprotocolbufferexception;
        invalidprotocolbufferexception;
        value = messagelite;
        memoizedBytes = ByteString.EMPTY;
          goto _L1
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof LazyFieldLite))
        {
            return false;
        }
        obj = (LazyFieldLite)obj;
        MessageLite messagelite = value;
        MessageLite messagelite1 = ((LazyFieldLite) (obj)).value;
        if (messagelite == null && messagelite1 == null)
        {
            return toByteString().equals(((LazyFieldLite) (obj)).toByteString());
        }
        if (messagelite != null && messagelite1 != null)
        {
            return messagelite.equals(messagelite1);
        }
        if (messagelite != null)
        {
            ((LazyFieldLite) (obj)).ensureInitialized(messagelite.getDefaultInstanceForType());
            return messagelite.equals(((LazyFieldLite) (obj)).value);
        } else
        {
            ensureInitialized(messagelite1.getDefaultInstanceForType());
            return value.equals(messagelite1);
        }
    }

    public int hashCode()
    {
        return 1;
    }

    public final ByteString toByteString()
    {
        if (memoizedBytes != null)
        {
            return memoizedBytes;
        }
        this;
        JVM INSTR monitorenter ;
        ByteString bytestring;
        if (memoizedBytes == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        bytestring = memoizedBytes;
        this;
        JVM INSTR monitorexit ;
        return bytestring;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (value != null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        memoizedBytes = ByteString.EMPTY;
_L1:
        exception = memoizedBytes;
        this;
        JVM INSTR monitorexit ;
        return exception;
        memoizedBytes = value.toByteString();
          goto _L1
    }

    static 
    {
        ExtensionRegistryLite.getEmptyRegistry();
    }
}
