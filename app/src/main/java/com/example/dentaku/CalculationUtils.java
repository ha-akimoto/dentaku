package com.example.dentaku;

public class CalculationUtils {

    private CalculationUtils() {}

    public static String calculate(float x, StringBuilder input, OperatorFlag flg) {

        try {
            float y = Float.valueOf(input.substring(input.indexOf(getOperation(flg)) + 1));
            if (OperatorFlag.PLUS == flg) {
                return String.valueOf(x + y);
            } else if (OperatorFlag.MINUS == flg) {
                return String.valueOf(x - y);
            } else if (OperatorFlag.TIMES == flg) {
                return String.valueOf(x * y);
            } else if (OperatorFlag.DIVIDED == flg) {
                return String.valueOf(x / y);
            } else {
                return "";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getOperation(OperatorFlag flg) {
        if (OperatorFlag.PLUS == flg) {
            return Constants.OPERATOR_PLUS;
        } else if (OperatorFlag.MINUS == flg) {
            return Constants.OPERATOR_MINUS;
        } else if (OperatorFlag.TIMES == flg) {
            return Constants.OPERATOR_TIMES;
        } else if (OperatorFlag.DIVIDED == flg) {
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
    public static String truncateDecimalPoint(String text) {
        if (text.matches("^.*\\.0+$")) {
            return text.substring(0, text.indexOf("."));
        } else {
            return text;
        }
    }
}