// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import javax.inject.Provider;

// Referenced classes of package com.google.android.calendar:
//            DaggerCalendarApplicationComponent

final class this._cls0
    implements Provider
{

    private final DaggerCalendarApplicationComponent this$0;

    public final Object get()
    {
        return new lInOneCalendarActivitySubcomponentBuilder(DaggerCalendarApplicationComponent.this);
    }

    lInOneCalendarActivitySubcomponentBuilder()
    {
        this$0 = DaggerCalendarApplicationComponent.this;
        super();
    }
}
