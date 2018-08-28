// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.graphics.Outline;

// Referenced classes of package android.support.design.internal:
//            CircularBorderDrawable

public final class CircularBorderDrawableLollipop extends CircularBorderDrawable
{

    public CircularBorderDrawableLollipop()
    {
    }

    public final void getOutline(Outline outline)
    {
        copyBounds(rect);
        outline.setOval(rect);
    }
}
