// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.arch.lifecycle:
//            OnLifecycleEvent, LifecycleOwner

final class ClassesInfoCache
{

    public static ClassesInfoCache sInstance = new ClassesInfoCache();
    public final Map mCallbackMap = new HashMap();
    public final Map mHasLifecycleMethods = new HashMap();

    ClassesInfoCache()
    {
    }

    static Method[] getDeclaredMethods(Class class1)
    {
        try
        {
            class1 = class1.getDeclaredMethods();
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", class1);
        }
        return class1;
    }

    private static void verifyAndPutHandler(Map map, MethodReference methodreference, Lifecycle.Event event, Class class1)
    {
        Lifecycle.Event event1 = (Lifecycle.Event)map.get(methodreference);
        if (event1 != null && event != event1)
        {
            map = methodreference.mMethod;
            throw new IllegalArgumentException((new StringBuilder("Method ")).append(map.getName()).append(" in ").append(class1.getName()).append(" already declared with different @OnLifecycleEvent value: previous value ").append(event1).append(", new value ").append(event).toString());
        }
        if (event1 == null)
        {
            map.put(methodreference, event);
        }
    }

    final CallbackInfo createInfo(Class class1, Method amethod[])
    {
        Object obj1 = class1.getSuperclass();
        HashMap hashmap = new HashMap();
        Object obj;
        if (obj1 != null)
        {
            obj = (CallbackInfo)mCallbackMap.get(obj1);
            int j;
            if (obj == null)
            {
                obj = createInfo(((Class) (obj1)), null);
            }
            if (obj != null)
            {
                hashmap.putAll(((CallbackInfo) (obj)).mHandlerToEvent);
            }
        }
        obj1 = class1.getInterfaces();
        j = obj1.length;
        java.util.Map.Entry entry;
        for (int i = 0; i < j; i++)
        {
            entry = obj1[i];
            obj = (CallbackInfo)mCallbackMap.get(entry);
            if (obj == null)
            {
                obj = createInfo(((Class) (entry)), null);
            }
            for (obj = ((CallbackInfo) (obj)).mHandlerToEvent.entrySet().iterator(); ((Iterator) (obj)).hasNext(); verifyAndPutHandler(hashmap, (MethodReference)entry.getKey(), (Lifecycle.Event)entry.getValue(), class1))
            {
                entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            }

        }

        int k;
        int l;
        boolean flag;
        if (amethod == null)
        {
            amethod = getDeclaredMethods(class1);
        }
        l = amethod.length;
        k = 0;
        flag = false;
        while (k < l) 
        {
            Method method = amethod[k];
            Object obj2 = (OnLifecycleEvent)method.getAnnotation(android/arch/lifecycle/OnLifecycleEvent);
            if (obj2 == null)
            {
                continue;
            }
            Class aclass[] = method.getParameterTypes();
            byte byte0;
            if (aclass.length > 0)
            {
                if (!aclass[0].isAssignableFrom(android/arch/lifecycle/LifecycleOwner))
                {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }
                byte0 = 1;
            } else
            {
                byte0 = 0;
            }
            obj2 = ((OnLifecycleEvent) (obj2)).value();
            if (aclass.length > 1)
            {
                if (!aclass[1].isAssignableFrom(android/arch/lifecycle/Lifecycle$Event))
                {
                    throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                }
                if (obj2 != Lifecycle.Event.ON_ANY)
                {
                    throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                }
                byte0 = 2;
            }
            if (aclass.length > 2)
            {
                throw new IllegalArgumentException("cannot have more than 2 params");
            }
            verifyAndPutHandler(hashmap, new MethodReference(byte0, method), ((Lifecycle.Event) (obj2)), class1);
            flag = true;
            k++;
        }
        amethod = new CallbackInfo(hashmap);
        mCallbackMap.put(class1, amethod);
        mHasLifecycleMethods.put(class1, Boolean.valueOf(flag));
        return amethod;
    }


    private class MethodReference
    {

        public final int mCallType;
        public final Method mMethod;

        public final boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj == null || getClass() != obj.getClass())
                {
                    return false;
                }
                obj = (MethodReference)obj;
                if (mCallType != ((MethodReference) (obj)).mCallType || !mMethod.getName().equals(((MethodReference) (obj)).mMethod.getName()))
                {
                    return false;
                }
            }
            return true;
        }

        public final int hashCode()
        {
            return mCallType * 31 + mMethod.getName().hashCode();
        }

        MethodReference(int i, Method method)
        {
            mCallType = i;
            mMethod = method;
            mMethod.setAccessible(true);
        }
    }


    private class CallbackInfo
    {

        public final Map mEventToHandlers = new HashMap();
        public final Map mHandlerToEvent;

        static void invokeMethodsForEvent(List list, LifecycleOwner lifecycleowner, Lifecycle.Event event, Object obj)
        {
            if (list == null) goto _L2; else goto _L1
_L1:
            int i = list.size() - 1;
_L9:
            if (i < 0) goto _L2; else goto _L3
_L3:
            MethodReference methodreference = (MethodReference)list.get(i);
            methodreference.mCallType;
            JVM INSTR tableswitch 0 2: default 153
        //                       0 64
        //                       1 96
        //                       2 127;
               goto _L4 _L5 _L6 _L7
_L5:
            methodreference.mMethod.invoke(obj, new Object[0]);
              goto _L4
_L6:
            try
            {
                methodreference.mMethod.invoke(obj, new Object[] {
                    lifecycleowner
                });
            }
            // Misplaced declaration of an exception variable
            catch (List list)
            {
                throw new RuntimeException("Failed to call observer method", list.getCause());
            }
            // Misplaced declaration of an exception variable
            catch (List list)
            {
                throw new RuntimeException(list);
            }
              goto _L4
_L7:
            methodreference.mMethod.invoke(obj, new Object[] {
                lifecycleowner, event
            });
              goto _L4
_L2:
            return;
_L4:
            i--;
            if (true) goto _L9; else goto _L8
_L8:
        }

        CallbackInfo(Map map)
        {
            mHandlerToEvent = map;
            java.util.Map.Entry entry;
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); map.add(entry.getKey()))
            {
                entry = (java.util.Map.Entry)iterator.next();
                Lifecycle.Event event = (Lifecycle.Event)entry.getValue();
                List list = (List)mEventToHandlers.get(event);
                map = list;
                if (list == null)
                {
                    map = new ArrayList();
                    mEventToHandlers.put(event, map);
                }
            }

        }
    }

}
