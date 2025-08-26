package com.ty.userService.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LongestPal {
    public static void main(String[] args) {
        String str="levellel";

    longestSubString(str);

    }

    private static void longestSubString(String str) {

        int right=0;
        int left=0;
        int maxLength=0;
        int startIndex=0;
        int n=str.length();

        Set<Character> set=new HashSet<>();

        while(right<n)
        {
            char currentCharacter=str.charAt(right);

            if(!set.contains(currentCharacter))
            {
                set.add(currentCharacter);
                int currentLength=right-left+1;

                if(currentLength>maxLength)
                {
                    maxLength=currentLength;
                    startIndex=left;
                }

                right++;
            }
            else{
                set.remove(str.charAt(left));
                left++;
            }
        }
        String s2=str.substring(startIndex,startIndex+maxLength);
        System.out.println(s2);
    }
}
