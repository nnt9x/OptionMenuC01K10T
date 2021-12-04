package com.bkacad.nnt.optionmenuc01k10t;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public abstract class MyDialog extends Dialog implements View.OnClickListener {

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void show() {
        super.show();
        edtInput.setText("");
    }

    private EditText edtInput;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);
        setCancelable(false);
        edtInput = findViewById(R.id.edtInput);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public abstract void addNewTodo(String data);

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                // Lấy dữ liệu từ Edit ->
                String input  = edtInput.getText().toString();
                if(input.isEmpty()){
                    edtInput.setError("Hãy nhập dữ liệu!");
                    return;
                }
                addNewTodo(input);
                dismiss();

                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
