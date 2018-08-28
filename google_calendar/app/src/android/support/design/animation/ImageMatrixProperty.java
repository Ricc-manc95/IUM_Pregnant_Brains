// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.animation;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

public final class ImageMatrixProperty extends Property
{

    private final Matrix matrix = new Matrix();

    public ImageMatrixProperty()
    {
        super(android/graphics/Matrix, "imageMatrixProperty");
    }

    public final Object get(Object obj)
    {
        obj = (ImageView)obj;
        matrix.set(((ImageView) (obj)).getImageMatrix());
        return matrix;
    }

    public final void set(Object obj, Object obj1)
    {
        ((ImageView)obj).setImageMatrix((Matrix)obj1);
    }
}
