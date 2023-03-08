import set1.challenge1.HexConverter
import set1.challenge2.FixedXOR

fun main(args: Array<String>) {
    //Test Set 1 Hex to Base64
    println("Test Set 1 Challenge 1: Hex to Base64")
    print(String(HexConverter.hexToBase64("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")))
    println()

    //Test Set 1 Challenge 2 Fixed XOR
    println("Test Set 1 Challenge 2: Fixed XOR")
    val fixedXOR: MutableList<Int>? = FixedXOR.xorHex("1c0111001f010100061a024b53535009181c", "686974207468652062756c6c277320657965")
    FixedXOR.printXORHex(fixedXOR)
    println()
}