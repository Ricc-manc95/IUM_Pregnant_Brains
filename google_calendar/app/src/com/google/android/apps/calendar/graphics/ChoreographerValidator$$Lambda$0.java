// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics;


// Referenced classes of package com.google.android.apps.calendar.graphics:
//            ChoreographerValidator

final class arg._cls2
    implements android.view.eographerValidator..Lambda._cls0
{

    private final ChoreographerValidator arg$1;
    private final Runnable arg$2;

    public final void doFrame(long l)
    {
        ChoreographerValidator choreographervalidator = arg$1;
        arg$2.run();
        choreographervalidator.isValid = true;
    }

    (ChoreographerValidator choreographervalidator, Runnable runnable)
    {
        arg$1 = choreographervalidator;
        arg$2 = runnable;
    }
}
