// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.text;


final class length
{

    private static final byte DIR_TYPE_CACHE[];
    public int charIndex;
    public final boolean isHtml = false;
    public char lastChar;
    public final int length;
    public final CharSequence text;

    static byte getCachedDirectionality(char c)
    {
        if (c < '\u0700')
        {
            return DIR_TYPE_CACHE[c];
        } else
        {
            return Character.getDirectionality(c);
        }
    }

    final byte dirTypeBackward()
    {
        byte byte2;
        byte2 = 12;
        lastChar = text.charAt(charIndex - 1);
        if (!Character.isLowSurrogate(lastChar)) goto _L2; else goto _L1
_L1:
        byte byte1;
        int i = Character.codePointBefore(text, charIndex);
        charIndex = charIndex - Character.charCount(i);
        byte1 = Character.getDirectionality(i);
_L4:
        return byte1;
_L2:
        byte byte0;
        charIndex = charIndex - 1;
        char c = lastChar;
        CharSequence charsequence;
        int l;
        if (c < '\u0700')
        {
            byte0 = DIR_TYPE_CACHE[c];
        } else
        {
            byte0 = Character.getDirectionality(c);
        }
        byte1 = byte0;
        if (!isHtml) goto _L4; else goto _L3
_L3:
        if (lastChar == '>')
        {
            int j = charIndex;
            do
            {
                if (charIndex <= 0)
                {
                    break;
                }
                charsequence = text;
                l = charIndex - 1;
                charIndex = l;
                lastChar = charsequence.charAt(l);
                if (lastChar == '<')
                {
                    return 12;
                }
                if (lastChar == '>')
                {
                    break;
                }
                if (lastChar != '"' && lastChar != '\'')
                {
                    break;
                }
                char c2 = lastChar;
                char c1;
                do
                {
                    if (charIndex <= 0)
                    {
                        break;
                    }
                    CharSequence charsequence1 = text;
                    int j1 = charIndex - 1;
                    charIndex = j1;
                    c1 = charsequence1.charAt(j1);
                    lastChar = c1;
                } while (c1 != c2);
            } while (true);
            charIndex = j;
            lastChar = '>';
            return 13;
        }
        byte1 = byte0;
        if (lastChar != ';') goto _L4; else goto _L5
_L5:
        int k = charIndex;
_L7:
        if (charIndex <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        CharSequence charsequence2 = text;
        int i1 = charIndex - 1;
        charIndex = i1;
        lastChar = charsequence2.charAt(i1);
        if (lastChar != '&')
        {
            continue; /* Loop/switch isn't completed */
        }
        byte0 = byte2;
_L8:
        return byte0;
        if (lastChar != ';') goto _L7; else goto _L6
_L6:
        charIndex = k;
        lastChar = ';';
        byte0 = 13;
          goto _L8
        if (true) goto _L7; else goto _L9
_L9:
    }

    static 
    {
        DIR_TYPE_CACHE = new byte[1792];
        for (int i = 0; i < 1792; i++)
        {
            DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
        }

    }

    (CharSequence charsequence, boolean flag)
    {
        text = charsequence;
        length = charsequence.length();
    }
}
