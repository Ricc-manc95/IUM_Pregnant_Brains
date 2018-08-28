// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.view.horizontalcarousel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

// Referenced classes of package com.google.android.libraries.view.horizontalcarousel:
//            HorizontalCarousel

public abstract class HorizontalCarouselAdapter extends android.support.v7.widget.RecyclerView.Adapter
{
    public static interface OnCarouselTileClickListener
    {

        public abstract void onCarouselTileClick(View view, Object obj);
    }


    public static final String TAG = com/google/android/libraries/view/horizontalcarousel/HorizontalCarouselAdapter.getSimpleName();
    public HorizontalCarousel horizontalCarousel;
    public OnCarouselTileClickListener onCarouselTileClickListener;
    private android.view.View.OnClickListener onClickListener;

    public HorizontalCarouselAdapter()
    {
        onClickListener = new _cls1();
    }

    public abstract Object getItemForPosition(int i);

    public final void onAttachedToRecyclerView(RecyclerView recyclerview)
    {
        super.onAttachedToRecyclerView(recyclerview);
        horizontalCarousel = (HorizontalCarousel)recyclerview;
    }

    public final void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewholder, int i)
    {
        viewholder = (HorizontalCarousel.ViewHolder)viewholder;
        onInternalBindViewHolder(viewholder, i);
        if (((HorizontalCarousel.ViewHolder) (viewholder)).itemView.getTag(0x7f10000f) == null)
        {
            ((HorizontalCarousel.ViewHolder) (viewholder)).itemView.setOnClickListener(onClickListener);
        }
    }

    public final void onDetachedFromRecyclerView(RecyclerView recyclerview)
    {
        super.onDetachedFromRecyclerView(recyclerview);
        horizontalCarousel = null;
    }

    public abstract void onInternalBindViewHolder(HorizontalCarousel.ViewHolder viewholder, int i);


    private class _cls1
        implements android.view.View.OnClickListener
    {

        private final HorizontalCarouselAdapter this$0;

        public final void onClick(View view)
        {
            HorizontalCarousel.ViewHolder viewholder = (HorizontalCarousel.ViewHolder)view.getTag(0x7f10000d);
            if (viewholder != null)
            {
                int i;
                if (((android.support.v7.widget.RecyclerView.ViewHolder) (viewholder)).mPreLayoutPosition == -1)
                {
                    i = ((android.support.v7.widget.RecyclerView.ViewHolder) (viewholder)).mPosition;
                } else
                {
                    i = ((android.support.v7.widget.RecyclerView.ViewHolder) (viewholder)).mPreLayoutPosition;
                }
                if (onCarouselTileClickListener != null)
                {
                    onCarouselTileClickListener.onCarouselTileClick(view, getItemForPosition(i));
                    return;
                } else
                {
                    Log.e(HorizontalCarouselAdapter.TAG, (new StringBuilder(60)).append("onCarouselTileClickListener is null for position ").append(i).toString());
                    return;
                }
            } else
            {
                String s = HorizontalCarouselAdapter.TAG;
                view = String.valueOf(view);
                Log.e(s, (new StringBuilder(String.valueOf(view).length() + 28)).append("ViewHolder is null for view ").append(view).toString());
                return;
            }
        }

        _cls1()
        {
            this$0 = HorizontalCarouselAdapter.this;
            super();
        }
    }

}
