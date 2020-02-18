package com.example.dentaku;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class CalculationTest {

    /**
     * コンストラクター
     * コンストラクターの起動で、入力値のYが正しくフィールドに設定されていること
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void Calculation() throws NoSuchFieldException, IllegalAccessException {
        // 入力値の設定
        Float x = 231.3f;
        Float y = 906.86f;
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_PLUS);
        input.append(y);
        int flg = Constants.FLG_PLUS;

        Calculation cal = new Calculation(x, input, flg);

        // privateフィールドにアクセスするためのリフレクション
        Field field = cal.getClass().getDeclaredField("y");
        field.setAccessible(true);

        // 入力値yとコンストラクターで設定されたyが一致すること
        assertEquals(y, (Float) field.getFloat(cal));

    }

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
        Float x = 29.52f;
        Float y = 342.534f;
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_PLUS);
        input.append(y);
        int flg = Constants.FLG_PLUS;

        // 期待値
        String result = "";

        Calculation cal = new Calculation(x, input, flg);

        // privateフィールドにアクセスするためのリフレクション
        Field field = cal.getClass().getDeclaredField("flg");
        field.setAccessible(true);
        field.setInt(cal, 0);

        assertEquals(result, cal.equal());

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
        Float x = 9.423f;
        Float y = 345.22f;
        // 演算子：プラス
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_PLUS);
        input.append(y);
        int flg = Constants.FLG_PLUS;

        // 期待値
        String result = String.valueOf(x + y);

        Calculation cal = new Calculation(x, input, flg);

        assertEquals(result, cal.equal());
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
        Float x = 301.4f;
        Float y = 3.2f;
        // 演算子：マイナス
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_MINUS);
        input.append(y);
        int flg = Constants.FLG_MINUS;

        // 期待値
        String result = String.valueOf(x - y);

        Calculation cal = new Calculation(x, input, flg);

        assertEquals(result, cal.equal());
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
        Float x = 4.72f;
        Float y = 859.3f;
        // 演算子：かける
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_TIMES);
        input.append(y);
        int flg = Constants.FLG_TIMES;

        // 期待値
        String result = String.valueOf(x * y);

        Calculation cal = new Calculation(x, input, flg);

        assertEquals(result, cal.equal());
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
        Float x = 3520.42f;
        Float y = 232.2f;
        // 演算子：割る
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_DIVIDED);
        input.append(y);
        int flg = Constants.FLG_DIVIDED;

        // 期待値
        String result = String.valueOf(x / y);

        Calculation cal = new Calculation(x, input, flg);

        assertEquals(result, cal.equal());
    }

    /**
     * 演算子取得
     * フラグに１〜４以外の値が設定されている場合
     * 空文字を返却する。
     * フラグ：１〜４以外
     * 期待値：空文字
     */
    @Test
    public void getOperatorFlgOther() throws NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        // 入力値の設定
        Float x = 3520.42f;
        Float y = 232.2f;
        StringBuilder input = new StringBuilder();
        input.append(x);
        input.append(Constants.OPERATOR_DIVIDED);
        input.append(y);
        int flg = Constants.FLG_DIVIDED;

        // 期待値
        String result = "";

        Calculation cal = new Calculation(x, input, flg);

        // privateフィールドにアクセスするためのリフレクション
        Field field = cal.getClass().getDeclaredField("flg");
        field.setAccessible(true);
        field.setInt(cal, 5);
        // privateメソッドにアクセスするためのリフレクション
        Method method = cal.getClass().getDeclaredMethod("getOperation");
        method.setAccessible(true);

        assertEquals(result, method.invoke(cal));
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
        Float x = 4f;
        Float y = 2f;
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(Constants.OPERATOR_PLUS);
        sb.append(y);
        int flg = Constants.FLG_PLUS;

        // 入力値の設定
        String input = "6.0";

        // 期待値
        String result = "6";

        Calculation cal = new Calculation(x, sb, flg);

        assertEquals(result, cal.truncateDecimalPoint(input));
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
        Float x = 4.4f;
        Float y = 2.5f;
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        sb.append(Constants.OPERATOR_PLUS);
        sb.append(y);
        int flg = Constants.FLG_PLUS;

        // 入力値の設定
        String input = "6.9";

        // 期待値
        String result = input;

        Calculation cal = new Calculation(x, sb, flg);

        assertEquals(result, cal.truncateDecimalPoint(input));
    }


}