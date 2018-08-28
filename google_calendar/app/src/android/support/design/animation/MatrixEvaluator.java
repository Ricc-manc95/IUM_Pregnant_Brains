// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public final class MatrixEvaluator
    implements TypeEvaluator
{

    private final float tempEndValues[] = new float[9];
    private final Matrix tempMatrix = new Matrix();
    private final float tempStartValues[] = new float[9];

    public MatrixEvaluator()
    {
    }

    public final Object evaluate(float f, Object obj, Object obj1)
    {
        obj = (Matrix)obj;
        obj1 = (Matrix)obj1;
        ((Matrix) (obj)).getValues(tempStartValues);
        ((Matrix) (obj1)).getValues(tempEndValues);
        for (int i = 0; i < 9; i++)
        {
            float f1 = tempEndValues[i];
            float f2 = tempStartValues[i];
            tempEndValues[i] = (f1 - f2) * f + tempStartValues[i];
        }

        tempMatrix.setValues(tempEndValues);
        return tempMatrix;
    }
}
