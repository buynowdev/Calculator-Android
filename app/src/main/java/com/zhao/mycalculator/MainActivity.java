package com.zhao.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {

    private final String TAG = "MainActivity";
    private final int STATE_INIT=0;           //输入第一个数字
    private final int STATE_EDITING=1;        //数字编辑阶段
    private final int STATE_RESULT=2;         //返回结果状态 不可以对数字修改 只可以进行运算 或重新开始一次新的运算
    List<StringBuffer> list;

    private int state;                                //输入框的状态
    private double result;
    private double cacheCount;
    char operate;
    TextView tv_display;
    StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 程序初始化
     */
    private void init(){
        tv_display=(TextView)findViewById(R.id.tv_display);
        list=new LinkedList<StringBuffer>();
        result=0;
        cacheCount=0;
        operate='+';
        state=STATE_INIT;
        sb=new StringBuffer();
        tv_display.setText(sb);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_0:
                updateData('0');
                checkIsResult(v);
                break;
            case R.id.bt_1:
                updateData('1');
                checkIsResult(v);
                break;
            case R.id.bt_2:
                updateData('2');
                checkIsResult(v);
                break;
            case R.id.bt_3:
                updateData('3');
                checkIsResult(v);
                break;
            case R.id.bt_4:
                updateData('4');
                checkIsResult(v);
                break;
            case R.id.bt_5:
                updateData('5');
                checkIsResult(v);
                break;
            case R.id.bt_6:
                updateData('6');
                checkIsResult(v);
                break;
            case R.id.bt_7:
                updateData('7');
                checkIsResult(v);
                break;
            case R.id.bt_8:
                updateData('8');
                checkIsResult(v);
                break;
            case R.id.bt_9:
                updateData('9');
                checkIsResult(v);
                break;
            case R.id.bt_point:
                updateData('.');
                checkIsResult(v);
                break;
            case R.id.bt_delete:
                updateData('d');
                break;
            case R.id.bt_add:
                updateData('+');
                break;
            case R.id.bt_minus:
                updateData('-');
                break;
            case R.id.bt_multiply:
                updateData('*');
                break;
            case R.id.bt_dividi:
                updateData('/');
                break;
            case R.id.bt_equal:
                updateData('=');

        }
    }

    /**
     * 每通过按钮　传入该方法一个字符
     * 实现对数据的更新
     */
    public void updateData(char ch){
        if ((ch<='9'&& ch>='0')||ch=='.'){
            sb.append(ch);
            tv_display.setText(sb);
        }
        switch (ch){
            case 'd':                                           //删除
                if (sb.length()==0)
                    break;
                sb.deleteCharAt(sb.length() - 1);
                tv_display.setText(sb);
                break;
            case '+':
                if(sb.length()==0){
                    init();
                    break;
                }
                cacheCount=Double.parseDouble(sb.toString());
                operator();
                operate='+';
                break;
            case '-':
                if(sb.length()==0){
                    init();
                    break;
                }
                cacheCount=Double.parseDouble(sb.toString());
                operator();
                operate='-';
                break;
            case '*':
                if(sb.length()==0){
                    init();
                    break;
                }
                cacheCount=Double.parseDouble(sb.toString());
                operator();
                operate='*';
                break;
            case '/':
                if(sb.length()==0){
                    init();
                    break;
                }
                cacheCount=Double.parseDouble(sb.toString());
                operator();
                operate='/';
                break;
            case '=':
                if(sb.length()==0){
                    init();
                    break;
                }
                cacheCount=Double.parseDouble(sb.toString());
                operator();
                operate='+';
                break;
        }
    }

    private void operator(){
        String str;
        switch (operate){
            case '+':
                result+=cacheCount;
                str= String.valueOf(result);
                sb=new StringBuffer();
         //       state=STATE_RESULT;
                tv_display.setText(str);
                break;
            case '-':
                result-=cacheCount;
                str=String.valueOf(result);
                sb=new StringBuffer();
                //       state=STATE_RESULT;
                tv_display.setText(str);
                break;
            case '/':
                if(cacheCount==0){
                    tv_display.setText("除零错误");
                    init();
                    break;
                }
                result/=cacheCount;
                str=String.valueOf(result);
                sb=new StringBuffer();
                //       state=STATE_RESULT;
                tv_display.setText(str);
                break;
            case '*':
                result*=cacheCount;
                str=String.valueOf(result);
                sb=new StringBuffer();
                //       state=STATE_RESULT;
                tv_display.setText(str);
                break;
            case '=':
                result+=cacheCount;
                str= String.valueOf(result);
                sb=new StringBuffer();
                //       state=STATE_RESULT;
                tv_display.setText(str);
                cacheCount=0;
                result=0;
                break;
        }
    }

    /**
     * 检测状态
     * @param v
     */
    public void checkIsResult(View v ){
        if (state==STATE_RESULT){
            init();
            onClick(v);
        }
    }
}
