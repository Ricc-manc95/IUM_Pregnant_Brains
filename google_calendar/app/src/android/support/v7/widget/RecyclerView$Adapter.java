// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Trace;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public static abstract class mHasStableIds
{

    public boolean mHasStableIds;
    public final ataObservable mObservable = new ataObservable();

    public final er createViewHolder(ViewGroup viewgroup, int i)
    {
        Trace.beginSection("RV CreateView");
        viewgroup = onCreateViewHolder(viewgroup, i);
        if (((er) (viewgroup)).itemView.getParent() != null)
        {
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        }
        break MISSING_BLOCK_LABEL_38;
        viewgroup;
        Trace.endSection();
        throw viewgroup;
        viewgroup.mItemViewType = i;
        Trace.endSection();
        return viewgroup;
    }

    public abstract int getItemCount();

    public long getItemId(int i)
    {
        return -1L;
    }

    public int getItemViewType(int i)
    {
        return 0;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerview)
    {
    }

    public abstract void onBindViewHolder(er er, int i);

    public void onBindViewHolder(er er, int i, List list)
    {
        onBindViewHolder(er, i);
    }

    public abstract er onCreateViewHolder(ViewGroup viewgroup, int i);

    public void onDetachedFromRecyclerView(RecyclerView recyclerview)
    {
    }

    public void onViewAttachedToWindow(er er)
    {
    }

    public void onViewDetachedFromWindow(er er)
    {
    }

    public void onViewRecycled(er er)
    {
    }

    public er()
    {
        mHasStableIds = false;
    }
}
