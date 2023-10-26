package com.example.qcassistant.domain.dto.orderNotes;

import com.example.qcassistant.domain.dto.study.info.IqviaStudyInfoDto;
import com.example.qcassistant.domain.entity.BaseEntity;
import com.example.qcassistant.domain.entity.tag.BaseTag;
import com.example.qcassistant.domain.enums.TagType;
import com.example.qcassistant.domain.note.Note;

import java.util.ArrayList;
import java.util.Collection;

public class IqviaOrderNotesDto extends OrderNotesDto{


    protected Collection<Note> windowsNotes;
    private IqviaStudyInfoDto study;

    public IqviaOrderNotesDto(){
        this.windowsNotes = new ArrayList<>();
    }

    public IqviaStudyInfoDto getStudy() {
        return study;
    }

    public IqviaOrderNotesDto setStudy(IqviaStudyInfoDto study) {
        this.study = study;
        return this;
    }

    public boolean isStudyUnknown(){
        if(this.study == null){
            return true;
        }

        if(this.study.getName().equals(BaseEntity.UNKNOWN)){
            return true;
        }

        return false;
    }

    public boolean containsWindowsDevices(){
        return this.windowsNotes.size() > 0;
    }

    public Collection<Note> getWindowsNotes() {
        return windowsNotes;
    }

    public IqviaOrderNotesDto setWindowsNotes(Collection<Note> windowsNotes) {
        this.windowsNotes = windowsNotes;
        return this;
    }

    @Override
    public <T extends BaseTag> void addTagNote(T tag) {
        if(!tag.getType().equals(TagType.WIN)){
            super.addTagNote(tag);
        }

        Note note = new Note(tag.getSeverity(), tag.getText());
        windowsNotes.add(note);
    }
}
