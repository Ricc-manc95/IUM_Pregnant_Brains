// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.impl.adapter.AdapterDay;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.FluentFuture;

final class arg._cls4
    implements Function
{

    private final <init> arg$1;
    private final AdapterDay arg$2;
    private final boolean arg$3;
    private final Integer arg$4;

    public final Object apply(Object obj)
    {
        arg._cls4 _lcls4 = arg$1;
        AdapterDay adapterday = arg$2;
        boolean flag = arg$3;
        Integer integer = arg$4;
        return (FluentFuture)AbstractTransformFuture.create((FluentFuture)obj, new <init>(_lcls4, adapterday, flag, integer), com.google.common.util.concurrent.TANCE);
    }

    q(q q, AdapterDay adapterday, boolean flag, Integer integer)
    {
        arg$1 = q;
        arg$2 = adapterday;
        arg$3 = flag;
        arg$4 = integer;
    }
}
