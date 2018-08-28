// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

// Referenced classes of package com.google.common.util.concurrent:
//            ListenableFuture, AbstractFuture, Uninterruptibles, AsyncFunction

public abstract class AbstractTransformFuture extends AbstractFuture.TrustedFuture
    implements Runnable
{

    private Object function;
    private ListenableFuture inputFuture;

    AbstractTransformFuture(ListenableFuture listenablefuture, Object obj)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        }
        inputFuture = (ListenableFuture)listenablefuture;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            function = obj;
            return;
        }
    }

    public static ListenableFuture create(ListenableFuture listenablefuture, Function function1, Executor executor)
    {
        if (function1 == null)
        {
            throw new NullPointerException();
        }
        function1 = new TransformFuture(listenablefuture, function1);
        if (executor == null)
        {
            throw new NullPointerException();
        }
        if (function1 == null)
        {
            throw new NullPointerException();
        }
        if (executor != MoreExecutors.DirectExecutor.INSTANCE)
        {
            executor = new MoreExecutors._cls5(executor, function1);
        }
        listenablefuture.addListener(function1, executor);
        return function1;
    }

    public static ListenableFuture create(ListenableFuture listenablefuture, AsyncFunction asyncfunction, Executor executor)
    {
        if (executor == null)
        {
            throw new NullPointerException();
        }
        asyncfunction = new AsyncTransformFuture(listenablefuture, asyncfunction);
        if (executor == null)
        {
            throw new NullPointerException();
        }
        if (asyncfunction == null)
        {
            throw new NullPointerException();
        }
        if (executor != MoreExecutors.DirectExecutor.INSTANCE)
        {
            executor = new MoreExecutors._cls5(executor, asyncfunction);
        }
        listenablefuture.addListener(asyncfunction, executor);
        return asyncfunction;
    }

    protected final void afterDone()
    {
        maybePropagateCancellationTo(inputFuture);
        inputFuture = null;
        function = null;
    }

    abstract Object doTransform(Object obj, Object obj1)
        throws Exception;

    protected final String pendingToString()
    {
        ListenableFuture listenablefuture = inputFuture;
        Object obj = function;
        String s1 = super.pendingToString();
        String s = "";
        if (listenablefuture != null)
        {
            s = String.valueOf(listenablefuture);
            s = (new StringBuilder(String.valueOf(s).length() + 16)).append("inputFuture=[").append(s).append("], ").toString();
        }
        if (obj != null)
        {
            s1 = String.valueOf(obj);
            return (new StringBuilder(String.valueOf(s).length() + 11 + String.valueOf(s1).length())).append(s).append("function=[").append(s1).append("]").toString();
        }
        if (s1 != null)
        {
            s = String.valueOf(s);
            s1 = String.valueOf(s1);
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
        boolean flag1 = true;
        Object obj2 = inputFuture;
        Object obj = function;
        boolean flag2 = isCancelled();
        boolean flag;
        if (obj2 == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (obj != null)
        {
            flag1 = false;
        }
        if (flag1 | (flag | flag2))
        {
            return;
        }
        inputFuture = null;
        try
        {
            if (!((Future) (obj2)).isDone())
            {
                throw new IllegalStateException(Strings.lenientFormat("Future was expected to be done: %s", new Object[] {
                    obj2
                }));
            }
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            cancel(false);
            return;
        }
        catch (ExecutionException executionexception)
        {
            setException(executionexception.getCause());
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            setException(runtimeexception);
            return;
        }
        catch (Error error)
        {
            setException(error);
            return;
        }
        obj2 = Uninterruptibles.getUninterruptibly(((Future) (obj2)));
        obj = doTransform(obj, obj2);
        function = null;
        setResult(obj);
        return;
        Object obj1;
        obj1;
        setException(((Throwable) (obj1)));
        function = null;
        return;
        obj1;
        function = null;
        throw obj1;
    }

    abstract void setResult(Object obj);

    private class TransformFuture extends AbstractTransformFuture
    {

        final Object doTransform(Object obj, Object obj1)
            throws Exception
        {
            return ((Function)obj).apply(obj1);
        }

        final void setResult(Object obj)
        {
            set(obj);
        }

        TransformFuture(ListenableFuture listenablefuture, Function function1)
        {
            super(listenablefuture, function1);
        }
    }


    private class AsyncTransformFuture extends AbstractTransformFuture
    {

        final Object doTransform(Object obj, Object obj1)
            throws Exception
        {
            obj = ((AsyncFunction)obj).apply(obj1);
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?"));
            } else
            {
                return obj;
            }
        }

        final void setResult(Object obj)
        {
            setFuture((ListenableFuture)obj);
        }

        AsyncTransformFuture(ListenableFuture listenablefuture, AsyncFunction asyncfunction)
        {
            super(listenablefuture, asyncfunction);
        }
    }

}
