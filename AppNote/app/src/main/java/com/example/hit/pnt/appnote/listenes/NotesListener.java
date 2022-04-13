package com.example.hit.pnt.appnote.listenes;

import com.example.hit.pnt.appnote.entities.Note;

public interface NotesListener {
    void onNoteClick(Note note, int position);
}
