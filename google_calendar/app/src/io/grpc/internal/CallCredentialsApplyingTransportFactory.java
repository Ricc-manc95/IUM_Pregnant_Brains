// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package io.grpc.internal:
//            ClientTransportFactory, ProxyParameters, ConnectionClientTransport

final class CallCredentialsApplyingTransportFactory
    implements ClientTransportFactory
{

    public final Executor appExecutor;
    private final ClientTransportFactory _flddelegate;

    CallCredentialsApplyingTransportFactory(ClientTransportFactory clienttransportfactory, Executor executor)
    {
        if (clienttransportfactory == null)
        {
            throw new NullPointerException(String.valueOf("delegate"));
        }
        _flddelegate = (ClientTransportFactory)clienttransportfactory;
        if (executor == null)
        {
            throw new NullPointerException(String.valueOf("appExecutor"));
        } else
        {
            appExecutor = (Executor)executor;
            return;
        }
    }

    public final void close()
    {
        _flddelegate.close();
    }

    public final ScheduledExecutorService getScheduledExecutorService()
    {
        return _flddelegate.getScheduledExecutorService();
    }

    public final ConnectionClientTransport newClientTransport(SocketAddress socketaddress, String s, String s1, ProxyParameters proxyparameters)
    {
        return new CallCredentialsApplyingTransport(_flddelegate.newClientTransport(socketaddress, s, s1, proxyparameters), s);
    }

    private class CallCredentialsApplyingTransport extends ForwardingConnectionClientTransport
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
            metadata = Attributes.newBuilder().set(CallCredentials.ATTR_AUTHORITY, authority).set(CallCredentials.ATTR_SECURITY_LEVEL, SecurityLevel.NONE);
            Attributes attributes = _flddelegate.getAttributes();
            int i = attributes.data.size();
            if (((io.grpc.Attributes.Builder) (metadata)).newdata == null)
            {
                metadata.newdata = new IdentityHashMap(i);
            }
            ((io.grpc.Attributes.Builder) (metadata)).newdata.putAll(attributes.data);
            if (calloptions.authority != null)
            {
                metadata.set(CallCredentials.ATTR_AUTHORITY, calloptions.authority);
            }
            Attributes attributes1;
            attributes1 = metadata.build();
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

        CallCredentialsApplyingTransport(ConnectionClientTransport connectionclienttransport, String s)
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

}
