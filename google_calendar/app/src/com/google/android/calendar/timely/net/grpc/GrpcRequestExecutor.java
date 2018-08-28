// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net.grpc;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.conscrypt.ConscryptInstallationException;
import com.google.android.apps.calendar.util.conscrypt.ConscryptUtils;
import com.google.android.calendar.utils.app.PackageUtils;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import com.google.rpc.Code;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.stub.AbstractStub;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

// Referenced classes of package com.google.android.calendar.timely.net.grpc:
//            GrpcRequestException, GrpcStubException, GrpcCall

public abstract class GrpcRequestExecutor
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/net/grpc/GrpcRequestExecutor);
    public String accountEmail;
    private String authToken;
    private final Context context;
    private CallCredentials credentials;
    public ManagedChannel grpcChannel;
    private final boolean retryWhenUnauthenticated;
    private AbstractStub stub;
    private final int targetEnvironment;

    public GrpcRequestExecutor(Context context1, String s, int i, boolean flag)
    {
        context = context1;
        accountEmail = s;
        targetEnvironment = i;
        retryWhenUnauthenticated = flag;
    }

    public final Object call(GrpcCall grpccall, Object obj, boolean flag)
        throws GrpcRequestException, GrpcStubException
    {
_L2:
        initGrpcStub();
        boolean flag1;
        if (stub != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new VerifyException(Strings.lenientFormat("initGrpcStub did not set stub", new Object[0]));
        }
        Object obj1 = grpccall.call(obj);
        return obj1;
        StatusRuntimeException statusruntimeexception;
        statusruntimeexception;
        if (!retryWhenUnauthenticated || flag)
        {
            break; /* Loop/switch isn't completed */
        }
        int i = statusruntimeexception.status.code.value;
        Code code = Code.UNAUTHENTICATED;
        if (code == Code.UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        if (i != code.value)
        {
            break; /* Loop/switch isn't completed */
        }
        LogUtils.w(TAG, statusruntimeexception, "Retrying with new credentials", new Object[0]);
        clearToken();
        createCredentials();
        flag = true;
        if (true) goto _L2; else goto _L1
_L1:
        throw new GrpcRequestException(statusruntimeexception.status, statusruntimeexception.getMessage(), statusruntimeexception);
        grpccall;
        LogUtils.e(TAG, grpccall, "Exception calling the Grpc layer", new Object[0]);
        throw new GrpcRequestException(Status.UNKNOWN, "Exception calling the Grpc layer", grpccall);
    }

    public final void clearToken()
        throws GrpcStubException
    {
        this;
        JVM INSTR monitorenter ;
        if (authToken != null)
        {
            GoogleAuthUtil.clearToken(context, authToken);
            authToken = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
_L2:
        throw new GrpcStubException(android.support.v4.content.ModernAsyncTask.Status.AUTHENTICATION$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TN6AT1FCTP70OPF8TP70OQJEHQM4HBOCDIN0T39DTN28KRFELP66P9R0, "Failed to clear auth token", ((Throwable) (obj)));
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void createCredentials()
        throws GrpcStubException
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = getSyncAuthority();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        obj = GoogleAuthUtil.getTokenWithNotification(context, accountEmail, getAuthScope(), null, ((String) (obj)), null);
_L1:
        authToken = ((String) (obj));
        if (authToken == null)
        {
            throw new GrpcStubException(android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TN6AT1FCTP70OPF8TP70OQJEHQM4HBOCDIN0T39DTN28KRFELP66P9R0, "Error fetching auth token for Grpc API", new IllegalStateException());
        }
        break MISSING_BLOCK_LABEL_116;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj = GoogleAuthUtil.getToken(context, accountEmail, getAuthScope());
          goto _L1
        Object obj1;
        obj1;
        throw new GrpcStubException(android.support.v4.content.ModernAsyncTask.Status.AUTHENTICATION$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TN6AT1FCTP70OPF8TP70OQJEHQM4HBOCDIN0T39DTN28KRFELP66P9R0, "Failed to retrieve auth token", ((Throwable) (obj1)));
        obj1;
        throw new GrpcStubException(android.support.v4.content.ModernAsyncTask.Status.IO$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TN6AT1FCTP70OPF8TP70OQJEHQM4HBOCDIN0T39DTN28KRFELP66P9R0, "Failed to retrieve auth token", ((Throwable) (obj1)));
        credentials = new GoogleAuthLibraryCallCredentials(new GoogleCredentials(new AccessToken(authToken, null)));
        this;
        JVM INSTR monitorexit ;
    }

    public abstract String getAuthScope();

    public AbstractStub getAuthenticatedStub()
    {
        AbstractStub abstractstub = stub;
        CallCredentials callcredentials = credentials;
        Channel channel = abstractstub.channel;
        CallOptions calloptions = new CallOptions(abstractstub.callOptions);
        calloptions.credentials = callcredentials;
        return abstractstub.build(channel, calloptions);
    }

    public abstract String getServerTargetProd();

    public abstract String getServerTargetStaging();

    public abstract String getServerTargetTest();

    public abstract AbstractStub getStub(Channel channel);

    public String getSyncAuthority()
    {
        return null;
    }

    public void initGrpcStub()
        throws GrpcStubException
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = stub;
        if (obj == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ConscryptUtils.installSecurityProvider(context);
        SSLContext sslcontext;
        sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, null, null);
        targetEnvironment;
        JVM INSTR tableswitch 0 1: default 204
    //                   0 168
    //                   1 176;
           goto _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_176;
_L3:
        obj = getServerTargetProd();
_L6:
        obj = new OkHttpChannelBuilder(((String) (obj)));
        obj.sslSocketFactory = sslcontext.getSocketFactory();
        obj.negotiationType = io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.TLS;
        obj.userAgent = String.format(null, "Calendar-Android(versionCode=%d)", new Object[] {
            Integer.valueOf(PackageUtils.getVersionCode(context))
        });
        grpcChannel = ((OkHttpChannelBuilder)obj).build();
        createCredentials();
        stub = getStub(grpcChannel);
        continue; /* Loop/switch isn't completed */
        obj;
        throw obj;
        obj;
        throw new GrpcStubException(android.support.v4.content.ModernAsyncTask.Status.SECURITY_PROVIDER$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TN6AT1FCTP70OPF8TP70OQJEHQM4HBOCDIN0T39DTN28KRFELP66P9R0, "Failed to install security provider", ((Throwable) (obj)));
_L4:
        obj = getServerTargetTest();
          goto _L6
        obj = getServerTargetStaging();
          goto _L6
        Object obj1;
        obj1;
_L8:
        throw new GrpcStubException(android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCDGMOPBECHGN4BRKD5MMAR3P5TN6AT1FCTP70OPF8TP70OQJEHQM4HBOCDIN0T39DTN28KRFELP66P9R0, "Failed to initialize gRPC Channel", ((Throwable) (obj1)));
        obj1;
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L1; else goto _L9
_L9:
    }

}
