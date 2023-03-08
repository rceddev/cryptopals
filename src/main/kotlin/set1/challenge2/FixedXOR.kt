package set1.challenge2

import set1.challenge1.HexConverter

class FixedXOR {
    companion object {

        /**
         * Produces XOR combination between two equal-length string
         * @param hex1 one of two hex numbers represented in string format to make xor combination
         * @param hex2 one of two hex numbers represented in string format to make xor combination
         * @return mutable list of integers with the result of XOR combination of hex1 and hex2
         */
        fun xorHex(hex1: String, hex2: String): MutableList<Int>? {

            if (hex1.length != hex2.length)
                return null

            //Check if hex1 length is an even number
            val hex1String: String = if (hex1.length % 2 == 0) hex1
            else hex1.subSequence(0, hex1.length - 1).toString()
            //Check if hex2 length is an even number
            val hex2String: String = if (hex2.length % 2 == 0) hex2
            else hex2.subSequence(0, hex2.length - 1).toString()

            val result: MutableList<Int> = mutableListOf()

            for (i in 0 until hex1String.length step 2) {
                result.add(
                    HexConverter.hexToDecimal(
                        hex1String.subSequence(i, i + 2).toString()
                    ) xor HexConverter.hexToDecimal(
                        hex2String.subSequence(i, i + 2).toString()
                    )
                )
            }
            return result
        }

        /**
         * Print the mutable list obtained after make xor combination
         * @param list mutable list of integers to print
         */
        fun printXORHex(list: MutableList<Int>?): Unit {
            if (list != null) {
                for (x in list) {
                    print(x.toString(16))
                }
            }
            println()
        }
    }
}