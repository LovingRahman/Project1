package org.example.service;

public class Encrypt {

    // result 563 x 541
    private int N = 304583;
    //result 562 x 540
    private int phi = 303480;

    private int e = 7;

    private int d = 130063;

    public String encrypt(String text){
        int[] a = new int[text.length()];

        for(int i = 0; i < text.length(); i++) {
            a[i] = (int) text.charAt(i);
        }
        return text;
    }




}
