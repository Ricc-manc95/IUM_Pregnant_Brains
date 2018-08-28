// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Path;

// Referenced classes of package android.support.transition:
//            PathMotion

final class  extends PathMotion
{

    public final Path getPath(float f, float f1, float f2, float f3)
    {
        Path path = new Path();
        path.moveTo(f, f1);
        path.lineTo(f2, f3);
        return path;
    }

    ()
    {
    }
}
