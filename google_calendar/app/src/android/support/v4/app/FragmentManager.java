// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            FragmentTransaction, Fragment

public abstract class FragmentManager
{

    public FragmentManager()
    {
    }

    public abstract void addOnBackStackChangedListener(OnBackStackChangedListener onbackstackchangedlistener);

    public abstract FragmentTransaction beginTransaction();

    public abstract void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[]);

    public abstract boolean executePendingTransactions();

    public abstract Fragment findFragmentById(int i);

    public abstract Fragment findFragmentByTag(String s);

    public abstract BackStackEntry getBackStackEntryAt(int i);

    public abstract int getBackStackEntryCount();

    public abstract List getFragments();

    public abstract boolean isDestroyed();

    public abstract boolean isStateSaved();

    public abstract void popBackStack();

    public abstract void popBackStack(int i, int j);

    public abstract boolean popBackStackImmediate();

    public abstract boolean popBackStackImmediate(String s, int i);

    public abstract void removeOnBackStackChangedListener(OnBackStackChangedListener onbackstackchangedlistener);
}
