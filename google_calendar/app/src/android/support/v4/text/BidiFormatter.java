// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            TextDirectionHeuristicsCompat, TextUtilsCompat, TextDirectionHeuristicCompat

public final class BidiFormatter
{

    public static final BidiFormatter DEFAULT_LTR_INSTANCE;
    public static final BidiFormatter DEFAULT_RTL_INSTANCE;
    public static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final String LRM_STRING = Character.toString('\u200E');
    private static final String RLM_STRING = Character.toString('\u200F');
    public final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    BidiFormatter(boolean flag, int i, TextDirectionHeuristicCompat textdirectionheuristiccompat)
    {
        mIsRtlContext = flag;
        mFlags = i;
        mDefaultTextDirectionHeuristicCompat = textdirectionheuristiccompat;
    }

    public static BidiFormatter getInstance()
    {
        return (new Builder()).build();
    }

    public static BidiFormatter getInstance(Locale locale)
    {
        return (new Builder(locale)).build();
    }

    static boolean isRtlLocale(Locale locale)
    {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    public final CharSequence unicodeWrap(CharSequence charsequence, TextDirectionHeuristicCompat textdirectionheuristiccompat, boolean flag)
    {
        SpannableStringBuilder spannablestringbuilder;
        int i;
        int j;
        byte byte0;
        int k;
        boolean flag1;
        boolean flag2;
        if (charsequence == null)
        {
            return null;
        }
        flag1 = textdirectionheuristiccompat.isRtl(charsequence, 0, charsequence.length());
        spannablestringbuilder = new SpannableStringBuilder();
        if ((mFlags & 2) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0 || !flag) goto _L2; else goto _L1
_L1:
        if (flag1)
        {
            textdirectionheuristiccompat = TextDirectionHeuristicsCompat.RTL;
        } else
        {
            textdirectionheuristiccompat = TextDirectionHeuristicsCompat.LTR;
        }
        flag2 = textdirectionheuristiccompat.isRtl(charsequence, 0, charsequence.length());
        if (mIsRtlContext) goto _L4; else goto _L3
_L3:
        if (flag2) goto _L6; else goto _L5
_L5:
        textdirectionheuristiccompat = new DirectionalityEstimator(charsequence, false);
        textdirectionheuristiccompat.charIndex = 0;
        k = 0;
        i = 0;
        byte0 = 0;
_L14:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex >= ((DirectionalityEstimator) (textdirectionheuristiccompat)).length || k != 0) goto _L8; else goto _L7
_L7:
        textdirectionheuristiccompat.lastChar = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text.charAt(((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex);
        if (!Character.isHighSurrogate(((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar)) goto _L10; else goto _L9
_L9:
        j = Character.codePointAt(((DirectionalityEstimator) (textdirectionheuristiccompat)).text, ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex);
        textdirectionheuristiccompat.charIndex = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex + Character.charCount(j);
        j = Character.getDirectionality(j);
_L19:
        j;
        JVM INSTR tableswitch 0 18: default 284
    //                   0 621
    //                   1 871
    //                   2 871
    //                   3 284
    //                   4 284
    //                   5 284
    //                   6 284
    //                   7 284
    //                   8 284
    //                   9 113
    //                   10 284
    //                   11 284
    //                   12 284
    //                   13 284
    //                   14 585
    //                   15 585
    //                   16 597
    //                   17 597
    //                   18 609;
           goto _L11 _L12 _L13 _L13 _L11 _L11 _L11 _L11 _L11 _L11 _L14 _L11 _L11 _L11 _L11 _L15 _L15 _L16 _L16 _L17
_L11:
        k = i;
          goto _L14
_L10:
        int l;
        textdirectionheuristiccompat.charIndex = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex + 1;
        l = DirectionalityEstimator.getCachedDirectionality(((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar);
        j = l;
        if (!((DirectionalityEstimator) (textdirectionheuristiccompat)).isHtml) goto _L19; else goto _L18
_L18:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '<') goto _L21; else goto _L20
_L20:
        j = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
_L24:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex < ((DirectionalityEstimator) (textdirectionheuristiccompat)).length)
        {
            CharSequence charsequence1 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text;
            l = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
            textdirectionheuristiccompat.charIndex = l + 1;
            textdirectionheuristiccompat.lastChar = charsequence1.charAt(l);
            if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '>')
            {
                continue; /* Loop/switch isn't completed */
            }
            j = 12;
        } else
        {
            textdirectionheuristiccompat.charIndex = j;
            textdirectionheuristiccompat.lastChar = '<';
            j = 13;
        }
          goto _L19
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '"' && ((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '\'') goto _L23; else goto _L22
_L23:
        break;
_L22:
        l = ((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar;
        char c;
        do
        {
            if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex >= ((DirectionalityEstimator) (textdirectionheuristiccompat)).length)
            {
                break;
            }
            CharSequence charsequence2 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text;
            int j1 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
            textdirectionheuristiccompat.charIndex = j1 + 1;
            c = charsequence2.charAt(j1);
            textdirectionheuristiccompat.lastChar = c;
        } while (c != l);
          goto _L24
_L21:
        j = l;
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar == '&')
        {
            char c1;
            do
            {
                if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex >= ((DirectionalityEstimator) (textdirectionheuristiccompat)).length)
                {
                    break;
                }
                CharSequence charsequence3 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text;
                j = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
                textdirectionheuristiccompat.charIndex = j + 1;
                c1 = charsequence3.charAt(j);
                textdirectionheuristiccompat.lastChar = c1;
            } while (c1 != ';');
            j = 12;
        }
          goto _L19
_L15:
        i++;
        byte0 = -1;
          goto _L14
_L16:
        i++;
        byte0 = 1;
          goto _L14
_L17:
        i--;
        byte0 = 0;
          goto _L14
_L12:
        if (i != 0) goto _L26; else goto _L25
_L25:
        j = -1;
_L44:
        if (j != 1) goto _L4; else goto _L6
_L6:
        textdirectionheuristiccompat = LRM_STRING;
_L80:
        spannablestringbuilder.append(textdirectionheuristiccompat);
_L2:
        if (flag1 != mIsRtlContext)
        {
            char c2;
            CharSequence charsequence4;
            int i1;
            int k1;
            if (flag1)
            {
                c2 = '\u202B';
            } else
            {
                c2 = '\u202A';
            }
            spannablestringbuilder.append(c2);
            spannablestringbuilder.append(charsequence);
            spannablestringbuilder.append('\u202C');
        } else
        {
            spannablestringbuilder.append(charsequence);
        }
        if (!flag) goto _L28; else goto _L27
_L27:
        if (flag1)
        {
            textdirectionheuristiccompat = TextDirectionHeuristicsCompat.RTL;
        } else
        {
            textdirectionheuristiccompat = TextDirectionHeuristicsCompat.LTR;
        }
        flag = textdirectionheuristiccompat.isRtl(charsequence, 0, charsequence.length());
        if (mIsRtlContext) goto _L30; else goto _L29
_L29:
        if (flag) goto _L32; else goto _L31
_L31:
        textdirectionheuristiccompat = new DirectionalityEstimator(charsequence, false);
        textdirectionheuristiccompat.charIndex = ((DirectionalityEstimator) (textdirectionheuristiccompat)).length;
        i = 0;
        j = 0;
_L38:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex <= 0) goto _L34; else goto _L33
_L33:
        textdirectionheuristiccompat.dirTypeBackward();
        JVM INSTR tableswitch 0 18: default 852
    //                   0 1737
    //                   1 1799
    //                   2 1799
    //                   3 852
    //                   4 852
    //                   5 852
    //                   6 852
    //                   7 852
    //                   8 852
    //                   9 752
    //                   10 852
    //                   11 852
    //                   12 852
    //                   13 852
    //                   14 1777
    //                   15 1777
    //                   16 1822
    //                   17 1822
    //                   18 1844;
           goto _L35 _L36 _L37 _L37 _L35 _L35 _L35 _L35 _L35 _L35 _L38 _L35 _L35 _L35 _L35 _L39 _L39 _L40 _L40 _L41
_L35:
        if (j == 0)
        {
            j = i;
        }
          goto _L38
_L26:
        k = i;
          goto _L14
_L13:
        if (i != 0) goto _L43; else goto _L42
_L42:
        j = 1;
          goto _L44
_L43:
        k = i;
          goto _L14
_L8:
        if (k == 0) goto _L46; else goto _L45
_L45:
        j = byte0;
        if (byte0 != 0) goto _L44; else goto _L47
_L47:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex <= 0) goto _L46; else goto _L48
_L48:
        textdirectionheuristiccompat.dirTypeBackward();
        JVM INSTR tableswitch 14 18: default 948
    //                   14 951
    //                   15 951
    //                   16 973
    //                   17 973
    //                   18 995;
           goto _L47 _L49 _L49 _L50 _L50 _L51
_L49:
        if (k != i) goto _L53; else goto _L52
_L52:
        j = -1;
          goto _L44
_L53:
        i--;
          goto _L47
_L50:
        if (k != i) goto _L55; else goto _L54
_L54:
        j = 1;
          goto _L44
_L55:
        i--;
          goto _L47
_L51:
        i++;
          goto _L47
_L46:
        j = 0;
          goto _L44
_L4:
        if (!mIsRtlContext) goto _L57; else goto _L56
_L56:
        if (!flag2) goto _L59; else goto _L58
_L58:
        textdirectionheuristiccompat = new DirectionalityEstimator(charsequence, false);
        textdirectionheuristiccompat.charIndex = 0;
        k = 0;
        i = 0;
        byte0 = 0;
_L67:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex >= ((DirectionalityEstimator) (textdirectionheuristiccompat)).length || k != 0) goto _L61; else goto _L60
_L60:
        textdirectionheuristiccompat.lastChar = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text.charAt(((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex);
        if (!Character.isHighSurrogate(((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar)) goto _L63; else goto _L62
_L62:
        j = Character.codePointAt(((DirectionalityEstimator) (textdirectionheuristiccompat)).text, ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex);
        textdirectionheuristiccompat.charIndex = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex + Character.charCount(j);
        j = Character.getDirectionality(j);
_L72:
        j;
        JVM INSTR tableswitch 0 18: default 1216
    //                   0 1540
    //                   1 1568
    //                   2 1568
    //                   3 1216
    //                   4 1216
    //                   5 1216
    //                   6 1216
    //                   7 1216
    //                   8 1216
    //                   9 1046
    //                   10 1216
    //                   11 1216
    //                   12 1216
    //                   13 1216
    //                   14 1504
    //                   15 1504
    //                   16 1516
    //                   17 1516
    //                   18 1528;
           goto _L64 _L65 _L66 _L66 _L64 _L64 _L64 _L64 _L64 _L64 _L67 _L64 _L64 _L64 _L64 _L68 _L68 _L69 _L69 _L70
_L64:
        k = i;
          goto _L67
_L63:
        textdirectionheuristiccompat.charIndex = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex + 1;
        i1 = DirectionalityEstimator.getCachedDirectionality(((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar);
        j = i1;
        if (!((DirectionalityEstimator) (textdirectionheuristiccompat)).isHtml) goto _L72; else goto _L71
_L71:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '<') goto _L74; else goto _L73
_L73:
        j = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
_L77:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex < ((DirectionalityEstimator) (textdirectionheuristiccompat)).length)
        {
            charsequence4 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text;
            i1 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
            textdirectionheuristiccompat.charIndex = i1 + 1;
            textdirectionheuristiccompat.lastChar = charsequence4.charAt(i1);
            if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '>')
            {
                continue; /* Loop/switch isn't completed */
            }
            j = 12;
        } else
        {
            textdirectionheuristiccompat.charIndex = j;
            textdirectionheuristiccompat.lastChar = '<';
            j = 13;
        }
          goto _L72
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '"' && ((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar != '\'') goto _L76; else goto _L75
_L76:
        break;
_L75:
        i1 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar;
        do
        {
            if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex >= ((DirectionalityEstimator) (textdirectionheuristiccompat)).length)
            {
                break;
            }
            charsequence4 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text;
            k1 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
            textdirectionheuristiccompat.charIndex = k1 + 1;
            c2 = charsequence4.charAt(k1);
            textdirectionheuristiccompat.lastChar = c2;
        } while (c2 != i1);
          goto _L77
_L74:
        j = i1;
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).lastChar == '&')
        {
            do
            {
                if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex >= ((DirectionalityEstimator) (textdirectionheuristiccompat)).length)
                {
                    break;
                }
                charsequence4 = ((DirectionalityEstimator) (textdirectionheuristiccompat)).text;
                j = ((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex;
                textdirectionheuristiccompat.charIndex = j + 1;
                c2 = charsequence4.charAt(j);
                textdirectionheuristiccompat.lastChar = c2;
            } while (c2 != ';');
            j = 12;
        }
          goto _L72
_L68:
        i++;
        byte0 = -1;
          goto _L67
_L69:
        i++;
        byte0 = 1;
          goto _L67
_L70:
        i--;
        byte0 = 0;
          goto _L67
_L65:
        if (i != 0) goto _L79; else goto _L78
_L78:
        j = -1;
_L83:
        if (j != -1) goto _L57; else goto _L59
_L59:
        textdirectionheuristiccompat = RLM_STRING;
          goto _L80
_L79:
        k = i;
          goto _L67
_L66:
        if (i != 0) goto _L82; else goto _L81
_L81:
        j = 1;
          goto _L83
_L82:
        k = i;
          goto _L67
_L61:
        if (k == 0) goto _L85; else goto _L84
_L84:
        j = byte0;
        if (byte0 != 0) goto _L83; else goto _L86
_L86:
        if (((DirectionalityEstimator) (textdirectionheuristiccompat)).charIndex <= 0) goto _L85; else goto _L87
_L87:
        textdirectionheuristiccompat.dirTypeBackward();
        JVM INSTR tableswitch 14 18: default 1644
    //                   14 1647
    //                   15 1647
    //                   16 1669
    //                   17 1669
    //                   18 1691;
           goto _L86 _L88 _L88 _L89 _L89 _L90
_L88:
        if (k != i) goto _L92; else goto _L91
_L91:
        j = -1;
          goto _L83
_L92:
        i--;
          goto _L86
_L89:
        if (k != i) goto _L94; else goto _L93
_L93:
        j = 1;
          goto _L83
_L94:
        i--;
          goto _L86
_L90:
        i++;
          goto _L86
_L85:
        j = 0;
          goto _L83
_L57:
        textdirectionheuristiccompat = "";
          goto _L80
_L36:
        if (i != 0) goto _L96; else goto _L95
_L95:
        i = -1;
_L99:
        if (i != 1) goto _L30; else goto _L32
_L32:
        charsequence = LRM_STRING;
_L119:
        spannablestringbuilder.append(charsequence);
_L28:
        return spannablestringbuilder;
_L96:
        if (j == 0)
        {
            j = i;
        }
          goto _L38
_L39:
        if (j != i) goto _L98; else goto _L97
_L97:
        i = -1;
          goto _L99
_L98:
        i--;
          goto _L38
_L37:
        if (i != 0) goto _L101; else goto _L100
_L100:
        i = 1;
          goto _L99
_L101:
        if (j == 0)
        {
            j = i;
        }
          goto _L38
_L40:
        if (j != i) goto _L103; else goto _L102
_L102:
        i = 1;
          goto _L99
_L103:
        i--;
          goto _L38
_L41:
        i++;
          goto _L38
_L34:
        i = 0;
          goto _L99
_L30:
        if (!mIsRtlContext) goto _L105; else goto _L104
_L104:
        if (!flag) goto _L107; else goto _L106
_L106:
        charsequence = new DirectionalityEstimator(charsequence, false);
        charsequence.charIndex = ((DirectionalityEstimator) (charsequence)).length;
        i = 0;
        j = 0;
_L113:
        if (((DirectionalityEstimator) (charsequence)).charIndex <= 0) goto _L109; else goto _L108
_L108:
        charsequence.dirTypeBackward();
        JVM INSTR tableswitch 0 18: default 1996
    //                   0 2008
    //                   1 2063
    //                   2 2063
    //                   3 1996
    //                   4 1996
    //                   5 1996
    //                   6 1996
    //                   7 1996
    //                   8 1996
    //                   9 1894
    //                   10 1996
    //                   11 1996
    //                   12 1996
    //                   13 1996
    //                   14 2041
    //                   15 2041
    //                   16 2086
    //                   17 2086
    //                   18 2108;
           goto _L110 _L111 _L112 _L112 _L110 _L110 _L110 _L110 _L110 _L110 _L113 _L110 _L110 _L110 _L110 _L114 _L114 _L115 _L115 _L116
_L110:
        if (j == 0)
        {
            j = i;
        }
          goto _L113
_L111:
        if (i != 0) goto _L118; else goto _L117
_L117:
        i = -1;
_L122:
        if (i != -1) goto _L105; else goto _L107
_L107:
        charsequence = RLM_STRING;
          goto _L119
_L118:
        if (j == 0)
        {
            j = i;
        }
          goto _L113
_L114:
        if (j != i) goto _L121; else goto _L120
_L120:
        i = -1;
          goto _L122
_L121:
        i--;
          goto _L113
_L112:
        if (i != 0) goto _L124; else goto _L123
_L123:
        i = 1;
          goto _L122
_L124:
        if (j == 0)
        {
            j = i;
        }
          goto _L113
_L115:
        if (j != i) goto _L126; else goto _L125
_L125:
        i = 1;
          goto _L122
_L126:
        i--;
          goto _L113
_L116:
        i++;
          goto _L113
_L109:
        i = 0;
          goto _L122
_L105:
        charsequence = "";
          goto _L119
    }

    public final String unicodeWrap(String s)
    {
        TextDirectionHeuristicCompat textdirectionheuristiccompat = mDefaultTextDirectionHeuristicCompat;
        if (s == null)
        {
            return null;
        } else
        {
            return unicodeWrap(((CharSequence) (s)), textdirectionheuristiccompat, true).toString();
        }
    }

    public final String unicodeWrap(String s, TextDirectionHeuristicCompat textdirectionheuristiccompat)
    {
        if (s == null)
        {
            return null;
        } else
        {
            return unicodeWrap(((CharSequence) (s)), textdirectionheuristiccompat, true).toString();
        }
    }

    static 
    {
        DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }

    private class Builder
    {

        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public final BidiFormatter build()
        {
            if (mFlags == 2 && mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC)
            {
                if (mIsRtlContext)
                {
                    return BidiFormatter.DEFAULT_RTL_INSTANCE;
                } else
                {
                    return BidiFormatter.DEFAULT_LTR_INSTANCE;
                }
            } else
            {
                return new BidiFormatter(mIsRtlContext, mFlags, mTextDirectionHeuristicCompat);
            }
        }

        public Builder()
        {
            mIsRtlContext = BidiFormatter.isRtlLocale(Locale.getDefault());
            mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            mFlags = 2;
        }

        public Builder(Locale locale)
        {
            mIsRtlContext = BidiFormatter.isRtlLocale(locale);
            mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            mFlags = 2;
        }
    }


    private class DirectionalityEstimator
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

        DirectionalityEstimator(CharSequence charsequence, boolean flag)
        {
            text = charsequence;
            length = charsequence.length();
        }
    }

}
