// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import dagger.internal.Factory;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs:
//            DialogRenderer

public final class DialogRenderer_Factory
    implements Factory
{

    public static final DialogRenderer_Factory INSTANCE = new DialogRenderer_Factory();

    public DialogRenderer_Factory()
    {
    }

    public final Object get()
    {
        return new DialogRenderer();
    }

}
