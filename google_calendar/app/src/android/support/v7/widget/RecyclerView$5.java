// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

final class this._cls0
    implements lback
{

    private final RecyclerView this$0;

    public final void addView(View view, int i)
    {
        RecyclerView.this.addView(view, i);
        RecyclerView recyclerview = RecyclerView.this;
        if (view == null)
        {
            view = null;
        } else
        {
            view = ((youtParams)view.getLayoutParams()).mViewHolder;
        }
        if (recyclerview.mAdapter != null && view != null)
        {
            recyclerview.mAdapter.onViewAttachedToWindow(view);
        }
    }

    public final void attachViewToParent(View view, int i, android.view.tParams tparams)
    {
        boolean flag1 = true;
        ewHolder ewholder = RecyclerView.getChildViewHolderInt(view);
        if (ewholder != null)
        {
            boolean flag;
            if ((ewholder.mFlags & 0x100) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if ((ewholder.mFlags & 0x80) != 0)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException((new StringBuilder("Called attach on a child which is not detached: ")).append(ewholder).append(exceptionLabel()).toString());
                }
            }
            ewholder.mFlags = ewholder.mFlags & 0xfffffeff;
        }
        RecyclerView.access$000(RecyclerView.this, view, i, tparams);
    }

    public final void detachViewFromParent(int i)
    {
        boolean flag1 = true;
        Object obj = RecyclerView.this.getChildAt(i);
        if (obj != null)
        {
            obj = RecyclerView.getChildViewHolderInt(((View) (obj)));
            if (obj != null)
            {
                boolean flag;
                if ((((ewHolder) (obj)).mFlags & 0x100) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if ((((ewHolder) (obj)).mFlags & 0x80) != 0)
                    {
                        flag = flag1;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        throw new IllegalArgumentException((new StringBuilder("called detach on an already detached child ")).append(obj).append(exceptionLabel()).toString());
                    }
                }
                obj.mFlags = 0x100 | ((ewHolder) (obj)).mFlags;
            }
        }
        RecyclerView.access$100(RecyclerView.this, i);
    }

    public final View getChildAt(int i)
    {
        return RecyclerView.this.getChildAt(i);
    }

    public final int getChildCount()
    {
        return RecyclerView.this.getChildCount();
    }

    public final ewHolder getChildViewHolder(View view)
    {
        return RecyclerView.getChildViewHolderInt(view);
    }

    public final int indexOfChild(View view)
    {
        return RecyclerView.this.indexOfChild(view);
    }

    public final void onEnteredHiddenState(View view)
    {
        view = RecyclerView.getChildViewHolderInt(view);
        if (view != null)
        {
            RecyclerView recyclerview = RecyclerView.this;
            if (((ewHolder) (view)).mPendingAccessibilityState != -1)
            {
                view.mWasImportantForAccessibilityBeforeHidden = ((ewHolder) (view)).mPendingAccessibilityState;
            } else
            {
                view.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(((ewHolder) (view)).itemView);
            }
            recyclerview.setChildImportantForAccessibilityInternal(view, 4);
        }
    }

    public final void onLeftHiddenState(View view)
    {
        view = RecyclerView.getChildViewHolderInt(view);
        if (view != null)
        {
            setChildImportantForAccessibilityInternal(view, ((ewHolder) (view)).mWasImportantForAccessibilityBeforeHidden);
            view.mWasImportantForAccessibilityBeforeHidden = 0;
        }
    }

    public final void removeAllViews()
    {
        int j = RecyclerView.this.getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = RecyclerView.this.getChildAt(i);
            dispatchChildDetached(view);
            view.clearAnimation();
        }

        RecyclerView.this.removeAllViews();
    }

    public final void removeViewAt(int i)
    {
        View view = RecyclerView.this.getChildAt(i);
        if (view != null)
        {
            dispatchChildDetached(view);
            view.clearAnimation();
        }
        RecyclerView.this.removeViewAt(i);
    }

    ewHolder()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
