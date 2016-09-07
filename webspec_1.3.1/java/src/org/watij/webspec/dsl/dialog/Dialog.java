package org.watij.webspec.dsl.dialog;

import com.teamdev.jxbrowser.prompt.*;
import org.watij.webspec.dsl.WebSpec;

import java.io.IOException;


public abstract class Dialog {

    private String value, message;
    private CloseStatus status;
    protected WebSpec spec;
    public org.watij.webspec.dsl.dialog.Set set;
    String username, password;

    public Dialog(WebSpec spec) {
        this.spec = spec;
        this.set = new org.watij.webspec.dsl.dialog.Set(this);
    }

    String value() {
        return value;
    }

    boolean isOk() {
        return CloseStatus.OK == status;
    }

    public void ok() {
        this.status = CloseStatus.OK;
    }

    public void cancel() {
        this.status = CloseStatus.CANCEL;
    }

    public Dialog set(String value) {
        this.value = value;
        return this;
    }

    public Dialog shouldHave(String message) {
        this.message = message;
        return this;
    }

    Dialog reset() {
        value = message = null;
        status = CloseStatus.OK;
        return this;
    }

    protected void runScript(String script, String windowTitle) {
        try {
            value = (value == null) ? "" : value;
            String exec = WebSpec.webspec_home+"\\autoit\\"+script+" \""+windowTitle+"\" \"" +value+"\"";
            WebSpec.debug("RUNSCRIPT = "+exec);
            Runtime.getRuntime().exec(exec);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Handler implements PromptService {
        Dialog dialog;

        public void dialog(Dialog dialog) {
            this.dialog = dialog;
            dialog.reset();
        }

        public void alertRequested(final DialogParams dialogParams) {
            WebSpec.debug("ALERT REQUESTED");
            shouldHave(dialogParams.getMessage());
        }

        public CloseStatus confirmationRequested(final DialogParams dialogParams) {
            WebSpec.debug("CONFIRM REQUESTED");
            shouldHave(dialogParams.getMessage());
            return dialog.status;
        }

        public CloseStatus promptRequested(PromptDialogParams promptDialogParams) {
            WebSpec.debug("PROMPT REQUESTED");
            shouldHave(promptDialogParams.getMessage());
            if (dialog.isOk() && dialog.value() != null) {
                promptDialogParams.setValue(dialog.value());
            }
            return dialog.status;
        }

        public CloseStatus loginRequested(LoginParams loginParams) {
            WebSpec.debug("LOGIN REQUESTED");
            if (dialog.isOk()) {
                if (loginParams.getType() == LoginParams.Type.USERNAME_AND_PASSWORD) {
                    loginParams.setUserName(dialog.username);
                }
                loginParams.setPassword(dialog.password);
            }
            return dialog.status;
        }


        public CloseStatus badCertificateRequested(DialogParams dialogParams) {
            WebSpec.debug("BAD CERTIFICATE REQUESTED - WebSpec currently doesn't handle this.");
            return null;
        }


        public CloseStatus scriptErrorRequested(ScriptErrorParams scriptErrorParams) {
            WebSpec.debug("SCRIPT ERROR REQUESTED - WebSpec currently doesn't handle this.");
            return null;
        }

        private void shouldHave(String message) {
            if (dialog.message != null && !message.contains(dialog.message)) {
                throw new RuntimeException("Expectation failed for dialog, expected: " + "message to contain " + dialog.message + ", got: " + message);
            }
        }
    }

}
