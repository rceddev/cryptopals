package set1.challenge1

import kotlin.math.pow

class HexToBase64 {

    fun stringToBinaryString(rawString: String){
        var rawByteArray: ByteArray = rawString.toByteArray(Charsets.UTF_8)
        var rawString: String = ""
        var tmpRawString : String = ""

        for (item in rawByteArray){
            tmpRawString = item.toString(radix = 2)
            for (i in 0 until 8-tmpRawString.length)
                tmpRawString = "0$tmpRawString"
            rawString += tmpRawString
        }

        var sixBinaryList: MutableList<Byte> = mutableListOf()
        var tmp: String = ""

        for (item in rawString){
            tmp += item
            if (tmp.length == 6) {
                sixBinaryList.add(tmp.toInt(2).toByte())
                tmp = ""
            }
        }

        var paddingCounter: Int = 0
        var paddingMissing = (6 - tmp.length) / 2
        if (tmp.length != 0) {
            for (i in 0 until paddingMissing) {
                paddingCounter++
                tmp += "00"
            }
            sixBinaryList.add(tmp.toInt(2).toByte())
        }

        for (i in 0 .. sixBinaryList.size-1)
            sixBinaryList.set(i, getBase64Byte(sixBinaryList.get(i)))

        for (i in 0 until paddingCounter)
            sixBinaryList.add("=".toByteArray(Charsets.UTF_8).get(0))

        print(String(sixBinaryList.toByteArray()))
    }

    fun getBase64Byte(charByte: Byte): Byte{
        /**             BASE64        ASCII       Diference
         *  {A..Z}     {0..25}       {65..90}        65
         *  {a..z}     {26..51}      {97..122}       71
         *  {0..9}     {52..61}      {48..57}        -4
         *    +          62             43          special
         *    /          63             47          special
        **/
        var intChar : Int = charByte.toInt()

        if (intChar >= 52 &&  intChar <= 61)//Numbers validation
            return (intChar - 4).toByte()
        if (intChar >= 0 &&  intChar <= 25)//Capital letters validation
            return (intChar + 65).toByte()
        if (intChar >= 26 &&  intChar <= 51)//Lowercase letters validation
            return (intChar + 71).toByte()
        if (intChar == 62)
            intChar = 43
        if (intChar == 63)
            intChar = 47

        return intChar.toByte()
    }

    fun hexToString(hexadecimalString: String) : String{
        var hexString: String = if (hexadecimalString.length%2 == 0) hexadecimalString
            else hexadecimalString.subSequence(0, hexadecimalString.length-1).toString()
        var hexValue: String = ""
        for (i in 0 until hexString.length step 2){
            hexValue += hexToDecimal(hexString.subSequence(i, i+2).toString()).toChar()
        }
        return hexValue
    }

    private fun hexToDecimal(hex: String) : Int {
        var decimal: Int = 0
        var tmp: Int = 0
        for (i:Int in 0 until hex.length){
            when(hex.get(i).uppercaseChar()){
                'A' -> tmp = 10
                'B' -> tmp = 11
                'C' -> tmp = 12
                'D' -> tmp = 13
                'E' -> tmp = 14
                'F' -> tmp = 15
                else -> {
                    tmp = Character.digit(hex.get(i), 10)
                }
            }
            decimal += tmp * 16.0.pow(hex.length - 1 - i).toInt()
        }
        return decimal
    }

}