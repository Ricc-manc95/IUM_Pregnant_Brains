// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.graphics.Rect;

final class FocusStrategy
{

    static boolean beamBeats(int i, Rect rect, Rect rect1, Rect rect2)
    {
        boolean flag1 = beamsOverlap(i, rect, rect1);
        if (beamsOverlap(i, rect, rect2) || !flag1)
        {
            return false;
        }
        i;
        JVM INSTR lookupswitch 4: default 68
    //                   17: 78
    //                   33: 128
    //                   66: 105
    //                   130: 151;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        boolean flag;
        if (rect.left >= rect2.right)
        {
            flag = true;
        } else
        {
            flag = false;
        }
_L7:
        if (!flag)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (rect.right <= rect2.left)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (rect.top >= rect2.bottom)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (rect.bottom <= rect2.top)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (true) goto _L7; else goto _L6
_L6:
        if (i == 17 || i == 66)
        {
            return true;
        }
        i;
        JVM INSTR lookupswitch 4: default 232
    //                   17: 242
    //                   33: 328
    //                   66: 314
    //                   130: 342;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L9:
        int j = rect.left - rect1.right;
_L18:
        j = Math.max(0, j);
        i;
        JVM INSTR lookupswitch 4: default 304
    //                   17: 356
    //                   33: 391
    //                   66: 378
    //                   130: 404;
           goto _L13 _L14 _L15 _L16 _L17
_L13:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L11:
        j = rect1.left - rect.right;
          goto _L18
_L10:
        j = rect.top - rect1.bottom;
          goto _L18
_L12:
        j = rect1.top - rect.bottom;
          goto _L18
_L14:
        i = rect.left - rect2.left;
_L19:
        return j < Math.max(1, i);
_L16:
        i = rect2.right - rect.right;
          goto _L19
_L15:
        i = rect.top - rect2.top;
          goto _L19
_L17:
        i = rect2.bottom - rect.bottom;
          goto _L19
    }

    private static boolean beamsOverlap(int i, Rect rect, Rect rect1)
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   17: 54
    //                   33: 80
    //                   66: 54
    //                   130: 80;
           goto _L1 _L2 _L3 _L2 _L3
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        if (rect1.bottom < rect.top || rect1.top > rect.bottom) goto _L5; else goto _L4
_L4:
        return true;
_L5:
        return false;
_L3:
        if (rect1.right < rect.left || rect1.left > rect.right)
        {
            return false;
        }
        if (true) goto _L4; else goto _L6
_L6:
    }

    static boolean isCandidate(Rect rect, Rect rect1, int i)
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   17: 54
    //                   33: 126
    //                   66: 91
    //                   130: 161;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        if (rect.right <= rect1.right && rect.left < rect1.right || rect.left <= rect1.left) goto _L7; else goto _L6
_L6:
        return true;
_L7:
        return false;
_L4:
        if (rect.left >= rect1.left && rect.right > rect1.left || rect.right >= rect1.right)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (rect.bottom <= rect1.bottom && rect.top < rect1.bottom || rect.top <= rect1.top)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (rect.top >= rect1.top && rect.bottom > rect1.top || rect.bottom >= rect1.bottom)
        {
            return false;
        }
        if (true) goto _L6; else goto _L8
_L8:
    }

    static int minorAxisDistance(int i, Rect rect, Rect rect1)
    {
        switch (i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
        case 66: // 'B'
            return Math.abs((rect.top + rect.height() / 2) - (rect1.top + rect1.height() / 2));

        case 33: // '!'
        case 130: 
            return Math.abs((rect.left + rect.width() / 2) - (rect1.left + rect1.width() / 2));
        }
    }
}
