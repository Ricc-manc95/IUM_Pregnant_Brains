// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;

// Referenced classes of package android.support.v4.app:
//            FragmentTransitionCompat21

final class t> extends android.transition._cls1._cls4
{

    private final Rect val$epicenter;

    public final Rect onGetEpicenter(Transition transition)
    {
        if (val$epicenter == null || val$epicenter.isEmpty())
        {
            return null;
        } else
        {
            return val$epicenter;
        }
    }

    _cls9(Rect rect)
    {
        val$epicenter = rect;
        super();
    }
}
