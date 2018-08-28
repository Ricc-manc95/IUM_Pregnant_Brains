// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import com.google.common.base.Charsets;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.Status;
import java.nio.charset.Charset;

// Referenced classes of package io.grpc.internal:
//            GrpcUtil, StatsTraceContext, TransportTracer

public abstract class Http2ClientStreamTransportState extends AbstractClientStream.TransportState
{

    public static final io.grpc.Metadata.Key HTTP2_STATUS;
    private static final io.grpc.InternalMetadata.TrustedAsciiMarshaller HTTP_STATUS_MARSHALLER;
    public Charset errorCharset;
    public boolean headersReceived;
    public Status transportError;
    public Metadata transportErrorMetadata;

    public Http2ClientStreamTransportState(int i, StatsTraceContext statstracecontext, TransportTracer transporttracer)
    {
        super(i, statstracecontext, transporttracer);
        errorCharset = Charsets.UTF_8;
    }

    public static Charset extractCharset(Metadata metadata)
    {
        metadata = (String)metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (metadata == null)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        metadata = metadata.split("charset=", 2);
        metadata = Charset.forName(metadata[metadata.length - 1].trim());
        return metadata;
        metadata;
        return Charsets.UTF_8;
    }

    public static Status validateInitialMetadata(Metadata metadata)
    {
        Object obj = (Integer)metadata.get(HTTP2_STATUS);
        if (obj == null)
        {
            return Status.INTERNAL.withDescription("Missing HTTP status code");
        }
        metadata = (String)metadata.get(GrpcUtil.CONTENT_TYPE_KEY);
        if (!GrpcUtil.isGrpcContentType(metadata))
        {
            obj = GrpcUtil.httpStatusToGrpcStatus(((Integer) (obj)).intValue());
            metadata = String.valueOf(metadata);
            if (metadata.length() != 0)
            {
                metadata = "invalid content-type: ".concat(metadata);
            } else
            {
                metadata = new String("invalid content-type: ");
            }
            return ((Status) (obj)).augmentDescription(metadata);
        } else
        {
            return null;
        }
    }

    public abstract void http2ProcessingFailed(Status status, boolean flag, Metadata metadata);

    static 
    {
        HTTP_STATUS_MARSHALLER = new _cls1();
        HTTP2_STATUS = InternalMetadata.keyOf(":status", HTTP_STATUS_MARSHALLER);
    }

    private class _cls1
        implements io.grpc.InternalMetadata.TrustedAsciiMarshaller
    {

        public final Object parseAsciiString(byte abyte0[])
        {
            if (abyte0.length >= 3)
            {
                return Integer.valueOf((abyte0[0] - 48) * 100 + (abyte0[1] - 48) * 10 + (abyte0[2] - 48));
            }
            abyte0 = String.valueOf(new String(abyte0, InternalMetadata.US_ASCII));
            if (abyte0.length() != 0)
            {
                abyte0 = "Malformed status code ".concat(abyte0);
            } else
            {
                abyte0 = new String("Malformed status code ");
            }
            throw new NumberFormatException(abyte0);
        }

        public final byte[] toAsciiString(Object obj)
        {
            throw new UnsupportedOperationException();
        }

        _cls1()
        {
        }
    }

}
