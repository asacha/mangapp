package com.freelance.ascstb.mangapp.model.data.converter

import android.util.Log
import com.freelance.ascstb.mangapp.model.entity.MangaDetail
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode
import java.io.*

@Suppress("UNCHECKED_CAST")
class MyCustomDeserializer : Serializer {
    companion object {
        private val TAG = MyCustomDeserializer::class.java.simpleName + "_TAG"
    }

    override fun validate(type: Class<*>?, source: String?): Boolean {
        Log.d(TAG, "validate: 1")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: File?): Boolean {
        Log.d(TAG, "validate: 2")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: InputStream?): Boolean {
        Log.d(TAG, "validate: 3")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: Reader?): Boolean {
        Log.d(TAG, "validate: 4")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: InputNode?): Boolean {
        Log.d(TAG, "validate: 5")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: String?, strict: Boolean): Boolean {
        Log.d(TAG, "validate: 6")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: File?, strict: Boolean): Boolean {
        Log.d(TAG, "validate: 7")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: InputStream?, strict: Boolean): Boolean {
        Log.d(TAG, "validate: 8")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: Reader?, strict: Boolean): Boolean {
        Log.d(TAG, "validate: 9")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validate(type: Class<*>?, source: InputNode?, strict: Boolean): Boolean {
        Log.d(TAG, "validate: 10")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun write(source: Any?, out: File?) {
        Log.d(TAG, "validate: 11")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun write(source: Any?, out: OutputStream?) {
        Log.d(TAG, "validate: 12")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun write(source: Any?, out: Writer?) {
        Log.d(TAG, "validate: 13")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun write(source: Any?, root: OutputNode?) {
        Log.d(TAG, "validate: 14")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: String?): T {
        Log.d(TAG, "validate: 15")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: File?): T {
        Log.d(TAG, "validate: 16")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: InputStream?): T {
        Log.d(TAG, "validate: 17")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: Reader?): T {
        Log.d(TAG, "validate: 18")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: InputNode?): T {
        Log.d(TAG, "validate: 19")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: String?, strict: Boolean): T {
        Log.d(TAG, "validate: 20")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: File?, strict: Boolean): T {
        Log.d(TAG, "validate: 21")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: Reader?, strict: Boolean): T {
        Log.d(TAG, "validate: 23")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: InputNode?, strict: Boolean): T {
        Log.d(TAG, "validate: 24")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: String?): T {
        Log.d(TAG, "validate: 25")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: File?): T {
        Log.d(TAG, "validate: 26")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: InputStream?): T {
        Log.d(TAG, "validate: 27")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: Reader?): T {
        Log.d(TAG, "validate: 28")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: InputNode?): T {
        Log.d(TAG, "validate: 29")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: String?, strict: Boolean): T {
        Log.d(TAG, "validate: 30")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: File?, strict: Boolean): T {
        Log.d(TAG, "validate: 31")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: InputStream?, strict: Boolean): T {
        Log.d(TAG, "validate: 32")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: Reader?, strict: Boolean): T {
        Log.d(TAG, "validate: 33")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(value: T, source: InputNode?, strict: Boolean): T {
        Log.d(TAG, "validate: 34")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> read(type: Class<out T>?, source: InputStream?, strict: Boolean): T {
        Log.d(TAG, "validate: 22")
        val detail = MyConverter().fromDetailResult(source)

        return detail as T
    }
}
