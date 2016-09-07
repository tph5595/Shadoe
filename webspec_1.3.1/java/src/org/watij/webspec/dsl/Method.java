package org.watij.webspec.dsl;

import java.util.ArrayList;

public class Method {

    String method;
    ArrayList<Class> types;
    ArrayList<Object> params;
    Method parent;
    WebSpec spec;

    public Method(String method, Method parent) {
        this.method = method;
        this.parent = parent;
        types = new ArrayList();
        params = new ArrayList();
    }

    public Method(String method, WebSpec spec) {
        this(method, (Method)null);
        this.spec = spec;
    }

    public Method param(String string) {
        types.add(String.class);
        params.add(string);
        return this;
    }

    public Method param(int intValue) {
        types.add(int.class);
        params.add(intValue);
        return this;
    }

    public Method param(long longValue) {
        types.add(long.class);
        params.add(longValue);
        return this;
    }

    public Object redo() {
        if (spec != null) {
            spec.timeout(0);
            return invoke(spec);
        }
        return invoke(parent.redo());
    }

    public Object invoke(Object target) {
        WebSpec.debug("invoke on target = " + target + " method = "+ method +" params = " + params);
        try {
            return target.getClass().getMethod(method, types.toArray(new Class[types.size()])).invoke(target, params.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Expectation Failed.  Exception = "+e.getMessage());
        }
    }
}
