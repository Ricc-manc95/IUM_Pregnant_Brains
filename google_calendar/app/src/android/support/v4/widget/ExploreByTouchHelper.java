// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v4.widget:
//            FocusStrategy

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
    final class MyNodeProvider extends AccessibilityNodeProviderCompat
    {

        private final ExploreByTouchHelper this$0;

        public final AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
        {
            return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(obtainAccessibilityNodeInfo(i).mInfo));
        }

        public final AccessibilityNodeInfoCompat findFocus(int i)
        {
            if (i == 2)
            {
                i = mAccessibilityFocusedVirtualViewId;
            } else
            {
                i = mKeyboardFocusedVirtualViewId;
            }
            if (i == 0x80000000)
            {
                return null;
            } else
            {
                return createAccessibilityNodeInfo(i);
            }
        }

        public final boolean performAction(int i, int j, Bundle bundle)
        {
            ExploreByTouchHelper explorebytouchhelper = ExploreByTouchHelper.this;
            switch (i)
            {
            default:
                switch (j)
                {
                default:
                    return explorebytouchhelper.onPerformActionForVirtualView(i, j, bundle);

                case 64: // '@'
                    if (!explorebytouchhelper.mManager.isEnabled() || !explorebytouchhelper.mManager.isTouchExplorationEnabled())
                    {
                        return false;
                    }
                    if (explorebytouchhelper.mAccessibilityFocusedVirtualViewId != i)
                    {
                        if (explorebytouchhelper.mAccessibilityFocusedVirtualViewId != 0x80000000)
                        {
                            explorebytouchhelper.clearAccessibilityFocus(explorebytouchhelper.mAccessibilityFocusedVirtualViewId);
                        }
                        explorebytouchhelper.mAccessibilityFocusedVirtualViewId = i;
                        explorebytouchhelper.mHost.invalidate();
                        explorebytouchhelper.sendEventForVirtualView(i, 32768);
                        return true;
                    } else
                    {
                        return false;
                    }

                case 128: 
                    return explorebytouchhelper.clearAccessibilityFocus(i);

                case 1: // '\001'
                    return explorebytouchhelper.requestKeyboardFocusForVirtualView(i);

                case 2: // '\002'
                    break;
                }
                break;

            case -1: 
                return ViewCompat.performAccessibilityAction(explorebytouchhelper.mHost, j, bundle);
            }
            if (explorebytouchhelper.mKeyboardFocusedVirtualViewId != i)
            {
                return false;
            } else
            {
                explorebytouchhelper.mKeyboardFocusedVirtualViewId = 0x80000000;
                explorebytouchhelper.sendEventForVirtualView(i, 8);
                return true;
            }
        }

        MyNodeProvider()
        {
            this$0 = ExploreByTouchHelper.this;
            super();
        }
    }


    private static final Rect INVALID_PARENT_BOUNDS = new Rect(0x7fffffff, 0x7fffffff, 0x80000000, 0x80000000);
    private static final FocusStrategy.BoundsAdapter NODE_ADAPTER = new _cls1();
    private static final FocusStrategy.CollectionAdapter SPARSE_VALUES_ADAPTER = new _cls2();
    public int mAccessibilityFocusedVirtualViewId;
    public final View mHost;
    public int mHoveredVirtualViewId;
    public int mKeyboardFocusedVirtualViewId;
    public final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int mTempGlobalRect[] = new int[2];
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();

    public ExploreByTouchHelper(View view)
    {
        mAccessibilityFocusedVirtualViewId = 0x80000000;
        mKeyboardFocusedVirtualViewId = 0x80000000;
        mHoveredVirtualViewId = 0x80000000;
        if (view == null)
        {
            throw new IllegalArgumentException("View may not be null");
        }
        mHost = view;
        mManager = (AccessibilityManager)view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(view) == 0)
        {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
    }

    private final AccessibilityEvent createEvent(int i, int j)
    {
        switch (i)
        {
        default:
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(j);
            Object obj;
            if (i == -1)
            {
                obj = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(mHost));
                ViewCompat.onInitializeAccessibilityNodeInfo(mHost, ((AccessibilityNodeInfoCompat) (obj)));
                ArrayList arraylist = new ArrayList();
                getVisibleVirtualViews(arraylist);
                if (((AccessibilityNodeInfoCompat) (obj)).mInfo.getChildCount() > 0 && arraylist.size() > 0)
                {
                    throw new RuntimeException("Views cannot have both real and virtual children");
                }
                int k = arraylist.size();
                for (j = 0; j < k; j++)
                {
                    View view = mHost;
                    int l = ((Integer)arraylist.get(j)).intValue();
                    ((AccessibilityNodeInfoCompat) (obj)).mInfo.addChild(view, l);
                }

            } else
            {
                obj = createNodeForChild(i);
            }
            accessibilityevent.getText().add(((AccessibilityNodeInfoCompat) (obj)).mInfo.getText());
            accessibilityevent.setContentDescription(((AccessibilityNodeInfoCompat) (obj)).mInfo.getContentDescription());
            accessibilityevent.setScrollable(((AccessibilityNodeInfoCompat) (obj)).mInfo.isScrollable());
            accessibilityevent.setPassword(((AccessibilityNodeInfoCompat) (obj)).mInfo.isPassword());
            accessibilityevent.setEnabled(((AccessibilityNodeInfoCompat) (obj)).mInfo.isEnabled());
            accessibilityevent.setChecked(((AccessibilityNodeInfoCompat) (obj)).mInfo.isChecked());
            onPopulateEventForVirtualView(i, accessibilityevent);
            if (accessibilityevent.getText().isEmpty() && accessibilityevent.getContentDescription() == null)
            {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            } else
            {
                accessibilityevent.setClassName(((AccessibilityNodeInfoCompat) (obj)).mInfo.getClassName());
                accessibilityevent.setSource(mHost, i);
                accessibilityevent.setPackageName(mHost.getContext().getPackageName());
                return accessibilityevent;
            }

        case -1: 
            obj = AccessibilityEvent.obtain(j);
            mHost.onInitializeAccessibilityEvent(((AccessibilityEvent) (obj)));
            return ((AccessibilityEvent) (obj));
        }
    }

    private final AccessibilityNodeInfoCompat createNodeForChild(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat1;
        accessibilitynodeinfocompat1 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
        accessibilitynodeinfocompat1.mInfo.setEnabled(true);
        accessibilitynodeinfocompat1.mInfo.setFocusable(true);
        accessibilitynodeinfocompat1.mInfo.setClassName("android.view.View");
        Object obj = INVALID_PARENT_BOUNDS;
        accessibilitynodeinfocompat1.mInfo.setBoundsInParent(((Rect) (obj)));
        obj = INVALID_PARENT_BOUNDS;
        accessibilitynodeinfocompat1.mInfo.setBoundsInScreen(((Rect) (obj)));
        obj = mHost;
        accessibilitynodeinfocompat1.mInfo.setParent(((View) (obj)));
        onPopulateNodeForVirtualView(i, accessibilitynodeinfocompat1);
        if (accessibilitynodeinfocompat1.mInfo.getText() == null && accessibilitynodeinfocompat1.mInfo.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obj = mTempParentRect;
        accessibilitynodeinfocompat1.mInfo.getBoundsInParent(((Rect) (obj)));
        if (mTempParentRect.equals(INVALID_PARENT_BOUNDS))
        {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int j = accessibilitynodeinfocompat1.mInfo.getActions();
        if ((j & 0x40) != 0)
        {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((j & 0x80) != 0)
        {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obj = mHost.getContext().getPackageName();
        accessibilitynodeinfocompat1.mInfo.setPackageName(((CharSequence) (obj)));
        obj = mHost;
        accessibilitynodeinfocompat1.mInfo.setSource(((View) (obj)), i);
        boolean flag;
        if (mAccessibilityFocusedVirtualViewId == i)
        {
            accessibilitynodeinfocompat1.mInfo.setAccessibilityFocused(true);
            accessibilitynodeinfocompat1.mInfo.addAction(128);
        } else
        {
            accessibilitynodeinfocompat1.mInfo.setAccessibilityFocused(false);
            accessibilitynodeinfocompat1.mInfo.addAction(64);
        }
        if (mKeyboardFocusedVirtualViewId == i)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            accessibilitynodeinfocompat1.mInfo.addAction(2);
        } else
        if (accessibilitynodeinfocompat1.mInfo.isFocusable())
        {
            accessibilitynodeinfocompat1.mInfo.addAction(1);
        }
        accessibilitynodeinfocompat1.mInfo.setFocused(flag);
        mHost.getLocationOnScreen(mTempGlobalRect);
        obj = mTempScreenRect;
        accessibilitynodeinfocompat1.mInfo.getBoundsInScreen(((Rect) (obj)));
        if (mTempScreenRect.equals(INVALID_PARENT_BOUNDS))
        {
            Rect rect = mTempScreenRect;
            accessibilitynodeinfocompat1.mInfo.getBoundsInParent(rect);
            if (accessibilitynodeinfocompat1.mParentVirtualDescendantId != -1)
            {
                AccessibilityNodeInfoCompat accessibilitynodeinfocompat = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
                for (i = accessibilitynodeinfocompat1.mParentVirtualDescendantId; i != -1; i = accessibilitynodeinfocompat.mParentVirtualDescendantId)
                {
                    Object obj2 = mHost;
                    accessibilitynodeinfocompat.mParentVirtualDescendantId = -1;
                    accessibilitynodeinfocompat.mInfo.setParent(((View) (obj2)), -1);
                    obj2 = INVALID_PARENT_BOUNDS;
                    accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj2)));
                    onPopulateNodeForVirtualView(i, accessibilitynodeinfocompat);
                    obj2 = mTempParentRect;
                    accessibilitynodeinfocompat.mInfo.getBoundsInParent(((Rect) (obj2)));
                    mTempScreenRect.offset(mTempParentRect.left, mTempParentRect.top);
                }

                accessibilitynodeinfocompat.mInfo.recycle();
            }
            mTempScreenRect.offset(mTempGlobalRect[0] - mHost.getScrollX(), mTempGlobalRect[1] - mHost.getScrollY());
        }
        if (!mHost.getLocalVisibleRect(mTempVisibleRect)) goto _L2; else goto _L1
_L1:
        mTempVisibleRect.offset(mTempGlobalRect[0] - mHost.getScrollX(), mTempGlobalRect[1] - mHost.getScrollY());
        if (!mTempScreenRect.intersect(mTempVisibleRect)) goto _L2; else goto _L3
_L3:
        Rect rect1;
        rect1 = mTempScreenRect;
        accessibilitynodeinfocompat1.mInfo.setBoundsInScreen(rect1);
        rect1 = mTempScreenRect;
        if (rect1 != null && !rect1.isEmpty()) goto _L5; else goto _L4
_L4:
        i = 0;
_L7:
        if (i != 0)
        {
            accessibilitynodeinfocompat1.mInfo.setVisibleToUser(true);
        }
_L2:
        return accessibilitynodeinfocompat1;
_L5:
        if (mHost.getWindowVisibility() == 0)
        {
            Object obj1 = mHost.getParent();
            do
            {
                if (!(obj1 instanceof View))
                {
                    break;
                }
                obj1 = (View)obj1;
                if (((View) (obj1)).getAlpha() <= 0.0F || ((View) (obj1)).getVisibility() != 0)
                {
                    i = 0;
                    continue; /* Loop/switch isn't completed */
                }
                obj1 = ((View) (obj1)).getParent();
            } while (true);
            if (obj1 != null)
            {
                i = 1;
                continue; /* Loop/switch isn't completed */
            }
        }
        i = 0;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static int keyToDirection(int i)
    {
        switch (i)
        {
        case 20: // '\024'
        default:
            return 130;

        case 21: // '\025'
            return 17;

        case 19: // '\023'
            return 33;

        case 22: // '\026'
            return 66;
        }
    }

    private final void updateHoveredVirtualView(int i)
    {
        if (mHoveredVirtualViewId == i)
        {
            return;
        } else
        {
            int j = mHoveredVirtualViewId;
            mHoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(j, 256);
            return;
        }
    }

    final boolean clearAccessibilityFocus(int i)
    {
        if (mAccessibilityFocusedVirtualViewId == i)
        {
            mAccessibilityFocusedVirtualViewId = 0x80000000;
            mHost.invalidate();
            sendEventForVirtualView(i, 0x10000);
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if (mManager.isEnabled() && mManager.isTouchExplorationEnabled()) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        switch (motionevent.getAction())
        {
        case 8: // '\b'
        default:
            return false;

        case 7: // '\007'
        case 9: // '\t'
            int i = getVirtualViewAt(motionevent.getX(), motionevent.getY());
            updateHoveredVirtualView(i);
            if (i == 0x80000000)
            {
                return false;
            }
            break;

        case 10: // '\n'
            if (mHoveredVirtualViewId != 0x80000000)
            {
                updateHoveredVirtualView(0x80000000);
                return true;
            } else
            {
                return false;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        if (mNodeProvider == null)
        {
            mNodeProvider = new MyNodeProvider();
        }
        return mNodeProvider;
    }

    public abstract int getVirtualViewAt(float f, float f1);

    public abstract void getVisibleVirtualViews(List list);

    public final void invalidateRoot()
    {
        if (mManager.isEnabled())
        {
            ViewParent viewparent = mHost.getParent();
            if (viewparent != null)
            {
                AccessibilityEvent accessibilityevent = createEvent(-1, 2048);
                accessibilityevent.setContentChangeTypes(1);
                viewparent.requestSendAccessibilityEvent(mHost, accessibilityevent);
            }
        }
    }

    public final boolean moveFocus(int i, Rect rect)
    {
        Object obj;
        SparseArrayCompat sparsearraycompat;
        obj = new ArrayList();
        getVisibleVirtualViews(((List) (obj)));
        sparsearraycompat = new SparseArrayCompat();
        for (int j = 0; j < ((List) (obj)).size(); j++)
        {
            sparsearraycompat.put(j, createNodeForChild(j));
        }

        int k = mKeyboardFocusedVirtualViewId;
        if (k == 0x80000000)
        {
            obj = null;
        } else
        {
            obj = (AccessibilityNodeInfoCompat)sparsearraycompat.get(k);
        }
        i;
        JVM INSTR lookupswitch 6: default 132
    //                   1: 157
    //                   2: 157
    //                   17: 397
    //                   33: 397
    //                   66: 397
    //                   130: 397;
           goto _L1 _L2 _L2 _L3 _L3 _L3 _L3
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        Object obj2;
        FocusStrategy.BoundsAdapter boundsadapter;
        int i2;
        boolean flag;
        if (ViewCompat.getLayoutDirection(mHost) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        rect = SPARSE_VALUES_ADAPTER;
        boundsadapter = NODE_ADAPTER;
        i2 = rect.size(sparsearraycompat);
        obj2 = new ArrayList(i2);
        for (int l = 0; l < i2; l++)
        {
            ((ArrayList) (obj2)).add(rect.get(sparsearraycompat, l));
        }

        Collections.sort(((List) (obj2)), new FocusStrategy.SequentialComparator(flag, boundsadapter));
        i;
        JVM INSTR tableswitch 1 2: default 280
    //                   1 354
    //                   2 291;
           goto _L4 _L5 _L6
_L4:
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
_L6:
        int i1 = ((ArrayList) (obj2)).size();
        if (obj == null)
        {
            i = -1;
        } else
        {
            i = ((ArrayList) (obj2)).lastIndexOf(obj);
        }
        i++;
        if (i < i1)
        {
            rect = ((Rect) (((ArrayList) (obj2)).get(i)));
        } else
        {
            rect = null;
        }
_L9:
        rect = (AccessibilityNodeInfoCompat)rect;
_L47:
        if (rect != null) goto _L8; else goto _L7
_L7:
        i = 0x80000000;
_L52:
        return requestKeyboardFocusForVirtualView(i);
_L5:
        i = ((ArrayList) (obj2)).size();
        if (obj != null)
        {
            i = ((ArrayList) (obj2)).indexOf(obj);
        }
        i--;
        if (i >= 0)
        {
            rect = ((Rect) (((ArrayList) (obj2)).get(i)));
        } else
        {
            rect = null;
        }
          goto _L9
_L3:
        Rect rect1 = new Rect();
        if (mKeyboardFocusedVirtualViewId == 0x80000000) goto _L11; else goto _L10
_L10:
        FocusStrategy.CollectionAdapter collectionadapter;
        FocusStrategy.BoundsAdapter boundsadapter1;
        Rect rect2;
        int j1 = mKeyboardFocusedVirtualViewId;
        if (j1 == -1)
        {
            rect = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(mHost));
            ViewCompat.onInitializeAccessibilityNodeInfo(mHost, rect);
            ArrayList arraylist = new ArrayList();
            getVisibleVirtualViews(arraylist);
            if (((AccessibilityNodeInfoCompat) (rect)).mInfo.getChildCount() > 0 && arraylist.size() > 0)
            {
                throw new RuntimeException("Views cannot have both real and virtual children");
            }
            int j2 = arraylist.size();
            for (j1 = 0; j1 < j2; j1++)
            {
                obj2 = mHost;
                int i3 = ((Integer)arraylist.get(j1)).intValue();
                ((AccessibilityNodeInfoCompat) (rect)).mInfo.addChild(((View) (obj2)), i3);
            }

        } else
        {
            rect = createNodeForChild(j1);
        }
        ((AccessibilityNodeInfoCompat) (rect)).mInfo.getBoundsInParent(rect1);
_L18:
        collectionadapter = SPARSE_VALUES_ADAPTER;
        boundsadapter1 = NODE_ADAPTER;
        rect2 = new Rect(rect1);
        i;
        JVM INSTR lookupswitch 4: default 624
    //                   17: 787
    //                   33: 938
    //                   66: 921
    //                   130: 954;
           goto _L12 _L13 _L14 _L15 _L16
_L12:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L11:
        if (rect == null)
        {
            break; /* Loop/switch isn't completed */
        }
        rect1.set(rect);
        if (true) goto _L18; else goto _L17
_L17:
        rect = mHost;
        int k1 = rect.getWidth();
        int k2 = rect.getHeight();
        switch (i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            rect1.set(k1, 0, k1, k2);
            break;

        case 33: // '!'
            rect1.set(0, k2, k1, k2);
            break;

        case 66: // 'B'
            rect1.set(-1, 0, -1, k2);
            break;

        case 130: 
            rect1.set(0, -1, k1, -1);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L13:
        rect2.offset(rect1.width() + 1, 0);
_L28:
        Rect rect3;
        int l2;
        int j3;
        j3 = collectionadapter.size(sparsearraycompat);
        rect3 = new Rect();
        l2 = 0;
        rect = null;
_L27:
        if (l2 >= j3) goto _L20; else goto _L19
_L19:
        Object obj1;
        obj2 = collectionadapter.get(sparsearraycompat, l2);
        obj1 = rect;
        if (obj2 == obj) goto _L22; else goto _L21
_L21:
        boundsadapter1.obtainBounds(obj2, rect3);
        if (!FocusStrategy.isCandidate(rect1, rect3, i)) goto _L24; else goto _L23
_L23:
        if (FocusStrategy.isCandidate(rect1, rect2, i)) goto _L26; else goto _L25
_L25:
        int l1 = 1;
_L31:
        obj1 = rect;
        if (l1 != 0)
        {
            rect2.set(rect3);
            obj1 = obj2;
        }
_L22:
        l2++;
        rect = ((Rect) (obj1));
          goto _L27
_L15:
        rect2.offset(-(rect1.width() + 1), 0);
          goto _L28
_L14:
        rect2.offset(0, rect1.height() + 1);
          goto _L28
_L16:
        rect2.offset(0, -(rect1.height() + 1));
          goto _L28
_L26:
        if (!FocusStrategy.beamBeats(i, rect1, rect3, rect2)) goto _L30; else goto _L29
_L29:
        l1 = 1;
          goto _L31
_L30:
        if (FocusStrategy.beamBeats(i, rect1, rect2, rect3)) goto _L24; else goto _L32
_L32:
        i;
        JVM INSTR lookupswitch 4: default 1048
    //                   17: 1059
    //                   33: 1159
    //                   66: 1143
    //                   130: 1175;
           goto _L33 _L34 _L35 _L36 _L37
_L33:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L34:
        l1 = rect1.left - rect3.right;
_L43:
        int k3;
        int l3;
        k3 = Math.max(0, l1);
        l3 = FocusStrategy.minorAxisDistance(i, rect1, rect3);
        i;
        JVM INSTR lookupswitch 4: default 1132
    //                   17: 1191
    //                   33: 1275
    //                   66: 1259
    //                   130: 1291;
           goto _L38 _L39 _L40 _L41 _L42
_L38:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L36:
        l1 = rect3.left - rect1.right;
          goto _L43
_L35:
        l1 = rect1.top - rect3.bottom;
          goto _L43
_L37:
        l1 = rect3.top - rect1.bottom;
          goto _L43
_L39:
        l1 = rect1.left - rect2.right;
_L45:
        int i4;
        l1 = Math.max(0, l1);
        i4 = FocusStrategy.minorAxisDistance(i, rect1, rect2);
        if (l3 * l3 + k3 * (k3 * 13) >= l1 * (l1 * 13) + i4 * i4) goto _L24; else goto _L44
_L44:
        l1 = 1;
          goto _L31
_L41:
        l1 = rect2.left - rect1.right;
          goto _L45
_L40:
        l1 = rect1.top - rect2.bottom;
          goto _L45
_L42:
        l1 = rect2.top - rect1.bottom;
          goto _L45
_L24:
        l1 = 0;
          goto _L31
        if (true) goto _L18; else goto _L46
_L46:
_L20:
        rect = (AccessibilityNodeInfoCompat)rect;
          goto _L47
_L8:
        if (sparsearraycompat.mGarbage)
        {
            sparsearraycompat.gc();
        }
        i = 0;
_L53:
        if (i >= sparsearraycompat.mSize) goto _L49; else goto _L48
_L48:
        if (sparsearraycompat.mValues[i] != rect) goto _L51; else goto _L50
_L50:
        if (sparsearraycompat.mGarbage)
        {
            sparsearraycompat.gc();
        }
        i = sparsearraycompat.mKeys[i];
          goto _L52
_L51:
        i++;
          goto _L53
_L49:
        i = -1;
          goto _L50
    }

    final AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i)
    {
        if (i == -1)
        {
            AccessibilityNodeInfoCompat accessibilitynodeinfocompat = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(mHost));
            ViewCompat.onInitializeAccessibilityNodeInfo(mHost, accessibilitynodeinfocompat);
            ArrayList arraylist = new ArrayList();
            getVisibleVirtualViews(arraylist);
            if (accessibilitynodeinfocompat.mInfo.getChildCount() > 0 && arraylist.size() > 0)
            {
                throw new RuntimeException("Views cannot have both real and virtual children");
            }
            int j = arraylist.size();
            for (i = 0; i < j; i++)
            {
                View view = mHost;
                int k = ((Integer)arraylist.get(i)).intValue();
                accessibilitynodeinfocompat.mInfo.addChild(view, k);
            }

            return accessibilitynodeinfocompat;
        } else
        {
            return createNodeForChild(i);
        }
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        onPopulateNodeForHost(accessibilitynodeinfocompat);
    }

    public abstract boolean onPerformActionForVirtualView(int i, int j, Bundle bundle);

    public void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
    {
    }

    public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
    }

    public abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat);

    public final boolean requestKeyboardFocusForVirtualView(int i)
    {
        while (!mHost.isFocused() && !mHost.requestFocus() || mKeyboardFocusedVirtualViewId == i) 
        {
            return false;
        }
        if (mKeyboardFocusedVirtualViewId != 0x80000000)
        {
            int j = mKeyboardFocusedVirtualViewId;
            if (mKeyboardFocusedVirtualViewId == j)
            {
                mKeyboardFocusedVirtualViewId = 0x80000000;
                sendEventForVirtualView(j, 8);
            }
        }
        mKeyboardFocusedVirtualViewId = i;
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i, int j)
    {
        ViewParent viewparent;
        if (i != 0x80000000 && mManager.isEnabled())
        {
            if ((viewparent = mHost.getParent()) != null)
            {
                AccessibilityEvent accessibilityevent = createEvent(i, j);
                return viewparent.requestSendAccessibilityEvent(mHost, accessibilityevent);
            }
        }
        return false;
    }


    private class _cls1
        implements FocusStrategy.BoundsAdapter
    {

        public final void obtainBounds(Object obj, Rect rect)
        {
            ((AccessibilityNodeInfoCompat)obj).mInfo.getBoundsInParent(rect);
        }

        _cls1()
        {
        }
    }


    private class _cls2
        implements FocusStrategy.CollectionAdapter
    {

        public final Object get(Object obj, int i)
        {
            obj = (SparseArrayCompat)obj;
            if (((SparseArrayCompat) (obj)).mGarbage)
            {
                ((SparseArrayCompat) (obj)).gc();
            }
            return (AccessibilityNodeInfoCompat)((SparseArrayCompat) (obj)).mValues[i];
        }

        public final int size(Object obj)
        {
            obj = (SparseArrayCompat)obj;
            if (((SparseArrayCompat) (obj)).mGarbage)
            {
                ((SparseArrayCompat) (obj)).gc();
            }
            return ((SparseArrayCompat) (obj)).mSize;
        }

        _cls2()
        {
        }
    }

}
