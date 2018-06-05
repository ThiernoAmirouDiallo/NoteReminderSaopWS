package com.emiage.s12018.noteReminder.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.emiage.s12018.noteReminder.entity.Note;
import com.emiage.s12018.noteReminder.service.NoteRepository;

@RestController
public class NoteController {
	
	
	@Autowired
	private NoteRepository noteRepository;
	
	
	@RequestMapping("/notes/add")
	public Note add(){
		Note note = new Note();
		note.setCouleur("rouge");
		note.setTexte("Première note ajoutée");
		note.setEcheance(new Date().toString());
		note.setOrdre(1);
		
		noteRepository.save(note);
			
		return note;
	}
	
	@RequestMapping("/notes")
	public List<Note> getNotes(){
		return noteRepository.findAll();
	}
	
}
