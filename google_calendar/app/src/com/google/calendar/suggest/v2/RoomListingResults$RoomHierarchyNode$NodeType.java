// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.suggest.v2;


// Referenced classes of package com.google.calendar.suggest.v2:
//            RoomListingResults

public static final class value extends Enum
    implements com.google.protobuf.e.NodeType
{

    private static final _cls2 $VALUES[];
    public static final _cls2 NAMED_NODE;
    public static final _cls2 UNNAMED_OTHER_NODE;
    public static final _cls2 UNRECOGNIZED;
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return NAMED_NODE;

        case 1: // '\001'
            return UNNAMED_OTHER_NODE;
        }
    }

    public static UNNAMED_OTHER_NODE[] values()
    {
        return (UNNAMED_OTHER_NODE[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        NAMED_NODE = new <init>("NAMED_NODE", 0, 0);
        UNNAMED_OTHER_NODE = new <init>("UNNAMED_OTHER_NODE", 1, 1);
        UNRECOGNIZED = new <init>("UNRECOGNIZED", 2, -1);
        $VALUES = (new .VALUES[] {
            NAMED_NODE, UNNAMED_OTHER_NODE, UNRECOGNIZED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return RoomListingResults.RoomHierarchyNode.NodeType.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

        new _cls2();
    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
