// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


// Referenced classes of package io.opencensus.trace:
//            BaseMessageEvent

public abstract class MessageEvent extends BaseMessageEvent
{

    MessageEvent()
    {
    }

    public static Builder builder(Type type, long l)
    {
        AutoValue_MessageEvent.Builder builder1 = new AutoValue_MessageEvent.Builder();
        if (type == null)
        {
            throw new NullPointerException("type");
        } else
        {
            return builder1.setType((Type)type).setMessageId(l).setUncompressedMessageSize(0L).setCompressedMessageSize(0L);
        }
    }

    public abstract long getCompressedMessageSize();

    public abstract long getMessageId();

    public abstract Type getType();

    public abstract long getUncompressedMessageSize();

    private class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type RECEIVED;
        public static final Type SENT;

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            SENT = new Type("SENT", 0);
            RECEIVED = new Type("RECEIVED", 1);
            $VALUES = (new Type[] {
                SENT, RECEIVED
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    private class Builder
    {

        public abstract MessageEvent build();

        public abstract Builder setCompressedMessageSize(long l);

        public abstract Builder setMessageId(long l);

        public abstract Builder setType(Type type);

        public abstract Builder setUncompressedMessageSize(long l);

        Builder()
        {
        }
    }

}
