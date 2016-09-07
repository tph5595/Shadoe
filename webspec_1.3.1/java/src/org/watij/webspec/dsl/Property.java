package org.watij.webspec.dsl;

import org.watij.webspec.dsl.Tag;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 10:51:14 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Property {

    protected Tag tag;

    public Property(Tag tag) {
        this.tag = tag;
    }

    public abstract Tag property(String property, String value);
    public abstract Tag property(String property, Number value);
    public abstract Tag property(String property, boolean value);

    public Tag type(String value) {
        return property("type", value);
    }

    public Tag name(String value) {
        return property("name", value);
    }

    public Tag className(String value) {
        return property("className", value);
    }

    public Tag title(String value) {
        return property("title", value);
    }

    public Tag id(String value) {
        return property("id", value);
    }

    public Tag value(String value) {
        return property("value", value);
    }

    public Tag innerText(String value) {
        return property("innerText", value);
    }

    public Tag checked(boolean value) {
        return property("checked", value);
    }

    public Tag href(String value) {
        return property("href", value);
    }

    public Tag src(String value) {
        return property("src", value);
    }
}
