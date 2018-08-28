// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Matrix;
import android.view.View;

class ViewUtilsBase
{

    ViewUtilsBase()
    {
    }

    public float getTransitionAlpha(View view)
    {
        Float float1 = (Float)view.getTag(0x7f100035);
        if (float1 != null)
        {
            return view.getAlpha() / float1.floatValue();
        } else
        {
            return view.getAlpha();
        }
    }

    public void setLeftTopRightBottom(View view, int i, int j, int k, int l)
    {
        view.setLeft(i);
        view.setTop(j);
        view.setRight(k);
        view.setBottom(l);
    }

    public void setTransitionAlpha(View view, float f)
    {
        Float float1 = (Float)view.getTag(0x7f100035);
        if (float1 != null)
        {
            view.setAlpha(float1.floatValue() * f);
            return;
        } else
        {
            view.setAlpha(f);
            return;
        }
    }

    public void transformMatrixToGlobal(View view, Matrix matrix)
    {
        Object obj = view.getParent();
        if (obj instanceof View)
        {
            obj = (View)obj;
            transformMatrixToGlobal(((View) (obj)), matrix);
            matrix.preTranslate(-((View) (obj)).getScrollX(), -((View) (obj)).getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        view = view.getMatrix();
        if (!view.isIdentity())
        {
            matrix.preConcat(view);
        }
    }

    public void transformMatrixToLocal(View view, Matrix matrix)
    {
        Object obj = view.getParent();
        if (obj instanceof View)
        {
            obj = (View)obj;
            transformMatrixToLocal(((View) (obj)), matrix);
            matrix.postTranslate(((View) (obj)).getScrollX(), ((View) (obj)).getScrollY());
        }
        matrix.postTranslate(view.getLeft(), view.getTop());
        view = view.getMatrix();
        if (!view.isIdentity())
        {
            Matrix matrix1 = new Matrix();
            if (view.invert(matrix1))
            {
                matrix.postConcat(matrix1);
            }
        }
    }
}
