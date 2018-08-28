// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;

import java.util.ArrayList;

// Referenced classes of package io.grpc:
//            Context

final class this._cls0
    implements tener
{

    private final Context this$0;

    public final void cancelled(Context context)
    {
        boolean flag = false;
        if (!(Context.this instanceof ext)) goto _L2; else goto _L1
_L1:
        context.cancellationCause();
        ext.cancel._mth5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTIIMG_0();
_L4:
        return;
_L2:
        context = Context.this;
        if (!context.canBeCancelled())
        {
            continue; /* Loop/switch isn't completed */
        }
        context;
        JVM INSTR monitorenter ;
        if (context.listeners != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        context;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        context;
        JVM INSTR monitorexit ;
        throw exception;
        ArrayList arraylist;
        arraylist = context.listeners;
        context.listeners = null;
        context;
        JVM INSTR monitorexit ;
        int i = 0;
        int j;
        do
        {
            j = ((flag) ? 1 : 0);
            if (i >= arraylist.size())
            {
                break;
            }
            if (!(((ner)arraylist.get(i)).listener instanceof ner.listener))
            {
                ((ner)arraylist.get(i)).deliver();
            }
            i++;
        } while (true);
        for (; j < arraylist.size(); j++)
        {
            if (((ner)arraylist.get(j)).listener instanceof ner.listener)
            {
                ((ner)arraylist.get(j)).deliver();
            }
        }

        if (context.cancellableAncestor != null)
        {
            context.cancellableAncestor.removeListener(context.parentListener);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ner()
    {
        this$0 = Context.this;
        super();
    }
}
