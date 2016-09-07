package org.watij.webspec.dsl;

import com.teamdev.jxbrowser.*;
import com.teamdev.jxbrowser.events.*;
import com.teamdev.jxbrowser.script.ScriptErrorEvent;
import com.teamdev.jxbrowser.script.ScriptErrorListener;
import com.teamdev.jxbrowser.security.*;
import com.teamdev.xpcom.*;
import org.watij.webspec.dsl.cookie.Cookie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class WebSpec extends Base implements Finder, NavigationListener, DisposeListener, HistoryChangeListener, ProgressListener, StatusListener, TitleListener, WebPolicyDelegate, ScriptErrorListener {

    public static boolean click_then_pause_until_ready = true;
    public static long default_timeout = 10000;
    public static int window_width = 1200;
    public static int window_height = 800;
    public static boolean debug = false;
    public static String http_proxy = null;
    public static int http_proxy_port = 80;
    public static String http_proxy_username = null;
    public static String http_proxy_password = null;
    public static boolean silent_mode = false;
    public static boolean extra_careful_pause_until_ready = true;
    public static boolean show_navigation_bar = true;
    public static String file_window_title_for_ie = "Choose File to Upload";
    public static String file_window_title_for_mozilla = "File Upload";
    public static String prompt_window_title_for_ie = "Explorer User Prompt";
    public static String webspec_home = System.getProperty("webspec_home");

    Browser browser;
    private static long count = 0;
    private long lastStatusChange;
    private boolean busy = false;
    boolean done = false;
    public Record record;
    public Find find;

    private ArrayList<Browser> browsers;

    public WebSpec() {
        super(null, null, default_timeout, null);
        spec = this;
        record = new Record(this);
        org.watij.webspec.dsl.dialog.Dialog.Handler handler = new org.watij.webspec.dsl.dialog.Dialog.Handler();
        record.handler(handler);
        find = new Find(this);
        browsers = new ArrayList();
    }

    //These are for ease of use in JRuby
    public static void click_then_pause_until_ready(boolean click_then_pause_until_ready){WebSpec.click_then_pause_until_ready = click_then_pause_until_ready;}
    public static void default_timeout(long default_timeout){WebSpec.default_timeout = default_timeout;}
    public static void window_width(int window_width){WebSpec.window_width = window_width;}
    public static void window_height(int window_height){WebSpec.window_height = window_height;}
    public static void debug(boolean debug){WebSpec.debug = debug;}
    public static void http_proxy(String http_proxy){WebSpec.http_proxy = http_proxy;}
    public static void http_proxy_port(int http_proxy_port){WebSpec.http_proxy_port = http_proxy_port;}
    public static void http_proxy_username(String http_proxy_username){WebSpec.http_proxy_username = http_proxy_username;}
    public static void http_proxy_password(String http_proxy_password){WebSpec.http_proxy_password = http_proxy_password;}
    public static void silent_mode(boolean silent_mode){WebSpec.silent_mode = silent_mode;}
    public static void extra_careful_pause_until_ready(boolean extra_careful_pause_until_ready){WebSpec.extra_careful_pause_until_ready = extra_careful_pause_until_ready;}
    public static void show_navigation_bar(boolean show_navigation_bar){WebSpec.show_navigation_bar = show_navigation_bar;}
    public static void file_window_title_for_ie(String file_window_title_for_ie){WebSpec.file_window_title_for_ie = file_window_title_for_ie;}
    public static void file_window_title_for_mozilla(String file_window_title_for_mozilla){WebSpec.file_window_title_for_mozilla = file_window_title_for_mozilla;}
    public static void prompt_window_title_for_ie(String prompt_window_title_for_ie){WebSpec.prompt_window_title_for_ie = prompt_window_title_for_ie;}
    public static void webspec_home(String webspec_home){WebSpec.webspec_home = webspec_home;}

    private static synchronized void initListeners(Browser browser, final WebSpec spec) {
        browser.addNavigationListener(spec);
//        browser.addDisposeListener(this);
//        browser.addHistoryChangeListener(this);
//        browser.addProgressListener(this);
        browser.addStatusListener(spec);
//        browser.addTitleListener(this);
        browser.getServices().setNewWindowManager(new NewWindowManager() {
            public NewWindowContainer evaluateWindow(NewWindowParams params) {
                return new MyWindowContainer(params, spec);
            }
        });

        browser.getServices().setPromptService(spec.record.handler);

        if (http_proxy != null) {
            Xpcom.initialize();
            ProxyConfiguration proxyConf = Services.getProxyConfiguration();
            proxyConf.setType(ProxyConfiguration.MANUAL);
            proxyConf.setHttpHost(http_proxy);
            proxyConf.setHttpPort(http_proxy_port);
            proxyConf.setPoxyAuthenticationHandler(ProxyServerType.HTTP, new PoxyAuthenticationHandler() {
                public ProxyServerAuthInfo authenticationRequired() {
                    return new ProxyServerAuthInfo(http_proxy_username, http_proxy_password);
                }
            });
        }
//        browser.getServices().setWebPolicyDelegate(this);
//        browser.getServices().getScriptErrorWatcher().addScriptErrorListener(this);

        browser.setHttpSecurityHandler(new HttpSecurityHandler() {
            public HttpSecurityAction onSecurityProblem(java.util.Set<SecurityProblem> securityProblems) {
                return HttpSecurityAction.CONTINUE;
            }
        });
    }

    public WebSpec open(String url) {
        return open(url, null);
    }

    public WebSpec open(String url, String post) {
        checkBrowser();
        checkResetTimeout();
        if (post == null) {
            browser.navigate(url);
        } else {
            browser.navigate(url, post);
        }
        pauseUntilReady();
        return this;
    }

    public Cookie cookie() {
        return new Cookie(this);
    }

    public String source() {
        return browser.getContent();
    }

    public void source(String html) {
        source(html,true);
    }

    public void source(String html, boolean pause_until_ready) {
        browser.setContent(html);
        if (pause_until_ready) {
            pauseUntilReady();
        }
    }

    public String url() {
        return browser.getCurrentLocation();
    }

    public WebSpec close() {
        removeBrowser(browser);
        if (browser != null && !browser.isDisposed()) {
            browser.dispose();
        }
        return this;
    }

    public WebSpec closeAll() {
        for (Browser browser : browsers) {
            if (!browser.isDisposed()) {
                browser.dispose();
            }
        }
        browsers.clear();
        return this;
    }

    public boolean isVisible() {
        return SwingUtilities.windowForComponent(browser.getComponent()).isVisible();
    }

    public WebSpec show() {
        SwingUtilities.windowForComponent(browser.getComponent()).setVisible(true);
        return this;
    }

    public WebSpec hide() {
        SwingUtilities.windowForComponent(browser.getComponent()).setVisible(false);
        return this;
    }

    private void checkBrowser() {
        if (browser == null) {
            browser();
        }
    }

    private static synchronized void addNavigationBarIfNeeded(JFrame frame, final WebSpec spec) {

        if (show_navigation_bar) {
            final JTextField textField = new JTextField(30);
            ActionListener go = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    spec.open(textField.getText());
                }
            };

            textField.addActionListener(go);
            final JButton goButton = new JButton("Go");
            goButton.addActionListener(go);
            final JButton doneButton = new JButton("Done");
            doneButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    spec.done = true;
                }
            });
            JLabel sep = new JLabel("  |  ");
            JLabel urlLabel = new JLabel("Url:");
            JPanel panel = new JPanel();
            panel.add(urlLabel);
            panel.add(textField);
            panel.add(goButton);
            panel.add(sep);
            panel.add(new JLabel("pauseUntil"));
            panel.add(doneButton);
            frame.add(panel, BorderLayout.NORTH);
        }
    }

    private WebSpec initBrowser(Browser browser) {
        this.browser = browser;
        browsers.add(this.browser);

        String title = "WebSpec - Internet Explorer";
        if (isSafari()) {
            title = "WebSpec - Safari";
        } else if (isMozilla()) {
            title = "WebSpec - Mozilla";
        }

        final JFrame frame = new JFrame(title);
        frame.add(browser.getComponent(), BorderLayout.CENTER);
        addNavigationBarIfNeeded(frame,this);
        frame.setSize(window_width, window_height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(!silent_mode);
        browser.addDisposeListener(new DisposeListener() {
            public void browserDisposed(DisposeEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        frame.dispose();
                    }
                });
            }
        });
        initListeners(browser, this);
        return this;
    }

    public WebSpec browser() {
        return initBrowser(BrowserFactory.createBrowser());
    }

    public WebSpec safari() {
        return initBrowser(BrowserFactory.createBrowser(BrowserType.Safari));
    }

    public WebSpec mozilla() {
        return initBrowser(BrowserFactory.createBrowser(BrowserType.Mozilla));
    }

    public WebSpec ie() {
        return initBrowser(BrowserFactory.createBrowser(BrowserType.IE));
    }

    public boolean isSafari() {
        return browser.getType() == BrowserType.Safari;
    }

    public boolean isMozilla() {
        return browser.getType() == BrowserType.Mozilla;
    }

    public boolean isIE() {
        return browser.getType() == BrowserType.IE;
    }

    public WebSpec browser(int index) {
        long start = new Date().getTime();
        while (new Date().getTime() - start < timeout) {
            spec.pause(100);
            if (browsers.size() - 1 >= index) {
                break;
            }
        }
        return browser(browsers.get(index));
    }

    public int browserCount() {
        return browsers.size();
    }

    private WebSpec browser(Browser browser) {
        this.browser = browser;
        return this;
    }

    private synchronized void addBrowser(Browser browser) {
        browsers.add(browser);
    }

    private synchronized void removeBrowser(Browser browser) {
        browsers.remove(browser);
    }

    @Override
    public WebSpec timeout(long timeout) {
        return (WebSpec) super.timeout(timeout);
    }

    static synchronized String nextListVar() {
        return "watix" + count++;
    }

    public Find find() {
        return find;
    }

    public Tag find(String tag) {
        beforeFind();
        checkResetTimeout();
        pause(100);

        String listVar = nextListVar();
        eval(
                listVar + " = [document.documentElement];"
        );
        Tag documentElement = new Tag(this, listVar, timeout, method("find", this).param(tag));
        if ("html".equalsIgnoreCase(tag)) {
            return documentElement;
        }
        return documentElement.find(tag);
    }

    public Tag findWithId(String id) {
        beforeFind();
        debug("FINDWITHID ", true);
        checkResetTimeout();
        pause(100);

        String listVar = nextListVar();
        eval(
                "watixTemp = document.getElementById('" + id + "');" +
                        listVar + " = (watixTemp == null) ? [] : [watixTemp];"
        );

        return new Tag(this, listVar, timeout, method("findWithId", this).param(id));
    }

    public Tag jquery(String selector) {
        beforeFind();
        debug("JQUERY ", true);
        checkResetTimeout();
        if (!"function".equals(execute("typeof jQuery"))) {
            execute(
                    "var body = document.getElementsByTagName('body').item(0);" +
                            "script = document.createElement('script');" +
                            "script.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js';" +
                            "script.type = 'text/javascript';" +
                            "body.appendChild(script);"
            );
            long start = new Date().getTime();
            while (new Date().getTime() - start < timeout) {
                pause(200);
                if ("function".equals(execute("typeof jQuery"))) {
                    break;
                }
            }
        }
        debug("jquery loaded = " + "function".equals(execute("typeof jQuery")));
        if (!"function".equals(execute("typeof jQuery"))) {
            throw new RuntimeException("JQuery failed to load.  Make sure you are connected to the internet.");
        }
        String listVar = nextListVar();
        execute(listVar + " = jQuery.makeArray(jQuery(\"" + selector + "\"));");
        return new Tag(this, listVar, timeout, method("jquery", this).param(selector));
    }

    private void beforeFind() {
        if (isMozilla()) {
            execute("Object.prototype.__defineGetter__(\"innerText\", function() {return this.textContent});");
        }
    }

    private void checkResetTimeout() {
        if (resetTimeout) {
            timeout = default_timeout;
        }
        resetTimeout = true;
    }

    @Override
    public WebSpec pauseUntilReady() {
        return (WebSpec) super.pauseUntilReady();
    }

    @Override
    public WebSpec pauseUntilReady(boolean extra_careful) {
        return (WebSpec)super.pauseUntilReady(extra_careful);
    }

    @Override
    public WebSpec pauseUntilDone() {
        return (WebSpec)super.pauseUntilDone();
    }

    public WebSpec pause(long milliseconds) {
        return (WebSpec) super.pause(milliseconds);
    }

    public WebSpec eval(String script) {
        execute(script);
        return this;
    }

    public String execute(String script) {
        String result = browser.executeScript(script);
        //       pause(25);
        return result;
    }

    public boolean ready() {
        long dur = new Date().getTime() - lastStatusChange;
        debug(dur + " ", true);
        if (dur > 2000) {
            //Safari looks at busy, Mozilla only looks at duration
            if (browser.getType() == BrowserType.Safari && busy == true) {
                return false;
            }
            busy = false;
            debug("IS READY");
            return true;
        }
        return false;
    }

    public boolean busy() {
        debug(busy + " ", true);
        if (busy) {
            debug("IS BUSY");
        }
        return busy;
    }

    public boolean done() {
        return done;
    }

    public WebSpec snap(String fileName) {
        return snap(fileName,false);
    }

    public WebSpec snapAll(String fileName) {
        return snap(fileName,true);
    }

    private WebSpec snap(String fileName, boolean all) {
        BufferedImage image = (BufferedImage) browser.toImage(all);
        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static void debug(String string) {
        debug(string, false);
    }

    static void debug(String string, boolean sameLine) {
        if (debug) {
            if (sameLine) {
                System.out.print(string);
            } else {
                System.out.println(string);
            }
        }
    }


    //Events

    public void browserDisposed(DisposeEvent disposeEvent) {
        //debug("browserDisposed");
    }

    public void navigationStarted(NavigationEvent navigationEvent) {
        debug("NAVIGATION STARTED => "+navigationEvent.getUrl());
        Component[] components = ((JPanel)navigationEvent.getBrowser().getComponent().getParent()).getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                Component[] myComponents = ((JPanel)component).getComponents();
                for (Component myComponent : myComponents) {
                    if (myComponent instanceof JTextField) {
                        ((JTextField)myComponent).setText(navigationEvent.getUrl());
                    }
                }
            }
        }
        busy = true;
        //System.out.print("NAVIGATION STARTED url = " + navigationEvent.getUrl() + " frame = " + navigationEvent.getFrameName() +"...");
    }

    public void navigationFinished(NavigationFinishedEvent navigationFinishedEvent) {
        debug("NAVIGATION FINISHED");
        //Safari looks at busy, Mozilla only looks at duration
        if (browser.getType() == BrowserType.Safari) {
            busy = false;
        }
        //System.out.print("NAVIGATION ENDED...");
    }

    public void historyChanged(HistoryChangeEvent historyChangeEvent) {
        //debug("***historyChanged");
    }

    public void titleChanged(TitleChangedEvent titleChangedEvent) {
        //debug("titleChanged");
    }

    public void statusChanged(StatusChangedEvent statusChangedEvent) {
        lastStatusChange = new Date().getTime();
    }

    public void progressChanged(ProgressChangedEvent progressChangedEvent) {
        //debug("progressChanged " + progressChangedEvent.getCurrentProgress());
    }

    public boolean allowNavigation(NavigationEvent navigationEvent) {
        ////debug("allowNavigation url = " + navigationEvent.getUrl() + " frame = " + navigationEvent.getFrameName());
        return true;
    }

    public boolean allowMimeType(String s, NavigationEvent navigationEvent) {
        //debug("allowMimeType");
        return true;
    }

    public void scriptErrorHappened(ScriptErrorEvent scriptErrorEvent) {
        ////debug("scriptErrorHappened message = " + scriptErrorEvent.getMessage() + " type = " + scriptErrorEvent.getType());
    }

    public Record record() {
        return record;
    }

    private static class MyWindowContainer extends VisualWindowContainer {
        private JFrame popupFrame;
        private final NewWindowParams windowParams;
        private WebSpec spec;
        private Browser browser;

        public MyWindowContainer(NewWindowParams windowParams, WebSpec spec) {
            this.windowParams = windowParams;
            this.spec = spec;
        }

        public void insertBrowser(final Browser browser) {
            this.browser = browser;
            spec.addBrowser(browser);

            String title = "WebSpec - Internet Explorer";
            if (spec.isSafari()) {
                title = "WebSpec - Safari";
            } else if (spec.isMozilla()) {
                title = "WebSpec - Mozilla";
            }
            // creates JFrame in which browser will be embedded
            popupFrame = new JFrame(title);
            popupFrame.getContentPane().add(browser.getComponent(), BorderLayout.CENTER);
            // sets window bounds using popup window params
            popupFrame.setSize(windowParams.getBounds().getSize());
            // registers window listener to dispose Browser instance on window close
            popupFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    browser.dispose();
                }
            });


            // registers Browser dispose listener to dispose window when Browser
            // is disposed (e.g. using the window.close JavaScript function)
            browser.addDisposeListener(new DisposeListener() {
                public void browserDisposed(DisposeEvent event) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            popupFrame.dispose();
                        }
                    });
                }
            });

            initListeners(browser, spec);
            addNavigationBarIfNeeded(popupFrame, spec);
        }

        @Override
        public void setVisible(boolean visible) {
            popupFrame.setVisible(!silent_mode);
        }

        @Override
        public void setLocation(Point location) {
            popupFrame.setLocation(location);
        }

        @Override
        public void setSize(Dimension size) {
            popupFrame.setSize(size);
        }
    }

}
