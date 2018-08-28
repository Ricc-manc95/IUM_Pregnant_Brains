// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.graphics.Matrix;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;

final class PathName
{

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

    public th()
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

    public th(mGroupName mgroupname, ArrayMap arraymap)
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
        mRotate = mgroupname.mRotate;
        mPivotX = mgroupname.mPivotX;
        mPivotY = mgroupname.mPivotY;
        mScaleX = mgroupname.mScaleX;
        mScaleY = mgroupname.mScaleY;
        mTranslateX = mgroupname.mTranslateX;
        mTranslateY = mgroupname.mTranslateY;
        mThemeAttrs = mgroupname.mThemeAttrs;
        mGroupName = mgroupname.mGroupName;
        mChangingConfigurations = mgroupname.mChangingConfigurations;
        if (mGroupName != null)
        {
            arraymap.put(mGroupName, this);
        }
        mLocalMatrix.set(mgroupname.mLocalMatrix);
        ArrayList arraylist = mgroupname.mChildren;
        int i = 0;
        do
        {
            if (i < arraylist.size())
            {
                mgroupname = ((mChildren) (arraylist.get(i)));
                if (mgroupname instanceof mChildren)
                {
                    mgroupname = (mChildren)mgroupname;
                    mChildren.add(new <init>(mgroupname, arraymap));
                } else
                {
                    if (mgroupname instanceof th)
                    {
                        mgroupname = new th((th)mgroupname);
                    } else
                    if (mgroupname instanceof th)
                    {
                        mgroupname = new th((th)mgroupname);
                    } else
                    {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    mChildren.add(mgroupname);
                    if (((mChildren) (mgroupname)).PathName != null)
                    {
                        arraymap.put(((PathName) (mgroupname)).PathName, mgroupname);
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
