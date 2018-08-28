// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

// Referenced classes of package android.support.v4.app:
//            Fragment

final class this._cls0
    implements LifecycleOwner
{

    private final Fragment this$0;

    public final Lifecycle getLifecycle()
    {
        if (mViewLifecycleRegistry == null)
        {
            mViewLifecycleRegistry = new LifecycleRegistry(mViewLifecycleOwner);
        }
        return mViewLifecycleRegistry;
    }

    gistry()
    {
        this$0 = Fragment.this;
        super();
    }
}
