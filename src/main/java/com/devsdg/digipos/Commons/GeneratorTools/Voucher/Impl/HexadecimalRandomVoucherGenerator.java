package com.devsdg.digipos.Commons.GeneratorTools.Voucher.Impl;

import com.devsdg.digipos.Commons.GeneratorTools.Voucher.VoucherGenerator;

import java.util.Random;

public class HexadecimalRandomVoucherGenerator extends VoucherGenerator {
    private static final int numDigits = 7;

    @Override
    public void generate(int quantity) throws IllegalArgumentException {
        if (quantity > 0 && String.valueOf(quantity).length() < HexadecimalRandomVoucherGenerator.numDigits) {
            while(list.size() < quantity) {
                int init = 0;
                int end = Integer.valueOf(new String(new char[numDigits + 1]).replace('\0', '9'));
                String num = Integer.toHexString(new Random().nextInt(end) + init);
                if(!list.contains(String.format(voucherFormat,0, num).substring(num.length()))) {
                    list.add(String.format(voucherFormat,0, num).substring(num.length()));
                }
            }

        }else{
            throw new IllegalArgumentException("The hexadecimal random only can be used quantities less than 1.000.000 and greater than 0");
        }
    }
}
