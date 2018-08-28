// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

final class tyNodeInfoCompat
    implements nAdapter
{

    public final Object get(Object obj, int i)
    {
        obj = (SparseArrayCompat)obj;
        if (((SparseArrayCompat) (obj)).mGarbage)
        {
            ((SparseArrayCompat) (obj)).gc();
        }
        return (AccessibilityNodeInfoCompat)((SparseArrayCompat) (obj)).mValues[i];
    }

    public final int size(Object obj)
    {
        obj = (SparseArrayCompat)obj;
        if (((SparseArrayCompat) (obj)).mGarbage)
        {
            ((SparseArrayCompat) (obj)).gc();
        }
        return ((SparseArrayCompat) (obj)).mSize;
    }

    tyNodeInfoCompat()
    {
    }
}
