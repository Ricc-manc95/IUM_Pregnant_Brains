// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.protobuf.lite;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ProtoLiteUtils
{

    public static volatile ExtensionRegistryLite globalRegistry = ExtensionRegistryLite.getEmptyRegistry();

    static long copy(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        if (inputstream == null)
        {
            throw new NullPointerException();
        }
        if (outputstream == null)
        {
            throw new NullPointerException();
        }
        byte abyte0[] = new byte[8192];
        long l = 0L;
        do
        {
            int i = inputstream.read(abyte0);
            if (i != -1)
            {
                outputstream.write(abyte0, 0, i);
                l += i;
            } else
            {
                return l;
            }
        } while (true);
    }

    public static io.grpc.MethodDescriptor.Marshaller marshaller(MessageLite messagelite)
    {
        return new MessageMarshaller(messagelite);
    }


    private class MessageMarshaller
        implements io.grpc.MethodDescriptor.Marshaller
    {

        private static final ThreadLocal bufs = new ThreadLocal();
        private final MessageLite defaultInstance;
        private final Parser parser;

        private final MessageLite parse(InputStream inputstream)
        {
            if (!(inputstream instanceof ProtoInputStream) || ((ProtoInputStream)inputstream).parser != parser) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = (ProtoInputStream)inputstream;
            if (((ProtoInputStream) (obj)).message == null)
            {
                throw new IllegalStateException("message not available");
            }
              goto _L3
            obj;
_L2:
            Object obj1;
            obj1 = null;
            obj = obj1;
            if (!(inputstream instanceof KnownLength)) goto _L5; else goto _L4
_L4:
            int j = inputstream.available();
            if (j <= 0 || j > 0x400000) goto _L7; else goto _L6
_L6:
            int i;
            int k;
            try
            {
                obj = (Reference)bufs.get();
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                throw new RuntimeException(inputstream);
            }
            if (obj == null) goto _L9; else goto _L8
_L8:
            obj1 = (byte[])((Reference) (obj)).get();
            if (obj1 == null) goto _L9; else goto _L10
_L10:
            obj = obj1;
            if (obj1.length >= j) goto _L11; else goto _L9
_L9:
            obj = new byte[j];
            bufs.set(new WeakReference(obj));
              goto _L11
_L16:
            if (i <= 0) goto _L13; else goto _L12
_L12:
            k = inputstream.read(((byte []) (obj)), j - i, i);
            if (k != -1)
            {
                i -= k;
                continue; /* Loop/switch isn't completed */
            }
              goto _L13
_L3:
            obj = ((ProtoInputStream) (obj)).message;
            return ((MessageLite) (obj));
_L13:
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_230;
            }
            throw new RuntimeException((new StringBuilder(43)).append("size inaccurate: ").append(j).append(" != ").append(j - i).toString());
            obj = CodedInputStream.newInstance(((byte []) (obj)), 0, j, false);
_L5:
            obj1 = obj;
            if (obj == null)
            {
                obj1 = CodedInputStream.newInstance(inputstream);
            }
            i = ((CodedInputStream) (obj1)).sizeLimit;
            obj1.sizeLimit = 0x7fffffff;
            try
            {
                inputstream = parseFrom(((CodedInputStream) (obj1)));
            }
            // Misplaced declaration of an exception variable
            catch (InputStream inputstream)
            {
                throw new StatusRuntimeException(Status.INTERNAL.withDescription("Invalid protobuf byte sequence").withCause(inputstream));
            }
            return inputstream;
_L7:
            obj = obj1;
            if (j != 0) goto _L5; else goto _L14
_L14:
            inputstream = defaultInstance;
            return inputstream;
_L11:
            i = j;
            if (true) goto _L16; else goto _L15
_L15:
        }

        private final MessageLite parseFrom(CodedInputStream codedinputstream)
            throws InvalidProtocolBufferException
        {
            MessageLite messagelite = (MessageLite)parser.parseFrom(codedinputstream, ProtoLiteUtils.globalRegistry);
            try
            {
                codedinputstream.checkLastTagWas(0);
            }
            // Misplaced declaration of an exception variable
            catch (CodedInputStream codedinputstream)
            {
                throw codedinputstream;
            }
            return messagelite;
        }

        public final volatile Object parse(InputStream inputstream)
        {
            return parse(inputstream);
        }

        public final InputStream stream(Object obj)
        {
            return new ProtoInputStream((MessageLite)obj, parser);
        }


        MessageMarshaller(MessageLite messagelite)
        {
            defaultInstance = messagelite;
            parser = messagelite.getParserForType();
        }
    }

}
