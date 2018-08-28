// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v7.graphics:
//            Palette, Target

public final class D
{

    public final Bitmap mBitmap;
    public final List mFilters = new ArrayList();
    public int mMaxColors;
    public int mResizeArea;
    public int mResizeMaxDimension;
    private final List mSwatches;
    public final List mTargets = new ArrayList();

    public (Bitmap bitmap)
    {
        mMaxColors = 16;
        mResizeArea = 12544;
        mResizeMaxDimension = -1;
        if (bitmap == null || bitmap.isRecycled())
        {
            throw new IllegalArgumentException("Bitmap is not valid");
        } else
        {
            mFilters.add(Palette.DEFAULT_FILTER);
            mBitmap = bitmap;
            mSwatches = null;
            mTargets.add(Target.LIGHT_VIBRANT);
            mTargets.add(Target.VIBRANT);
            mTargets.add(Target.DARK_VIBRANT);
            mTargets.add(Target.LIGHT_MUTED);
            mTargets.add(Target.MUTED);
            mTargets.add(Target.DARK_MUTED);
            return;
        }
    }
}
