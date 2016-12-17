/**
 * Created by Pan on 2016/12/17.
 */
function genkeys(encryptionExponent, modulus, maxdigits) {
    setMaxDigits(parseInt(maxdigits, 10));
    var ee = biFromHex(encryptionExponent)
    var mm = biFromHex(modulus);
    var cc = biHighIndex(mm)
    var bb = new BarrettMu(mm);
    var keys = {
        e : ee,
        m : mm,
        chunkSize : 2 * cc,
        radix :16,
        barrett : bb
    }
    return keys;
};