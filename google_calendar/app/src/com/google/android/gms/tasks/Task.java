// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

// Referenced classes of package com.google.android.gms.tasks:
//            OnFailureListener, OnSuccessListener

public abstract class Task
{

    public Task()
    {
    }

    public abstract Task addOnFailureListener(Executor executor, OnFailureListener onfailurelistener);

    public abstract Task addOnSuccessListener(Executor executor, OnSuccessListener onsuccesslistener);

    public abstract Exception getException();

    public abstract Object getResult();

    public abstract boolean isSuccessful();
}
