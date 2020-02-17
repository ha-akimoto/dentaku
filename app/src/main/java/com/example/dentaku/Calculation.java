package com.example.dentaku;

import androidx.annotation.Nullable;

public class Calculation {
    private float x;
    private float y;
    private int flg;

    public Calculation(float x, StringBuilder input, int flg) {
        this.x = x;
        this.flg = flg;
        this.y = Float.valueOf(
                input.substring(input.indexOf(getOperation()) + 1));
    }

    public String equal() {
        if (Constants.FLG_PLUS == this.flg) {
            return String.valueOf(this.x + this.y);
        } else if (Constants.FLG_MINUS == this.flg) {
            return String.valueOf(this.x - this.y);
        } else if (Constants.FLG_TIMES == this.flg) {
            return String.valueOf(this.x * this.y);
        } else if (Constants.FLG_DIVIDED == this.flg) {
            return String.valueOf(this.x / this.y);
        } else {
            return "";
        }

    }

    private String getOperation() {
        if (Constants.FLG_PLUS == this.flg) {
            return Constants.OPERATION_PLUS;
        } else if (Constants.FLG_MINUS == this.flg) {
            return Constants.OPERATION_MINUS;
        } else if (Constants.FLG_TIMES == this.flg) {
            return Constants.OPERATION_TIMES;
        } else if (Constants.FLG_DIVIDED == this.flg) {
            return Constants.OPERATION_DIVIDED;
        } else {
            return "";
        }
    }

    /**
     * 小数点以下が０なら小数点以上の値を返却する
     *
     * @param text 対象の文字列
     * @return String 処理結果の文字列
     */
    public String truncateDecimalPoint(String text) {
        if (text.matches("^.*\\.0+$")) {
            return text.substring(0, text.indexOf("."));
        } else {
            return text;
        }
    }
}
