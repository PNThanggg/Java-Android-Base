package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.LocaleList;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.R;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4;
    Button button5, button6, button7, button8, button9;
    Button C, one_div_x, dot;
    String input = "";
    String answer = "";
    TextView display1, display2;
    ImageView bang, cong, tru, doidau, nhan, chia, can, binh, del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] kt = {0, 0};

        button0 = findViewById(R.id.zero);
        button1 = findViewById(R.id.one);
        button2 = findViewById(R.id.two);
        button3 = findViewById(R.id.three);
        button4 = findViewById(R.id.four);
        button5 = findViewById(R.id.five);
        button6 = findViewById(R.id.six);
        button7 = findViewById(R.id.seven);
        button8 = findViewById(R.id.eight);
        button9 = findViewById(R.id.nine);

        display1 = findViewById(R.id.hienThi1);
        display2 = findViewById(R.id.hienThi2);

        one_div_x = findViewById(R.id.one_div_x);
        dot = findViewById(R.id.dot);
        C = findViewById(R.id.C);
        bang = findViewById(R.id.bang);
        cong = findViewById(R.id.plus);
        tru = findViewById(R.id.tru);
        chia = findViewById(R.id.chia);
        doidau = findViewById(R.id.doidau);
        can = findViewById(R.id.can);
        binh = findViewById(R.id.binh);
        del = findViewById(R.id.del);

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = display1.getText().toString();

                if(newText.equals("0")){
                    if(!newText.contains(".")){
                        input = "0.";
                        display1.setText(input);
                    } else {
                        display1.setText(input);
                    }
                } else {
                    if(!newText.contains(".")){
                        input += ".";
                        display1.setText(input);
                    } else {
                        display1.setText(input);
                    }
                }
            }
        });

        one_div_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = display1.getText().toString();
                kt[0] = 1;

                if(newText.length() >= 1){
                    Float number = Float.parseFloat(newText);
                    answer = "1/" + newText;
                    number = 1 / number;
                    input = number.toString();

                    String [] a = input.split("\\.");
                    if(Long.valueOf(a[1]).equals(0L)){
                        input = Long.valueOf(a[0]).toString();
                    }

                    display1.setText(input);
                    display2.setText(answer);
                } else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                    input = "";
                    answer = "";
                    display1.setText("0");
                    display2.setText("");
                }
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = "";
                answer = "";
                display1.setText("0");
                display2.setText("");
            }
        });

        bang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kt[1] = 1; // check đk xem đã chọn bằng hay chưa

                String newText = display1.getText().toString();

                answer = display2.getText().toString();
                answer += newText;
                answer += " = ";

                String [] a = answer.split("\\s");

                if(a[1].equals("=")){
                    display1.setText(input);
                    display2.setText(answer);
                } else if(a.length == 4) {
                    // phép nhân
                    if (a[1].equals("*")) {
                        Float number1 = Float.valueOf(a[0]);
                        Float number2 = Float.valueOf(a[2]);

                        Float ans = number1 * number2;
                        input = ans.toString();

                        String [] b = input.split("\\.");

                        if(Long.valueOf(b[1]).equals(0L)){
                            input = Long.valueOf(b[0]).toString();
                        }

                        display1.setText(input);
                        display2.setText(answer);
                    } else if(a[1].equals("-")){ // phép trừ
                        Float number1 = Float.valueOf(a[0]);
                        Float number2 = Float.valueOf(a[2]);

                        Float ans = number1 - number2;
                        input = ans.toString();
                        String [] b = input.split("\\.");

                        if(Long.valueOf(b[1]).equals(0L)){
                            input = Long.valueOf(b[0]).toString();
                        }

                        display1.setText(input);
                        display2.setText(answer);
                    } else if(a[1].equals("+")){ // phép cộng
                        Float number1 = Float.valueOf(a[0]);
                        Float number2 = Float.valueOf(a[2]);

                        Float ans = number1 + number2;
                        input = ans.toString();

                        String [] b = input.split("\\.");
                        if(Long.valueOf(b[1]).equals(0L)){
                            input = Long.valueOf(b[0]).toString();
                        }

                        display1.setText(input);
                        display2.setText(answer);
                    } else if(a[1].equals("/")){ // phép chia
                        Float number1 = Float.valueOf(a[0]);
                        Float number2 = Float.valueOf(a[2]);

                        Float ans = number1 / number2;
                        input = ans.toString();

                        String [] b = input.split("\\.");
                        if(Long.valueOf(b[1]).equals(0L)){
                            input = Long.valueOf(b[0]).toString();
                        }

                        display1.setText(input);
                        display2.setText(answer);
                    }
                }
                input = "";
                answer = "";
            }
        });

        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nếu mà trước đó đã chọn = thì sex lấy kết quả để thực hiện
                // tiếp phép tính
                if(kt[1] == 1){
                    kt[1] = 0;

                    String newText = display1.getText().toString();
                    answer = newText + " + ";
                    input = "";
                    display2.setText(answer);
                } else if(kt[0] == 1){
                    answer = display2.getText().toString() + " + ";
                    display2.setText(answer);
                } else {
                    if(answer.length() == 0){
                        if(input.length() == 0){
                            answer = "0 + ";
                            display1.setText("0");
                            display2.setText(answer);
                        } else {
                            answer = input + " + ";
                            input = "";
                            display2.setText(answer);
                        }
                    }
                    else {
                        String s = display2.getText().toString();
                        String [] ss = s.split("\\s");

                        if(!ss[1].equals("+")){
                            if(ss[1].equals("/")){
                                ss[1] = "/";
                            } else if(ss[1].equals("-")){
                                ss[1] = "-";
                            } else if(ss[1].equals("*")){
                                ss[1] = "*";
                            }

                            answer = ss[0] + " " + ss[1] + " ";
                            display2.setText(answer);
                        } else {
                            String newText = answer.substring(0, answer.length() - 3);
                            Float number = Float.valueOf(newText);
                            Float number1;
                            if(!input.equals("")) number1 = Float.valueOf(input);
                            else number1 = 0F;
                            Float ans = number + number1;
                            input = ans.toString();

                            String [] a = input.split("\\.");
                            if(Long.valueOf(a[1]).equals(0L)){
                                input = Long.valueOf(a[0]).toString();
                            }

                            answer = input + " + ";
                            display1.setText(input);
                            display2.setText(answer);
                            input = "";
                        }
                    }
                }
            }
        });

        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1){
                    kt[1] = 0;

                    String newText = display1.getText().toString();
                    answer = newText + " - ";
                    display2.setText(answer);
                    input = "";
                } else {
                    if(answer.length() == 0){
                        if(input.length() == 0){
                            answer = "0 - ";
                            display1.setText("0");
                            display2.setText(answer);
                        } else {
                            answer = input + " - ";
                            input = "";
                            display2.setText(answer);
                        }
                    }
                    else {
                        String s = display2.getText().toString();
                        String [] ss = s.split("\\s");

                        if(!ss[1].equals("-")){
                            if(ss[1].equals("+")) ss[1] = "+";
                            else if(ss[1].equals("/")) ss[1] = "/";
                            else if(ss[1].equals("*")) ss[1] = "*";

                            answer = ss[0] + " " + ss[1] + " ";
                            display2.setText(answer);
                        } else {
                            String newText = answer.substring(0, answer.length() - 3);
                            Float number = Float.valueOf(newText);
                            Float number1 = 0f;
                            if(!input.equals("")) number1 = Float.valueOf(input);
                            else number1 = 0F;

                            Float ans = number - number1;

                            input = ans.toString();

                            String [] a = input.split("\\.");
                            if(Long.valueOf(a[1]).equals(0L)){
                                input = Long.valueOf(a[0]).toString();
                            }

                            answer = input + " - ";
                            display1.setText(input);
                            display2.setText(answer);
                            input = "";
                        }
                    }
                }
            }
        });

        nhan = findViewById(R.id.nhan);
        nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;

                    String newText = display1.getText().toString();
                    answer = newText + " * ";
                    input = "";
                    display2.setText(answer);
                } else {
                    if(answer.length() == 0){
                        if(input.length() == 0){
                            answer = "0 * ";
                            display1.setText("0");
                            display2.setText(answer);
                        } else {
                            answer = input + " * ";
                            input = "";
                            display2.setText(answer);
                        }
                    }
                    else {
                        String s = display2.getText().toString();
                        String [] ss = s.split("\\s");

                        if(!ss[1].equals("*")){
                            if(ss[1].equals("+")){
                                ss[1] = "+";
                            } else if(ss[1].equals("-")){
                                ss[1] = "-";
                            } else if(ss[1].equals("/")){
                                ss[1] = "/";
                            }

                            answer = ss[0] + " " + ss[1] + " ";
                            display2.setText(answer);
                        } else {
                            String newText = answer.substring(0, answer.length() - 3);
                            Float number = Float.valueOf(newText);
                            Float number1;
                            if(!input.equals("")) number1 = Float.valueOf(input);
                            else number1 = 1F;
                            Float ans = number * number1;
                            input = ans.toString();

                            String [] a = input.split("\\.");
                            if(Long.valueOf(a[1]).equals(0L)){
                                input = Long.valueOf(a[0]).toString();
                            }

                            answer = input + " * ";
                            display1.setText(input);
                            display2.setText(answer);
                            input = "";
                        }
                    }
                }
            }
        });

        chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;

                    String newText = display1.getText().toString();
                    answer = newText + " / ";
                    input = "";
                    display2.setText(answer);
                } else {
                    String s = display2.getText().toString();

                    if(s.length() == 0){
                        if(input.equals("")){
                            answer = "0 / ";
                            display1.setText("0");
                            display2.setText(answer);
                        } else {
                            answer = input + " / ";
                            input = "";
                            display2.setText(answer);
                        }
                    } else {
                        String [] ss = s.split("\\s");

                        if(!ss[1].equals("/")){
                            if(ss[1].equals("+")) ss[1] = "+";
                            else if(ss[1].equals("-")) ss[1] = "-";
                            else if(ss[1].equals("*")) ss[1] = "*";


                            answer = ss[0] + " " + ss[1] + " ";
                            display2.setText(answer);
                        } else {
                            String newText = answer.substring(0, answer.length() - 3);
                            Float number = Float.valueOf(newText);
                            Float number1;
                            if(!input.equals("")) number1 = Float.valueOf(input);
                            else number1 = 1F;
                            Float ans = number / number1;

                            input = ans.toString();

                            String [] a = input.split("\\.");
                            if(Long.valueOf(a[1]).equals(0L)){
                                input = Long.valueOf(a[0]).toString();
                            }

                            answer = input + " / ";
                            display1.setText(input);
                            display2.setText(answer);
                            input = "";
                        }
                    }
                }
            }
        });

        doidau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = display1.getText().toString();

                if(newText.length() >= 1){
                    Double number = 0d;
                    if(!input.equals("")) number = Double.valueOf(input);
                    else number = Double.valueOf(display1.getText().toString());
                    number *= -1;
                    input = number.toString();

                    String [] a = input.split("\\.");
                    if(Long.valueOf(a[1]).equals(0L)){
                        input = Long.valueOf(a[0]).toString();
                    }

                    display1.setText(input);
                }
            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = display1.getText().toString();

                if(newText.length() >= 1){
                    kt[0] = 1;
                    Float number = 0f;
                    if(input.equals("")) number = Float.valueOf(newText);
                    else number = Float.valueOf(input);

                    String cur = number.toString();
                    number = Float.valueOf((float) Math.sqrt(number));
                    input = number.toString();

                    String [] a = input.split("\\.");
                    if(Long.valueOf(a[1]).equals(0L)){
                        input = Long.valueOf(a[0]).toString();
                    }

                    String [] b = cur.split("\\.");
                    if(Long.valueOf(b[1]).equals(0L)){
                        cur = Long.valueOf(b[0]).toString();
                    }

                    display1.setText(input);
                    display2.setText("Sqrt(" + cur + ")");
                } else {
                    display2.setText("Sqrt(0)");
                    display1.setText("0");
                }
            }
        });

        binh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input.length() >= 1){
                    kt[0] = 1;
                    Float number = Float.valueOf(input);
                    String cur = number.toString();
                    number *= number;
                    input = number.toString();

                    String [] a = input.split("\\.");
                    if(Long.valueOf(a[1]).equals(0L)){
                        input = Long.valueOf(a[0]).toString();
                    }

                    String [] b = cur.split("\\.");
                    if(Long.valueOf(b[1]).equals(0L)){
                        cur = Long.valueOf(b[0]).toString();
                    }

                    display1.setText(input);
                    display2.setText("Sqr(" + cur + ")");
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[0] != 1){
                    if(input.length() >= 1){
                        String newText = input.substring(0, input.length() - 1);
                        input = newText;

                        if(input.length() >= 1) display1.setText(input);
                        else display1.setText("0");
                    }
                }
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1){
                    input = "";
                    kt[1] = 0;
                    display1.setText("0");
                    display2.setText("");
                } else {
                    if(kt[0] == 1) {
                        input = "";
                        kt[0] = 0;
                        display1.setText("0");
                    } else {
                        if(input.length() >= 1) {
                            input += "0";
                            display1.setText(input);
                        } else {
                            input = "";
                            display1.setText("0");
                        }
                    }
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "1";
                display1.setText(input);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "2";
                display1.setText(input);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "3";
                display1.setText(input);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "4";
                display1.setText(input);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "5";
                display1.setText(input);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "6";
                display1.setText(input);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "7";
                display1.setText(input);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "8";
                display1.setText(input);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kt[1] == 1) {
                    kt[1] = 0;
                    display1.setText(input);
                } else if(kt[0] == 1) {
                    input = "";
                    kt[0] = 0;
                }

                input += "9";
                display1.setText(input);
            }
        });
    }
}