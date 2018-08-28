// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.data;


// Referenced classes of package com.google.android.calendar.timely.rooms.data:
//            RoomHierarchyNode

abstract class $AutoValue_RoomHierarchyNode extends RoomHierarchyNode
{

    private final String id;
    private final String name;
    private final int type;

    $AutoValue_RoomHierarchyNode(String s, int i, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null id");
        } else
        {
            id = s;
            type = i;
            name = s1;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof RoomHierarchyNode))
        {
            break MISSING_BLOCK_LABEL_76;
        }
        obj = (RoomHierarchyNode)obj;
        if (!id.equals(((RoomHierarchyNode) (obj)).getId()) || type != ((RoomHierarchyNode) (obj)).getType())
        {
            break; /* Loop/switch isn't completed */
        }
        if (name != null) goto _L4; else goto _L3
_L3:
        if (((RoomHierarchyNode) (obj)).getName() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!name.equals(((RoomHierarchyNode) (obj)).getName())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final String getId()
    {
        return id;
    }

    public final String getName()
    {
        return name;
    }

    public final int getType()
    {
        return type;
    }

    public int hashCode()
    {
        int j = id.hashCode();
        int k = type;
        int i;
        if (name == null)
        {
            i = 0;
        } else
        {
            i = name.hashCode();
        }
        return i ^ ((j ^ 0xf4243) * 0xf4243 ^ k) * 0xf4243;
    }

    public String toString()
    {
        String s = id;
        int i = type;
        String s1 = name;
        return (new StringBuilder(String.valueOf(s).length() + 47 + String.valueOf(s1).length())).append("RoomHierarchyNode{id=").append(s).append(", type=").append(i).append(", name=").append(s1).append("}").toString();
    }
}
