// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.provider;

import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.provider:
//            FontsContractCompat

final class val.id
    implements ReplyCallback
{

    private final String val$id;

    public final void onReply(Object obj)
    {
        pefaceResult pefaceresult = (pefaceResult)obj;
        obj = FontsContractCompat.sLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)FontsContractCompat.sPendingReplies.get(val$id);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        FontsContractCompat.sPendingReplies.remove(val$id);
        obj;
        JVM INSTR monitorexit ;
        for (int i = 0; i < arraylist.size(); i++)
        {
            ((ReplyCallback)arraylist.get(i)).onReply(pefaceresult);
        }

        break MISSING_BLOCK_LABEL_86;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    pefaceResult()
    {
        val$id = s;
        super();
    }
}
