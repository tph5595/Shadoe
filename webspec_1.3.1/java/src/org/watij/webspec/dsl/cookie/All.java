package org.watij.webspec.dsl.cookie;

import com.teamdev.jxbrowser.cookie.HttpCookieStorage;
import org.watij.webspec.dsl.WebSpec;


public class All {
    Cookie cookie;

    public All(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie delete() {
        WebSpec.debug("ALL COOKIE DELETE");
        cookie.cookieStorage.deleteCookie(cookie.cookies);
        cookie.cookieStorage = HttpCookieStorage.getInstance(cookie.browserType());
        cookie.cookies = cookie.cookieStorage.getCookies();
        return cookie;
    }

    public int length() {
        int length = cookie.cookies.size();
        WebSpec.debug("ALL COOKIE LENGTH "+length);
        return length;
    }
}
