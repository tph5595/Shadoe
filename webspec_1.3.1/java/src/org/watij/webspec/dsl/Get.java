package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 11:23:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Get {

    Tag tag;

    public Get(Tag tag) {
        this.tag = tag;
    }

    public String property(String property) {
        return tag.get(property);
    }

    public String innerText() {
        return property("innerText");
    }

    public String innerHTML() {
        return property("innerHTML");
    }

    public String className() {
        return property("className");
    }

    public boolean disabled() {
        return Boolean.valueOf(property("disabled"));
    }

    public String name() {
        return property("name");
    }

    public String value() {
        return property("value");
    }

    public String tagName() {
        return property("tagName");
    }

    public String id() {
        return property("id");
    }

    public String title() {
        return property("title");
    }

    public int height() {
        return Integer.valueOf(property("height"));
    }

    public int width() {
        return Integer.valueOf(property("width"));
    }
}
