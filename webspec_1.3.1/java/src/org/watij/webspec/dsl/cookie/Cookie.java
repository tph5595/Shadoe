package org.watij.webspec.dsl.cookie;

import com.teamdev.jxbrowser.BrowserType;
import com.teamdev.jxbrowser.cookie.HttpCookieStorage;
import org.watij.webspec.dsl.WebSpec;

import com.teamdev.jxbrowser.cookie.HttpCookie;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class Cookie {
    WebSpec spec;
    HttpCookieStorage cookieStorage;
    List<HttpCookie> cookies;
    public With with;
    public All all;
    public Get get;

    public Cookie(WebSpec spec) {
        this.spec = spec;
        this.with = new With(this);
        this.all = new All(this);
        this.get = new Get(this);
        cookieStorage = HttpCookieStorage.getInstance(browserType());
        cookies = cookieStorage.getCookies();
    }

    BrowserType browserType() {
        BrowserType browserType = BrowserType.IE;
        if (spec.isMozilla()) {
            browserType = BrowserType.Mozilla;
        } else if (spec.isSafari()) {
            browserType = BrowserType.Safari;
        }
        return browserType;
    }
    
    public Cookie at(int index) {
        List<HttpCookie> temp = new ArrayList();
        temp.add(cookies.get(index));
        cookies = temp;
        return this;
    }

    public Cookie delete() {
        cookieStorage.deleteCookie(cookies.get(0));
        cookies = cookieStorage.getCookies();
        return this;
    }

    public void create(String url, String name, String value, String domain, String path, long expires, boolean httpOnly, boolean secure, boolean sessionOnly) {
        WebSpec.debug("COOKIE CREATE:" + " url=" + url + " name=" + name + " value=" + value + " domain=" + domain + " path=" + path + " expires=" + expires + " httpOnly=" + httpOnly + " secure=" + secure + " sessionOnly=" + sessionOnly);
        cookieStorage.setCookie(new HttpCookie(name, value, domain, path, expires, httpOnly, secure, sessionOnly), url);
        cookies = cookieStorage.getCookies();
    }

    public With with() {
        return with;
    }

    public All all() {
        return all;
    }

    public Get get() {
        return get;
    }
}
