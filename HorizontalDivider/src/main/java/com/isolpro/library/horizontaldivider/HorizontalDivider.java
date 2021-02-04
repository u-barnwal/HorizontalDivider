package com.isolpro.library.horizontaldivider;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class HorizontalDivider extends RelativeLayout {

  private View mv, vLeft, vRight;
  private TextView tvTitle;

  public HorizontalDivider(Context context) {
    super(context);
    init(context, null, 0, 0);
  }

  public HorizontalDivider(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0, 0);
  }

  public HorizontalDivider(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr, 0);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HorizontalDivider, 0, 0);

    mv = LayoutInflater.from(context).inflate(R.layout.horizontal_divider, null, false);

    instantiate();
    initialize();

    try {
      setTitle(typedArray.getString(R.styleable.HorizontalDivider_title));
      setPosition(typedArray.getInt(R.styleable.HorizontalDivider_position, 2));
    } finally {
      typedArray.recycle();
    }

    addView(mv);
  }

  private void instantiate() {
    vLeft = mv.findViewById(R.id.vLeft);
    vRight = mv.findViewById(R.id.vRight);
    tvTitle = mv.findViewById(R.id.tvTitle);
  }

  private void initialize() {
  }

  public void setTitle(String title) {
    tvTitle.setText(title);
  }

  public void setPosition(Position position) {
    switch (position) {
      case LEFT:
        vLeft.setVisibility(View.GONE);
        vRight.setVisibility(View.VISIBLE);
        break;
      case CENTER:
        vLeft.setVisibility(View.VISIBLE);
        vRight.setVisibility(View.VISIBLE);
        break;
      case RIGHT:
        vLeft.setVisibility(View.VISIBLE);
        vRight.setVisibility(View.GONE);
        break;
    }
  }

  public void setPosition(int position) {
    switch (position) {
      case 1:
        setPosition(Position.LEFT);
        break;
      case 3:
        setPosition(Position.RIGHT);
        break;
      default:
        setPosition(Position.CENTER);
    }
  }

  public enum Position {LEFT, CENTER, RIGHT}

}
