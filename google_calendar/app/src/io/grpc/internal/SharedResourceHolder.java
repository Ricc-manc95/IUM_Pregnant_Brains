// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.IdentityHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            GrpcUtil, LogExceptionRunnable

public final class SharedResourceHolder
{

    public static final SharedResourceHolder holder = new SharedResourceHolder(new _cls1());
    public ScheduledExecutorService destroyer;
    private final ScheduledExecutorFactory destroyerFactory;
    public final IdentityHashMap instances = new IdentityHashMap();

    private SharedResourceHolder(ScheduledExecutorFactory scheduledexecutorfactory)
    {
        destroyerFactory = scheduledexecutorfactory;
    }

    public final Object getInternal(Resource resource)
    {
        this;
        JVM INSTR monitorenter ;
        Instance instance1 = (Instance)instances.get(resource);
        Instance instance;
        instance = instance1;
        if (instance1 != null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        instance = new Instance(resource.create());
        instances.put(resource, instance);
        if (instance.destroyTask != null)
        {
            instance.destroyTask.cancel(false);
            instance.destroyTask = null;
        }
        instance.refcount = instance.refcount + 1;
        resource = ((Resource) (instance.payload));
        this;
        JVM INSTR monitorexit ;
        return resource;
        resource;
        throw resource;
    }

    public final Object releaseInternal(final Resource resource, final Object instance)
    {
        boolean flag1 = true;
        this;
        JVM INSTR monitorenter ;
        final Instance cached = (Instance)instances.get(resource);
        if (cached != null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        resource = String.valueOf(resource);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(resource).length() + 29)).append("No cached instance found for ").append(resource).toString());
        resource;
        this;
        JVM INSTR monitorexit ;
        throw resource;
        boolean flag;
        if (instance == cached.payload)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_97;
        }
        throw new IllegalArgumentException(String.valueOf("Releasing the wrong instance"));
        if (cached.refcount > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        throw new IllegalStateException(String.valueOf("Refcount has already reached zero"));
        cached.refcount = cached.refcount - 1;
        if (cached.refcount != 0) goto _L2; else goto _L1
_L1:
        if (!GrpcUtil.IS_RESTRICTED_APPENGINE) goto _L4; else goto _L3
_L3:
        resource.close(instance);
        instances.remove(resource);
_L2:
        this;
        JVM INSTR monitorexit ;
        return null;
_L4:
        if (cached.destroyTask == null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        throw new IllegalStateException(String.valueOf("Destroy task already scheduled"));
        if (destroyer == null)
        {
            destroyer = destroyerFactory.createScheduledExecutor();
        }
        cached.destroyTask = destroyer.schedule(new LogExceptionRunnable(new _cls2()), 1L, TimeUnit.SECONDS);
          goto _L2
    }


    private class Instance
    {

        public ScheduledFuture destroyTask;
        public final Object payload;
        public int refcount;

        Instance(Object obj)
        {
            payload = obj;
        }
    }


    private class Resource
    {

        public abstract void close(Object obj);

        public abstract Object create();
    }


    private class ScheduledExecutorFactory
    {

        public abstract ScheduledExecutorService createScheduledExecutor();
    }


    private class _cls2
        implements Runnable
    {

        private final SharedResourceHolder this$0;
        private final Instance val$cached;
        private final Object val$instance;
        private final Resource val$resource;

        public final void run()
        {
            synchronized (SharedResourceHolder.this)
            {
                if (cached.refcount == 0)
                {
                    resource.close(instance);
                    instances.remove(resource);
                    if (instances.isEmpty())
                    {
                        destroyer.shutdown();
                        destroyer = null;
                    }
                }
            }
            return;
            exception;
            sharedresourceholder;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls2()
        {
            this$0 = SharedResourceHolder.this;
            cached = instance1;
            resource = resource1;
            instance = obj;
            super();
        }
    }


    private class _cls1
        implements ScheduledExecutorFactory
    {

        public final ScheduledExecutorService createScheduledExecutor()
        {
            return Executors.newSingleThreadScheduledExecutor(GrpcUtil.getThreadFactory("grpc-shared-destroyer-%d", true));
        }

        _cls1()
        {
        }
    }

}
