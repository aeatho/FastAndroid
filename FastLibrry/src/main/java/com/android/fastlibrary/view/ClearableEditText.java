/*
 * Copyright (C) 2014 loQua.Xee <loquaciouser@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.fastlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.fastlibrary.R;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.view.ClearableEditText
 * @Description: TODO 能够清空的EditText
 * @author: LoQua.Xee    loquaciouser@gmail.com
 * @date: 2014/9/13 14:38
 * @version: V1.0
 */

public class ClearableEditText extends RelativeLayout {
  /**
   * EditText component
   */
  private EditText editText;

  /**
   * Button that clears the EditText contents
   */
  private Button clearButton;

  /**
   * Additional listener fired when cleared
   */
  private OnClickListener onClickClearListener;

  public ClearableEditText(Context context) {
    super(context);
    init(null);
  }

  public ClearableEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs);
  }

  /**
   * Initialize view
   */
  private void init(AttributeSet attrs) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context
        .LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.view_edittext_clearable, this, true);

    editText = (EditText) findViewById(R.id.edittext);
    boolean enabled = true;
    if (attrs != null) {
      TypedArray attrsArray = getContext().obtainStyledAttributes(attrs,
          R.styleable.ClearableEditText);
      editText.setInputType(attrsArray.getInt(R.styleable
          .ClearableEditText_android_inputType, InputType.TYPE_CLASS_TEXT));
      editText.setHint(attrsArray.getString(R.styleable.ClearableEditText_android_hint));
      enabled = attrsArray.getBoolean(R.styleable.ClearableEditText_android_enabled, true);
    }
    if (enabled) {
      editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          clearButton.setVisibility(s.length() > 0 ? RelativeLayout.VISIBLE :
              RelativeLayout.GONE);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
      });
    } else {
      editText.setEnabled(false);
    }

    clearButton = (Button) findViewById(R.id.button_clear);
    clearButton.setVisibility(RelativeLayout.INVISIBLE);
    clearButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        editText.setText("");
        if (onClickClearListener != null) onClickClearListener.onClick(v);
      }
    });
  }

  /**
   * Get value
   *
   * @return text
   */
  public Editable getText() {
    return editText.getText();
  }

  /**
   * Set value
   */
  public void setText(String text) {
    editText.setText(text);
  }

  /**
   * Set OnClickListener, making EditText unfocusable
   */
  @Override
  public void setOnClickListener(OnClickListener listener) {
    editText.setFocusable(false);
    editText.setFocusableInTouchMode(false);
    editText.setOnClickListener(listener);
  }

  /**
   * Set listener to be fired after EditText is cleared
   */
  public void setOnClearListener(OnClickListener listener) {
    onClickClearListener = listener;
  }
}
