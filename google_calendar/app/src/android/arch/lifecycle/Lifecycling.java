// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.arch.lifecycle:
//            GeneratedAdapter, FullLifecycleObserver, FullLifecycleObserverAdapter, GenericLifecycleObserver, 
//            SingleGeneratedAdapterObserver, CompositeGeneratedAdaptersObserver, ReflectiveGenericLifecycleObserver, ClassesInfoCache, 
//            LifecycleObserver, OnLifecycleEvent

public final class Lifecycling
{

    private static Map sCallbackCache = new HashMap();
    private static Map sClassToAdapters = new HashMap();

    private static GeneratedAdapter createGeneratedAdapter(Constructor constructor, Object obj)
    {
        try
        {
            constructor = (GeneratedAdapter)constructor.newInstance(new Object[] {
                obj
            });
        }
        // Misplaced declaration of an exception variable
        catch (Constructor constructor)
        {
            throw new RuntimeException(constructor);
        }
        // Misplaced declaration of an exception variable
        catch (Constructor constructor)
        {
            throw new RuntimeException(constructor);
        }
        // Misplaced declaration of an exception variable
        catch (Constructor constructor)
        {
            throw new RuntimeException(constructor);
        }
        return constructor;
    }

    private static Constructor generatedConstructor(Class class1)
    {
        Object obj;
        String s;
        try
        {
            obj = class1.getPackage();
            s = class1.getCanonicalName();
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            return null;
        }
        // Misplaced declaration of an exception variable
        catch (Class class1)
        {
            throw new RuntimeException(class1);
        }
        if (obj == null) goto _L2; else goto _L1
_L1:
        obj = ((Package) (obj)).getName();
_L9:
        if (!((String) (obj)).isEmpty()) goto _L4; else goto _L3
_L3:
        s = (new StringBuilder()).append(s.replace(".", "_")).append("_LifecycleAdapter").toString();
        if (!((String) (obj)).isEmpty()) goto _L6; else goto _L5
_L5:
        obj = s;
_L8:
        class1 = Class.forName(((String) (obj))).getDeclaredConstructor(new Class[] {
            class1
        });
        if (class1.isAccessible())
        {
            break; /* Loop/switch isn't completed */
        }
        class1.setAccessible(true);
        return class1;
_L4:
        s = s.substring(((String) (obj)).length() + 1);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = (new StringBuilder()).append(((String) (obj))).append(".").append(s).toString();
        if (true) goto _L8; else goto _L7
_L7:
        return class1;
        if (true) goto _L3; else goto _L2
_L2:
        obj = "";
          goto _L9
    }

    static GenericLifecycleObserver getCallback(Object obj)
    {
        if (obj instanceof FullLifecycleObserver)
        {
            return new FullLifecycleObserverAdapter((FullLifecycleObserver)obj);
        }
        if (obj instanceof GenericLifecycleObserver)
        {
            return (GenericLifecycleObserver)obj;
        }
        Object obj1 = obj.getClass();
        if (getObserverConstructorType(((Class) (obj1))) == 2)
        {
            obj1 = (List)sClassToAdapters.get(obj1);
            if (((List) (obj1)).size() == 1)
            {
                return new SingleGeneratedAdapterObserver(createGeneratedAdapter((Constructor)((List) (obj1)).get(0), obj));
            }
            GeneratedAdapter ageneratedadapter[] = new GeneratedAdapter[((List) (obj1)).size()];
            for (int i = 0; i < ((List) (obj1)).size(); i++)
            {
                ageneratedadapter[i] = createGeneratedAdapter((Constructor)((List) (obj1)).get(i), obj);
            }

            return new CompositeGeneratedAdaptersObserver(ageneratedadapter);
        } else
        {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
    }

    private static int getObserverConstructorType(Class class1)
    {
        if (sCallbackCache.containsKey(class1))
        {
            return ((Integer)sCallbackCache.get(class1)).intValue();
        }
        if (class1.getCanonicalName() == null) goto _L2; else goto _L1
_L1:
        Object obj = generatedConstructor(class1);
        if (obj == null) goto _L4; else goto _L3
_L3:
        int i;
        sClassToAdapters.put(class1, Collections.singletonList(obj));
        i = 2;
_L20:
        sCallbackCache.put(class1, Integer.valueOf(i));
        return i;
_L4:
        obj = ClassesInfoCache.sInstance;
        if (!((ClassesInfoCache) (obj)).mHasLifecycleMethods.containsKey(class1)) goto _L6; else goto _L5
_L5:
        boolean flag = ((Boolean)((ClassesInfoCache) (obj)).mHasLifecycleMethods.get(class1)).booleanValue();
_L17:
        if (flag) goto _L2; else goto _L7
_L7:
        Class class2 = class1.getSuperclass();
        obj = null;
        Object aobj[];
        Class class3;
        int k;
        if (class2 != null && android/arch/lifecycle/LifecycleObserver.isAssignableFrom(class2))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L9; else goto _L8
_L8:
        if (getObserverConstructorType(class2) == 1) goto _L2; else goto _L10
_L10:
        obj = new ArrayList((Collection)sClassToAdapters.get(class2));
_L9:
        aobj = class1.getInterfaces();
        k = aobj.length;
        i = 0;
_L16:
        if (i >= k) goto _L12; else goto _L11
_L11:
        class3 = aobj[i];
        int j;
        if (class3 != null && android/arch/lifecycle/LifecycleObserver.isAssignableFrom(class3))
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L14; else goto _L13
_L13:
        if (getObserverConstructorType(class3) == 1) goto _L2; else goto _L15
_L15:
        if (obj == null)
        {
            obj = new ArrayList();
        }
        ((List) (obj)).addAll((Collection)sClassToAdapters.get(class3));
_L14:
        i++;
          goto _L16
_L6:
        aobj = ClassesInfoCache.getDeclaredMethods(class1);
        j = aobj.length;
        i = 0;
_L18:
label0:
        {
            if (i >= j)
            {
                break MISSING_BLOCK_LABEL_324;
            }
            if ((OnLifecycleEvent)aobj[i].getAnnotation(android/arch/lifecycle/OnLifecycleEvent) == null)
            {
                break label0;
            }
            ((ClassesInfoCache) (obj)).createInfo(class1, ((Method []) (aobj)));
            flag = true;
        }
          goto _L17
        i++;
          goto _L18
        ((ClassesInfoCache) (obj)).mHasLifecycleMethods.put(class1, Boolean.valueOf(false));
        flag = false;
          goto _L17
_L12:
        if (obj != null)
        {
            sClassToAdapters.put(class1, obj);
            i = 2;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        i = 1;
        if (true) goto _L20; else goto _L19
_L19:
    }

}
