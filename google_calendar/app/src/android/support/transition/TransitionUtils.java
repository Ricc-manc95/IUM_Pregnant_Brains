// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.ImageView;

// Referenced classes of package android.support.transition:
//            ViewUtils, ViewUtilsBase

final class TransitionUtils
{

    private static final boolean HAS_IS_ATTACHED_TO_WINDOW = true;
    private static final boolean HAS_OVERLAY = true;
    private static final boolean HAS_PICTURE_BITMAP;

    static View copyViewImage(ViewGroup viewgroup, View view, View view1)
    {
        Object obj;
        Matrix matrix;
        RectF rectf;
        boolean flag;
        matrix = new Matrix();
        matrix.setTranslate(-view1.getScrollX(), -view1.getScrollY());
        ViewUtils.IMPL.transformMatrixToGlobal(view, matrix);
        ViewUtils.IMPL.transformMatrixToLocal(viewgroup, matrix);
        rectf = new RectF(0.0F, 0.0F, view.getWidth(), view.getHeight());
        matrix.mapRect(rectf);
        int j = Math.round(rectf.left);
        int k = Math.round(rectf.top);
        int l = Math.round(rectf.right);
        int i1 = Math.round(rectf.bottom);
        ImageView imageview = new ImageView(view.getContext());
        imageview.setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
        boolean flag1;
        if (HAS_IS_ATTACHED_TO_WINDOW)
        {
            if (!view.isAttachedToWindow())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (viewgroup == null)
            {
                flag1 = false;
            } else
            {
                flag1 = viewgroup.isAttachedToWindow();
            }
        } else
        {
            flag = false;
            flag1 = false;
        }
        if (!HAS_OVERLAY || !flag) goto _L2; else goto _L1
_L1:
        if (flag1) goto _L4; else goto _L3
_L3:
        obj = null;
_L5:
        if (obj != null)
        {
            imageview.setImageBitmap(((Bitmap) (obj)));
        }
        imageview.measure(android.view.View.MeasureSpec.makeMeasureSpec(l - j, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(i1 - k, 0x40000000));
        imageview.layout(j, k, l, i1);
        return imageview;
_L4:
        ViewGroup viewgroup1;
        int i;
        viewgroup1 = (ViewGroup)view.getParent();
        i = viewgroup1.indexOfChild(view);
        viewgroup.getOverlay().add(view);
_L6:
        obj = null;
        int k1 = Math.round(rectf.width());
        int j1 = Math.round(rectf.height());
        view1 = ((View) (obj));
        if (k1 > 0)
        {
            view1 = ((View) (obj));
            if (j1 > 0)
            {
                float f = Math.min(1.0F, 1048576F / (float)(k1 * j1));
                k1 = Math.round((float)k1 * f);
                j1 = Math.round((float)j1 * f);
                matrix.postTranslate(-rectf.left, -rectf.top);
                matrix.postScale(f, f);
                if (HAS_PICTURE_BITMAP)
                {
                    view1 = new Picture();
                    obj = view1.beginRecording(k1, j1);
                    ((Canvas) (obj)).concat(matrix);
                    view.draw(((Canvas) (obj)));
                    view1.endRecording();
                    view1 = Bitmap.createBitmap(view1);
                } else
                {
                    view1 = Bitmap.createBitmap(k1, j1, android.graphics.Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(view1);
                    canvas.concat(matrix);
                    view.draw(canvas);
                }
            }
        }
        obj = view1;
        if (HAS_OVERLAY)
        {
            obj = view1;
            if (flag)
            {
                viewgroup.getOverlay().remove(view);
                viewgroup1.addView(view, i);
                obj = view1;
            }
        }
        if (true) goto _L5; else goto _L2
_L2:
        i = 0;
        viewgroup1 = null;
          goto _L6
    }

    static 
    {
        boolean flag = true;
        if (android.os.Build.VERSION.SDK_INT < 28)
        {
            flag = false;
        }
        HAS_PICTURE_BITMAP = flag;
    }
}
