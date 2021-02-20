package com.devsdg.digipos.Commons.GeneratorTools.Voucher.Impl;

import com.devsdg.digipos.Commons.GeneratorTools.Voucher.VoucherGenerator;

public class HexadecimalVoucherGenerator extends VoucherGenerator {
    @Override
    public void generate(int quantity) throws IllegalArgumentException {
        if (quantity > 0) {
            for(int i = 0; i<quantity; i++) {
                String num = Integer.toHexString(i);
                list.add(String.format(voucherFormat,0, num).substring(num.length()));
            }
        }else{
            throw new IllegalArgumentException("Can't generate vouchers of negative or zero quantities");
        }
    }
}
