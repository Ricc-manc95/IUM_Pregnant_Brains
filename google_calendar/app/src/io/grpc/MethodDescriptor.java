// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.atomic.AtomicReferenceArray;

public final class MethodDescriptor
{

    public final String fullMethodName;
    private final boolean idempotent;
    public final Marshaller requestMarshaller;
    public final Marshaller responseMarshaller;
    public final boolean safe;
    public final boolean sampledToLocalTracing;
    private final Object schemaDescriptor;
    public final MethodType type;

    MethodDescriptor(MethodType methodtype, String s, Marshaller marshaller, Marshaller marshaller1, Object obj, boolean flag, boolean flag1, 
            boolean flag2)
    {
        new AtomicReferenceArray(1);
        if (methodtype == null)
        {
            throw new NullPointerException(String.valueOf("type"));
        }
        type = (MethodType)methodtype;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("fullMethodName"));
        }
        fullMethodName = (String)s;
        if (marshaller == null)
        {
            throw new NullPointerException(String.valueOf("requestMarshaller"));
        }
        requestMarshaller = (Marshaller)marshaller;
        if (marshaller1 == null)
        {
            throw new NullPointerException(String.valueOf("responseMarshaller"));
        }
        responseMarshaller = (Marshaller)marshaller1;
        schemaDescriptor = obj;
        idempotent = flag;
        safe = flag1;
        sampledToLocalTracing = flag2;
        boolean flag3;
        if (!flag1 || methodtype == MethodType.UNARY)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (!flag3)
        {
            throw new IllegalArgumentException(String.valueOf("Only unary methods can be specified safe"));
        } else
        {
            return;
        }
    }

    public static String extractFullServiceName(String s)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("fullMethodName"));
        }
        int i = ((String)s).lastIndexOf('/');
        if (i == -1)
        {
            return null;
        } else
        {
            return s.substring(0, i);
        }
    }

    public static String generateFullMethodName(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("fullServiceName"));
        }
        s = (String)s;
        if (s1 == null)
        {
            throw new NullPointerException(String.valueOf("methodName"));
        } else
        {
            s1 = (String)s1;
            return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append("/").append(s1).toString();
        }
    }

    public final String toString()
    {
        com.google.common.base.MoreObjects.ToStringHelper tostringhelper = (new com.google.common.base.MoreObjects.ToStringHelper(getClass().getSimpleName())).add("fullMethodName", fullMethodName).add("type", type).add("idempotent", idempotent).add("safe", safe).add("sampledToLocalTracing", sampledToLocalTracing).add("requestMarshaller", requestMarshaller).add("responseMarshaller", responseMarshaller).add("schemaDescriptor", schemaDescriptor);
        tostringhelper.omitNullValues = true;
        return tostringhelper.toString();
    }

    private class MethodType extends Enum
    {

        private static final MethodType $VALUES[];
        private static final MethodType BIDI_STREAMING;
        private static final MethodType CLIENT_STREAMING;
        public static final MethodType SERVER_STREAMING;
        public static final MethodType UNARY;
        private static final MethodType UNKNOWN;

        public static MethodType[] values()
        {
            return (MethodType[])$VALUES.clone();
        }

        static 
        {
            UNARY = new MethodType("UNARY", 0);
            CLIENT_STREAMING = new MethodType("CLIENT_STREAMING", 1);
            SERVER_STREAMING = new MethodType("SERVER_STREAMING", 2);
            BIDI_STREAMING = new MethodType("BIDI_STREAMING", 3);
            UNKNOWN = new MethodType("UNKNOWN", 4);
            $VALUES = (new MethodType[] {
                UNARY, CLIENT_STREAMING, SERVER_STREAMING, BIDI_STREAMING, UNKNOWN
            });
        }

        private MethodType(String s, int i)
        {
            super(s, i);
        }
    }


    private class Marshaller
    {

        public abstract Object parse(InputStream inputstream);

        public abstract InputStream stream(Object obj);
    }

}
