// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

// Referenced classes of package io.grpc.okhttp.internal:
//            DistinguishedNameParser

public final class OkHostnameVerifier
    implements HostnameVerifier
{

    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private OkHostnameVerifier()
    {
    }

    private static List getSubjectAltNames(X509Certificate x509certificate, int i)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        Object obj;
        Integer integer;
        try
        {
            x509certificate = x509certificate.getSubjectAlternativeNames();
        }
        // Misplaced declaration of an exception variable
        catch (X509Certificate x509certificate)
        {
            return Collections.emptyList();
        }
        if (x509certificate != null)
        {
            break MISSING_BLOCK_LABEL_21;
        }
        return Collections.emptyList();
        x509certificate = x509certificate.iterator();
_L2:
        do
        {
            if (!x509certificate.hasNext())
            {
                break MISSING_BLOCK_LABEL_118;
            }
            obj = (List)x509certificate.next();
        } while (obj == null);
        if (((List) (obj)).size() < 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        integer = (Integer)((List) (obj)).get(0);
        if (integer == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (integer.intValue() != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = (String)((List) (obj)).get(1);
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        arraylist.add(obj);
        if (true) goto _L2; else goto _L1
_L1:
        return arraylist;
    }

    private static boolean verifyHostName(String s, String s1)
    {
        if (s != null && s.length() != 0 && !s.startsWith(".") && !s.endsWith("..") && s1 != null && s1.length() != 0 && !s1.startsWith(".") && !s1.endsWith(".."))
        {
            String s2 = s;
            if (!s.endsWith("."))
            {
                s = String.valueOf(s);
                s2 = (new StringBuilder(String.valueOf(s).length() + 1)).append(s).append('.').toString();
            }
            s = s1;
            if (!s1.endsWith("."))
            {
                s = String.valueOf(s1);
                s = (new StringBuilder(String.valueOf(s).length() + 1)).append(s).append('.').toString();
            }
            s = s.toLowerCase(Locale.US);
            if (!s.contains("*"))
            {
                return s2.equals(s);
            }
            if (s.startsWith("*.") && s.indexOf('*', 1) == -1 && s2.length() >= s.length() && !"*.".equals(s))
            {
                s = s.substring(1);
                if (s2.endsWith(s))
                {
                    int i = s2.length() - s.length();
                    if (i <= 0 || s2.lastIndexOf('.', i - 1) == -1)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final boolean verify(String s, SSLSession sslsession)
    {
        boolean flag1 = false;
        sslsession = (X509Certificate)sslsession.getPeerCertificates()[0];
        if (!VERIFY_AS_IP_ADDRESS.matcher(s).matches()) goto _L2; else goto _L1
_L1:
        int j;
        sslsession = getSubjectAltNames(sslsession, 7);
        j = sslsession.size();
        int i = 0;
_L51:
        boolean flag = flag1;
        if (i >= j) goto _L4; else goto _L3
_L3:
        if (!s.equalsIgnoreCase((String)sslsession.get(i))) goto _L6; else goto _L5
_L5:
        flag = true;
          goto _L4
_L2:
        String s1;
        DistinguishedNameParser distinguishednameparser;
        int k;
        int l;
        try
        {
            s1 = s.toLowerCase(Locale.US);
            s = getSubjectAltNames(sslsession, 2);
            l = s.size();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            return false;
        }
        k = 0;
        i = 0;
        if (k >= l)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        if (verifyHostName(s1, (String)s.get(k)))
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
        if (i != 0) goto _L8; else goto _L7
_L7:
        distinguishednameparser = new DistinguishedNameParser(sslsession.getSubjectX500Principal());
        distinguishednameparser.pos = 0;
        distinguishednameparser.beg = 0;
        distinguishednameparser.end = 0;
        distinguishednameparser.cur = 0;
        distinguishednameparser.chars = distinguishednameparser.dn.toCharArray();
        sslsession = distinguishednameparser.nextAT();
        if (sslsession != null) goto _L10; else goto _L9
_L9:
        s = null;
_L12:
        if (s == null) goto _L8; else goto _L11
_L11:
        return verifyHostName(s1, s);
_L10:
        s = "";
        if (distinguishednameparser.pos != distinguishednameparser.length)
        {
            break MISSING_BLOCK_LABEL_234;
        }
        s = null;
          goto _L12
        distinguishednameparser.chars[distinguishednameparser.pos];
        JVM INSTR lookupswitch 5: default 1721
    //                   34: 385
    //                   35: 663
    //                   43: 358
    //                   44: 358
    //                   59: 358;
           goto _L13 _L14 _L15 _L16 _L16 _L16
_L13:
        distinguishednameparser.beg = distinguishednameparser.pos;
        distinguishednameparser.end = distinguishednameparser.pos;
_L46:
        if (distinguishednameparser.pos < distinguishednameparser.length) goto _L18; else goto _L17
_L17:
        s = new String(distinguishednameparser.chars, distinguishednameparser.beg, distinguishednameparser.end - distinguishednameparser.beg);
_L16:
        if ("cn".equalsIgnoreCase(sslsession)) goto _L12; else goto _L19
_L19:
        if (distinguishednameparser.pos < distinguishednameparser.length) goto _L21; else goto _L20
_L20:
        s = null;
          goto _L12
_L14:
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
        distinguishednameparser.beg = distinguishednameparser.pos;
        distinguishednameparser.end = distinguishednameparser.beg;
_L27:
        if (distinguishednameparser.pos == distinguishednameparser.length)
        {
            s = String.valueOf(distinguishednameparser.dn);
            if (s.length() != 0)
            {
                s = "Unexpected end of DN: ".concat(s);
            } else
            {
                s = new String("Unexpected end of DN: ");
            }
            throw new IllegalStateException(s);
        }
        if (distinguishednameparser.chars[distinguishednameparser.pos] != '"') goto _L23; else goto _L22
_L22:
        for (distinguishednameparser.pos = distinguishednameparser.pos + 1; distinguishednameparser.pos < distinguishednameparser.length && distinguishednameparser.chars[distinguishednameparser.pos] == ' '; distinguishednameparser.pos = distinguishednameparser.pos + 1) { }
          goto _L24
_L23:
        if (distinguishednameparser.chars[distinguishednameparser.pos] != '\\') goto _L26; else goto _L25
_L25:
        distinguishednameparser.chars[distinguishednameparser.end] = distinguishednameparser.getEscaped();
_L28:
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
        distinguishednameparser.end = distinguishednameparser.end + 1;
          goto _L27
_L26:
        distinguishednameparser.chars[distinguishednameparser.end] = distinguishednameparser.chars[distinguishednameparser.pos];
          goto _L28
_L24:
        s = new String(distinguishednameparser.chars, distinguishednameparser.beg, distinguishednameparser.end - distinguishednameparser.beg);
          goto _L16
_L15:
        if (distinguishednameparser.pos + 4 >= distinguishednameparser.length)
        {
            s = String.valueOf(distinguishednameparser.dn);
            if (s.length() != 0)
            {
                s = "Unexpected end of DN: ".concat(s);
            } else
            {
                s = new String("Unexpected end of DN: ");
            }
            throw new IllegalStateException(s);
        }
        distinguishednameparser.beg = distinguishednameparser.pos;
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
_L38:
        if (distinguishednameparser.pos != distinguishednameparser.length && distinguishednameparser.chars[distinguishednameparser.pos] != '+' && distinguishednameparser.chars[distinguishednameparser.pos] != ',' && distinguishednameparser.chars[distinguishednameparser.pos] != ';') goto _L30; else goto _L29
_L29:
        distinguishednameparser.end = distinguishednameparser.pos;
_L37:
        l = distinguishednameparser.end - distinguishednameparser.beg;
        if (l >= 5 && (l & 1) != 0) goto _L32; else goto _L31
_L31:
        s = String.valueOf(distinguishednameparser.dn);
        if (s.length() == 0) goto _L34; else goto _L33
_L33:
        s = "Unexpected end of DN: ".concat(s);
_L39:
        throw new IllegalStateException(s);
_L30:
        if (distinguishednameparser.chars[distinguishednameparser.pos] != ' ') goto _L36; else goto _L35
_L35:
        distinguishednameparser.end = distinguishednameparser.pos;
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
        while (distinguishednameparser.pos < distinguishednameparser.length && distinguishednameparser.chars[distinguishednameparser.pos] == ' ') 
        {
            distinguishednameparser.pos = distinguishednameparser.pos + 1;
        }
          goto _L37
_L36:
        if (distinguishednameparser.chars[distinguishednameparser.pos] < 'A' || distinguishednameparser.chars[distinguishednameparser.pos] > 'F')
        {
            break MISSING_BLOCK_LABEL_1013;
        }
        s = distinguishednameparser.chars;
        i = distinguishednameparser.pos;
        s[i] = (char)(s[i] + 32);
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
          goto _L38
_L34:
        s = new String("Unexpected end of DN: ");
          goto _L39
_L32:
        s = new byte[l / 2];
        k = distinguishednameparser.beg + 1;
        i = 0;
_L41:
        if (i >= s.length)
        {
            break; /* Loop/switch isn't completed */
        }
        s[i] = (byte)distinguishednameparser.getByte(k);
        k += 2;
        i++;
        if (true) goto _L41; else goto _L40
_L40:
        s = new String(distinguishednameparser.chars, distinguishednameparser.beg, l);
          goto _L16
_L18:
        distinguishednameparser.chars[distinguishednameparser.pos];
        JVM INSTR lookupswitch 5: default 1727
    //                   32: 1310
    //                   43: 1232
    //                   44: 1232
    //                   59: 1232
    //                   92: 1264;
           goto _L42 _L43 _L44 _L44 _L44 _L45
_L42:
        s = distinguishednameparser.chars;
        i = distinguishednameparser.end;
        distinguishednameparser.end = i + 1;
        s[i] = distinguishednameparser.chars[distinguishednameparser.pos];
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
          goto _L46
_L44:
        s = new String(distinguishednameparser.chars, distinguishednameparser.beg, distinguishednameparser.end - distinguishednameparser.beg);
          goto _L16
_L45:
        s = distinguishednameparser.chars;
        i = distinguishednameparser.end;
        distinguishednameparser.end = i + 1;
        s[i] = distinguishednameparser.getEscaped();
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
          goto _L46
_L43:
        distinguishednameparser.cur = distinguishednameparser.end;
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
        s = distinguishednameparser.chars;
        i = distinguishednameparser.end;
        distinguishednameparser.end = i + 1;
        s[i] = ' ';
_L47:
        if (distinguishednameparser.pos >= distinguishednameparser.length || distinguishednameparser.chars[distinguishednameparser.pos] != ' ')
        {
            continue; /* Loop/switch isn't completed */
        }
        s = distinguishednameparser.chars;
        i = distinguishednameparser.end;
        distinguishednameparser.end = i + 1;
        s[i] = ' ';
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
          goto _L47
        if (distinguishednameparser.pos != distinguishednameparser.length && distinguishednameparser.chars[distinguishednameparser.pos] != ',' && distinguishednameparser.chars[distinguishednameparser.pos] != '+' && distinguishednameparser.chars[distinguishednameparser.pos] != ';') goto _L46; else goto _L48
_L48:
        s = new String(distinguishednameparser.chars, distinguishednameparser.beg, distinguishednameparser.cur - distinguishednameparser.beg);
          goto _L16
_L21:
        if (distinguishednameparser.chars[distinguishednameparser.pos] != ',' && distinguishednameparser.chars[distinguishednameparser.pos] != ';' && distinguishednameparser.chars[distinguishednameparser.pos] != '+')
        {
            s = String.valueOf(distinguishednameparser.dn);
            if (s.length() != 0)
            {
                s = "Malformed DN: ".concat(s);
            } else
            {
                s = new String("Malformed DN: ");
            }
            throw new IllegalStateException(s);
        }
        distinguishednameparser.pos = distinguishednameparser.pos + 1;
        sslsession = distinguishednameparser.nextAT();
        if (sslsession != null) goto _L10; else goto _L49
_L49:
        s = String.valueOf(distinguishednameparser.dn);
        if (s.length() != 0)
        {
            s = "Malformed DN: ".concat(s);
        } else
        {
            s = new String("Malformed DN: ");
        }
        throw new IllegalStateException(s);
_L8:
        flag = false;
_L4:
        return flag;
_L6:
        i++;
        if (true) goto _L51; else goto _L50
_L50:
        k++;
        i = 1;
        break MISSING_BLOCK_LABEL_109;
    }

}
