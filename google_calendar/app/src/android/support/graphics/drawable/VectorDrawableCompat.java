// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCommon, AndroidResources

public final class VectorDrawableCompat extends VectorDrawableCommon
{

    public static final android.graphics.PorterDuff.Mode DEFAULT_TINT_MODE;
    public boolean mAllowCaching;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float mTmpFloats[];
    private final Matrix mTmpMatrix;
    public VectorDrawableCompatState mVectorState;

    public VectorDrawableCompat()
    {
        mAllowCaching = true;
        mTmpFloats = new float[9];
        mTmpMatrix = new Matrix();
        mTmpBounds = new Rect();
        mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(VectorDrawableCompatState vectordrawablecompatstate)
    {
        mAllowCaching = true;
        mTmpFloats = new float[9];
        mTmpMatrix = new Matrix();
        mTmpBounds = new Rect();
        mVectorState = vectordrawablecompatstate;
        ColorStateList colorstatelist = vectordrawablecompatstate.mTint;
        vectordrawablecompatstate = vectordrawablecompatstate.mTintMode;
        if (colorstatelist == null || vectordrawablecompatstate == null)
        {
            vectordrawablecompatstate = null;
        } else
        {
            vectordrawablecompatstate = new PorterDuffColorFilter(colorstatelist.getColorForState(getState(), 0), vectordrawablecompatstate);
        }
        mTintFilter = vectordrawablecompatstate;
    }

    static int applyAlpha(int i, float f)
    {
        return (int)((float)Color.alpha(i) * f) << 24 | 0xffffff & i;
    }

    public static VectorDrawableCompat create(Resources resources, int i, android.content.res.Resources.Theme theme)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = resources.getDrawable(i, theme);
            new VectorDrawableDelegateState(vectordrawablecompat.mDelegateDrawable.getConstantState());
            return vectordrawablecompat;
        }
        android.content.res.XmlResourceParser xmlresourceparser;
        AttributeSet attributeset;
        xmlresourceparser = resources.getXml(i);
        attributeset = Xml.asAttributeSet(xmlresourceparser);
        do
        {
            i = xmlresourceparser.next();
        } while (i != 2 && i != 1);
        if (i != 2)
        {
            VectorDrawableCompat vectordrawablecompat1;
            try
            {
                throw new XmlPullParserException("No start tag found");
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources)
            {
                Log.e("VectorDrawableCompat", "parser error", resources);
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources)
            {
                Log.e("VectorDrawableCompat", "parser error", resources);
            }
            return null;
        }
        vectordrawablecompat1 = new VectorDrawableCompat();
        vectordrawablecompat1.inflate(resources, xmlresourceparser, attributeset, theme);
        return vectordrawablecompat1;
    }

    public final volatile void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
    }

    public final boolean canApplyTheme()
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.canApplyTheme();
        }
        return false;
    }

    public final volatile void clearColorFilter()
    {
        super.clearColorFilter();
    }

    public final void draw(Canvas canvas)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.draw(canvas);
        } else
        {
            copyBounds(mTmpBounds);
            if (mTmpBounds.width() > 0 && mTmpBounds.height() > 0)
            {
                float f;
                float f1;
                float f2;
                float f3;
                Object obj;
                int i;
                int j;
                int l;
                int i1;
                if (mColorFilter == null)
                {
                    obj = mTintFilter;
                } else
                {
                    obj = mColorFilter;
                }
                canvas.getMatrix(mTmpMatrix);
                mTmpMatrix.getValues(mTmpFloats);
                f1 = Math.abs(mTmpFloats[0]);
                f = Math.abs(mTmpFloats[4]);
                f3 = Math.abs(mTmpFloats[1]);
                f2 = Math.abs(mTmpFloats[3]);
                if (f3 != 0.0F || f2 != 0.0F)
                {
                    f = 1.0F;
                    f1 = 1.0F;
                }
                j = (int)(f1 * (float)mTmpBounds.width());
                i = (int)(f * (float)mTmpBounds.height());
                l = Math.min(2048, j);
                i1 = Math.min(2048, i);
                if (l > 0 && i1 > 0)
                {
                    Object obj1;
                    boolean flag;
label0:
                    {
                        int k = canvas.save();
                        canvas.translate(mTmpBounds.left, mTmpBounds.top);
                        if (isAutoMirrored() && DrawableCompat.getLayoutDirection(this) == 1)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            canvas.translate(mTmpBounds.width(), 0.0F);
                            canvas.scale(-1F, 1.0F);
                        }
                        mTmpBounds.offsetTo(0, 0);
                        obj1 = mVectorState;
                        if (((VectorDrawableCompatState) (obj1)).mCachedBitmap != null)
                        {
                            Object obj2;
                            if (l == ((VectorDrawableCompatState) (obj1)).mCachedBitmap.getWidth() && i1 == ((VectorDrawableCompatState) (obj1)).mCachedBitmap.getHeight())
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                break label0;
                            }
                        }
                        obj1.mCachedBitmap = Bitmap.createBitmap(l, i1, android.graphics.Bitmap.Config.ARGB_8888);
                        obj1.mCacheDirty = true;
                    }
                    if (!mAllowCaching)
                    {
                        obj2 = mVectorState;
                        ((VectorDrawableCompatState) (obj2)).mCachedBitmap.eraseColor(0);
                        obj1 = new Canvas(((VectorDrawableCompatState) (obj2)).mCachedBitmap);
                        obj2 = ((VectorDrawableCompatState) (obj2)).mVPathRenderer;
                        ((VPathRenderer) (obj2)).drawGroupTree(((VPathRenderer) (obj2)).mRootGroup, VPathRenderer.IDENTITY_MATRIX, ((Canvas) (obj1)), l, i1, null);
                    } else
                    {
                        obj1 = mVectorState;
                        if (!((VectorDrawableCompatState) (obj1)).mCacheDirty && ((VectorDrawableCompatState) (obj1)).mCachedTint == ((VectorDrawableCompatState) (obj1)).mTint && ((VectorDrawableCompatState) (obj1)).mCachedTintMode == ((VectorDrawableCompatState) (obj1)).mTintMode && ((VectorDrawableCompatState) (obj1)).mCachedAutoMirrored == ((VectorDrawableCompatState) (obj1)).mAutoMirrored && ((VectorDrawableCompatState) (obj1)).mCachedRootAlpha == ((VectorDrawableCompatState) (obj1)).mVPathRenderer.getRootAlpha())
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            Object obj3 = mVectorState;
                            ((VectorDrawableCompatState) (obj3)).mCachedBitmap.eraseColor(0);
                            obj1 = new Canvas(((VectorDrawableCompatState) (obj3)).mCachedBitmap);
                            obj3 = ((VectorDrawableCompatState) (obj3)).mVPathRenderer;
                            ((VPathRenderer) (obj3)).drawGroupTree(((VPathRenderer) (obj3)).mRootGroup, VPathRenderer.IDENTITY_MATRIX, ((Canvas) (obj1)), l, i1, null);
                            obj1 = mVectorState;
                            obj1.mCachedTint = ((VectorDrawableCompatState) (obj1)).mTint;
                            obj1.mCachedTintMode = ((VectorDrawableCompatState) (obj1)).mTintMode;
                            obj1.mCachedRootAlpha = ((VectorDrawableCompatState) (obj1)).mVPathRenderer.getRootAlpha();
                            obj1.mCachedAutoMirrored = ((VectorDrawableCompatState) (obj1)).mAutoMirrored;
                            obj1.mCacheDirty = false;
                        }
                    }
                    obj1 = mVectorState;
                    obj2 = mTmpBounds;
                    if (((VectorDrawableCompatState) (obj1)).mVPathRenderer.getRootAlpha() < 255)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag && obj == null)
                    {
                        obj = null;
                    } else
                    {
                        if (((VectorDrawableCompatState) (obj1)).mTempPaint == null)
                        {
                            obj1.mTempPaint = new Paint();
                            ((VectorDrawableCompatState) (obj1)).mTempPaint.setFilterBitmap(true);
                        }
                        ((VectorDrawableCompatState) (obj1)).mTempPaint.setAlpha(((VectorDrawableCompatState) (obj1)).mVPathRenderer.getRootAlpha());
                        ((VectorDrawableCompatState) (obj1)).mTempPaint.setColorFilter(((ColorFilter) (obj)));
                        obj = ((VectorDrawableCompatState) (obj1)).mTempPaint;
                    }
                    canvas.drawBitmap(((VectorDrawableCompatState) (obj1)).mCachedBitmap, null, ((Rect) (obj2)), ((Paint) (obj)));
                    canvas.restoreToCount(k);
                    return;
                }
            }
        }
    }

    public final int getAlpha()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getAlpha();
        } else
        {
            return mVectorState.mVPathRenderer.getRootAlpha();
        }
    }

    public final int getChangingConfigurations()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getChangingConfigurations();
        } else
        {
            return super.getChangingConfigurations() | mVectorState.getChangingConfigurations();
        }
    }

    public final volatile ColorFilter getColorFilter()
    {
        return super.getColorFilter();
    }

    public final android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if (mDelegateDrawable != null && android.os.Build.VERSION.SDK_INT >= 24)
        {
            return new VectorDrawableDelegateState(mDelegateDrawable.getConstantState());
        } else
        {
            mVectorState.mChangingConfigurations = getChangingConfigurations();
            return mVectorState;
        }
    }

    public final volatile Drawable getCurrent()
    {
        return super.getCurrent();
    }

    public final int getIntrinsicHeight()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getIntrinsicHeight();
        } else
        {
            return (int)mVectorState.mVPathRenderer.mBaseHeight;
        }
    }

    public final int getIntrinsicWidth()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getIntrinsicWidth();
        } else
        {
            return (int)mVectorState.mVPathRenderer.mBaseWidth;
        }
    }

    public final volatile int getMinimumHeight()
    {
        return super.getMinimumHeight();
    }

    public final volatile int getMinimumWidth()
    {
        return super.getMinimumWidth();
    }

    public final int getOpacity()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.getOpacity();
        } else
        {
            return -3;
        }
    }

    public final volatile boolean getPadding(Rect rect)
    {
        return super.getPadding(rect);
    }

    public final volatile int[] getState()
    {
        return super.getState();
    }

    public final volatile Region getTransparentRegion()
    {
        return super.getTransparentRegion();
    }

    public final void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.inflate(resources, xmlpullparser, attributeset);
            return;
        } else
        {
            inflate(resources, xmlpullparser, attributeset, null);
            return;
        }
    }

    public final void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        Object obj;
        TypedArray typedarray;
        VectorDrawableCompatState vectordrawablecompatstate;
        Object obj2;
        int i;
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.inflate(resources, xmlpullparser, attributeset, theme);
            return;
        }
        vectordrawablecompatstate = mVectorState;
        vectordrawablecompatstate.mVPathRenderer = new VPathRenderer();
        obj = AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY;
        VectorDrawableCompatState vectordrawablecompatstate1;
        byte byte0;
        if (theme == null)
        {
            typedarray = resources.obtainAttributes(attributeset, ((int []) (obj)));
        } else
        {
            typedarray = theme.obtainStyledAttributes(attributeset, ((int []) (obj)), 0, 0);
        }
        vectordrawablecompatstate1 = mVectorState;
        obj2 = vectordrawablecompatstate1.mVPathRenderer;
        byte0 = -1;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "tintMode") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = byte0;
        } else
        {
            i = typedarray.getInt(6, -1);
        }
        obj = android.graphics.PorterDuff.Mode.SRC_IN;
        i;
        JVM INSTR tableswitch 3 16: default 176
    //                   3 390
    //                   4 176
    //                   5 398
    //                   6 176
    //                   7 176
    //                   8 176
    //                   9 406
    //                   10 176
    //                   11 176
    //                   12 176
    //                   13 176
    //                   14 414
    //                   15 422
    //                   16 430;
           goto _L1 _L2 _L1 _L3 _L1 _L1 _L1 _L4 _L1 _L1 _L1 _L1 _L5 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_430;
_L8:
        vectordrawablecompatstate1.mTintMode = ((android.graphics.PorterDuff.Mode) (obj));
        obj = typedarray.getColorStateList(1);
        if (obj != null)
        {
            vectordrawablecompatstate1.mTint = ((ColorStateList) (obj));
        }
        boolean flag = vectordrawablecompatstate1.mAutoMirrored;
        float f;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "autoMirrored") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            flag = typedarray.getBoolean(5, flag);
        }
        vectordrawablecompatstate1.mAutoMirrored = flag;
        f = ((VPathRenderer) (obj2)).mViewportWidth;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportWidth") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f = typedarray.getFloat(7, f);
        }
        obj2.mViewportWidth = f;
        f = ((VPathRenderer) (obj2)).mViewportHeight;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportHeight") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f = typedarray.getFloat(8, f);
        }
        obj2.mViewportHeight = f;
        if (((VPathRenderer) (obj2)).mViewportWidth <= 0.0F)
        {
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires viewportWidth > 0").toString());
        }
        break MISSING_BLOCK_LABEL_497;
_L2:
        obj = android.graphics.PorterDuff.Mode.SRC_OVER;
          goto _L8
_L3:
        obj = android.graphics.PorterDuff.Mode.SRC_IN;
          goto _L8
_L4:
        obj = android.graphics.PorterDuff.Mode.SRC_ATOP;
          goto _L8
_L5:
        obj = android.graphics.PorterDuff.Mode.MULTIPLY;
          goto _L8
_L6:
        obj = android.graphics.PorterDuff.Mode.SCREEN;
          goto _L8
        obj = android.graphics.PorterDuff.Mode.ADD;
          goto _L8
        float f1;
        VPathRenderer vpathrenderer;
        ArrayDeque arraydeque;
        VGroup vgroup1;
        int j;
        if (((VPathRenderer) (obj2)).mViewportHeight <= 0.0F)
        {
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires viewportHeight > 0").toString());
        }
        obj2.mBaseWidth = typedarray.getDimension(3, ((VPathRenderer) (obj2)).mBaseWidth);
        obj2.mBaseHeight = typedarray.getDimension(2, ((VPathRenderer) (obj2)).mBaseHeight);
        if (((VPathRenderer) (obj2)).mBaseWidth <= 0.0F)
        {
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires width > 0").toString());
        }
        if (((VPathRenderer) (obj2)).mBaseHeight <= 0.0F)
        {
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires height > 0").toString());
        }
        f1 = ((VPathRenderer) (obj2)).getAlpha();
        VFullPath vfullpath;
        int k;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "alpha") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(4, f1);
        }
        ((VPathRenderer) (obj2)).setAlpha(f1);
        obj = typedarray.getString(0);
        if (obj != null)
        {
            obj2.mRootName = ((String) (obj));
            ((VPathRenderer) (obj2)).mVGTargetsMap.put(obj, obj2);
        }
        typedarray.recycle();
        vectordrawablecompatstate.mChangingConfigurations = getChangingConfigurations();
        vectordrawablecompatstate.mCacheDirty = true;
        obj2 = mVectorState;
        vpathrenderer = ((VectorDrawableCompatState) (obj2)).mVPathRenderer;
        i = 1;
        arraydeque = new ArrayDeque();
        arraydeque.push(vpathrenderer.mRootGroup);
        j = xmlpullparser.getEventType();
        k = xmlpullparser.getDepth();
_L23:
        if (j == 1 || xmlpullparser.getDepth() < k + 1 && j == 3)
        {
            break MISSING_BLOCK_LABEL_2555;
        }
        if (j != 2)
        {
            break MISSING_BLOCK_LABEL_2525;
        }
        obj = xmlpullparser.getName();
        vgroup1 = (VGroup)arraydeque.peek();
        if (!"path".equals(obj)) goto _L10; else goto _L9
_L9:
        vfullpath = new VFullPath();
        obj = AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH;
        if (theme == null)
        {
            typedarray = resources.obtainAttributes(attributeset, ((int []) (obj)));
        } else
        {
            typedarray = theme.obtainStyledAttributes(attributeset, ((int []) (obj)), 0, 0);
        }
        vfullpath.mThemeAttrs = null;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L12; else goto _L11
_L11:
        obj = typedarray.getString(0);
        if (obj != null)
        {
            vfullpath.mPathName = ((String) (obj));
        }
        obj = typedarray.getString(2);
        if (obj != null)
        {
            vfullpath.mNodes = PathParser.createNodesFromPathData(((String) (obj)));
        }
        j = vfullpath.mFillColor;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillColor") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = typedarray.getColor(1, j);
        }
        vfullpath.mFillColor = i;
        f1 = vfullpath.mFillAlpha;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillAlpha") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(12, f1);
        }
        vfullpath.mFillAlpha = f1;
        j = -1;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineCap") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = typedarray.getInt(8, -1);
        }
        obj = vfullpath.mStrokeLineCap;
        i;
        JVM INSTR tableswitch 0 2: default 1100
    //                   0 1651
    //                   1 1659
    //                   2 1667;
           goto _L13 _L14 _L15 _L16
_L13:
        vfullpath.mStrokeLineCap = ((android.graphics.Paint.Cap) (obj));
        j = -1;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeLineJoin") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = typedarray.getInt(9, -1);
        }
        obj = vfullpath.mStrokeLineJoin;
        i;
        JVM INSTR tableswitch 0 2: default 1172
    //                   0 1694
    //                   1 1702
    //                   2 1710;
           goto _L17 _L18 _L19 _L20
_L17:
        vfullpath.mStrokeLineJoin = ((android.graphics.Paint.Join) (obj));
        f1 = vfullpath.mStrokeMiterlimit;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeMiterLimit") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(10, f1);
        }
        vfullpath.mStrokeMiterlimit = f1;
        j = vfullpath.mStrokeColor;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeColor") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = typedarray.getColor(3, j);
        }
        vfullpath.mStrokeColor = i;
        f1 = vfullpath.mStrokeAlpha;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeAlpha") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(11, f1);
        }
        vfullpath.mStrokeAlpha = f1;
        f1 = vfullpath.mStrokeWidth;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeWidth") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(4, f1);
        }
        vfullpath.mStrokeWidth = f1;
        f1 = vfullpath.mTrimPathEnd;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathEnd") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(6, f1);
        }
        vfullpath.mTrimPathEnd = f1;
        f1 = vfullpath.mTrimPathOffset;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathOffset") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(7, f1);
        }
        vfullpath.mTrimPathOffset = f1;
        f1 = vfullpath.mTrimPathStart;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "trimPathStart") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            f1 = typedarray.getFloat(5, f1);
        }
        vfullpath.mTrimPathStart = f1;
        j = vfullpath.mFillRule;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "fillType") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = typedarray.getInt(13, j);
        }
        vfullpath.mFillRule = i;
_L12:
        typedarray.recycle();
        vgroup1.mChildren.add(vfullpath);
        if (vfullpath.getPathName() != null)
        {
            vpathrenderer.mVGTargetsMap.put(vfullpath.getPathName(), vfullpath);
        }
        i = 0;
        obj2.mChangingConfigurations = ((VectorDrawableCompatState) (obj2)).mChangingConfigurations | vfullpath.mChangingConfigurations;
_L21:
        j = xmlpullparser.next();
        continue; /* Loop/switch isn't completed */
_L14:
        obj = android.graphics.Paint.Cap.BUTT;
          goto _L13
_L15:
        obj = android.graphics.Paint.Cap.ROUND;
          goto _L13
_L16:
        obj = android.graphics.Paint.Cap.SQUARE;
          goto _L13
_L18:
        obj = android.graphics.Paint.Join.MITER;
          goto _L17
_L19:
        obj = android.graphics.Paint.Join.ROUND;
          goto _L17
_L20:
        obj = android.graphics.Paint.Join.BEVEL;
          goto _L17
_L10:
        if ("clip-path".equals(obj))
        {
            VClipPath vclippath = new VClipPath();
            if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                obj = AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH;
                String s;
                if (theme == null)
                {
                    obj = resources.obtainAttributes(attributeset, ((int []) (obj)));
                } else
                {
                    obj = theme.obtainStyledAttributes(attributeset, ((int []) (obj)), 0, 0);
                }
                s = ((TypedArray) (obj)).getString(0);
                if (s != null)
                {
                    vclippath.mPathName = s;
                }
                s = ((TypedArray) (obj)).getString(1);
                if (s != null)
                {
                    vclippath.mNodes = PathParser.createNodesFromPathData(s);
                }
                ((TypedArray) (obj)).recycle();
            }
            vgroup1.mChildren.add(vclippath);
            if (vclippath.getPathName() != null)
            {
                vpathrenderer.mVGTargetsMap.put(vclippath.getPathName(), vclippath);
            }
            obj2.mChangingConfigurations = ((VectorDrawableCompatState) (obj2)).mChangingConfigurations | vclippath.mChangingConfigurations;
        } else
        if ("group".equals(obj))
        {
            VGroup vgroup = new VGroup();
            Object obj1 = AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP;
            float f2;
            String s1;
            if (theme == null)
            {
                obj1 = resources.obtainAttributes(attributeset, ((int []) (obj1)));
            } else
            {
                obj1 = theme.obtainStyledAttributes(attributeset, ((int []) (obj1)), 0, 0);
            }
            vgroup.mThemeAttrs = null;
            f2 = vgroup.mRotate;
            if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "rotation") != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                f2 = ((TypedArray) (obj1)).getFloat(5, f2);
            }
            vgroup.mRotate = f2;
            vgroup.mPivotX = ((TypedArray) (obj1)).getFloat(1, vgroup.mPivotX);
            vgroup.mPivotY = ((TypedArray) (obj1)).getFloat(2, vgroup.mPivotY);
            f2 = vgroup.mScaleX;
            if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleX") != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                f2 = ((TypedArray) (obj1)).getFloat(3, f2);
            }
            vgroup.mScaleX = f2;
            f2 = vgroup.mScaleY;
            if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "scaleY") != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                f2 = ((TypedArray) (obj1)).getFloat(4, f2);
            }
            vgroup.mScaleY = f2;
            f2 = vgroup.mTranslateX;
            if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "translateX") != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                f2 = ((TypedArray) (obj1)).getFloat(6, f2);
            }
            vgroup.mTranslateX = f2;
            f2 = vgroup.mTranslateY;
            if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "translateY") != null)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                f2 = ((TypedArray) (obj1)).getFloat(7, f2);
            }
            vgroup.mTranslateY = f2;
            s1 = ((TypedArray) (obj1)).getString(0);
            if (s1 != null)
            {
                vgroup.mGroupName = s1;
            }
            vgroup.updateLocalMatrix();
            ((TypedArray) (obj1)).recycle();
            vgroup1.mChildren.add(vgroup);
            arraydeque.push(vgroup);
            if (vgroup.getGroupName() != null)
            {
                vpathrenderer.mVGTargetsMap.put(vgroup.getGroupName(), vgroup);
            }
            obj2.mChangingConfigurations = ((VectorDrawableCompatState) (obj2)).mChangingConfigurations | vgroup.mChangingConfigurations;
        }
          goto _L21
        if (j == 3 && "group".equals(xmlpullparser.getName()))
        {
            arraydeque.pop();
        }
          goto _L21
        if (i != 0)
        {
            throw new XmlPullParserException("no path defined");
        }
        resources = vectordrawablecompatstate.mTint;
        xmlpullparser = vectordrawablecompatstate.mTintMode;
        if (resources == null || xmlpullparser == null)
        {
            resources = null;
        } else
        {
            resources = new PorterDuffColorFilter(resources.getColorForState(getState(), 0), xmlpullparser);
        }
        mTintFilter = resources;
        return;
        if (true) goto _L23; else goto _L22
_L22:
    }

    public final void invalidateSelf()
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.invalidateSelf();
            return;
        } else
        {
            super.invalidateSelf();
            return;
        }
    }

    public final boolean isAutoMirrored()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.isAutoMirrored();
        } else
        {
            return mVectorState.mAutoMirrored;
        }
    }

    public final boolean isStateful()
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.isStateful();
        }
        return super.isStateful() || mVectorState != null && mVectorState.mTint != null && mVectorState.mTint.isStateful();
    }

    public final volatile void jumpToCurrentState()
    {
        super.jumpToCurrentState();
    }

    public final Drawable mutate()
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.mutate();
        } else
        if (!mMutated && super.mutate() == this)
        {
            mVectorState = new VectorDrawableCompatState(mVectorState);
            mMutated = true;
            return this;
        }
        return this;
    }

    protected final void onBoundsChange(Rect rect)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setBounds(rect);
        }
    }

    protected final boolean onStateChange(int ai[])
    {
        boolean flag1 = false;
        boolean flag;
        if (mDelegateDrawable != null)
        {
            flag = mDelegateDrawable.setState(ai);
        } else
        {
            Object obj = mVectorState;
            flag = flag1;
            if (((VectorDrawableCompatState) (obj)).mTint != null)
            {
                flag = flag1;
                if (((VectorDrawableCompatState) (obj)).mTintMode != null)
                {
                    ai = ((VectorDrawableCompatState) (obj)).mTint;
                    obj = ((VectorDrawableCompatState) (obj)).mTintMode;
                    if (ai == null || obj == null)
                    {
                        ai = null;
                    } else
                    {
                        ai = new PorterDuffColorFilter(ai.getColorForState(getState(), 0), ((android.graphics.PorterDuff.Mode) (obj)));
                    }
                    mTintFilter = ai;
                    invalidateSelf();
                    return true;
                }
            }
        }
        return flag;
    }

    public final void scheduleSelf(Runnable runnable, long l)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.scheduleSelf(runnable, l);
            return;
        } else
        {
            super.scheduleSelf(runnable, l);
            return;
        }
    }

    public final void setAlpha(int i)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setAlpha(i);
        } else
        if (mVectorState.mVPathRenderer.getRootAlpha() != i)
        {
            mVectorState.mVPathRenderer.setRootAlpha(i);
            invalidateSelf();
            return;
        }
    }

    public final void setAutoMirrored(boolean flag)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setAutoMirrored(flag);
            return;
        } else
        {
            mVectorState.mAutoMirrored = flag;
            return;
        }
    }

    public final volatile void setChangingConfigurations(int i)
    {
        super.setChangingConfigurations(i);
    }

    public final volatile void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        super.setColorFilter(i, mode);
    }

    public final void setColorFilter(ColorFilter colorfilter)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setColorFilter(colorfilter);
            return;
        } else
        {
            mColorFilter = colorfilter;
            invalidateSelf();
            return;
        }
    }

    public final volatile void setFilterBitmap(boolean flag)
    {
        super.setFilterBitmap(flag);
    }

    public final volatile void setHotspot(float f, float f1)
    {
        super.setHotspot(f, f1);
    }

    public final volatile void setHotspotBounds(int i, int j, int k, int l)
    {
        super.setHotspotBounds(i, j, k, l);
    }

    public final volatile boolean setState(int ai[])
    {
        return super.setState(ai);
    }

    public final void setTint(int i)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setTint(i);
            return;
        } else
        {
            setTintList(ColorStateList.valueOf(i));
            return;
        }
    }

    public final void setTintList(ColorStateList colorstatelist)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setTintList(colorstatelist);
        } else
        {
            Object obj = mVectorState;
            if (((VectorDrawableCompatState) (obj)).mTint != colorstatelist)
            {
                obj.mTint = colorstatelist;
                obj = ((VectorDrawableCompatState) (obj)).mTintMode;
                if (colorstatelist == null || obj == null)
                {
                    colorstatelist = null;
                } else
                {
                    colorstatelist = new PorterDuffColorFilter(colorstatelist.getColorForState(getState(), 0), ((android.graphics.PorterDuff.Mode) (obj)));
                }
                mTintFilter = colorstatelist;
                invalidateSelf();
                return;
            }
        }
    }

    public final void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.setTintMode(mode);
        } else
        {
            Object obj = mVectorState;
            if (((VectorDrawableCompatState) (obj)).mTintMode != mode)
            {
                obj.mTintMode = mode;
                obj = ((VectorDrawableCompatState) (obj)).mTint;
                if (obj == null || mode == null)
                {
                    mode = null;
                } else
                {
                    mode = new PorterDuffColorFilter(((ColorStateList) (obj)).getColorForState(getState(), 0), mode);
                }
                mTintFilter = mode;
                invalidateSelf();
                return;
            }
        }
    }

    public final boolean setVisible(boolean flag, boolean flag1)
    {
        if (mDelegateDrawable != null)
        {
            return mDelegateDrawable.setVisible(flag, flag1);
        } else
        {
            return super.setVisible(flag, flag1);
        }
    }

    public final void unscheduleSelf(Runnable runnable)
    {
        if (mDelegateDrawable != null)
        {
            mDelegateDrawable.unscheduleSelf(runnable);
            return;
        } else
        {
            super.unscheduleSelf(runnable);
            return;
        }
    }

    static 
    {
        DEFAULT_TINT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
    }

    private class VectorDrawableCompatState extends android.graphics.drawable.Drawable.ConstantState
    {
        private class VPathRenderer
        {
            private class VGroup
            {
                private class VFullPath extends VPath
                {
                    private class VPath
                    {

                        public int mChangingConfigurations;
                        public android.support.v4.graphics.PathParser.PathDataNode mNodes[];
                        public String mPathName;

                        public android.support.v4.graphics.PathParser.PathDataNode[] getPathData()
                        {
                            return mNodes;
                        }

                        public String getPathName()
                        {
                            return mPathName;
                        }

                        public boolean isClipPath()
                        {
                            return false;
                        }

                        public void setPathData(android.support.v4.graphics.PathParser.PathDataNode apathdatanode[])
                        {
                            if (!PathParser.canMorph(mNodes, apathdatanode))
                            {
                                mNodes = PathParser.deepCopyNodes(apathdatanode);
                            } else
                            {
                                android.support.v4.graphics.PathParser.PathDataNode apathdatanode1[] = mNodes;
                                int i = 0;
                                while (i < apathdatanode.length) 
                                {
                                    apathdatanode1[i].mType = apathdatanode[i].mType;
                                    for (int j = 0; j < apathdatanode[i].mParams.length; j++)
                                    {
                                        apathdatanode1[i].mParams[j] = apathdatanode[i].mParams[j];
                                    }

                                    i++;
                                }
                            }
                        }

                        public VPath()
                        {
                            mNodes = null;
                        }

                        public VPath(VPath vpath)
                        {
                            mNodes = null;
                            mPathName = vpath.mPathName;
                            mChangingConfigurations = vpath.mChangingConfigurations;
                            mNodes = PathParser.deepCopyNodes(vpath.mNodes);
                        }
                    }


                    public float mFillAlpha;
                    public int mFillColor;
                    public int mFillRule;
                    public float mStrokeAlpha;
                    public int mStrokeColor;
                    public android.graphics.Paint.Cap mStrokeLineCap;
                    public android.graphics.Paint.Join mStrokeLineJoin;
                    public float mStrokeMiterlimit;
                    public float mStrokeWidth;
                    public int mThemeAttrs[];
                    public float mTrimPathEnd;
                    public float mTrimPathOffset;
                    public float mTrimPathStart;

                    final float getFillAlpha()
                    {
                        return mFillAlpha;
                    }

                    final int getFillColor()
                    {
                        return mFillColor;
                    }

                    final float getStrokeAlpha()
                    {
                        return mStrokeAlpha;
                    }

                    final int getStrokeColor()
                    {
                        return mStrokeColor;
                    }

                    final float getStrokeWidth()
                    {
                        return mStrokeWidth;
                    }

                    final float getTrimPathEnd()
                    {
                        return mTrimPathEnd;
                    }

                    final float getTrimPathOffset()
                    {
                        return mTrimPathOffset;
                    }

                    final float getTrimPathStart()
                    {
                        return mTrimPathStart;
                    }

                    final void setFillAlpha(float f)
                    {
                        mFillAlpha = f;
                    }

                    final void setFillColor(int i)
                    {
                        mFillColor = i;
                    }

                    final void setStrokeAlpha(float f)
                    {
                        mStrokeAlpha = f;
                    }

                    final void setStrokeColor(int i)
                    {
                        mStrokeColor = i;
                    }

                    final void setStrokeWidth(float f)
                    {
                        mStrokeWidth = f;
                    }

                    final void setTrimPathEnd(float f)
                    {
                        mTrimPathEnd = f;
                    }

                    final void setTrimPathOffset(float f)
                    {
                        mTrimPathOffset = f;
                    }

                    final void setTrimPathStart(float f)
                    {
                        mTrimPathStart = f;
                    }

                    public VFullPath()
                    {
                        mStrokeColor = 0;
                        mStrokeWidth = 0.0F;
                        mFillColor = 0;
                        mStrokeAlpha = 1.0F;
                        mFillRule = 0;
                        mFillAlpha = 1.0F;
                        mTrimPathStart = 0.0F;
                        mTrimPathEnd = 1.0F;
                        mTrimPathOffset = 0.0F;
                        mStrokeLineCap = android.graphics.Paint.Cap.BUTT;
                        mStrokeLineJoin = android.graphics.Paint.Join.MITER;
                        mStrokeMiterlimit = 4F;
                    }

                    public VFullPath(VFullPath vfullpath)
                    {
                        super(vfullpath);
                        mStrokeColor = 0;
                        mStrokeWidth = 0.0F;
                        mFillColor = 0;
                        mStrokeAlpha = 1.0F;
                        mFillRule = 0;
                        mFillAlpha = 1.0F;
                        mTrimPathStart = 0.0F;
                        mTrimPathEnd = 1.0F;
                        mTrimPathOffset = 0.0F;
                        mStrokeLineCap = android.graphics.Paint.Cap.BUTT;
                        mStrokeLineJoin = android.graphics.Paint.Join.MITER;
                        mStrokeMiterlimit = 4F;
                        mThemeAttrs = vfullpath.mThemeAttrs;
                        mStrokeColor = vfullpath.mStrokeColor;
                        mStrokeWidth = vfullpath.mStrokeWidth;
                        mStrokeAlpha = vfullpath.mStrokeAlpha;
                        mFillColor = vfullpath.mFillColor;
                        mFillRule = vfullpath.mFillRule;
                        mFillAlpha = vfullpath.mFillAlpha;
                        mTrimPathStart = vfullpath.mTrimPathStart;
                        mTrimPathEnd = vfullpath.mTrimPathEnd;
                        mTrimPathOffset = vfullpath.mTrimPathOffset;
                        mStrokeLineCap = vfullpath.mStrokeLineCap;
                        mStrokeLineJoin = vfullpath.mStrokeLineJoin;
                        mStrokeMiterlimit = vfullpath.mStrokeMiterlimit;
                    }
                }

                private class VClipPath extends VPath
                {

                    public final boolean isClipPath()
                    {
                        return true;
                    }

                    public VClipPath()
                    {
                    }

                    public VClipPath(VClipPath vclippath)
                    {
                        super(vclippath);
                    }
                }


                public int mChangingConfigurations;
                public final ArrayList mChildren;
                public String mGroupName;
                public final Matrix mLocalMatrix;
                public float mPivotX;
                public float mPivotY;
                public float mRotate;
                public float mScaleX;
                public float mScaleY;
                public final Matrix mStackedMatrix;
                public int mThemeAttrs[];
                public float mTranslateX;
                public float mTranslateY;

                public final String getGroupName()
                {
                    return mGroupName;
                }

                public final Matrix getLocalMatrix()
                {
                    return mLocalMatrix;
                }

                public final float getPivotX()
                {
                    return mPivotX;
                }

                public final float getPivotY()
                {
                    return mPivotY;
                }

                public final float getRotation()
                {
                    return mRotate;
                }

                public final float getScaleX()
                {
                    return mScaleX;
                }

                public final float getScaleY()
                {
                    return mScaleY;
                }

                public final float getTranslateX()
                {
                    return mTranslateX;
                }

                public final float getTranslateY()
                {
                    return mTranslateY;
                }

                public final void setPivotX(float f)
                {
                    if (f != mPivotX)
                    {
                        mPivotX = f;
                        updateLocalMatrix();
                    }
                }

                public final void setPivotY(float f)
                {
                    if (f != mPivotY)
                    {
                        mPivotY = f;
                        updateLocalMatrix();
                    }
                }

                public final void setRotation(float f)
                {
                    if (f != mRotate)
                    {
                        mRotate = f;
                        updateLocalMatrix();
                    }
                }

                public final void setScaleX(float f)
                {
                    if (f != mScaleX)
                    {
                        mScaleX = f;
                        updateLocalMatrix();
                    }
                }

                public final void setScaleY(float f)
                {
                    if (f != mScaleY)
                    {
                        mScaleY = f;
                        updateLocalMatrix();
                    }
                }

                public final void setTranslateX(float f)
                {
                    if (f != mTranslateX)
                    {
                        mTranslateX = f;
                        updateLocalMatrix();
                    }
                }

                public final void setTranslateY(float f)
                {
                    if (f != mTranslateY)
                    {
                        mTranslateY = f;
                        updateLocalMatrix();
                    }
                }

                final void updateLocalMatrix()
                {
                    mLocalMatrix.reset();
                    mLocalMatrix.postTranslate(-mPivotX, -mPivotY);
                    mLocalMatrix.postScale(mScaleX, mScaleY);
                    mLocalMatrix.postRotate(mRotate, 0.0F, 0.0F);
                    mLocalMatrix.postTranslate(mTranslateX + mPivotX, mTranslateY + mPivotY);
                }

                public VGroup()
                {
                    mStackedMatrix = new Matrix();
                    mChildren = new ArrayList();
                    mRotate = 0.0F;
                    mPivotX = 0.0F;
                    mPivotY = 0.0F;
                    mScaleX = 1.0F;
                    mScaleY = 1.0F;
                    mTranslateX = 0.0F;
                    mTranslateY = 0.0F;
                    mLocalMatrix = new Matrix();
                    mGroupName = null;
                }

                public VGroup(VGroup vgroup, ArrayMap arraymap)
                {
                    mStackedMatrix = new Matrix();
                    mChildren = new ArrayList();
                    mRotate = 0.0F;
                    mPivotX = 0.0F;
                    mPivotY = 0.0F;
                    mScaleX = 1.0F;
                    mScaleY = 1.0F;
                    mTranslateX = 0.0F;
                    mTranslateY = 0.0F;
                    mLocalMatrix = new Matrix();
                    mGroupName = null;
                    mRotate = vgroup.mRotate;
                    mPivotX = vgroup.mPivotX;
                    mPivotY = vgroup.mPivotY;
                    mScaleX = vgroup.mScaleX;
                    mScaleY = vgroup.mScaleY;
                    mTranslateX = vgroup.mTranslateX;
                    mTranslateY = vgroup.mTranslateY;
                    mThemeAttrs = vgroup.mThemeAttrs;
                    mGroupName = vgroup.mGroupName;
                    mChangingConfigurations = vgroup.mChangingConfigurations;
                    if (mGroupName != null)
                    {
                        arraymap.put(mGroupName, this);
                    }
                    mLocalMatrix.set(vgroup.mLocalMatrix);
                    ArrayList arraylist = vgroup.mChildren;
                    int i = 0;
                    do
                    {
                        if (i < arraylist.size())
                        {
                            vgroup = ((VGroup) (arraylist.get(i)));
                            if (vgroup instanceof VGroup)
                            {
                                vgroup = (VGroup)vgroup;
                                mChildren.add(new VGroup(vgroup, arraymap));
                            } else
                            {
                                if (vgroup instanceof VFullPath)
                                {
                                    vgroup = new VFullPath((VFullPath)vgroup);
                                } else
                                if (vgroup instanceof VClipPath)
                                {
                                    vgroup = new VClipPath((VClipPath)vgroup);
                                } else
                                {
                                    throw new IllegalStateException("Unknown object in the tree!");
                                }
                                mChildren.add(vgroup);
                                if (((VPath) (vgroup)).mPathName != null)
                                {
                                    arraymap.put(((VPath) (vgroup)).mPathName, vgroup);
                                }
                            }
                        } else
                        {
                            return;
                        }
                        i++;
                    } while (true);
                }
            }


            public static final Matrix IDENTITY_MATRIX = new Matrix();
            public float mBaseHeight;
            public float mBaseWidth;
            private int mChangingConfigurations;
            public Paint mFillPaint;
            private final Matrix mFinalPathMatrix;
            private final Path mPath;
            private PathMeasure mPathMeasure;
            private final Path mRenderPath;
            private int mRootAlpha;
            public final VGroup mRootGroup;
            public String mRootName;
            public Paint mStrokePaint;
            public final ArrayMap mVGTargetsMap;
            public float mViewportHeight;
            public float mViewportWidth;

            final void drawGroupTree(VGroup vgroup, Matrix matrix, Canvas canvas, int i, int j, ColorFilter colorfilter)
            {
                vgroup.mStackedMatrix.set(matrix);
                vgroup.mStackedMatrix.preConcat(vgroup.mLocalMatrix);
                canvas.save();
                int k = 0;
                do
                {
                    if (k < vgroup.mChildren.size())
                    {
                        matrix = ((Matrix) (vgroup.mChildren.get(k)));
                        if (matrix instanceof VGroup)
                        {
                            drawGroupTree((VGroup)matrix, vgroup.mStackedMatrix, canvas, i, j, colorfilter);
                        } else
                        if (matrix instanceof VPath)
                        {
                            matrix = (VPath)matrix;
                            float f = (float)i / mViewportWidth;
                            float f2 = (float)j / mViewportHeight;
                            float f1 = Math.min(f, f2);
                            Matrix matrix1 = vgroup.mStackedMatrix;
                            mFinalPathMatrix.set(matrix1);
                            mFinalPathMatrix.postScale(f, f2);
                            float af[] = new float[4];
                            float[] _tmp = af;
                            af[0] = 0.0F;
                            af[1] = 1.0F;
                            af[2] = 1.0F;
                            af[3] = 0.0F;
                            matrix1.mapVectors(af);
                            float f8 = (float)Math.hypot(af[0], af[1]);
                            float f10 = (float)Math.hypot(af[2], af[3]);
                            f = af[0];
                            f2 = af[1];
                            float f4 = af[2];
                            float f6 = af[3];
                            f8 = Math.max(f8, f10);
                            if (f8 > 0.0F)
                            {
                                f = Math.abs(f6 * f - f2 * f4) / f8;
                            } else
                            {
                                f = 0.0F;
                            }
                            if (f != 0.0F)
                            {
                                Path path = mPath;
                                path.reset();
                                if (((VPath) (matrix)).mNodes != null)
                                {
                                    android.support.v4.graphics.PathParser.PathDataNode.nodesToPath(((VPath) (matrix)).mNodes, path);
                                }
                                Object obj = mPath;
                                mRenderPath.reset();
                                if (matrix.isClipPath())
                                {
                                    mRenderPath.addPath(((Path) (obj)), mFinalPathMatrix);
                                    canvas.clipPath(mRenderPath);
                                } else
                                {
                                    VFullPath vfullpath = (VFullPath)matrix;
                                    if (vfullpath.mTrimPathStart != 0.0F || vfullpath.mTrimPathEnd != 1.0F)
                                    {
                                        float f9 = vfullpath.mTrimPathStart;
                                        float f11 = vfullpath.mTrimPathOffset;
                                        float f5 = vfullpath.mTrimPathEnd;
                                        float f7 = vfullpath.mTrimPathOffset;
                                        if (mPathMeasure == null)
                                        {
                                            mPathMeasure = new PathMeasure();
                                        }
                                        mPathMeasure.setPath(mPath, false);
                                        float f3 = mPathMeasure.getLength();
                                        f9 = ((f9 + f11) % 1.0F) * f3;
                                        f5 = ((f5 + f7) % 1.0F) * f3;
                                        ((Path) (obj)).reset();
                                        Path path1;
                                        if (f9 > f5)
                                        {
                                            mPathMeasure.getSegment(f9, f3, ((Path) (obj)), true);
                                            mPathMeasure.getSegment(0.0F, f5, ((Path) (obj)), true);
                                        } else
                                        {
                                            mPathMeasure.getSegment(f9, f5, ((Path) (obj)), true);
                                        }
                                        ((Path) (obj)).rLineTo(0.0F, 0.0F);
                                    }
                                    mRenderPath.addPath(((Path) (obj)), mFinalPathMatrix);
                                    if (vfullpath.mFillColor != 0)
                                    {
                                        if (mFillPaint == null)
                                        {
                                            mFillPaint = new Paint();
                                            mFillPaint.setStyle(android.graphics.Paint.Style.FILL);
                                            mFillPaint.setAntiAlias(true);
                                        }
                                        obj = mFillPaint;
                                        ((Paint) (obj)).setColor(VectorDrawableCompat.applyAlpha(vfullpath.mFillColor, vfullpath.mFillAlpha));
                                        ((Paint) (obj)).setColorFilter(colorfilter);
                                        path1 = mRenderPath;
                                        if (vfullpath.mFillRule == 0)
                                        {
                                            matrix = android.graphics.Path.FillType.WINDING;
                                        } else
                                        {
                                            matrix = android.graphics.Path.FillType.EVEN_ODD;
                                        }
                                        path1.setFillType(matrix);
                                        canvas.drawPath(mRenderPath, ((Paint) (obj)));
                                    }
                                    if (vfullpath.mStrokeColor != 0)
                                    {
                                        if (mStrokePaint == null)
                                        {
                                            mStrokePaint = new Paint();
                                            mStrokePaint.setStyle(android.graphics.Paint.Style.STROKE);
                                            mStrokePaint.setAntiAlias(true);
                                        }
                                        matrix = mStrokePaint;
                                        if (vfullpath.mStrokeLineJoin != null)
                                        {
                                            matrix.setStrokeJoin(vfullpath.mStrokeLineJoin);
                                        }
                                        if (vfullpath.mStrokeLineCap != null)
                                        {
                                            matrix.setStrokeCap(vfullpath.mStrokeLineCap);
                                        }
                                        matrix.setStrokeMiter(vfullpath.mStrokeMiterlimit);
                                        matrix.setColor(VectorDrawableCompat.applyAlpha(vfullpath.mStrokeColor, vfullpath.mStrokeAlpha));
                                        matrix.setColorFilter(colorfilter);
                                        matrix.setStrokeWidth(vfullpath.mStrokeWidth * (f * f1));
                                        canvas.drawPath(mRenderPath, matrix);
                                    }
                                }
                            }
                        }
                    } else
                    {
                        canvas.restore();
                        return;
                    }
                    k++;
                } while (true);
            }

            public final float getAlpha()
            {
                return (float)getRootAlpha() / 255F;
            }

            public final int getRootAlpha()
            {
                return mRootAlpha;
            }

            public final void setAlpha(float f)
            {
                setRootAlpha((int)(255F * f));
            }

            public final void setRootAlpha(int i)
            {
                mRootAlpha = i;
            }


            public VPathRenderer()
            {
                mFinalPathMatrix = new Matrix();
                mBaseWidth = 0.0F;
                mBaseHeight = 0.0F;
                mViewportWidth = 0.0F;
                mViewportHeight = 0.0F;
                mRootAlpha = 255;
                mRootName = null;
                mVGTargetsMap = new ArrayMap();
                mRootGroup = new VGroup();
                mPath = new Path();
                mRenderPath = new Path();
            }

            public VPathRenderer(VPathRenderer vpathrenderer)
            {
                mFinalPathMatrix = new Matrix();
                mBaseWidth = 0.0F;
                mBaseHeight = 0.0F;
                mViewportWidth = 0.0F;
                mViewportHeight = 0.0F;
                mRootAlpha = 255;
                mRootName = null;
                mVGTargetsMap = new ArrayMap();
                mRootGroup = new VGroup(vpathrenderer.mRootGroup, mVGTargetsMap);
                mPath = new Path(vpathrenderer.mPath);
                mRenderPath = new Path(vpathrenderer.mRenderPath);
                mBaseWidth = vpathrenderer.mBaseWidth;
                mBaseHeight = vpathrenderer.mBaseHeight;
                mViewportWidth = vpathrenderer.mViewportWidth;
                mViewportHeight = vpathrenderer.mViewportHeight;
                mChangingConfigurations = vpathrenderer.mChangingConfigurations;
                mRootAlpha = vpathrenderer.mRootAlpha;
                mRootName = vpathrenderer.mRootName;
                if (vpathrenderer.mRootName != null)
                {
                    mVGTargetsMap.put(vpathrenderer.mRootName, this);
                }
            }
        }


        public boolean mAutoMirrored;
        public boolean mCacheDirty;
        public boolean mCachedAutoMirrored;
        public Bitmap mCachedBitmap;
        public int mCachedRootAlpha;
        public ColorStateList mCachedTint;
        public android.graphics.PorterDuff.Mode mCachedTintMode;
        public int mChangingConfigurations;
        public Paint mTempPaint;
        public ColorStateList mTint;
        public android.graphics.PorterDuff.Mode mTintMode;
        public VPathRenderer mVPathRenderer;

        public final int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public final Drawable newDrawable()
        {
            return new VectorDrawableCompat(this);
        }

        public final Drawable newDrawable(Resources resources)
        {
            return new VectorDrawableCompat(this);
        }

        public VectorDrawableCompatState()
        {
            mTint = null;
            mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            mVPathRenderer = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectordrawablecompatstate)
        {
            mTint = null;
            mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if (vectordrawablecompatstate != null)
            {
                mChangingConfigurations = vectordrawablecompatstate.mChangingConfigurations;
                mVPathRenderer = new VPathRenderer(vectordrawablecompatstate.mVPathRenderer);
                if (vectordrawablecompatstate.mVPathRenderer.mFillPaint != null)
                {
                    mVPathRenderer.mFillPaint = new Paint(vectordrawablecompatstate.mVPathRenderer.mFillPaint);
                }
                if (vectordrawablecompatstate.mVPathRenderer.mStrokePaint != null)
                {
                    mVPathRenderer.mStrokePaint = new Paint(vectordrawablecompatstate.mVPathRenderer.mStrokePaint);
                }
                mTint = vectordrawablecompatstate.mTint;
                mTintMode = vectordrawablecompatstate.mTintMode;
                mAutoMirrored = vectordrawablecompatstate.mAutoMirrored;
            }
        }
    }


    private class VectorDrawableDelegateState extends android.graphics.drawable.Drawable.ConstantState
    {

        private final android.graphics.drawable.Drawable.ConstantState mDelegateState;

        public final boolean canApplyTheme()
        {
            return mDelegateState.canApplyTheme();
        }

        public final int getChangingConfigurations()
        {
            return mDelegateState.getChangingConfigurations();
        }

        public final Drawable newDrawable()
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = (VectorDrawable)mDelegateState.newDrawable();
            return vectordrawablecompat;
        }

        public final Drawable newDrawable(Resources resources)
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = (VectorDrawable)mDelegateState.newDrawable(resources);
            return vectordrawablecompat;
        }

        public final Drawable newDrawable(Resources resources, android.content.res.Resources.Theme theme)
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = (VectorDrawable)mDelegateState.newDrawable(resources, theme);
            return vectordrawablecompat;
        }

        public VectorDrawableDelegateState(android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            mDelegateState = constantstate;
        }
    }

}
