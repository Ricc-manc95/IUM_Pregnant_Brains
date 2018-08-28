// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public static class itemView
{

    private static final List FULLUPDATE_PAYLOADS;
    public final View itemView;
    public int mFlags;
    public boolean mInChangeScrap;
    private int mIsRecyclableCount;
    public long mItemId;
    public int mItemViewType;
    public WeakReference mNestedRecyclerView;
    public int mOldPosition;
    public RecyclerView mOwnerRecyclerView;
    public List mPayloads;
    public int mPendingAccessibilityState;
    public int mPosition;
    public int mPreLayoutPosition;
    public FULLUPDATE_PAYLOADS mScrapContainer;
    public FULLUPDATE_PAYLOADS mShadowedHolder;
    public FULLUPDATE_PAYLOADS mShadowingHolder;
    private List mUnmodifiedPayloads;
    public int mWasImportantForAccessibilityBeforeHidden;

    public final void addChangePayload(Object obj)
    {
        if (obj == null)
        {
            mFlags = 0x400 | mFlags;
        } else
        if ((mFlags & 0x400) == 0)
        {
            if (mPayloads == null)
            {
                mPayloads = new ArrayList();
                mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
            }
            mPayloads.add(obj);
            return;
        }
    }

    final List getUnmodifiedPayloads()
    {
        if ((mFlags & 0x400) == 0)
        {
            if (mPayloads == null || mPayloads.size() == 0)
            {
                return FULLUPDATE_PAYLOADS;
            } else
            {
                return mUnmodifiedPayloads;
            }
        } else
        {
            return FULLUPDATE_PAYLOADS;
        }
    }

    final void offsetPosition(int i, boolean flag)
    {
        if (mOldPosition == -1)
        {
            mOldPosition = mPosition;
        }
        if (mPreLayoutPosition == -1)
        {
            mPreLayoutPosition = mPosition;
        }
        if (flag)
        {
            mPreLayoutPosition = mPreLayoutPosition + i;
        }
        mPosition = mPosition + i;
        if (itemView.getLayoutParams() != null)
        {
            ((s)itemView.getLayoutParams()).mInsetsDirty = true;
        }
    }

    final void resetInternal()
    {
        mFlags = 0;
        mPosition = -1;
        mOldPosition = -1;
        mItemId = -1L;
        mPreLayoutPosition = -1;
        mIsRecyclableCount = 0;
        mShadowedHolder = null;
        mShadowingHolder = null;
        if (mPayloads != null)
        {
            mPayloads.clear();
        }
        mFlags = mFlags & 0xfffffbff;
        mWasImportantForAccessibilityBeforeHidden = 0;
        mPendingAccessibilityState = -1;
        RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }

    public final void setIsRecyclable(boolean flag)
    {
        int i;
        if (flag)
        {
            i = mIsRecyclableCount - 1;
        } else
        {
            i = mIsRecyclableCount + 1;
        }
        mIsRecyclableCount = i;
        if (mIsRecyclableCount < 0)
        {
            mIsRecyclableCount = 0;
            Log.e("View", (new StringBuilder("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ")).append(this).toString());
        } else
        {
            if (!flag && mIsRecyclableCount == 1)
            {
                mFlags = mFlags | 0x10;
                return;
            }
            if (flag && mIsRecyclableCount == 0)
            {
                mFlags = mFlags & 0xffffffef;
                return;
            }
        }
    }

    public String toString()
    {
        boolean flag;
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder("ViewHolder{")).append(Integer.toHexString(hashCode())).append(" position=").append(mPosition).append(" id=").append(mItemId).append(", oldPos=").append(mOldPosition).append(", pLpos:").append(mPreLayoutPosition).toString());
        if (mScrapContainer != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            StringBuilder stringbuilder1 = stringbuilder.append(" scrap ");
            String s;
            if (mInChangeScrap)
            {
                s = "[changeScrap]";
            } else
            {
                s = "[attachedScrap]";
            }
            stringbuilder1.append(s);
        }
        if ((mFlags & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuilder.append(" invalid");
        }
        if ((mFlags & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            stringbuilder.append(" unbound");
        }
        if ((mFlags & 2) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuilder.append(" update");
        }
        if ((mFlags & 8) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuilder.append(" removed");
        }
        if ((mFlags & 0x80) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuilder.append(" ignored");
        }
        if ((mFlags & 0x100) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuilder.append(" tmpDetached");
        }
        if ((mFlags & 0x10) == 0 && !ViewCompat.hasTransientState(itemView))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            stringbuilder.append((new StringBuilder(" not recyclable(")).append(mIsRecyclableCount).append(")").toString());
        }
        if ((mFlags & 0x200) != 0) goto _L2; else goto _L1
_L1:
        if ((mFlags & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L3; else goto _L2
_L2:
        flag = true;
_L5:
        if (flag)
        {
            stringbuilder.append(" undefined adapter position");
        }
        if (itemView.getParent() == null)
        {
            stringbuilder.append(" no parent");
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
_L3:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
    }

    static 
    {
        FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
    }

    public s(View view)
    {
        mPosition = -1;
        mOldPosition = -1;
        mItemId = -1L;
        mItemViewType = -1;
        mPreLayoutPosition = -1;
        mShadowedHolder = null;
        mShadowingHolder = null;
        mPayloads = null;
        mUnmodifiedPayloads = null;
        mIsRecyclableCount = 0;
        mScrapContainer = null;
        mInChangeScrap = false;
        mWasImportantForAccessibilityBeforeHidden = 0;
        mPendingAccessibilityState = -1;
        if (view == null)
        {
            throw new IllegalArgumentException("itemView may not be null");
        } else
        {
            itemView = view;
            return;
        }
    }
}
