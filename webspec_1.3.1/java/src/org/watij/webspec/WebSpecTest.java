package org.watij.webspec;

import org.watij.webspec.dsl.WebSpec;
import org.watij.webspec.dsl.Tag;

import java.io.File;

public class WebSpecTest extends WebSpecTestCase {
    static {
        WebSpec.debug = true;
        WebSpec.click_then_pause_until_ready = false;
        WebSpec.extra_careful_pause_until_ready = false;
        //WebSpec.webspec_home = "C:\\Dev\\WebSpec";
        WebSpec.webspec_home("Users/bknorr/Dev/WebSpec");
    }

    final static String testHtml = "file:///" + WebSpec.webspec_home + "/html/test.html";

    Tag result;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mozilla().open(testHtml);
        result = find.input().with.id("result");
        result.shouldExist();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        closeAll();
    }

    public void testLoginDialog() {
        record.login().set.username("ebookz").set.password("n2ptsd75f98ej").ok();
        open("http://ebookz.sign.sk/CS/PalmBookz_Mirror/");
    }

    public void testInvalidAttributes() {
        find.span().with("getAttribute('type')=='myinvalidattribute'").shouldExist();
    }

    public void testShowHide() {
        assertTrue(isVisible());
        hide();
        pause(500);
        assertFalse(isVisible());
        show();
        pause(500);
        assertTrue(isVisible());
    }

    public void testSource() {
        assertTrue(source().contains("<html>") || source().contains("<HTML>"));
        assertTrue(find.html().get.innerHTML().contains("<head>") || find.html().get.innerHTML().contains("<HEAD>"));

        open("about:blank");
        source("<html><head><title>My New Title</title></head><body><input type='text' value=''></body></html>");
        find.title("My New Title").shouldExist();
        find.input().set.value("New Html");
        find.input().shouldHave.value("New Html");
    }

    public void testFrames() {
        open("file:///" + WebSpec.webspec_home + "/html/topframe.html");
        Tag frame1 = findWithId("f1");
        Tag frame2 = findWithId("f2");
        frame1.find.title().shouldHave("I am inside frame 1");
        frame2.find.title().shouldHave("I am inside frame 2");
    }

    public void testCookies() {
        cookie().all.delete();
        assertEquals(0, cookie().all.length());
        cookie().create("http://www.watij.com/", "coolName", "coolValue", null, null, System.currentTimeMillis() + 3600000, false, false, false);
        assertEquals(1, cookie().all.length());
        assertEquals("coolName", cookie().get.name());
        assertEquals("coolValue", cookie().get.value());
        cookie().all.delete();
        assertEquals(0, cookie().all.length());
    }

    public void testOpenManyWindows() {
        assertTrue(browserCount() == 1);
        close();
        assertTrue(browserCount() == 0);
        for (int i = 0; i < 5; i++) {
            browser().open(testHtml);
        }
        assertTrue(browserCount() == 5);
        closeAll();
        assertTrue(browserCount() == 0);
        close();
        assertTrue(browserCount() == 0);
    }

    public void testPopupWindow() {
        find.a("/Pop/").click();

        browser(1);

        //System.out.println("browser count = "+browserCount());
        assertTrue(browserCount() == 2);
        Tag popupResult = findWithId("result");
        popupResult.shouldHave.value("empty");

        popupResult.set.value("I have set the popup");
        popupResult.shouldHave.value("I have set the popup");

        close();
        browser(0);

        assertTrue(browserCount() == 1);
    }

    public void testFileInput() {
        if (isIE()) {
            Tag inputFile = find.input().with.name("file_input");
            inputFile.shouldHave.value("");
            String fileToUpload = WebSpec.webspec_home + "\\autoit\\file_ok.exe";
            record.file().set(fileToUpload).cancel();
            inputFile.click();
            inputFile.shouldHave.value("");

            record.file().set(fileToUpload).ok();
            inputFile.click();
            inputFile.shouldHave.value(fileToUpload.replace("\\", "\\\\"));
        } else {
            fail("File input is only supported with IE on Windows");
        }
    }

    public void testShouldNotExist() {
        findWithId("not_here").shouldNotExist();
    }

    public void testDialogs() {
        /*Alert*/

        for (int i = 0; i < 3; i++) {
            record.alert().shouldHave("alert" + i).ok();
            find.a("alert" + i).click();
        }

        /*Confirm*/

        record.confirm().ok();
        find.a("confirm0").click();
        result.shouldHave.value("true");

        record.confirm().cancel();
        find.a("confirm0").click();
        result.shouldHave.value("false");


        /*Prompt*/
        record.prompt().set("myvalue0").ok();
        find.a("prompt0").click();
        result.shouldHave("value=='myvalue0'");

        record.prompt().set(null).ok();
        find("a").with("prompt0").click();
        result.shouldHave("value=='default0'");

        record.prompt().set("somevalue").cancel();
        find("a").with("prompt0").click();
        String resultValueCompare = "value=='somevalue'";

        if (isIE()) {
            resultValueCompare = "value=='null'";
        }
        
        result.shouldHave(resultValueCompare);
    }

    public void testButtons() {

        for (int i = 0; i < 3; i++) {
            find.input().with.type("button").with.name("button" + i).click();
            result.shouldHave.value("button" + i);
        }

        for (int i = 0; i < 3; i++) {
            find.input().with.type("button").at(i).click();
            result.shouldHave.value("button" + i);
        }

        Tag imageButton = find.input().with.type("image");
        imageButton.shouldExist();
        imageButton.click();
        result.shouldHave.value("image");
    }

    public void testTextFields() {

        Tag textField = find.input().with.name("textfield0");
        textField.set.value("textfield0");
        textField.call.onBlur();
        result.shouldHave.value("textfield0");
    }

    public void testCheckboxes() {
        Tag checkbox0 = find.input().with.name("checkbox0");
        checkbox0.shouldHave.checked(false);
        checkbox0.set.checked(true);
        checkbox0.shouldHave.checked(true);

        Tag checkbox1 = find.input().with.name("checkbox1");
        checkbox1.shouldHave.checked(true);
        checkbox1.click();
        checkbox1.shouldHave.checked(false);
        result.shouldHave.value("false");
        checkbox1.click();
        checkbox1.shouldHave.checked(true);
        result.shouldHave.value("true");
    }

    public void testRadios() {
        Tag radio0 = find.input().with.name("radio").at(0);
        Tag radio1 = find.input().with.name("radio").at(1);

        radio0.shouldHave.value("radio0").shouldHave.checked(true);
        radio1.shouldHave.value("radio1").shouldHave.checked(false);

        radio1.click();
        radio1.shouldHave("checked==true");
        radio0.shouldHave("checked==false");
        result.shouldHave("value=='radio1'");

        radio0.click();
        radio0.shouldHave("checked==true");
        radio1.shouldHave("checked==false");
        result.shouldHave("value=='radio0'");
    }

    public void testSelects() {
        Tag select = find.select();
        Tag option0 = find.option(0);
        Tag option1 = find.option(1);
        Tag option2 = find.option(2);

        select.shouldHave("value=='select0'");
        option0.shouldHave("selected==true");
        option0.shouldHave("option0");
        option1.shouldHave("selected==false");
        option1.shouldHave("option1");
        option2.shouldHave("selected==false");
        option2.shouldHave("option2");

        option1.set("selected=true");
        option0.shouldHave("selected==false");
        option1.shouldHave("selected==true");
        option2.shouldHave("selected==false");
        select.shouldHave("value=='select1'");
        select.call.onChange();
        result.shouldHave("value=='select1'");

        select.set("value='select2'");
        option0.shouldHave("selected==false");
        option1.shouldHave("selected==false");
        option2.shouldHave("selected==true");
        select.shouldHave("value=='select2'");
        select.call.onChange();
        result.shouldHave("value=='select2'");
    }

    public void testSnap() {
        if (!isSafari()) {
            File file = new File("snap_webspec.png");
            assertFalse(file.exists());
            snap("snap_webspec.png");
            file = new File("snap_webspec.png");
            assertTrue(file.exists());
            file.delete();
        }
    }

    public void testIframe() {
        find.iframe().find.title().shouldHave("I am an iframe");
    }

    public void testTables() {
        Tag outTable = find.table();
        Tag inTable = outTable.find.table();

        outTable.find.tr().all.shouldHave(6);
        outTable.child.tbody().child.tr().all.shouldHave(4);
        inTable.find.tr().all.shouldHave(2);
        inTable.child.tbody().child.tr().all.shouldHave(2);

        for (int i = 0; i < 3; i++) {
            Tag tr = outTable.find.tr(i);
            for (int j = 0; j <= i; j++) {
                Tag td = tr.find.td(j);
                td.shouldHave("out-r" + i + "-c" + j);
                td.parent.table().all.shouldHave(1);
            }
        }

        for (int i = 0; i < 2; i++) {
            Tag tr = inTable.find.tr(i);
            for (int j = 0; j <= i; j++) {
                Tag td = tr.find.td(j);
                td.shouldHave("in-r" + i + "-c" + j);
                td.parent.table().all.shouldHave(2);
            }
        }
    }
}
