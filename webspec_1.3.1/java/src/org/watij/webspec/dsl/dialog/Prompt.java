package org.watij.webspec.dsl.dialog;

import org.watij.webspec.dsl.WebSpec;


public class Prompt extends Dialog {
    public Prompt(WebSpec spec) {
        super(spec);
    }

    public void ok() {
        if (spec.isIE()) {
            runScript("prompt_ok.exe", WebSpec.prompt_window_title_for_ie);
        } else {
            super.ok();
        }
    }

    public void cancel() {
        if (spec.isIE()) {
            runScript("prompt_cancel.exe", WebSpec.prompt_window_title_for_ie);
        } else {
            super.ok();
        }
    }
}
