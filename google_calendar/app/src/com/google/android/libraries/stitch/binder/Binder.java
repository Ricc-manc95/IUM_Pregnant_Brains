// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.binder;

import android.content.Context;
import android.content.ContextWrapper;
import com.google.android.libraries.stitch.flags.DebugFlag;
import com.google.android.libraries.stitch.flags.Flags;
import com.google.android.libraries.stitch.flags.TestFlag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.google.android.libraries.stitch.binder:
//            BinderProvider, RootBinderInitializer, SingleBinderLock, BinderContext, 
//            BinderLocks, Module

public final class Binder
{

    private static final TestFlag DETAIL_TRACE_ENABLED = new TestFlag("test.binder.detail_trace");
    private static final DebugFlag EXTRA_VERIFICATION = new DebugFlag("debug.binder.verification");
    private static final boolean IS_STRICT_MODE_ALLOWED = Flags.get(new DebugFlag("debug.binder.strict_mode"));
    private static final TestFlag TRACE_ENABLED = new TestFlag("test.binder.trace");
    private static final Object UNBOUND = new Object();
    public static final BinderProvider rootBinderProvider = new BinderProvider(false, new RootBinderInitializer());
    private volatile BinderLocks binderLocks;
    private final Map bindings;
    private Context context;
    private final ThreadLocal isInternallyBinding;
    private final Map keyBindings;
    public final CopyOnWriteArrayList modules;
    private final Map multiBindings;
    public Binder parent;
    public volatile boolean sealed;

    public Binder()
    {
        bindings = Collections.synchronizedMap(new HashMap());
        multiBindings = Collections.synchronizedMap(new HashMap());
        keyBindings = Collections.synchronizedMap(new HashMap());
        Collections.synchronizedSet(new HashSet());
        modules = new CopyOnWriteArrayList();
        isInternallyBinding = new ThreadLocal();
        binderLocks = new SingleBinderLock();
    }

    public Binder(Context context1)
    {
        this(context1, null);
    }

    private Binder(Context context1, Binder binder)
    {
        bindings = Collections.synchronizedMap(new HashMap());
        multiBindings = Collections.synchronizedMap(new HashMap());
        keyBindings = Collections.synchronizedMap(new HashMap());
        Collections.synchronizedSet(new HashSet());
        modules = new CopyOnWriteArrayList();
        isInternallyBinding = new ThreadLocal();
        binderLocks = new SingleBinderLock();
        context = context1;
        parent = null;
        context1.getClass().getName();
    }

    public static Binder findBinder(Context context1)
    {
        Context context3 = context1.getApplicationContext();
        boolean flag = false;
        do
        {
            Binder binder;
            if (context1 instanceof BinderContext)
            {
                Binder binder1 = ((BinderContext)context1).getBinder();
                binder = binder1;
                if (binder1 == null)
                {
                    context1 = String.valueOf(context1);
                    throw new IllegalStateException((new StringBuilder(String.valueOf(context1).length() + 43)).append("BinderContext must not return null Binder: ").append(context1).toString());
                }
            } else
            {
                binder = null;
            }
            if (binder != null)
            {
                return binder;
            }
            boolean flag1;
            if (context1 == context3)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            flag = flag1 | flag;
            if (context1 instanceof ContextWrapper)
            {
                Context context2 = ((ContextWrapper)context1).getBaseContext();
                context1 = context2;
                if (context2 == null)
                {
                    throw new IllegalStateException("Invalid ContextWrapper -- If this is a Robolectric test, have you called ActivityController.create()?");
                }
            } else
            if (!flag)
            {
                context1 = context3;
            } else
            {
                context1 = null;
            }
        } while (context1 != null);
        return rootBinderProvider.get(context3.getApplicationContext());
    }

    private final Object getInstance(Class class1)
    {
        boolean flag;
        int i;
        flag = true;
        i = 0;
        if (class1 == null)
        {
            throw new NullPointerException();
        }
        if (context == null)
        {
            throw new IllegalStateException("Binder not initialized yet.");
        }
        Object obj1 = binderLocks.getLock$5166KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0();
        obj1;
        JVM INSTR monitorenter ;
        Object obj = bindings.get(class1);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        Object obj2;
        int j;
        if (obj != UNBOUND)
        {
            class1 = ((Class) (obj));
        } else
        {
            class1 = null;
        }
        obj1;
        JVM INSTR monitorexit ;
        return class1;
        obj = (Boolean)isInternallyBinding.get();
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (!((Boolean) (obj)).booleanValue()) goto _L2; else goto _L3
_L3:
        if (flag)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        isInternallyBinding.set(Boolean.valueOf(true));
        j = modules.size();
_L6:
        if (i >= j) goto _L5; else goto _L4
_L4:
        obj = (Module)modules.get(i);
        ((Module) (obj)).configure(context, class1, this);
        if (Flags.get(EXTRA_VERIFICATION))
        {
            break MISSING_BLOCK_LABEL_366;
        }
        obj = bindings.get(class1);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_366;
        }
        obj2 = UNBOUND;
        if (obj == obj2)
        {
            break MISSING_BLOCK_LABEL_366;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        isInternallyBinding.set(Boolean.valueOf(false));
        obj1;
        JVM INSTR monitorexit ;
        return obj;
        class1;
        obj1;
        JVM INSTR monitorexit ;
        throw class1;
_L2:
        flag = false;
          goto _L3
        class1;
        throw class1;
        class1;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_240;
        }
        isInternallyBinding.set(Boolean.valueOf(false));
        throw class1;
_L5:
        if (flag)
        {
            break MISSING_BLOCK_LABEL_264;
        }
        isInternallyBinding.set(Boolean.valueOf(false));
        if (!IS_STRICT_MODE_ALLOWED);
        obj = bindings.get(class1);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_357;
        }
        if (Flags.get(EXTRA_VERIFICATION) && multiBindings.containsKey(class1))
        {
            class1 = String.valueOf(class1);
            throw new IllegalStateException((new StringBuilder(String.valueOf(class1).length() + 36)).append("get() called for multibound object: ").append(class1).toString());
        }
        bindings.put(class1, UNBOUND);
        obj1;
        JVM INSTR monitorexit ;
        return obj;
        i++;
          goto _L6
    }

    public final List getChain(Class class1)
    {
        if (class1 != null)
        {
            break MISSING_BLOCK_LABEL_15;
        }
        throw new NullPointerException();
        class1;
        throw class1;
        ArrayList arraylist = new ArrayList();
        Binder binder = this;
_L2:
        Object obj = binder.getInstance(class1);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        arraylist.add(obj);
        obj = binder.parent;
        binder = ((Binder) (obj));
        if (obj == null)
        {
            return arraylist;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final Object getOptional(Class class1)
    {
        Binder binder;
        binder = this;
        if (class1 != null)
        {
            break MISSING_BLOCK_LABEL_17;
        }
        throw new NullPointerException();
        class1;
        throw class1;
_L2:
        Object obj = binder.getInstance(class1);
        if (obj != null)
        {
            return obj;
        }
        obj = binder.parent;
        binder = ((Binder) (obj));
        if (obj == null)
        {
            return null;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

}
