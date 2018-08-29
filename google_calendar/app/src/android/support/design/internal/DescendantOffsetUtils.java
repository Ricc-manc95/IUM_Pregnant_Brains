// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public final class DescendantOffsetUtils
{

    private static final ThreadLocal matrix = new ThreadLocal();
    private static final ThreadLocal rectF = new ThreadLocal();

    public static void getDescendantRect(ViewGroup viewgroup, View view, Rect rect)
    {
        rect.set(0, 0, view.getWidth(), view.getHeight());
        Matrix matrix1 = (Matrix)matrix.get();
        if (matrix1 == null)
        {
            matrix1 = new Matrix();
            matrix.set(matrix1);
        } else
        {
            matrix1.reset();
        }
        offsetDescendantMatrix(viewgroup, view, matrix1);
        view = (RectF)rectF.get();
        viewgroup = view;
        if (view == null)
        {
            viewgroup = new RectF();
            rectF.set(viewgroup);
        }
        viewgroup.set(rect);
        matrix1.mapRect(viewgroup);
        rect.set((int)(((RectF) (viewgroup)).left + 0.5F), (int)(((RectF) (viewgroup)).top + 0.5F), (int)(((RectF) (viewgroup)).right + 0.5F), (int)(((RectF) (viewgroup)).bottom + 0.5F));
    }

    private static void offsetDescendantMatrix(ViewParent viewparent, View view, Matrix matrix1)
    {
        Object obj = view.getParent();
        if ((obj instanceof View) && obj != viewparent)
        {
            obj = (View)obj;
            offsetDescendantMatrix(viewparent, ((View) (obj)), matrix1);
            matrix1.preTranslate(-((View) (obj)).getScrollX(), -((View) (obj)).getScrollY());
        }
        matrix1.preTranslate(view.getLeft(), view.getTop());
        if (!view.getMatrix().isIdentity())
        {
            matrix1.preConcat(view.getMatrix());
        }
    }

}