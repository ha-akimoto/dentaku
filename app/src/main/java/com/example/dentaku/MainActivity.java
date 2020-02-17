package com.example.dentaku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 内部から直接操作する要素
    Button buttonEquals;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonTimes;
    Button buttonDivided;
    Button buttonDot;
    TextView textView;

    // 文字列表示用のストリングビルダー
    StringBuilder sb;

    // 計算用変数
    float x = 0;
    float y = 0;

    // 演算フラグ
    int flg = Constants.FLG_DEFAULT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // テキストビューの取得
        textView = findViewById(R.id.textView);

        // ボタンの取得
        buttonEquals = findViewById(R.id.button_equals);
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        buttonTimes = findViewById(R.id.button_times);
        buttonDivided = findViewById(R.id.button_divided);
        buttonDot = findViewById(R.id.button_dot);

        // 初期化
        begin();
        textView.setText(sb);

        // ボタン押下イベントリスナーの登録
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button_dot).setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonTimes.setOnClickListener(this);
        buttonDivided.setOnClickListener(this);

    }

    /**
     * リスナーに設定されるクリックイベント
     * @param view ビュー
     */
    public void onClick(View view){
        // 文字列とフラグの設定
        switch(view.getId()){
            case R.id.button1:
                sb.append("1");
                break;
            case R.id.button2:
                sb.append("2");
                break;
            case R.id.button3:
                sb.append("3");
                break;
            case R.id.button4:
                sb.append("4");
                break;
            case R.id.button5:
                sb.append("5");
                break;
            case R.id.button6:
                sb.append("6");
                break;
            case R.id.button7:
                sb.append("7");
                break;
            case R.id.button8:
                sb.append("8");
                break;
            case R.id.button9:
                sb.append("9");
                break;
            case R.id.button0:
                sb.append("0");
                break;
            case R.id.button_dot:
                sb.append(".");
                buttonDot.setEnabled(false);
                break;
            case R.id.button_plus:
                x = Float.valueOf(sb.toString());
                sb.append("+");
                // フラグを設定
                flg = Constants.FLG_PLUS;
                break;
            case R.id.button_minus:
                x = Float.valueOf(sb.toString());
                sb.append("-");
                // フラグを設定
                flg = Constants.FLG_MINUS;
                break;
            case R.id.button_times:
                x = Float.valueOf(sb.toString());
                sb.append("×");
                // フラグを設定
                flg = Constants.FLG_TIMES;
                break;
            case R.id.button_divided:
                x = Float.valueOf(sb.toString());
                sb.append("÷");
                // フラグを設定
                flg = Constants.FLG_DIVIDED;
                break;
        }
        textView.setText(sb);

        // ボタンの有効無効制御
        if (Constants.FLG_DEFAULT == flg) {
            // フラグが立っていなければ演算ボタン有効化
            buttonPlus.setEnabled(true);
            buttonMinus.setEnabled(true);
            buttonDivided.setEnabled(true);
            buttonTimes.setEnabled(true);
        }else{
            // 演算ボタン無効化
            buttonPlus.setEnabled(false);
            buttonMinus.setEnabled(false);
            buttonDivided.setEnabled(false);
            buttonTimes.setEnabled(false);
            buttonDot.setEnabled(true);
            // 押されたボタンが演算ボタンでなければ
            if(R.id.button_plus != view.getId() &&
                    R.id.button_minus != view.getId() &&
                    R.id.button_times != view.getId() &&
                    R.id.button_divided != view.getId() ){
                buttonEquals.setEnabled(true);
            }
        }

    }

    /**
     * イコールボタン押下
     *
     * @param view ビュー
     */
    public void equals(View view){
        String equalsText = "";

        // 計算結果の取得
        Calculation cal = new Calculation(this.x, this.sb, this.flg);
        equalsText = cal.equal();
        equalsText = cal.truncateDecimalPoint(equalsText);

        // 答えをテキストビューに設定
        textView.setText(equalsText);
        // 初期化
        begin();
    }

    /**
     * クリア
     * @param view ビュー
     */
    public void clear(View view){
        begin();
        textView.setText(sb);
    }

    /**
     * 初期化
     */
    private void begin(){
        x = 0;
        y = 0;
        flg = Constants.FLG_DEFAULT;
        sb = new StringBuilder();
        buttonEquals.setEnabled(false);
        buttonPlus.setEnabled(false);
        buttonMinus.setEnabled(false);
        buttonTimes.setEnabled(false);
        buttonDivided.setEnabled(false);
        buttonDot.setEnabled(true);

    }

}
