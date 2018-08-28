// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.io.Closeable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package io.grpc:
//            PersistentHashArrayMappedTrie, ThreadLocalContextStorage, Deadline

public class Context
{
    public static final class CancellableContext extends Context
        implements Closeable
    {

        public static boolean cancel$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIIMG_0()
        {
            throw new NoSuchMethodError();
        }

        public final Context attach()
        {
            throw new NoSuchMethodError();
        }

        final boolean canBeCancelled()
        {
            throw new NoSuchMethodError();
        }

        public final Throwable cancellationCause()
        {
            throw new NoSuchMethodError();
        }

        public final void close()
        {
            throw new NoSuchMethodError();
        }

        public final void detach(Context context)
        {
            throw new NoSuchMethodError();
        }

        public final Deadline getDeadline()
        {
            throw new NoSuchMethodError();
        }

        public final boolean isCancelled()
        {
            throw new NoSuchMethodError();
        }
    }

    public static interface CancellationListener
    {

        public abstract void cancelled(Context context);
    }

    static final class DirectExecutor extends Enum
        implements Executor
    {

        private static final DirectExecutor $VALUES[];
        public static final DirectExecutor INSTANCE;

        public static DirectExecutor[] values()
        {
            return (DirectExecutor[])$VALUES.clone();
        }

        public final void execute(Runnable runnable)
        {
            runnable.run();
        }

        public final String toString()
        {
            return "Context.DirectExecutor";
        }

        static 
        {
            INSTANCE = new DirectExecutor("INSTANCE", 0);
            $VALUES = (new DirectExecutor[] {
                INSTANCE
            });
        }

        private DirectExecutor(String s, int i)
        {
            super(s, 0);
        }
    }

    final class ExecutableListener
        implements Runnable
    {

        private final Executor executor;
        public final CancellationListener listener;
        private final Context this$0;

        final void deliver()
        {
            try
            {
                executor.execute(this);
                return;
            }
            catch (Throwable throwable)
            {
                Context.log.logp(Level.INFO, "io.grpc.Context$ExecutableListener", "deliver", "Exception notifying context listener", throwable);
            }
        }

        public final void run()
        {
            listener.cancelled(Context.this);
        }

        ExecutableListener(Executor executor1, CancellationListener cancellationlistener)
        {
            this$0 = Context.this;
            super();
            executor = executor1;
            listener = cancellationlistener;
        }
    }

    public static final class Key
    {

        public final Object defaultValue;
        private final String name;

        public final String toString()
        {
            return name;
        }

        Key(String s)
        {
            this(s, null);
        }

        private Key(String s, Object obj)
        {
            name = (String)Context.checkNotNull(s, "name");
            defaultValue = null;
        }
    }

    final class ParentListener
        implements CancellationListener
    {

        private final Context this$0;

        public final void cancelled(Context context)
        {
            boolean flag = false;
            if (!(Context.this instanceof CancellableContext)) goto _L2; else goto _L1
_L1:
            context.cancellationCause();
            CancellableContext.cancel._mth5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIIMG_0();
_L4:
            return;
_L2:
            context = Context.this;
            if (!context.canBeCancelled())
            {
                continue; /* Loop/switch isn't completed */
            }
            context;
            JVM INSTR monitorenter ;
            if (context.listeners != null)
            {
                break MISSING_BLOCK_LABEL_52;
            }
            context;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            context;
            JVM INSTR monitorexit ;
            throw exception;
            ArrayList arraylist;
            arraylist = context.listeners;
            context.listeners = null;
            context;
            JVM INSTR monitorexit ;
            int i = 0;
            int j;
            do
            {
                j = ((flag) ? 1 : 0);
                if (i >= arraylist.size())
                {
                    break;
                }
                if (!(((ExecutableListener)arraylist.get(i)).listener instanceof ParentListener))
                {
                    ((ExecutableListener)arraylist.get(i)).deliver();
                }
                i++;
            } while (true);
            for (; j < arraylist.size(); j++)
            {
                if (((ExecutableListener)arraylist.get(j)).listener instanceof ParentListener)
                {
                    ((ExecutableListener)arraylist.get(j)).deliver();
                }
            }

            if (context.cancellableAncestor != null)
            {
                context.cancellableAncestor.removeListener(context.parentListener);
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        ParentListener()
        {
            this$0 = Context.this;
            super();
        }
    }

    public static abstract class Storage
    {

        public abstract Context current();

        public abstract void detach(Context context, Context context1);

        public Context doAttach(Context context)
        {
            current();
            throw new UnsupportedOperationException("Deprecated. Do not call.");
        }

        public Storage()
        {
        }
    }


    private static final PersistentHashArrayMappedTrie EMPTY_ENTRIES;
    private static final Context ROOT;
    public static final Logger log = Logger.getLogger(io/grpc/Context.getName());
    private static final AtomicReference storage = new AtomicReference();
    public final CancellableContext cancellableAncestor;
    private final int generation = 0;
    public final PersistentHashArrayMappedTrie keyValueEntries;
    public ArrayList listeners;
    public CancellationListener parentListener;

    private Context(Context context, PersistentHashArrayMappedTrie persistenthasharraymappedtrie)
    {
        context = null;
        super();
        parentListener = new ParentListener();
        if (false)
        {
            if (null instanceof CancellableContext)
            {
                context = (CancellableContext)null;
            } else
            {
                throw new NullPointerException();
            }
        }
        cancellableAncestor = context;
        keyValueEntries = persistenthasharraymappedtrie;
        if (generation == 1000)
        {
            log.logp(Level.SEVERE, "io.grpc.Context", "validateGeneration", "Context ancestry chain length is abnormally long. This suggests an error in application code. Length exceeded: 1000", new Exception());
        }
    }

    static Object checkNotNull(Object obj, Object obj1)
    {
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf(obj1));
        } else
        {
            return obj;
        }
    }

    private static Storage createStorage()
    {
        Storage storage1 = (Storage)Class.forName("io.grpc.override.ContextStorageOverride").getConstructor(new Class[0]).newInstance(new Object[0]);
        storage.compareAndSet(null, storage1);
_L2:
        return (Storage)storage.get();
        Object obj;
        obj;
        ThreadLocalContextStorage threadlocalcontextstorage = new ThreadLocalContextStorage();
        if (storage.compareAndSet(null, threadlocalcontextstorage))
        {
            log.logp(Level.FINE, "io.grpc.Context", "createStorage", "Storage override doesn't exist. Using default", ((Throwable) (obj)));
        }
        if (true) goto _L2; else goto _L1
_L1:
        obj;
        throw new RuntimeException("Storage override failed to initialize", ((Throwable) (obj)));
    }

    public static Context current()
    {
        Object obj1 = (Storage)storage.get();
        Object obj = obj1;
        if (obj1 == null)
        {
            obj = createStorage();
        }
        obj1 = ((Storage) (obj)).current();
        obj = obj1;
        if (obj1 == null)
        {
            obj = ROOT;
        }
        return ((Context) (obj));
    }

    public static Key key(String s)
    {
        return new Key(s);
    }

    public final void addListener(CancellationListener cancellationlistener, Executor executor)
    {
        if (cancellationlistener == null)
        {
            throw new NullPointerException(String.valueOf("cancellationListener"));
        }
        if (executor == null)
        {
            throw new NullPointerException(String.valueOf("executor"));
        }
        if (!canBeCancelled())
        {
            break MISSING_BLOCK_LABEL_136;
        }
        cancellationlistener = new ExecutableListener(executor, cancellationlistener);
        this;
        JVM INSTR monitorenter ;
        if (!isCancelled()) goto _L2; else goto _L1
_L1:
        cancellationlistener.deliver();
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (listeners != null)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        listeners = new ArrayList();
        listeners.add(cancellationlistener);
        if (cancellableAncestor != null)
        {
            cancellableAncestor.addListener(parentListener, ((Executor) (DirectExecutor.INSTANCE)));
        }
          goto _L3
        cancellationlistener;
        this;
        JVM INSTR monitorexit ;
        throw cancellationlistener;
        listeners.add(cancellationlistener);
          goto _L3
    }

    public Context attach()
    {
        Object obj1 = (Storage)storage.get();
        Object obj = obj1;
        if (obj1 == null)
        {
            obj = createStorage();
        }
        obj1 = ((Storage) (obj)).doAttach(this);
        obj = obj1;
        if (obj1 == null)
        {
            obj = ROOT;
        }
        return ((Context) (obj));
    }

    boolean canBeCancelled()
    {
        return cancellableAncestor != null;
    }

    public Throwable cancellationCause()
    {
        if (cancellableAncestor == null)
        {
            return null;
        } else
        {
            return cancellableAncestor.cancellationCause();
        }
    }

    public void detach(Context context)
    {
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("toAttach"));
        }
        Storage storage2 = (Storage)storage.get();
        Storage storage1 = storage2;
        if (storage2 == null)
        {
            storage1 = createStorage();
        }
        storage1.detach(this, context);
    }

    public Deadline getDeadline()
    {
        if (cancellableAncestor == null)
        {
            return null;
        } else
        {
            return cancellableAncestor.getDeadline();
        }
    }

    public boolean isCancelled()
    {
        if (cancellableAncestor == null)
        {
            return false;
        } else
        {
            return cancellableAncestor.isCancelled();
        }
    }

    public final void removeListener(CancellationListener cancellationlistener)
    {
        if (!canBeCancelled())
        {
            return;
        }
        this;
        JVM INSTR monitorenter ;
        if (listeners == null) goto _L2; else goto _L1
_L1:
        int i = listeners.size() - 1;
_L8:
        if (i < 0) goto _L4; else goto _L3
_L3:
        if (((ExecutableListener)listeners.get(i)).listener != cancellationlistener) goto _L6; else goto _L5
_L5:
        listeners.remove(i);
_L4:
        if (listeners.isEmpty())
        {
            if (cancellableAncestor != null)
            {
                cancellableAncestor.removeListener(parentListener);
            }
            listeners = null;
        }
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        cancellationlistener;
        this;
        JVM INSTR monitorexit ;
        throw cancellationlistener;
_L6:
        i--;
        if (true) goto _L8; else goto _L7
_L7:
    }

    static 
    {
        EMPTY_ENTRIES = new PersistentHashArrayMappedTrie();
        ROOT = new Context(null, EMPTY_ENTRIES);
    }
}
