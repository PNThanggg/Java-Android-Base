package com.example.hit.pnt.appnote.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hit.pnt.appnote.R;
import com.example.hit.pnt.appnote.adapter.NoteAdapter;
import com.example.hit.pnt.appnote.database.NotesDatabase;
import com.example.hit.pnt.appnote.entities.Note;
import com.example.hit.pnt.appnote.listenes.NotesListener;

import java.util.ArrayList;
import java.util.List;

// cấp quyền truy cập thư viện ảnh để lấy ảnh
// ở manifest thêm:
    //     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    //         android:requestLegacyExternalStorage="true" ở tag application

public class MainActivity extends AppCompatActivity implements NotesListener {

    public static final int REQUEST_CODE_APP_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;
    public static final int REQUEST_CODE_SHOW_NOTES = 3;
    public static final int REQUEST_CODE_SELECT_IMAGE = 4;
    public static final int REQUEST_CODE_STORAGE_PERMISSION = 5;

    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;

    private int noteClickedPosition = -1;

    private AlertDialog dialogAddURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageAdd = findViewById(R.id.imageAdd);

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNoteActivity.class), REQUEST_CODE_APP_NOTE
                );
            }
        });

        recyclerView = findViewById(R.id.noteRecycleView);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);

        getNotes(REQUEST_CODE_SHOW_NOTES, false);

        EditText inputSearch = findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                noteAdapter.cancelTimer();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                noteAdapter.searchNotes(editable.toString());
            }
        });

        findViewById(R.id.imageAddNote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNoteActivity.class), REQUEST_CODE_APP_NOTE
                );
            }
        });

        findViewById(R.id.imageAddImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                } else {
                    selectImage();
                }
            }
        });

        findViewById(R.id.imageAddURL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddURLDialog();
            }
        });
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getPathFromUri(Uri contenUri){
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contenUri, null, null, null, null);

        if(cursor == null) {
            filePath = contenUri.getPath();
        } else {
            cursor.moveToFirst();

            int index = cursor.getColumnIndex("_data");

            filePath = cursor.getString(index);
            cursor.close();
        }

        return filePath;
    }

    @Override
    public void onNoteClick(Note note, int position) {
        noteClickedPosition = position;

        Intent intent = new Intent(getApplicationContext(), CreateNoteActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);
    }

    private void getNotes(final int requestCode, final boolean isNoteDeleted){
        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>>{

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase
                        .getDatabase(getApplicationContext())
                        .noteDao().getALlNotes();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);

//                if(noteList.size() == 0){
//                    noteList.addAll(notes);
//                    noteAdapter.notifyDataSetChanged();
//                } else {
//                    noteList.add(0, notes.get(0));
//                    noteAdapter.notifyItemInserted(0);
//                }
//
//                recyclerView.smoothScrollToPosition(0);

                if(requestCode == REQUEST_CODE_SHOW_NOTES) {
                    noteList.addAll(notes);
                    noteAdapter.notifyDataSetChanged();
                } else if (requestCode == REQUEST_CODE_APP_NOTE) {
                    noteList.add(0, noteList.get(0));
                    noteAdapter.notifyItemInserted(0);
                    recyclerView.smoothScrollToPosition(0);
                } else if(requestCode == REQUEST_CODE_UPDATE_NOTE) {
                    noteList.remove(noteClickedPosition);
                    noteList.add(noteClickedPosition, noteList.get(noteClickedPosition));
                    noteAdapter.notifyItemChanged(noteClickedPosition);

                    if(isNoteDeleted) {
                        noteAdapter.notifyItemRemoved(noteClickedPosition);
                    } else {
                        noteList.add(noteClickedPosition, notes.get(noteClickedPosition));
                        noteAdapter.notifyItemRemoved(noteClickedPosition);
                    }
                }
            }
        }

        new GetNotesTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK ở phần gửi dữ liệu của class CreateNoteActivity
        if(requestCode == REQUEST_CODE_APP_NOTE && resultCode == RESULT_OK){
            getNotes(REQUEST_CODE_APP_NOTE, false);
        } else if(requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK) {
            if(data != null) {
                getNotes(REQUEST_CODE_UPDATE_NOTE, data.getBooleanExtra("isNoteDeleted", false));
            }
        } else if(requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if(data != null) {
                try {
                    String selectedImagePath = getPathFromUri(data.getData());
                    Intent intent = new Intent(getApplicationContext(), CreateNoteActivity.class);
                    intent.putExtra("isFromQuickActions", true);
                    intent.putExtra("quickActionType", "image");
                    intent.putExtra("imagePath", selectedImagePath);
                    startActivityForResult(intent, REQUEST_CODE_APP_NOTE);
                } catch (Exception ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void showAddURLDialog() {
        if(dialogAddURL == null) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);

            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_add_url,
                    (ViewGroup) findViewById(R.id.layoutAddUrlContainer)
            );

            builder.setView(view);

            dialogAddURL = builder.create();
            if(dialogAddURL.getWindow() != null){
                dialogAddURL.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            final EditText inputURL = view.findViewById(R.id.inputURL);
            inputURL.requestFocus();

            view.findViewById(R.id.textAdd).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(inputURL.getText().toString().trim().isEmpty()){
                        Toast.makeText(MainActivity.this, "Enter URL", Toast.LENGTH_LONG).show();
                    } else if(!Patterns.WEB_URL.matcher(inputURL.getText().toString()).matches()){
                        Toast.makeText(MainActivity.this, "Ener vaild URL", Toast.LENGTH_LONG).show();
                    } else {
                        dialogAddURL.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("isFromQuickActions", true);
                        intent.putExtra("quickActionType", "URL");
                        intent.putExtra("URL", inputURL.getText().toString());

                        startActivityForResult(intent, REQUEST_CODE_APP_NOTE);
                    }
                }
            });

            view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogAddURL.dismiss();
                }
            });
        }

        dialogAddURL.show();
    }
}