package com.example.dentaku;

public class Calculation {
    private float x;
    private float y;
    private OperatorFlag flg;

    public Calculation(float x, StringBuilder input, OperatorFlag flg) {
        this.x = x;
        this.flg = flg;
        this.y = Float.valueOf(
                input.substring(input.indexOf(getOperation()) + 1));
    }

    public String equal() {
        if (OperatorFlag.PLUS == this.flg) {
            return String.valueOf(this.x + this.y);
        } else if (OperatorFlag.MINUS == this.flg) {
            return String.valueOf(this.x - this.y);
        } else if (OperatorFlag.TIMES == this.flg) {
            return String.valueOf(this.x * this.y);
        } else if (OperatorFlag.DIVIDED == this.flg) {
            return String.valueOf(this.x / this.y);
        } else {
            return "";
        }

    }

    private String getOperation() {
        if (OperatorFlag.PLUS == this.flg) {
            return Constants.OPERATOR_PLUS;
        } else if (OperatorFlag.MINUS == this.flg) {
            return Constants.OPERATOR_MINUS;
        } else if (OperatorFlag.TIMES == this.flg) {
            return Constants.OPERATOR_TIMES;
        } else if (OperatorFlag.DIVIDED == this.flg) {
            return Constants.OPERATOR_DIVIDED;
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
