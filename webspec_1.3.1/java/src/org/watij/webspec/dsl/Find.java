package org.watij.webspec.dsl;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: Nov 29, 2010
 * Time: 9:55:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Find {

    Finder finder;

    protected Find(Finder finder) {
        this.finder = finder;
    }

    public Tag tag(String tagName) {
        return finder.find(tagName);
    }

    public Tag form() {
        return tag("form");
    }

    public Tag form(int at) {
        return form().at(at);
    }

    public Tag input() {
        return tag("input");
    }

    public Tag input(int at) {
        return input().at(at);
    }

    public Tag a() {
        return tag("a");
    }

    public Tag a(String with) {
        return a().with(with);
    }

    public Tag a(int at) {
        return a().at(at);
    }

    public Tag dd() {
        return tag("dd");
    }

    public Tag dd(String with) {
        return dd().with(with);
    }

    public Tag dd(int at) {
        return dd().at(at);
    }

    public Tag dl() {
        return tag("dl");
    }

    public Tag dl(int at) {
        return dl().at(at);
    }

    public Tag dt() {
        return tag("dt");
    }

    public Tag dt(String with) {
        return dt().with(with);
    }

    public Tag dt(int at) {
        return dt().at(at);
    }

    public Tag fieldset() {
        return tag("fieldset");
    }

    public Tag fieldset(int at) {
        return fieldset().at(at);
    }

    public Tag frame() {
        return tag("frame");
    }

    public Tag frame(int at) {
        return frame().at(at);
    }

    public Tag frameset() {
        return tag("frameset");
    }

    public Tag frameset(int at) {
        return frameset().at(at);
    }

    public Tag option() {
        return tag("option");
    }

    public Tag option(String with) {
        return option().with(with);
    }

    public Tag option(int at) {
        return option().at(at);
    }

    public Tag pre() {
        return tag("pre");
    }

    public Tag pre(int at) {
        return pre().at(at);
    }

    public Tag iframe() {
        return tag("iframe");
    }

    public Tag iframe(int at) {
        return iframe().at(at);
    }

    public Tag img() {
        return tag("img");
    }

    public Tag img(int at) {
        return img().at(at);
    }

    public Tag textArea() {
        return tag("textarea");
    }

    public Tag textArea(int at) {
        return textArea().at(at);
    }

    public Tag button() {
        return tag("button");
    }

    public Tag button(String with) {
        return button().with(with);
    }

    public Tag button(int at) {
        return button().at(at);
    }

    public Tag label() {
        return tag("label");
    }

    public Tag label(String with) {
        return label().with(with);
    }

    public Tag label(int at) {
        return label().at(at);
    }

    public Tag h1() {
        return tag("h1");
    }

    public Tag h1(String with) {
        return h1().with(with);
    }

    public Tag h1(int at) {
        return h1().at(at);
    }

    public Tag h2() {
        return tag("h2");
    }

    public Tag h2(String with) {
        return h2().with(with);
    }

    public Tag h2(int at) {
        return h2().at(at);
    }

    public Tag h3() {
        return tag("h3");
    }

    public Tag h3(String with) {
        return h3().with(with);
    }

    public Tag h3(int at) {
        return h3().at(at);
    }

    public Tag p() {
        return tag("p");
    }

    public Tag p(String with) {
        return p().with(with);
    }

    public Tag p(int at) {
        return p().at(at);
    }

    public Tag table() {
        return tag("table");
    }

    public Tag table(int at) {
        return table().at(at);
    }

    public Tag tr() {
        return tag("tr");
    }

    public Tag tr(int at) {
        return tr().at(at);
    }

    public Tag td() {
        return tag("td");
    }

    public Tag td(String with) {
        return td().with(with);
    }

    public Tag td(int at) {
        return td().at(at);
    }

    public Tag thead() {
        return tag("thead");
    }

    public Tag thead(int at) {
        return thead().at(at);
    }

    public Tag th() {
        return tag("th");
    }

    public Tag th(String with) {
        return th().with(with);
    }

    public Tag th(int at) {
        return th().at(at);
    }

    public Tag tfoot() {
        return tag("tfoot");
    }

    public Tag tfoot(int at) {
        return tfoot().at(at);
    }

    public Tag div() {
        return tag("div");
    }

    public Tag div(String with) {
        return div().with(with);
    }

    public Tag div(int at) {
        return div().at(at);
    }

    public Tag span() {
        return tag("span");
    }

    public Tag span(String with) {
        return span().with(with);
    }

    public Tag span(int at) {
        return span().at(at);
    }

    public Tag select() {
        return tag("select");
    }

    public Tag select(int at) {
        return select().at(at);
    }

    public Tag tbody() {
        return tag("tbody");
    }

    public Tag tbody(int at) {
        return tbody().at(at);
    }

    public Tag html() {
        return tag("html");
    }

    public Tag html(String with) {
        return html().with(with);
    }

    public Tag title() {
        return tag("title");
    }

    public Tag title(String with) {
        return title().with(with);
    }

    public Tag head() {
        return tag("head");
    }

    public Tag body() {
        return tag("body");
    }

    public Tag body(String with) {
        return body().with(with);
    }

    public Tag ul() {
        return tag("ul");
    }

    public Tag ul(int at) {
        return ul().at(at);
    }

    public Tag li() {
        return tag("li");
    }

    public Tag li(String with) {
        return li().with(with);
    }

    public Tag li(int at) {
        return li().at(at);
    }
}
