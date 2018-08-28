// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

// Referenced classes of package android.support.transition:
//            ViewUtils, ViewUtilsBase

final class  extends Property
{

    public final volatile Object get(Object obj)
    {
        return null;
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (View)obj;
        obj1 = (PointF)obj1;
        int i = ((View) (obj)).getLeft();
        int j = ((View) (obj)).getTop();
        int k = Math.round(((PointF) (obj1)).x);
        int l = Math.round(((PointF) (obj1)).y);
        ViewUtils.IMPL.setLeftTopRightBottom(((View) (obj)), i, j, k, l);
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
