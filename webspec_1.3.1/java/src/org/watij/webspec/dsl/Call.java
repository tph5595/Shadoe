package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 11:19:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Call {

    Tag tag;

    public Call(Tag tag) {
        this.tag = tag;
    }

    public Tag onChange() {
        tag.call("onchange()");
        return tag;
    }

    public Tag onClick() {
        tag.call("onclick()");
        return tag;
    }

    public Tag onDblClick() {
        tag.call("ondblclick()");
        return tag;
    }

    public Tag onFocus() {
        tag.call("onfocus()");
        return tag;
    }

    public Tag onBlur() {
        tag.call("onblur()");
        return tag;
    }

    public Tag onKeyDown() {
        tag.call("onkeydown()");
        return tag;
    }

    public Tag onKeyUp() {
        tag.call("onkeyup()");
        return tag;
    }

    public Tag onKeyPress() {
        tag.call("onkeypress()");
        return tag;
    }

    public Tag onMouseDown() {
        tag.call("onmousedown()");
        return tag;
    }

    public Tag onMouseMove() {
        tag.call("onmousemove()");
        return tag;
    }

    public Tag onMouseOut() {
        tag.call("onmouseout()");
        return tag;
    }

    public Tag onMouseOver() {
        tag.call("onmouseover()");
        return tag;
    }

    public Tag onMouseUp() {
        tag.call("onmouseup()");
        return tag;
    }

    public Tag onResize() {
        tag.call("onresize()");
        return tag;
    }
}
