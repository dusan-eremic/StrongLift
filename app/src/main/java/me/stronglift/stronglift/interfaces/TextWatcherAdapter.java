package me.stronglift.stronglift.interfaces;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by Dusan Eremic.
 */
public abstract class TextWatcherAdapter implements TextWatcher {

    private TextView textView;

    public TextWatcherAdapter(TextView textView) {
        this.textView = textView;
        textView.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    public TextView getTextView() {
        return textView;
    }
}
