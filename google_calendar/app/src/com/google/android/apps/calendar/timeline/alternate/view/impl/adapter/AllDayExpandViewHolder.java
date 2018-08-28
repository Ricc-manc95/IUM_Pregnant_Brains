// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import android.content.Context;
import android.view.View;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.apps.calendar.util.concurrent.ObservableReference;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            TimelineAdapterViewHolderImpl

public final class AllDayExpandViewHolder extends TimelineAdapterViewHolderImpl
{

    public final ExpandButtonDrawable background;

    public AllDayExpandViewHolder(Context context, ObservableReference observablereference, ExpandButtonDrawable expandbuttondrawable)
    {
        super(new View(context));
        View view = itemView;
        background = expandbuttondrawable;
        view.setBackground(background);
        boolean flag = ((Boolean)observablereference.get()).booleanValue();
        expandbuttondrawable = itemView;
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final ObservableReference arg$1;

            public final void onClick(View view1)
            {
                AllDayExpandViewHolder.lambda$new$0$AllDayExpandViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T7M4SR5E9R62OJCCL96APJ5E9IMSOR57D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(arg$1);
            }

            .Lambda._cls0(ObservableReference observablereference)
            {
                arg$1 = observablereference;
            }
        }

        class .Lambda._cls1
            implements Consumer
        {

            private final AllDayExpandViewHolder arg$1;
            private final Context arg$2;

            public final void accept(Object obj)
            {
                final ExpandButtonDrawable final_expandbuttondrawable = arg$1;
                Context context1 = arg$2;
                obj = (Boolean)obj;
                boolean flag1 = ((Boolean) (obj)).booleanValue();
                View view1 = ((AllDayExpandViewHolder) (final_expandbuttondrawable)).itemView;
                class ExpandButtonDrawable..Lambda._cls0
                    implements android.animation.ValueAnimator.AnimatorUpdateListener
                {

                    private final ExpandButtonDrawable arg$1;
                    private final int arg$2;
                    private final int arg$3;

                    public final void onAnimationUpdate(ValueAnimator valueanimator)
                    {
                        ExpandButtonDrawable expandbuttondrawable1 = arg$1;
                        int l = arg$2;
                        float f = arg$3;
                        expandbuttondrawable1.rotation = (int)(((Float)valueanimator.getAnimatedValue()).floatValue() * f) + l;
                        expandbuttondrawable1.invalidateSelf();
                    }

                        ExpandButtonDrawable..Lambda._cls0(ExpandButtonDrawable expandbuttondrawable, int i, int j)
                        {
                            arg$1 = expandbuttondrawable;
                            arg$2 = i;
                            arg$3 = j;
                        }
                }

                class ExpandButtonDrawable._cls1 extends AnimatorListenerAdapter
                {

                    private final ExpandButtonDrawable this$0;
                    private final int val$targetRotation;

                    public final void onAnimationEnd(Animator animator)
                    {
                        rotation = targetRotation;
                        lastAnimator = null;
                    }

                        ExpandButtonDrawable._cls1()
                        {
                            this$0 = final_expandbuttondrawable;
                            targetRotation = I.this;
                            super();
                        }
                }

                int j;
                int k;
                if (flag1)
                {
                    j = 0x7f130053;
                } else
                {
                    j = 0x7f13005a;
                }
                view1.setContentDescription(context1.getString(j));
                final_expandbuttondrawable = ((AllDayExpandViewHolder) (final_expandbuttondrawable)).background;
                if (((Boolean) (obj)).booleanValue())
                {
                    j = 180;
                } else
                {
                    j = 0;
                }
                if (final_expandbuttondrawable.lastAnimator != null)
                {
                    final_expandbuttondrawable.lastAnimator.cancel();
                }
                k = final_expandbuttondrawable.rotation;
                obj = ValueAnimator.ofFloat(new float[] {
                    0.0F, 1.0F
                });
                ((ValueAnimator) (obj)).addUpdateListener(new ExpandButtonDrawable..Lambda._cls0(final_expandbuttondrawable, k, j - k));
                ((ValueAnimator) (obj)).addListener(j. new ExpandButtonDrawable._cls1());
                ((ValueAnimator) (obj)).start();
                final_expandbuttondrawable.lastAnimator = ((Animator) (obj));
            }

            .Lambda._cls1(Context context)
            {
                arg$1 = AllDayExpandViewHolder.this;
                arg$2 = context;
            }

            private class ExpandButtonDrawable extends Drawable
            {

                private final Bitmap arrow;
                private final int bottomPadding;
                public Animator lastAnimator;
                private final Matrix matrix = new Matrix();
                private final Paint paint = new Paint();
                public int rotation;

                public final void draw(Canvas canvas)
                {
                    int i = (getBounds().width() - arrow.getWidth()) / 2;
                    int j = getBounds().height();
                    int k = arrow.getHeight();
                    int l = bottomPadding;
                    matrix.setRotate(rotation, arrow.getWidth() / 2, arrow.getHeight() / 2);
                    matrix.postTranslate(i, j - k - l);
                    canvas.drawBitmap(arrow, matrix, paint);
                }

                public final int getOpacity()
                {
                    return -3;
                }

                public final void setAlpha(int i)
                {
                }

                public final void setColorFilter(ColorFilter colorfilter)
                {
                }

                ExpandButtonDrawable(Context context, DimensConverter dimensconverter, ObservableReference observablereference)
                {
                    rotation = 0;
                    lastAnimator = null;
                    paint.setAntiAlias(true);
                    arrow = BitmapFactory.decodeResource(context.getResources(), 0x7f0201f6);
                    float f;
                    boolean flag;
                    if ((ScreenType)observablereference.get() == ScreenType.PHONE)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        f = 1.0F;
                    } else
                    {
                        f = 4F;
                    }
                    bottomPadding = dimensconverter.getPixelOffset(f);
                }
            }

        }

        int i;
        if (flag)
        {
            i = 0x7f130053;
        } else
        {
            i = 0x7f13005a;
        }
        expandbuttondrawable.setContentDescription(context.getString(i));
        view.setOnClickListener(new .Lambda._cls0(observablereference));
        expandbuttondrawable = itemView;
        expandbuttondrawable.addOnAttachStateChangeListener(new com.google.android.apps.calendar.util.android.Views._cls1(expandbuttondrawable, observablereference, new .Lambda._cls1(context), CalendarExecutor.MAIN, true));
    }

    static final void lambda$new$0$AllDayExpandViewHolder$51666RRD5TJMURR7DHIIUOBECHP6UQB45TGN0S3J5THM2R35DPI62SHFELQ6IR1FCDNMSORLE9P6ARJK5T7M4SR5E9R62OJCCL96APJ5E9IMSOR57D662RJ4E9NMIP1FEPKMATPFAPKMATPR55B0____0(ObservableReference observablereference)
    {
        boolean flag;
        if (!((Boolean)observablereference.get()).booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        observablereference.set(Boolean.valueOf(flag));
    }
}
