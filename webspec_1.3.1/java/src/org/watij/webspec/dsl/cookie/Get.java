package org.watij.webspec.dsl.cookie;

public class Get {

    Cookie cookie;

    public Get(Cookie cookie) {
        this.cookie = cookie;
    }

    public String name() {
        return cookie.cookies.get(0).getName();
    }

    public String value() {
        return cookie.cookies.get(0).getValue();
    }

    public String domain() {
        return cookie.cookies.get(0).getDomain();
    }

    public String path() {
        return cookie.cookies.get(0).getPath();
    }

    public long expires() {
        return cookie.cookies.get(0).getExpiresDate();
    }

    public boolean httpOnly() {
       return cookie.cookies.get(0).isHTTPOnly();
    }

    public boolean secure() {
       return cookie.cookies.get(0).isSecure();
    }

    public boolean sessionOnly() {
       return cookie.cookies.get(0).isSessionOnly(); 
    }
}
