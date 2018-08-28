// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

final class RectEvaluator
    implements TypeEvaluator
{

    RectEvaluator()
    {
    }

    public final Object evaluate(float f, Object obj, Object obj1)
    {
        obj = (Rect)obj;
        obj1 = (Rect)obj1;
        return new Rect(((Rect) (obj)).left + (int)((float)(((Rect) (obj1)).left - ((Rect) (obj)).left) * f), ((Rect) (obj)).top + (int)((float)(((Rect) (obj1)).top - ((Rect) (obj)).top) * f), ((Rect) (obj)).right + (int)((float)(((Rect) (obj1)).right - ((Rect) (obj)).right) * f), ((Rect) (obj)).bottom + (int)((float)(((Rect) (obj1)).bottom - ((Rect) (obj)).bottom) * f));
    }
}
