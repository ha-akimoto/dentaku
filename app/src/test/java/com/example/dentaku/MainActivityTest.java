package com.example.dentaku;

import android.os.Build;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;


@Config(sdk = Build.VERSION_CODES.O_MR1)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Before
    public void setup() {
        ActivityScenario.launch(MainActivity.class);
    }

    private static void clickButtonWithId(int id) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click());
    }

    private static void checkResultWithText(String text) {
        Espresso.onView(ViewMatchers.withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.withText(text)));
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
        clickButtonWithId(R.id.button_equals);

        checkResultWithText("");
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
        // 入力値X
        int x = 106;
        clickButtonWithId(R.id.button1);
        clickButtonWithId(R.id.button0);
        clickButtonWithId(R.id.button6);

        // 演算子：足し算
        clickButtonWithId(R.id.button_plus);

        // 入力値Y
        int y = 39;
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button9);

        // x + y = ?
        String result = Integer.toString(x + y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        float x = 53.3f;
        clickButtonWithId(R.id.button5);
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button3);

        // 演算子：足し算
        clickButtonWithId(R.id.button_plus);

        // 入力値Y
        float y = 234.135f;
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button1);
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button5);

        // x + y = ?
        String result = Float.toString(x + y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        int x = 248;
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button8);

        // 演算子：引き算
        clickButtonWithId(R.id.button_minus);

        // 入力値Y
        int y = 63;
        clickButtonWithId(R.id.button6);
        clickButtonWithId(R.id.button3);

        // x - y = ?
        String result = Integer.toString(x - y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        float x = 342.144f;
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button1);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button4);

        // 演算子：引き算
        clickButtonWithId(R.id.button_minus);

        // 入力値Y
        float y = 48.67f;
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button8);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button6);
        clickButtonWithId(R.id.button7);

        // x - y = ?
        String result = Float.toString(x - y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        float x = 2.24f;
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button4);

        // 演算子：引き算
        clickButtonWithId(R.id.button_minus);

        // 入力値Y
        float y = 340.23f;
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button0);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button3);

        // x - y = ?
        String result = Float.toString(x - y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        int x = 620;
        clickButtonWithId(R.id.button6);
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button0);

        // 演算子：掛け算
        clickButtonWithId(R.id.button_times);

        // 入力値Y
        int y = 93;
        clickButtonWithId(R.id.button9);
        clickButtonWithId(R.id.button3);

        // x * y = ?
        String result = Integer.toString(x * y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        float x = 24.1331f;

        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button1);
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button1);

        // 演算子：掛け算
        clickButtonWithId(R.id.button_times);

        // 入力値Y
        float y = 428.3f;
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button8);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button3);

        // x * y = ?
        String result = Float.toString(x * y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        int x = 942;
        clickButtonWithId(R.id.button9);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button2);

        // 演算子：割り算
        clickButtonWithId(R.id.button_divided);

        // 入力値Y
        int y = 2;
        clickButtonWithId(R.id.button2);

        // x ÷ y = ?
        String result = Integer.toString(x / y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
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
        // 入力値X
        float x = 342.12f;
        clickButtonWithId(R.id.button3);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button2);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button1);
        clickButtonWithId(R.id.button2);

        // 演算子：割り算
        clickButtonWithId(R.id.button_divided);

        // 入力値Y
        float y = 94.676f;
        clickButtonWithId(R.id.button9);
        clickButtonWithId(R.id.button4);
        clickButtonWithId(R.id.button_dot);
        clickButtonWithId(R.id.button6);
        clickButtonWithId(R.id.button7);
        clickButtonWithId(R.id.button6);

        // x ÷ y = ?
        String result = Float.toString(x / y);
        clickButtonWithId(R.id.button_equals);

        checkResultWithText(result);
    }
}