// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

// Referenced classes of package com.google.common.util.concurrent:
//            ListenableFuture, AbstractFuture, Uninterruptibles

public abstract class AbstractCatchingFuture extends AbstractFuture.TrustedFuture
    implements Runnable
{

    private Class exceptionType;
    private Object fallback;
    private ListenableFuture inputFuture;

    AbstractCatchingFuture(ListenableFuture listenablefuture, Class class1, Object obj)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        }
        inputFuture = (ListenableFuture)listenablefuture;
        if (class1 == null)
        {
            throw new NullPointerException();
        }
        exceptionType = (Class)class1;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            fallback = obj;
            return;
        }
    }

    public static ListenableFuture create(ListenableFuture listenablefuture, Class class1, Function function, Executor executor)
    {
        class1 = new CatchingFuture(listenablefuture, class1, function);
        if (executor == null)
        {
            throw new NullPointerException();
        }
        if (class1 == null)
        {
            throw new NullPointerException();
        }
        if (executor != MoreExecutors.DirectExecutor.INSTANCE)
        {
            executor = new MoreExecutors._cls5(executor, class1);
        }
        listenablefuture.addListener(class1, executor);
        return class1;
    }

    protected final void afterDone()
    {
        boolean flag1 = true;
        ListenableFuture listenablefuture = inputFuture;
        boolean flag;
        if (listenablefuture != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag & isCancelled())
        {
            Object obj = super.value;
            if (!(obj instanceof AbstractFuture.Cancellation) || !((AbstractFuture.Cancellation)obj).wasInterrupted)
            {
                flag1 = false;
            }
            listenablefuture.cancel(flag1);
        }
        inputFuture = null;
        exceptionType = null;
        fallback = null;
    }

    abstract Object doFallback(Object obj, Throwable throwable)
        throws Exception;

    protected final String pendingToString()
    {
        ListenableFuture listenablefuture = inputFuture;
        Class class1 = exceptionType;
        Object obj = fallback;
        String s2 = super.pendingToString();
        String s = "";
        if (listenablefuture != null)
        {
            s = String.valueOf(listenablefuture);
            s = (new StringBuilder(String.valueOf(s).length() + 16)).append("inputFuture=[").append(s).append("], ").toString();
        }
        if (class1 != null && obj != null)
        {
            s2 = String.valueOf(class1);
            obj = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(s).length() + 29 + String.valueOf(s2).length() + String.valueOf(obj).length())).append(s).append("exceptionType=[").append(s2).append("], fallback=[").append(((String) (obj))).append("]").toString();
        }
        if (s2 != null)
        {
            s = String.valueOf(s);
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                return s.concat(s1);
            } else
            {
                return new String(s);
            }
        } else
        {
            return null;
        }
    }

    public final void run()
    {
        Object obj;
        Class class1;
        Object obj3;
        boolean flag2 = true;
        obj = inputFuture;
        class1 = exceptionType;
        obj3 = fallback;
        boolean flag;
        boolean flag1;
        if (obj == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (class1 == null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (obj3 != null)
        {
            flag2 = false;
        }
        if (flag2 | (flag1 | flag) | isCancelled())
        {
            return;
        }
        inputFuture = null;
        Object obj2;
        try
        {
            if (!((Future) (obj)).isDone())
            {
                throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                    obj
                }));
            }
            break MISSING_BLOCK_LABEL_126;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj = ((ExecutionException) (obj)).getCause();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj = (Throwable)obj;
            obj2 = null;
            continue; /* Loop/switch isn't completed */
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            obj2 = null;
        }
          goto _L1
        obj2 = Uninterruptibles.getUninterruptibly(((Future) (obj)));
        obj = null;
_L3:
        if (obj == null)
        {
            set(obj2);
            return;
        }
        break; /* Loop/switch isn't completed */
_L1:
        if (true) goto _L3; else goto _L2
_L2:
        if (!class1.isInstance(obj))
        {
            setException(((Throwable) (obj)));
            return;
        }
        obj = doFallback(obj3, ((Throwable) (obj)));
        exceptionType = null;
        fallback = null;
        setResult(obj);
        return;
        Object obj1;
        obj1;
        setException(((Throwable) (obj1)));
        exceptionType = null;
        fallback = null;
        return;
        obj1;
        exceptionType = null;
        fallback = null;
        throw obj1;
    }

    abstract void setResult(Object obj);

    private class CatchingFuture extends AbstractCatchingFuture
    {

        final Object doFallback(Object obj, Throwable throwable)
            throws Exception
        {
            return ((Function)obj).apply(throwable);
        }

        final void setResult(Object obj)
        {
            set(obj);
        }

        CatchingFuture(ListenableFuture listenablefuture, Class class1, Function function)
        {
            super(listenablefuture, class1, function);
        }
    }

}
