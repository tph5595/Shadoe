package org.watij.webspec.dsl.cookie;

import com.teamdev.jxbrowser.cookie.HttpCookie;

import java.util.ArrayList;
import java.util.List;


public class With {

    Cookie cookie;

    public With(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie domain(final String domain) {
        return doWith(new CookieCompare() {
            public boolean equals(HttpCookie httpCookie) {
                return domain.equals(httpCookie.getDomain());
            }
        });
    }

    public Cookie name(final String name) {
        return doWith(new CookieCompare() {
            public boolean equals(HttpCookie httpCookie) {
                return name.equals(httpCookie.getName());
            }
        });
    }

    public Cookie value(final String value) {
        return doWith(new CookieCompare() {
            public boolean equals(HttpCookie httpCookie) {
                return value.equals(httpCookie.getValue());
            }
        });
    }

    private Cookie doWith(CookieCompare cookieCompare) {
        List<HttpCookie> temp = new ArrayList();
        for (HttpCookie httpCookie : cookie.cookies) {
            if (cookieCompare.equals(httpCookie)) {
                temp.add(httpCookie);
            }
        }
        cookie.cookies = temp;
        return cookie;
    }


    private static abstract class CookieCompare {
        public abstract boolean equals(HttpCookie httpCookie);
    }
}
