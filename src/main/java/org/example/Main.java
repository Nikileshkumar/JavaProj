package org.example;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        System.out.println(increasingTriplet(new int[]{20,100,10,12,5,13}));
    }

    public String reverseWords(String s) {
        s = s.trim();
        String result = "";
        for(String a: s.split(" ")){
            if(!Objects.equals(a, ""))
                result = a + " " + result;
        }
        return result.trim();
    }
    public static boolean increasingTriplet(int[] nums) {
        int i=0, j=1, k=2;
        while(i<j && j<k && k<nums.length){
            if(nums[i]<nums[j]){
                if(nums[j]<nums[k])
                    return true;
                else{
                    k++;
                }
            }
            else{
                j++;
                k=j+1;
            }
            if(k==nums.length) {
                if(j==nums.length-1){
                    i++;
                    j=i+1;
                    k=i+2;
                }
                else {
                    j++;
                    k=j+1;
                }
            }
        }
        return false;
    }
}