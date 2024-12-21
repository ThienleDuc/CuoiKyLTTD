package com.ktck124.lop124LTDD04.nhom17.LeDucThien.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class danhSachRap extends AppCompatActivity {
    private int _maRapChieu = -1;
    private int _maTinhThanh = -1;
    private EditText editText;
    private TextWatcher textWatcher;
    private String previousSearch = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_rap);

        // Đóng Activity khi nhấn nút close
        ImageView close = findViewById(R.id.danhsachrap_close);
        close.setOnClickListener(v -> finish());

        danhSachRapChieu();
        danhSachDiaChiRap();
    }

    private void danhSachRapChieu() {
        // Hiển thị danh sách các rạp chiếu
        RecyclerView recyclerView = findViewById(R.id.danhsachrap_recycle_view);
        BL_RapChieu blRapChieu = new BL_RapChieu();
        RapChieuAdapter adapter = new RapChieuAdapter();
        blRapChieu.loadRapChieuToRecyclerView(this, recyclerView, adapter);
    }

    private void danhSachDiaChiRap() {
        // Hiển thị danh sách địa chỉ rạp chiếu
        RecyclerView recyclerView = findViewById(R.id.diachirap_recyclerview);
        BL_RapChieuCon blRapChieuCon = new BL_RapChieuCon();
        BL_RapChieu blRapChieu = new BL_RapChieu();

        SharedPreferences sharedPreferences = getSharedPreferences("LeDucThien", MODE_PRIVATE);
        _maRapChieu = sharedPreferences.getInt("maRapChieu", -1);
        if (_maRapChieu == -1 ) {
            _maRapChieu = blRapChieu.loadMinMaRapChieu();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maRapChieu", _maRapChieu);
            editor.apply();
        }

        _maTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);

        RapChieuConAdapter adapter = new RapChieuConAdapter();
        blRapChieuCon.loadRapChieuConToRecyclerView(this, recyclerView, _maTinhThanh,_maRapChieu, adapter);

        editText = findViewById(R.id.header_search_input);

        // TextWatcher để xử lý tìm kiếm
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần xử lý
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();

                // Cập nhật dữ liệu chỉ khi từ khóa tìm kiếm thay đổi
                if (!input.equals(previousSearch)) {
                    previousSearch = input;

                    if (!input.isEmpty()) {
                        Log.d("Search", "Searching for: " + input);  // Log tìm kiếm
                        blRapChieuCon.loadRapChieuConToRecyclerViewAfterSearch(
                                danhSachRap.this,
                                recyclerView,
                                _maTinhThanh,
                                _maRapChieu,
                                input,
                                adapter);
                    } else {
                        Log.d("Search", "Clearing search input");
                    }
                }
            }
        };

        // Xử lý sự kiện focus của EditText
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.addTextChangedListener(textWatcher);
            } else {
                editText.removeTextChangedListener(textWatcher);
            }
        });
    }

    // Phương thức đóng Activity
    public void dongActivity() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText.setText("");
    }
}