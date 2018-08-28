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
        int i = Math.round(((PointF) (obj1)).x);
        int j = Math.round(((PointF) (obj1)).y);
        int k = ((View) (obj)).getWidth();
        int l = ((View) (obj)).getHeight();
        ViewUtils.IMPL.setLeftTopRightBottom(((View) (obj)), i, j, i + k, j + l);
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
