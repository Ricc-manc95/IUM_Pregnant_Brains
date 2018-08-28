// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.stitch.binder;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.google.android.libraries.stitch.binder:
//            RootModule, Binder, AndroidManifestModule

public final class RootBinderInitializer
    implements BinderProvider.Initializer
{

    public RootBinderInitializer()
    {
    }

    public final void initBinder(Context context, Binder binder)
    {
        Object obj;
        Object obj1;
        obj1 = (RootModule)Class.forName("gen_binder.root.RootModule$Generated").newInstance();
        obj = ((RootModule) (obj1)).getSourceModuleNames();
        boolean flag = binder.sealed;
        binder.modules.add(obj1);
        ((RootModule) (obj1)).bindConstants$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TPN8QBKCDK2UOJ9DPI6ASHF89KMSP35E8TIILG_0();
        obj1 = obj;
_L2:
        context = new AndroidManifestModule(context, ((java.util.Set) (obj1)));
        boolean flag1 = binder.sealed;
        binder.modules.add(context);
        return;
        context;
        throw new IllegalStateException("Failed to instantiate root module gen_binder.root.RootModule$Generated", context);
        context;
        throw new IllegalStateException("Failed to instantiate root module gen_binder.root.RootModule$Generated", context);
        obj;
        obj = null;
_L3:
        obj1 = obj;
        if (Log.isLoggable("Binder", 5))
        {
            Log.w("Binder", "To use Binder more efficiently, your android_binary target should include \"//java/com/google/android/libraries/stitch/binder:rootmodule\" in srcs.");
            obj1 = obj;
        }
        if (true) goto _L2; else goto _L1
_L1:
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
          goto _L3
    }
}
