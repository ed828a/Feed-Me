package net.gahfy.feedme.utils


/**
 * Created by Edward on 7/13/2018.
 */


fun String.extractDate(): String {
    val stringArray = this.split('T')

    return stringArray[0]
}
