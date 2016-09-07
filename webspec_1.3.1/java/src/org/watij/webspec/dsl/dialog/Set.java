package org.watij.webspec.dsl.dialog;

/**
 * Created by IntelliJ IDEA.
 * User: bknorr
 * Date: 12/13/10
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Set {
    Dialog dialog;

    public Set(Dialog dialog) {
        this.dialog = dialog;
    }

    public Dialog username(String username) {
        dialog.username = username;
        return dialog;
    }

    public Dialog password(String password) {
        dialog.password = password;
        return dialog;
    }
}
