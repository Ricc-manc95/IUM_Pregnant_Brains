// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;

public interface NestedScrollingParent2
{

    public abstract void onNestedPreScroll(View view, int i, int j, int ai[], int k);

    public abstract void onNestedScroll(View view, int i, int j, int k, int l, int i1);

    public abstract void onNestedScrollAccepted(View view, View view1, int i, int j);

    public abstract boolean onStartNestedScroll(View view, View view1, int i, int j);

    public abstract void onStopNestedScroll(View view, int i);
}
