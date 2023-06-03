package net.ivanvega.clientecontentprovidermoneda

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    lateinit var listView: ListView
    lateinit var adapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        adapter = SimpleCursorAdapter(
            applicationContext,
            android.R.layout.simple_list_item_2,
            null,
            arrayOf("CodigoCambioDivisa", "Divisa"),
            intArrayOf(android.R.id.text1, android.R.id.text2),
            SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        )
        listView.adapter = adapter

        supportLoaderManager.initLoader(1001, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            applicationContext,
            Uri.parse("content://net.ivanvega.proyectodivisacontentprividera/monedas"),
            arrayOf("_ID", "CodigoCambioDivisa", "Divisa", "FechaActualizacion", "FechaConsulta"),
            null,
            null,
            null
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val adapter = SimpleCursorAdapter(
            applicationContext,
            R.layout.list_item,
            data,
            arrayOf("CodigoCambioDivisa", "Divisa", "FechaActualizacion", "FechaConsulta"),
            intArrayOf(R.id.tvCodigoCambioDivisa, R.id.tvDivisa, R.id.tvFechaActualizacion, R.id.tvFechaConsulta),
            SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        )
        listView.adapter = adapter
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapter.swapCursor(null)
    }
}
