package org.watij.webspec.dsl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: brianknorr
 * Date: Apr 5, 2010
 * Time: 5:05:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tag extends Base implements Finder {

    static List<String> operators = Arrays.asList("=", ">", "<", "!=", ".");
    public Find find;
    public Parent parent;
    public Child child;
    public With with;
    public Set set;
    public ShouldHave shouldHave;
    public Call call;
    public All all;
    public Get get;

    public Tag(WebSpec spec, String listVar, long timeout, Method method) {
        super(spec, listVar, timeout, method);
        find = new Find(this);
        parent = new Parent(this);
        child = new Child(this);
        with = new With(this);
        set = new Set(this);
        call = new Call(this);
        shouldHave = new ShouldHave(this);
        all = new All(this);
        get = new Get(this);
    }

    int length() {
        WebSpec.debug("LENGTH", true);
        int length =Integer.valueOf(spec.execute(listVar+".length;"));
        return length;
    }



    public Tag find(String tag) {
        spec.debug("FIND "+ tag + " ", true);
        String nextListVar = spec.nextListVar();
        Tag query = this;
        if (!query.doFind(tag, nextListVar)) {
            long start = new Date().getTime();
            while (new Date().getTime() - start  < timeout) {
                query = (Tag)method.redo();
                if (query.doFind(tag, nextListVar)) { break; }
            }
        }
        return new Tag(spec, nextListVar, timeout, method("find").param(tag));
    }

    protected boolean doFind(String tag, String nextListVar) {
        //debug("______query doTag");
        return "true".equals(spec.execute(
                    "elementForWatix = "+listVar+"[0];" +
                    "if (elementForWatix.tagName == 'IFRAME' || elementForWatix.tagName == 'FRAME') {"+
                        "if (elementForWatix.contentDocument) { "+
                            "elementForWatix = elementForWatix.contentDocument;"+
                        "} else { "+
                            "elementForWatix = elementForWatix.contentWindow.document;"+
                        "}"+
                    "}"+
                    nextListVar + " = elementForWatix.getElementsByTagName('"+tag+"');" +
                    nextListVar + ".length > 0;"
            ));
    }

    public Tag child(String tag) {
        spec.debug("CHILD "+ tag, true);
        String nextListVar = spec.nextListVar();
        Tag query = this;
        if (!query.doChild(tag, nextListVar)) {
            long start = new Date().getTime();
            while (new Date().getTime() - start  < timeout) {
                query = (Tag)method.redo();
                if (query.doFind(tag, nextListVar)) { break; }
            }
        }
        return new Tag(spec, nextListVar, timeout, method("child").param(tag));
    }

    protected boolean doChild(String tag, String nextListVar) {
        //debug("______query doTag");
        return "true".equals(spec.execute(
                    "elementForWatix = "+listVar+"[0];" +
                    "if (elementForWatix.tagName == 'IFRAME' || elementForWatix.tagName == 'FRAME') {"+
                        "elementForWatix = elementForWatix.contentDocument;"+
                    "}"+
                    "watixTemp = new Array();" +
                    "for (var i=0; i<elementForWatix.childNodes.length; i++) {"+
                        "if ((elementForWatix.childNodes[i].nodeType == 1) && (elementForWatix.childNodes[i].nodeName == '"+tag.toUpperCase()+"')) {"+
                            "watixTemp.push(elementForWatix.childNodes[i]);" +
                        "}"+
                    "}"+
                    nextListVar + " = watixTemp;" +
                    nextListVar + ".length > 0;"
            ));
    }

    public Tag parent(String tag) {
        spec.debug("PARENT "+ tag, true);
        String nextListVar = spec.nextListVar();
        Tag query = this;
        if (!query.doParent(tag, nextListVar)) {
            long start = new Date().getTime();
            while (new Date().getTime() - start  < timeout) {
                query = (Tag)method.redo();
                if (query.doFind(tag, nextListVar)) { break; }
            }
        }
        return new Tag(spec, nextListVar, timeout, method("parent").param(tag));
    }

    protected boolean doParent(String tag, String nextListVar) {
        //debug("______query doTag");
        return "true".equals(spec.execute(
                    "elementForWatix = "+listVar+"[0];" +
                    "watixTemp = new Array();" +
                    "watixParent = elementForWatix.parentNode;" +
                    "while (watixParent != null) {"+
                        "if ((watixParent.nodeType == 1) && (watixParent.nodeName == '"+tag.toUpperCase()+"')) {"+
                            "watixTemp.push(watixParent);" +
                        "}"+
                        "watixParent = watixParent.parentNode;" +
                    "}"+
                    nextListVar + " = watixTemp;" +
                    nextListVar + ".length > 0;"
            ));
    }

    public Tag with(String property, String value) {
        return with().property(property, value);
    }

    public Tag with(String property, Number value) {
        return with().property(property, value);
    }

    public Tag with(String property, boolean value) {
        return with().property(property, value);
    }

    public Tag with(String with) {
        spec.debug("WITH "+ with + " ", true);
        String nextListVar = spec.nextListVar();
        with = withContentIfNeeded(with);
        Tag tag = this;
        if (!tag.doWith(with, nextListVar)) {
            long start = new Date().getTime();
            while (new Date().getTime() - start  < timeout) {
                tag = (Tag)method.redo();
                if (tag.doWith(with, nextListVar)) { break; }
            }
        }
        return new Tag(spec, nextListVar, timeout, method("with").param(with));
    }

    private boolean doWith(String with, String nextListVar) {
        return "true".equals(spec.execute(
           "watixTemp = new Array();" +
            "for (i=0;i<"+listVar+".length;i++) {" +
                "elementForWatix = "+listVar+"[i];" +
                "if (elementForWatix."+with+") {" +
                    "watixTemp.push(elementForWatix);" +
                "}" +
            "}" +
            nextListVar + " = watixTemp;" +
            nextListVar + ".length > 0;"
        ));
    }

    private static String operatorFor(String call) {
        for (String op: operators) {
            if (call.contains(op)) {
                return op;
            }
        }
        return null;
    }

    private String withContentIfNeeded(String call) {
        if (operatorFor(call) == null) {
            call = contentMask() + useEqualOrSearch(call);
        }
        return call;
    }

    private String setContentIfNeeded(String set) {
        if (!set.contains("=")) {
            set = contentMask() + "='"+set+"'";
        }
        return set;
    }

    private String contentMask() {
        return spec.isMozilla() ? "textContent" : "innerText";
    }

    public Tag at(int index) {
        spec.debug("AT "+ index + " ", true);
        String nextListVar = spec.nextListVar();
        Tag tag = this;
        if (!tag.doAt(index, nextListVar)) {
            long start = new Date().getTime();
            while (new Date().getTime() - start  < timeout) {
                tag = (Tag)method.redo();
                if (tag.doAt(index, nextListVar)) { break; }
            }
        }
        return new Tag(spec, nextListVar, timeout, method("at").param(index));
    }

    private boolean doAt(int index, String nextListVar) {
        return "true".equals(spec.execute(
                "elementForWatix = "+listVar+"["+index+"];" +
                nextListVar + " = (elementForWatix==null) ? [] : [elementForWatix];" +
                nextListVar + ".length > 0;"
        ));
    }

    public Tag set(String property, String value) {
        return set().property(property, value);
    }

    public Tag set(String property, Number value) {
        return set().property(property, value);
    }

    public Tag set(String property, boolean value) {
        return set().property(property, value);
    }

    public Tag set(String set) {
        WebSpec.debug("SET ", true);
        call(setContentIfNeeded(set));
        return this;
    }

    public String get(String get) {
        WebSpec.debug("GET ", true);
        return call(get);
    }

    public Tag click() {
        return click(WebSpec.click_then_pause_until_ready);
    }

    public Tag click(boolean pauseUntilReady) {
        WebSpec.debug("CLICK ");
        shouldExist("CLICK");
        spec.eval(
                "elementForWatix = "+listVar+"[0];" +
                "if (elementForWatix.click) {" +
                    "elementForWatix.click();" +
                "} else { " +
                    "allowDefaultActionForWatix = (elementForWatix.onclick == undefined) || (elementForWatix.onclick() != false);" +
                    "if (allowDefaultActionForWatix) {"+
                        "if (elementForWatix.href.replace(/^\\s*/, \"\").toLowerCase().indexOf(\"javascript\") == 0) {"+
                            "eval(elementForWatix.href);"+
                        "} else { " +
                            "window.location = elementForWatix.href;"+
                        "}"+

                    "}"+

                "}"
        );
        if (pauseUntilReady) {
            pauseUntilReady();
        } else {
            pause(100);
        }
        return this;
    }

    public Tag flash() {
        String originalColor = call("style.backgroundColor");
        if (originalColor == null) {
            originalColor = "";
        }
        long start = new Date().getTime();
        while (new Date().getTime() - start  < 1000) {
            call("style.backgroundColor = 'blue'");
            spec.pause(25);
            call("style.backgroundColor = 'yellow'");
        }
        call("style.backgroundColor = '" + originalColor + "'");
        return this;
    }

    public Tag shouldHave(String property, String value) {
        return shouldHave().property(property, value);
    }

    public Tag shouldHave(String property, Number value) {
        return shouldHave().property(property, value);
    }

    public Tag shouldHave(String property, boolean value) {
        return shouldHave().property(property, value);
    }

    public Tag shouldHave(String expectation) {
        WebSpec.debug("SHOULD HAVE ", true);
        shouldExist("SHOULD HAVE");
        return shouldHave("true".equals(call(withContentIfNeeded(expectation))), expectation);
    }

    public Tag shouldHave(boolean success, String expected) {
        if (!success) {
            String tagName = "unknown tag";
            if (exists()) {
                tagName = get("tagName");
            }
            throw new RuntimeException("Expectation failed for element: " + tagName +", expected: " + expected);
        }
        return this;
    }

    private Tag shouldExist(String methodName) {
        if (!exists()) {
            throw new RuntimeException("Expectation failed: Element should exist before calling "+methodName);
        }
        return this;
    }

    public Tag shouldExist() {
        return shouldHave(exists(),"element to exist");
    }

    public Tag shouldNotExist() {
        return shouldHave(!exists(),"element to not exist");
    }

    public boolean exists() {
        return "true".equals(spec.execute(
                listVar+".length > 0;"
        ));
    }

    public String call(String call) {
        WebSpec.debug(" => " + call);
        shouldExist(call);
        return spec.execute(
                "elementForWatix = "+listVar+"[0];" +
                "elementForWatix."+call+";"
        );
    }

    public String typeof(String call) {
        shouldExist("TYPEOF");
        return spec.execute(
                "elementForWatix = "+listVar+"[0];" +
                "typeof elementForWatix."+call+";"
        );
    }

    public boolean respondsTo(String call) {
        if (!exists()) {
            WebSpec.debug("Tag doesn't exist for RESPONDS TO "+call);
            //need to return true for JRuby so with is called instead of get
            return true;
        }
        return "true".equals(spec.execute(
                "elementForWatix = "+listVar+"[0];" +
                "'"+call+"' in elementForWatix;"
        ));
    }

    @Override
    public Tag timeout(long timeout) {
        return (Tag)super.timeout(timeout);
    }

    public Tag pause(long miliseconds) {
        return (Tag) super.pause(miliseconds);
    }

    @Override
    public Tag pauseUntilReady() {
        return (Tag) super.pauseUntilReady();
    }

    @Override
    public Tag pauseUntilReady(boolean extra_careful) {
        return (Tag)super.pauseUntilReady(extra_careful);
    }

    @Override
    public Tag pauseUntilDone() {
        return (Tag)super.pauseUntilDone();
    }

    String useEqualOrSearch(String value) {
        if (value == null) {
            return "==null";
        }
        if (value.startsWith("/") && value.endsWith("/")) {
            return ".search(" + value + ")!=-1";
        }
        return "=='" + value + "'";
    }




    public Parent parent() {
        return parent;
    }

    public Child child() {
        return child;
    }

    public With with() {
        return with;
    }

    public Set set() {
        return set;
    }

    public ShouldHave shouldHave() {
        return shouldHave;
    }

    public Call call() {
        return call;
    }

    public All all() {
        return all;
    }

    public Get get() {
        return get;
    }

    public Find find() {
        return find;
    }
}