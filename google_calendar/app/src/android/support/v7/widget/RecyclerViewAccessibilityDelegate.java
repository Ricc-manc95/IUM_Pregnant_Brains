// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

public class RecyclerViewAccessibilityDelegate extends AccessibilityDelegateCompat
{

    private final AccessibilityDelegateCompat mItemDelegate = new ItemDelegate();
    public final RecyclerView mRecyclerView;

    public RecyclerViewAccessibilityDelegate(RecyclerView recyclerview)
    {
        mRecyclerView = recyclerview;
    }

    public AccessibilityDelegateCompat getItemDelegate()
    {
        return mItemDelegate;
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(android/support/v7/widget/RecyclerView.getName());
        if ((view instanceof RecyclerView) && !mRecyclerView.hasPendingAdapterUpdates())
        {
            view = (RecyclerView)view;
            if (((RecyclerView) (view)).mLayout != null)
            {
                ((RecyclerView) (view)).mLayout.onInitializeAccessibilityEvent(accessibilityevent);
            }
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        view = android/support/v7/widget/RecyclerView.getName();
        accessibilitynodeinfocompat.mInfo.setClassName(view);
        if (!mRecyclerView.hasPendingAdapterUpdates() && mRecyclerView.mLayout != null)
        {
            view = mRecyclerView.mLayout;
            view.onInitializeAccessibilityNodeInfo(((RecyclerView.LayoutManager) (view)).mRecyclerView.mRecycler, ((RecyclerView.LayoutManager) (view)).mRecyclerView.mState, accessibilitynodeinfocompat);
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        boolean flag1 = false;
        if (!super.performAccessibilityAction(view, i, bundle)) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        flag = flag1;
        if (mRecyclerView.hasPendingAdapterUpdates()) goto _L4; else goto _L3
_L3:
        flag = flag1;
        if (mRecyclerView.mLayout == null) goto _L4; else goto _L5
_L5:
        view = mRecyclerView.mLayout;
        bundle = ((RecyclerView.LayoutManager) (view)).mRecyclerView.mRecycler;
        bundle = ((RecyclerView.LayoutManager) (view)).mRecyclerView.mState;
        flag = flag1;
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView == null) goto _L4; else goto _L6
_L6:
        i;
        JVM INSTR lookupswitch 2: default 108
    //                   4096: 290
    //                   8192: 138;
           goto _L7 _L8 _L9
_L7:
        int j;
        i = 0;
        j = 0;
_L13:
        if (j != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (i == 0) goto _L4; else goto _L10
_L10:
        ((RecyclerView.LayoutManager) (view)).mRecyclerView.smoothScrollBy(i, j);
        return true;
_L9:
        int k;
        int i1;
        int j1;
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView.canScrollVertically(-1))
        {
            k = ((RecyclerView.LayoutManager) (view)).mHeight;
            int l;
            if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
            {
                i = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingTop();
            } else
            {
                i = 0;
            }
            if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
            {
                j = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingBottom();
            } else
            {
                j = 0;
            }
            i = -(k - i - j);
        } else
        {
            i = 0;
        }
        j = i;
        if (!((RecyclerView.LayoutManager) (view)).mRecyclerView.canScrollHorizontally(-1)) goto _L12; else goto _L11
_L11:
        l = ((RecyclerView.LayoutManager) (view)).mWidth;
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
        {
            j = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingLeft();
        } else
        {
            j = 0;
        }
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
        {
            k = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingRight();
        } else
        {
            k = 0;
        }
        k = -(l - j - k);
        j = i;
        i = k;
          goto _L13
_L8:
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView.canScrollVertically(1))
        {
            k = ((RecyclerView.LayoutManager) (view)).mHeight;
            if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
            {
                i = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingTop();
            } else
            {
                i = 0;
            }
            if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
            {
                j = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingBottom();
            } else
            {
                j = 0;
            }
            i = k - i - j;
        } else
        {
            i = 0;
        }
        j = i;
        if (!((RecyclerView.LayoutManager) (view)).mRecyclerView.canScrollHorizontally(1)) goto _L12; else goto _L14
_L14:
        j1 = ((RecyclerView.LayoutManager) (view)).mWidth;
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
        {
            j = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingLeft();
        } else
        {
            j = 0;
        }
        if (((RecyclerView.LayoutManager) (view)).mRecyclerView != null)
        {
            i1 = ((RecyclerView.LayoutManager) (view)).mRecyclerView.getPaddingRight();
        } else
        {
            i1 = 0;
        }
        k = i;
        i = j1 - j - i1;
        j = k;
          goto _L13
_L12:
        i = 0;
          goto _L13
    }

    private class ItemDelegate extends AccessibilityDelegateCompat
    {

        private final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if (!mRecyclerViewDelegate.mRecyclerView.hasPendingAdapterUpdates() && mRecyclerViewDelegate.mRecyclerView.mLayout != null)
            {
                RecyclerView.LayoutManager layoutmanager = mRecyclerViewDelegate.mRecyclerView.mLayout;
                Object obj1 = RecyclerView.getChildViewHolderInt(view);
                if (obj1 != null)
                {
                    int i;
                    if ((((RecyclerView.ViewHolder) (obj1)).mFlags & 8) != 0)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        ChildHelper childhelper = layoutmanager.mChildHelper;
                        obj1 = ((RecyclerView.ViewHolder) (obj1)).itemView;
                        if (!childhelper.mHiddenViews.contains(obj1))
                        {
                            Object obj = layoutmanager.mRecyclerView.mRecycler;
                            obj = layoutmanager.mRecyclerView.mState;
                            int j;
                            if (layoutmanager.canScrollVertically())
                            {
                                i = RecyclerView.LayoutManager.getPosition(view);
                            } else
                            {
                                i = 0;
                            }
                            if (layoutmanager.canScrollHorizontally())
                            {
                                j = RecyclerView.LayoutManager.getPosition(view);
                            } else
                            {
                                j = 0;
                            }
                            view = new android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat(android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, 1, j, 1, false, false));
                            accessibilitynodeinfocompat = accessibilitynodeinfocompat.mInfo;
                            if (view == null)
                            {
                                view = null;
                            } else
                            {
                                view = (android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat)view).mInfo;
                            }
                            accessibilitynodeinfocompat.setCollectionItemInfo(view);
                        }
                    }
                }
            }
        }

        public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            boolean flag1 = false;
            boolean flag;
            if (super.performAccessibilityAction(view, i, bundle))
            {
                flag = true;
            } else
            {
                flag = flag1;
                if (!mRecyclerViewDelegate.mRecyclerView.hasPendingAdapterUpdates())
                {
                    flag = flag1;
                    if (mRecyclerViewDelegate.mRecyclerView.mLayout != null)
                    {
                        view = mRecyclerViewDelegate.mRecyclerView.mLayout;
                        bundle = ((RecyclerView.LayoutManager) (view)).mRecyclerView.mRecycler;
                        view = ((RecyclerView.LayoutManager) (view)).mRecyclerView.mState;
                        return false;
                    }
                }
            }
            return flag;
        }

        public ItemDelegate()
        {
            mRecyclerViewDelegate = RecyclerViewAccessibilityDelegate.this;
        }
    }

}
