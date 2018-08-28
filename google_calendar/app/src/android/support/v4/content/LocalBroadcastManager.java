// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager
{

    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    public final HashMap mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList mPendingBroadcasts = new ArrayList();
    public final HashMap mReceivers = new HashMap();

    private LocalBroadcastManager(Context context)
    {
        mAppContext = context;
        mHandler = new _cls1(context.getMainLooper());
    }

    public static LocalBroadcastManager getInstance(Context context)
    {
        synchronized (mLock)
        {
            if (mInstance == null)
            {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            context = mInstance;
        }
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    final void executePendingBroadcasts()
    {
_L4:
        Object obj = mReceivers;
        obj;
        JVM INSTR monitorenter ;
        int i = mPendingBroadcasts.size();
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_24;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        BroadcastRecord abroadcastrecord[];
        abroadcastrecord = new BroadcastRecord[i];
        mPendingBroadcasts.toArray(abroadcastrecord);
        mPendingBroadcasts.clear();
        obj;
        JVM INSTR monitorexit ;
        i = 0;
_L2:
        if (i < abroadcastrecord.length)
        {
            obj = abroadcastrecord[i];
            int k = ((BroadcastRecord) (obj)).receivers.size();
            for (int j = 0; j < k; j++)
            {
                ReceiverRecord receiverrecord = (ReceiverRecord)((BroadcastRecord) (obj)).receivers.get(j);
                if (!receiverrecord.dead)
                {
                    receiverrecord.receiver.onReceive(mAppContext, ((BroadcastRecord) (obj)).intent);
                }
            }

            break MISSING_BLOCK_LABEL_131;
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void registerReceiver(BroadcastReceiver broadcastreceiver, IntentFilter intentfilter)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj;
        ReceiverRecord receiverrecord;
        receiverrecord = new ReceiverRecord(intentfilter, broadcastreceiver);
        obj = (ArrayList)mReceivers.get(broadcastreceiver);
        ArrayList arraylist;
        arraylist = ((ArrayList) (obj));
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        arraylist = new ArrayList(1);
        mReceivers.put(broadcastreceiver, arraylist);
        arraylist.add(receiverrecord);
        int i = 0;
_L2:
        if (i >= intentfilter.countActions())
        {
            break; /* Loop/switch isn't completed */
        }
        obj = intentfilter.getAction(i);
        arraylist = (ArrayList)mActions.get(obj);
        broadcastreceiver = arraylist;
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        broadcastreceiver = new ArrayList(1);
        mActions.put(obj, broadcastreceiver);
        broadcastreceiver.add(receiverrecord);
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        broadcastreceiver;
        hashmap;
        JVM INSTR monitorexit ;
        throw broadcastreceiver;
    }

    public final boolean sendBroadcast(Intent intent)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        String s;
        String s1;
        android.net.Uri uri;
        String s2;
        java.util.Set set;
        s = intent.getAction();
        s1 = intent.resolveTypeIfNeeded(mAppContext.getContentResolver());
        uri = intent.getData();
        s2 = intent.getScheme();
        set = intent.getCategories();
        ArrayList arraylist;
        Object obj;
        int i;
        ArrayList arraylist1;
        int j;
        int k;
        if ((intent.getFlags() & 8) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_98;
        }
        (new StringBuilder("Resolving type ")).append(s1).append(" scheme ").append(s2).append(" of intent ").append(intent);
        arraylist1 = (ArrayList)mActions.get(intent.getAction());
        if (arraylist1 == null) goto _L2; else goto _L1
_L1:
        if (i == 0) goto _L4; else goto _L3
_L3:
        (new StringBuilder("Action list: ")).append(arraylist1);
          goto _L4
_L19:
        if (j >= arraylist1.size()) goto _L6; else goto _L5
_L5:
        obj = (ReceiverRecord)arraylist1.get(j);
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_185;
        }
        (new StringBuilder("Matching against filter ")).append(((ReceiverRecord) (obj)).filter);
        if (!((ReceiverRecord) (obj)).broadcasting) goto _L8; else goto _L7
_L7:
        if (i == 0);
          goto _L9
_L8:
        k = ((ReceiverRecord) (obj)).filter.match(s, s1, s2, uri, set, "LocalBroadcastManager");
        if (k < 0) goto _L11; else goto _L10
_L10:
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_249;
        }
        (new StringBuilder("  Filter matched!  match=0x")).append(Integer.toHexString(k));
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_261;
        }
        arraylist = new ArrayList();
        arraylist.add(obj);
        obj.broadcasting = true;
          goto _L9
        intent;
        hashmap;
        JVM INSTR monitorexit ;
        throw intent;
_L11:
        if (i == 0) goto _L9; else goto _L12
_L12:
        k;
        JVM INSTR tableswitch -4 -1: default 320
    //                   -4 453
    //                   -3 447
    //                   -2 459
    //                   -1 465;
           goto _L13 _L14 _L15 _L16 _L17
_L13:
        obj = "unknown reason";
_L20:
        (new StringBuilder("  Filter did not match: ")).append(((String) (obj)));
          goto _L9
_L18:
        if (i >= arraylist.size())
        {
            break MISSING_BLOCK_LABEL_371;
        }
        ((ReceiverRecord)arraylist.get(i)).broadcasting = false;
        i++;
          goto _L18
        mPendingBroadcasts.add(new BroadcastRecord(intent, arraylist));
        if (!mHandler.hasMessages(1))
        {
            mHandler.sendEmptyMessage(1);
        }
        hashmap;
        JVM INSTR monitorexit ;
        return true;
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return false;
_L4:
        arraylist = null;
        j = 0;
          goto _L19
_L9:
        j++;
          goto _L19
_L15:
        obj = "action";
          goto _L20
_L14:
        obj = "category";
          goto _L20
_L16:
        obj = "data";
          goto _L20
_L17:
        obj = "type";
          goto _L20
_L6:
        if (arraylist == null) goto _L2; else goto _L21
_L21:
        i = 0;
          goto _L18
    }

    public final void unregisterReceiver(BroadcastReceiver broadcastreceiver)
    {
        HashMap hashmap = mReceivers;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mReceivers.remove(broadcastreceiver);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_26;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return;
        int i = arraylist.size() - 1;
_L12:
        if (i < 0) goto _L2; else goto _L1
_L1:
        ReceiverRecord receiverrecord;
        receiverrecord = (ReceiverRecord)arraylist.get(i);
        receiverrecord.dead = true;
        int j = 0;
_L10:
        String s;
        ArrayList arraylist1;
        if (j >= receiverrecord.filter.countActions())
        {
            break; /* Loop/switch isn't completed */
        }
        s = receiverrecord.filter.getAction(j);
        arraylist1 = (ArrayList)mActions.get(s);
        if (arraylist1 == null) goto _L4; else goto _L3
_L3:
        int k = arraylist1.size() - 1;
_L8:
        if (k < 0) goto _L6; else goto _L5
_L5:
        ReceiverRecord receiverrecord1 = (ReceiverRecord)arraylist1.get(k);
        if (receiverrecord1.receiver == broadcastreceiver)
        {
            receiverrecord1.dead = true;
            arraylist1.remove(k);
        }
          goto _L7
_L6:
        if (arraylist1.size() <= 0)
        {
            mActions.remove(s);
        }
        break; /* Loop/switch isn't completed */
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        broadcastreceiver;
        hashmap;
        JVM INSTR monitorexit ;
        throw broadcastreceiver;
_L7:
        k--;
        if (true) goto _L8; else goto _L4
_L4:
        j++;
        if (true) goto _L10; else goto _L9
_L9:
        i--;
        if (true) goto _L12; else goto _L11
_L11:
    }


    private class _cls1 extends Handler
    {

        private final LocalBroadcastManager this$0;

        public final void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                super.handleMessage(message);
                return;

            case 1: // '\001'
                executePendingBroadcasts();
                break;
            }
        }

        _cls1(Looper looper)
        {
            this$0 = LocalBroadcastManager.this;
            super(looper);
        }
    }


    private class BroadcastRecord
    {

        public final Intent intent;
        public final ArrayList receivers;

        BroadcastRecord(Intent intent1, ArrayList arraylist)
        {
            intent = intent1;
            receivers = arraylist;
        }
    }


    private class ReceiverRecord
    {

        public boolean broadcasting;
        public boolean dead;
        public final IntentFilter filter;
        public final BroadcastReceiver receiver;

        public final String toString()
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            stringbuilder.append("Receiver{");
            stringbuilder.append(receiver);
            stringbuilder.append(" filter=");
            stringbuilder.append(filter);
            if (dead)
            {
                stringbuilder.append(" DEAD");
            }
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        ReceiverRecord(IntentFilter intentfilter, BroadcastReceiver broadcastreceiver)
        {
            filter = intentfilter;
            receiver = broadcastreceiver;
        }
    }

}
