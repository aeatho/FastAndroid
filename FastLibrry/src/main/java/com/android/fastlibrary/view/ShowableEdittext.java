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
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.fastlibrary.R;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.view.ShowEdittextPassword
 * @Description: TODO 能够显示密码的EditText
 * @author: LoQua.Xee    loquaciouser@gmail.com
 * @date: 2014/9/13 14:03
 * @version: V1.0
 */

public class ShowableEdittext extends RelativeLayout {
  /**
   * EditText component
   */
  private EditText editText;

  /**
   * Button that let the EditText show its contents
   */
  private Button showpasswordButton;

  public ShowableEdittext(Context context) {
    super(context);
    init(null);
  }

  public ShowableEdittext(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public ShowableEdittext(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs);
  }

  /**
   * Initialize view
   */
  private void init(AttributeSet attrs) {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context
        .LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.view_edittext_showpassword, this, true);

    editText = (EditText) findViewById(R.id.edittext);
    boolean enabled = true;
    if (attrs != null) {
      TypedArray attrsArray = getContext().obtainStyledAttributes(attrs,
          R.styleable.ShownEdittext);
      enabled = attrsArray.getBoolean(R.styleable.ShownEdittext_android_enabled, true);
    }
    if (enabled) {
      editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          showpasswordButton.setVisibility(s.length() > 0 ? RelativeLayout.VISIBLE :
              RelativeLayout.GONE);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
      });
    } else {
      editText.setEnabled(false);
    }

    showpasswordButton = (Button) findViewById(R.id.button_clear);
    showpasswordButton.setVisibility(RelativeLayout.INVISIBLE);
    showpasswordButton.setOnTouchListener(mOnTouchListener);
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

  OnTouchListener mOnTouchListener = new OnTouchListener() {

    private int mPreviousInputType;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
      final int action = event.getAction();
      switch (action) {
        case MotionEvent.ACTION_DOWN:
          mPreviousInputType = editText.getInputType();
          setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo
              .TYPE_TEXT_VARIATION_VISIBLE_PASSWORD, true);
          break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
          setInputType(mPreviousInputType, true);
          mPreviousInputType = -1;
          break;
      }

      return false;
    }
  };

  private void setInputType(int inputType, boolean keepState) {
    int selectionStart = -1;
    int selectionEnd = -1;
    if (keepState) {
      selectionStart = editText.getSelectionStart();
      selectionEnd = editText.getSelectionEnd();
    }
    editText.setInputType(inputType);
    if (keepState) {
      editText.setSelection(selectionStart, selectionEnd);
    }
  }
}
