package org.watij.webspec.dsl;


public class All {

    Tag tag;

    public All(Tag tag) {
        this.tag = tag;
    }

    public int length() {
        return tag.length();
    }

    public Tag call(String call) {
        int length = length();
        for (int i=0; i< length; i++) {
            tag.at(i).call(call);
        }
        return tag;
    }

    public Tag set(String set) {
        int length = length();
        for (int i=0; i< length; i++) {
            tag.at(i).set(set);
        }
        return tag;
    }

    public Tag shouldHave(String expectation) {
        int length = length();
        for (int i=0; i< length; i++) {
            tag.at(i).shouldHave(expectation);
        }
        return tag;
    }

    public Tag shouldHave(int length) {
        return tag.shouldHave(this.length() == length, ""+length);
    }

    public Tag click() {
        return click(WebSpec.click_then_pause_until_ready);
    }

    public Tag click(boolean pauseUntilReady) {
        int length = length();
        for (int i=0; i< length; i++) {
            tag.at(i).click(pauseUntilReady);
        }
        return tag;
    }

    public Tag flash() {
        int length = length();
        for (int i=0; i< length; i++) {
            tag.at(i).flash();
        }
        return tag;
    }
}
