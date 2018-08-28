// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.animation.TypeEvaluator;
import android.support.v4.graphics.PathParser;

final class 
    implements TypeEvaluator
{

    private android.support.v4.graphics.uator.mNodeArray mNodeArray[];

    public final Object evaluate(float f, Object obj, Object obj1)
    {
        obj = (android.support.v4.graphics.uator[])obj;
        obj1 = (android.support.v4.graphics.uator[])obj1;
        if (!PathParser.canMorph(((android.support.v4.graphics.uator []) (obj)), ((android.support.v4.graphics.uator []) (obj1))))
        {
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
        if (mNodeArray == null || !PathParser.canMorph(mNodeArray, ((android.support.v4.graphics.uator.mNodeArray []) (obj))))
        {
            mNodeArray = PathParser.deepCopyNodes(((android.support.v4.graphics.uator.mNodeArray []) (obj)));
        }
        for (int i = 0; i < obj.length; i++)
        {
            android.support.v4.graphics.uator uator = mNodeArray[i];
            Object obj2 = obj[i];
            Object obj3 = obj1[i];
            for (int j = 0; j < ((android.support.v4.graphics.uator.mNodeArray) (obj2)).mNodeArray.length; j++)
            {
                uator.mNodeArray[j] = ((android.support.v4.graphics.uator.mNodeArray) (obj2)).mNodeArray[j] * (1.0F - f) + ((android.support.v4.graphics.uator.mNodeArray) (obj3)).mNodeArray[j] * f;
            }

        }

        return mNodeArray;
    }

    ()
    {
    }
}
