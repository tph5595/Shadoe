package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 10:42:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShouldHave extends Property {

    public ShouldHave(Tag tag) {
        super(tag);
    }

    @Override
    public Tag property(String property, String value) {
        return tag.shouldHave(property + tag.useEqualOrSearch(value));
    }

    @Override
    public Tag property(String property, Number value) {
        return tag.shouldHave(property + "==" + value);
    }

    @Override
    public Tag property(String property, boolean value) {
        return tag.shouldHave(property + "==" + value);
    }
}
