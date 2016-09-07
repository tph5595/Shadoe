package org.watij.webspec.dsl;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: brianknorr
 * Date: Apr 15, 2010
 * Time: 5:08:28 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Base {

    protected WebSpec spec;
    protected String listVar;
    protected long timeout = WebSpec.default_timeout;
    protected boolean resetTimeout = true;
    protected Method method;

    protected Base(WebSpec spec, String listVar, long timeout, Method method) {
        this.spec = spec;
        this.listVar = listVar;
        this.timeout = timeout;
        this.method = method;
    }

    protected Method method(String method) {
        return new Method(method, this.method);
    }

    protected Method method(String method, WebSpec spec) {
        return new Method(method, spec);
    }

    public Base timeout(long timeout) {
        this.timeout = timeout;
        resetTimeout = false;
        return this;
    }

    long timeout() {
        return timeout;
    }

    public Base pause(long milliseconds) {
        WebSpec.debug(".", true);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Base pauseUntilReady() {
        return pauseUntilReady(WebSpec.extra_careful_pause_until_ready);
    }

    public Base pauseUntilReady(boolean extra_careful) {
        if (extra_careful) {
            pauseUntilBusy();
            WebSpec.debug("PAUSE UNTIL READY");
            long start = new Date().getTime();
            while (new Date().getTime() - start  < timeout) {
                spec.pause(100);
                if (spec.ready()) {
                    spec.browser.waitReady();
                    break;
                }
            }
        } else {
            spec.browser.waitReady();
        }
        return this;
    }

    private void pauseUntilBusy() {
        WebSpec.debug("PAUSE UNTIL BUSY");
        long start = new Date().getTime();
        while (new Date().getTime() - start  < timeout) {
            if (spec.busy()) { break; }
            spec.pause(100);
        }
    }

    public Base pauseUntilDone() {
        spec.done = false;
        WebSpec.debug("PAUSE UNTIL DONE");
        while (true) {
            spec.pause(500);
            if (spec.done()) {
                break;
            }
        }
        return this;
    }

}
