package sivachenko.m.l32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    double p = 4;
    int numOfPair = 0;

    int[] a = {0, 6};
    int[] b = {1, 5};
    int[] c = {3, 3};
    int[] d = {2, 4};

    double[] ab = new double[2];
    double[] bc = new double[2];
    double[] cd = new double[2];

    double[] num = {0, 0};

    double speed;
    int deadline;

    Button radioButton;
    Button radioButton2;
    Button radioButton3;
    Button radioButton4;
    Button radioButton5;
    Button radioButton6;
    Button radioButton7;
    Button radioButton8;
    Button radioButton9;
    Button radioButton10;
    Button count;

    TextView tvResult1;
    TextView tvResult2;
    TextView tvResult3;
    TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvResult1 = (TextView) findViewById(R.id.result1);
        tvResult2 = (TextView) findViewById(R.id.result2);
        tvResult3 = (TextView) findViewById(R.id.result3);
        tvRes = (TextView) findViewById(R.id.res);

        radioButton = (Button) findViewById(R.id.radioButton);
        radioButton2 = (Button) findViewById(R.id.radioButton2);
        radioButton3 = (Button) findViewById(R.id.radioButton3);
        radioButton4 = (Button) findViewById(R.id.radioButton4);
        radioButton5 = (Button) findViewById(R.id.radioButton5);
        radioButton6 = (Button) findViewById(R.id.radioButton6);
        radioButton7 = (Button) findViewById(R.id.radioButton7);
        radioButton8 = (Button) findViewById(R.id.radioButton8);
        radioButton9 = (Button) findViewById(R.id.radioButton9);
        radioButton10 = (Button) findViewById(R.id.radioButton10);
        count = (Button) findViewById(R.id.count);

        radioButton.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        radioButton4.setOnClickListener(this);
        radioButton5.setOnClickListener(this);
        radioButton6.setOnClickListener(this);
        radioButton7.setOnClickListener(this);
        radioButton8.setOnClickListener(this);
        radioButton9.setOnClickListener(this);
        radioButton10.setOnClickListener(this);
        count.setOnClickListener(this);

    }

    public void find(int[] point1, int[] point2, double[] w) {
        num[0] = 0;
        num[1] = 0;
        double delta = 4;
        w[0] = 0;
        w[1] = 0;
        for (int i = 0; i < deadline; i++) {
            if (i % 2 == 0) {
                delta = p - num[0];
                w[0] = w[0] + (delta * point1[0] * speed);
                w[1] = w[1] + (delta * point1[1] * speed);
                num[0] = point1[0] * w[0] + point1[1] * w[1];
                num[1] = point2[0] * w[0] + point2[1] * w[1];
            } else {
                delta = p - num[1];
                w[0] = w[0] + (delta * point2[0] * speed);
                w[1] = w[1] + (delta * point2[1] * speed);
                num[0] = point1[0] * w[0] + point1[1] * w[1];
                num[1] = point2[0] * w[0] + point2[1] * w[1];

            }
            w[0] = round(w[0], 3);
            w[1] = round(w[1], 3);
            num[0] = round(num[0], 3);
            num[1] = round(num[1], 3);
            if (num[0] > p && num[1] < p) {
                break;
            }
        }
        if(w[0] == 0 && w[1] ==0){
            numOfPair++;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.radioButton:
                speed = 0.001;
                break;
            case R.id.radioButton2:
                speed = 0.01;
                break;
            case R.id.radioButton3:
                speed = 0.05;
                break;
            case R.id.radioButton4:
                speed = 0.1;
                break;
            case R.id.radioButton5:
                speed = 0.2;
                break;
            case R.id.radioButton6:
                speed = 0.3;
                break;
            case R.id.radioButton7:
                deadline = 100;
                break;
            case R.id.radioButton8:
                deadline = 200;
                break;
            case R.id.radioButton9:
                deadline = 500;
                break;
            case R.id.radioButton10:
                deadline = 1000;
                break;
            case R.id.count:
                find(a, b, ab);
                find(b, c, bc);
                find(c, d, cd);
                tvResult1.setText(" Для точок A, B. w1 = " + ab[0] + ", w2 = " + ab[1]);
                tvResult2.setText(" Для точок B, C. w1 = " + bc[0] + ", w2 = " + bc[1]);
                tvResult3.setText(" Для точок C, D. w1 = " + cd[0] + ", w2 = " + cd[1]);

                if(numOfPair != 0){
                    tvRes.setText("Для " + numOfPair + " точок, не було знайдено w, за обрану швидкість");
                }
                break;
            default:
                break;
        }

        // tvResult.setText("lala");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}
