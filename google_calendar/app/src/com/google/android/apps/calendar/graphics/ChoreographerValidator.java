// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics;

import android.view.Choreographer;

public final class ChoreographerValidator
{

    public boolean isValid;
    public final android.view.Choreographer.FrameCallback validateFrameCallback;

    public ChoreographerValidator(Runnable runnable)
    {
        class .Lambda._cls0
            implements android.view.Choreographer.FrameCallback
        {

            private final ChoreographerValidator arg$1;
            private final Runnable arg$2;

            public final void doFrame(long l)
            {
                ChoreographerValidator choreographervalidator = arg$1;
                arg$2.run();
                choreographervalidator.isValid = true;
            }

            .Lambda._cls0(Runnable runnable)
            {
                arg$1 = ChoreographerValidator.this;
                arg$2 = runnable;
            }
        }

        validateFrameCallback = new .Lambda._cls0(runnable);
        Choreographer.getInstance().postFrameCallback(validateFrameCallback);
    }
}
