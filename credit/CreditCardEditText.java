package net.pirsquare.haventmet.view.credit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Nut on 8/23/17.
 */

public class CreditCardEditText extends EditText implements CreditCardTextWatcher.TextWatcherListener {
    private static final String SEPARATOR = "-";
    private static final int MINIMUM_CREDIT_CARD_LENGTH = 13;
    private static final int MAXIMUM_CREDIT_CARD_LENGTH = 19;
    private CreditCardTextWatcher mTextWatcher;
    private String mSeparator;
    private int mMinimumCreditCardLength;
    private int mMaximumCreditCardLength;
    private String mPreviousText;

    public CreditCardEditText(Context context) {
        super(context);
        this.init();
    }

    public CreditCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public CreditCardEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.init();
    }

    public int getMaximumCreditCardLength() {
        return this.mMaximumCreditCardLength;
    }

    public void setMaximumCreditCardLength(int maximumCreditCardLength) {
        this.mMaximumCreditCardLength = maximumCreditCardLength;
    }

    public int getMinimumCreditCardLength() {
        return this.mMinimumCreditCardLength;
    }

    public void setMinimumCreditCardLength(int minimumCreditCardLength) {
        this.mMinimumCreditCardLength = minimumCreditCardLength;
    }

    public String getSeparator() {
        return this.mSeparator;
    }

    public void setSeparator(String mSeparator) {
        this.mSeparator = mSeparator;
    }

    public String getCreditCardNumber() {
        String creditCardNumber = this.getText().toString().replace(mSeparator, "");
        return creditCardNumber.length() >= this.mMinimumCreditCardLength && creditCardNumber.length() <= this.mMaximumCreditCardLength ? creditCardNumber : null;
    }

    public void onTextChanged(EditText view, String text) {
        if (this.mPreviousText != null && text.length() > this.mPreviousText.length()) {
            String difference = StringUtil.difference(text, this.mPreviousText);
            if (!difference.equals(mSeparator)) {
                this.addSeparatorToText();
            }
        }

        this.mPreviousText = text;
    }

    private void init() {
        this.mSeparator = SEPARATOR;
        this.mMinimumCreditCardLength = MINIMUM_CREDIT_CARD_LENGTH;
        this.mMaximumCreditCardLength = MAXIMUM_CREDIT_CARD_LENGTH;
        this.setInputType(2);

        this.mTextWatcher = new CreditCardTextWatcher(this, this);
        this.addTextChangedListener(this.mTextWatcher);
    }

    private void addSeparatorToText() {
        String text = this.getText().toString();
        text = text.replace(mSeparator, "");
        if (text.length() < 16) {
            byte interval = 4;
            char separator = mSeparator.charAt(0);
            StringBuilder stringBuilder = new StringBuilder(text);

            for (int i = 0; i < text.length() / interval; ++i) {
                stringBuilder.insert((i + 1) * interval + i, separator);
            }

            this.removeTextChangedListener(this.mTextWatcher);
            this.setText(stringBuilder.toString());
            this.setSelection(this.getText().length());
            this.addTextChangedListener(this.mTextWatcher);
        }
    }
}