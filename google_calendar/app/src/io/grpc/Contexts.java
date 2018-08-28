// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.concurrent.TimeoutException;

// Referenced classes of package io.grpc:
//            Context, Status

public final class Contexts
{

    public static Status statusFromCancelled(Context context)
    {
        boolean flag4;
        boolean flag5;
        boolean flag6;
        flag4 = false;
        flag5 = false;
        flag6 = false;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("context must not be null"));
        }
        if (context.isCancelled()) goto _L2; else goto _L1
_L1:
        context = null;
_L4:
        return context;
_L2:
        Throwable throwable;
        Status status;
        boolean flag;
label0:
        {
            throwable = context.cancellationCause();
            if (throwable != null)
            {
                break; /* Loop/switch isn't completed */
            }
            status = Status.CANCELLED;
            context = status.description;
            if (context != "io.grpc.Context was cancelled without error")
            {
                flag = flag6;
                if (context == null)
                {
                    break label0;
                }
                flag = flag6;
                if (!context.equals("io.grpc.Context was cancelled without error"))
                {
                    break label0;
                }
            }
            flag = true;
        }
        context = status;
        if (!flag)
        {
            return new Status(status.code, "io.grpc.Context was cancelled without error", status.cause);
        }
        if (true) goto _L4; else goto _L3
_L3:
label1:
        {
            if (!(throwable instanceof TimeoutException))
            {
                break; /* Loop/switch isn't completed */
            }
            Status status1 = Status.DEADLINE_EXCEEDED;
            context = throwable.getMessage();
            String s = status1.description;
            boolean flag1;
            if (s == context || s != null && s.equals(context))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                status1 = new Status(status1.code, context, status1.cause);
            }
            context = status1.cause;
            if (context != throwable)
            {
                flag1 = flag4;
                if (context == null)
                {
                    break label1;
                }
                flag1 = flag4;
                if (!context.equals(throwable))
                {
                    break label1;
                }
            }
            flag1 = true;
        }
        context = status1;
        if (!flag1)
        {
            return new Status(status1.code, status1.description, throwable);
        }
        if (true) goto _L4; else goto _L5
_L5:
        Status status2;
label2:
        {
            status2 = Status.fromThrowable(throwable);
            if (!Status.Code.UNKNOWN.equals(status2.code) || status2.cause != throwable)
            {
                break; /* Loop/switch isn't completed */
            }
            status2 = Status.CANCELLED;
            context = status2.description;
            boolean flag2;
            if (context == "Context cancelled" || context != null && context.equals("Context cancelled"))
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!flag2)
            {
                status2 = new Status(status2.code, "Context cancelled", status2.cause);
            }
            context = status2.cause;
            if (context != throwable)
            {
                flag2 = flag5;
                if (context == null)
                {
                    break label2;
                }
                flag2 = flag5;
                if (!context.equals(throwable))
                {
                    break label2;
                }
            }
            flag2 = true;
        }
        context = status2;
        if (!flag2)
        {
            return new Status(status2.code, status2.description, throwable);
        }
        if (true) goto _L4; else goto _L6
_L6:
        context = status2.cause;
        boolean flag3;
        if (context == throwable || context != null && context.equals(throwable))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        context = status2;
        if (!flag3)
        {
            return new Status(status2.code, status2.description, throwable);
        }
        if (true) goto _L4; else goto _L7
_L7:
    }
}
