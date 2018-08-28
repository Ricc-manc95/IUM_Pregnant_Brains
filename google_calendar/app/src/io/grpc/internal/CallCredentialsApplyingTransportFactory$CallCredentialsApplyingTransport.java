// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

// Referenced classes of package io.grpc.internal:
//            ForwardingConnectionClientTransport, ConnectionClientTransport, MetadataApplierImpl, CallCredentialsApplyingTransportFactory, 
//            ClientStream

final class authority extends ForwardingConnectionClientTransport
{

    private final String authority;
    private final ConnectionClientTransport _flddelegate;
    private final CallCredentialsApplyingTransportFactory this$0;

    protected final ConnectionClientTransport _mthdelegate()
    {
        return _flddelegate;
    }

    public final ClientStream newStream(MethodDescriptor methoddescriptor, Metadata metadata, CallOptions calloptions)
    {
        CallCredentials callcredentials;
        MetadataApplierImpl metadataapplierimpl;
        callcredentials = calloptions.credentials;
        if (callcredentials == null)
        {
            break MISSING_BLOCK_LABEL_211;
        }
        metadataapplierimpl = new MetadataApplierImpl(_flddelegate, methoddescriptor, metadata, calloptions);
        metadata = Attributes.newBuilder()._mthdelegate(CallCredentials.ATTR_AUTHORITY, authority).authority(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.NONE);
        Attributes attributes = _flddelegate.getAttributes();
        int i = attributes.data.size();
        if (((io.grpc.ransport.delegate) (metadata))._flddelegate == null)
        {
            metadata._flddelegate = new IdentityHashMap(i);
        }
        ((io.grpc.ransport.delegate) (metadata))._flddelegate.putAll(attributes.data);
        if (calloptions.authority != null)
        {
            metadata._mthdelegate(CallCredentials.ATTR_AUTHORITY, calloptions.authority);
        }
        Attributes attributes1;
        attributes1 = metadata._mthdelegate();
        metadata = calloptions.executor;
        calloptions = appExecutor;
        if (metadata == null)
        {
label0:
            {
                if (calloptions == null)
                {
                    break label0;
                }
                metadata = calloptions;
            }
        }
        try
        {
            callcredentials.applyRequestMetadata(methoddescriptor, attributes1, (Executor)metadata, metadataapplierimpl);
        }
        // Misplaced declaration of an exception variable
        catch (MethodDescriptor methoddescriptor)
        {
            metadataapplierimpl.fail(Status.UNAUTHENTICATED.withDescription("Credentials should use fail() instead of throwing exceptions").withCause(methoddescriptor));
        }
        return metadataapplierimpl.returnStream();
        throw new NullPointerException("Both parameters are null");
        return _flddelegate.newStream(methoddescriptor, metadata, calloptions);
    }

    (ConnectionClientTransport connectionclienttransport, String s)
    {
        this$0 = CallCredentialsApplyingTransportFactory.this;
        super();
        if (connectionclienttransport == null)
        {
            throw new NullPointerException(String.valueOf("delegate"));
        }
        _flddelegate = (ConnectionClientTransport)connectionclienttransport;
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("authority"));
        } else
        {
            authority = (String)s;
            return;
        }
    }
}
