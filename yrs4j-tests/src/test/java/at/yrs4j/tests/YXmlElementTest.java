package at.yrs4j.tests;

import at.yrs4j.wrapper.interfaces.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class YXmlElementTest extends TestsCommon{

    @Test
    public void YXmlElement_insert_get() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        xml.insertAttr(txn, "key1", "value1");
        xml.insertAttr(txn, "key2", "value2");
        xml.insertAttr(txn, "key3", "value3");
        xml.insertAttr(txn, "key4", "value4");

        String a = xml.getAttr(txn, "key1");
        String b = xml.getAttr(txn, "key2");
        String c = xml.getAttr(txn, "key3");
        String d = xml.getAttr(txn, "key4");

        assertNotNull(a);
        assertNotNull(b);
        assertNotNull(c);
        assertNotNull(d);

        assertEquals("value1", a);
        assertEquals("value2", b);
        assertEquals("value3", c);
        assertEquals("value4", d);
    }

    @Test
    public void YXmlElement_iterator() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        xml.insertAttr(txn, "key1", "value1");
        xml.insertAttr(txn, "key2", "value2");
        xml.insertAttr(txn, "key3", "value3");
        xml.insertAttr(txn, "key4", "value4");

        YXmlAttrIter i = xml.attrIter(txn);

        YXmlAttr attr = i.next();
        assertNotNull(attr);
        attr = i.next();
        assertNotNull(attr);
        attr = i.next();
        assertNotNull(attr);
        attr = i.next();
        assertNotNull(attr);
        attr = i.next();
        assertNull(attr);
    }

    @Test
    void YXmlElement_childrenAPI() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        YXmlElement inner = xml.insertElem(txn, 0, "p");
        YXmlText innerTxt = inner.insertText(txn, 0);
        innerTxt.insert(txn, 0, "hello", null);

        int len = xml.childLen(txn);
        assertEquals(1, len);

        YXmlText txt = xml.insertText(txn, 1);
        txt.insert(txn, 0, "world", null);

        len = xml.childLen(txn);
        assertEquals(2, len);
    }

    @Test
    void YXmlElement_tagNames() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        YXmlElement inner = xml.insertElem(txn, 0, "p");
        YXmlText innerTxt = inner.insertText(txn, 0);
        innerTxt.insert(txn, 0, "hello", null);

        String tag = inner.tag();
        assertEquals("p", tag);

        tag = xml.tag();
        assertEquals("div", tag);

    }

    @Test
    void YXmlElement_parents() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        YXmlElement inner = xml.insertElem(txn, 0, "p");
        YXmlText innerTxt = inner.insertText(txn, 0);
        innerTxt.insert(txn, 0, "hello", null);

        Optional<YXmlElement> parent = inner.parent();
        assertTrue(parent.isPresent());

        String tagParent = parent.get().tag();

        assertEquals("div", tagParent);

        parent = xml.parent();
        assertTrue(parent.isPresent());
    }

    @Test
    void YXmlElement_childTraversal() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        YXmlElement inner = xml.insertElem(txn, 0, "p");
        YXmlText innerTxt = inner.insertText(txn, 0);
        innerTxt.insert(txn, 0, "hello", null);

        Optional<YOutput> curr = xml.firstChild();
        assertTrue(curr.isPresent());

        YXmlElement first = curr.get().readYXmlElement();

        assertFalse(first.prevSibling(txn).isPresent());

        String str = first.string(txn);
        assertEquals("<p>hello</p>", str);
    }

    @Test
    void YXmlElement_treeWalker() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        YXmlElement inner = xml.insertElem(txn, 0, "p");
        YXmlText innerTxt = inner.insertText(txn, 0);
        innerTxt.insert(txn, 0, "hello", null);

        YXmlText text = xml.insertText(txn, 1);
        text.insert(txn, 0, "world", null);

        YXmlTreeWalker walker = xml.treeWalker(txn);
        YOutput curr = walker.next();
        YXmlElement e = curr.readYXmlElement();
        String str = e.string(txn);
        assertEquals("<p>hello</p>", str);

        curr = walker.next();
        YXmlText t = curr.readYXmlText();
        str = t.string(txn);
        assertEquals("hello", str);

        curr = walker.next();
        t = curr.readYXmlText();
        str = t.string(txn);
        assertEquals("world", str);

        curr = walker.next();
        assertNull(curr);

        txn.commit();
    }







}
