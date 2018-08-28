// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;

public final class StarRatingBar extends View
{
    public static interface OnRatingChangeListener
    {

        public abstract void onRatingChanged(int i);
    }

    static final class SavedState extends android.view.View.BaseSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public int numStars;
        public int rating;

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(numStars);
            parcel.writeInt(rating);
        }


        SavedState(Parcel parcel)
        {
            super(parcel);
            numStars = parcel.readInt();
            rating = parcel.readInt();
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.Creator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }


    private AccessibilityManager accessibilityManager;
    private Bitmap emptyStarBitmap;
    private int numStars;
    public OnRatingChangeListener onRatingChangeListener;
    private Paint paint;
    private int rating;
    private Bitmap starBitmap;

    public StarRatingBar(Context context)
    {
        super(context);
        numStars = 11;
        init(context);
    }

    public StarRatingBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        numStars = 11;
        init(context);
    }

    public StarRatingBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        numStars = 11;
        init(context);
    }

    public StarRatingBar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        numStars = 11;
        init(context);
    }

    private static Bitmap getBitmap(Context context, int i)
    {
        context = VectorDrawableCompat.create(context.getResources(), i, null);
        Bitmap bitmap = Bitmap.createBitmap(context.getIntrinsicWidth(), context.getIntrinsicHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        context.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        context.draw(canvas);
        return bitmap;
    }

    private final void init(Context context)
    {
        accessibilityManager = (AccessibilityManager)context.getSystemService("accessibility");
        starBitmap = getBitmap(context, 0x7f020143);
        emptyStarBitmap = getBitmap(context, 0x7f020142);
        paint = new Paint(5);
        paint.setStyle(android.graphics.Paint.Style.FILL);
    }

    private final void setRating(int i)
    {
        if (i > 0 && i <= numStars && i != rating)
        {
            rating = i;
            invalidate();
            if (onRatingChangeListener != null)
            {
                onRatingChangeListener.onRatingChanged(rating);
            }
            if (accessibilityManager.isEnabled())
            {
                sendAccessibilityEvent(4);
            }
        }
    }

    protected final void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (getWidth() != 0 && getHeight() != 0)
        {
            int i = 0;
            while (i < numStars) 
            {
                Bitmap bitmap;
                if (i < rating)
                {
                    bitmap = starBitmap;
                } else
                {
                    bitmap = emptyStarBitmap;
                }
                canvas.drawBitmap(bitmap, (float)getPaddingLeft() + (float)i * ((float)(getWidth() - getPaddingLeft() - getPaddingRight() - starBitmap.getWidth()) / (float)(numStars - 1)), getPaddingTop(), paint);
                i++;
            }
        }
    }

    public final boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 21)
        {
            setRating(rating - 1);
            return true;
        }
        if (i == 22)
        {
            setRating(rating + 1);
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    protected final void onMeasure(int i, int j)
    {
        int k = numStars;
        int l = starBitmap.getWidth();
        int i1 = getPaddingLeft();
        int j1 = getPaddingRight();
        int k1 = starBitmap.getHeight();
        int l1 = getPaddingTop();
        int i2 = getPaddingBottom();
        setMeasuredDimension(resolveSize(k * l + i1 + j1, i), resolveSize(k1 + l1 + i2, j));
    }

    protected final void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        numStars = ((SavedState) (parcelable)).numStars;
        rating = ((SavedState) (parcelable)).rating;
    }

    protected final Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.numStars = numStars;
        savedstate.rating = rating;
        return savedstate;
    }

    public final boolean onTouchEvent(MotionEvent motionevent)
    {
        float f1;
        switch (motionevent.getAction() & 0xff)
        {
        case 1: // '\001'
        default:
            return false;

        case 0: // '\0'
        case 2: // '\002'
            f1 = motionevent.getX();
            break;
        }
        motionevent.getY();
        float f2 = (float)(getWidth() - getPaddingLeft() - getPaddingRight() - starBitmap.getWidth()) / (float)(numStars - 1);
        float f = (float)getPaddingLeft() + (float)starBitmap.getWidth() / 2.0F + f2 / 2.0F;
        int i;
        for (i = 1; f < f1 && i < numStars; f += f2)
        {
            i++;
        }

        setRating(i);
        return true;
    }

    public final void setNumStars(int i)
    {
        if (i < 3)
        {
            throw new IllegalArgumentException("numStars must be at least 3");
        } else
        {
            numStars = i;
            requestLayout();
            return;
        }
    }
}
