// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


final class payload
{

    public int cmd;
    public int itemCount;
    public Object payload;
    public int positionStart;

    public final boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        obj = (payload)obj;
        if (cmd != ((cmd) (obj)).cmd)
        {
            return false;
        }
        if (cmd == 8 && Math.abs(itemCount - positionStart) == 1 && itemCount == ((itemCount) (obj)).positionStart && positionStart == ((positionStart) (obj)).itemCount) goto _L1; else goto _L3
_L3:
        if (itemCount != ((itemCount) (obj)).itemCount)
        {
            return false;
        }
        if (positionStart != ((positionStart) (obj)).positionStart)
        {
            return false;
        }
        if (payload == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (payload.equals(((payload) (obj)).payload)) goto _L1; else goto _L4
_L4:
        return false;
        if (((payload) (obj)).payload == null) goto _L1; else goto _L5
_L5:
        return false;
    }

    public final int hashCode()
    {
        return (cmd * 31 + positionStart) * 31 + itemCount;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(Integer.toHexString(System.identityHashCode(this))).append("[");
        cmd;
        JVM INSTR tableswitch 1 8: default 72
    //                   1 125
    //                   2 131
    //                   3 72
    //                   4 137
    //                   5 72
    //                   6 72
    //                   7 72
    //                   8 143;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
        String s = "??";
_L7:
        return stringbuilder.append(s).append(",s:").append(positionStart).append("c:").append(itemCount).append(",p:").append(payload).append("]").toString();
_L2:
        s = "add";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "rm";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "up";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "mv";
        if (true) goto _L7; else goto _L6
_L6:
    }

    (int i, int j, int k, Object obj)
    {
        cmd = i;
        positionStart = j;
        itemCount = k;
        payload = obj;
    }
}
