// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.Closeable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.concurrent:
//            AsyncCloseableFunction

public final class AsyncCloseable
{

    public final CloseableList closeableList;
    public final ListenableFuture future;
    public final AtomicReference state;

    public AsyncCloseable(ListenableFuture listenablefuture, CloseableList closeablelist)
    {
        future = listenablefuture;
        closeableList = closeablelist;
        state = new AtomicReference(State.INITIAL);
    }

    public static AsyncCloseable fromFuture(ListenableFuture listenablefuture)
    {
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        } else
        {
            return new AsyncCloseable((ListenableFuture)listenablefuture, new CloseableList());
        }
    }

    public static transient AsyncCloseable fromFutureWithCloseables(ListenableFuture listenablefuture, Closeable acloseable[])
    {
        acloseable = new CloseableList(acloseable);
        if (listenablefuture == null)
        {
            throw new NullPointerException();
        } else
        {
            return new AsyncCloseable((ListenableFuture)listenablefuture, acloseable);
        }
    }

    static final ListenableFuture lambda$createWrapperAsyncFunction$7$AsyncCloseable(AsyncCloseableFunction asynccloseablefunction, CloseableList closeablelist, Object obj)
        throws Exception
    {
        asynccloseablefunction = asynccloseablefunction.apply(obj);
        closeablelist.add(((AsyncCloseable) (asynccloseablefunction)).closeableList);
        return ((AsyncCloseable) (asynccloseablefunction)).future;
    }

    static final ListenableFuture lambda$fromCloseableFuture$0$AsyncCloseable(CloseableList closeablelist, ListenableFuture listenablefuture, Closeable closeable)
        throws Exception
    {
        closeablelist.add(closeable);
        return listenablefuture;
    }

    public final ListenableFuture transformAndClose()
    {
        State state1 = (State)state.getAndSet(State.CLOSED);
        if (!state1.equals(State.INITIAL))
        {
            throw new IllegalStateException(Strings.lenientFormat("Attempting to transform or close an AsyncCloseable that was already in state %s", new Object[] {
                state1
            }));
        } else
        {
            ListenableFuture listenablefuture = AbstractTransformFuture.create(future, new com.google.common.base.Functions.ConstantFunction(null), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            class .Lambda._cls9
                implements Runnable
            {

                private final AsyncCloseable arg$1;

                public final void run()
                {
                    Object obj = arg$1;
                    ListenableFuture listenablefuture1 = ((AsyncCloseable) (obj)).future;
                    obj = ((AsyncCloseable) (obj)).closeableList;
                    obj.getClass();
                    class .Lambda._cls10
                        implements Runnable
                    {

                        private final CloseableList arg$1;

                        public final void run()
                        {
                            arg$1.close();
                        }

                            .Lambda._cls10(CloseableList closeablelist)
                            {
                                arg$1 = closeablelist;
                            }
                    }

                    listenablefuture1.addListener(new .Lambda._cls10(((CloseableList) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
                }

            .Lambda._cls9()
            {
                arg$1 = AsyncCloseable.this;
            }
            }

            listenablefuture.addListener(new .Lambda._cls9(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            return listenablefuture;
        }
    }

    public final ListenableFuture transformAndClose(Function function, Executor executor)
    {
        State state1 = (State)state.getAndSet(State.CLOSED);
        if (!state1.equals(State.INITIAL))
        {
            throw new IllegalStateException(Strings.lenientFormat("Attempting to transform or close an AsyncCloseable that was already in state %s", new Object[] {
                state1
            }));
        } else
        {
            function = AbstractTransformFuture.create(future, function, executor);
            function.addListener(new .Lambda._cls9(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            return function;
        }
    }

    public final AsyncCloseable transformAsync(AsyncFunction asyncfunction, Executor executor)
    {
        State state1 = (State)state.getAndSet(State.TRANSFORMED);
        if (!state1.equals(State.INITIAL))
        {
            throw new IllegalStateException(Strings.lenientFormat("Attempting to transform or close an AsyncCloseable that was already in state %s", new Object[] {
                state1
            }));
        } else
        {
            return new AsyncCloseable(AbstractTransformFuture.create(future, asyncfunction, executor), closeableList);
        }
    }

    public final ListenableFuture transformAsyncAndClose(AsyncFunction asyncfunction, Executor executor)
    {
        State state1 = (State)state.getAndSet(State.CLOSED);
        if (!state1.equals(State.INITIAL))
        {
            throw new IllegalStateException(Strings.lenientFormat("Attempting to transform or close an AsyncCloseable that was already in state %s", new Object[] {
                state1
            }));
        } else
        {
            asyncfunction = AbstractTransformFuture.create(future, asyncfunction, executor);
            asyncfunction.addListener(new .Lambda._cls9(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            return asyncfunction;
        }
    }

    public final AsyncCloseable transformAsyncCloseable(AsyncCloseableFunction asynccloseablefunction, Executor executor)
    {
        State state1 = (State)state.getAndSet(State.TRANSFORMED);
        if (!state1.equals(State.INITIAL))
        {
            throw new IllegalStateException(Strings.lenientFormat("Attempting to transform or close an AsyncCloseable that was already in state %s", new Object[] {
                state1
            }));
        } else
        {
            CloseableList closeablelist = new CloseableList(new Closeable[] {
                closeableList
            });
            class .Lambda._cls8
                implements AsyncFunction
            {

                private final AsyncCloseableFunction arg$1;
                private final CloseableList arg$2;

                public final ListenableFuture apply(Object obj)
                {
                    return AsyncCloseable.lambda$createWrapperAsyncFunction$7$AsyncCloseable(arg$1, arg$2, obj);
                }

            .Lambda._cls8(AsyncCloseableFunction asynccloseablefunction, CloseableList closeablelist)
            {
                arg$1 = asynccloseablefunction;
                arg$2 = closeablelist;
            }
            }

            return new AsyncCloseable(AbstractTransformFuture.create(future, new .Lambda._cls8(asynccloseablefunction, closeablelist), executor), closeablelist);
        }
    }

    private class State extends Enum
    {

        private static final State $VALUES[];
        public static final State CLOSED;
        public static final State INITIAL;
        public static final State TRANSFORMED;

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            INITIAL = new State("INITIAL", 0);
            TRANSFORMED = new State("TRANSFORMED", 1);
            CLOSED = new State("CLOSED", 2);
            $VALUES = (new State[] {
                INITIAL, TRANSFORMED, CLOSED
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    private class CloseableList
        implements Closeable
    {

        private List closeables;
        private boolean closed;

        final void add(Closeable closeable)
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            if (closeables == null)
            {
                closeables = new ArrayList();
            }
            closeables.add(closeable);
            this;
            JVM INSTR monitorexit ;
            return;
            this;
            JVM INSTR monitorexit ;
            if (closeable != null)
            {
                try
                {
                    closeable.close();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (Closeable closeable)
                {
                    return;
                }
            } else
            {
                return;
            }
            closeable;
            this;
            JVM INSTR monitorexit ;
            throw closeable;
        }

        public final void close()
        {
            this;
            JVM INSTR monitorenter ;
            if (!closed)
            {
                break MISSING_BLOCK_LABEL_12;
            }
            this;
            JVM INSTR monitorexit ;
            return;
            closed = true;
            this;
            JVM INSTR monitorexit ;
            if (closeables == null)
            {
                break MISSING_BLOCK_LABEL_82;
            }
            Iterator iterator = closeables.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Closeable closeable = (Closeable)iterator.next();
                if (closeable != null)
                {
                    try
                    {
                        closeable.close();
                    }
                    catch (IOException ioexception) { }
                }
            } while (true);
            break MISSING_BLOCK_LABEL_77;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            closeables = null;
        }

        public CloseableList()
        {
        }

        transient CloseableList(Closeable acloseable[])
        {
            closeables = new ArrayList(acloseable.length);
            closeables.addAll(Arrays.asList(acloseable));
        }
    }

}
