package com.example.calculator;

import java.util.Stack;

public class Result_Returner {
    private char [] str;
    private int result;
    public Result_Returner(char[] str) {
        this.str = str;
    }

    public char[] getStr() {
        return str;
    }

    public void setStr(char[] str) {
        this.str = str;
    }
    public boolean Operator(char ch){
        return (ch=='+' || ch=='-' || ch=='*' || ch=='/');
    }
    public int Order(char ch){
        if (ch=='+' || ch=='-'){
            return 1;
        }
        else if (ch=='*' || ch=='/'){
            return 2;
        }
        else
            return 3;
    }
    public int Precedence(char ch,char top){
        int p1 = Order(ch);
        int p2 = Order(top);
        return (p1<=p2) ? 1 : 2;
    }
    public int Calculate(){
        char[] post_fix = new char[0];
        int i=0,j=0,l=0;
        float value=0.0f;
        Stack<Character> stk = new Stack();
        while (str[i]!='\0'){
            if (!Operator(str[i])){
                post_fix[l++] = str[i];
            }
            else{
                if(stk.empty() || Precedence(str[i],stk.peek())==2)
                    stk.push(str[i]);
                else if (Precedence(str[i], stk.peek())==1){
                    post_fix[l++] = str[i];
                    while (!stk.empty() && Precedence(str[i],stk.peek())==1){
                        post_fix[l++] = stk.pop();
                    }
                    stk.push(str[i]);
                }
            }
        }
        return  result;
    }
}
