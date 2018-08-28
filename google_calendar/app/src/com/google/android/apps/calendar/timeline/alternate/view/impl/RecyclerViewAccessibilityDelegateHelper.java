// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public final class RecyclerViewAccessibilityDelegateHelper extends RecyclerViewAccessibilityDelegate
{

    private static final Rect INVALID_PARENT_BOUNDS = new Rect(0x7fffffff, 0x7fffffff, 0x80000000, 0x80000000);
    public int accessibilityFocusedVirtualViewId;
    public final Delegate _flddelegate;
    public final RecyclerView hostView;
    private int hoveredVirtualViewId;
    private int keyboardFocusedVirtualViewId;
    public final AccessibilityManager manager;
    private final int tempGlobalRect[] = new int[2];
    private final Rect tempParentRect = new Rect();
    private final Rect tempScreenRect = new Rect();
    private final Rect tempVisibleRect = new Rect();

    public RecyclerViewAccessibilityDelegateHelper(RecyclerView recyclerview, Delegate delegate1)
    {
        super(recyclerview);
        hoveredVirtualViewId = 0x80000000;
        accessibilityFocusedVirtualViewId = 0x80000000;
        keyboardFocusedVirtualViewId = 0x80000000;
        hostView = recyclerview;
        _flddelegate = delegate1;
        manager = (AccessibilityManager)recyclerview.getContext().getSystemService("accessibility");
        delegate1.setHelper(this);
    }

    private final boolean sendEventForVirtualView(int i, int j)
    {
        ViewParent viewparent;
        boolean flag;
        flag = false;
        if (i == 0x80000000 || !manager.isEnabled())
        {
            return false;
        }
        viewparent = hostView.getParent();
        if (viewparent == null)
        {
            return false;
        }
        i;
        JVM INSTR tableswitch -1 -1: default 56
    //                   -1 222;
           goto _L1 _L2
_L1:
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(j);
        if (i != -1) goto _L4; else goto _L3
_L3:
        Object obj;
        obj = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(hostView));
        ViewCompat.onInitializeAccessibilityNodeInfo(hostView, ((AccessibilityNodeInfoCompat) (obj)));
        _flddelegate.populateNodeForHost(hostView, ((AccessibilityNodeInfoCompat) (obj)));
          goto _L5
_L2:
        obj = AccessibilityEvent.obtain(j);
        hostView.onInitializeAccessibilityEvent(((AccessibilityEvent) (obj)));
_L20:
        return viewparent.requestSendAccessibilityEvent(hostView, ((AccessibilityEvent) (obj)));
_L4:
        accessibilitynodeinfocompat = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
        accessibilitynodeinfocompat.mInfo.setEnabled(true);
        accessibilitynodeinfocompat.mInfo.setFocusable(true);
        accessibilitynodeinfocompat.mInfo.setClassName("android.view.View");
        obj = INVALID_PARENT_BOUNDS;
        accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
        obj = INVALID_PARENT_BOUNDS;
        accessibilitynodeinfocompat.mInfo.setBoundsInScreen(((Rect) (obj)));
        obj = hostView;
        accessibilitynodeinfocompat.mInfo.setParent(((View) (obj)));
        if (!_flddelegate.populateNodeForVirtualView(hostView, i, accessibilitynodeinfocompat)) goto _L7; else goto _L6
_L6:
        if (accessibilitynodeinfocompat.mInfo.getText() == null && accessibilitynodeinfocompat.mInfo.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obj = tempParentRect;
        accessibilitynodeinfocompat.mInfo.getBoundsInParent(((Rect) (obj)));
        if (tempParentRect.equals(INVALID_PARENT_BOUNDS))
        {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        j = accessibilitynodeinfocompat.mInfo.getActions();
        if ((j & 0x40) != 0)
        {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((j & 0x80) != 0)
        {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obj = hostView.getContext().getPackageName();
        accessibilitynodeinfocompat.mInfo.setPackageName(((CharSequence) (obj)));
        obj = hostView;
        accessibilitynodeinfocompat.mInfo.setSource(((View) (obj)), i);
        boolean flag1;
        if (accessibilityFocusedVirtualViewId == i)
        {
            accessibilitynodeinfocompat.mInfo.setAccessibilityFocused(true);
            accessibilitynodeinfocompat.mInfo.addAction(128);
        } else
        {
            accessibilitynodeinfocompat.mInfo.setAccessibilityFocused(false);
            accessibilitynodeinfocompat.mInfo.addAction(64);
        }
        if (keyboardFocusedVirtualViewId == i)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            accessibilitynodeinfocompat.mInfo.addAction(2);
        } else
        if (accessibilitynodeinfocompat.mInfo.isFocusable())
        {
            accessibilitynodeinfocompat.mInfo.addAction(1);
        }
        accessibilitynodeinfocompat.mInfo.setFocused(flag1);
        hostView.getLocationOnScreen(tempGlobalRect);
        obj = tempScreenRect;
        accessibilitynodeinfocompat.mInfo.getBoundsInScreen(((Rect) (obj)));
        if (tempScreenRect.equals(INVALID_PARENT_BOUNDS))
        {
            obj = tempScreenRect;
            accessibilitynodeinfocompat.mInfo.getBoundsInParent(((Rect) (obj)));
            if (accessibilitynodeinfocompat.mParentVirtualDescendantId != -1)
            {
                obj = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
                for (j = accessibilitynodeinfocompat.mParentVirtualDescendantId; j != -1; j = ((AccessibilityNodeInfoCompat) (obj)).mParentVirtualDescendantId)
                {
                    Object obj1 = hostView;
                    obj.mParentVirtualDescendantId = -1;
                    ((AccessibilityNodeInfoCompat) (obj)).mInfo.setParent(((View) (obj1)), -1);
                    obj1 = INVALID_PARENT_BOUNDS;
                    ((AccessibilityNodeInfoCompat) (obj)).mInfo.setBoundsInParent(((Rect) (obj1)));
                    _flddelegate.populateNodeForVirtualView(hostView, j, ((AccessibilityNodeInfoCompat) (obj)));
                    obj1 = tempParentRect;
                    ((AccessibilityNodeInfoCompat) (obj)).mInfo.getBoundsInParent(((Rect) (obj1)));
                    tempScreenRect.offset(tempParentRect.left, tempParentRect.top);
                }

                ((AccessibilityNodeInfoCompat) (obj)).mInfo.recycle();
            }
            tempScreenRect.offset(tempGlobalRect[0] - hostView.getScrollX(), tempGlobalRect[1] - hostView.getScrollY());
        }
        if (!hostView.getLocalVisibleRect(tempVisibleRect)) goto _L7; else goto _L8
_L8:
        tempVisibleRect.offset(tempGlobalRect[0] - hostView.getScrollX(), tempGlobalRect[1] - hostView.getScrollY());
        if (!tempScreenRect.intersect(tempVisibleRect)) goto _L7; else goto _L9
_L9:
        obj = tempScreenRect;
        accessibilitynodeinfocompat.mInfo.setBoundsInScreen(((Rect) (obj)));
        obj = tempScreenRect;
        j = ((flag) ? 1 : 0);
        if (obj == null) goto _L11; else goto _L10
_L10:
        if (!((Rect) (obj)).isEmpty()) goto _L13; else goto _L12
_L12:
        j = ((flag) ? 1 : 0);
_L11:
        if (j != 0)
        {
            accessibilitynodeinfocompat.mInfo.setVisibleToUser(true);
        }
_L7:
        obj = accessibilitynodeinfocompat;
          goto _L5
_L13:
        j = ((flag) ? 1 : 0);
        if (hostView.getWindowVisibility() != 0) goto _L11; else goto _L14
_L14:
        obj = hostView.getParent();
_L19:
        if (!(obj instanceof View)) goto _L16; else goto _L15
_L15:
        obj = (View)obj;
        j = ((flag) ? 1 : 0);
        if (((View) (obj)).getAlpha() <= 0.0F) goto _L11; else goto _L17
_L17:
        j = ((flag) ? 1 : 0);
        if (((View) (obj)).getVisibility() != 0) goto _L11; else goto _L18
_L18:
        obj = ((View) (obj)).getParent();
          goto _L19
_L16:
        j = ((flag) ? 1 : 0);
        if (obj != null)
        {
            j = 1;
        }
          goto _L11
_L5:
        accessibilityevent.getText().add(((AccessibilityNodeInfoCompat) (obj)).mInfo.getText());
        accessibilityevent.setContentDescription(((AccessibilityNodeInfoCompat) (obj)).mInfo.getContentDescription());
        accessibilityevent.setScrollable(((AccessibilityNodeInfoCompat) (obj)).mInfo.isScrollable());
        accessibilityevent.setPassword(((AccessibilityNodeInfoCompat) (obj)).mInfo.isPassword());
        accessibilityevent.setEnabled(((AccessibilityNodeInfoCompat) (obj)).mInfo.isEnabled());
        accessibilityevent.setChecked(((AccessibilityNodeInfoCompat) (obj)).mInfo.isChecked());
        _flddelegate._mth514KOOBECHP6UQB45TR6IPBN5TGM6OR5EDPMIOJ9DHKN8U9F85HM6PBJEDKM4QBCD5Q7IHBMCLN78EP9AO______0();
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat;
        if (accessibilityevent.getText().isEmpty() && accessibilityevent.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityevent.setClassName(((AccessibilityNodeInfoCompat) (obj)).mInfo.getClassName());
        accessibilityevent.setSource(hostView, i);
        accessibilityevent.setPackageName(hostView.getContext().getPackageName());
        obj = accessibilityevent;
          goto _L20
    }

    final boolean clearAccessibilityFocus(int i)
    {
        if (accessibilityFocusedVirtualViewId == i)
        {
            accessibilityFocusedVirtualViewId = 0x80000000;
            hostView.invalidate();
            sendEventForVirtualView(i, 0x10000);
            return true;
        } else
        {
            return false;
        }
    }

    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        return new _cls1();
    }

    final AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat2;
        if (i == -1)
        {
            AccessibilityNodeInfoCompat accessibilitynodeinfocompat = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(hostView));
            ViewCompat.onInitializeAccessibilityNodeInfo(hostView, accessibilitynodeinfocompat);
            _flddelegate.populateNodeForHost(hostView, accessibilitynodeinfocompat);
            return accessibilitynodeinfocompat;
        }
        accessibilitynodeinfocompat2 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
        accessibilitynodeinfocompat2.mInfo.setEnabled(true);
        accessibilitynodeinfocompat2.mInfo.setFocusable(true);
        accessibilitynodeinfocompat2.mInfo.setClassName("android.view.View");
        Object obj = INVALID_PARENT_BOUNDS;
        accessibilitynodeinfocompat2.mInfo.setBoundsInParent(((Rect) (obj)));
        obj = INVALID_PARENT_BOUNDS;
        accessibilitynodeinfocompat2.mInfo.setBoundsInScreen(((Rect) (obj)));
        obj = hostView;
        accessibilitynodeinfocompat2.mInfo.setParent(((View) (obj)));
        if (!_flddelegate.populateNodeForVirtualView(hostView, i, accessibilitynodeinfocompat2)) goto _L2; else goto _L1
_L1:
        if (accessibilitynodeinfocompat2.mInfo.getText() == null && accessibilitynodeinfocompat2.mInfo.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        Object obj1 = tempParentRect;
        accessibilitynodeinfocompat2.mInfo.getBoundsInParent(((Rect) (obj1)));
        if (tempParentRect.equals(INVALID_PARENT_BOUNDS))
        {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int j = accessibilitynodeinfocompat2.mInfo.getActions();
        if ((j & 0x40) != 0)
        {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((j & 0x80) != 0)
        {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obj1 = hostView.getContext().getPackageName();
        accessibilitynodeinfocompat2.mInfo.setPackageName(((CharSequence) (obj1)));
        obj1 = hostView;
        accessibilitynodeinfocompat2.mInfo.setSource(((View) (obj1)), i);
        boolean flag;
        if (accessibilityFocusedVirtualViewId == i)
        {
            accessibilitynodeinfocompat2.mInfo.setAccessibilityFocused(true);
            accessibilitynodeinfocompat2.mInfo.addAction(128);
        } else
        {
            accessibilitynodeinfocompat2.mInfo.setAccessibilityFocused(false);
            accessibilitynodeinfocompat2.mInfo.addAction(64);
        }
        if (keyboardFocusedVirtualViewId == i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            accessibilitynodeinfocompat2.mInfo.addAction(2);
        } else
        if (accessibilitynodeinfocompat2.mInfo.isFocusable())
        {
            accessibilitynodeinfocompat2.mInfo.addAction(1);
        }
        accessibilitynodeinfocompat2.mInfo.setFocused(flag);
        hostView.getLocationOnScreen(tempGlobalRect);
        obj1 = tempScreenRect;
        accessibilitynodeinfocompat2.mInfo.getBoundsInScreen(((Rect) (obj1)));
        if (tempScreenRect.equals(INVALID_PARENT_BOUNDS))
        {
            Rect rect = tempScreenRect;
            accessibilitynodeinfocompat2.mInfo.getBoundsInParent(rect);
            if (accessibilitynodeinfocompat2.mParentVirtualDescendantId != -1)
            {
                AccessibilityNodeInfoCompat accessibilitynodeinfocompat1 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
                for (i = accessibilitynodeinfocompat2.mParentVirtualDescendantId; i != -1; i = accessibilitynodeinfocompat1.mParentVirtualDescendantId)
                {
                    Object obj3 = hostView;
                    accessibilitynodeinfocompat1.mParentVirtualDescendantId = -1;
                    accessibilitynodeinfocompat1.mInfo.setParent(((View) (obj3)), -1);
                    obj3 = INVALID_PARENT_BOUNDS;
                    accessibilitynodeinfocompat1.mInfo.setBoundsInParent(((Rect) (obj3)));
                    _flddelegate.populateNodeForVirtualView(hostView, i, accessibilitynodeinfocompat1);
                    obj3 = tempParentRect;
                    accessibilitynodeinfocompat1.mInfo.getBoundsInParent(((Rect) (obj3)));
                    tempScreenRect.offset(tempParentRect.left, tempParentRect.top);
                }

                accessibilitynodeinfocompat1.mInfo.recycle();
            }
            tempScreenRect.offset(tempGlobalRect[0] - hostView.getScrollX(), tempGlobalRect[1] - hostView.getScrollY());
        }
        if (!hostView.getLocalVisibleRect(tempVisibleRect)) goto _L2; else goto _L3
_L3:
        tempVisibleRect.offset(tempGlobalRect[0] - hostView.getScrollX(), tempGlobalRect[1] - hostView.getScrollY());
        if (!tempScreenRect.intersect(tempVisibleRect)) goto _L2; else goto _L4
_L4:
        Rect rect1;
        rect1 = tempScreenRect;
        accessibilitynodeinfocompat2.mInfo.setBoundsInScreen(rect1);
        rect1 = tempScreenRect;
        if (rect1 != null && !rect1.isEmpty()) goto _L6; else goto _L5
_L5:
        i = 0;
_L8:
        if (i != 0)
        {
            accessibilitynodeinfocompat2.mInfo.setVisibleToUser(true);
        }
_L2:
        return accessibilitynodeinfocompat2;
_L6:
        if (hostView.getWindowVisibility() == 0)
        {
            Object obj2 = hostView.getParent();
            do
            {
                if (!(obj2 instanceof View))
                {
                    break;
                }
                obj2 = (View)obj2;
                if (((View) (obj2)).getAlpha() <= 0.0F || ((View) (obj2)).getVisibility() != 0)
                {
                    i = 0;
                    continue; /* Loop/switch isn't completed */
                }
                obj2 = ((View) (obj2)).getParent();
            } while (true);
            if (obj2 != null)
            {
                i = 1;
                continue; /* Loop/switch isn't completed */
            }
        }
        i = 0;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        accessibilitynodeinfocompat.mInfo.setScrollable(false);
        accessibilitynodeinfocompat.mInfo.addAction(8192);
        accessibilitynodeinfocompat.mInfo.addAction(4096);
        view = new android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat(android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(0, 0, false, 1));
        accessibilitynodeinfocompat = accessibilitynodeinfocompat.mInfo;
        if (view == null)
        {
            view = null;
        } else
        {
            view = (android.view.accessibility.AccessibilityNodeInfo.CollectionInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat)view).mInfo;
        }
        accessibilitynodeinfocompat.setCollectionInfo(view);
    }

    public final boolean requestAccessibilityFocus(int i)
    {
        while (!manager.isEnabled() || !manager.isTouchExplorationEnabled() || accessibilityFocusedVirtualViewId == i) 
        {
            return false;
        }
        if (accessibilityFocusedVirtualViewId != 0x80000000)
        {
            clearAccessibilityFocus(accessibilityFocusedVirtualViewId);
        }
        accessibilityFocusedVirtualViewId = i;
        hostView.invalidate();
        sendEventForVirtualView(i, 32768);
        return true;
    }

    final void updateHoveredVirtualView(int i)
    {
        if (hoveredVirtualViewId == i)
        {
            return;
        } else
        {
            int j = hoveredVirtualViewId;
            hoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(j, 256);
            return;
        }
    }


    private class Delegate
    {

        public abstract int getVirtualViewAt(float f, float f1);

        public abstract boolean performActionForChild$514KIJ31DPI74RR9CGNMUSPF89QMSP3CCKTIIMG_0(int i, int j);

        public abstract boolean performActionForHost$514KOOBECHP6UQB45TNN6BQ2ELN68R357CKLK___0(int i);

        public abstract void populateEventForVirtualView$514KOOBECHP6UQB45TR6IPBN5TGM6OR5EDPMIOJ9DHKN8U9F85HM6PBJEDKM4QBCD5Q7IHBMCLN78EP9AO______0();

        public abstract void populateNodeForHost(RecyclerView recyclerview, AccessibilityNodeInfoCompat accessibilitynodeinfocompat);

        public abstract boolean populateNodeForVirtualView(RecyclerView recyclerview, int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat);

        public abstract void requestAccessibilityFocusOnNextUpdate(int i);

        public abstract void setHelper(RecyclerViewAccessibilityDelegateHelper recyclerviewaccessibilitydelegatehelper);
    }


    private class _cls1 extends AccessibilityNodeProviderCompat
    {

        private final RecyclerViewAccessibilityDelegateHelper this$0;

        public final AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
        {
            return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(obtainAccessibilityNodeInfo(i).mInfo));
        }

        public final boolean performAction(int i, int j, Bundle bundle)
        {
            RecyclerViewAccessibilityDelegateHelper recyclerviewaccessibilitydelegatehelper = RecyclerViewAccessibilityDelegateHelper.this;
            i;
            JVM INSTR tableswitch -1 -1: default 24
        //                       -1 41;
               goto _L1 _L2
_L1:
            if (!recyclerviewaccessibilitydelegatehelper._flddelegate._mth514KIJ31DPI74RR9CGNMUSPF89QMSP3CCKTIIMG_0(i, j)) goto _L4; else goto _L3
_L3:
            return true;
_L2:
            if (!recyclerviewaccessibilitydelegatehelper._flddelegate._mth514KOOBECHP6UQB45TNN6BQ2ELN68R357CKLK___0(j) && !ViewCompat.performAccessibilityAction(recyclerviewaccessibilitydelegatehelper.hostView, j, bundle))
            {
                return false;
            }
            if (true) goto _L3; else goto _L4
_L4:
            switch (j)
            {
            default:
                return ViewCompat.performAccessibilityAction(recyclerviewaccessibilitydelegatehelper.hostView, j, bundle);

            case 64: // '@'
                return recyclerviewaccessibilitydelegatehelper.requestAccessibilityFocus(i);

            case 128: 
                return recyclerviewaccessibilitydelegatehelper.clearAccessibilityFocus(i);
            }
        }

        _cls1()
        {
            this$0 = RecyclerViewAccessibilityDelegateHelper.this;
            super();
        }
    }

}
