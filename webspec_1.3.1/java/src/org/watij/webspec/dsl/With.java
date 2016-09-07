package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 10:33:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class With extends Property {
    
    public With(Tag tag) {
        super(tag);
    }

    public Tag property(String property, String value) {
        return tag.with(property + tag.useEqualOrSearch(value));
    }

    public Tag property(String property, Number value) {
        return tag.with(property + "==" + value);
    }

    public Tag property(String property, boolean value) {
        return tag.with(property + "==" + value);
    }

}
