// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            AccessibilityVirtualView

abstract class ndler
{

    abstract ndler actionHandler(ndler ndler);

    abstract ndler bottom(int i);

    public abstract AccessibilityVirtualView build();

    abstract ndler canScrollBackward(Boolean boolean1);

    abstract ndler canScrollForward(Boolean boolean1);

    abstract ndler clickHandler(Runnable runnable);

    abstract ndler contentDescription(CharSequence charsequence);

    abstract ndler id(int i);

    abstract ndler left(int i);

    abstract ndler parentId(Integer integer);

    abstract ndler right(int i);

    abstract ndler top(int i);

    abstract ndler traversalAfter(Integer integer);

    abstract ndler traversalBefore(Integer integer);

    public abstract ndler type(ndler ndler);

    abstract ndler zOrder(int i);

    ndler()
    {
    }
}
