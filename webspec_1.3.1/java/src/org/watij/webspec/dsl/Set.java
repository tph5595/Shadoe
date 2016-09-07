package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 10:38:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Set extends Property {

    public Set(Tag tag) {
        super(tag);
    }

    @Override
    public Tag property(String property, String value) {
        return tag.set(property + "='" + value + "'");
    }

    @Override
    public Tag property(String property, Number value) {
        return tag.set(property + "=" + value);
    }

    @Override
    public Tag property(String property, boolean value) {
        return tag.set(property + "=" + value);
    }
}
