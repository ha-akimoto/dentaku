package com.example.dentaku;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CalculationUtilsTest {

    /**
     * イコール
     * 入力値のフラグが、想定外の値の場合
     * 空文字を返却する。
     * フラグ：１〜４以外
     * 期待値：空文字
     */
    @Test
    public void equalFlgOther() throws NoSuchFieldException, IllegalAccessException {
        // 入力値の設定
        float x = 29.52f;
        float y = 342.534f;
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_PLUS);
        input.append(y);

        // 期待値
        String result = "";
        assertEquals(result, CalculationUtils.calculate(x, input, OperatorFlag.DEFAULT));
    }

    /**
     * イコール
     * 入力値のフラグが、プラスの場合
     * 計算した値の文字列を返却する。
     * フラグ：プラス
     * 期待値：足し算された値の文字列
     */
    @Test
    public void equalPlus() {
        // 入力値の設定
        float x = 9.423f;
        float y = 345.22f;
        // 演算子：プラス
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_PLUS);
        input.append(y);

        // 期待値
        String result = String.valueOf(x + y);
        assertEquals(result, CalculationUtils.calculate(x, input, OperatorFlag.PLUS));
    }

    /**
     * イコール
     * 入力値のフラグが、マイナスの場合
     * 計算した値の文字列を返却する。
     * フラグ：プラス
     * 期待値：引き算された値の文字列
     */
    @Test
    public void equalMinus() {
        // 入力値の設定
        float x = 301.4f;
        float y = 3.2f;
        // 演算子：マイナス
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_MINUS);
        input.append(y);

        // 期待値
        String result = String.valueOf(x - y);
        assertEquals(result, CalculationUtils.calculate(x, input, OperatorFlag.MINUS));
    }

    /**
     * イコール
     * 入力値のフラグが、かけるの場合
     * 計算した値の文字列を返却する。
     * フラグ：かける
     * 期待値：掛け算された値の文字列
     */
    @Test
    public void equalTimes() {
        // 入力値の設定
        float x = 4.72f;
        float y = 859.3f;
        // 演算子：かける
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_TIMES);
        input.append(y);

        // 期待値
        String result = String.valueOf(x * y);
        assertEquals(result, CalculationUtils.calculate(x, input, OperatorFlag.TIMES));
    }

    /**
     * イコール
     * 入力値のフラグが、割るの場合
     * 計算した値の文字列を返却する。
     * フラグ：割る
     * 期待値：算された値の文字列
     */
    @Test
    public void equalDivided() {
        // 入力値の設定
        float x = 3520.42f;
        float y = 232.2f;
        // 演算子：割る
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_DIVIDED);
        input.append(y);

        // 期待値
        String result = String.valueOf(x / y);
        assertEquals(result, CalculationUtils.calculate(x, input, OperatorFlag.DIVIDED));
    }

    /**
     * 演算子取得
     * フラグに１〜４以外の値が設定されている場合
     * 空文字を返却する。
     * フラグ：１〜４以外
     * 期待値：空文字
     */
    @Test
    public void getOperatorFlgOther() {
        try {
            Method method = CalculationUtils.class.getDeclaredMethod("getOperation", OperatorFlag.class);
            method.setAccessible(true);

            // 期待値
            String result = "";
            assertEquals(result, method.invoke(null, OperatorFlag.DEFAULT));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * 小数点以下切り捨て
     * 入力値に小数点以下の値が０の場合
     * 入力値の小数点以下の値を切り捨てて返却する。
     * 入力値：小数点以下の値０
     * 期待値：小数点以下なし
     */
    @Test
    public void truncateDecimalPointTrue() {
        // コンストラクターの設定
        float x = 4f;
        float y = 2f;
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(Constants.OPERATOR_PLUS);
        sb.append(y);
        OperatorFlag flg = OperatorFlag.PLUS;

        String text = CalculationUtils.calculate(x, sb, flg);

        // 期待値
        String result = "6";
        assertEquals(result, CalculationUtils.truncateDecimalPoint(text));
    }

    /**
     * 小数点以下切り捨て
     * 入力値に小数点以下の値が有る場合
     * 入力値の値を返却する。
     * 入力値：小数点以下の値あり
     * 期待値：小数点以下あり
     */
    @Test
    public void truncateDecimalPointFalse() {
        // コンストラクターの設定
        float x = 4.4f;
        float y = 2.5f;
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(Constants.OPERATOR_PLUS);
        sb.append(y);
        OperatorFlag flg = OperatorFlag.PLUS;

        // 入力値の設定
        String input = CalculationUtils.calculate(x, sb, flg);

        // 期待値
        String result = "6.9";
        assertEquals(result, CalculationUtils.truncateDecimalPoint(input));
    }
}