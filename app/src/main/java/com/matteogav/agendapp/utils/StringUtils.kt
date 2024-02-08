package com.matteogav.agendapp.utils

import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

fun String.parseDate(): String {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    originalFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateParse = originalFormat.parse(this)
    val parsedFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return dateParse?.let { parsedFormat.format(it) } ?: ""
}

fun String.parsePhoneString(): String = "+34 ${this.replace("-", "")}"

fun String.decodeBase64AndDecompress(): String {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    val inputStream = ByteArrayInputStream(decodedBytes)
    val gzip = GZIPInputStream(inputStream)
    val buffer = ByteArray(4096)
    val outputStream = ByteArrayOutputStream()
    var bytesRead: Int
    while (gzip.read(buffer).also { bytesRead = it } != -1) {
        outputStream.write(buffer, 0, bytesRead)
    }
    return outputStream.toString()
}

fun String.gzipCompress(): String {
    val bos = ByteArrayOutputStream()
    val gzip = GZIPOutputStream(bos)
    gzip.write(this.toByteArray(Charsets.UTF_8))
    gzip.close()
    val compressed = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT)
    bos.close()
    return compressed
}
