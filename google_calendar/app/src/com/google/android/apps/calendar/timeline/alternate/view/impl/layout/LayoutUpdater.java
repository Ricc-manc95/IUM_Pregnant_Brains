// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import com.google.common.util.concurrent.FluentFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            LayoutItemParams

public interface LayoutUpdater
{

    public abstract void addItem(LayoutItemParams layoutitemparams);

    public abstract FluentFuture finish(boolean flag, boolean flag1);
}
