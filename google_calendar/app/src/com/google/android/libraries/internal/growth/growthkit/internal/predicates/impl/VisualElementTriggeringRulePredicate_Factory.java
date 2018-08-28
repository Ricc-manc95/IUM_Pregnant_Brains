// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.predicates.impl:
//            VisualElementTriggeringRulePredicate

public final class VisualElementTriggeringRulePredicate_Factory
    implements Factory
{

    public static final VisualElementTriggeringRulePredicate_Factory INSTANCE = new VisualElementTriggeringRulePredicate_Factory();

    public VisualElementTriggeringRulePredicate_Factory()
    {
    }

    public final Object get()
    {
        return new VisualElementTriggeringRulePredicate();
    }

}
