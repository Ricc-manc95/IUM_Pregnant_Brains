// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.net.taskassist;

import android.app.Activity;
import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.conscrypt.ConscryptInstallationException;
import com.google.android.apps.calendar.util.conscrypt.ConscryptUtils;
import com.google.android.calendar.net.exceptions.GrpcServiceException;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestRequest;
import com.google.personalization.assist.annotate.api.AnnotatedSuggestResponse;
import com.google.personalization.assist.annotate.api.LoggingRequest;
import com.google.personalization.assist.annotate.api.LoggingResponse;
import com.google.personalization.assist.annotate.api.MyndServiceGrpc;
import com.google.personalization.assist.annotate.api.TaskAssistanceRequest;
import com.google.personalization.assist.annotate.api.TaskAssistanceResponse;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.ManagedChannelBuilder;
import io.grpc.auth.ClientAuthInterceptor;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;

public class TaskAssistService
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/net/taskassist/TaskAssistService);
    private final String accountEmail;
    public final Context context;
    private ListenableFuture stubFuture;
    private final boolean useStaging;

    public TaskAssistService(Context context1, String s, boolean flag)
    {
        context = context1;
        accountEmail = s;
        useStaging = flag;
    }

    private final ListenableFuture getStubAsync()
    {
        this;
        JVM INSTR monitorenter ;
        ListenableFuture listenablefuture;
        class .Lambda._cls3
            implements Callable
        {

            private final TaskAssistService arg$1;

            public final Object call()
            {
                return arg$1.lambda$getStubAsync$4$TaskAssistService();
            }

            .Lambda._cls3()
            {
                arg$1 = TaskAssistService.this;
            }
        }

        if (stubFuture == null)
        {
            stubFuture = (FluentFuture)CalendarExecutor.NET.submit(new .Lambda._cls3());
        }
        listenablefuture = stubFuture;
        this;
        JVM INSTR monitorexit ;
        return listenablefuture;
        Exception exception;
        exception;
        throw exception;
    }

    static final TaskAssistanceResponse lambda$assist$1$TaskAssistService(TaskAssistanceRequest taskassistancerequest, com.google.personalization.assist.annotate.api.MyndServiceGrpc.MyndServiceBlockingStub myndserviceblockingstub)
    {
        return (TaskAssistanceResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (myndserviceblockingstub)).channel, MyndServiceGrpc.getAssistMethod(), ((AbstractStub) (myndserviceblockingstub)).callOptions, taskassistancerequest);
    }

    static final LoggingResponse lambda$log$2$TaskAssistService(LoggingRequest loggingrequest, com.google.personalization.assist.annotate.api.MyndServiceGrpc.MyndServiceBlockingStub myndserviceblockingstub)
    {
        return (LoggingResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (myndserviceblockingstub)).channel, MyndServiceGrpc.getLogMethod(), ((AbstractStub) (myndserviceblockingstub)).callOptions, loggingrequest);
    }

    static final AnnotatedSuggestResponse lambda$suggest$0$TaskAssistService(AnnotatedSuggestRequest annotatedsuggestrequest, com.google.personalization.assist.annotate.api.MyndServiceGrpc.MyndServiceBlockingStub myndserviceblockingstub)
    {
        return (AnnotatedSuggestResponse)ClientCalls.blockingUnaryCall(((AbstractStub) (myndserviceblockingstub)).channel, MyndServiceGrpc.getSuggestMethod(), ((AbstractStub) (myndserviceblockingstub)).callOptions, annotatedsuggestrequest);
    }

    final com.google.personalization.assist.annotate.api.MyndServiceGrpc.MyndServiceBlockingStub lambda$getStubAsync$4$TaskAssistService()
        throws Exception
    {
        Object obj;
        Object obj1;
        try
        {
            ConscryptUtils.installSecurityProvider(context);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new GrpcServiceException("Failed to install security provider", ((Throwable) (obj)));
        }
        if (useStaging)
        {
            LogUtils.i(TAG, "Using Task Assist Staging servers", new Object[0]);
            obj = "staging-taskassist-pa.sandbox.googleapis.com";
        } else
        {
            obj = "taskassist-pa.googleapis.com";
        }
        obj1 = SSLContext.getInstance("TLS");
        ((SSLContext) (obj1)).init(null, null, null);
        obj = new OkHttpChannelBuilder(((String) (obj)), 443);
        obj.sslSocketFactory = ((SSLContext) (obj1)).getSocketFactory();
        obj.negotiationType = io.grpc.okhttp.OkHttpChannelBuilder.NegotiationType.TLS;
        obj1 = ((ManagedChannelBuilder) (obj)).build();
        obj = GoogleAuthUtil.getToken(context, accountEmail, "oauth2:https://www.googleapis.com/auth/taskassist.readonly");
_L1:
        if (obj == null)
        {
            throw new GrpcServiceException("Error fetching User Token for Task Assist API");
        } else
        {
            return new com.google.personalization.assist.annotate.api.MyndServiceGrpc.MyndServiceBlockingStub(ClientInterceptors.intercept(((io.grpc.Channel) (obj1)), new ClientInterceptor[] {
                new ClientAuthInterceptor(new GoogleCredentials(new AccessToken(((String) (obj)), null)))
            }));
        }
        obj;
_L3:
        throw new GrpcServiceException("Failed to initialize gRPC Channel", ((Throwable) (obj)));
        obj;
        class .Lambda._cls4
            implements Runnable
        {

            private final TaskAssistService arg$1;
            private final UserRecoverableAuthException arg$2;

            public final void run()
            {
                TaskAssistService taskassistservice = arg$1;
                Object obj2 = arg$2;
                if (((UserRecoverableAuthException) (obj2)).mIntent == null)
                {
                    obj2 = null;
                } else
                {
                    obj2 = new Intent(((UserRecoverableAuthException) (obj2)).mIntent);
                }
                ((Activity)taskassistservice.context).startActivityForResult(((Intent) (obj2)), 1007);
            }

            .Lambda._cls4(UserRecoverableAuthException userrecoverableauthexception)
            {
                arg$1 = TaskAssistService.this;
                arg$2 = userrecoverableauthexception;
            }
        }

        if (context instanceof Activity)
        {
            ((Activity)context).runOnUiThread(new .Lambda._cls4(((UserRecoverableAuthException) (obj))));
        }
        obj = null;
          goto _L1
        obj;
_L2:
        LogUtils.e(TAG, ((Throwable) (obj)), "Error getting user token for suggestions", new Object[0]);
        obj = null;
          goto _L1
        obj;
          goto _L2
        obj;
          goto _L3
    }

    public final ListenableFuture startRequest(Function function)
    {
        function = AbstractTransformFuture.create(getStubAsync(), function, CalendarExecutor.NET);
        _cls1 _lcls1 = new _cls1();
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (_lcls1 == null)
        {
            throw new NullPointerException();
        } else
        {
            function.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(function, _lcls1), directexecutor);
            return function;
        }
    }

    final void tryResetStubAfterRuntimeException(Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        if (throwable.getCause() instanceof UnknownHostException)
        {
            stubFuture = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        throwable;
        throw throwable;
    }


    private class _cls1
        implements FutureCallback
    {

        private final TaskAssistService this$0;

        public final void onFailure(Throwable throwable)
        {
            tryResetStubAfterRuntimeException(throwable);
        }

        public final void onSuccess(Object obj)
        {
        }

        _cls1()
        {
            this$0 = TaskAssistService.this;
            super();
        }
    }

}
