package com.devsdg.digipos.Commons.GeneratorTools.Voucher;

import com.devsdg.digipos.Commons.GeneratorTools.Exceptions.AlgorithmException;
import com.devsdg.digipos.Commons.GeneratorTools.Voucher.Impl.DecimalVoucherGenerator;
import com.devsdg.digipos.Commons.GeneratorTools.Voucher.Impl.HexadecimalRandomVoucherGenerator;
import com.devsdg.digipos.Commons.GeneratorTools.Voucher.Impl.HexadecimalVoucherGenerator;

import java.util.ArrayList;

public abstract class VoucherGenerator {
    protected String voucherFormat;
    protected ArrayList<String> list;


    /**
     * Voucher Generator Constructor
     */
    protected VoucherGenerator() {
        this.voucherFormat = "%06d%s";
        this.list = new ArrayList<String>();
    }

    /**
     * List of voucher codes
     * @return codes list
     */
    public ArrayList<String> getCodes() {
        return list;
    }


    /**
     * Get Voucher Generator
     * @param type - Generator Type
     * @return VoucherGenerator
     * @throws AlgorithmException
     */
    public static VoucherGenerator getVoucherGenerator(int type) throws AlgorithmException {
        switch (type) {
            case 0: return new DecimalVoucherGenerator();
            case 1: return new HexadecimalVoucherGenerator();
            case 2: return new HexadecimalRandomVoucherGenerator();
            default: throw new AlgorithmException("Can't generate vouchers with an undefined algorithm");
        }
    }

    /**
     * Generate list of codes
     * @param quantity - number of voucher
     * @throws IllegalArgumentException - quantity must be greather than 0
     */
    public abstract void generate(int quantity) throws IllegalArgumentException;
}
