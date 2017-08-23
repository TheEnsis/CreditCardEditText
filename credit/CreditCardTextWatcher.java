package net.pirsquare.haventmet.view.credit;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Nut on 8/23/17.
 */

public class CreditCardTextWatcher implements TextWatcher {
    private final EditText mEditText;
    private final TextWatcherListener mListener;

    public CreditCardTextWatcher(EditText editText, TextWatcherListener listener) {
        this.mEditText = editText;
        this.mListener = listener;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.mListener.onTextChanged(this.mEditText, s.toString());
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void afterTextChanged(Editable s) {
    }

    public interface TextWatcherListener {
        void onTextChanged(EditText var1, String var2);
    }
}
