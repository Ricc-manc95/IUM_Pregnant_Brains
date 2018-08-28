// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl:
//            RecyclerViewAccessibilityDelegateHelper

public interface 
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
