package com.yugal.android.mobilemouse;

public class TextAnalyzer {
    final int BACKSPACE=1;
    final int ADDED=2;

    public int detectAction(String prev,String finl){
        if (finl.equals("")){
            return BACKSPACE;
        }
        if(prev.startsWith(finl) && prev.length()-finl.length()==1){
            return BACKSPACE;
        }
        if(finl.startsWith(prev) && finl.length()-prev.length()==1){
            return ADDED;
        }
        return 0;
    }
}
