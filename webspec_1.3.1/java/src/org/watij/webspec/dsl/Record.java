package org.watij.webspec.dsl;

import org.watij.webspec.dsl.dialog.*;


public class Record {

    Dialog.Handler handler;
    private File file;
    private Alert alert;
    private Confirm confirm;
    private Prompt prompt;
    private Login login;
    
    private WebSpec spec;

    public Record(WebSpec spec) {
        this.spec = spec;
    }

    void handler(Dialog.Handler handler) {
        this.handler = handler;
    }

    public File file() {
        if (file == null) {
            file = new File(spec);
        }
        handler.dialog(file);
        return file;
    }
    
    public Alert alert() {
        if (alert == null) {
            alert = new Alert(spec);
        }
        handler.dialog(alert);
        return alert;
    }
    
    public Confirm confirm() {
        if (confirm == null) {
            confirm = new Confirm(spec);
        }
        handler.dialog(confirm);
        return confirm;
    }
    
    public Prompt prompt() {
        if (prompt == null) {
            prompt = new Prompt(spec);
        }
        handler.dialog(prompt);
        return prompt;
    }

    public Login login() {
        if (login == null) {
            login = new Login(spec);
        }
        handler.dialog(login);
        return login;
    }
}
