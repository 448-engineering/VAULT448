package engineering448.vault448

class Vault448Native {
    init {
        System.loadLibrary("vault448_native");
    }

    external fun sillyString(): String
}