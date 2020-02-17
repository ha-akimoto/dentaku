package com.example.dentaku;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(sdk = Build.VERSION_CODES.O_MR1)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    Context context;

    private static final int FLG_DEFAULT = 0;
    private static final int FLG_PLUS = 1;
    private static final int FLG_MINUS = 2;
    private static final int FLG_TIMES = 3;
    private static final int FLG_DIVIDED = 4;

    private static final String OPERATOR_PLUS = "+";
    private static final String OPERATOR_MINUS = "-";
    private static final String OPERATOR_TIMES = "×";
    private static final String OPERATOR_DIVIDED = "÷";

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getInstrumentation().getContext();
    }

    private MainActivity setupActivity(int operator, StringBuilder input, float x) {
        MainActivity activity = new MainActivity();
        activity.flg = operator;
        if (null != input) {
            activity.sb = input;
        }
        activity.x = x;

        activity.buttonEquals = new Button(this.context);
        activity.buttonPlus = new Button(this.context);
        activity.buttonMinus = new Button(this.context);
        activity.buttonTimes = new Button(this.context);
        activity.buttonDivided = new Button(this.context);
        activity.buttonDot = new Button(this.context);

        return activity;
    }

    /**
     * 演算フラグが想定外の値の場合
     * 計算の処理を行わず正常終了すること
     * 演算フラグ：0~4以外
     * 期待値：Viewに設定されるテキスト=空文字
     */
    @Test
    public void flgOther() {
        // 演算子：0~4以外
        int operator = 5;

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, null, 0);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals("", view.getText());

    }

    /**
     * 演算フラグがデフォルトの値の場合
     * 計算の処理を行わず正常終了すること
     * 演算フラグ：デフォルト
     * 期待値：Viewに設定されるテキスト=空文字
     */
    @Test
    public void flgDefault() {
        // 演算子：デフォルト
        int operator = this.FLG_DEFAULT;

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, null, 0);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals("", view.getText());

    }

    /**
     * 足し算の計算で、入力値に小数点以下の値が無い場合
     * 計算処理が行われ、小数点以上の値がViewに設定されること
     * 演算フラグ：プラス
     * 入力値：小数点以下なし
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下なし）
     */
    @Test
    public void plusPositive() {
        // 演算子：足し算
        int operator = this.FLG_PLUS;
        // 入力値X
        int x = 106;
        // 入力値Y
        int y = 39;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_PLUS);
        input.append(y);
        // 期待値
        String result = Integer.toString(x + y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 足し算の計算で、入力値に小数点以下の値が有る場合
     * 計算処理が行われ、小数点以下の値も含めた値がViewに設定されること
     * 演算フラグ：プラス
     * 入力値：小数点以下有り
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下有り）
     */
    @Test
    public void plusPositiveDecimal() {
        // 演算子：足し算
        int operator = this.FLG_PLUS;
        // 入力値X
        float x = 53.3f;
        // 入力値Y
        float y = 234.135f;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_PLUS);
        input.append(y);
        // 期待値
        String result = Float.toString(x + y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 引き算の計算で、入力値に小数点以下の値が無い場合
     * 計算処理が行われ、小数点以上の値がViewに設定されること
     * 演算フラグ：マイナス
     * 入力値：小数点以下なし
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下なし）
     */
    @Test
    public void minusPositive() {
        // 演算子：引き算
        int operator = this.FLG_MINUS;
        // 入力値X
        int x = 248;
        // 入力値Y
        int y = 63;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_MINUS);
        input.append(y);
        // 期待値
        String result = Integer.toString(x - y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 引き算の計算で、入力値に小数点以下の値が有る場合
     * 計算処理が行われ、小数点以下の値も含めた値がViewに設定されること
     * 演算フラグ：マイナス
     * 入力値：小数点以下有り
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下有り）
     */
    @Test
    public void minusPositiveDecimal() {
        // 演算子：引き算
        int operator = this.FLG_MINUS;
        // 入力値X
        float x = 342.144f;
        // 入力値Y
        float y = 48.67f;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_MINUS);
        input.append(y);
        // 期待値
        String result = Float.toString(x - y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 引き算の計算で、入力値に小数点以下の値が有り、計算結果が負の値になる場合
     * 計算処理が行われ、小数点以下の値も含めた値がViewに設定されること
     * 演算フラグ：マイナス
     * 入力値：小数点以下有り
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下有り、負の値）
     */
    @Test
    public void minusNegativeDecimal() {
        // 演算子：引き算
        int operator = this.FLG_MINUS;
        // 入力値X
        float x = 2.24f;
        // 入力値Y
        float y = 340.23f;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_MINUS);
        input.append(y);
        // 期待値
        String result = Float.toString(x - y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 掛け算の計算で、入力値に小数点以下の値が無い場合
     * 計算処理が行われ、小数点以上の値がViewに設定されること
     * 演算フラグ：かける
     * 入力値：小数点以下なし
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下なし）
     */
    @Test
    public void timesPositive() {
        // 演算子：掛け算
        int operator = this.FLG_TIMES;
        // 入力値X
        int x = 620;
        // 入力値Y
        int y = 93;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_TIMES);
        input.append(y);
        // 期待値
        String result = Integer.toString(x * y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 掛け算の計算で、入力値に小数点以下の値が有る場合
     * 計算処理が行われ、小数点以下の値も含めた値がViewに設定されること
     * 演算フラグ：かける
     * 入力値：小数点以下有り
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下有り）
     */
    @Test
    public void timesPositiveDecimal() {
        // 演算子：掛け算
        int operator = this.FLG_TIMES;
        // 入力値X
        float x = 24.1331f;
        // 入力値Y
        float y = 428.3f;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_TIMES);
        input.append(y);
        // 期待値
        String result = Float.toString(x * y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 割り算の計算で、入力値に小数点以下の値が無いの場合
     * 計算処理が行われ、小数点以上の値がViewに設定されること
     * 演算フラグ：割る
     * 入力値：小数点以下なし
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下なし）
     */
    @Test
    public void dividedPositive() {
        // 演算子：割り算
        int operator = this.FLG_DIVIDED;
        // 入力値X
        int x = 942;
        // 入力値Y
        int y = 2;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_DIVIDED);
        input.append(y);
        // 期待値
        String result = Integer.toString(x / y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

    /**
     * 割り算の計算で、入力値に小数点以下の値が有る場合
     * 計算処理が行われ、小数点以下の値も含めた値がViewに設定されること
     * 演算フラグ：割る
     * 入力値：小数点以下有り
     * 期待値：Viewに設定されるテキスト=計算結果の値（小数点以下有り）
     */
    @Test
    public void dividedPositiveDecimal() {
        // 演算子：掛け算
        int operator = this.FLG_DIVIDED;
        // 入力値X
        float x = 342.12f;
        // 入力値Y
        float y = 94.676f;
        // 入力値の文字列
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(this.OPERATOR_DIVIDED);
        input.append(y);
        // 期待値
        String result = Float.toString(x / y);

        TextView view = new TextView(this.context);
        MainActivity activity = setupActivity(operator, input, x);
        activity.textView = view;

        activity.equals(view);
        Assert.assertEquals(result, view.getText());
    }

}