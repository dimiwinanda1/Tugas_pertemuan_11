//untuk mendeklarasikan package com.dimiwinanda.internalstorage kita dapat menggunakan kata kunci package.
package com.dimiwinanda.internalstorage

//kita dapat menggunakan deklarasi import untuk mengaktifkan compiler untuk menemukan class, function, interface atau object yang akan diimpor.
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*

//Class MainActivity merupakan turunan dari Class AppCompatActivity dan mengimplementasikan Interface View.
//onCreate() adalah kondisi awal saat Activity baru diciptakan, disin dilakukan inisialisasi.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //untuk mengakses, mengambil dan menyimpan file kita dapat menggunakan File API untuk mengakses dan menyimpan file.
        //pada projek kali ini kita akan mengambil atau menginputkan data fileName dan juga fileData, kemudian mengakses tombol save dan juga view.
        val fileName = findViewById<EditText>(R.id.editFile)
        val fileData = findViewById<EditText>(R.id.editData)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)

        //untuk menyimpan file menggunakan stream sebagai alternatif dari menggunakan File API, 
        //kita dapat memanggil openFileOutput() untuk mendapatkan FileOutputStream yang menulis ke file dalam direktori filesDir.
        //Method View.OnClickListener dipanggil bila pengguna menyentuh item (saat dalam mode sentuh). 
        //atau pada tombol navigasi, seperti projek kita kali ini yang berfungsi untuk menekan tombol "Save".
        btnSave.setOnClickListener(View.OnClickListener {
            val file:String = fileName.text.toString()
            val data:String = fileData.text.toString()
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            Toast.makeText(applicationContext,"data save",Toast.LENGTH_LONG).show()
            fileName.text.clear()
            fileData.text.clear()
        })

        //untuk dapat mengakses file menggunakan stream atau membaca file sebagai stream, kita bisa mengunakan openFileInput()
        //Method View.OnClickListener dipanggil bila pengguna menyentuh item (saat dalam mode sentuh). 
        //atau pada tombol navigasi, seperti projek kita kali ini yang berfungsi untuk menekan tombol "View".
        //terdapat InputStreamReader yang merupakan sebuah Variabel Pada Aplikasi  yang di gunakan untuk memasukan Inputan ke dalam sebuah program.
        btnView.setOnClickListener(View.OnClickListener {
            val filename = fileName.text.toString()
            if(filename.toString()!=null && filename.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                fileData.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"file name cannot be blank", Toast.LENGTH_LONG).show()
            }
        })
    }
}
