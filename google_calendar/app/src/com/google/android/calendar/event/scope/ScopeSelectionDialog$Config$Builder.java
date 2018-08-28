// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.os.Bundle;
import java.util.List;

public abstract class Q
{

    public abstract Q additionalArguments(Bundle bundle);

    public abstract Q build();

    public abstract Q positiveButton(int i);

    public abstract Q scopes(List list);

    public abstract Q title(int i);

    public Q()
    {
    }
}
