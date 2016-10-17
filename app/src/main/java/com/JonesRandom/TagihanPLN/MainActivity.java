package com.JonesRandom.TagihanPLN;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.JonesRandom.TagihanPLN.Model.Data;
import com.JonesRandom.TagihanPLN.Model.ResponseJSON;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String[] bulan = {
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"};

    EditText id_pelanggan;
    EditText tahun;
    Button cek_tagihan;

    String ID_PELANGGAN;
    String TAHUN;
    String BULAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spiBulan = (Spinner) findViewById(R.id.spinBulan);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bulan);
        spiBulan.setAdapter(adapter);
        spiBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (bulan[position]) {

                    case "Januari":
                        BULAN = "01";
                        break;
                    case "Februari":
                        BULAN = "02";
                        break;
                    case "Maret":
                        BULAN = "03";
                        break;
                    case "April":
                        BULAN = "04";
                        break;
                    case "Mei":
                        BULAN = "05";
                        break;
                    case "Juni":
                        BULAN = "06";
                        break;
                    case "Juli":
                        BULAN = "07";
                        break;
                    case "Agustus":
                        BULAN = "08";
                        break;
                    case "September":
                        BULAN = "09";
                        break;
                    case "Oktober":
                        BULAN = "10";
                        break;
                    case "November":
                        BULAN = "11";
                        break;
                    case "Desember":
                        BULAN = "12";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        id_pelanggan = (EditText) findViewById(R.id.get_id);
        tahun = (EditText) findViewById(R.id.get_tahun);


        cek_tagihan = (Button) findViewById(R.id.cek_tagihan);
        cek_tagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID_PELANGGAN = id_pelanggan.getText().toString();
                TAHUN = tahun.getText().toString();

                if (ID_PELANGGAN.isEmpty() || TAHUN.isEmpty()) {
                    toast("Pastikan Kolom Id Pelanggan/Tahun Telah Terisi");
                } else {
                    getDataTagihan(ID_PELANGGAN, TAHUN, BULAN);
                    InputMethodManager iml = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                    iml.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                }


            }
        });

    }

    public void getDataTagihan(final String id_pelanggan, String Tahun, String Bulan) {

        final ProgressDialog dialog = ProgressDialog.show(this, "Memuat Data Tagihan", "Please Wait");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ibacor.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResponseJSON> call = service.getDataTagihan(id_pelanggan, Tahun, Bulan);
        call.enqueue(new Callback<ResponseJSON>() {
            @Override
            public void onResponse(Call<ResponseJSON> call, Response<ResponseJSON> response) {

                if (response.isSuccessful()) {
                    String status = response.body().getStatus();

                    if (status.equals("success")) {

                        Data data = new Data();

                        data.setIdpel(response.body().getData().getIdpel());
                        data.setTagihan(response.body().getData().getTagihan());
                        data.setTerbilang(response.body().getData().getTerbilang());
                        data.setDaya(response.body().getData().getDaya());
                        data.setTarif(response.body().getData().getTarif());
                        data.setPemkwh(response.body().getData().getPemkwh());
                        data.setNama(response.body().getData().getNama());
                        data.setAlamat(response.body().getData().getAlamat());
                        data.setTglbacalalu(response.body().getData().getTglbacalalu());
                        data.setTglbacaakhir(response.body().getData().getTglbacaakhir());

                        dialogDetail(data);


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Tagihan PLN");
                        builder.setMessage("ID Pelanggan " + id_pelanggan + " Tidak Dapat Di Temukan");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /// KOSONG KEK ISI HATI YANG BIKIN :v
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                } else {
                    toast("Koneksi Bermasalah");
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseJSON> call, Throwable t) {
                toast("Koneksi Dapat Terhubung");
                dialog.dismiss();
            }
        });


    }

    public void toast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_LONG).show();
    }

    public void dialogDetail(Data data) {

        View v = getLayoutInflater().inflate(R.layout.dialog_detail, null);

        TextView id = (TextView) v.findViewById(R.id.set_id);
        TextView nama = (TextView) v.findViewById(R.id.set_nama);
        TextView alamat = (TextView) v.findViewById(R.id.set_alamat);
        TextView tagihan = (TextView) v.findViewById(R.id.set_tagihan);
        TextView terbilang = (TextView) v.findViewById(R.id.set_terbilang);
        TextView jenis = (TextView) v.findViewById(R.id.set_jenis);
        TextView daya = (TextView) v.findViewById(R.id.set_daya);
        TextView pemakaian = (TextView) v.findViewById(R.id.set_pemakaian);
        TextView pembacaan_sebelum = (TextView) v.findViewById(R.id.set_pembacaan_sebelum);
        TextView pembacaan_sesudah = (TextView) v.findViewById(R.id.set_pembacaan_sesudah);

        id.setText(data.getIdpel());
        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());

        int i = Integer.parseInt(data.getTagihan());
        String Tagihan = NumberFormat.getIntegerInstance().format(i);

        tagihan.setText("Rp. " + Tagihan + ",-");

        terbilang.setText(data.getTerbilang());
        jenis.setText(data.getTarif());
        daya.setText(data.getDaya());
        pemakaian.setText(data.getPemkwh());
        pembacaan_sebelum.setText(data.getTglbacalalu());
        pembacaan_sesudah.setText(data.getTglbacaakhir());

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(v);
        dialog.show();

    }


}
