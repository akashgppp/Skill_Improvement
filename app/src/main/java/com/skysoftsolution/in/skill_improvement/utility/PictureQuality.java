package com.skysoftsolution.in.skill_improvement.utility;

public enum PictureQuality {

    /*HIGH_QUALITY(3), MEDIUM_QUALITY(2), LOW_QUALITY(1);*/
    QUALITY10(10), QUALITY9(9), QUALITY8(8), QUALITY7(7), QUALITY6(6), QUALITY5(
            5), QUALITY4(4), QUALITY3(3), QUALITY2(2),QUALITY1(1);
    public int quality;

    private PictureQuality(int qlty) {
        quality = qlty;
    }

}
