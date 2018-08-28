// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Optional;

public final class Optionals
{

    public static void ifPresent(Optional optional, Consumer consumer)
    {
        class .Lambda._cls0
            implements Runnable
        {

            public static final Runnable $instance = new .Lambda._cls0();

            public final void run()
            {
            }


            private .Lambda._cls0()
            {
            }
        }

        Runnable runnable = .Lambda._cls0..instance;
        optional = ((Optional) (optional.orNull()));
        if (optional != null)
        {
            consumer.accept(optional);
            return;
        } else
        {
            runnable.run();
            return;
        }
    }
}
