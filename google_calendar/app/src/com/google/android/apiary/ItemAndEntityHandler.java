// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apiary;

import android.content.Entity;
import android.content.EntityIterator;
import android.content.SyncResult;
import android.os.RemoteException;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package com.google.android.apiary:
//            ParseException

public interface ItemAndEntityHandler
{

    public abstract String getEntitySelection();

    public abstract String itemToSourceId(Object obj);

    public abstract EntityIterator newEntityIterator(String s, String as[], int i)
        throws RemoteException, ParseException;

    public abstract ArrayList sendEntityToServer(Entity entity, SyncResult syncresult)
        throws ParseException, RemoteException, IOException;
}
