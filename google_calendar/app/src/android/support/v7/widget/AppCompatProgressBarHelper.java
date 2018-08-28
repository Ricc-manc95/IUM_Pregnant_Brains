// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray

class AppCompatProgressBarHelper
{

    private static final int TINT_ATTRS[] = {
        0x101013b, 0x101013c
    };
    public Bitmap mSampleTile;
    private final ProgressBar mView;

    AppCompatProgressBarHelper(ProgressBar progressbar)
    {
        mView = progressbar;
    }

    private final Drawable tileify(Drawable drawable, boolean flag)
    {
        boolean flag1 = false;
        if (drawable instanceof WrappedDrawable)
        {
            Drawable drawable1 = ((WrappedDrawable)drawable).getWrappedDrawable();
            if (drawable1 != null)
            {
                drawable1 = tileify(drawable1, flag);
                ((WrappedDrawable)drawable).setWrappedDrawable(drawable1);
            }
        } else
        {
            if (drawable instanceof LayerDrawable)
            {
                drawable = (LayerDrawable)drawable;
                int k = drawable.getNumberOfLayers();
                Drawable adrawable[] = new Drawable[k];
                int i = 0;
                while (i < k) 
                {
                    int l = drawable.getId(i);
                    Drawable drawable2 = drawable.getDrawable(i);
                    if (l == 0x102000d || l == 0x102000f)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    adrawable[i] = tileify(drawable2, flag);
                    i++;
                }
                LayerDrawable layerdrawable = new LayerDrawable(adrawable);
                for (int j = ((flag1) ? 1 : 0); j < k; j++)
                {
                    layerdrawable.setId(j, drawable.getId(j));
                }

                return layerdrawable;
            }
            if (drawable instanceof BitmapDrawable)
            {
                drawable = (BitmapDrawable)drawable;
                Object obj = drawable.getBitmap();
                if (mSampleTile == null)
                {
                    mSampleTile = ((Bitmap) (obj));
                }
                ShapeDrawable shapedrawable = new ShapeDrawable(new RoundRectShape(new float[] {
                    5F, 5F, 5F, 5F, 5F, 5F, 5F, 5F
                }, null, null));
                obj = new BitmapShader(((Bitmap) (obj)), android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.CLAMP);
                shapedrawable.getPaint().setShader(((android.graphics.Shader) (obj)));
                shapedrawable.getPaint().setColorFilter(drawable.getPaint().getColorFilter());
                if (flag)
                {
                    return new ClipDrawable(shapedrawable, 3, 1);
                } else
                {
                    return shapedrawable;
                }
            }
        }
        return drawable;
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        Object obj = mView.getContext();
        TintTypedArray tinttypedarray = new TintTypedArray(((Context) (obj)), ((Context) (obj)).obtainStyledAttributes(attributeset, TINT_ATTRS, i, 0));
        obj = tinttypedarray.getDrawableIfKnown(0);
        if (obj != null)
        {
            ProgressBar progressbar = mView;
            attributeset = ((AttributeSet) (obj));
            if (obj instanceof AnimationDrawable)
            {
                obj = (AnimationDrawable)obj;
                int j = ((AnimationDrawable) (obj)).getNumberOfFrames();
                attributeset = new AnimationDrawable();
                attributeset.setOneShot(((AnimationDrawable) (obj)).isOneShot());
                for (i = 0; i < j; i++)
                {
                    Drawable drawable = tileify(((AnimationDrawable) (obj)).getFrame(i), true);
                    drawable.setLevel(10000);
                    attributeset.addFrame(drawable, ((AnimationDrawable) (obj)).getDuration(i));
                }

                attributeset.setLevel(10000);
            }
            progressbar.setIndeterminateDrawable(attributeset);
        }
        attributeset = tinttypedarray.getDrawableIfKnown(1);
        if (attributeset != null)
        {
            mView.setProgressDrawable(tileify(attributeset, false));
        }
        tinttypedarray.mWrapped.recycle();
    }

}
