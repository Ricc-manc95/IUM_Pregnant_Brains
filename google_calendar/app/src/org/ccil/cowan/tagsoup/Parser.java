// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

// Referenced classes of package org.ccil.cowan.tagsoup:
//            ScanHandler, Schema, ElementType, Element, 
//            AutoDetector, AttributesImpl, Scanner, HTMLSchema, 
//            HTMLScanner

public final class Parser extends DefaultHandler
    implements ScanHandler, XMLReader, LexicalHandler
{

    private static boolean DEFAULT_BOGONS_EMPTY = false;
    private static boolean DEFAULT_CDATA_ELEMENTS = true;
    private static boolean DEFAULT_DEFAULT_ATTRIBUTES = true;
    private static boolean DEFAULT_IGNORABLE_WHITESPACE = false;
    private static boolean DEFAULT_IGNORE_BOGONS = false;
    private static boolean DEFAULT_NAMESPACES = true;
    private static boolean DEFAULT_RESTART_ELEMENTS = true;
    private static boolean DEFAULT_ROOT_BOGONS = true;
    private static boolean DEFAULT_TRANSLATE_COLONS = false;
    private static char etagchars[] = {
        '<', '/', '>'
    };
    private static String legal = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-'()+,./:=?;!*#@$_%";
    private boolean CDATAElements;
    private boolean bogonsEmpty;
    private boolean defaultAttributes;
    private boolean ignorableWhitespace;
    private boolean ignoreBogons;
    private boolean namespaces;
    private boolean restartElements;
    private boolean rootBogons;
    private String theAttributeName;
    private AutoDetector theAutoDetector;
    private ContentHandler theContentHandler;
    private DTDHandler theDTDHandler;
    private boolean theDoctypeIsPresent;
    private String theDoctypeName;
    private String theDoctypePublicId;
    private String theDoctypeSystemId;
    private int theEntity;
    private EntityResolver theEntityResolver;
    private ErrorHandler theErrorHandler;
    private HashMap theFeatures;
    private LexicalHandler theLexicalHandler;
    private Element theNewElement;
    private Element thePCDATA;
    private String thePITarget;
    private Element theSaved;
    private Scanner theScanner;
    private Schema theSchema;
    private Element theStack;
    private boolean translateColons;
    private boolean virginStack;

    public Parser()
    {
        theContentHandler = this;
        theLexicalHandler = this;
        theDTDHandler = this;
        theErrorHandler = this;
        theEntityResolver = this;
        namespaces = DEFAULT_NAMESPACES;
        ignoreBogons = false;
        bogonsEmpty = false;
        rootBogons = DEFAULT_ROOT_BOGONS;
        defaultAttributes = DEFAULT_DEFAULT_ATTRIBUTES;
        translateColons = false;
        restartElements = DEFAULT_RESTART_ELEMENTS;
        ignorableWhitespace = false;
        CDATAElements = DEFAULT_CDATA_ELEMENTS;
        theFeatures = new HashMap();
        HashMap hashmap = theFeatures;
        Boolean boolean1;
        if (DEFAULT_NAMESPACES)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        hashmap.put("http://xml.org/sax/features/namespaces", boolean1);
        theFeatures.put("http://xml.org/sax/features/namespace-prefixes", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/external-general-entities", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/external-parameter-entities", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/is-standalone", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/lexical-handler/parameter-entities", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/resolve-dtd-uris", Boolean.TRUE);
        theFeatures.put("http://xml.org/sax/features/string-interning", Boolean.TRUE);
        theFeatures.put("http://xml.org/sax/features/use-attributes2", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/use-locator2", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/use-entity-resolver2", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/validation", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/xmlns-uris", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/xmlns-uris", Boolean.FALSE);
        theFeatures.put("http://xml.org/sax/features/xml-1.1", Boolean.FALSE);
        theFeatures.put("http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons", Boolean.FALSE);
        theFeatures.put("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty", Boolean.FALSE);
        hashmap = theFeatures;
        if (DEFAULT_ROOT_BOGONS)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        hashmap.put("http://www.ccil.org/~cowan/tagsoup/features/root-bogons", boolean1);
        hashmap = theFeatures;
        if (DEFAULT_DEFAULT_ATTRIBUTES)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        hashmap.put("http://www.ccil.org/~cowan/tagsoup/features/default-attributes", boolean1);
        theFeatures.put("http://www.ccil.org/~cowan/tagsoup/features/translate-colons", Boolean.FALSE);
        hashmap = theFeatures;
        if (DEFAULT_RESTART_ELEMENTS)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        hashmap.put("http://www.ccil.org/~cowan/tagsoup/features/restart-elements", boolean1);
        theFeatures.put("http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace", Boolean.FALSE);
        hashmap = theFeatures;
        if (DEFAULT_CDATA_ELEMENTS)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        hashmap.put("http://www.ccil.org/~cowan/tagsoup/features/cdata-elements", boolean1);
        theNewElement = null;
        theAttributeName = null;
        theDoctypeIsPresent = false;
        theDoctypePublicId = null;
        theDoctypeSystemId = null;
        theDoctypeName = null;
        thePITarget = null;
        theStack = null;
        theSaved = null;
        thePCDATA = null;
        theEntity = 0;
        virginStack = true;
    }

    private final void etag_basic(char ac[], int i, int j)
        throws SAXException
    {
        theNewElement = null;
        if (j == 0) goto _L2; else goto _L1
_L1:
        ac = makeName(ac, i, j);
        ac = (ElementType)theSchema.theElementTypes.get(ac.toLowerCase());
        if (ac != null) goto _L4; else goto _L3
_L3:
        return;
_L4:
        ac = ((ElementType) (ac)).theName;
_L6:
        Element element;
        i = 0;
        for (element = theStack; element != null && !element.theType.theName.equals(ac); element = element.theNext)
        {
            if ((element.theType.theFlags & 4) != 0)
            {
                i = 1;
            }
        }

        break; /* Loop/switch isn't completed */
_L2:
        ac = theStack.theType.theName;
        if (true) goto _L6; else goto _L5
_L5:
        if (element == null || element.theNext == null || element.theNext.theNext == null) goto _L3; else goto _L7
_L7:
        if (i == 0) goto _L9; else goto _L8
_L8:
        element.preclosed = true;
_L12:
        if (!theStack.preclosed)
        {
            restart(null);
            return;
        }
          goto _L10
_L9:
        while (theStack != element) 
        {
            restartablyPop();
        }
_L10:
        pop();
        if (true) goto _L12; else goto _L11
_L11:
    }

    private final boolean foreign(String s, String s1)
    {
        return !s.equals("") && !s1.equals("") && !s1.equals(theSchema.theURI);
    }

    private final Reader getReader(InputSource inputsource)
        throws SAXException, IOException
    {
        String s;
label0:
        {
            Reader reader = inputsource.getCharacterStream();
            java.io.InputStream inputstream = inputsource.getByteStream();
            s = inputsource.getEncoding();
            inputsource.getPublicId();
            String s1 = inputsource.getSystemId();
            inputsource = reader;
            if (reader == null)
            {
                inputsource = inputstream;
                if (inputstream == null)
                {
                    inputsource = (new URL(new URL("file", "", System.getProperty("user.dir") + "/."), s1)).openConnection().getInputStream();
                }
                if (s != null)
                {
                    break label0;
                }
                inputsource = theAutoDetector.autoDetectingReader(inputsource);
            }
            return inputsource;
        }
        InputStreamReader inputstreamreader;
        try
        {
            inputstreamreader = new InputStreamReader(inputsource, s);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            return new InputStreamReader(inputsource);
        }
        return inputstreamreader;
    }

    private final int lookupEntity(char ac[], int i, int j)
    {
        if (j <= 0)
        {
            return 0;
        }
        if (ac[i] == '#')
        {
            if (j > 1 && (ac[i + 1] == 'x' || ac[i + 1] == 'X'))
            {
                try
                {
                    i = Integer.parseInt(new String(ac, i + 2, j - 2), 16);
                }
                // Misplaced declaration of an exception variable
                catch (char ac[])
                {
                    return 0;
                }
                return i;
            }
            try
            {
                i = Integer.parseInt(new String(ac, i + 1, j - 1), 10);
            }
            // Misplaced declaration of an exception variable
            catch (char ac[])
            {
                return 0;
            }
            return i;
        }
        Schema schema = theSchema;
        ac = new String(ac, i, j);
        ac = (Integer)schema.theEntities.get(ac);
        if (ac == null)
        {
            return 0;
        } else
        {
            return ac.intValue();
        }
    }

    private final String makeName(char ac[], int i, int j)
    {
        StringBuffer stringbuffer = new StringBuffer(j + 2);
        boolean flag = true;
        boolean flag3 = false;
        int k = j;
        int l = i;
        i = ((flag3) ? 1 : 0);
        j = ((flag) ? 1 : 0);
        while (k > 0) 
        {
            char c = ac[l];
            int i1;
            if (Character.isLetter(c) || c == '_')
            {
                stringbuffer.append(c);
                boolean flag1 = false;
                j = i;
                i = ((flag1) ? 1 : 0);
            } else
            if (Character.isDigit(c) || c == '-' || c == '.')
            {
                if (j != 0)
                {
                    stringbuffer.append('_');
                }
                stringbuffer.append(c);
                boolean flag2 = false;
                j = i;
                i = ((flag2) ? 1 : 0);
            } else
            if (c == ':' && i == 0)
            {
                if (j != 0)
                {
                    stringbuffer.append('_');
                }
                if (translateColons)
                {
                    c = '_';
                }
                stringbuffer.append(c);
                i = 1;
                j = 1;
            } else
            {
                int j1 = i;
                i = j;
                j = j1;
            }
            i1 = l + 1;
            l = j;
            k--;
            j = i;
            i = l;
            l = i1;
        }
        i = stringbuffer.length();
        if (i == 0 || stringbuffer.charAt(i - 1) == ':')
        {
            stringbuffer.append('_');
        }
        return stringbuffer.toString().intern();
    }

    private final void pop()
        throws SAXException
    {
        if (theStack == null)
        {
            return;
        }
        String s4 = theStack.theType.theName;
        String s2 = theStack.theType.theLocalName;
        Object obj = theStack.theType.theNamespace;
        int i = s4.indexOf(':');
        String s = "";
        if (i != -1)
        {
            s = s4.substring(0, i);
        }
        if (!namespaces)
        {
            obj = "";
            s2 = "";
        }
        theContentHandler.endElement(((String) (obj)), s2, s4);
        if (foreign(s, ((String) (obj))))
        {
            theContentHandler.endPrefixMapping(s);
        }
        obj = theStack.theAtts;
        for (int j = ((Attributes) (obj)).getLength() - 1; j >= 0; j--)
        {
            String s3 = ((Attributes) (obj)).getURI(j);
            String s5 = ((Attributes) (obj)).getQName(j);
            int k = s5.indexOf(':');
            String s1 = "";
            if (k != -1)
            {
                s1 = s5.substring(0, k);
            }
            if (foreign(s1, s3))
            {
                theContentHandler.endPrefixMapping(s1);
            }
        }

        theStack = theStack.theNext;
    }

    private final void push(Element element)
        throws SAXException
    {
        String s4 = element.theType.theName;
        String s1 = element.theType.theLocalName;
        String s = element.theType.theNamespace;
        int i = s4.indexOf(':');
        String s2 = "";
        if (i != -1)
        {
            s2 = s4.substring(0, i);
        }
        for (int j = element.theAtts.getLength() - 1; j >= 0; j--)
        {
            String s5 = element.theAtts.getLocalName(j);
            if (element.theAtts.getValue(j) == null || s5 == null || s5.length() == 0)
            {
                element.theAtts.removeAttribute(j);
            }
        }

        if (!namespaces)
        {
            s = "";
            s1 = "";
        }
        AttributesImpl attributesimpl;
        int l;
        if (virginStack && s1.equalsIgnoreCase(theDoctypeName))
        {
            try
            {
                theEntityResolver.resolveEntity(theDoctypePublicId, theDoctypeSystemId);
            }
            catch (IOException ioexception) { }
        }
        if (foreign(s2, s))
        {
            theContentHandler.startPrefixMapping(s2, s);
        }
        attributesimpl = element.theAtts;
        l = attributesimpl.getLength();
        for (int k = 0; k < l; k++)
        {
            String s6 = attributesimpl.getURI(k);
            String s7 = attributesimpl.getQName(k);
            int i1 = s7.indexOf(':');
            String s3 = "";
            if (i1 != -1)
            {
                s3 = s7.substring(0, i1);
            }
            if (foreign(s3, s6))
            {
                theContentHandler.startPrefixMapping(s3, s6);
            }
        }

        theContentHandler.startElement(s, s1, s4, element.theAtts);
        element.theNext = theStack;
        theStack = element;
        virginStack = false;
        if (CDATAElements && (theStack.theType.theFlags & 2) != 0)
        {
            theScanner.startCDATA();
        }
    }

    private final void rectify(Element element)
        throws SAXException
    {
        Element element1;
        do
        {
            element1 = theStack;
            do
            {
                if (element1 == null)
                {
                    break;
                }
                ElementType elementtype = element1.theType;
                ElementType elementtype2 = element.theType;
                boolean flag;
                if ((elementtype.theModel & elementtype2.theMemberOf) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break;
                }
                element1 = element1.theNext;
            } while (true);
            if (element1 != null)
            {
                break;
            }
            ElementType elementtype1 = element.theType.theParent;
            if (elementtype1 == null)
            {
                break;
            }
            element1 = new Element(elementtype1, defaultAttributes);
            element1.theNext = element;
            element = element1;
        } while (true);
        if (element1 == null)
        {
            return;
        }
        Element element2;
        do
        {
            element2 = element;
            if (theStack == element1)
            {
                break;
            }
            element2 = element;
            if (theStack == null)
            {
                break;
            }
            element2 = element;
            if (theStack.theNext == null)
            {
                break;
            }
            element2 = element;
            if (theStack.theNext.theNext == null)
            {
                break;
            }
            restartablyPop();
        } while (true);
        for (; element2 != null; element2 = element)
        {
            element = element2.theNext;
            if (!element2.theType.theName.equals("<pcdata>"))
            {
                push(element2);
            }
            restart(element);
        }

        theNewElement = null;
    }

    private final void restart(Element element)
        throws SAXException
    {
        while (theSaved != null) 
        {
            Object obj2 = theStack;
            Object obj = theSaved;
            obj2 = ((Element) (obj2)).theType;
            obj = ((Element) (obj)).theType;
            boolean flag;
            if ((((ElementType) (obj2)).theModel & ((ElementType) (obj)).theMemberOf) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break;
            }
            if (element != null)
            {
                Object obj1 = theSaved.theType;
                ElementType elementtype = element.theType;
                if ((((ElementType) (obj1)).theModel & elementtype.theMemberOf) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break;
                }
            }
            obj1 = theSaved.theNext;
            push(theSaved);
            theSaved = ((Element) (obj1));
        }
    }

    private final void restartablyPop()
        throws SAXException
    {
        Element element = theStack;
        pop();
        if (restartElements && (element.theType.theFlags & 1) != 0)
        {
            for (int i = element.theAtts.getLength() - 1; i >= 0; i--)
            {
                if (element.theAtts.getType(i).equals("ID") || element.theAtts.getQName(i).equals("name"))
                {
                    element.theAtts.removeAttribute(i);
                }
            }

            element.theNext = theSaved;
            theSaved = element;
        }
    }

    private static String trimquotes(String s)
    {
        int i;
        char c;
        if (s != null)
        {
            if ((i = s.length()) != 0 && ((c = s.charAt(0)) == s.charAt(i - 1) && (c == '\'' || c == '"')))
            {
                return s.substring(1, s.length() - 1);
            }
        }
        return s;
    }

    public final void adup$51DK6IA955B0____0()
        throws SAXException
    {
        if (theNewElement == null || theAttributeName == null)
        {
            return;
        } else
        {
            Element element = theNewElement;
            String s = theAttributeName;
            String s1 = theAttributeName;
            element.theType.setAttribute(element.theAtts, s, null, s1);
            theAttributeName = null;
            return;
        }
    }

    public final void aname(char ac[], int i, int j)
        throws SAXException
    {
        if (theNewElement == null)
        {
            return;
        } else
        {
            theAttributeName = makeName(ac, 0, j).toLowerCase();
            return;
        }
    }

    public final void aval(char ac[], int i, int j)
        throws SAXException
    {
        if (theNewElement == null || theAttributeName == null)
        {
            return;
        }
        ac = new String(ac, 0, j);
        int j1 = ac.length();
        char ac1[] = new char[j1];
        int i1 = 0;
        i = 0;
        j = -1;
        while (i1 < j1) 
        {
            char c = ac.charAt(i1);
            int k = i + 1;
            ac1[i] = c;
            int l;
            if (c == '&' && j == -1)
            {
                i = k;
                l = k;
            } else
            {
                i = j;
                l = k;
                if (j != -1)
                {
                    i = j;
                    l = k;
                    if (!Character.isLetter(c))
                    {
                        i = j;
                        l = k;
                        if (!Character.isDigit(c))
                        {
                            i = j;
                            l = k;
                            if (c != '#')
                            {
                                if (c == ';')
                                {
                                    i = lookupEntity(ac1, j, k - j - 1);
                                    Element element;
                                    String s;
                                    if (i > 65535)
                                    {
                                        i -= 0x10000;
                                        ac1[j - 1] = (char)((i >> 10) + 55296);
                                        ac1[j] = (char)((i & 0x3ff) + 56320);
                                        i = j + 1;
                                    } else
                                    if (i != 0)
                                    {
                                        ac1[j - 1] = (char)i;
                                        i = j;
                                    } else
                                    {
                                        i = k;
                                    }
                                    l = i;
                                    i = -1;
                                } else
                                {
                                    i = -1;
                                    l = k;
                                }
                            }
                        }
                    }
                }
            }
            i1++;
            j = i;
            i = l;
        }
        ac = new String(ac1, 0, i);
        element = theNewElement;
        s = theAttributeName;
        element.theType.setAttribute(element.theAtts, s, null, ac);
        theAttributeName = null;
    }

    public final void cmnt(char ac[], int i, int j)
        throws SAXException
    {
        theLexicalHandler.comment(ac, 0, j);
    }

    public final void comment(char ac[], int i, int j)
        throws SAXException
    {
    }

    public final void decl(char ac[], int i, int j)
        throws SAXException
    {
        Object obj;
        ac = (new String(ac, 0, j)).trim();
        if (ac.length() == 0)
        {
            obj = new String[0];
        } else
        {
            obj = new ArrayList();
            int j1 = ac.length();
            i = 0;
            boolean flag2 = false;
            boolean flag1 = false;
            int l = 0;
            j = 0;
            while (i < j1) 
            {
                char c = ac.charAt(i);
                boolean flag3;
                boolean flag4;
                if (!flag2 && c == '\'' && j != 92)
                {
                    char c2;
                    if (!flag1)
                    {
                        c2 = '\001';
                    } else
                    {
                        c2 = '\0';
                    }
                    flag3 = flag2;
                    flag4 = c2;
                    j = l;
                    if (l < 0)
                    {
                        j = i;
                        flag4 = c2;
                        flag3 = flag2;
                    }
                } else
                if (!flag1 && c == '"' && j != 92)
                {
                    boolean flag;
                    if (!flag2)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    flag3 = flag;
                    flag4 = flag1;
                    j = l;
                    if (l < 0)
                    {
                        j = i;
                        flag3 = flag;
                        flag4 = flag1;
                    }
                } else
                {
                    flag3 = flag2;
                    flag4 = flag1;
                    j = l;
                    if (!flag1)
                    {
                        flag3 = flag2;
                        flag4 = flag1;
                        j = l;
                        if (!flag2)
                        {
                            if (Character.isWhitespace(c))
                            {
                                if (l >= 0)
                                {
                                    ((ArrayList) (obj)).add(ac.substring(l, i));
                                }
                                j = -1;
                                flag3 = flag2;
                                flag4 = flag1;
                            } else
                            {
                                flag3 = flag2;
                                flag4 = flag1;
                                j = l;
                                if (l < 0)
                                {
                                    flag3 = flag2;
                                    flag4 = flag1;
                                    j = l;
                                    if (c != ' ')
                                    {
                                        j = i;
                                        flag3 = flag2;
                                        flag4 = flag1;
                                    }
                                }
                            }
                        }
                    }
                }
                i++;
                c2 = c;
                flag2 = flag3;
                flag1 = flag4;
                l = j;
                j = c2;
            }
            ((ArrayList) (obj)).add(ac.substring(l, i));
            obj = (String[])((ArrayList) (obj)).toArray(new String[0]);
        }
        if (obj.length <= 0 || !"DOCTYPE".equalsIgnoreCase(obj[0])) goto _L2; else goto _L1
_L1:
        if (!theDoctypeIsPresent) goto _L4; else goto _L3
_L3:
        return;
_L4:
        String s;
        theDoctypeIsPresent = true;
        if (obj.length <= 1)
        {
            break; /* Loop/switch isn't completed */
        }
        s = obj[1];
        char c1;
        StringBuffer stringbuffer;
        int k;
        int i1;
        if (obj.length > 3 && "SYSTEM".equals(obj[2]))
        {
            obj = obj[3];
            ac = null;
        } else
        if (obj.length > 3 && "PUBLIC".equals(obj[2]))
        {
            ac = obj[3];
            if (obj.length > 4)
            {
                obj = obj[4];
            } else
            {
                obj = "";
            }
        } else
        {
            ac = null;
            obj = null;
        }
_L5:
        ac = trimquotes(ac);
        obj = trimquotes(((String) (obj)));
        if (s != null)
        {
            if (ac == null)
            {
                ac = null;
            } else
            {
                i1 = ac.length();
                stringbuffer = new StringBuffer(i1);
                k = 1;
                j = 0;
                while (j < i1) 
                {
                    c1 = ac.charAt(j);
                    if (legal.indexOf(c1) != -1)
                    {
                        stringbuffer.append(c1);
                        i = 0;
                    } else
                    {
                        i = k;
                        if (k == 0)
                        {
                            stringbuffer.append(' ');
                            i = 1;
                        }
                    }
                    j++;
                    k = i;
                }
                ac = stringbuffer.toString().trim();
            }
            theLexicalHandler.startDTD(s, ac, ((String) (obj)));
            theLexicalHandler.endDTD();
            theDoctypeName = s;
            theDoctypePublicId = ac;
            if (theScanner instanceof Locator)
            {
                theDoctypeSystemId = ((Locator)theScanner).getSystemId();
                try
                {
                    theDoctypeSystemId = (new URL(new URL(theDoctypeSystemId), ((String) (obj)))).toString();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (char ac[])
                {
                    return;
                }
            }
        }
        if (true) goto _L3; else goto _L2
_L2:
        ac = null;
        obj = null;
        s = null;
          goto _L5
    }

    public final void endCDATA()
        throws SAXException
    {
    }

    public final void endDTD()
        throws SAXException
    {
    }

    public final void endEntity(String s)
        throws SAXException
    {
    }

    public final void entity(char ac[], int i, int j)
        throws SAXException
    {
        theEntity = lookupEntity(ac, 1, j);
    }

    public final void eof$51DK6IA955B0____0()
        throws SAXException
    {
        if (virginStack)
        {
            rectify(thePCDATA);
        }
        while (theStack.theNext != null) 
        {
            pop();
        }
        if (!theSchema.theURI.equals(""))
        {
            theContentHandler.endPrefixMapping(theSchema.thePrefix);
        }
        theContentHandler.endDocument();
    }

    public final void etag(char ac[], int i, int j)
        throws SAXException
    {
        int k;
        boolean flag = true;
        String s = theStack.theType.theName;
        if (!CDATAElements || (theStack.theType.theFlags & 2) == 0)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        int l;
        if (j == s.length())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        l = i;
        if (i == 0) goto _L2; else goto _L1
_L1:
        k = 0;
_L6:
        l = i;
        if (k >= j) goto _L2; else goto _L3
_L3:
        if (Character.toLowerCase(ac[0 + k]) == Character.toLowerCase(s.charAt(k))) goto _L5; else goto _L4
_L4:
        l = 0;
_L2:
        if (l != 0)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        theContentHandler.characters(etagchars, 0, 2);
        theContentHandler.characters(ac, 0, j);
        theContentHandler.characters(etagchars, 2, 1);
        theScanner.startCDATA();
        i = ((flag) ? 1 : 0);
_L7:
        if (i != 0)
        {
            return;
        } else
        {
            etag_basic(ac, 0, j);
            return;
        }
_L5:
        k++;
          goto _L6
        i = 0;
          goto _L7
    }

    public final ContentHandler getContentHandler()
    {
        if (theContentHandler == this)
        {
            return null;
        } else
        {
            return theContentHandler;
        }
    }

    public final DTDHandler getDTDHandler()
    {
        if (theDTDHandler == this)
        {
            return null;
        } else
        {
            return theDTDHandler;
        }
    }

    public final int getEntity()
    {
        return theEntity;
    }

    public final EntityResolver getEntityResolver()
    {
        if (theEntityResolver == this)
        {
            return null;
        } else
        {
            return theEntityResolver;
        }
    }

    public final ErrorHandler getErrorHandler()
    {
        if (theErrorHandler == this)
        {
            return null;
        } else
        {
            return theErrorHandler;
        }
    }

    public final boolean getFeature(String s)
        throws SAXNotRecognizedException, SAXNotSupportedException
    {
        Boolean boolean1 = (Boolean)theFeatures.get(s);
        if (boolean1 == null)
        {
            throw new SAXNotRecognizedException("Unknown feature " + s);
        } else
        {
            return boolean1.booleanValue();
        }
    }

    public final Object getProperty(String s)
        throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if (s.equals("http://xml.org/sax/properties/lexical-handler"))
        {
            if (theLexicalHandler == this)
            {
                return null;
            } else
            {
                return theLexicalHandler;
            }
        }
        if (s.equals("http://www.ccil.org/~cowan/tagsoup/properties/scanner"))
        {
            return theScanner;
        }
        if (s.equals("http://www.ccil.org/~cowan/tagsoup/properties/schema"))
        {
            return theSchema;
        }
        if (s.equals("http://www.ccil.org/~cowan/tagsoup/properties/auto-detector"))
        {
            return theAutoDetector;
        } else
        {
            throw new SAXNotRecognizedException("Unknown property " + s);
        }
    }

    public final void gi(char ac[], int i, int j)
        throws SAXException
    {
        byte byte0 = -1;
        if (theNewElement == null) goto _L2; else goto _L1
_L1:
        String s;
        return;
_L2:
        if ((s = makeName(ac, 0, j)) == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        ElementType elementtype = (ElementType)theSchema.theElementTypes.get(s.toLowerCase());
        ac = elementtype;
        if (elementtype != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (ignoreBogons)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (bogonsEmpty)
        {
            i = 0;
        } else
        {
            i = -1;
        }
        if (rootBogons)
        {
            j = byte0;
        } else
        {
            j = 0x7fffffff;
        }
        theSchema.elementType(s, i, j, 0);
        if (!rootBogons)
        {
            theSchema.parent(s, theSchema.theRoot.theName);
        }
        ac = (ElementType)theSchema.theElementTypes.get(s.toLowerCase());
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L3
_L3:
        theNewElement = new Element(ac, defaultAttributes);
        return;
    }

    public final void parse(String s)
        throws IOException, SAXException
    {
        parse(new InputSource(s));
    }

    public final void parse(InputSource inputsource)
        throws IOException, SAXException
    {
        if (theSchema == null)
        {
            theSchema = new HTMLSchema();
        }
        if (theScanner == null)
        {
            theScanner = new HTMLScanner();
        }
        if (theAutoDetector == null)
        {
            theAutoDetector = new _cls1();
        }
        theStack = new Element((ElementType)theSchema.theElementTypes.get("<root>".toLowerCase()), defaultAttributes);
        thePCDATA = new Element((ElementType)theSchema.theElementTypes.get("<pcdata>".toLowerCase()), defaultAttributes);
        theNewElement = null;
        theAttributeName = null;
        thePITarget = null;
        theSaved = null;
        theEntity = 0;
        virginStack = true;
        theDoctypeSystemId = null;
        theDoctypePublicId = null;
        theDoctypeName = null;
        Reader reader = getReader(inputsource);
        theContentHandler.startDocument();
        theScanner.resetDocumentLocator(inputsource.getPublicId(), inputsource.getSystemId());
        if (theScanner instanceof Locator)
        {
            theContentHandler.setDocumentLocator((Locator)theScanner);
        }
        if (!theSchema.theURI.equals(""))
        {
            theContentHandler.startPrefixMapping(theSchema.thePrefix, theSchema.theURI);
        }
        theScanner.scan(reader, this);
    }

    public final void pcdata(char ac[], int i, int j)
        throws SAXException
    {
        if (j != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        i = 0;
        boolean flag = true;
        for (; i < j; i++)
        {
            if (!Character.isWhitespace(ac[i + 0]))
            {
                flag = false;
            }
        }

        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = theStack;
        Object obj = thePCDATA;
        obj1 = ((Element) (obj1)).theType;
        obj = ((Element) (obj)).theType;
        if ((((ElementType) (obj1)).theModel & ((ElementType) (obj)).theMemberOf) != 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (ignorableWhitespace)
        {
            theContentHandler.ignorableWhitespace(ac, 0, j);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        rectify(thePCDATA);
        theContentHandler.characters(ac, 0, j);
        return;
    }

    public final void pi(char ac[], int i, int j)
        throws SAXException
    {
        while (theNewElement != null || thePITarget == null || "xml".equalsIgnoreCase(thePITarget)) 
        {
            return;
        }
        i = j;
        if (j > 0)
        {
            i = j;
            if (ac[j - 1] == '?')
            {
                i = j - 1;
            }
        }
        theContentHandler.processingInstruction(thePITarget, new String(ac, 0, i));
        thePITarget = null;
    }

    public final void pitarget(char ac[], int i, int j)
        throws SAXException
    {
        if (theNewElement != null)
        {
            return;
        } else
        {
            thePITarget = makeName(ac, 0, j).replace(':', '_');
            return;
        }
    }

    public final void setContentHandler(ContentHandler contenthandler)
    {
        Object obj = contenthandler;
        if (contenthandler == null)
        {
            obj = this;
        }
        theContentHandler = ((ContentHandler) (obj));
    }

    public final void setDTDHandler(DTDHandler dtdhandler)
    {
        Object obj = dtdhandler;
        if (dtdhandler == null)
        {
            obj = this;
        }
        theDTDHandler = ((DTDHandler) (obj));
    }

    public final void setEntityResolver(EntityResolver entityresolver)
    {
        Object obj = entityresolver;
        if (entityresolver == null)
        {
            obj = this;
        }
        theEntityResolver = ((EntityResolver) (obj));
    }

    public final void setErrorHandler(ErrorHandler errorhandler)
    {
        Object obj = errorhandler;
        if (errorhandler == null)
        {
            obj = this;
        }
        theErrorHandler = ((ErrorHandler) (obj));
    }

    public final void setFeature(String s, boolean flag)
        throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if ((Boolean)theFeatures.get(s) == null)
        {
            throw new SAXNotRecognizedException("Unknown feature " + s);
        }
        if (flag)
        {
            theFeatures.put(s, Boolean.TRUE);
        } else
        {
            theFeatures.put(s, Boolean.FALSE);
        }
        if (s.equals("http://xml.org/sax/features/namespaces"))
        {
            namespaces = flag;
        } else
        {
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/ignore-bogons"))
            {
                ignoreBogons = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/bogons-empty"))
            {
                bogonsEmpty = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/root-bogons"))
            {
                rootBogons = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/default-attributes"))
            {
                defaultAttributes = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/translate-colons"))
            {
                translateColons = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/restart-elements"))
            {
                restartElements = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/ignorable-whitespace"))
            {
                ignorableWhitespace = flag;
                return;
            }
            if (s.equals("http://www.ccil.org/~cowan/tagsoup/features/cdata-elements"))
            {
                CDATAElements = flag;
                return;
            }
        }
    }

    public final void setProperty(String s, Object obj)
        throws SAXNotRecognizedException, SAXNotSupportedException
    {
        if (s.equals("http://xml.org/sax/properties/lexical-handler"))
        {
            if (obj == null)
            {
                theLexicalHandler = this;
                return;
            }
            if (obj instanceof LexicalHandler)
            {
                theLexicalHandler = (LexicalHandler)obj;
                return;
            } else
            {
                throw new SAXNotSupportedException("Your lexical handler is not a LexicalHandler");
            }
        }
        if (s.equals("http://www.ccil.org/~cowan/tagsoup/properties/scanner"))
        {
            if (obj instanceof Scanner)
            {
                theScanner = (Scanner)obj;
                return;
            } else
            {
                throw new SAXNotSupportedException("Your scanner is not a Scanner");
            }
        }
        if (s.equals("http://www.ccil.org/~cowan/tagsoup/properties/schema"))
        {
            if (obj instanceof Schema)
            {
                theSchema = (Schema)obj;
                return;
            } else
            {
                throw new SAXNotSupportedException("Your schema is not a Schema");
            }
        }
        if (s.equals("http://www.ccil.org/~cowan/tagsoup/properties/auto-detector"))
        {
            if (obj instanceof AutoDetector)
            {
                theAutoDetector = (AutoDetector)obj;
                return;
            } else
            {
                throw new SAXNotSupportedException("Your auto-detector is not an AutoDetector");
            }
        } else
        {
            throw new SAXNotRecognizedException("Unknown property " + s);
        }
    }

    public final void stagc(char ac[], int i, int j)
        throws SAXException
    {
        if (theNewElement != null)
        {
            rectify(theNewElement);
            if (theStack.theType.theModel == 0)
            {
                etag_basic(ac, 0, j);
                return;
            }
        }
    }

    public final void stage(char ac[], int i, int j)
        throws SAXException
    {
        if (theNewElement == null)
        {
            return;
        } else
        {
            rectify(theNewElement);
            etag_basic(ac, 0, j);
            return;
        }
    }

    public final void startCDATA()
        throws SAXException
    {
    }

    public final void startDTD(String s, String s1, String s2)
        throws SAXException
    {
    }

    public final void startEntity(String s)
        throws SAXException
    {
    }


    private class _cls1
        implements AutoDetector
    {

        public final Reader autoDetectingReader(InputStream inputstream)
        {
            return new InputStreamReader(inputstream);
        }

        _cls1()
        {
        }
    }

}
