package org.watij.webspec.dsl.dialog;
import org.watij.webspec.dsl.WebSpec;

public class File extends Dialog {

    public File(WebSpec spec) {
        super(spec);
    }
    
    public void ok() {
        runScript("file_ok.exe", spec.isIE() ? WebSpec.file_window_title_for_ie : WebSpec.file_window_title_for_mozilla);
    }

    public void cancel() {
        runScript("file_cancel.exe", spec.isIE() ? WebSpec.file_window_title_for_ie : WebSpec.file_window_title_for_mozilla);
    }
}