package org.watij.webspec;

import junit.framework.TestCase;
import org.watij.webspec.dsl.*;
import org.watij.webspec.dsl.cookie.Cookie;

/**
 * Created by IntelliJ IDEA.
 * User: brianknorr
 * Date: Apr 11, 2010
 * Time: 6:28:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebSpecTestCase extends TestCase {

    protected WebSpec spec;
    protected Record record;
    protected Find find;

    public WebSpecTestCase() {
        this.spec = new WebSpec();
        record = spec.record;
        find = spec.find;
    }

    public WebSpec open(String url) {
        return spec.open(url);
    }

    public WebSpec open(String url, String post) {
        return spec.open(url, post);
    }

    public String url() {
        return spec.url();
    }

    public WebSpec close() {
        return spec.close();
    }

    public WebSpec closeAll() {
        return spec.closeAll();
    }

    public WebSpec browser() {
        return spec.browser();
    }

    public WebSpec safari() {
        return spec.safari();
    }

    public WebSpec mozilla() {
        return spec.mozilla();
    }

    public WebSpec ie() {
        return spec.ie();
    }

    public boolean isSafari() {
        return spec.isSafari();
    }

    public boolean isMozilla() {
        return spec.isMozilla();
    }

    public boolean isIE() {
        return spec.isIE();
    }

    public WebSpec browser(int index) {
        return spec.browser(index);
    }

    public int browserCount() {
        return spec.browserCount();
    }

    public WebSpec timeout(long timeout) {
        return spec.timeout(timeout);
    }

    public Find find() {
        return spec.find();
    }

    public Tag find(String tag) {
        return spec.find(tag);
    }

    public Tag findWithId(String id) {
        return spec.findWithId(id);
    }

    public WebSpec pauseUntilReady() {
        return spec.pauseUntilReady();
    }

    public WebSpec pauseUntilDone() {
        return spec.pauseUntilDone();
    }

    public WebSpec pause(long milliseconds) {
        return spec.pause(milliseconds);
    }

    public WebSpec eval(String script) {
        return spec.eval(script);
    }

    public String execute(String script) {
        return spec.execute(script);
    }

    public boolean ready() {
        return spec.ready();
    }

    public boolean busy() {
        return spec.busy();
    }

    public boolean done() {
        return spec.done();
    }

    public WebSpec snap(String fileName) {
        return spec.snap(fileName);
    }

    public Tag jquery(String selector) {
        return spec.jquery(selector);
    }

    public String source() {
        return spec.source();
    }

    public void source(String html) {
        spec.source(html);
    }

    public boolean isVisible() {
        return spec.isVisible();
    }

    public WebSpec show() {
        return spec.show();
    }

    public WebSpec hide() {
        return spec.hide();
    }

    public Cookie cookie() {
        return spec.cookie();
    }
}
