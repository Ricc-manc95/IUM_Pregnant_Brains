// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import java.util.List;
import java.util.Map;

// Referenced classes of package android.arch.lifecycle:
//            GenericLifecycleObserver, ClassesInfoCache, LifecycleOwner

final class ReflectiveGenericLifecycleObserver
    implements GenericLifecycleObserver
{

    private final ClassesInfoCache.CallbackInfo mInfo;
    private final Object mWrapped;

    ReflectiveGenericLifecycleObserver(Object obj)
    {
        mWrapped = obj;
        ClassesInfoCache classesinfocache = ClassesInfoCache.sInstance;
        Class class1 = mWrapped.getClass();
        obj = (ClassesInfoCache.CallbackInfo)classesinfocache.mCallbackMap.get(class1);
        if (obj == null)
        {
            obj = classesinfocache.createInfo(class1, null);
        }
        mInfo = ((ClassesInfoCache.CallbackInfo) (obj));
    }

    public final void onStateChanged(LifecycleOwner lifecycleowner, Lifecycle.Event event)
    {
        ClassesInfoCache.CallbackInfo callbackinfo = mInfo;
        Object obj = mWrapped;
        ClassesInfoCache.CallbackInfo.invokeMethodsForEvent((List)callbackinfo.mEventToHandlers.get(event), lifecycleowner, event, obj);
        ClassesInfoCache.CallbackInfo.invokeMethodsForEvent((List)callbackinfo.mEventToHandlers.get(Lifecycle.Event.ON_ANY), lifecycleowner, event, obj);
    }
}
