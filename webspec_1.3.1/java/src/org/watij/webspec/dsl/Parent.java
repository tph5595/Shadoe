package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 10:15:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Parent extends Find {

    protected Parent(Tag tag) {
        super(tag);
    }

    public Tag tag(String tagName) {
        return ((Tag)finder).parent(tagName);
    }
}
