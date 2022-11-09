package ru.solarmeister.generatorofcolors.model

data class Colors(var red: Int = 0, var green: Int = 0, var blue: Int = 0, val variations: Array<String> = arrayOf("Случайный цвет", "Свой цвет")) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Colors

        if (!variations.contentEquals(other.variations)) return false

        return true
    }

    override fun hashCode(): Int {
        return variations.contentHashCode()
    }
}